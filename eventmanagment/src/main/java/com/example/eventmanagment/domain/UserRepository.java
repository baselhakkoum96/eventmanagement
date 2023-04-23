package com.example.eventmanagment.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
		User findByUsername(String username);
		User findByEmail(String email);
		long deleteByUsername (String username);
	}