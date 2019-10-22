package com.smart.rider.goods.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.account.dto.AccountDTO;
import com.smart.rider.account.service.AccountService;
import com.smart.rider.goods.dto.GoodsPurchaseDTO;
import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.goods.service.GoodsPurchaseService;
import com.smart.rider.goods.service.GoodsdbService;
import com.smart.rider.member.dto.MemberDTO;


@Controller
public class GoodsPurchaseController {
	@Autowired
	private GoodsPurchaseService goodsPurchaseService;
	@Autowired
	private GoodsdbService goodsdbservice;
	@Autowired
	private AccountService accountService;
	
	//삭제가능한매입리스트상세보기
	@GetMapping("/yesPurchaseList")
	public String yesGoodsDbList(@RequestParam(value="purchaseCode")String purchaseCode,Model model) {
			
		model.addAttribute("y", goodsPurchaseService.yesPurchaseList(purchaseCode));
		
		return "purchase/yesPurchaseList";
		
	}
	
	//매입 삭제
	@GetMapping("/purchaseDelete")
	public String purchaseDelete(@RequestParam(value="purchaseCode")String purchaseCode,
								Model model) {
		//System.out.println("삭제 코드넘어오는값확인"+model.addAttribute("pList", goodsPurchaseService.getPurchaseList(purchaseCode)));
		model.addAttribute("pList", goodsPurchaseService.getPurchaseList(purchaseCode));
		model.addAttribute("purchaseCode", purchaseCode);
		return "purchase/purchaseDelete";
	}
	//매입삭제 
	  @PostMapping("/purchaseDelete")
	  public String purchaseDelete(GoodsPurchaseDTO goodsPurchaseDto,MemberDTO memberDto,Model model) {
		
		  int result = goodsPurchaseService.purchaseDelete(goodsPurchaseDto.getPurchaseCode()
				  											,memberDto.getMemberId()
				  											,memberDto.getMemberPw());
		  //System.out.println(goodsPurchaseDto.getPurchaseCode());
		  //System.out.println(memberDto.getMemberId());
		  //System.out.println(memberDto.getMemberPw());
		  if(result==0 ) {			  
			  model.addAttribute("result", "비밀번호불일치");			  
			  model.addAttribute("memberId", memberDto.getMemberId());
			  model.addAttribute("pList", goodsPurchaseService.getPurchaseList(goodsPurchaseDto.getPurchaseCode()));
			  return"purchase/purchaseDelete";
		}
		
		  return "redirect:purchaseList";
	  }
	 
	
	//매입 검색메서드
	@PostMapping("/purchaseSearchList")
	public String purchaseSearchList(@RequestParam(value="select")String select
										,@RequestParam(value="searchInput")String searchInput
										,@RequestParam(value="beginDate")String beginDate
										,@RequestParam(value="endDate")String endDate										
										,Model model,HttpSession session) {
		//List<GoodsPurchaseDTO> search = goodsPurchaseService.purchaseSearchList(select, searchInput, beginDate, endDate);
		String SCODE = (String)session.getAttribute("SCODE");
		
		
		List<GoodsHapDTO> yList = goodsPurchaseService.purchaseYlist(select, searchInput, beginDate, endDate,SCODE);
		model.addAttribute("yList",yList);
		List<GoodsHapDTO> nList = goodsPurchaseService.purchaseNlist(select, searchInput, beginDate, endDate,SCODE);		
		model.addAttribute("nList",nList);
		//System.out.println(select + " <-- select goodsSearchList GoodsController.java");
		//System.out.println(searchInput + " <-- searchInput goodsSearchList GoodsController.java");
		//System.out.println(beginDate + " <-- beginDate goodsSearchList GoodsController.java");
		//System.out.println(endDate + " <-- endDate goodsSearchList GoodsController.java");
		//System.out.println("검색ㄷ확인,,,,,,,,,,,,,,,,,,,,,,,,,,"+search);
		//model.addAttribute("hList", search);
		
		if(yList.size()==0 && nList.size()==0 ) {
			model.addAttribute("alert", "검색 결과가 없습니다");
		}
		
		return "purchase/purchaseList";
	}
	
