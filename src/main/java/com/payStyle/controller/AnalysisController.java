package com.payStyle.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.payStyle.model.Withdraw;
import com.payStyle.repository.AnalysisRepository;
import com.payStyle.repository.WithdrawRepository;
import com.payStyle.service.AccountService;
import com.payStyle.service.AnalysisService;
import com.payStyle.service.ReportService;
import com.payStyle.specification.AnalysisSpecification;

@Controller
public class AnalysisController {
	
	@Autowired
	private WithdrawRepository withdrawRepository;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AnalysisService analysisService;
	@Autowired
	private AnalysisRepository analysisRepository;
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/analysis")
	public String analysis(Model model,
			@RequestParam(value="startDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,

			@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@AuthenticationPrincipal PrincipalDetail principal) throws ParseException{
		
		
		if(startDate==null) {
			 // 포맷터
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        // 문자열 -> Date
	        
	        Date date = formatter.parse("2000-01-01");
	        Date date3 = formatter.parse("2999-01-01");


	        startDate=date;

		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.DATE, 1);
		Date firstDayOfMonth = cal.getTime();
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		Date lastDayOfMonth = cal.getTime();
		 System.out.println("startDate : "+startDate);

		Specification<Withdraw> spec = (root, query, criteriaBuilder) -> null;

		spec=spec.and(AnalysisSpecification.betweenDate(firstDayOfMonth,lastDayOfMonth));
		
//		model.addAttribute("analysisPage", analysisRepository.find(spec,pageable));
		model.addAttribute("analysisPage", withdrawRepository.findAll(spec,pageable));
		model.addAttribute("json", analysisService.테이블데이터(principal.getUser(),firstDayOfMonth,lastDayOfMonth));
        model.addAttribute("analysisPages",analysisRepository.findByUserAndCate(principal.getUser()));
        
		//model.addAttribute("schedules",accountService.날짜검색(startDate,endDate));
        //추가
        model.addAttribute("jsonMontlyWithdraw", reportService.월별지출차트데이터(principal.getUser(),startDate));
		model.addAttribute("jsonMontlyDeposit", reportService.월별수익차트데이터(principal.getUser(),startDate));
		
		return "write/analysis";
	}
	
}
