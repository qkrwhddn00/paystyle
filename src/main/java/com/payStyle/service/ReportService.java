package com.payStyle.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.payStyle.model.Deposit;
import com.payStyle.model.Users;
import com.payStyle.model.Withdraw;
import com.payStyle.repository.AccountRepository;
import com.payStyle.repository.DepositRepository;
import com.payStyle.repository.WithdrawRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReportService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private WithdrawRepository withdrawRepository;
	@Autowired
	private DepositRepository depositRepository;



	public List<monthlyValue> 월별지출데이터(Users user, Date startDate) {
	    List<monthlyValue> arrayValues = new ArrayList<>();
	    String startDate1 = new SimpleDateFormat("yyyy").format(startDate);
	    for (int i = 1; i <= 12; i++) {
			
			System.out.println("year : "+startDate1);
			System.out.println("user : "+user.getId());
			
			String mm = null;
			if(i<10) {
				mm = "0"+String.valueOf(i);
			}else {
				mm = String.valueOf(i);
			}
			
			System.out.println("month : "+mm);
			
	        Integer totalValue = withdrawRepository.findValueMonthBycreateDate(startDate1, mm, user.getId());
	        Integer foodmonthlyValue=withdrawRepository.findValueMonthFoodBycreateDate(startDate1, mm, user.getId());
	        Integer housemonthlyValue=withdrawRepository.findValueMonthHouseBycreateDate(startDate1, mm, user.getId());
	        Integer transportmonthlyValue=withdrawRepository.findValueMonthTransportBycreateDate(startDate1, mm, user.getId());
	        Integer edulyValue=withdrawRepository.findValueMonthEduBycreateDate(startDate1, mm, user.getId());
	        Integer taxmonthlyValue=withdrawRepository.findValueMonthTaxBycreateDate(startDate1, mm, user.getId());
	        Integer duesmonthlyValue=withdrawRepository.findValueMonthDuesBycreateDate(startDate1, mm, user.getId());
	        Integer clothesmonthlyValue=withdrawRepository.findValueMonthClothesBycreateDate(startDate1, mm, user.getId());
	        
	        if(totalValue==null) {
	        	totalValue = 0;
	        }
	        if(foodmonthlyValue==null) {
	        	foodmonthlyValue = 0;
	        }
	        if(housemonthlyValue==null) {
	        	housemonthlyValue = 0;
	        }
	        if(transportmonthlyValue==null) {
	        	transportmonthlyValue = 0;
	        }
	        if(edulyValue==null) {
	        	edulyValue = 0;
	        }
	        if(taxmonthlyValue==null) {
	        	taxmonthlyValue = 0;
	        }
	        if(duesmonthlyValue==null) {
	        	duesmonthlyValue = 0;
	        }
	        if(clothesmonthlyValue==null) {
	        	clothesmonthlyValue = 0;
	        }
	        System.out.println("MMMMMMMMMMMMMMMMM : "+totalValue);
	        arrayValues.add(new monthlyValue(totalValue,foodmonthlyValue,housemonthlyValue,
	        		transportmonthlyValue,edulyValue,taxmonthlyValue,duesmonthlyValue,clothesmonthlyValue));
	    }
	    	    
	    System.out.println("+_________________"+arrayValues);
	    
	    return arrayValues;
	    
	}
	
	@Data
	public class monthlyValue{
		int totalValue;
		int food;
		int house;
		int transport;
		int edu;
		int tax;
		int dues;
		int clothes;
	    monthlyValue(int totalValue,int food,int house,int transport,
	    		int edu,int tax,int dues,int clothes){
	        this.totalValue =  totalValue;
	        this.food = food;
	        this.house=house;
	        this.transport=transport;
	        this.edu=edu;
	        this.tax=tax;
	        this.dues=dues;
	        this.clothes=clothes;
	    }
	    
	}
	
	public List<monthlyProfit> 월별수익데이터(Users user, Date startDate) {
	    List<monthlyProfit> arrayValues = new ArrayList<>();
	    String startDate1 = new SimpleDateFormat("yyyy").format(startDate);
	    for (int i = 1; i <= 12; i++) {
			
			System.out.println("year : "+startDate1);
			System.out.println("user : "+user.getId());
			
			String mm = null;
			if(i<10) {
				mm = "0"+String.valueOf(i);
			}else {
				mm = String.valueOf(i);
			}
			
			System.out.println("month : "+mm);
			
	        Integer totalProfit = depositRepository.findProfitMonthBycreateDate(startDate1, mm, user.getId());
	        Integer salarymonthlyValue=depositRepository.findProfitMonthSalaryBycreateDate(startDate1, mm, user.getId());
	        Integer investmonthlyValue=depositRepository.findProfitMonthInvestdBycreateDate(startDate1, mm, user.getId());
	        Integer pinmoneymonthlyValue=depositRepository.findProfitMonthPinmoneyBycreateDate(startDate1, mm, user.getId());
	        Integer etclyValue=depositRepository.findProfitMonthEtcBycreateDate(startDate1, mm, user.getId());
	        
	        if(totalProfit==null) {
	        	totalProfit = 0;
	        }
	        if(salarymonthlyValue==null) {
	        	salarymonthlyValue = 0;
	        }
	        if(investmonthlyValue==null) {
	        	investmonthlyValue = 0;
	        }
	        if(pinmoneymonthlyValue==null) {
	        	pinmoneymonthlyValue = 0;
	        }
	        if(etclyValue==null) {
	        	etclyValue = 0;
	        }
	       
	        System.out.println("MMMMMMMMMMMMMMMMM : "+totalProfit);
	        arrayValues.add(new monthlyProfit(totalProfit,salarymonthlyValue,investmonthlyValue,
	        		pinmoneymonthlyValue,etclyValue));
	    }
	    	    
	    System.out.println("+_________________"+arrayValues);
	    
	    return arrayValues;
	    
	}
	
	
	@Data
	public class monthlyProfit{
		int totalProfit;
		int salary;
		int invest;
		int pinmoney;
		int etc;
		monthlyProfit(int totalProfit,int salary,int invest,int pinmoney, int etc){
	        this.totalProfit =  totalProfit;
	        this.salary = salary;
	        this.invest=invest;
	        this.pinmoney=pinmoney;
	        this.etc=etc;
	    }
	    
	}
	
	
	@Transactional
	public String 월별지출차트데이터(Users user, Date startDate) {
		List<Withdraw> withdrawchartList = withdrawRepository.findByUsers(user);
		Gson gson = new Gson();
		JsonArray jArray = new JsonArray();
		Iterator<Withdraw> it = withdrawchartList.iterator();
		while (it.hasNext()) {
			Withdraw chartWithdraw = it.next();
			JsonObject object = new JsonObject();

			String cate = chartWithdraw.getCategory();// 지출방식 이름
			String bankName = chartWithdraw.getPayMethod();// 은행 이름
			String checkDate = new SimpleDateFormat("yyyy-MM-dd").format(chartWithdraw.getCreateDate());
			String startDate1 = new SimpleDateFormat("yyyy-MM-dd").format(startDate);

			int value = chartWithdraw.getValue();// 지출값
			int bankValue = withdrawRepository.findValueByBank(bankName);
			int cateValue = withdrawRepository.findValueByCategory(cate);
			// 연도 동일한 데이터만 가져오기
			if (startDate1.substring(0, 4).equals(checkDate.substring(0, 4))) {
				for (int i = 1; i <= 12; i++) {
					// System.out.println("------------------------"+String.valueOf(i));
					if (Integer.parseInt(checkDate.substring(5, 7)) == i) {// "01" "02"
						// addProperty에 넣으려면 timestamp를 string으로 변환
						object.addProperty("startDate", startDate1);// 시작날짜
						object.addProperty("checkDate", checkDate);// 지출값
						object.addProperty("value", value);// 지출값
						object.addProperty("cate", cate);// 지출방식 이름
						object.addProperty("bankValue", bankValue);// 은행이름으로 group by한 지출값
						object.addProperty("cateValue", cateValue);// 지출별 group by한 지출값
						object.addProperty("bankName", bankName);// 은행 이름
						object.addProperty("month",i);
						//monthArray.add(object);// i문자열 String.valueOf(i)
						//monthObject.add(String.valueOf(i), monthArray);
					}
				}
				jArray.add(object);
				// System.out.println("MMMMMMMMMMMMMMMMM"+jArray);

//				[연도별json{월별jArray2(jArray)()()()} 연도별{월별(jArray)()()()} 연도별{월별(jArray)()()()}]

			}
		}

		String json = gson.toJson(jArray);
		return json;
	}

	@Transactional
	public String 월별수익차트데이터(Users user, Date startDate) {
		List<Deposit> depositchartList = depositRepository.findByUsers(user);
//	List<Withdraw> chartList = withdrawRepository.findAll();
		Gson gson = new Gson();
		JsonArray jArray = new JsonArray();
		Iterator<Deposit> it = depositchartList.iterator();

		while (it.hasNext()) {
			Deposit chartDeposit = it.next();
			JsonObject object = new JsonObject();
			int profit = chartDeposit.getProfit();// 지출값
			String sort = chartDeposit.getCategory();// 지출방식 이름
			String bankName = chartDeposit.getPayMethod();// 은행 이름
			

			int bankProfit = depositRepository.findProfitByBank(bankName);
			int sortProfit = depositRepository.findProfitByCategory(sort);
			String startDate1 = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
			// addProperty에 넣으려면 timestamp를 string으로 변환
			String checkDate = new SimpleDateFormat("yyyy-MM-dd").format(chartDeposit.getCreateDate());
			// 연도 동일한 데이터만 가져오기
			if (startDate1.substring(0, 4).equals(checkDate.substring(0, 4))) {// 연도 확인
				for (int i = 1; i <= 12; i++) {// 월별 확인
					// System.out.println("------------------------"+String.valueOf(i));
					if (Integer.parseInt(checkDate.substring(5, 7)) == i) {// "01" "02" string startDate1-> int
						// addProperty에 넣으려면 timestamp를 string으로 변환
						object.addProperty("startDate", startDate1);// 시작날짜
						object.addProperty("checkDate", checkDate);// 지출값
						object.addProperty("profit", profit);// 지출값
						object.addProperty("sort", sort);// 지출방식 이름
						object.addProperty("bankProfit", bankProfit);// 은행이름으로 group by한 지출값
						object.addProperty("sortProfit", sortProfit);// 지출별 group by한 지출값
						object.addProperty("bankName", bankName);// 은행 이름
						object.addProperty("month",String.valueOf(i));
						
//						System.out.println("______" + String.valueOf(i));
					}
				}
				jArray.add(object);
				// System.out.println("MMMMMMMMMMMMMMMMM"+jArray);
//				json[연도별jArray{월별monthObject(object)()()()} 연도별jArray{월별monthObject(object)()()()} 연도별jArray{월별monthObject(object)()()()}]

			}
//		System.out.println("DDDDDDDDDD"+startDate1.substring(5,7)+"DDDDDDDD"+startDate1);
		}
		String json = gson.toJson(jArray);
//	System.out.println("월별수익차트 : "+jArray);
		return json;
	}

}
