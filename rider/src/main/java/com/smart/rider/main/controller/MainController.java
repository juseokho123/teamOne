package com.smart.rider.main.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.main.service.MainService;


@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@GetMapping("/")
	public String index(@RequestParam(value = "totalYear", required = false, defaultValue = "2019") String totalYear
					   ,HttpSession session
					   ,Model model) {
		
		String contractShopCode = (String)session.getAttribute("SCODE");
		//System.out.println(contractShopCode + " <-- contractShopCode index() MaindController.java");
		
		if(contractShopCode == null) {
			contractShopCode = "SR0000";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		/** 191008 재욱, Read : 지출_통합 급여 월별 총 지출 금액 차트 **/
		map.put("columnDate", "spend_salary_date");		// 조회할 날짜 db 컬럼
		map.put("columnInt", "spend_salary_total"); 	// 합산할 db 컬럼 
		map.put("chartTable", "spend_salary");			// 조회할 db 테이블명
		map.put("contractShopCode", contractShopCode);	// 검색 조건, contractShopCode
		map.put("chartYear", totalYear);				// 검색할 연도
		
		int[] salaryChartArrays = mainService.chartValue(map);
		//System.out.println(Arrays.toString(salaryChartArrays) + " <-- salaryChartArrays index() MainController.java");
		
		/** 191008 재욱, Read : 지출_통합 공과금/기타 월별 총 지출 금액 차트 **/
		map.put("columnDate", "spend_utility_date");
		map.put("columnInt", "spend_utility_pay");
		map.put("chartTable", "spend_utility");	
		map.put("contractShopCode", contractShopCode);
		map.put("chartYear", totalYear);
		
		int[] utilityChartArrays = mainService.chartValue(map);
		//System.out.println(Arrays.toString(utilityChartArrays) + " <-- utilityChartArrays index() MainController.java");
		
		/** 191008 재욱, Read : 지출_통합 매입 월별 총 지출 금액 차트 **/
		map.put("columnDate", "purchase_date");	
		map.put("columnInt", "purchase_pay"); 
		map.put("chartTable", "purchase");	
		map.put("contractShopCode", contractShopCode);
		map.put("chartYear", totalYear);
		
		int[] purchaseChartArrays = mainService.chartValue(map);
		//System.out.println(Arrays.toString(purchaseChartArrays) + " <-- purchaseChartArrays index() MainController.java");
		
		int[] spendSum = new int[12]; // 매입, 급여, 공과금/기타 합산할 배열 선언
		
		for(int i=0; i<12; i++) { // 반복문을 통한 배열에 각각의 값 합산하여 대입
			spendSum[i] = salaryChartArrays[i] + utilityChartArrays[i] + purchaseChartArrays[i];
		}
		
		// to view model.addAttribute
		for(int i=0; i<12; i++) { 
			String spend = "spend" + String.valueOf(i);
			model.addAttribute(spend, spendSum[i]);
		}
		//System.out.println(Arrays.toString(spendSum) + " <-- spendSum index() MainController.java");
		
		
		/** 191008 재욱, Read : 지출_통합 매출 월별 총 지출 금액 차트 **/
		map.put("columnDate", "sales_date");	
		map.put("columnInt", "sales_amount"); 
		map.put("chartTable", "sales");	
		map.put("contractShopCode", contractShopCode);
		map.put("chartYear", totalYear);
		
		int[] salesChartArrays = mainService.chartValue(map);
		//System.out.println(Arrays.toString(salesChartArrays) + " <-- salesChartArrays index() MainController.java");
		
		for(int i=0; i<12; i++) { 
			String sales = "sales" + String.valueOf(i);
			model.addAttribute(sales, salesChartArrays[i]);
		}
		
		int[] netPay = new int[12]; // 순수익 계산할 배열 선언
		
		for(int i=0; i<12; i++) {
			netPay[i] = salesChartArrays[i] - spendSum[i];
			String net = "netPay" + String.valueOf(i);
			model.addAttribute(net, netPay[i]);
		}		
		return "index";
	}
	
	
}
