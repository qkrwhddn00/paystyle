package com.payStyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payStyle.model.Withdraw;

public interface WithdrawRepository extends JpaRepository<Withdraw, Integer>{

	

}
