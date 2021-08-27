package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.beans.ContentBean;

@Service
public interface BoardService {

	public void insertContent(ContentBean contentBean);
	
	public String getBoardInfo(int boardInfoIdx);
	
	public List<ContentBean> getContentList(int boardInfoIdx);

	public ContentBean getContentInfo(int contentIdx);
	
	public void modifyContent(ContentBean contentBean);
}
