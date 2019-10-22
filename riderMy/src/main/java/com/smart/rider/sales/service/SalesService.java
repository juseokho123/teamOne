package com.smart.rider.sales.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.rider.goods.dto.GoodsHapDTO;

import com.smart.rider.sales.dto.SalesDTO;
import com.smart.rider.sales.mapper.SalesMapper;


@Service
@Transactional
public class SalesService {
	@Autowired
	private SalesMapper salesMapper;
	
	
	//매출검색
	public List<SalesDTO> salesSearchList(String select ,String searchInput,String beginDate,String endDate,String SCODE){
		List<SalesDTO> search = salesMapper.salesSearchList(select, searchInput, beginDate, endDate,SCODE);
		//System.out.println("매출서비스확인@@@@@@@@@@@@@@@@@@@@@"+search);
		return search;
	}
	//매출삭제
	public int salesDelete(String salesCode,String memberId,String memberPw) {
		return salesMapper.salesDelete(salesCode, memberId, memberPw);
	}
	//매출수정
	public int salesUpdate(SalesDTO salesDto) {
		return salesMapper.salesUpdate(salesDto);
	}
	//매출상세보기
	public GoodsHapDTO getSalesList(String salesCode) {
		return salesMapper.getSalesList(salesCode);
	}
	//매출등록
	public int salesInsert(SalesDTO salesDto) {
		String salesCode = "SI"+salesMapper.salesCodeCount();
		//System.out.println(salesCode+"<<<<<<<<<<<<<<<<<코드확인");
		
		
		  if(salesCode.equals("SInull")) {
			  
			  salesCode = "SI0001"; 
			 }
		  salesDto.setSalesCode(salesCode);
		
		return salesMapper.salesInsert(salesDto);
		
	}
	
	//매출리스트
	public Map<String,Object> salesList(String select ,String searchInput,String beginDate,String endDate,String SCODE,String SLEVEL){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(SLEVEL.equals("관리자")) {
			SCODE="A";
			map.put("sList", salesMapper.salesList(select, searchInput, beginDate, endDate, SCODE));
			return map;
		}
			map.put("sList", salesMapper.salesList(select, searchInput, beginDate, endDate, SCODE));
		return map;
	}
	
}
