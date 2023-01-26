package com.payStyle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payStyle.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByUserid(String userid);

}
