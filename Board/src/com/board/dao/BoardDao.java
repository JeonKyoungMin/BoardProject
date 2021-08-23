package com.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.board.beans.ContentBean;

@Repository
public interface BoardDao {

	public void insertContent(ContentBean contentBean);
	
	public String getBoardInfo(int boardInfoIdx);
	
	public List<ContentBean> getContentList(int boardInfoIdx);
	
	public ContentBean getContentInfo(int contentIdx);
}
