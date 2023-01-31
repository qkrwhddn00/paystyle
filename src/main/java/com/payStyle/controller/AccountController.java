package com.payStyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.payStyle.model.Account;
import com.payStyle.service.AccountService;


@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/withdraw")
	public String withdraw(Account account,Model model,
			@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("modelBankName", accountService.은행목록());
		model.addAttribute("withdrawPage", accountService.지출목록(pageable));
		return "withdraw";
	}
	
	@GetMapping("/addAccount")
	public String addAccount() {
		return "withdraw";
	}
	@GetMapping("/addWithdraw")
	public String addWithdraw() {
		return "withdraw";
	}
	
}
