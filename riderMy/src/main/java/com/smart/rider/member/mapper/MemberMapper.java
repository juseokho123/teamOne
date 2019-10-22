package com.smart.rider.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.shop.dto.SsrHapDTO;

@Mapper
public interface MemberMapper {

	//회원 등록처리
	public int memberInsert(MemberDTO memberdto);
	
	//회원 목록 가져오기
	public List<MemberDTO> memberList(Map<String, Object> map);
	
	//19.09.16작성 회원가입 아이디 중복체크
	public int memberIdCheck(String memberId);
	
	//회원 상세보기
	public MemberDTO getMemberList(String memberId);
	
	//19.09.17작성 회원 수정처리
	public int memberUpdate(MemberDTO memberdto);
	
	//회원 검색처리
	public List<MemberDTO> searchMember(Map<String, Object> map);
	
	//19.09.18작성 회원 탈퇴처리
	public int memberDelete(String memberId, String memberPw);
	
	//19.09.24작성 관리자권한 회원 삭제
	public int levelDelete(String memberId);
	
	//19.09.25작성 회원 비밀번호 수정
	public int changePassword(String memberId, String memberPw, String memberPw2);
	
	//19.10.02 페이지작업위한 작성
	public int getMemberAllCount();
	
	//19.10.04검색 페이지작업
	public int getSearchAllCount(String select, String searchInput, String beginDate, String endDate, String shopCode);
	
	//계약매장 가져오기
	public List<SsrHapDTO> utilityShop();
}
