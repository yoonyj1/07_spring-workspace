package com.kh.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.Member;

@Controller
public class AjaxController {

	/*
	 * 1. HTTPServletResponse 객체로 데이터 응답하기
	 */
	/*
	 * @RequestMapping("ajax1.do") public void ajaxMethod1(String name, int age,
	 * HttpServletResponse response) throws IOException { System.out.println(name);
	 * System.out.println(age);
	 * 
	 * // 요청처리를 위해 서비스 호출
	 * 
	 * // 요청처리가 다 됐다는 가정하에 요청한 페이지에 응답할 데이터가 있을 경우 String responseData = "응답 문자열: "
	 * + name + "은(는)" + age + "살 입니다.";
	 * 
	 * response.setContentType("text/html; charset=UTF-8");
	 * response.getWriter().print(responseData); // 예외 던지면 스프링이 알아서 예외처리해줌 }
	 */
	
	/*
	 * 2. 응답할 데이터를 문자열로 리턴
	 * 	=>HTTPServletResponse 안 쓸 수 있음
	 * 단, 문자열 리턴하면 원래는 포워딩 방식이였음 => 응답뷰로 인식해서 해당 뷰 페이지를 찾음
	 * 따라서 내가 리턴하는 문자열이 응답뷰가 아닌 응답데이터라는 것을 선언하는 @ResponseBody를 붙여야됨
	 * 인코딩 필터는 동기식 방식에 해당하는 것이라서 이거랑은 상관없음
	 */
	
	/*
	@ResponseBody
	// @RequestMapping("ajax1.do")
	// @RequestMapping(value="ajax1.do") // 이게 정석이지만 value가 하나면 value= 생략가능
	@RequestMapping(value="ajax1.do", produces= "text/html; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		String responseData = "응답 문자열: " + name + "은(는)" + age + "살 입니다.";
		
		return responseData;
	}
	*/
	
	/*
	// 다수의 응답데이터가 있을 경우
	@RequestMapping("ajax1.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) throws IOException {
		// 요청 처리가 다 됐다는 가정 하에 데이터 응답
		/* response.setContentType("text/html; charset=utf-8"); */
		
		/*
		 * response.getWriter().print(name); 
		 * response.getWriter().print(age);
		 */
		
		// 출력하는 데이터가 하나의 문자열로 연이어져 있음
		
		// JSON(JavaScript Object Notation) 형태로 담아서 응답
		// JSONArray => [값, 값, 값, ...] => Java의 ArrayList와 유사
		// JSONObject => {키:값, 키:값, 키:값..} => Java에서의 HashMap과 유사
		
		// 1) JSONArray로 담아서 응답
		/*
			JSONArray jArr = new JSONArray(); // []
			jArr.add(name); // ["윤여진"]
			jArr.add(age); // ["윤여진", age]
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jArr);
		
		
		// 2) JSONObject로 담아서 응답
		JSONObject jObj = new JSONObject(); // {}
		jObj.put("name", name); // {name:"윤여진"}
		jObj.put("age", age); // {age:20, name:"윤여진"} => 순서대로 안담겨서 랜덤으로 담김
		
		response.setContentType("application/json; charset=utf-8");
		
		response.getWriter().print(jObj);
	}
	*/
	
	@ResponseBody
	@RequestMapping(value="ajax1.do", produces="application/json; charset=utf-8")
	public String ajaxMethod1(String name, int age) {
		JSONObject jObj = new JSONObject();
		jObj.put("name", name); // {name:'윤여진'}
		jObj.put("age", age); // {age:29, name:'윤여진'}
		
		return jObj.toJSONString(); // "{age:12, name:"윤여진"}"
	}
	
	/*
	@ResponseBody
	@RequestMapping(value="ajax2.do", produces="application/json; charset=utf-8")
	public String ajaxMethod2(int num) {
		// Member m = mService.selectMember(num);
		
		Member m = new Member("user01", "pass01", "차은우", 20, "01012345679");
		
		// JSON 형태로 만들어서 응답
		// JSONObject
		JSONObject jObj = new JSONObject();
		jObj.put("userId", m.getUserId());
		jObj.put("userName", m.getUserName());
		jObj.put("age", m.getAge());
		jObj.put("phone", m.getPhone());
		
		return jObj.toJSONString();
		
	}
	*/
	
	@ResponseBody
	@RequestMapping(value="ajax2.do", produces="application/json; charset=utf-8")
	public String ajaxMethod2(int num) {
		Member m = new Member("user01", "pass01", "차은우", 20, "01012345679");

		return new Gson().toJson(m); // json 형태로 만들어서 문자열로 리턴해주며 멤버객체의 필드명으로 키값이 잡힘
	}
	
	@ResponseBody
	@RequestMapping(value="ajax3.do", produces="application/json; charset=utf-8")
	public String ajaxMethod3() {
		// ArrayList<Member> list = mService.selectMember();
		
		ArrayList<Member> list = new ArrayList<Member>();
		list.add(new Member("user01", "pass01", "차은우", 20, "01012345679"));
		list.add(new Member("user02", "pass02", "가나다", 30, "01032545679"));
		list.add(new Member("user03", "pass03", "라마바", 120, "01056345679"));
		
		return new Gson().toJson(list);
	}
}
