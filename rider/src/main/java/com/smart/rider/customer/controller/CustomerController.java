package com.smart.rider.customer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.customer.dto.CustomerDTO;
import com.smart.rider.customer.service.CustomerService;
import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.goods.service.GoodsRentalService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private GoodsRentalService goodsRentalService;
	
	//19.09.20작성 대여고객등록화면
	@GetMapping("/customerInsert")
	public String customerInsert() {
		
		return "customer/customerInsert";
	}
	
	//19.10.10작성 대여고객등록처리
	@PostMapping("/customerInsert")
	public String customerInsert(CustomerDTO customerdto) {
		customerService.customerInsert(customerdto);
		
		return "redirect:/customerList";
	}
	
	//19.09.23작성 대여고객상세보기
	@GetMapping("/getCustomerList")
	public String getCustomerList(@RequestParam(value="rentalCustomerCode") String rentalCustomerCode, Model model) {
		model.addAttribute("customerList", customerService.getCustomerList(rentalCustomerCode));
		
		return "customer/customerUpdate";
	}
	
	//19.10.02작성 페이지 작업
	@GetMapping("/customerList")
	public String customerList(@RequestParam(value="contractShopCode", defaultValue="") String contractShopCode
							  ,@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage
							  ,Model model) {
		Map<String, Object> map = customerService.customerList(currentPage, contractShopCode);
		
		model.addAttribute("customerList"	,map.get("list"));
		model.addAttribute("currentPage"	,map.get("currentPage"));
		model.addAttribute("lastPage"		,map.get("lastPage"));
		model.addAttribute("startPageNum"	,map.get("startPageNum"));
		model.addAttribute("lastPageNum"	,map.get("lastPageNum"));
		
		return "customer/customerList";
	}
	
	//19.10.10작성 대여상품 리스트가져오기
	@GetMapping("/rentalGoodsList")
	public String rentalGoodsList(Model model, HttpSession session) {
		String select = null;
		String searchInput = "";
		String beginDate = "";
		String endDate = "";
		String SCODE = (String)session.getAttribute("SCODE");
		String SLEVEL = (String)session.getAttribute("SLEVEL");
		Map<String, Object> map = goodsRentalService.goodsRentalList(select, searchInput, beginDate, endDate, SCODE, SLEVEL);
		@SuppressWarnings("unchecked")
		List<GoodsHapDTO> rList = (List<GoodsHapDTO>)map.get("rList");
		
		model.addAttribute("rList", rList);
		
		return "customer/rentalGoodsList";
	}
	
	//대여상품 상세보기
	@GetMapping("/rentalGoodsDetail")
	public String goodsRentalUpdate(@RequestParam(value="goodsRentalCode")String goodsRentalCode, Model model) {
		model.addAttribute("goodsRentalCode", goodsRentalService.getGoodsRentalList(goodsRentalCode));
		
		return "customer/rentalGoodsDetail";
	}
	
	//대여고객 수정처리
	@PostMapping("/customerUpdate")
	public String customerUpdate(CustomerDTO customerdto) {
		customerService.customerUpdate(customerdto);
		
		return "redirect:/customerList";
	}
	
	//대여고객 목록에서 반납버튼클릭시 반납처리
	@GetMapping("/returnButton")
	public String returnButton(CustomerDTO customerdto) {
		customerService.returnButton(customerdto);
		
		return "redirect:/customerList";
	}
	
	//대여고객 검색
	@PostMapping("/searchCustomer")
	public String searchCustomer(@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage
							  	,@RequestParam(value="contractShopCode") String contractShopCode
							  	,@RequestParam(value="returnNo", defaultValue="") String returnNo
							  	,@RequestParam(value="overdueYes", defaultValue="") String overdueYes
							  	,@RequestParam(value="select") String select
							  	,@RequestParam(value="searchInput") String searchInput
							  	,@RequestParam(value="selectDate") String selectDate
							  	,@RequestParam(value="beginDate") String beginDate
							  	,@RequestParam(value="endDate") String endDate
							  	,Model model) {
		//System.out.println(returnNo + "<--체크확인");
		Map<String, Object> map = customerService.searchCustomer(currentPage, contractShopCode, returnNo, overdueYes
																,select, searchInput, selectDate, beginDate, endDate);
		model.addAttribute("customerList"	,map.get("list"));
		model.addAttribute("currentPage"	,map.get("currentPage"));
		model.addAttribute("lastPage"		,map.get("lastPage"));
		model.addAttribute("startPageNum"	,map.get("startPageNum"));
		model.addAttribute("lastPageNum"	,map.get("lastPageNum"));
		
		return "customer/customerList";
	}
}
