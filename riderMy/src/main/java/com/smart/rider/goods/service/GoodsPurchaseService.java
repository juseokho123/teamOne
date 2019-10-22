package com.smart.rider.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.goods.dto.GoodsPurchaseDTO;
import com.smart.rider.goods.mapper.GoodsPurchaseMapper;



@Service
@Transactional
public class GoodsPurchaseService {
	@Autowired
	private GoodsPurchaseMapper goodsPurchaseMapper;
	
	//삭제가능 매입리스트상세조회
	public GoodsHapDTO yesPurchaseList(String purchaseCode) {
		return goodsPurchaseMapper.yesPurchaseList(purchaseCode);
	}
	
	//리스트 관리자, 점주 화면나눠주기
	public Map<String,Object> purchaseList(String select ,String searchInput,String beginDate,String endDate,String SCODE,String SLEVEL){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(SLEVEL.equals("관리자")) {
			SCODE="A";
			map.put("yList", goodsPurchaseMapper.purchaseYlist(select, searchInput, beginDate, endDate, SCODE));
			map.put("nList", goodsPurchaseMapper.purchaseNlist(select, searchInput, beginDate, endDate, SCODE));
			return map;
		}
		map.put("yList", goodsPurchaseMapper.purchaseYlist(select, searchInput, beginDate, endDate, SCODE));
		map.put("nList", goodsPurchaseMapper.purchaseNlist(select, searchInput, beginDate, endDate, SCODE));
		return map;
	}
	
	
	  //삭제가능 매입리스트 
		public List<GoodsHapDTO> purchaseYlist(String select ,String searchInput,String beginDate,String endDate,String SCODE) {
			return  goodsPurchaseMapper.purchaseYlist(select, searchInput, beginDate, endDate,SCODE);
		}
	 //삭제 불가능 매입리스트
		public List<GoodsHapDTO>  purchaseNlist(String select ,String searchInput,String beginDate,String  endDate,String SCODE) {
			return goodsPurchaseMapper.purchaseNlist(select,  searchInput, beginDate, endDate ,SCODE);
			}
	 
		
	//매입삭제 메서드
	public int purchaseDelete(String purchaseCode,String memberId,String memberPw) {
		return goodsPurchaseMapper.purchaseDelete(purchaseCode, memberId, memberPw); 
	}
	
	//매입 검색 메서드
	public List<GoodsPurchaseDTO> purchaseSearchList(String select ,String searchInput,String beginDate,String endDate,String SCODE){
		List<GoodsPurchaseDTO> search = goodsPurchaseMapper.purchaseSearchList(select, searchInput, beginDate, SCODE);
		return search;
	}
	
	//매입 상세조회
	public GoodsHapDTO getPurchaseList(String purchaseCode) {
		return goodsPurchaseMapper.getPurchaseList(purchaseCode);
	}
	
	//매입리스트
	public List<GoodsHapDTO> purchaseList(){
		return goodsPurchaseMapper.purchaseList();
	}
	
	
	//매입insert
	public int purchaseInsert(GoodsPurchaseDTO goodsPurchaseDto) {
		//코드자동증가 
		String purchaseCode = "P" + goodsPurchaseMapper.purchaseCodeCount();
		//System.out.println(purchaseCode+"lllllllllllllllllllllllllll");
		
		
		if(purchaseCode.equals("Pnull")) {
			purchaseCode = "P0001";
		}
		goodsPurchaseDto.setPurchaseCode(purchaseCode);
	
		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("purchaseCode", goodsPurchaseDto.getPurchaseCode());
		 * map.put("accountCode", goodsPurchaseDto.getAccountCode());
		 * map.put("contractShopCode", goodsPurchaseDto.getContractShopCode());
		 * map.put("goodsDbCode", goodsPurchaseDto.getGoodsDbCode());
		 * map.put("purchasePay", goodsPurchaseDto.getPurchasePay());
		 * map.put("purchaseState", goodsPurchaseDto.getPurchaseState());
		 */
		
		return goodsPurchaseMapper.purchaseInsert(goodsPurchaseDto);		
		 
		
	}

}
