package com.board.beans;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ReplyBean {

	public int replyBoardIdx;
	public int replyContentIdx;
	public int replyIdx;
	
	@NotBlank
	public String replyContent;
	
	@NotBlank
	public String replyWriter;
	
	public Date replyYmd;
	public String replyPass;
}
