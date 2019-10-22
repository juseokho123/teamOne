package com.smart.rider.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.rider.member.dto.MemberDTO;
import com.smart.rider.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	//회원 등록화면
	@GetMapping("/memberInsert")
	public String memberInsert() {
		
		return "member/memberInsert";
	}
	
	//회원 등록처리
	@PostMapping("/memberInsert")
	public String memberInsert(MemberDTO memberdto) {
		memberService.memberInsert(memberdto);
		//System.out.println("회원등록컨트롤러");
		return "redirect:/login";
	}
	
	//회원 목록가져오기
	@GetMapping("/memberList")
	public String memberList(@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage
						    ,Model model) {
		//계약된 매장들가져오기
		model.addAttribute("utilityShop", memberService.utilityShop());
		Map<String, Object> map = memberService.memberList(currentPage);
		
		model.addAttribute("memberList"	 ,map.get("list"));
		model.addAttribute("currentPage" ,map.get("currentPage"));
		model.addAttribute("lastPage"	 ,map.get("lastPage"));
		model.addAttribute("startPageNum",map.get("startPageNum"));
		model.addAttribute("lastPageNum" ,map.get("lastPageNum"));
		
		return "member/memberList";
	}
	
	//회원 상세보기
	@GetMapping("/getMemberList")
	public String getMemberList(@RequestParam(value="memberId") String memberId, Model model) {
		//System.out.println(memberId + "<--상세보기id");
		model.addAttribute("memberList", memberService.getMemberList(memberId));

		return "member/memberUpdate";
	}
	
	//19.09.17작성 회원수정
	@PostMapping("/memberUpdate")
	public String memberUpdate(MemberDTO memberdto) {
		memberService.memberUpdate(memberdto);
		
		return "redirect:/memberList";
	}
	
	//회원 검색하기
	@PostMapping("/searchMember")
	public String searchMember(@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage
							  ,@RequestParam(value="select") String select
							  ,@RequestParam(value="searchInput") String searchInput
							  ,@RequestParam(value="beginDate") String beginDate
							  ,@RequestParam(value="endDate") String endDate
							  ,@RequestParam(value="shopCode") String shopCode
							  ,Model model) {
		//System.out.println(shopCode +"<---검색매장");
		//계약된 매장가져오기
		model.addAttribute("utilityShop", memberService.utilityShop());
		
		Map<String, Object> map = memberService.searchMember(currentPage, select, searchInput, beginDate, endDate, shopCode);
		model.addAttribute("memberList"	 ,map.get("list"));
		model.addAttribute("currentPage" ,map.get("currentPage"));
		model.addAttribute("lastPage"	 ,map.get("lastPage"));
		model.addAttribute("startPageNum",map.get("startPageNum"));
		model.addAttribute("lastPageNum" ,map.get("lastPageNum"));
		
		return "member/memberList";
	}
	
	//19.09.18작성
	//비밀번호 수정화면 팝업으로 띄우기
	@GetMapping("/changePassword")
	public String memberPassword(@RequestParam(value="memberId") String memberId, Model model) {
		model.addAttribute("memberId", memberId);
		
		return "member/memberPassword";
	}
	
	//19.09.20작성 회원 탈퇴 화면
	@GetMapping("/memberDelete")
	public String memberDelete(@RequestParam(value="memberId") String memberId, Model model) {
		//System.out.println(memberId + "<--딜리트 아이디");
		model.addAttribute("memberId", memberId);
		
		return "/member/memberDelete";
	}
	
	//19.09.23 작성 회원 탈퇴처리
	@PostMapping("/memberDelete")
	public String memberDelete(MemberDTO memberdto, Model model) {
		int result = memberService.memberDelete(memberdto.getMemberId(), memberdto.getMemberPw());
		if(result == 0) {
			model.addAttribute("result", "비밀번호가 일치하지 않습니다!");
			model.addAttribute("memberId", memberdto.getMemberId());
			return "member/memberDelete";
		}
		
		return "redirect:/memberList";
	}
	
	//관리자일때 목록에서 삭제
	@GetMapping("/levelDelete")
	public String levelDelete(@RequestParam(value="memberId") String memberId, Model model) {
		//System.out.println(memberId + "<--바로 삭제할 아이디");
		model.addAttribute("deleteMember", memberService.levelDelete(memberId));
		
		return "redirect:/memberList";
	}
	
	//19.09.25작성
	//팝업창 완료 메시지
	@GetMapping("/memberSuccess")
	public String memberSuccess() {
		
		return "member/memberSuccess";
	}
	
	//상세보기에서 비밀번호 수정처리
	@PostMapping("/updatePassword")
	public String changePassword(@RequestParam(value="memberPw2") String memberPw2, MemberDTO memberdto, Model model) {
		int result = memberService.changePassword(memberdto.getMemberId(), memberdto.getMemberPw(), memberPw2);
		if(result == 0) {
			model.addAttribute("result", "비밀번호가 일치하지 않습니다!");
			model.addAttribute("memberId", memberdto.getMemberId());
			return "member/memberPassword";
		}
		
		return "redirect:/memberSuccess";
	}
	
	//아이디중복확인
	@RequestMapping(value = "/memberIdCheck", produces = "text/plain")
	public @ResponseBody String memberIdCheck(@RequestParam(value="memberId") String memberId) {
		//System.out.println(memberId + "<--중복확인 아이디");
		int result = memberService.memberIdCheck(memberId);
		if(result == 1) {
			return "no";
		}
		
		return "yes";
	}
}
