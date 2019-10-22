package com.smart.rider.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.inventory.dto.InventoryDTO;
import com.smart.rider.inventory.mapper.InventoryMapper;

@Service
@Transactional
public class InventoryService {
	@Autowired
	private InventoryMapper inventoryMapper;
	
	//재고등록
	public int inventoryInsert(InventoryDTO inventoryDto) {
		String inventoryCode = "I" + inventoryMapper.inventoryCodeCount();
		//System.out.println(inventoryCode+"lllllllllllllllllllllllllll");
		
		
		if(inventoryCode.equals("Inull")) {
			inventoryCode = "I0001";
		}
		inventoryDto.setInventoryCode(inventoryCode);
		return inventoryMapper.inventoryInsert(inventoryDto);
	}
	//재고리스트
	public List<GoodsHapDTO> inventoryList(){
		
		return inventoryMapper.inventoryList();
	}

}
