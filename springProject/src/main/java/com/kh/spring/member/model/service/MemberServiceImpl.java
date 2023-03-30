package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

// @Component
@Service
public class MemberServiceImpl implements MemberService{

	// private MemberDao mDao = new MemberDao();
	@Autowired
	private MemberDao mDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession; // 스프링에서 다름
	
	@Override
	public Member loginMember(Member m) {
		// 매일 sqlSession 객체 만들고, MemberDao 생성해서 호출함
		// 이제는 의존성 주입으로 하겠음
		
		Member loginUser = mDao.loginMember(sqlSession, m);
		
		// close도 스프링이 알아서 해줌
		
		return loginUser;
	}

	@Override
	public int insertMember(Member m) {
		
		int result = mDao.insertMember(sqlSession, m);
		
		return result;
	}

	@Override
	public int updateMember(Member m) {
		int result = mDao.updateMember(sqlSession, m);
		
		return result;
	}

	@Override
	public int deleteMember(Member m) {
		return 0;
	}

	@Override
	public int idCheck(String checkId) {
		return 0;
	}

}
