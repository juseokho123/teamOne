package com.smart.rider.work.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.work.dto.WorkDTO;
import com.smart.rider.work.service.WorkService;

@Controller
public class WorkController {

	@Autowired
	private WorkService workService;
	
	//출근 등록화면
	@GetMapping("/goInsert")
	public String goInsert() {
		
		return "work/goInsert";
	}
	
	//퇴근 등록화면
	@GetMapping("/leaveInsert")
	public String leaveInsert() {
		
		return "work/leaveInsert";
	}
	
	//출,퇴근 등록처리 후 알림
	@GetMapping("/workSuccess")
	public String workSuccess() {
		
		return "work/workSuccess";
	}
	
	//출근 등록처리
	@PostMapping("/goInsert")
	public String goInsert(@RequestParam(value="memberId") String memberId
						  ,@RequestParam(value="memberPw") String memberPw
						  ,WorkDTO workdto, MemberDTO memberdto, Model model) {
		//직원 아이디와 비밀번호 확인
		Map<String,Object> map = workService.employeeCheck(memberdto);
		String result = (String) map.get("result");
		
		if(!result.equals("확인")) {
			model.addAttribute("result", result);
			//System.out.println(result + "<--실패");
			return "work/goInsert";
		}
		//System.out.println(result + "<--직원아이디확인");
		workService.goInsert(workdto);
		
		return "redirect:/workSuccess";
	}
	
	//직원 출.퇴근 목록
	@GetMapping("/workAdmin")
	public String workList(@RequestParam(value="contractShopCode",defaultValue="") String contractShopCode
						  ,@RequestParam(value="memberId",defaultValue="") String memberId
						  ,@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage
						  ,Model model) {
		Map<String, Object> map = workService.workList(currentPage, contractShopCode, memberId);
		
		model.addAttribute("workList"	 ,map.get("list"));
		model.addAttribute("currentPage" ,map.get("currentPage"));
		model.addAttribute("lastPage"	 ,map.get("lastPage"));
		model.addAttribute("startPageNum",map.get("startPageNum"));
		model.addAttribute("lastPageNum" ,map.get("lastPageNum"));
		
		return "work/workAdmin";
	}
	
	//직원 퇴근 등록
	@PostMapping("/leaveInsert")
	public String leaveInsert(@RequestParam(value="memberId") String memberId
			 				 ,@RequestParam(value="memberPw") String memberPw
			 				 ,WorkDTO workdto, MemberDTO memberdto, Model model) {
		//직원 아이디와 비밀번호 확인
		Map<String,Object> map = workService.employeeCheck(memberdto);
		String result = (String) map.get("result");
		
		if(!result.equals("확인")) {
			model.addAttribute("result", result);
			//System.out.println(result + "<--퇴근실패");
			return "work/leaveInsert";
		}
		//System.out.println(result + "<--퇴근 직원아이디확인");
		
		//출근등록을 하지않았을때
		int fail = workService.leaveInsert(workdto);
		if(fail == 0) {
			model.addAttribute("alert", "출근등록을 하지 않았습니다!");
			return "work/leaveInsert";
		}
		workService.leaveInsert(workdto);
		
		return "redirect:/workSuccess";
	}
	
	//직원 출.퇴근 검색
	@PostMapping("/searchWork")
	public String searchMember(@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage
							  ,@RequestParam(value="contractShopCode") String contractShopCode
							  ,@RequestParam(value="select") String select
							  ,@RequestParam(value="searchInput") String searchInput
							  ,@RequestParam(value="beginDate") String beginDate
							  ,@RequestParam(value="endDate") String endDate
							  ,Model model) {
		//System.out.println(beginDate + "~" + endDate + "<----날짜검색");
		Map<String, Object> map = workService.searchWork(currentPage, contractShopCode, select, searchInput, beginDate, endDate);
		model.addAttribute("workList"	 ,map.get("list"));
		model.addAttribute("currentPage" ,map.get("currentPage"));
		model.addAttribute("lastPage"	 ,map.get("lastPage"));
		model.addAttribute("startPageNum",map.get("startPageNum"));
		model.addAttribute("lastPageNum" ,map.get("lastPageNum"));
		
		return "work/workAdmin";
	}
}
