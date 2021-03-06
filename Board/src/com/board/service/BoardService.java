package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.beans.ContentBean;
import com.board.beans.Criteria;
import com.board.beans.PageBean;

/**
 * @author 전경민
 * @date 2021-08
 *  
 */
@Service
public interface BoardService {
	
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
	public List<ContentBean> getContentList(int boardInfoIdx, int page);

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
	public PageBean getContentCnt(int contentBoardIdx, int currentPage);
	
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
	 * 처리내용 : 페이징을 포함한 게시글 리스트 가져오기 
	 */
	public List<ContentBean> listPage(Criteria cri);

	/**
	 * <pre>
	 * 처리내용 : 게시글 총 갯수 가져오기
	 */
	public int totalCount(Criteria cri);
}
