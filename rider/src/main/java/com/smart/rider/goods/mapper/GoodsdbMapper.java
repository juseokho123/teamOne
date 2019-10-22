package com.smart.rider.goods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.goods.dto.GoodsdbDTO;

@Mapper
public interface GoodsdbMapper {
	//삭제가능 리스트 상세보기문영성
	public GoodsHapDTO yesGoodsDblist(String goodsDbCode);
	//삭제가능 DB리스트
	public List<GoodsHapDTO> goodsDbYlist(String select ,String searchInput,String beginDate,String endDate);
	//삭제 불가능 DB리스트
	public List<GoodsHapDTO> goodsDbNlist(String select ,String searchInput,String beginDate,String endDate);
	//상품DB삭제 메서드 문영성
	//상품코드.아이디,비밀번호가져오기
	public int goodsDbDelete(String goodsDbCode,String memberId,String memberPw);
	
	//상품코드자동추가하기위한 메서드 문영성
	public String getGoodsDbCodeMax();
	
	//상품DB등록하기 
	//날짜 19-09-10 문영성
	public int goodsDbInsert(GoodsdbDTO goodsdbdto);
	
	//상품DB리스트조회 메서드 문영성
	public List<GoodsdbDTO> goodsDbList();
	
	//상품코드로 상품DB상제조회 메서드 문영성
	public GoodsdbDTO getGoodsDbCode(String goodsDbCode);
	//상품 검색 매서드 문영성
	//19-09-18
	public List<GoodsdbDTO> goodsDbSearchList(String select ,String searchInput,String beginDate,String endDate);
}
