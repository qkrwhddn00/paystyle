package com.payStyle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payStyle.model.KakaoProfile;
import com.payStyle.model.OAuthToken;
import com.payStyle.model.RoleType;
import com.payStyle.model.Users;
import com.payStyle.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encodeer;
	@Autowired
	private final AuthenticationManager authenticationManager;

	@Value("${cos.key}")
	private String cosKey;

	// 회원가입시 작동
	@Transactional
	public void 회원가입(Users user) {

		String rawPassword = user.getPassword(); // 원본
		String encPassword = encodeer.encode(rawPassword); // 암호화
		user.setPassword(encPassword);
		user.setRoles(RoleType.USER);
		userRepository.save(user); // 하나의 트랙잭션
	}
	@Transactional
	public Users 회원찾기(Users user){
		return userRepository.findByEmail(user.getEmail());
	}

	@Transactional
	public void 회원수정(Users user) {
		Users persistance = userRepository.findByUserid(user.getUserid()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		
	}
@Transactional
	public void 회원삭제(int id) {
		userRepository.deleteById(id);
	}



@Transactional
public void 카카오로그인(String code) {

	RestTemplate rt = new RestTemplate();

	// httpheader오브젝트 생성
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

	// httpbody오브젝트 생성
	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	params.add("grant_type", "authorization_code");
	params.add("client_id", "8389bb661384d76130a94d659e880757");
	params.add("redirect_uri", "http://localhost:8010/auth/kakao/callback");
	params.add("code", code);

	// httpheader와 httpbody를 하나의 오브젝트에 담기
	HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest2 = new HttpEntity<>(params, headers);// body와
																										// header값을
																										// 가지는
																										// entity
	// http 요청하기-post방식으로-그리고 response변수의 응답 받음
	ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
			kakaoTokenRequest2, String.class);

	ObjectMapper objectMapper = new ObjectMapper();
	OAuthToken oauthToken = null;
	try {
		oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);

	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	System.out.println("카카오 엑세스 토큰" + oauthToken.getAccess_token());

	RestTemplate rt2 = new RestTemplate();

	// httpheader오브젝트 생성
	HttpHeaders headers2 = new HttpHeaders();
	headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
	headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	
	// httpheader와 httpbody를 하나의 오브젝트에 담기
	HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);// body와 header값을 가지는
																								// entity

	// http 요청하기-post방식으로-그리고 response변수의 응답 받음
	ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
			kakaoProfileRequest, String.class);
	System.out.println("카카오 엑세스 토큰" + response2);

	ObjectMapper objectMapper2 = new ObjectMapper();
	KakaoProfile kakaoProfile = null;
	try {
		kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);

	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(kakaoProfile);
	Users kakaoUser = Users.builder()
			.userid(kakaoProfile.getKakao_account().getEmail())
			.username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId()).password(cosKey)
			.email(kakaoProfile.getKakao_account().getEmail()).oauth("kakao").build();

	
	Users originUser=userRepository.findByUsername(kakaoUser.getUsername()).orElseGet(()->{
		return new Users();
	});
	System.out.println("-----------"+originUser.getUsername());
	if (originUser.getUsername() == null) {

		회원가입(kakaoUser);
	}
	System.out.println("----__+++------______-"+kakaoUser.getUsername());

//	Authentication authentication = authenticationManager
//			.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
//	SecurityContextHolder.getContext().setAuthentication(authentication)
	////////

	Authentication authentication = 
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUserid(),cosKey));
	

	SecurityContextHolder.getContext().setAuthentication(authentication);// 하나의 트랙잭션
}

	

}
