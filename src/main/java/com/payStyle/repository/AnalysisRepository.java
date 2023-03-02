package com.payStyle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payStyle.model.Users;
import com.payStyle.model.Withdraw;

public interface AnalysisRepository extends JpaRepository<Withdraw, Integer>{
	@Query(value = "SELECT category, SUM(value) FROM withdraws GROUP BY userid,category having userid=:userid ORDER BY SUM(value) DESC", nativeQuery = true)
	List<Object[]> findByUserAndCate(Users userid);
	

}
