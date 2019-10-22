package com.smart.rider.shop.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.smart.rider.contract.dto.ContractDTO;
import com.smart.rider.contract.service.ContractService;
import com.smart.rider.main.dto.SearchDTO;
import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.shop.dto.PosDTO;
import com.smart.rider.shop.dto.ShopDTO;
import com.smart.rider.shop.dto.ShopRelationDTO;
import com.smart.rider.shop.service.PosService;
import com.smart.rider.shop.service.ShopService;

@Controller
public class ShopController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private ContractService contractService;
	@Autowired
	private PosService posService;
	
	//매장 관리쪽 목록들 받기
	//매장으로 넘어 갈때 필요한 값들
	@GetMapping("/shopInsert")
	public String shopInsert(Model model) {
		List<ContractDTO> contractList =  contractService.contractList();
		//필요한 contractList에 담긴 값을 가져오기 위해 model 사용
		//System.out.println("contractList:"+contractList);
		model.addAttribute("contractList", contractList);
		return  "/shop/shopInsert";
	}
	
	//매장 생성
	@PostMapping("/shopInsert")
	public String shopInsert(ShopDTO shop,HttpSession session,ShopRelationDTO relation) {
		//입력값 확인
		//System.out.println(shop+"<-담겨있는값");
		shopService.shopInsert(shop,session,relation);
		return "redirect:/shop";
	}
	
	//수정입력 값 받기
	@GetMapping("/shopUpdate")
	public String shopUpdate(@RequestParam(value ="shopCode") String shopCode,Model model) {
		//System.out.println(shopCode + "<--넘어오는 코드값 확인");
		List<ShopDTO> shopList = shopService.shopUpdate(shopCode);
		//System.out.println(shopList + "<--코드로 조회하는 데이터 확인");
		//shopList에 담겨있는 값 모델에 담기
		model.addAttribute("shopList", shopList);
		return "shop/shopUpdate";
	}
	
	//수정
	@PostMapping("/shopUpdate")
	public String shopUpdate(ShopDTO shop) {
		//System.out.println(shop + "<--  수정된 값");
		shopService.shopUpdate(shop);
		return "redirect:/shop";
	}
	
	//상세보기
	@GetMapping("/shopList")
	public String getShopList(Model model,HttpSession session) {
		//맵으로 받기
		Map<String, Object> map =  shopService.getShopList(session);
		//SuppressWarnings("unchecked") 메소드상태가 경고 일 때 나오지 않게 해주기
		@SuppressWarnings("unchecked")
		List<ShopDTO> shopListYes = (List<ShopDTO>)map.get("shopListYes");
		@SuppressWarnings("unchecked")
		List<ShopDTO> shopListNo = (List<ShopDTO>)map.get("shopListNo");
		//System.out.println(shopListYes);
		//System.out.println(shopListNo);
		//입력값 확인 후 모델값 값을 넣는다.
		model.addAttribute("shopListYes", shopListYes);
		model.addAttribute("shopListNo", shopListNo);
		return "shop/shopList";
	}
	
	//상세보기에서 검색시
	@PostMapping("/shopSearchList")
	public String shopSearchList(SearchDTO search, Model model,HttpSession session) {
		//System.out.println(search + "<-- 담겨있는값 ");
		Map<String,Object> map = shopService.shopSearchList(search,session);
		// model에 값 넣기
		@SuppressWarnings("unchecked")
		List<ShopDTO> shopListYes = (List<ShopDTO>)map.get("shopSearchListYes");
		@SuppressWarnings("unchecked")
		List<ShopDTO> shopListNo = (List<ShopDTO>)map.get("shopSearchListNo");
		//System.out.println(shopListYes +"<--삭제가능리스트 확인");
		//System.out.println(shopListNo +"<--삭제불가능리스트 확인");
		//model에 대입값 넣기
		model.addAttribute("shopListYes", shopListYes);
		model.addAttribute("shopListNo", shopListNo);
		//조회 결과가 없으면 나오는 문장
		if(shopListYes.size()  == 0  && shopListNo.size() == 0) {
			model.addAttribute("alert", "검색 결과가 없습니다");
		}
		return "shop/shopList";
	}
	
	//매장 목록 5개씩 보여주기
	@GetMapping("/shop")
	public String shop(@RequestParam(value="currentPage",required=false, defaultValue="1") int currentPage
						,Model model,HttpSession session) {
		//값 확인
		List<ShopRelationDTO> srList = shopService.relationList();
		List<PosDTO> pList = posService.getPosList(session);
		List<ShopDTO> personnelList = shopService.personnelList(session);
		//System.out.println("relationList"+srList );
		//System.out.println("posList" + pList);
		//System.out.println("personnelList" + personnelList);
		//맵으로 서비스부분 받기
		Map<String, Object> returnMap = shopService.shopList(currentPage,session);
    	//Map객체주소로 보내는 경우(model.addAttribute("map", returnMap);
		//System.out.println(returnMap + " map 담긴 값 확인 ");
		//returnMap(Map타입 객체)에 담겨있는 값 -> model(Model타입 객체)에 복사 ->  view전달
		//매장 목록
    	model.addAttribute("shopList", returnMap.get("list"));
    	//현재 페이지
    	model.addAttribute("currentPage",returnMap.get("currentPage"));
    	//마지막페이지
    	model.addAttribute("lastPage",returnMap.get("lastPage"));	
    	//계약매장 목록
		model.addAttribute("relationList", srList);
		//pos 목록
		model.addAttribute("posList", pList);
		//매장인원 목록
		model.addAttribute("personnelList", personnelList);
		return "/shop/shop";
	}
	
	//매장계약코드 상세보기
	@GetMapping("/relationList")
	public String getRelationList(Model model) {
		//맵으로 받기
		Map<String, Object> map =  shopService.getRelationList();
		//SuppressWarnings("unchecked") 메소드상태가 경고 일 때 나오지 않게 해주기
		@SuppressWarnings("unchecked")
		List<ShopRelationDTO> relationYes = (List<ShopRelationDTO>)map.get("relationYes");
		@SuppressWarnings("unchecked")
		List<ShopRelationDTO> relationNo = (List<ShopRelationDTO>)map.get("relationNo");
		//System.out.println(relationYes + "<-- 삭제 할 수 있는 값");
		//System.out.println(relationNo + "<-- 삭제 할 수 없는 값");
		//입력값 확인 후 모델값 값을 넣는다.
		model.addAttribute("relationYes", relationYes);
		model.addAttribute("relationNo", relationNo);
		return "relation/relationList";
	}
	
	//매장계약코드 상세보기에서 검색시
	@PostMapping("/getRelationSearch")
	public String getRelationSearch(SearchDTO search, Model model) {
		System.out.println(search + "<-- 담겨있는값 ");
		Map<String,Object> map = shopService.getRelationSearch(search);
		// model에 값 넣기
		@SuppressWarnings("unchecked")
		List<ShopRelationDTO> relationSearchYes = (List<ShopRelationDTO>)map.get("relationSearchYes");
		@SuppressWarnings("unchecked")
		List<ShopRelationDTO> relationSearchNo = (List<ShopRelationDTO>)map.get("relationSearchNo");
		//System.out.println(relationSearchYes +"<--삭제가능리스트 확인");
		//System.out.println(relationSearchNo +"<--삭제불가능리스트 확인");
		//model에 대입값 넣기
		model.addAttribute("relationYes", relationSearchYes);
		model.addAttribute("relationNo", relationSearchNo);
		//조회 결과가 없으면 나오는 문장
		if(relationSearchYes.size()  == 0  && relationSearchNo.size() == 0) {
			model.addAttribute("alert", "검색 결과가 없습니다");
		}
		return "relation/relationList";
	}
	
	//매장계약코드 수정화면
	@GetMapping("/relationUpdate")
	public String relationUpdate(@RequestParam(value ="contractShopCode") String contractShopCode,Model model) {
		//System.out.println(contractShopCode + "<--넘어오는 계약매장코드값 확인");
		List<ShopRelationDTO> relationList = shopService.relationUpdate(contractShopCode);
		//System.out.println(contractShopCode + "<--계약매장코드로 조회하는 데이터 확인");
		//relationList에 담겨있는 값 모델에 담기
		
		List<MemberDTO> memberList = shopService.getMemberId();
		List<ShopDTO> shopList = shopService.shopListAll();
		//System.out.println(memberList + "<-- 점주 아이디 목록");
		//System.out.println(shopList + "<-- 계약코드목록");
		model.addAttribute("relationList", relationList);
		if(relationList != null) {
			model.addAttribute("relationId", relationList.get(0).getMemberId());
			model.addAttribute("relationShop", relationList.get(0).getShopCode());
			//System.out.println(relationList.get(0).getMemberId() + " <-- 아이디값");
			//System.out.println(relationList.get(0).getShopCode() + "<-- 매장코드값");
		}
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("shopList", shopList);
		return "relation/relationUpdate";
	}
	
	//계약코드 수정
	@PostMapping("/relationUpdate")
	public String relationUpdate(ShopRelationDTO relation) {
		//System.out.println(relation + "<-- 계약코드 데이터 수정된 값");
		shopService.relationUpdate(relation);
		return "redirect:/relationList";
	}
	

}
