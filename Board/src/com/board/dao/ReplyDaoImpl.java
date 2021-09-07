package com.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.beans.ReplyBean;

@Repository
public class ReplyDaoImpl implements ReplyDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<ReplyBean> selectReply(ReplyBean replyBean) {
		List<ReplyBean> result = sqlSessionTemplate.selectList("reply.selectReply",replyBean);
		
		return result;
	}
	
	@Override
	public void writeReply(ReplyBean replyBean) {
		sqlSessionTemplate.insert("reply.writeReply", replyBean);
	}
}
