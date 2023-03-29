package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		/*
		@RequestMapping("login.me")
		public String loginMember(Member m) {
			// System.out.println("ID: " + m.getUserId());
			// System.out.println("PWD: " + m.getUserPwd());
			
			Member loginUser = mService.loginMember(m);
			
			if(loginUser == null) { // 로그인 실패 => requestScope에 담아서 errorPage 포워딩
				System.out.println("로그인 실패");
			} else { // 로그인 성공 => loginUser sessionScope에 담아서 메인페이지 url 재요청
				System.out.println("로그인 성공");
			}
			
			return "main"; => "WEB-INF/views/" + "main" + ".jsp"
		}
		*/
	
	
	/*
	 *  * 요청 처리 후 응답페이지로 포워딩 또는 url 재요청, 응답데이터 담는 방법
	 *  
	 *  1. 스프링에서 제공하는 Model 객체를 사용하는 방법
	 *  	포워딩할 뷰로 전달하고자 하는 데이터를 맵 형식(key-value)으로 담을 수 있는 영역
	 *  	Model 객체는 requestScope이다.
	 *  	단, setAttribute가 아닌 addAttribute 메소드 이용
	 
	@RequestMapping("login.me")
	public String loginMember(Member m, Model model, HttpSession session) {
		Member loginUser = mService.loginMember(m);
		
		if(loginUser == null) { 
			model.addAttribute("errorMsg", "로그인 실패");
			
			return "common/errorPage";
		} else { 
			session.setAttribute("loginUser", loginUser);
			
			return "redirect:/"; // 포워딩방식이 아닌 => 메인페이지로 재요청한다는 뜻 / redirect:/ => 메인화면으로 요청
		}
		
	}
	*/
	
	/*
	 * 2. 스프링에서 제공하는 ModelAndView 객체를 이용하는 방법
	 * 
	 * 		Model은 데이터를 key-value 세트로 담을 수 있는 공간이라고 한다면
	 * 		View는 응답뷰에 대한 정보를 담을 수 있는 공간
	 */
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
		Member loginUser = mService.loginMember(m);
		
		if(loginUser == null) { 
			mv.addObject("errorMsg", "로그인 실패");
			
			mv.setViewName("common/errorPage");
		} else { 
			session.setAttribute("loginUser", loginUser);
			
			mv.setViewName("redirect:/");
		}
		
		return mv;
	}
	
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("enrollForm.me")
	public String EnrollMemberForm() {
		return "member/memberEnrollForm";
	}
	
	@RequestMapping("insert.me")
	public void insertMember(Member m) {
		System.out.println(m);
		// 1. 한글 깨짐 => 스프링에서 제공하는 Encoding 필터 등록 => web.xml에 등록
		// 2. 나이를 입력하지 않았을 경우 "" 빈 문자열이 넘어오는 데 int 형 필드에 담을 수 없어서 400에러 발생
		// 	=> Member 클래스의 age 필드를 int 형 => String형으로 변경(오라클은 자동 형변환 되서 상관없음)
	}
}
