package com.payStyle.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.payStyle.dto.ResponseDto;
import com.payStyle.model.Users;
import com.payStyle.service.UserService;

@RestController
public class UserApiController {
	@Autowired 
	private UserService userService;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody Users user) {
		System.out.println("UserApiController 호출됨");
		userService.회원가입(user);
		System.out.println("회원가입 실행");
		return new ResponseDto<>(HttpStatus.OK.value(), 1);
	}

	@ResponseBody
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody Users user) {
		userService.회원수정(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	

}
