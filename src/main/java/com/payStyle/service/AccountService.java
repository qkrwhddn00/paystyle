package com.payStyle.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.payStyle.model.Account;
import com.payStyle.model.Deposit;
import com.payStyle.model.Users;
import com.payStyle.model.Withdraw;
import com.payStyle.repository.AccountRepository;
import com.payStyle.repository.DepositRepository;
import com.payStyle.repository.WithdrawRepository;
import com.sun.istack.Nullable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private WithdrawRepository withdrawRepository;
	@Autowired
	private DepositRepository depositRepository;
	
	@Transactional
	public Account save(Users user, @Nullable Withdraw withdraw, @Nullable Deposit deposit,
						java.sql.Date date){
		int balance = 0;
		String method = "";
		if (withdraw!=null){
			balance -= withdraw.getValue(); //지출이면 마이너스
			method = withdraw.getPayMethod();
		}else {
			balance += deposit.getProfit(); //수익이면 플러스
			method = deposit.getPayMethod();
		}
		
		
		//유저아이디와 현재날짜로 조회해서 없으면 insert 있으면 update
		Account acc = accountRepository.findByUsersAndPayMethodAndInputdate(user,method,date).orElseGet(Account::new);
		//계산해서 넣어주기
		if(acc.getUsers() == null) {
			acc.setInputdate(date);
			acc.setBalance(balance);
			acc.setPayMethod(method);
			acc.setUsers(user);
			accountRepository.save(acc);
			
		}else {
			acc.setBalance(acc.getBalance()+balance);
			
		}
		
		System.out.println("***********************************************************acc : "+acc);
		return acc;
	}
////////////////////////삭제 추가
@Transactional
public void 지출삭제하기(int id) {
	Withdraw withdraw = withdrawRepository.findAllById(id);
	Account acc = accountRepository.findAllByUsersAndInputdate(withdraw.getUsers(),withdraw.getInputdate());
	acc.setBalance(acc.getBalance()+withdraw.getValue());
	withdrawRepository.deleteById(id);

}
@Transactional
public void 수익삭제하기(int id) {
	Deposit deposit = depositRepository.findAllById(id);
	Account acc = accountRepository.findAllByUsersAndInputdate(deposit.getUsers(),deposit.getInputdate());
	acc.setBalance(acc.getBalance()-deposit.getProfit());
	depositRepository.deleteById(id);
}
//////////////////////
	@Transactional
	public int 총자산(Users user){
		return accountRepository.findBalanceByUsers(user);
	}
	@Transactional
	public List<Object[]> 월별총지출(Users user){
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String sdate = year+"-01-01";
		String edate = year+"-12-31";
		java.sql.Date start = java.sql.Date.valueOf(sdate);
		java.sql.Date end = java.sql.Date.valueOf(edate);
		return withdrawRepository.월별지출(user,start);
	}
	@Transactional
	public List<Object[]> 월별총수익(Users user){
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		System.out.println(year);
		String sdate = year+"-01-01";
		String edate = year+"-12-31";
		java.sql.Date start = java.sql.Date.valueOf(sdate);
		java.sql.Date end = java.sql.Date.valueOf(edate);
		return depositRepository.월별수익(user,start,end);
	}
	@Transactional
	public int 이번달총지출(Users user, String start , String end)  {
		java.sql.Date start1 = java.sql.Date.valueOf(start);
		java.sql.Date end1 = java.sql.Date.valueOf(end);
		return withdrawRepository.이번달총지출(start1,user);
	}
	@Transactional
	public int 이번달총수익(Users user, String start , String end)  {
		java.sql.Date start1 = java.sql.Date.valueOf(start);
		java.sql.Date end1 = java.sql.Date.valueOf(end);
		return depositRepository.이번달총수익(start1,user);
	}
	@Transactional
	public List 날짜검색(String 시작날짜,String 끝날짜) {
		return withdrawRepository.findByCreateDateBetween(시작날짜,끝날짜);
	}
	
	@Transactional
	public String 차트데이터(Users user,Date startDate,Date endDate,String selectChart){
		List<Withdraw> chartList = withdrawRepository.findByUsers(user);
		Gson gson = new Gson();
		JsonArray jArray = new JsonArray();
		Iterator<Withdraw> it = chartList.iterator();
		while(it.hasNext()) {
			Withdraw chartWithdraw = it.next();
			JsonObject object = new JsonObject();
			
			int value = chartWithdraw.getValue();//지출값
			String cate= chartWithdraw.getCategory();//지출방식 이름
			String bankName=chartWithdraw.getPayMethod();//은행 이름
			String checkDate = new SimpleDateFormat("yyyy-MM-dd").format(chartWithdraw.getCreateDate());
			String startDate1 = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
			String endDate1 = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
			

			
			int bankValue=withdrawRepository.findValueByBank(bankName);
			int cateValue=withdrawRepository.findValueByCategory(cate);
			
			//addProperty에 넣으려면 timestamp를 string으로 변환
			object.addProperty("startDate", startDate1);//시작날짜
			object.addProperty("endDate", endDate1);//끝날짜
			object.addProperty("selectChart", selectChart);//선택한 차트 종류
			object.addProperty("checkDate", checkDate);//지출값
		    object.addProperty("value", value);//지출값
		    object.addProperty("cate", cate);//지출방식 이름
		    object.addProperty("bankValue", bankValue);//은행이름으로 group by한 지출값
		    object.addProperty("cateValue", cateValue);//지출별 group by한 지출값
		    object.addProperty("bankName", bankName);//은행 이름
			jArray.add(object);
		}
		String json = gson.toJson(jArray);
		return json;
		}
	
	@Transactional
	public void payMethod추가(Account account, Users user) {
		account.setBalance(0);
		account.setUsers(user);
		accountRepository.save(account);
	}
	@Transactional
	public void 지출추가(Withdraw withdraw, Users user) {
		withdraw.setUsers(user);
		withdrawRepository.save(withdraw);
		save(user,withdraw,null,withdraw.getInputdate());
	}
	
	@Transactional
	public List<Account> 은행목록(Users user){
		return accountRepository.findByUsers(user);
	}

