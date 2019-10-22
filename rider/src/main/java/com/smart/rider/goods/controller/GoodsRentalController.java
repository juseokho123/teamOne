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

import com.smart.rider.goods.dto.GoodsDTO;
import com.smart.rider.goods.dto.GoodsHapDTO;
import com.smart.rider.goods.dto.GoodsRentalDTO;
import com.smart.rider.goods.service.GoodsRentalService;
import com.smart.rider.goods.service.GoodsService;
import com.smart.rider.member.dto.MemberDTO;

@Controller
public class GoodsRentalController {
	@Autowired
	private GoodsRentalService goodsRentalService;
	@Autowired
	private GoodsService goodsService;
	
	
	
	
	//대여품삭제요청
	@GetMapping("/goodsRentalDelete")
	public String goodsRentalDelete(@RequestParam(value="goodsRentalCode")String goodsRentalCode,Model model) {
		model.addAttribute("goodsRentalCode", goodsRentalService.getGoodsRentalList(goodsRentalCode));
		return "goods/goodsRentalDelete";
	}
	//대여품삭제처리
	@PostMapping("/goodsRentalDelete")
	public String goodsRentalDelete(GoodsRentalDTO goodsRentalDto,MemberDTO memberDto,Model model) {
		int result = goodsRentalService.goodsRentalDelete(goodsRentalDto.getGoodsRentalCode(),
														  memberDto.getMemberId(),
														  memberDto.getMemberPw());
		//System.out.println("############## memberDto goodsRentalDelete GoodsRentalController.java");
		//System.out.println(goodsRentalDto + "<- goodsRentalDto goodsRentalDelete GoodsRentalController.java");
		//System.out.println(memberDto + "<- memberDto goodsRentalDelete GoodsRentalController.java");
		//System.out.println("############## memberDto goodsRentalDelete GoodsRentalController.java");
		if(result == 0) {
			model.addAttribute("result", "비밀번호를 바르게입력하세요");
			model.addAttribute("goodsRentalCode",goodsRentalService.getGoodsRentalList(goodsRentalDto.getGoodsRentalCode()));
			return "goods/goodsRentalDelete";
		}
		return "redirect:goodsRentalList";	
	}
	//대여품 수정 요청
	@GetMapping("/goodsRentalUpdate")
	public String goodsRentalUpdate(@RequestParam(value="goodsRentalCode")String goodsRentalCode,Model model) {
		model.addAttribute("goodsRentalCode", goodsRentalService.getGoodsRentalList(goodsRentalCode));
		return "goods/goodsRentalUpdate";
	}
	//수정처리
	@PostMapping("/goodsRentalUpdate")
	public String goodsRentalUpdate(GoodsRentalDTO goodsRentalDto) {
		//System.out.println("대여상품수정값 가져오기 확인"+goodsRentalDto);
		goodsRentalService.goodsRentalUpdate(goodsRentalDto);
		return "redirect:goodsRentalList";
	}
	
	
	
	//상품상세보기요청
	@GetMapping("/getGoodsRentalList")
	public String getGoodsRentalList(@RequestParam("goodsRentalCode")String goodsRentalCode,Model model) {
		model.addAttribute("goodsRentalCode", goodsRentalService.getGoodsRentalList(goodsRentalCode));
		return "goods/getGoodsRentalList";			
	}
	//대여상품 등록요청
	@GetMapping("/goodsRentalInsert")
	public String goodsRentalInsert(@RequestParam(value="goodsCode")String goodsCode,Model model) {
		//System.out.println("판매상품코드값 대여로넘어오는지확인"+goodsCode);
		model.addAttribute("goodsCode", goodsService.getGoodsList(goodsCode));
		return "goods/goodsRentalInsert";
	}
	//대여상품등록 처리 insert
	@PostMapping("/goodsRentalInsert")
	public String goodsRentalInsert(GoodsRentalDTO goodsRentalDto,GoodsDTO goodsDto,HttpSession session,Model model) {
		//System.out.println("대여상품 입력확인"+goodsRentalDto);
		String contractShopCode = (String)session.getAttribute("SCODE");
		//System.out.println("매장코드 확인"+contractShopCode);
		goodsRentalDto.setContractShopCode(contractShopCode);
		goodsRentalService.goodsRentalInsert(goodsRentalDto);
		//goodsService.goodsUpdate(goodsDto);
		return "redirect:goodsRentalList";
	}
	
	
	@GetMapping("/goodsRentalList")	
	//대여상품 리스트조회
	public String rentalList(Model model,HttpSession session) {
		String select = null;
		String searchInput = "";
		String beginDate = "";
		String endDate = "";
		String SCODE = (String)session.getAttribute("SCODE");
		String SLEVEL = (String)session.getAttribute("SLEVEL");
		
		Map<String,Object> map = goodsRentalService.goodsRentalList(select, searchInput, beginDate, endDate, SCODE, SLEVEL);
		@SuppressWarnings("unchecked")
		List<GoodsHapDTO> rList = (List<GoodsHapDTO>)map.get("rList");
		//System.out.println("대여상품리스트 값 넘어오는지확
		model.addAttribute("rList", rList);
		
		return "/goods/goodsRentalList";
	}
	//대여상품 검색
	@PostMapping("/goodsRentalSearchList")
	public String goodsRentalSearchList(@RequestParam(value="select")String select
										,@RequestParam(value="searchInput")String searchInput
										,@RequestParam(value="beginDate")String beginDate
										,@RequestParam(value="endDate")String endDate
										,Model model,HttpSession session) {
		
		String SCODE = (String)session.getAttribute("SCODE");
		
		//System.out.println("대여상품 앞날짜검색"+beginDate);
		//System.out.println("대여상품 뒤날짜검색"+endDate);
		//System.out.println("대여상품 카테고리"+select);
		//System.out.println("대여상품 카테고리명"+searchInput);
		List<GoodsRentalDTO> rList = goodsRentalService.goodsRentalSearchList(select, searchInput, beginDate, endDate, SCODE);
		model.addAttribute("rList", rList);
		if(rList.size()==0) {
			model.addAttribute("alert", "검색 결과가 없습니다");
		}		
		return "goods/goodsRentalList";
	}
}
