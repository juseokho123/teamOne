package com.smart.rider.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.member.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	//19.09.15작성 직원등록화면
	@GetMapping("/employeeInsert")
	public String employeeInsert() {
		
		return "employee/employeeInsert";
	}
	
	//직원등록처리
	@PostMapping("/employeeInsert")
	public String employeeInsert(MemberDTO memberdto) {
		//System.out.println(memberdto.getContractShopCode() + "<---릴레코드");
		//매장의 직원만 리스트에 보여주기위한
		String contractShopCode = memberdto.getContractShopCode();
		employeeService.employeeInsert(memberdto);	
		
		return "redirect:/employeeList?contractShopCode="+contractShopCode+"";
	}
	
	//직원목록
	@GetMapping("/employeeList")
	public String employeeList(@RequestParam(value="contractShopCode") String contractShopCode, Model model) {
		List<MemberDTO> list = employeeService.employeeList(contractShopCode);
		model.addAttribute("employeeList", list);
		
		return "employee/employeeList";
	}
	
	//19.09.20작성 직원상세보기
	@GetMapping("/getEmployeeList")
	public String getEmployeeList(@RequestParam(value="memberId") String memberId, Model model) {
		model.addAttribute("getEmployeeList", employeeService.getEmployeeList(memberId));
		
		return "employee/employeeUpdate";
	}
	
	//직원 수정하기
	@PostMapping("/employeeUpdate")
	public String employeeUpdate(MemberDTO memberdto) {
		String memberId = memberdto.getMemberId();
		employeeService.employeeUpdate(memberdto);
		
		return "redirect:/getEmployeeList?memberId="+memberId+"";
	}
	
	//19.09.25작성 직원 탈퇴
	@PostMapping("/employeeDelete")
	public String employeeDelete(MemberDTO memberdto, Model model) {
		
		String contractShopCode = memberdto.getContractShopCode();
		int result = employeeService.employeeDelete(memberdto.getMemberId(), memberdto.getMemberPw());
		if(result == 0) {
			model.addAttribute("result", "비밀번호가 일치하지 않습니다!");
			model.addAttribute("memberId", memberdto.getMemberId());
			return "employee/employeeDelete";
		}
		
		return "redirect:/employeeList?contractShopCode="+contractShopCode+"";
	}
	
	//직원 탈퇴화면
	@GetMapping("/employeeDelete")
	public String employeeDelete(@RequestParam(value="memberId") String memberId, Model model) {
		model.addAttribute("memberId", memberId);
		
		return "employee/employeeDelete";
	}
	
	//19.09.26작성 직원 검색
	@PostMapping("/searchEmployee")
	public String searchEmployee(@RequestParam(value="contractShopCode") String contractShopCode
								,@RequestParam(value="select") String select
								,@RequestParam(value="searchInput") String searchInput
								,@RequestParam(value="beginDate") String beginDate
								,@RequestParam(value="endDate") String endDate, Model model) {
		List<MemberDTO> searchList = employeeService.searchEmployee(contractShopCode, select, searchInput, beginDate, endDate);
		model.addAttribute("employeeList", searchList);
		
		return "employee/employeeList";
	}
}
