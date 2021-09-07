package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.beans.ReplyBean;
import com.board.dao.ReplyDao;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public List<ReplyBean> selectReply(ReplyBean replyBean) {
		List<ReplyBean> result = replyDao.selectReply(replyBean);
		
		return result;
	}
	
	@Override
	public void writeReply(ReplyBean replyBean) {
		replyDao.writeReply(replyBean);
	}
}
