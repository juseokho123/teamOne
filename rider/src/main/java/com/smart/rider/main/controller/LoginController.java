package com.smart.rider.main.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.rider.main.service.LoginService;
import com.smart.rider.member.dto.MemberDTO;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	/**** 191007 재욱, Read 로그인 ajax ****/
	@RequestMapping("/loginCheck")
	public @ResponseBody int loginCheck(String memberId, String memberPw) {
		int result = loginService.loginCount(memberId, memberPw);
		//System.out.println(result + " <-- result loginCheck() LoginController.java");
		return result;
	}

	//로그인 화면 이동
	@GetMapping("/login")
	public String login() {
		return "/login/login";
	}
	

	//로그인 프로세스 
	@PostMapping("/loginProcess")
	public String loginProcess(@RequestParam(value = "memberId") String memberId
							 , @RequestParam(value = "memberPw") String memberPw
							 , HttpSession session, Model model) {
		
		//System.out.println(memberId + " <-- memberId loginProcess LoginController.java");
		//System.out.println(memberPw + " <-- memberPw loginProcess LoginController.java");
		
		Map<String, Object> map = loginService.loginCheck(memberId, memberPw);
		MemberDTO loginCheck = (MemberDTO)map.get("loginCheck");
		
		//로그인
		if(loginCheck != null) {
			//System.out.println(loginCheck + " <-- loginCheck loginProcess LoginController.java");
			session.setAttribute("SID", loginCheck.getMemberId());
			session.setAttribute("SNAME", loginCheck.getMemberName());
			session.setAttribute("SLEVEL", loginCheck.getMemberLevel());
			
			if(loginCheck.getContractShopCode() == null) {
				session.setAttribute("SCODE", "SR0000");
				//System.out.println(session.getAttribute("SCODE") + " <-- SCODE loginProcess LoginController.java");
			} else {
				session.setAttribute("SCODE", loginCheck.getContractShopCode());
			}
			return "redirect:/";
		} else {
			//System.out.println(loginCheck + " <-- loginCheck loginProcess LoginController.java");
			return "redirect:/login";
		}
	}
	
	//로그아웃 프로세스
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
