package fr.dta.linksport.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.dta.linksport.domain.Sport;
import fr.dta.linksport.domain.User;




@Repository
public interface IUserDao extends JpaRepository<User, Long>{
	Optional<User> findByUsernameOrEmail(String username, String email);
	List<User> findByIdIn(List<Long> userIds);
	Optional<User> findByUsername(String username);
	List<User> findBySport(Sport sport);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);

	@Query("SELECT u FROM User u " + "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerm, '%')) "
			+ "ORDER BY u.username")
	Page<User> findUsers(@Param("searchTerm") String searchTerm, Pageable pageRequest);

	//	@Query("SELECT u FROM User u " +
	//            "WHERE (:user) MEMBER OF u.followerOf " +
	//            "   AND LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
	//            "ORDER BY u.username")
	//    Page<User> findFollowers(
	//            @Param("user") User user,
	//            @Param("searchTerm") String searchTerm,
	//            Pageable pageRequest);
	//
	//	@Query("SELECT u FROM User u " +
	//            "WHERE (:user) MEMBER OF u.followed " +
	//            "   AND LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
	//            "ORDER BY u.username")
	//    Page<User> findFolloweds(
	//            @Param("user") User user,
	//            @Param("searchTerm") String searchTerm,
	//            Pageable pageRequest);
}