//	@Transactional
//	public Page<Withdraw> 지출목록(spec,Pageable pageable){
//		return withdrawRepository.findAll(specpageable);
//		}

	
//	@Transactional
//	public List<Withdraw> 지출차트(Users user){
//		return withdrawRepository.findById(user.getId());
//	
//	}
	
	/////////////////////////////////////////////////////////////////////
	
	@Transactional
	public String 차트데이터1(Users user,Date startDate,Date endDate,String selectChart) {
	List<Deposit> chartList = depositRepository.findByUsers(user);
//	List<Withdraw> chartList = withdrawRepository.findAll();
	Gson gson = new Gson();
	JsonArray jArray = new JsonArray();
	Iterator<Deposit> it = chartList.iterator();
	while(it.hasNext()) {
		Deposit chartDeposit = it.next();
		JsonObject object = new JsonObject();
		
		int profit = chartDeposit.getProfit();//지출값
		String sort= chartDeposit.getCategory();//지출방식 이름
		String bankName=chartDeposit.getPayMethod();//은행 이름
		
		int bankProfit=depositRepository.findProfitByBank(bankName);
		int sortProfit=depositRepository.findProfitByCategory(sort);
		String startDate1 = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
		String endDate1 = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
		//addProperty에 넣으려면 timestamp를 string으로 변환
		String checkDate = new SimpleDateFormat("yyyy-MM-dd").format(chartDeposit.getCreateDate());
		object.addProperty("selectChart", selectChart);
		object.addProperty("startDate", startDate1);
		object.addProperty("endDate", endDate1);
		object.addProperty("checkDate", checkDate);//지출값
	    object.addProperty("profit", profit);//지출값
	    object.addProperty("sort", sort);//지출방식 이름
	    object.addProperty("bankProfit", bankProfit);//은행이름으로 group by한 지출값
	    object.addProperty("sortProfit", sortProfit);//지출별 group by한 지출값
	    object.addProperty("bankName", bankName);//은행 이름
		jArray.add(object);
	}
	String json = gson.toJson(jArray);
	return json;
	}
	
	@Transactional
	public void 수익추가(Deposit deposit, Users user) {
		deposit.setUsers(user);
		depositRepository.save(deposit);
		save(user,null,deposit,deposit.getInputdate());
	}
	
	
	@Transactional
	public Page<Deposit> 수익목록(Pageable pageable){
		return depositRepository.findAll(pageable);
		}
	
	
}
