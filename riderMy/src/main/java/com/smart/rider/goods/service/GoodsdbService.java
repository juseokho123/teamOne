package com.smart.rider.goods.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.goods.dto.GoodsdbDTO;
import com.smart.rider.goods.mapper.GoodsdbMapper;



@Service
@Transactional
public class GoodsdbService {
	@Autowired
	private GoodsdbMapper goodsdbmapper;
	//삭제가능리스트 상세보기	
	public GoodsHapDTO yesGoodsDblist(String goodsDbCode) {
			
		return goodsdbmapper.yesGoodsDblist(goodsDbCode);
	}
	//삭제가능 DB리스트
	public List<GoodsHapDTO> goodsDbYlist(String select ,String searchInput,String beginDate,String endDate){
		//System.out.println("DB서비스단확인 삭제가능리스트"+goodsdbmapper.goodsDbYlist(select, searchInput, beginDate, endDate));
		return goodsdbmapper.goodsDbYlist(select, searchInput, beginDate, endDate); 
	}
	//삭제불가능 DB리스트
	public List<GoodsHapDTO> goodsDbNlist(String select ,String searchInput,String beginDate,String endDate){
		return goodsdbmapper.goodsDbNlist(select, searchInput, beginDate, endDate);
	}
	//05상품 검색 메서드 
	//19-19-18 문영성
	public List<GoodsdbDTO> goodsDbSearchList(String select ,String searchInput,String beginDate,String endDate) {
		List<GoodsdbDTO> search = goodsdbmapper.goodsDbSearchList(select, searchInput, beginDate, endDate);
		return search;
	}
	
	//04 상품DB삭제 메서드
	//상품코드 상품아이디.비빌번호가져오기
	public int goodsDbDelete(String goodsDbCode,String memberId,String memberPw) {
		
		return goodsdbmapper.goodsDbDelete(goodsDbCode, memberId, memberPw);
	}
	
		
	
	//상품DB상세조회 위해 코드가져오기위한 메서드
	public GoodsdbDTO getGoodsDbCode(String goodsDbCode) {
		
		return goodsdbmapper.getGoodsDbCode(goodsDbCode);
	}

	//03 상품DB리스트 조회 메서드선언
	public List<GoodsdbDTO> goodsDbList() {
		
		return goodsdbmapper.goodsDbList();
	}
	
	
	//02
	//코드자동추가메서드를 합하여 상품db등록 메서드선언 
	//dto멤버아이디에 세션 셋팅
	//19-19-10 문영성
	public int goodsDbInsert(GoodsdbDTO goodsdbdto, HttpSession session) { 
		String goodsDbCode = "GD" + goodsdbmapper.getGoodsDbCodeMax();
		goodsdbdto.setMemberId((String) session.getAttribute("SID"));
		
		if(goodsDbCode.equals("GDnull")) {
			
			goodsDbCode = "GD0001";
		}
		
		goodsdbdto.setGoodsDbCode(goodsDbCode);
		
		return goodsdbmapper.goodsDbInsert(goodsdbdto);
	}

	

	//01첫번쨰
	// 상품DB등록 확인메서드
	/*
	 * public int goodsDbInsert(GoodsdbDTO goodsdbdto) {
	 * 
	 * return goodsdbmapper.goodsDbInsert(goodsdbdto);
	 * 
	 * }
	 */



}
