package com.smart.rider.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.member.mapper.EmployeeMapper;
import com.smart.rider.shop.dto.SsrHapDTO;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	//19.09.15작성 직원등록처리
	public int employeeInsert(MemberDTO memberdto) {
		
		return employeeMapper.employeeInsert(memberdto);
	}
	
	//직원 목록가져오기
	public List<MemberDTO> employeeList(String contractShopCode) {
		
		List<MemberDTO> list = employeeMapper.employeeList(contractShopCode);
		
		return list;
	}
	
	//19.09.20작성 직원 상세보기
	public MemberDTO getEmployeeList(String memberId) {
		
		return employeeMapper.getEmployeeList(memberId);
	}
	
	//직원수정처리
	public int employeeUpdate(MemberDTO memberdto) {
		
		return employeeMapper.employeeUpdate(memberdto);
	}
	
	//19.09.25작성 직원탈퇴처리
	public int employeeDelete(String memberId, String memberPw) {
		
		return employeeMapper.employeeDelete(memberId, memberPw);
	}
	
	//19.09.26작성 직원검색
	public List<MemberDTO> searchEmployee(String contractShopCode, String select, String searchInput, String beginDate, String endDate) {
		
		return employeeMapper.searchEmployee(contractShopCode, select, searchInput, beginDate, endDate);
	}
	
	//19.09.27 계약코드 
	public List<SsrHapDTO> getShopRelationCode(String SID){
		
		return employeeMapper.getShopRelationCode(SID);
	}
}
