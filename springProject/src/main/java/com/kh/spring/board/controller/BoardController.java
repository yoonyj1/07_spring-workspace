package com.kh.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.board.model.service.BoardServiceImpl;

@Controller
public class BoardController {

	@Autowired
	private BoardServiceImpl bService;
	
	// 메뉴바 클릭시 			/list.bo		(기본적으로 1번 페이지 요청)
	// 페이징바 클릭시 			/list.bo?cpage=요청하는 페이지수
	
	@RequestMapping("list.bo")
	public void selectList(@RequestParam(value="cpage", defaultValue="1") int currentPage) {
		System.out.println(currentPage);
	}
}
