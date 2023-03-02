package com.payStyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.payStyle.repository.UseridCheckRepository;
import com.payStyle.service.UserService;



@Controller
public class UsersController {
	@Autowired
	UseridCheckRepository useridCheckRepository;
	@Autowired
	UserService userService;
	@GetMapping({"","/"})
	public String index() {
		return "index";
	}
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	@GetMapping("/auth/findId")
	public String findIdView() {
		return "user/findId";
	}
	
	@GetMapping("/auth/findId/{email}")
	public String findId(Model model,@PathVariable String email) {
		if(useridCheckRepository.findByEmail(email)!=null) {
		model.addAttribute("email",useridCheckRepository.findByEmail(email));
//		입력전에는 model이 null 값 
		}
		return "user/findId";
	}
	@GetMapping("/userUpdate")
	public String updateForm() {
		return "user/updateForm";
	}
	@GetMapping("/calendar")
	public String calForm(){return "calendar";}
	// 카카오톡 로그인
		@GetMapping("/auth/kakao/callback")
		public String kakaoCallback(String code) { // Data를 리턴해주는 컨트롤러 함수
			userService.카카오로그인(code);
			// post 방식으로 key=value데이터를 요청(카카오쪽으로)
			// a태그를 통해 전달하는 방식은 get방식

			

			return "redirect:/";
		}
	
	
}
