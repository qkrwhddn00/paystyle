package com.payStyle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payStyle.model.Users;

public interface UseridCheckRepository  extends JpaRepository<Users, Integer>{
	List<Users> findByEmail(String email);
	Users findByUserid(Users idCheck);
 
}
