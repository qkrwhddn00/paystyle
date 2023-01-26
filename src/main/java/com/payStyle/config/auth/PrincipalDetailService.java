package com.payStyle.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.payStyle.model.Users;
import com.payStyle.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService { //실제로 가로챌 녀석
	@Autowired
	private UserRepository userRepository;
	
	//스프링이 로그인 요청을 가로챌때 username, password 변수 2개를 가로채는데
	//pasword 부분처리는 알아서 함.
	//username이 DB에 있는지만 확인해주면 된다.
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException{
		System.out.println("UserDetails"+userid);
		Users principal = userRepository.findByUserid(userid).orElseThrow(()->{
			return new UsernameNotFoundException("해당 사용자를 찾을수없습니다. : "+userid);
		});
		System.out.println("userid:" + principal.getUserid());
		System.out.println("userpwd:" + principal.getPassword());
		System.out.println("username:" +principal.getUsername());
		System.out.println("UserDetails"+principal);
		return new PrincipalDetail(principal);
		//시큐리티 세션에 유저정보가 저장이 된다.
		//그때 타입이 userdetails 타입이다.
	}

}
