package com.smart.rider.spend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.main.dto.SearchDTO;
import com.smart.rider.main.service.MainService;
import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.spend.dto.JoinSalaryDTO;
import com.smart.rider.spend.dto.SpendAdminDTO;
import com.smart.rider.spend.service.SalaryService;
import com.smart.rider.spend.service.SpendService;

@Controller
public class SalaryController {
	
	@Autowired
	private SalaryService salaryService;
	
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private SpendService spendService;
	
	/**** 190102 재욱, Update : 지출_급여 수정 ****/
	@PostMapping("/salaryUpdate")
	public String salaryUpdate(@RequestParam(value = "selectShopCode", required = false, defaultValue = "SR0000") String selectShopCode
							  ,JoinSalaryDTO joinSalaryDTO
							  ,Model model
							  ,HttpSession session) {
		
		//System.out.println(joinSalaryDTO.toString() + " <-- joinSalaryDTO.toString() salaryUpdate SalaryController.java");
		
		String contractShopCode = selectShopCode;
		String userLevel = (String)session.getAttribute("SLEVEL");
		
		if(!userLevel.equals("관리자")) {
			contractShopCode = (String)session.getAttribute("SCODE");
			
			salaryService.salaryUpdate(joinSalaryDTO);
			return "redirect:/spendSalary";
		}
		
		salaryService.salaryUpdate(joinSalaryDTO);
		
		return "redirect:/spendSalary?selectShopCode=" + contractShopCode;
	}
	
	
	/**** 190930 재욱, Read : 지출_급여 상세보기 ****/
	@GetMapping("/spendSalaryDetails")
	public String spendSalaryDetails(@RequestParam(value = "selectShopCode", required = false, defaultValue = "SR0000") String selectShopCode
									,@RequestParam(value = "spendSalaryCode") String spendSalaryCode
									,HttpSession session
									,Model model) {
		//System.out.println(spendSalaryCode + " <-- spendSalaryCode spendSalaryDetails SalaryController.java");
		//System.out.println(selectShopCode + " <-- selectShopCode spendSalaryDetails() SalaryController.java");
		
		String contractShopCode = selectShopCode;
		String userLevel = (String)session.getAttribute("SLEVEL");
		
		if(!userLevel.equals("관리자")) {
			contractShopCode = (String)session.getAttribute("SCODE");
			
			List<JoinSalaryDTO> list = salaryService.spendSalaryDetails(contractShopCode, spendSalaryCode);
			model.addAttribute("salaryDetails", list);
			return "spend/spendSalaryDetails";
		}
		
		List<JoinSalaryDTO> list = salaryService.spendSalaryDetails(contractShopCode, spendSalaryCode);
		model.addAttribute("salaryDetails", list);
		return "spend/spendSalaryDetails";
	}
	
	
	/**** 190930 재욱, 지출_급여 등록 ****/
	@PostMapping("/salaryInsert")
	public String salaryInsert(@RequestParam(value = "masterShopCode", required = false, defaultValue = "SR0000") String masterShopCode 
							, JoinSalaryDTO salaryDTO
							, HttpSession session) {
		//System.out.println(salaryDTO.toString() + " <-- salaryDTO.toString() salaryInsert() SalaryController.java");
		
		String contractShopCode = (String)session.getAttribute("SCODE");
		String userLevel = (String)session.getAttribute("SLEVEL");
		
		/** 191001 재욱, 관리자 권한으로 계약된 매장 내역 **/
		if(userLevel.equals("관리자")) {
			contractShopCode = masterShopCode;
			salaryService.salaryInsert(salaryDTO, contractShopCode);	
			return "redirect:/spendSalary?selectShopCode=" + contractShopCode;
		}
		
		salaryService.salaryInsert(salaryDTO, contractShopCode);
		
		return "redirect:/spendSalary";
	}
	
	
	/**** 191001 재욱, 지출_급여 검색 화면 ****/
	@PostMapping("/spendSalaryList")
	public String spendSalaryList(@RequestParam(value = "selectShopCode", required = false, defaultValue = "SR0000") String selectShopCode
								, @RequestParam(value = "salaryYear", required = false, defaultValue = "2019") String salaryYear
								, @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage
								, SearchDTO searchDTO
								, HttpSession session
								, Model model) {
		
		//System.out.println(searchDTO.toString() + " <-- searchDTO.toString() spendSalaryList() SalaryController.java");
		
		this.spendSalary(selectShopCode, salaryYear, currentPage, searchDTO, session, model);
		
		return "spend/spendSalary";
	}
	
	
	
