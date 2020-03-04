package fr.dta.linksport.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.dta.linksport.domain.User;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FollowService {
//	@Transactional(readOnly = true)
//	public Page<Person> getFriends(Person person, String searchTerm, Pageable pageRequest) {
//		return personRepository.findFriends(person, searchTerm, pageRequest);
//	}
//
//	@Transactional(readOnly = true)
//	public Page<Person> getFriendOf(Person person, String searchTerm, Pageable pageRequest) {
//		return personRepository.findFriendOf(person, searchTerm, pageRequest);
//	}

	@Transactional
	public static void addFollow(User follower, User followed) {
		if (!followed.hasFollower(follower)) {
			followed.addFollower(follower);
		}
	}

	@Transactional
	public static void removeFollow(User follower, User followed) {
		if (followed.hasFollower(follower)) {
			followed.removeFollow(follower);
        }
	}

}
