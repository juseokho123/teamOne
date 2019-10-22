package com.smart.rider.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.member.mapper.MemberMapper;
import com.smart.rider.shop.dto.SsrHapDTO;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	//회원등록 처리
	public int memberInsert(MemberDTO memberdto) {
		
		return memberMapper.memberInsert(memberdto);
	}
	
	//회원 목록 페이지 작업
	public Map<String, Object> memberList(int currentPage) {
		//페이지 구성 할 행의 갯수
		final int rowPerPage = 10;
		//보여줄 첫번째 페이지번호 초기화
		int startPageNum = 1;
		//보여줄 페이지번호의 갯수 초기화
		int lastPageNum = rowPerPage;
		
		if(currentPage > (rowPerPage/2)) {
			startPageNum = currentPage - ((lastPageNum/2)-1);
			lastPageNum += (startPageNum-1);
		}
		// limit 적용될 값 startRow, 상수 rowPerPage
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startRow = (currentPage-1)*rowPerPage;
		
		map.put("startRow"	,startRow);
		map.put("rowPerPage",rowPerPage);
		
		//전체행의 갯수를 가져오는 쿼리
		double memberCount = memberMapper.getMemberAllCount();
							//올림함수 소수점이있으면 무조건 올림
		int lastPage = (int)(Math.ceil(memberCount/rowPerPage));
		
		if(currentPage >= (lastPage-4)) {
			lastPageNum = lastPage;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", memberMapper.memberList(map));
		//System.out.println(memberMapper.memberList(map) + "<-----");
		resultMap.put("currentPage" ,currentPage);
		resultMap.put("lastPage"	,lastPage);
		resultMap.put("startPageNum",startPageNum);
		resultMap.put("lastPageNum" ,lastPageNum);
		
		return resultMap;
	}
	
	//19.09.16작성 회원가입 아이디중복체크
	public int memberIdCheck(String memberId) {
		
		return memberMapper.memberIdCheck(memberId);
	}
	
	//회원 상세보기
	public MemberDTO getMemberList(String memberId) {
		
		return memberMapper.getMemberList(memberId);
	}
	
	//19.09.17작성 회원 수정처리
	public int memberUpdate(MemberDTO memberdto) {
		
		return memberMapper.memberUpdate(memberdto);
	}
	
	//회원 검색처리
	public Map<String, Object> searchMember(int currentPage, String select, String searchInput, String beginDate, String endDate, String shopCode) {
		//System.out.println(beginDate+"<--시작날짜");
		//페이지 구성 할 행의 갯수
		final int rowPerPage = 10;
		//보여줄 첫번째 페이지번호 초기화
		int startPageNum = 1;
		//보여줄 페이지번호의 갯수 초기화
		int lastPageNum = rowPerPage;
		
		if(currentPage > (rowPerPage/2)) {
			startPageNum = currentPage - ((lastPageNum/2)-1);
			lastPageNum += (startPageNum-1);
		}
		// limit 적용될 값 startRow, 상수 rowPerPage
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startRow = (currentPage-1)*rowPerPage;
		
		map.put("startRow"	 ,startRow);
		map.put("rowPerPage" ,rowPerPage);
		map.put("select"	 ,select);
		map.put("searchInput",searchInput);
		map.put("beginDate"	 ,beginDate);
		map.put("endDate"	 ,endDate);
		map.put("shopCode"	 ,shopCode);
		//검색결과 전체행의 갯수를 가져오는 쿼리
		double memberCount = memberMapper.getSearchAllCount(select, searchInput, beginDate, endDate, shopCode);
							//올림함수 소수점이있으면 무조건 올림
		int lastPage = (int)(Math.ceil(memberCount/rowPerPage));
		
		if(currentPage >= (lastPage-4)) {
			lastPageNum = lastPage;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list"		,memberMapper.searchMember(map));
		//System.out.println(memberMapper.searchMember(map) + "<---검색결과");
		resultMap.put("currentPage" ,currentPage);
		resultMap.put("lastPage"	,lastPage);
		resultMap.put("startPageNum",startPageNum);
		resultMap.put("lastPageNum" ,lastPageNum);
		
		return resultMap;
	}
	
	//19.09.18작성 회원 탈퇴처리
	public int memberDelete(String memberId, String memberPw) {
		
		return memberMapper.memberDelete(memberId, memberPw);
	}
	
	//19.09.24작성 관리자 회원삭제
	public int levelDelete(String memberId) {
		
		return memberMapper.levelDelete(memberId);
	}
	
	//19.09.25작성 회원 비밀번호 수정
	public int changePassword(String memberId, String memberPw, String memberPw2) {
		
		return memberMapper.changePassword(memberId, memberPw, memberPw2);
	}
	
	//계약된 매장가져오기
	public List<SsrHapDTO> utilityShop() {
		
		return memberMapper.utilityShop();
	}
}
