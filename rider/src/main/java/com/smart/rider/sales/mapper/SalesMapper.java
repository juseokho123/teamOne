package com.smart.rider.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.sales.dto.SalesDTO;

@Mapper
public interface SalesMapper {
	
	//매출검색
	public List<SalesDTO> salesSearchList(String select ,String searchInput,String beginDate,String endDate,String SCODE);
	//매출삭제
	public int salesDelete(String salesCode,String memberId,String memberPw);
	//매출수정
	public int salesUpdate(SalesDTO salesDto);
	//매출상세보기
	public GoodsHapDTO getSalesList(String salesCode);
	//매출등록
	public int salesInsert(SalesDTO salesDto);
	//매출리스트
	public List<GoodsHapDTO> salesList(String select ,String searchInput,String beginDate,String endDate,String SCODE);
	//insert 시 코드자동증가
	public String salesCodeCount();
	

}
