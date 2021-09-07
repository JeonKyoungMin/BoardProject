package com.board.dao;

import java.util.List;

import com.board.beans.ReplyBean;

public interface ReplyDao {

	public List<ReplyBean> selectReply(ReplyBean replyBean);
	
	public void writeReply(ReplyBean replyBean);
}
