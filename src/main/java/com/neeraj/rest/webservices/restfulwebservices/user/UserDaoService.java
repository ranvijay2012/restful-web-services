package com.neeraj.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();

	private static int counter = 3;
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++counter);
		}
		users.add(user);
		return user;
	}

	public User findOne(int Id) {
		for (User user : users) {
			;
			if (user.getId() == Id) {
				return user;
			}
		}
		return null;
	}
	
}
