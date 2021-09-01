package com.board.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.board.beans.ContentBean;
import com.board.beans.PageBean;

@Service
public interface BoardService {

	public void insertContent(ContentBean contentBean);
	
	public String getBoardInfo(int boardInfoIdx);
	
	public List<ContentBean> getContentList(int boardInfoIdx, int page);

	public ContentBean getContentInfo(int contentIdx);
	
	public void modifyContent(ContentBean contentBean);
	
	public void deleteContent(int contentIdx);
	
	public PageBean getContentCnt(int contentBoardIdx, int currentPage);
	
}
