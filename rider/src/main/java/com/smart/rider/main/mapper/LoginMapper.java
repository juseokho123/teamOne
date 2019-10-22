package com.smart.rider.main.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.member.dto.MemberDTO;

@Mapper
public interface LoginMapper {
	
	/**** 191007 재욱, 로그인 ajax 호출용 카운트 ****/
	public int loginCount(String memberId, String memberPw);
	
	// 로그인 확인 
	public MemberDTO loginCheck(String memberId, String memberPw);

}
