package com.smart.rider.main.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.main.dto.ChartValueDTO;

@Mapper
public interface MainMapper {
	
	// 190926 재욱, 월별 컬럼의 합
	public List<ChartValueDTO> chartValue(Map<String, Object> map);
} 
