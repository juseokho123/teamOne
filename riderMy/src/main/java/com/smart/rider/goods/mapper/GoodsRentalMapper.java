package com.smart.rider.goods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.goods.dto.GoodsRentalDTO;
@Mapper
public interface GoodsRentalMapper {
	
	//대여상품검색기능추가
	public List<GoodsRentalDTO> goodsRentalSearchList(String select ,String searchInput,String beginDate,String endDate,String SCODE);
	//대여상품삭제
	public int goodsRentalDelete(String goodsRentalCode,String memberId,String memberPw);
	//대여상품수정
	public int goodsRentalUpdate(GoodsRentalDTO goodsRentalDto);
	//코드자동증가
	public String goodsRentalCodeCount();
	//대여상품 상세조회 insert
	public GoodsHapDTO getGoodsRentalList(String goodsRentalCode);
	//대여상품등록 insert
	public int goodsRentalInsert(GoodsRentalDTO goodsRentalDto);
	//대여상품 리스트
	public List<GoodsHapDTO> goodsRentalList(String select ,String searchInput,String beginDate,String endDate,String SCODE,String SLEVEL);
	
}
