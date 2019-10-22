package com.smart.rider.sales.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.goods.dto.GoodsDTO;
import com.smart.rider.goods.dto.GoodsHapDTO;

import com.smart.rider.goods.service.GoodsRentalService;
import com.smart.rider.goods.service.GoodsService;
import com.smart.rider.main.service.MainService;
import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.sales.dto.SalesDTO;
import com.smart.rider.sales.service.SalesService;


@Controller
public class SalesController {
	@Autowired
	private SalesService salesService;
	@Autowired
	private GoodsRentalService goodsRentalService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private MainService mainService;
	
	//매출검색
	@PostMapping("/salesSearchList")
	public String salesSearchList(@RequestParam(value="select")String select
									,@RequestParam(value="searchInput")String searchInput
									,@RequestParam(value="beginDate")String beginDate
									,@RequestParam(value="endDate")String endDate
									,@RequestParam(value = "salesYear", required = false, defaultValue = "2019") String salesYear
									,Model model,HttpSession session) {
		
		String SCODE = (String)session.getAttribute("SCODE");
		/*
		 * System.out.println("매출 앞날짜검색----"+beginDate);
		 * System.out.println("매출 뒤날짜검색-*---"+endDate);
		 * System.out.println("판매/대여 카테고리*****"+select);
		 * System.out.println("판매/대여!!!!!!!!!!!!!"+searchInput);
		 */
		//년도별 월별 차트값
		Map<String,Object> map = new HashMap<String, Object>();
			map.put("columnDate", "sales_date");	// 조회할 날짜 db 컬럼
			map.put("columnInt", "sales_amount"); 		// 합산할 db 컬럼 
			map.put("chartTable", "sales");			// 조회할 db 테이블명
			//map.put("contractShopCode", contractShopCode);	// 검색 조건, contractShopCode
			map.put("chartYear", salesYear);				// 검색할 연도	
			int[] chartValueArrays = mainService.chartValue(map);
			//System.out.println(Arrays.toString(chartValueArrays) + " <-- salesController.java");
			
			//반복문을 통해 배열에 값 입력
			for(int i=0; i<12; i++) {
				String salesChart = "sales" + String.valueOf(i); // model에 담기 위한 변수명 생성
				model.addAttribute(salesChart, chartValueArrays[i]); // 생성된 변수명에 각 배열의 값 담기
				//System.out.println("차트값확인~~~~~~~~~~~~~~~~~~~~~~~~~~"+salesChart.toString());
			}
		
		  List<SalesDTO> search = salesService.salesSearchList(select, searchInput, beginDate, endDate, SCODE);
		  
		  model.addAttribute("sList", search);
		  
		  if(search.size()==0) {
		  
		  model.addAttribute("alert", "검색 결과가 없습니다");
		  } 
		 
		 		
		return "sales/salesList";
	}
	//매출삭제	
	@GetMapping("/salesDelete")
	public String salesDelete(@RequestParam(value="salesCode")String salesCode,Model model) {
		model.addAttribute("salesCode", salesService.getSalesList(salesCode));
		return "sales/salesDelete";
	}
	//매출삭제처리
	@PostMapping("/salesDelete")
	public String goodsRentalDelete(SalesDTO salesDto,MemberDTO memberDto,Model model) {
		int result = salesService.salesDelete(salesDto.getSalesCode(), memberDto.getMemberId(),memberDto.getMemberPw());
		if(result == 0) {
			model.addAttribute("result", "비밀번호를 바르게입력하세요");
			model.addAttribute("salesCode", salesService.getSalesList(salesDto.getSalesCode()));
			return "sales/salesDelete";
		}
		return "redirect:salesList";
	
	}	

	//매출수정
	@PostMapping("/salesUpdate")
	public String salesUpdate(SalesDTO salesDto) {
		//System.out.println("매출수정값 가져오기"+salesDto);
		salesService.salesUpdate(salesDto);
		return "redirect:salesList";
		
	}
	//매출상세보기
	@GetMapping("/getSalesList")
	public String getSalesList(@RequestParam(value="salesCode")String salesCode,Model model) {
		model.addAttribute("salesCode", salesService.getSalesList(salesCode));
		return "sales/getSalesList";
	}
	
