package com.kh.spring.board.model.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService{

	@Override
	public int selectListCount() {
		return 0;
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		return null;
	}

	@Override
	public int insertBoard(Board b) {
		return 0;
	}

	@Override
	public int increaseCount(int boardNo) {
		return 0;
	}

	@Override
	public Board selectBoard(int boardNo) {
		return null;
	}

	@Override
	public int deleteBoard(int boardNo) {
		return 0;
	}

	@Override
	public int updateBoard(int boardNo, Board b) {
		return 0;
	}

	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {
		return null;
	}

	@Override
	public int insertReply(Reply r) {
		return 0;
	}

}