	/**** 190927 재욱, 지출_급여 화면 ****/
	@GetMapping("/spendSalary")
	public String spendSalary(@RequestParam(value = "selectShopCode", required = false, defaultValue = "SR0000") String selectShopCode
							, @RequestParam(value = "salaryYear", required = false, defaultValue = "2019") String salaryYear
							, @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage
							, SearchDTO searchDTO
							, HttpSession session
							, Model model) {
		
		String contractShopCode = selectShopCode;
		String userLevel = (String)session.getAttribute("SLEVEL");
		
		if(!userLevel.equals("관리자")) {
			contractShopCode = (String)session.getAttribute("SCODE");
		}
		
		/** 190927 재욱, 지출_급여 직원 select box list **/
		List<MemberDTO> employeeSelect = salaryService.salarySelectBox(contractShopCode);
		//System.out.println(salarySelectList + " <-- salarySelectList spendSalary() SalaryController.java");
		model.addAttribute("employeeSelect", employeeSelect);
		model.addAttribute("selectedYear", salaryYear);
		
		// 등록된 직원이 없을시 select box alert
		if(employeeSelect.size() == 0) {
			model.addAttribute("alert", "등록된 직원이 없습니다");
		}
		
		/** 191001 재욱, Read : 지출_급여 등록 내역 **/
		Map<String, Object> map = salaryService.salaryList(contractShopCode, searchDTO, currentPage);
		
		@SuppressWarnings("unchecked")
		List<JoinSalaryDTO> salaryList = (List<JoinSalaryDTO>)map.get("salaryList");
		//System.out.println(salaryList + " <-- list spendSalary() SalaryController.java");
		model.addAttribute("salaryList", salaryList);
		
		// 검색 결과가 없을시 텍스트 알림
		if(salaryList.size()==0) {
			model.addAttribute("result", "검색 결과가 없습니다");
		}
		
		/** 191001 재욱, Read : 지출_급여 월별 총 지출 금액 차트 **/
		map.put("columnDate", "spend_salary_date");	// 조회할 날짜 db 컬럼
		map.put("columnInt", "spend_salary_total"); 		// 합산할 db 컬럼 
		map.put("chartTable", "spend_salary");			// 조회할 db 테이블명
		map.put("contractShopCode", contractShopCode);	// 검색 조건, contractShopCode
		map.put("chartYear", salaryYear);				// 검색할 연도
		
		int[] chartValueArrays = mainService.chartValue(map);
		//System.out.println(Arrays.toString(chartValueArrays) + " <-- chartValueArrays spendUtility UtilityController.java");
		
		// to view model.addAttribute
		for(int i=0; i<12; i++) { 
			String salaryChart = "salary" + String.valueOf(i);
			model.addAttribute(salaryChart, chartValueArrays[i]);
		}
		
		model.addAttribute("selectedYear", salaryYear);
		
		/** 191007 재욱, Read : 지출_급여 등록 내역 페이징 **/
		int lastPageNum = (int)map.get("lastPageNum");
		int startPageNum = (int)map.get("startPageNum");
		int lastPage = (int)map.get("lastPage");
		
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		
		model.addAttribute("contractShopCode", contractShopCode);
		
		List<SpendAdminDTO> list = spendService.spendAdminDetails(contractShopCode);
		model.addAttribute("shop", list);
		
		return "spend/spendSalary";
	}
	
	
	
}
