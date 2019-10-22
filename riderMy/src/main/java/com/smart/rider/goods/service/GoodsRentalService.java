package com.smart.rider.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.goods.dto.GoodsRentalDTO;

import com.smart.rider.goods.mapper.GoodsRentalMapper;

@Service
@Transactional
public class GoodsRentalService {
	@Autowired
	private GoodsRentalMapper goodsRentalMapper;

	
	//대여상품검색추가
	public List<GoodsRentalDTO> goodsRentalSearchList(String select ,String searchInput,String beginDate,String endDate,String SCODE){
		List<GoodsRentalDTO> search = goodsRentalMapper.goodsRentalSearchList(select, searchInput, beginDate, endDate, SCODE);
		return search;
	}
	//대여상품 삭제하기
	public int goodsRentalDelete(String goodsRentalCode,String memberId,String memberPw) {
		return goodsRentalMapper.goodsRentalDelete(goodsRentalCode, memberId, memberPw);
	}
	//대여상품 수정하기
	public int goodsRentalUpdate(GoodsRentalDTO goodsRentalDto) {
		return goodsRentalMapper.goodsRentalUpdate(goodsRentalDto);
	}
	//대여상품상세보기 select
	public GoodsHapDTO getGoodsRentalList(String goodsRentalCode) {
		return goodsRentalMapper.getGoodsRentalList(goodsRentalCode);
	}
	
	//대여상품 등록 insert 19-09-25 문영성
	public int goodsRentalInsert(GoodsRentalDTO goodsRentalDto) {
		String goodsRentalCode = "GR"+goodsRentalMapper.goodsRentalCodeCount();
		//System.out.println(goodsRentalCode+"<<<<<<<<<<<<<<<<<코드확인");
		
		
		  if(goodsRentalCode.equals("GRnull")) {
			  
			  goodsRentalCode = "GR0001"; 
			 }
		  	goodsRentalDto.setGoodsRentalCode(goodsRentalCode);
		  
		
		return goodsRentalMapper.goodsRentalInsert(goodsRentalDto);		
	}
	
	//대여상품 리스트조회
	public Map<String,Object> goodsRentalList(String select ,String searchInput,String beginDate,String endDate,String SCODE,String SLEVEL){
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(SLEVEL.equals("관리자")) {
			SCODE="A";	
			map.put("rList", goodsRentalMapper.goodsRentalList(select, searchInput, beginDate, endDate, SCODE, SLEVEL));
			return map;
		}
			map.put("rList", goodsRentalMapper.goodsRentalList(select, searchInput, beginDate, endDate, SCODE, SLEVEL));
		return map;
	}

	
}
