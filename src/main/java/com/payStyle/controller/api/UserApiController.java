package com.payStyle.controller.api;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payStyle.dto.ResponseDto;
import com.payStyle.model.Account;
import com.payStyle.model.Deposit;
import com.payStyle.model.Users;
import com.payStyle.model.Withdraw;
import com.payStyle.repository.UserRepository;
import com.payStyle.service.AccountService;
import com.payStyle.service.UserService;

@RestController
public class UserApiController {
	@Autowired 
	private UserService userService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserRepository userRepository;
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody Users user) {
		 //회원가입후 조회해서 자동생성된 ID 찾기
		if(!userRepository.existsByUserid(user.getUserid())) {
			userService.회원가입(user);
			Users 불러온유저 = userService.회원찾기(user);
			회원가입account(불러온유저);
			회원가입withdraw(불러온유저);
			회원가입deposit(불러온유저);
			System.out.println("회원가입 실행");
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
			}else if(userRepository.existsByUserid(user.getUserid())) {
			return new ResponseDto<Integer>(HttpStatus.OK.value(), -1);	
			}
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 0);	
	}
	public void 회원가입account (Users user){
		Account account = new Account();
		account.setUsers(user);
		account.setBalance(0);
		account.setPayMethod("현금");
		account.setAccountNum(0);
		account.setInputdate(new Date(System.currentTimeMillis())); //현재 시간을 불러와서 yyyy-MM-dd 형태로 저장
		accountService.payMethod추가(account,user);
	}
	public void 회원가입withdraw(Users user){
		Withdraw withdraw = new Withdraw();
		withdraw.setCategory("회원가입");
		withdraw.setValue(0);
		withdraw.setPayMethod("현금");
		withdraw.setInputdate(new Date(System.currentTimeMillis()));
		accountService.지출추가(withdraw,user);
	}
	public void 회원가입deposit(Users user){
		Deposit deposit = new Deposit();
		deposit.setCategory("회원가입");
		deposit.setProfit(0);
		deposit.setPayMethod("현금");
		deposit.setInputdate(new Date(System.currentTimeMillis()));
		accountService.수익추가(deposit,user);
	}


	@PutMapping("/userUpdate")
	public ResponseDto<Integer> update(@RequestBody Users user) {
		userService.회원수정(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	

	@DeleteMapping("/delete/{id}")
	public ResponseDto<Integer> delete(@PathVariable int id) {
		userService.회원삭제(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	@PostMapping("/auth/idCheckView")
	public ResponseDto<Integer> useridCheckView(@RequestBody Users user) {
		System.out.println("UserApicontroller idCheckView userid String : "+user);
		if(!userRepository.existsByUserid(user.getUserid())) {
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
			}else if(userRepository.existsByUserid(user.getUserid())) {
			return new ResponseDto<Integer>(HttpStatus.OK.value(), -1);	
			}
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 0);	
	}
}
