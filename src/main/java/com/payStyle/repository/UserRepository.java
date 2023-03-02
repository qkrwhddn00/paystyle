package com.payStyle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payStyle.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByUserid(String userid);
	Users findByEmail(String email);
	boolean existsByUserid(String string);
	
	Optional<Users> findByUsername(String username);
}
