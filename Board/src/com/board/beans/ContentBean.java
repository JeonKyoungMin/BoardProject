package com.board.beans;

import lombok.Data;

@Data
public class ContentBean {

	private int contentIdx;
	private String contentTtl;
	private String contentCont;
	private String contentFile;
	private int contentWriterIdx;
	private int contentBoardIdx;
	private String contentYmd;
	
}
