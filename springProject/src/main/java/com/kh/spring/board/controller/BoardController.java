package com.kh.spring.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.service.BoardServiceImpl;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {

	@Autowired
	private BoardServiceImpl bService;
	
	// 메뉴바 클릭시 			/list.bo		(기본적으로 1번 페이지 요청)
	// 페이징바 클릭시 			/list.bo?cpage=요청하는 페이지수
	
	/*
	 * @RequestMapping("list.bo") public String
	 * selectList(@RequestParam(value="cpage", defaultValue="1") int currentPage,
	 * Model model) { // System.out.println(currentPage);
	 * 
	 * int listCount = bService.selectListCount();
	 * 
	 * PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 10, 5);
	 * 
	 * ArrayList<Board> list = bService.selectList(pi);
	 * 
	 * model.addAttribute("pi", pi); model.addAttribute("list", list);
	 * 
	 * return "board/boardListView";
	 * 
	 * }
	 */
	
	@RequestMapping("list.bo")
	public ModelAndView selectList(@RequestParam(value="cpage", defaultValue="1") int currentPage, ModelAndView mv) {
		int listCount = bService.selectListCount();
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, 10, 5);
		
		ArrayList<Board> list = bService.selectList(pi);
		
		/*
		mv.addObject("pi", pi);
		mv.addObject("list", list);
		
		mv.setViewName("board/boardListView");
		*/
		
		// 메소드 체이닝
		mv.addObject("pi", pi).addObject("list", list).setViewName("board/boardListView");
		return mv;
	}
	
	@RequestMapping("enrollForm.bo")
	public String enrollForm() {
		// WEB-INF/views/	board/boardEnrollForm	.jsp
		return "board/boardEnrollForm";
	}
	
	@RequestMapping("insert.bo")
	public void insertBoard(Board b, MultipartFile upfile /* jsp에 있는 upfile 이름과 맞춰야함 */) {
		System.out.println(b);
		System.out.println(upfile);
		
		// 추가적인 라이브러리 필요
	}
}
