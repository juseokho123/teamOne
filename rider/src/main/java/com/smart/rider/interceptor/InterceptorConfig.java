package com.smart.rider.interceptor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Qualifier; 
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.web.servlet.HandlerInterceptor; 
import org.springframework.web.servlet.config.annotation.InterceptorRegistry; 
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	@Qualifier(value = "loginInterceptor") 
	private HandlerInterceptor loginInterceptor;

	@Value("${resources.notload.list}") 
	private List<String> notLoadList;

	@Override 
	public void addInterceptors(InterceptorRegistry registry) {

		notLoadList.add("/login"); 
		notLoadList.add("/");
		notLoadList.add("/loginProcess");
		notLoadList.add("/memberInsert");
		notLoadList.add("/loginCheck");
		notLoadList.add("/memberIdCheck");

		
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(notLoadList);
	}
}
