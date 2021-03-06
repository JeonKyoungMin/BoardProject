package com.board.service;

import java.util.List;

import com.board.beans.ReplyBean;


/**
 * @author km
 * @date 2021-08
 */
public interface ReplyService {

	/**
	 * <p>댓글 리스트 가져오기</p>
	 */
	public List<ReplyBean> selectReply(ReplyBean replyBean);
	
	/**
	 * <p>댓글 작성</p>
	 */
	public void writeReply(ReplyBean replyBean);
}
