package fr.dta.linksport;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		BackLinksportApplication.class,
		Jsr310JpaConverters.class
})
public class BackLinksportApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BackLinksportApplication.class, args);
	}

}
