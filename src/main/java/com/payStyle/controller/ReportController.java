package com.payStyle.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.payStyle.config.auth.PrincipalDetail;
import com.payStyle.service.ReportService;
import com.payStyle.service.ReportService.monthlyProfit;
import com.payStyle.service.ReportService.monthlyValue;


@Controller
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/annualReport")
	public String addAccount(Model model,
			@RequestParam(value="startDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
			@AuthenticationPrincipal PrincipalDetail principal) throws ParseException{
		
		if(startDate==null) {
			 // 포맷터
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        // 문자열 -> Date
	        Date date = formatter.parse("2000-01-01");


	        startDate=date;
		}
		
	//	System.out.println("__________________"+reportService.월별지출데이터(principal.getUser(),startDate));
//		model.addAttribute("jsonTotal", reportService.전체차트데이터(principal.getUser(),startDate,endDate));
		
		
		
		
		List<monthlyValue> monthvalueList = reportService.월별지출데이터(principal.getUser(), startDate);
		List<monthlyProfit> monthprofitList = reportService.월별수익데이터(principal.getUser(), startDate);

		
		model.addAttribute("jsonMontlyWithdraw", reportService.월별지출차트데이터(principal.getUser(),startDate));
		model.addAttribute("jsonMontlyDeposit", reportService.월별수익차트데이터(principal.getUser(),startDate));
		model.addAttribute("monthlyWithdrawData",monthvalueList);
		model.addAttribute("monthlyDepositData",monthprofitList);

		return "report/annualReport";
	}


}
