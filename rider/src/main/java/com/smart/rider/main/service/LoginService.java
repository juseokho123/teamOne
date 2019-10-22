package com.smart.rider.main.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.main.mapper.LoginMapper;
import com.smart.rider.member.dto.MemberDTO;

@Service
public class LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	/**** 191007 재욱, 로그인 ajax 호출용 카운트 ****/
	public int loginCount(String memberId, String memberPw) {
		return loginMapper.loginCount(memberId, memberPw);
	}
	
	//로그인
	public Map<String, Object> loginCheck(String memberId, String memberPw){
		String check = "";
		Map<String, Object> map = new HashMap<String, Object>();
		MemberDTO loginCheck = loginMapper.loginCheck(memberId, memberPw);
		
		if(loginCheck != null) {
			//System.out.println(loginCheck + " <-- success loginCheck LoginService.java");
			map.put("loginCheck", loginCheck);
		} else {
			//System.out.println(loginCheck + " <-- failed loginCheck LoginService.java");
			check = "아이디 혹은 비밀번호가 일치하지 않습니다";
			map.put("result", check);
		}
		
		return map;
	}
	
}
