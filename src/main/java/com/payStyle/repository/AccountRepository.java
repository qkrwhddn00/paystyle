package com.payStyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payStyle.model.Account;
import com.payStyle.model.Withdraw;

public interface AccountRepository extends JpaRepository<Account, Integer>{



}
