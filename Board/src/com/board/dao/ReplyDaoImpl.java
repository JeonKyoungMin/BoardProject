package com.board.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDaoImpl implements ReplyDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int countReply() {
		int result = sqlSessionTemplate.selectOne("reply.countReply");
		
		return result;
	}
}