	//매출등록요청
	@GetMapping("/salesInsert")
	public String salesInsert(Model model,String goodsCode) {
		//System.out.println("매출등록시 상품코드값"+goodsCode);
		//System.out.println("등록요청시 값확인"+goodsService.getGoodsList(goodsCode));
		
		model.addAttribute("goodsCode", goodsService.getGoodsList(goodsCode));
		//model.addAttribute("goodsRentalCode", goodsRentalService.getGoodsRentalList(goodsRentalCode));
		
		return "sales/salesInsert";
	}
	//대여품매출등록
	@GetMapping("/salesInsert2")
	public String salesInsert2(Model model,String goodsRentalCode) {
		//System.out.println("매출등록시 상품코드값"+goodsCode);
		//System.out.println("등록요청시 값확인"+goodsService.getGoodsList(goodsCode));
		
		model.addAttribute("goodsRentalCode", goodsRentalService.getGoodsRentalList(goodsRentalCode));
		//model.addAttribute("goodsRentalCode", goodsRentalService.getGoodsRentalList(goodsRentalCode));
		
		return "sales/salesInsert2";
	}
	//매출등록처리
	@PostMapping("/salesInsert")
	public String salesInsert(SalesDTO salesDto,GoodsDTO goodsDto,HttpSession session) {
		//System.out.println("매출등록 입력확인"+salesDto);
		goodsService.goodsSalesUpdate(goodsDto);
		String contractShopCode = (String)session.getAttribute("SCODE");
		salesDto.setContractShopCode(contractShopCode);
		salesService.salesInsert(salesDto);
		return "redirect:salesList";
		
	}
	//대여매출등록처리
		@PostMapping("/salesRentalInsert")
		public String salesRentalInsert(SalesDTO salesDto,GoodsDTO goodsDto,HttpSession session) {
			//System.out.println("매출등록 입력확인"+salesDto);
			//상품
			goodsService.goodsSalesRentalUpdate(goodsDto);
			String contractShopCode = (String)session.getAttribute("SCODE");
			salesDto.setContractShopCode(contractShopCode);
			salesService.salesInsert(salesDto);
			return "redirect:salesList";
			
		}
	//매출리스트 조회요청
	@GetMapping("/salesList")
	public String salseList(Model model,@RequestParam(value = "salesYear", required = false, defaultValue = "2019") String salesYear 
							,HttpSession session) {
		String select = null;
		String searchInput = "";
		String beginDate = "";
		String endDate = "";
		String SCODE = (String)session.getAttribute("SCODE");
		String SLEVEL = (String)session.getAttribute("SLEVEL");
		
		Map<String,Object> map = salesService.salesList(select, searchInput, beginDate, endDate, SCODE, SLEVEL);
		@SuppressWarnings("unchecked")	
		List<GoodsHapDTO> sList = (List<GoodsHapDTO>)map.get("sList");
		
		//매출합 차트에 보여주기,,main참조
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("columnDate", "sales_date");	// 조회할 날짜 db 컬럼
		map1.put("columnInt", "sales_amount"); 		// 합산할 db 컬럼 
		map1.put("chartTable", "sales");			// 조회할 db 테이블명
		//map.put("contractShopCode", contractShopCode);	// 검색 조건, contractShopCode
		map1.put("chartYear", salesYear);				// 검색할 연도	
		int[] chartValueArrays = mainService.chartValue(map1);
		//System.out.println(Arrays.toString(chartValueArrays) + " <-- salesController.java");
		
		//반복문을 통해 배열에 값 입력
		for(int i=0; i<12; i++) {
			String salesChart = "sales" + String.valueOf(i); // model에 담기 위한 변수명 생성
			model.addAttribute(salesChart, chartValueArrays[i]); // 생성된 변수명에 각 배열의 값 담기
			//System.out.println("차트값확인~~~~~~~~~~~~~~~~~~~~~~~~~~"+salesChart.toString());
		}
	
		//System.out.println("매출리스트 뽑아오기"+sList);
		model.addAttribute("sList", sList);
		
		
		return "sales/salesList";
	}
	
}
