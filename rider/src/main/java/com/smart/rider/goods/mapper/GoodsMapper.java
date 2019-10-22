package com.smart.rider.goods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.goods.dto.GoodsDTO;
import com.smart.rider.goods.dto.GoodsHapDTO;

@Mapper
public interface GoodsMapper {
	
	//대여상품 매출등록시 상품상태변경
	public int goodsSalesRentalUpdate(GoodsDTO goodsDto);
	//매출등록시 상품판매상태변경
	public int goodsSalesUpdate(GoodsDTO goodsDto);
	//삭제가능상품 상세보기
	public GoodsHapDTO yesGoodsList(String goodsCode);
	
	//삭제불가능상품메서드
	public List<GoodsHapDTO> goodsNlist(String select ,String searchInput,String beginDate,String endDate,String SCODE);
	
	//삭제가능상품메서드
	public List<GoodsHapDTO> goodsYlist(String select ,String searchInput,String beginDate,String endDate,String SCODE);
	
	//코드자동증가
	public String goodsCodeCount();
	
	//상품검색추가
	public List<GoodsDTO> goodsSearchList(String select ,String searchInput,String beginDate,String endDate,String SCODE);
	//상품삭제
	public int goodsDelete(String goodsCode,String memberId,String memberPw);
	
	//상품수정하기
	public int goodsUpdate(GoodsDTO goodsDto);
	
	//상품상세보기메서드
	
	public GoodsHapDTO getGoodsList(String goodsCode);
	
	//01판매상품 리스트 조회 메서드
	//테이블 3개조인.. 문영성
	public List<GoodsHapDTO> goodsList();
	
	//02판매상품insert 입력메서드
	public int goodsInsert(GoodsDTO goodsDto);
	
	
}
