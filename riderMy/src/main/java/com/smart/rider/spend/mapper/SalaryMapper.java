package com.smart.rider.spend.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.spend.dto.JoinSalaryDTO;

@Mapper
public interface SalaryMapper {
	
	/*** 191002 재욱, Read : 지출_급여 등록 내역 페이징 AllCount ***/
	public int salaryAllCount(String contractShopCode);
	
	/**** 191002 재욱, Update : 지출_급여 수정 ****/
	public int salaryUpdate(JoinSalaryDTO joinSalaryDTO);
	
	/*** 190930 재욱, Read : 지출_급여 등록 내역 ***/
	public List<JoinSalaryDTO> salaryList(Map<String, Object> map);
	
	/*** 190930 재욱, 지출_급여 코드 자동증가용 카운트 ***/
	public String salaryCodeCount();
	
	/*** 190927 재욱, Insert : 지출_급여 내역 등록 ***/
	public int salaryInsert(JoinSalaryDTO salaryDTO);
	
	/*** 190927 재욱, Read : 지출_급여 직원 select box list ***/
	public List<MemberDTO> salarySelectBox(String contractShopCode);

}
