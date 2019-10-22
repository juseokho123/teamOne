package com.smart.rider.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.smart.rider.inventory.service.InventoryService;

@Controller
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;

	
	@GetMapping("/inventoryList")
	public String inventoryList(Model model) {
		model.addAttribute("iList", inventoryService.inventoryList());
				
		return "/inventory/inventoryList";
	}
}
