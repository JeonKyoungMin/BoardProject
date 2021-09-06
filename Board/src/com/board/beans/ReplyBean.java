package com.board.beans;

import lombok.Data;

@Data
public class ReplyBean {

	public int replyBoardIdx;
	public int replyContentIdx;
	public int replyIdx;
	public String replyContent;
	public String replyWriter;
	public String replyYmd;
}
