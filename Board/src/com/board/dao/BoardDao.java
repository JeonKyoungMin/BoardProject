package com.board.dao;

import java.util.List;


import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.board.beans.ContentBean;

@Repository
public interface BoardDao {

	public void insertContent(ContentBean contentBean);
	
	public String getBoardInfo(int boardInfoIdx);
	
	public List<ContentBean> getContentList(int boardInfoIdx, RowBounds rowBounds);
	
	public ContentBean getContentInfo(int contentIdx);
	
	public void modifyContent(ContentBean contentBean);
	
	public void deleteContent(int contentIdx);
	
	public int getContentCnt(int contentBoardIdx);
	
}
