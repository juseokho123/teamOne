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

import com.smart.rider.main.service.MainService;
import com.smart.rider.spend.dto.JoinUtilityDTO;
import com.smart.rider.spend.dto.SpendAdminDTO;
import com.smart.rider.spend.dto.UtilityDTO;
import com.smart.rider.spend.service.SpendService;
import com.smart.rider.spend.service.UtilityService;
import com.smart.rider.subject.dto.SubjectDTO;

@Controller
public class UtilityController {

	@Autowired
	private UtilityService utilityService;
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private SpendService spendService;
	
	
	
	/*** 190926 재욱, 지출_공과금 등록내역 삭제 프로세스 ***/ 
	@GetMapping("/spendUtilityDelete")
	public String spendUtilityDelete(@RequestParam(value = "selectShopCode", required = false, defaultValue = "SR0000") String selectShopCode
									,@RequestParam(value = "spendUtilityCode") String spendUtilityCode
									,Model model
									,HttpSession session) {
		//System.out.println(spendUtilityCode + " <-- spendUtilityCode spendUtilityDelete() UtilityController.java");
		
		String contractShopCode = selectShopCode;
		String userLevel = (String)session.getAttribute("SLEVEL");
		//System.out.println(contractShopCode + " <-- contractShopCode spendUtilityUpdate() UtilityController.java");
		
		if(!userLevel.equals("관리자")) {
			contractShopCode = (String)session.getAttribute("SCODE");
			
			utilityService.utilityDelete(spendUtilityCode);
			return "redirect:/spendUtility";
		}
		
		utilityService.utilityDelete(spendUtilityCode);
		return "redirect:/spendUtility?selectShopCode=" + contractShopCode;
	}
	
	
	/*** 190926 재욱, 지출_공과금 등록내역 수정 프로세스 ***/ 
	@PostMapping("/spendUtilityUpdate")
	public String spendUtilityUpdate(@RequestParam(value = "selectShopCode", required = false, defaultValue = "SR0000") String selectShopCode
									,UtilityDTO utilityDTO
									,HttpSession session) {
		//System.out.println(utilityDTO.toString() + " <-- utilityDTO.toString() spendUtilityUpdate() UtilityController.java");
		
		String contractShopCode = selectShopCode;
		String userLevel = (String)session.getAttribute("SLEVEL");
		
		if(!userLevel.equals("관리자")) {
			contractShopCode = (String)session.getAttribute("SCODE");
			
			utilityService.utilityUpdate(utilityDTO);
			return "redirect:/spendUtility";
		}
		
		utilityService.utilityUpdate(utilityDTO);
		return "redirect:/spendUtility?selectShopCode=" + contractShopCode;
	}

	
	/*** 190925 재욱, 지출_공과금 등록내역 상세보기 화면 ***/ 
	@GetMapping("/spendUtilityDetails")
	public String spendUtilityDetails(String spendUtilityCode, Model model) {
		//System.out.println(spendUtilityCode + " <-- spendUtilityCode spendUtilityDetails UtilityController.java");
		List<JoinUtilityDTO> detailsList = utilityService.utilityDetails(spendUtilityCode);
		//System.out.println(detailsList + " <-- detailsList spendUtilityDetails UtilityController.java");
		model.addAttribute("detailsList", detailsList);
		
		return "spend/spendUtilityDetails";
	}
	
	
	/*** 190925 재욱, 지출_공과금 검색 화면 ***/
	@PostMapping("/spendUtilityList")
	public String spendUtilityList(@RequestParam(value = "utilityKey") String utilityKey
								 , @RequestParam(value = "utilityValue") String utilityValue
								 , @RequestParam(value = "beginDate") String beginDate
								 , @RequestParam(value = "endDate") String endDate
								 , @RequestParam(value = "utilityYear", required = false, defaultValue = "2019") String utilityYear
								 , @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage
								 , @RequestParam(value = "selectShopCode", required = false, defaultValue = "SR0000") String selectShopCode
								 , HttpSession session, Model model) {
		
		//System.out.println(utilityKey + " < -- utilityKey utilityList() UtilityController.java");
		//System.out.println(utilityValue + " < -- utilityValue utilityList() UtilityController.java");
		//System.out.println(beginDate + " < -- beginDate utilityList() UtilityController.java");
		//System.out.println(endDate + " < -- endDate utilityList() UtilityController.java");
		
		String contractShopCode = selectShopCode;
		String userLevel = (String)session.getAttribute("SLEVEL");
		
		if(!userLevel.equals("관리자")) {
			contractShopCode = (String)session.getAttribute("SCODE");
		}
		
		
		/*** 190925 재욱, begin 페이징 ***/
		Map<String, Object> map = utilityService.utilityList(currentPage, contractShopCode, utilityKey, utilityValue, beginDate, endDate);
		//System.out.println(map + " <-- map spendUtility UtilityController.java");
		
		int lastPageNum = (int)map.get("lastPageNum");
		int startPageNum = (int)map.get("startPageNum");
		int lastPage = (int)map.get("lastPage");
		
		@SuppressWarnings("unchecked")
		List<UtilityDTO> utilityList = (List<UtilityDTO>)map.get("list");
		//System.out.println(utilityList + " <-- utilityList spendUtility UtilityController.java");
		
		// 검색 결과가 없을시 텍스트 알림
		if(utilityList.size()==0) {
			model.addAttribute("alert", "검색 결과가 없습니다");
		}
		
		// 페이지 번호 model.addAttribute
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("utilityList", utilityList);
		/*** 190925 재욱, end 페이징 ***/
		

		/*** 190925 재욱, 지출_공과금 등록 계정과목 select box 리스트 ***/ // 
		List<SubjectDTO> subjectList = utilityService.subjectSelectBox();
		model.addAttribute("subjectSelectBox", subjectList);
		

		/*** 190926 재욱, begin 년도에 따른 월별 공과금 지출 금액 차트 ***/
		map.put("columnDate", "spend_utility_date");	// 조회할 날짜 db 컬럼
		map.put("columnInt", "spend_utility_pay"); 		// 합산할 db 컬럼 
		map.put("chartTable", "spend_utility");			// 조회할 db 테이블명
		map.put("contractShopCode", contractShopCode);	// 검색 조건, contractShopCode
		map.put("chartYear", utilityYear);				// 검색할 연도
		
		int[] chartValueArrays = mainService.chartValue(map);
		//System.out.println(Arrays.toString(chartValueArrays) + " <-- chartValueArrays spendUtility UtilityController.java");
		
		// to view model.addAttribute
		for(int i=0; i<12; i++) { 
			String utilityChart = "utility" + String.valueOf(i);
			model.addAttribute(utilityChart, chartValueArrays[i]);
		}
		/*** 190926 재욱, end 년도에 따른 월별 공과금 지출 금액 차트 ***/
		
		model.addAttribute("contractShopCode", contractShopCode);
		
		List<SpendAdminDTO> list = spendService.spendAdminDetails(contractShopCode);
		model.addAttribute("shop", list);
		
		return "spend/spendUtility";
	}

	
	/*** 190925 재욱, 지출_공과금 화면 ***/
	@GetMapping("/spendUtility")
	public String spendUtility(Model model, HttpSession session 
					  , @RequestParam(value = "utilityYear", required = false, defaultValue = "2019") String utilityYear
					  , @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage
					  , @RequestParam(value = "selectShopCode", required = false, defaultValue = "SR0000") String selectShopCode){

		String contractShopCode = selectShopCode;
		String userLevel = (String)session.getAttribute("SLEVEL");
		
		if(!userLevel.equals("관리자")) {
			contractShopCode = (String)session.getAttribute("SCODE");
		}
		
		
		//System.out.println(contractShopCode + " <-- contractShopCode spendUtility UtilityController.java");
		//System.out.println(utilityYear + " <-- utilityYear spendUtility() UtilityController.java");
		//System.out.println(contractShopCode + " <-- contractShopCode spendUtility() UtilityController.java");
		
		model.addAttribute("selectedYear", utilityYear);
		
		String utilityKey = "";
		String utilityValue = "";
		String beginDate = "";
		String endDate = "";
		
		
		/*** 190925 재욱, begin 페이징 ***/
		Map<String, Object> map = utilityService.utilityList(currentPage, contractShopCode, utilityKey, utilityValue, beginDate, endDate);
		//System.out.println(map + " <-- map spendUtility UtilityController.java");
		
		int lastPageNum = (int)map.get("lastPageNum");
		int startPageNum = (int)map.get("startPageNum");
		
		int lastPage = (int)map.get("lastPage");
		
		@SuppressWarnings("unchecked")
		List<UtilityDTO> utilityList = (List<UtilityDTO>)map.get("list");
		//System.out.println(utilityList + " <-- utilityList spendUtility UtilityController.java");
		
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("utilityList", utilityList);
		/*** 190925 재욱, end 페이징 ***/
		
		// 등록된 내역이 없을 시 텍스트 알림
		if(utilityList.size()==0) {
			model.addAttribute("result", "등록된 내역이 없습니다");
		}

		// 지출_공과금 등록 계정과목 select box 리스트
		List<SubjectDTO> subjectList = utilityService.subjectSelectBox();
		model.addAttribute("subjectSelectBox", subjectList);

		
		/*** 190926 재욱, begin 년도에 따른 월별 공과금 지출 금액 차트 ***/
		map.put("columnDate", "spend_utility_date");	// 조회할 날짜 db 컬럼
		map.put("columnInt", "spend_utility_pay"); 		// 합산할 db 컬럼 
		map.put("chartTable", "spend_utility");			// 조회할 db 테이블명
		map.put("contractShopCode", contractShopCode);	// 검색 조건, contractShopCode
		map.put("chartYear", utilityYear);				// 검색할 연도
		
		int[] chartValueArrays = mainService.chartValue(map);
		//System.out.println(Arrays.toString(chartValueArrays) + " <-- chartValueArrays spendUtility UtilityController.java");
		
		//반복문을 통해 배열에 값 입력
		for(int i=0; i<12; i++) {
			String utilityChart = "utility" + String.valueOf(i); // model에 담기 위한 변수명 생성
			model.addAttribute(utilityChart, chartValueArrays[i]); // 생성된 변수명에 각 배열의 값 담기
		}
		/*** 190926 재욱, end 년도에 따른 월별 공과금 지출 금액 차트 ***/

		model.addAttribute("contractShopCode", contractShopCode);
		
		List<SpendAdminDTO> list = spendService.spendAdminDetails(contractShopCode);
		model.addAttribute("shop", list);
		
		return "spend/spendUtility";
	}

	
	/*** 190925 재욱, 지출_공과금 내역 등록 ***/
	@PostMapping("/utilityInsert")
	public String utilityInsert(@RequestParam(value = "subjectCode") String subjectCode
							  , @RequestParam(value = "selectShopCode", required = false, defaultValue = "SR0000") String selectShopCode
							  , UtilityDTO utilityDTO
							  , HttpSession session) {
		
		String contractShopCode = selectShopCode;
		String userLevel = (String)session.getAttribute("SLEVEL");
		//System.out.println(contractShopCode + " <-- contractShopCode utilityInsert() UtilityController.java");
		
		if(!userLevel.equals("관리자")) {
			contractShopCode = (String)session.getAttribute("SCODE");
			
			utilityService.utilityInsert(utilityDTO, contractShopCode);
			return "redirect:/spendUtility";
		}
		
		
		utilityService.utilityInsert(utilityDTO, contractShopCode);

		return "redirect:/spendUtility?selectShopCode=" + contractShopCode;
	}

}
