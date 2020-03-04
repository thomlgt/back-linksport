package fr.dta.linksport.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.dta.linksport.security.JwtAuthenticationEntryPoint;
import fr.dta.linksport.security.JwtAuthenticationFilter;
import fr.dta.linksport.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
		)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
		.userDetailsService(this.userDetailsService)
		.passwordEncoder(this.passwordEncoder());
	}
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.csrf()
		.disable()
		.exceptionHandling()
		.authenticationEntryPoint(this.unauthorizedHandler)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/",
				"/favicon.ico",
				"/**/*.png",
				"/**/*.gif",
				"/**/*.svg",
				"/**/*.jpg",
				"/**/*.html",
				"/**/*.css",
				"/**/*.js")
		.permitAll()
		.antMatchers("/api/auth/**")
		.permitAll()
		.antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
		.permitAll()
		.antMatchers(HttpMethod.GET, "/api/exemple/**", "/api/users/**", "/api/pub/**")
		.permitAll()
		.antMatchers(HttpMethod.POST, "/api/exemple/**", "/api/users/**", "/api/pub/**")
		.permitAll()
		.anyRequest()
		.authenticated();
		// Add our custom JWT security filter
		http.addFilterBefore(this.jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
