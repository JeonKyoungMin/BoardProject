package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.ReplyDao;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public int countReply() {
		int result = replyDao.countReply();
		
		return result;
	}
	
}
