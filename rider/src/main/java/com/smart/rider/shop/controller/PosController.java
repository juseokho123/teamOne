package com.smart.rider.shop.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.rider.shop.dto.PosDTO;
import com.smart.rider.shop.dto.ShopDTO;
import com.smart.rider.shop.dto.ShopRelationDTO;
import com.smart.rider.shop.dto.SsrHapDTO;
import com.smart.rider.shop.mapper.ShopMapper;
import com.smart.rider.shop.service.PosService;

@Controller
public class PosController {

	@Autowired
	private PosService posService;
	@Autowired
	private ShopMapper shopMapper;
	
	//생성 폼가기
	@GetMapping("/posInsert")
	public String posInsert(HttpSession session,Model model) {
		//현재 로그인 된 아이디 값 받기
		String memberId = (String)session.getAttribute("SID");
		//System.out.println(memberId+"<--담겨있는 세션값 확인");
		//현재 로그인 된 아이디 대입 결과 확인
		List<SsrHapDTO> ssrList = posService.getMemberId(memberId);
		//System.out.println(ssrList+"<--memberId 대입 결과");
		//세션에 담기
		model.addAttribute("ssrHapList", ssrList);
		return "pos/posInsert";
	}
	
	//생성하기
	@PostMapping("/posInsert")
	public String posInsert(PosDTO pos) {
		//입력값 확인
		//System.out.println(pos+"<--받아온 pos 값");
		posService.posInsert(pos);
		return "redirect:/shop";
	}
	
	//상세보기
	@GetMapping("/posList")
	public String getPosList(Model model,HttpSession session) {
		//PosDTO에 결과 담기
		List<PosDTO> posList = posService.getPosList(session); 
		System.out.println(posList + "posList 입력값 확인");
		//PosDTO값을 model에 담아 넘기기
		model.addAttribute("posList", posList);
		return "pos/posList";
	}
	
	//수정하기 위해 코드값으로 데이터 조회
	@GetMapping("/posUpdate")
	public String posUpdate(@RequestParam(value="posCode")String posCode,Model model) {
		//System.out.println(posCode + "코드 확인");
		List<PosDTO> posList = posService.posUpdate(posCode);
		model.addAttribute("posList", posList);
		//System.out.println(posList + "결과값 확인");
		if(posList !=null) {
			String csCode= posList.get(0).getContractShopCode();
			//System.out.println(csCode + "담겨있는 계약매장코드값 확인");
			model.addAttribute("shopName", "해당되는 매장 이름이 없습니다.");
			if(csCode != null) {
				List<ShopRelationDTO> relation = shopMapper.relationUpdate(csCode);
				//System.out.println(relation + "계약매장코드 값 확인");
				String shopCode = relation.get(0).getShopCode();
				List<ShopDTO> shopList = shopMapper.getScode(shopCode);
				//System.out.println(shopList + "매장목록 값 확인");
				String memberId = shopList.get(0).getMemberId();
				List<SsrHapDTO> ssrList = posService.getMemberId(memberId);
				model.addAttribute("shopName", ssrList.get(0).getShopName());
			}	
		}
		
		//List<SsrHapDTO> ssrList = posService.getMemberId();
		
		
		return "pos/posUpdate";
	}
	
	//수정하기
	@PostMapping("/posUpdate")
	public String posUpdateSet(PosDTO pos) {
		//System.out.println(pos + "수정값 확인");
		posService.posUpdateSet(pos);
		return "redirect:/shop";
	}
	
	//수정하기 위해 코드값으로 데이터 조회
	@GetMapping("/posDelete")
	public String posDelete(@RequestParam(value="posCode")String posCode,Model model,HttpSession session) {
		//코드 값 확인
		//System.out.println(posCode+"<-- pos코드값 확인");
		//코드 대입한 결과값 확인
		String memberId = (String)session.getAttribute("SID");
		List<SsrHapDTO> ssrList = posService.getMemberId(memberId);
		List<PosDTO> posDelete = posService.posUpdate(posCode);
		//System.out.println(ssrList + "<-- ssrList 값 확인");
		//System.out.println(posDelete + "<-- PosDTO 값 확인");
		model.addAttribute("ssrList", ssrList);
		model.addAttribute("posDelete", posDelete);
		return "pos/posDelete";
	}
	
	//삭제하기
	@PostMapping("/posDelete")
	public String posDeleteSet(	@RequestParam(value="memberPw")String memberPw,
								@RequestParam(value="posCode")String posCode,
								HttpSession session,
								Model model){
		//삭제할 Code값 과 입력된 PW값 확인
		//System.out.println(memberPw +"<--memberPw에 입력된 값");
		//System.out.println(posCode +"<--posCode에 입력된 값");
		//삭제 확인을 위해서 변수를 선언한다.
		int deleteCk = posService.posDeleteSet(memberPw,posCode,session);
		System.out.println(deleteCk +"<--값 확인");
		//deleteCk가 0이면  삭제가 안되므로 다시 값을 가지고 삭제화면으로 리턴 시킨다.
		if(deleteCk == 0 ) { 
			model.addAttribute("result", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("posDelete", posService.posUpdate(posCode)); 
			return "pos/posDelete"; 
		}
		 
		return "redirect:/shop";
	}
}
