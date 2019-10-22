package com.smart.rider.spend.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.spend.dto.JoinUtilityDTO;
import com.smart.rider.spend.dto.UtilityDTO;
import com.smart.rider.subject.dto.SubjectDTO;

@Mapper
public interface UtilityMapper {
	
	/*** 190926 재욱, 지출_공과금 등록 내역 수정 ***/
	public int utilityUpdate(UtilityDTO utilityDTO);
	
	/*** 190926 재욱, 지출_공과금 등록 내역 삭제 ***/
	public int utilityDelete(String spendUtilityCode);
	
	/*** 190924 재욱, 지출_공과금 최근 등록 목록 ***/ 
	public List<UtilityDTO> utilityList(Map<String, Object> map);
	
	/*** 190924 재욱, 지출_공과금 등록 계정과목 selectBox ***/
	public List<SubjectDTO> subjectSelectBox();
	
	/*** 190924 재욱, 지출_공과금 등록 ***/  
	public int utilityInsert(UtilityDTO utilityDTO);
	
	/*** 190924 재욱, 지출_공과금 코드 카운트 ***/
	public String utilityCodeCount();
	
	/*** 190924 재욱, 지출_공과금 등록 내역 페이징 AllCount ***/
	public int utilityAllCount(String contractShopCode);
	
	/*** 190924 재욱, 지출_공과금 검색 내역 페이징 Count ***/
	public int utilitySearchCount(Map<String, Object> map);
	
	/*** 190924 재욱, 지출_공과금 등록 내역 상세보기 ***/
	public List<JoinUtilityDTO> utilityDetails(String spendUtilityCode);

}
