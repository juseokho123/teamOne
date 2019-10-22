package com.smart.rider.contract.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.contract.dto.ContractManagementDTO;
import com.smart.rider.contract.dto.ManagementDTO;
import com.smart.rider.contract.service.ManagementService;
import com.smart.rider.main.dto.SearchDTO;

@Controller
public class ManagementController {

	@Autowired
	private ManagementService managementService;
	
	//계약금 목록
	@GetMapping("/managementList")
	public String managementList(@RequestParam(value="currentPage",required=false, defaultValue="1") int currentPage
								,Model model) {
			//맵으로 서비스부분 받기
			Map<String, Object> returnMap = managementService.managementList(currentPage);
	    	//Map객체주소로 보내는 경우(model.addAttribute("map", returnMap);
			//System.out.println(returnMap + " map 담긴 값 확인 ");
			//returnMap(Map타입 객체)에 담겨있는 값 -> model(Model타입 객체)에 복사 ->  view전달
			//계약금 납부 목록
	    	model.addAttribute("managementList", returnMap.get("list"));
	    	//System.out.println(returnMap.get("list") + "값 확인");
	    	//현재 페이지
	    	model.addAttribute("currentPage",returnMap.get("currentPage"));
	    	//마지막페이지
	    	model.addAttribute("lastPage",returnMap.get("lastPage"));	
	    	return "management/managementList";
	}
	
	//계약금 수정화면
	@GetMapping("/managementUpdate")
	public String getmanagementList(@RequestParam(value = "contractManagementCode") String managementCode, Model model) {
		//값들어오는지 확인
		//System.out.println(managementCode);
		//getManagementList 값 확인
		List<ManagementDTO> List = managementService.getManagementList(managementCode);
		//System.out.println("ManagementList"+List);
		model.addAttribute("managementList", List);
		return "management/managementUpdate";
	}
	
	//계약금 수정하기
	@PostMapping("/managementUpdate")
	public String managementUpdate(ManagementDTO management) {
		managementService.managementUpdate(management);
		//들어오는  값 확인 
		//System.out.println(management.toString() + "<-- management.toString");
		return "redirect:/managementList";
	}
	
	//계약금 검색하기
	@PostMapping("/managementSearchList")
	public String managementSearchList(SearchDTO search,Model model) {
		//System.out.println(search + "<-입력받은값");
		List<ContractManagementDTO> cmList = managementService.managementSearchList(search);
		//System.out.println(cmList+"<--입력받은 search 값을 대입하여 나온 결과");
		//결과 값 model에 담기
		model.addAttribute("managementList", cmList);
		//조회 결과가 없으면 나오는 문장
		if(cmList.size()  == 0) {
			model.addAttribute("alert", "검색 결과가 없습니다");
		}
		return "management/managementList";
	}
}
