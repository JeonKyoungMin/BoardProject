package com.board.service;

import java.util.List;

import com.board.beans.ReplyBean;

public interface ReplyService {

	public List<ReplyBean> selectReply(ReplyBean replyBean);
	
	public void writeReply(ReplyBean replyBean);
}
