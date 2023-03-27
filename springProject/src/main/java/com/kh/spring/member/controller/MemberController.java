package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.service.MemberServiceImpl;
import com.kh.spring.member.model.vo.Member;

@Controller // Controller 타입의 어노테이션을 붙여주면 빈 스캐닝을 통해 자동으로 빈 등록
public class MemberController {
	// private MemberServiceImpl mService = new MemberServiceImpl();
	// 이렇게 쓰면 결합도가 높아짐(높으면 안좋음) 만약 개발 이후에 이름이 변경된다고 하면 전부 다 바꿔야함 => 번거로움
	
	@Autowired // DI(Dependency Injection) 특징 = 의존성 주입
	// private MemberService mService; // 권장
	private MemberServiceImpl mService; // 권장
	
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
	 
		@RequestMapping("login.me")
		public String loginMember(@RequestParam(value = "id", defaultValue="aaa") String userId,
								  @RequestParam("pwd") String userPwd) {
			System.out.println("ID: " + userId);
			System.out.println("PWD: " + userPwd);
			
			return "main";
		}
	 */
	
	/*
	 * 3. @RequestParam 어노테이션을 생략하는 방법
	 * 		** 단, 매개변수명 name값(요청 시 전달값의 키값)과 동일하게 세팅해둬야 자동으로 값이 주입됨
	 
		@RequestMapping("login.me")
		public String loginMember(String id, String pwd) {
			System.out.println("ID: " + id);
			System.out.println("PWD: " + pwd);
			
			Member m = new Member();
			m.setUserId(id);
			m.setUserPwd(pwd);
			
			// Service쪽 메소드에 m을 전달하며 조회
			
			return "main";
		}
	   */
	
		/*
		 *  4. 커맨드 객체 방식
		 *  	해당 메소드의 매개변수로
		 *  	요청 시 전달값을 담고자 하는 vo클래스 타입을 세팅 후
		 *  	요청 시 전달값의 키 값(name값)을 vo클래스에 담고자하는 **필드명으로 작성
		 *  
		 *  	스프링 컨테이너가 해당 객체를 기본 생성자로 생성 후 setter 메소드 찾아서 => 기본생성자와 getter/setter 메소드를 무조건 생성해야한다는 말
		 *  	요청 시 전달값을 해당 필드에 담아주는 내부적인 원리
		 */
	
		@RequestMapping("login.me")
		public String loginMember(Member m) {
			// System.out.println("ID: " + m.getUserId());
			// System.out.println("PWD: " + m.getUserPwd());
			
			Member loginUser = mService.loginMember(m);
			
			return "main";
		}
}
