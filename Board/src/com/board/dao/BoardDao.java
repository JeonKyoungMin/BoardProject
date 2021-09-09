package com.board.dao;

import java.util.List;


import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.board.beans.ContentBean;

/**
 * @author km
 * @date 2021-08
 */
@Repository
public interface BoardDao {

	/**
	 * <p>게시글 작성</p>
	 */
	public void insertContent(ContentBean contentBean);
	
	/**
	 * <p>게시판 정보 가져오기</p>
	 */
	public String getBoardInfo(int boardInfoIdx);
	
	/**
	 * <p>게시글 리스트 가져오기</p>
	 */
	public List<ContentBean> getContentList(int boardInfoIdx, RowBounds rowBounds);
	
	/**
	 * <p>게시글 정보 가져오기</p>
	 */
	public ContentBean getContentInfo(int contentIdx);
	
	/**
	 * <p>게시글 수정</p>
	 */
	public void modifyContent(ContentBean contentBean);
	
	/**
	 * <p>게시글 삭제</p>
	 */
	public void deleteContent(int contentIdx);
	
	/**
	 * <p>페이징을 위해 게시글 개수 가져오기</p>
	 */
	public int getContentCnt(int contentBoardIdx);
	
}
