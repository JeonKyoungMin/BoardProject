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
	
	/**
	 * <pre>
	 * 처리내용 : 댓글 리스트 가져오기
	 */
	@Override
	public List<ReplyBean> selectReply(ReplyBean replyBean) {
		List<ReplyBean> result = sqlSessionTemplate.selectList("reply.selectReply",replyBean);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 댓글 작성
	 */
	@Override
	public void writeReply(ReplyBean replyBean) {
		sqlSessionTemplate.insert("reply.writeReply", replyBean);
	}
}
