package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Controller 타입의 어노테이션을 붙여주면 빈 스캐닝을 통해 자동으로 빈 등록
public class MemberController {
	
	/*
	@RequestMapping(value="login.me") // RequestMapping 타입의 어노테이션을 붙여줌으로써 HandlerMapping 등록
	public void loginMember() {
		
	}
	
	*/
	
	/*
	 * * 파라미터(요청 시 전달값)를 받는 방법 (요청 시 전달값들 처리 방법)
	 * 	1. HttpServletRequest를 이용해서 전달받기(기존 jsp/servlet 방식)
	 * 		해당 메소드의 매개변수 HttpServletRequest를 작성해두면
	 * 		스프링 컨테이너가 해당 메소드 호출 시(실행 시) 자동으로 해당 객체를 생성해서 인자로 주입해줌 
	 */
	/*
	@RequestMapping("login.me") // 속성하나만 쓸 때
	public String loginMember(HttpServletRequest request) {
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		
		System.out.println("ID: " + userId);
		System.out.println("PWD: " + userPwd);
		
		return "main";
	}
	*/
	
	/*
	 * 2. @RequestParam 어노테이션을 이용하는 방법
	 * 		request.getParameter("키"): 벨류의 역할을 대신해주는 어노테이션
	 */
	@RequestMapping("login.me")
	public String loginMember(@RequestParam(value = "id") String userId,
							  @RequestParam(value = "pwd") String userPwd) {
		System.out.println("ID: " + userId);
		System.out.println("PWD: " + userPwd);
		
		return "main";
	}
	
}
