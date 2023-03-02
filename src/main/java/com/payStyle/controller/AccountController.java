package com.payStyle.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.payStyle.config.auth.PrincipalDetail;
import com.payStyle.model.Account;
import com.payStyle.model.Deposit;
import com.payStyle.model.Withdraw;
import com.payStyle.repository.DepositRepository;
import com.payStyle.repository.WithdrawRepository;
import com.payStyle.service.AccountService;
import com.payStyle.specification.DepositSpecification;
import com.payStyle.specification.WithdrawSpecification;


@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private WithdrawRepository withdrawRepository;
	@Autowired
	private DepositRepository depositRepository;
	
	@GetMapping("/withdraw")
	public String withdraw(Model model,
			@RequestParam(value="startDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
			@RequestParam(value="endDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
			@RequestParam(value="selectChart",required=false) String selectChart,
			@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@AuthenticationPrincipal PrincipalDetail principal) throws ParseException{
		
		
		if(startDate==null&&endDate==null) {
			 // 포맷터
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        // 문자열 -> Date
	        Date date = formatter.parse("2000-01-01");
	        Date date3 = formatter.parse("2999-01-01");


	        startDate=date;
			endDate=date3;
		}
		 System.out.println("startDate : "+startDate);

		Specification<Withdraw> spec = (root, query, criteriaBuilder) -> null;

		spec=spec.and(WithdrawSpecification.betweenDate(startDate,endDate));
		
		model.addAttribute("modelBankName", accountService.은행목록(principal.getUser()));
		model.addAttribute("withdrawPage", withdrawRepository.findAll(spec,pageable));
		model.addAttribute("json", accountService.차트데이터(principal.getUser(),startDate,endDate,selectChart));
		//model.addAttribute("schedules",accountService.날짜검색(startDate,endDate));
		
		return "withdraw";
	}
	
	
	
	@GetMapping("/addAccount")
	public String addAccount() {
		return "withdraw";
	}

	
	@GetMapping("/addWithdraw")
	public String addWithdraw() {
		System.out.println("지출추가");
		return "withdraw";
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	@GetMapping("/deposit")
	public String deposit(Account account,Model model,
			@RequestParam(value="startDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
			@RequestParam(value="endDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
			@RequestParam(value="selectChart",required=false) String selectChart,
			@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@AuthenticationPrincipal PrincipalDetail principal) throws ParseException {
		
		if(startDate==null&&endDate==null) {
			 // 포맷터
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        // 문자열 -> Date
	        Date date = formatter.parse("2000-01-01");
	        Date date3 = formatter.parse("2999-01-01");


	        startDate=date;
			endDate=date3;
		}
		 System.out.println("startDate : "+startDate);

		Specification<Deposit> spec = (root, query, criteriaBuilder) -> null;

		spec=spec.and(DepositSpecification.betweenDate(startDate,endDate));
		
		model.addAttribute("modelBankName", accountService.은행목록(principal.getUser()));
		model.addAttribute("depositPage", depositRepository.findAll(spec,pageable));
		model.addAttribute("json", accountService.차트데이터1(principal.getUser(),startDate,endDate,selectChart));
		//model.addAttribute("schedules",accountService.날짜검색(startDate,endDate));
		
		return "deposit";
	}

	
	@GetMapping("/addDeposit")
	public String addDeposit() {
		System.out.println("수익추가");
		return "deposit";
	}

}
