package com.smart.rider.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Component; 
import org.springframework.web.servlet.HandlerInterceptor;

@Component 
public class LoginInterceptor implements HandlerInterceptor{

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		logger.info("LoginInterceptor - {}", "호출완료");

		HttpSession session = request.getSession(); String SID = (String)session.getAttribute("SID"); 
		//System.out.println(SID + " <-- SID session check LoginInterceptor.java");

		if(SID == null) {
			try {
				response.sendRedirect("/login"); 
				return false; 
			} catch (IOException e) {
			} 
		} return true;
		}
}