	//매입상세보기
	@GetMapping("/getPurchaseList")
	public String getPurchaseList(@RequestParam(value="purchaseCode")String purchaseCode,Model model) {
		//System.out.println(model.addAttribute("purchaseCode", goodsPurchaseService.getPurchaseList(purchaseCode)));
		model.addAttribute("purchaseCode", goodsPurchaseService.getPurchaseList(purchaseCode));
		return "purchase/getPurchaseList";
	}
	
	//매입리스트 
	@GetMapping("/purchaseList")
	public String purchase(Model model,HttpSession session) {
		String select = null;
		String searchInput = "";
		String beginDate = "";
		String endDate = "";
		String SCODE = (String)session.getAttribute("SCODE");
		String SLEVEL = (String)session.getAttribute("SLEVEL");
		
		Map<String,Object> map = goodsPurchaseService.purchaseList(select, searchInput, beginDate, endDate, SCODE, SLEVEL);
		
		@SuppressWarnings("unchecked")
		List<GoodsHapDTO> yList = (List<GoodsHapDTO>)map.get("yList");
		@SuppressWarnings("unchecked")
		List<GoodsHapDTO> nList = (List<GoodsHapDTO>)map.get("nList");
		
		
		/*
		 * List<GoodsHapDTO> yList = goodsPurchaseService.purchaseYlist(select,
		 * searchInput, beginDate, endDate); model.addAttribute("yList",yList);
		 * List<GoodsHapDTO> nList = goodsPurchaseService.purchaseNlist(select,
		 * searchInput, beginDate, endDate); model.addAttribute("nList",nList);
		 */
		 
		
		//List<GoodsHapDTO> hList = goodsPurchaseService.purchaseList();
		//System.out.println(model.addAttribute("hList", hList));
		
		
		  model.addAttribute("yList",yList);
		  model.addAttribute("nList",nList);		 
		
		//  model.addAttribute("hList",hList);
	
		
		return "purchase/purchaseList";
	}

	
	
	//상품DB코드로 매입상품등록요청
	//19-09-20 문영성
	@GetMapping("/purchaseInsert")
	public String purchaseInsert(@RequestParam(value="goodsDbCode")String goodsDbCode, Model model,HttpSession session) {
		//System.out.println(goodsDbCode+"<======매입등록시작 DB코드값 확인");
		model.addAttribute("goodsDbCode", goodsdbservice.getGoodsDbCode(goodsDbCode));
		
		String sCode = (String)session.getAttribute("SCODE");
		String sLevel = (String)session.getAttribute("SLEVEL");
		Map<String, Object> map =  accountService.accountList(sCode,sLevel);
		@SuppressWarnings("unchecked")
		List<AccountDTO> pListYes = (List<AccountDTO>)map.get("accountListYes");
		@SuppressWarnings("unchecked")
		List<AccountDTO> pListNo = (List<AccountDTO>)map.get("accountListNo");

		map.get("accountListNo");
		
		/* 거래처 코드리스트 */

		map.get("accountListNo");
		/* 거래처 코드리스트 */	

		model.addAttribute("pListYes", pListYes);
		model.addAttribute("pListNo", pListNo);
		//System.out.println(pListYes + "<- Yes 담겨있는값");
		//System.out.println(pListNo + "<-No 담겨있는값");

		
		return "purchase/purchaseInsert"; 
	}
	//상품등록 
	@PostMapping("/purchaseInsert")
	public String purchaseInsert(GoodsPurchaseDTO goodsPurchaseDto,HttpSession session, Model model) {
		/* System.out.println(goodsPurchaseDto+"<<<<<<<<<<<<<<<<<<<<<<매입insert 확인"); */
		String contractShopCode = (String)session.getAttribute("SCODE");
		//System.out.println(contractShopCode+"<======매입등록시작 DB코드값 확인");
		goodsPurchaseDto.setContractShopCode(contractShopCode);
		//System.out.println("goodsPurchaseDto: "+goodsPurchaseDto);
		goodsPurchaseService.purchaseInsert(goodsPurchaseDto);
		return "redirect:purchaseList";
	}
	
	
}
