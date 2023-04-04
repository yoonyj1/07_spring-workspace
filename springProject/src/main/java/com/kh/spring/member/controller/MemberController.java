package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
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
		/* 암호화 전 로그인 메소드
		Member loginUser = mService.loginMember(m);
		
		if(loginUser == null) { 
			mv.addObject("errorMsg", "로그인 실패");
			
			mv.setViewName("common/errorPage");
		} else { 
			session.setAttribute("loginUser", loginUser);
			
			mv.setViewName("redirect:/");
		}
		
		return mv;
		*/
		// 암호화 작업 후에 해야하는 과정
		// Member m userId: 사용자가 입력한 아이디
		// 			userPwd: 사용자가 입력한 비밀번호(평문)
		Member loginUser = mService.loginMember(m);
		// loginUser: 오로지 아이디만을 가지고 조회한 회원
		// loginUser userPwd: db에 기록된 비밀번호 (암호문)
		
		if(loginUser != null && bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			// 로그인 성공
			session.setAttribute("loginUser", loginUser);
			
			mv.setViewName("redirect:/");
		} else { // 로그인 실패
			mv.addObject("errorMsg", "로그인 실패");
			
			mv.setViewName("common/errorPage");
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
	public String insertMember(Member m, Model model, HttpSession session) {
		// System.out.println(m);
		// 1. 한글 깨짐 => 스프링에서 제공하는 Encoding 필터 등록 => web.xml에 등록
		// 2. 나이를 입력하지 않았을 경우 "" 빈 문자열이 넘어오는 데 int 형 필드에 담을 수 없어서 400에러 발생
		// 	=> Member 클래스의 age 필드를 int 형 => String형으로 변경(오라클은 자동 형변환 되서 상관없음)
		// 3. 비밀번호가 사용자가 입력한 그대로의 평문
		// 	=> Bcrypt 방식의 암호화 통해서 암호문으로 변경
		//		1) 스프링 시큐리티 모듈에서 제공하고 있음(라이브러리 추가 pom.xml)
		//		2) BcryptPassWordEncoder 라는 클래스를 빈으로 등록(spring-security.xml 파일에)
		//		3) web.xml에 spring-security.xml 파일을 pre-loading할 수 있도록 작성
		// System.out.println("평문: " + m.getUserPwd());
		
		// 암호화 작업(암호문을 만들어내는 과정)
		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		// System.out.println(encPwd);
		m.setUserPwd(encPwd); // Member 객체에 userPwd에 평문이 아닌 암호문으로 변경
		
		int result = mService.insertMember(m);
		
		if(result > 0) { // 성공 => 메인페이지 url 재요청! 알림창
			session.setAttribute("alertMsg", "회원가입 성공");
			
			return "redirect:/";
		} else { // 실패 => 에러문구 담아서 에러페이지 포워딩
			model.addAttribute("errorMsg", "회원가입 실패");
			
			return "common/errorPage";
		}
	}
	
	@RequestMapping("mypage.me")
	public String myPage() {
		return "member/myPage";
	}
	
	@RequestMapping("update.me")
	public String updateMember(Member m, HttpSession session, Model model) {
		int result = mService.updateMember(m);
		
		if(result > 0) { // 수정 성공
			// DB로부터 수정된 회원 정보를 다시 조회해와서
			// session loginUser 키값으로 덮어씌워야함
			
			// Member updateMem = mService.loginMember(m);
			// session.setAttribute("loginUser", updateMem);
			// 위의 두 줄을 한 줄로
			session.setAttribute("loginUser", mService.loginMember(m));
			
			// alertMsg에 담기
			session.setAttribute("alertMsg", "회원정보 수정 완료");
			
			// 마이페이지에 url 재요청
			return "redirect:mypage.me";
		} else { // 수정 실패
			model.addAttribute("errorMsg", "회원정보 수정 실패");
			
			return "common/errorPage";
		}
	}
	
	@RequestMapping("delete.me")
	public String deleteMember(String userPwd, String userId, HttpSession session, Model model) {
		// userPwd: 회원탈퇴 요청 시 사용자가 입력한 비밀번호 평문
		// session에 loginUser Member 객체 userPwd 필드: db로부터 조회 된 비번(암호문)
		
		String encPwd = ((Member)session.getAttribute("loginUser")).getUserPwd();
		
		// System.out.println("평문 비번: " + userPwd);
		// System.out.println("암호문 비번: " + encPwd);
		
		if(bcryptPasswordEncoder.matches(userPwd, encPwd)) {
			// 비번 맞음 => 탈퇴 처리
			int result = mService.deleteMember(userId);
			
			if(result > 0) { // 탈퇴처리 성공 => session loginUser 지움, alert 메시지 담아서 메인페이지 url 재요청
				session.removeAttribute("loginUser");
				
				session.setAttribute("alertMsg", "회원탈퇴가 되었습니다. 그동안 이용해주셔서 감사합니다");
				
				return "redirect:/";
			} else {  // 탈퇴처리 실패 => 에러문구 담아서 에러페이지 포워딩
				model.addAttribute("errorMsg", "회원탈퇴 실패");
				
				return "common/errorPage";
			}
			
		} else { // 비밀번호가 틀린 경우 => 비밀번호 틀린 것 알려주고 마이페이지 보여지게
			session.setAttribute("alertMsg", "비밀번호를 잘못 입력하셨습니다.");
			
			return "redirect:mypage.me";
		}
		
	}
	
	@RequestMapping("idCheck.me")
	@ResponseBody
	public String idCheck(String checkId) {
		int count = mService.idCheck(checkId);
		
		/*
		 * if(count > 0) { // 사용 불가능 한(이미 존재하는) 아이디 => (NNNNN) return "NNNNN"; } else {
		 * // 사용가능 한 아이디 => (NNNNY) return "NNNNY"; }
		 */
		return count > 0? "NNNNN" : "NNNNY";
	}
}
