package com.payStyle.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.payStyle.model.Users;
import com.payStyle.model.Withdraw;
import com.payStyle.repository.AnalysisRepository;
import com.payStyle.repository.WithdrawRepository;

@Service
public class AnalysisService {
	

	@Autowired
	private WithdrawRepository withdrawRepository;

	@Autowired
    private AnalysisRepository analysisRepository;
	
	@Transactional
	public List 날짜검색(String 시작날짜,String 끝날짜) {
		return withdrawRepository.findByCreateDateBetween(시작날짜,끝날짜);
	}
	
	@Transactional
	public String 테이블데이터(Users user,Date startDate,Date endDate){
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
	

//    public List<Object[]> findByUserAndCate(Users user) {
//		return analysisRepository.findByUserAndCate(user);
//	}
 
}
