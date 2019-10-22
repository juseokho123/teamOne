package com.smart.rider.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.shop.dto.SsrHapDTO;

@Mapper
public interface EmployeeMapper {
	
	//19.09.15작성
	public int employeeInsert(MemberDTO memberdto);
	
	//직원 목록가져오기
	public List<MemberDTO> employeeList(String contractShopCode);
	
	//19.09.20작성 직원상세보기
	public MemberDTO getEmployeeList(String memberId);
	
	//직원 수정처리
	public int employeeUpdate(MemberDTO memberdto);
	
	//19.09.25작성 직원탈퇴처리
	public int employeeDelete(String memberId, String memberPw);
	
	//19.09.26작성 직원목록검색
	public List<MemberDTO> searchEmployee(String contractShopCode, String select, String searchInput, String beginDate, String endDate);
	
	//19.09.27 계약매장코드 
	public List<SsrHapDTO> getShopRelationCode(String SID);
}
