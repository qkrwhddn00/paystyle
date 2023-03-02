package com.payStyle.controller.api;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payStyle.config.auth.PrincipalDetail;
import com.payStyle.dto.ResponseDto;
import com.payStyle.model.Account;
import com.payStyle.model.Deposit;
import com.payStyle.model.Withdraw;
import com.payStyle.repository.AccountRepository;
import com.payStyle.repository.DepositRepository;
import com.payStyle.repository.WithdrawRepository;
import com.payStyle.service.AccountService;
import com.payStyle.service.AnalysisService;

@RestController
public class AccountApiController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private WithdrawRepository withdrawRepository;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private DepositRepository depositRepository;
	@Autowired
	private AccountRepository accountRepository;
/////////////////삭제추가
@DeleteMapping("/withdraw/{id}") 
public ResponseDto<Integer> deleteWithdrawById(@PathVariable int id) {
accountService.지출삭제하기(id);
return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
}
@DeleteMapping("/deposit/{id}") 
public ResponseDto<Integer> deleteDepositById(@PathVariable int id) {
accountService.수익삭제하기(id);
return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
}
///////////////////////////
	@PostMapping("/addAccount")
	public ResponseDto<Integer> save(@RequestBody Account account, @AuthenticationPrincipal PrincipalDetail principal){
		accountService.payMethod추가(account,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@PostMapping("/thisMonthTotalAccount")
	public  String 자산_지출_수익불러오기(@AuthenticationPrincipal PrincipalDetail principal) {
		String total = "";
		LocalDate date = LocalDate.now();
		String start = date.withDayOfMonth(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String end = date.withDayOfMonth(date.lengthOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		total += accountService.총자산(principal.getUser());
		total += "!"+accountService.이번달총수익(principal.getUser(),start,end);
		total += "!"+accountService.이번달총지출(principal.getUser(),start,end);
		System.out.println("AccountApiController total:"+total);
		return total;
	}
	@PostMapping("/withdrawCategory")
	public List<Object[]> 최다지출(@AuthenticationPrincipal PrincipalDetail principal){
		return withdrawRepository.최다지출(principal.getUser());
	}
	@PostMapping("/depositCategory")
	public List<Object[]> 수익카테고리(@AuthenticationPrincipal PrincipalDetail principal){
		return depositRepository.수익카테고리(principal.getUser());
	}
	@PostMapping("/addWithdraw")
	public ResponseDto<Integer> addWithdraw(@RequestBody Withdraw withdraw, @AuthenticationPrincipal PrincipalDetail principal){
		LocalDate now = LocalDate.now();
		withdraw.setInputdate(new Date(now.getYear()-1900, now.getMonthValue()-1, now.getDayOfMonth()));
		accountService.지출추가(withdraw, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/withdraw")
	public ResponseDto<Integer> banklist(@AuthenticationPrincipal PrincipalDetail principal){
		accountService.은행목록(principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@PostMapping("/monthWithdraw")
	public List<Object[]> monthWithdraw(@AuthenticationPrincipal PrincipalDetail principal){

		return accountService.월별총지출(principal.getUser());
	}
	@PostMapping("/callPayMethod")
	public List<Object[]> payMethod호출(@AuthenticationPrincipal PrincipalDetail principal){
		return accountRepository.payMethod호출(principal.getUser());
	}
	@PostMapping("/monthDeposit")
	public List<Object[]> monthDeposit(@AuthenticationPrincipal PrincipalDetail principal){
		return  accountService.월별총수익(principal.getUser());
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	
	@PostMapping("/addDeposit")
	public ResponseDto<Integer> addDeposit(@RequestBody Deposit deposit, @AuthenticationPrincipal PrincipalDetail principal){
		LocalDate now = LocalDate.now();
		System.out.println("year : "+now.getYear());
		deposit.setInputdate(new Date(now.getYear()-1900, now.getMonthValue()-1, now.getDayOfMonth()));
		accountService.수익추가(deposit, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
//	@GetMapping("/cate-sum")
//	public ResponseEntity<Map<String, Integer>> getWithdrawSumByCate() {
//		Map<String, Integer> withdrawSumByCate = analysisService.getWithdrawSumByCate();
//		return new ResponseEntity<>(withdrawSumByCate, HttpStatus.OK);
//	}


}
