package com.board.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.board.beans.ContentBean;
import com.board.beans.Criteria;

/**
 * @author km
 * @date 2021-08
 */
@Repository
public interface BoardDao {

	/**
	 * <pre>
	 * 처리내용 : 게시글 작성
	 */
	public void insertContent(ContentBean contentBean);
	
	/**
	 * <pre>
	 * 처리내용 : 게시판 정보 가져오기
	 */
	public String getBoardInfo(int boardInfoIdx);
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 리스트 가져오기
	 */
	public List<ContentBean> getContentList(int boardInfoIdx, RowBounds rowBounds);
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 정보 가져오기
	 */
	public ContentBean getContentInfo(int contentIdx);
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 수정
	 */
	public void modifyContent(ContentBean contentBean);
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 삭제
	 */
	public void deleteContent(int contentIdx);
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 총갯수 가져오기
	 */
	public int getContentCnt(int contentBoardIdx);
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 조회수 증가
	 */
	public void hitByIdx(ContentBean contentBean);
	
	/**
	 * <pre>
	 * 처리내용 : 게시글의 이전, 다음 글 정보 가져오기
	 */
	public Criteria selectPrevNextNum(Criteria cri);
	
	/**
	 * <pre>
	 * 처리내용 : 페이징을 포함한 게시글 가져오기
	 */
	public List<ContentBean> listPage(Criteria cri);
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 전체 갯수 가져오기
	 */
	public int totalCount(Criteria cri);
}
