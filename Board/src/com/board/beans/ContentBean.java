package com.board.beans;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ContentBean {

	private int contentIdx;

	@NotBlank
	private String contentTtl;

	@NotBlank
	private String contentCont;
	
	private String contentFile;
	
	private int contentWriterIdx;
	
	private int contentBoardIdx;
	
	private String contentYmd;
	
	private int contentCnt;
	
	private String contentWriterName;

	private MultipartFile uploadFile;
}
