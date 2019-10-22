package com.smart.rider.inventory.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.inventory.dto.InventoryDTO;

@Mapper
public interface InventoryMapper {
	
	//재고등록시 코드증가
	public String inventoryCodeCount();
	
	//재고등록
	public int inventoryInsert(InventoryDTO inventoryDto);
	
	//재고 리스트
	public List<GoodsHapDTO> inventoryList();
}
