package com.smart.rider.work.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.work.dto.WorkDTO;

@Mapper
public interface WorkMapper {
	
	/*** 직원 아이디와 비밀번호 체크 ***/
	public MemberDTO employeeCheck(MemberDTO memberdto);
	
	//근태등록 코드자동증가
	public String workCodeCount();
	
	//출근등록처리
	public int goInsert(WorkDTO workdto);
	
	//근태목록처리
	public List<WorkDTO> workList(Map<String, Object> map);
	
	//퇴근등록처리
	public int leaveInsert(WorkDTO workdto);
	
	//페이지작업위한 목록 총 카운트
	public int getWorkAllCount(String contractShopCode, String memberId);
	
	//근태검색처리
	public List<WorkDTO> searchWork(Map<String, Object> map);
	
	//검색처리 목록 총 카운트
	public int getSearchAllCount(String contractShopCode, String select, String searchInput, String beginDate, String endDate);
}
