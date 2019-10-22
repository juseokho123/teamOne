package com.smart.rider.contract.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.contract.dto.UnitDTO;

@Mapper
public interface UnitMapper {
	
	//계약 단가표 목록
	public List<UnitDTO> unitList();
	
	//계약코드 중 높은 숫자 가져오기
	public String unitCodeMax();
	
	//계약코드 생성
	public int unitInsert(UnitDTO unit);

	
}
