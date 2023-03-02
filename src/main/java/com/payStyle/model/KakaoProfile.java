package com.payStyle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class KakaoProfile {
	public long id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown=true)
	public class Properties {
		public String nickname;
	}


	@Data
	@JsonIgnoreProperties(ignoreUnknown=true)
	public class KakaoAccount {
		public Boolean profile_nickname_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needsAgreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;
	
		@Data
		@JsonIgnoreProperties(ignoreUnknown=true)
		public class Profile {
			public String nickname;
		}
	}
}

//{
//	  "id": 2605326735,
//	  "connected_at": "2023-01-02T02:50:29Z",
//	  "properties": {
//	    "nickname": "차민건"
//	  },
//	  "kakao_account": {
//	    "profile_nickname_needs_agreement": false,
//	    "profile": {
//	      "nickname": "차민건"
//	    },
//	    "has_email": true,
//	    "email_needs_agreement": false,
//	    "is_email_valid": true,
//	    "is_email_verified": true,
//	    "email": "mk633@naver.com"
//	  }
//	}




