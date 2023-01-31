package com.payStyle.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payStyle.config.auth.PrincipalDetail;
import com.payStyle.dto.ResponseDto;
import com.payStyle.model.Account;
import com.payStyle.model.Withdraw;
import com.payStyle.service.AccountService;

@RestController
public class AccountApiController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/addAccount")
	public ResponseDto<Integer> save(@RequestBody Account account, @AuthenticationPrincipal PrincipalDetail principal){
		accountService.은행추가(account, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/addWithdraw")
	public ResponseDto<Integer> addWithdraw(@RequestBody Withdraw withdraw, @AuthenticationPrincipal PrincipalDetail principal){
		accountService.지출추가(withdraw, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/withdraw")
	public ResponseDto<Integer> banklist(){
		accountService.은행목록();
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
