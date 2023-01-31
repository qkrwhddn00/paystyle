package com.payStyle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payStyle.model.Account;
import com.payStyle.model.Users;
import com.payStyle.model.Withdraw;
import com.payStyle.repository.AccountRepository;
import com.payStyle.repository.WithdrawRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private WithdrawRepository withdrawRepository;
	
	
	@Transactional
	public void 은행추가(Account account, Users user) {
		account.setBalance(0);
		account.setUsers(user);
		accountRepository.save(account);
	}
	@Transactional
	public void 지출추가(Withdraw withdraw, Users user) {
		withdraw.setUsers(user);
		withdrawRepository.save(withdraw);
	}
	
	@Transactional
	public List<Account> 은행목록(){
		return accountRepository.findAll();
		}
	@Transactional
	public Page<Withdraw> 지출목록(Pageable pageable){
		return withdrawRepository.findAll(pageable);
		}
}
