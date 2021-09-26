package com.board.dao;

import java.util.List;

import com.board.beans.ReplyBean;

/**
 * @author km
 * @date 2021-08
 */
public interface ReplyDao {

	/**
	 * <pre>
	 * 처리내용 : 댓글 리스트 가져오기
	 */
	public List<ReplyBean> selectReply(ReplyBean replyBean);
	
	/**
	 * <pre>
	 * 처리내용 : 댓글 작성
	 */
	public void writeReply(ReplyBean replyBean);
}
