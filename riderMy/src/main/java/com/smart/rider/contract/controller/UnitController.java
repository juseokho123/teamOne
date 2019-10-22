package com.smart.rider.contract.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smart.rider.contract.dto.UnitDTO;
import com.smart.rider.contract.service.UnitService;

@Controller
public class UnitController {

	@Autowired 
	private UnitService unitService;
	
	//계약단가표 생성화면
	@GetMapping("/unitInsert.html")
	public String unitInsert() {
		return "/unit/unitInsert";
	}
	
	//계약단가표 목록
	@GetMapping("/unitList.html")
	public String unitList(Model model) {
		model.addAttribute("unitList", unitService.unitList());
		return "/unit/unitList";
	}

	//계약단가표 생성하기
	@PostMapping("/unitInsert")
	public String unitInsert(UnitDTO unit, HttpSession session) {
		//System.out.println(unit.toString() + "<-- unit.toString");
		unitService.unitInsert(unit,session);
		return "redirect:/unitList.html";
	}
	

}
