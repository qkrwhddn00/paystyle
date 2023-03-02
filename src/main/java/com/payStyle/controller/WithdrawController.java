//package com.payStyle.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.payStyle.repository.WithdrawRepository;
//import com.payStyle.service.WithdrawService;
//
//@RestController
////@RequestMapping("/api")
//public class WithdrawController {
//	 @Autowired
//	    private WithdrawService withdrawService;
//	 @Autowired
//		private WithdrawRepository withdrawRepository;
//
//	 @GetMapping("/api/withdraws/cate")
//		public List<Object[]> findByCate() {
//			return withdrawService.findByCate();
//		}
//
//}
