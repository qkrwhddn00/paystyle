package com.payStyle.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payStyle.config.auth.PrincipalDetail;
import com.payStyle.model.Users;
import com.payStyle.service.AnalysisService;

@RestController
public class AnalysisApiController {
	@Autowired
    private AnalysisService analysisService;


//    @GetMapping("/api/withdraws/cate")
//	public List<Object[]> findByUserAndCate(@AuthenticationPrincipal PrincipalDetail principal) {
//		return analysisService.findByUserAndCate(principal.getUser());
//	}


}
