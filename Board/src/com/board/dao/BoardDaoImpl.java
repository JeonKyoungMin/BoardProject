package com.board.dao;

import java.util.List;


import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.beans.ContentBean;
import com.board.beans.Criteria;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 작성
	 */
	@Override
	public void insertContent(ContentBean contentBean) {
		sqlSessionTemplate.insert("board.insertContent", contentBean);
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시판 정보 가져오기
	 */
	@Override
	public String getBoardInfo(int boardInfoIdx) {
		String result = sqlSessionTemplate.selectOne("board.getBoardInfo", boardInfoIdx);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 리스트 가져오기
	 */
	@Override
	public List<ContentBean> getContentList(int boardInfoIdx, RowBounds rowBounds) {
		List<ContentBean> result = sqlSessionTemplate.selectList("board.getContentList", boardInfoIdx, rowBounds);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 정보 가져오기 
	 */
	@Override
	public ContentBean getContentInfo(int contentIdx) {
		ContentBean result = sqlSessionTemplate.selectOne("board.getContentInfo", contentIdx);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 수정
	 */
	@Override
	public void modifyContent(ContentBean contentBean) {
		
		sqlSessionTemplate.update("board.modifyContent", contentBean);
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 삭제
	 */
	@Override
	public void deleteContent(int contentIdx) {
		
		sqlSessionTemplate.delete("board.deleteContent", contentIdx);
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 총갯수 가져오기 
	 */
	@Override
	public int getContentCnt(int contentBoardIdx) {
		int result = sqlSessionTemplate.selectOne("board.getContentCnt", contentBoardIdx);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 조회수 증가 
	 */
	@Override
	public void hitByIdx(ContentBean contentBean) {
		sqlSessionTemplate.update("board.hitByIdx", contentBean);
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글의 이전, 다음 글 정보 가져오기
	 */
	@Override
	public Criteria selectPrevNextNum(Criteria cri) {
		Criteria result = sqlSessionTemplate.selectOne("board.selectPrevNextNum", cri);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 페이징을 포함한 게시글 가져오기
	 */
	@Override
	public List<ContentBean> listPage(Criteria cri) {
		
		List<ContentBean> result = sqlSessionTemplate.selectList("board.listPage", cri);
		
		return result;
	}
	
	/**
	 * <pre>
	 * 처리내용 : 게시글 전체 갯수 가져오기
	 */
	@Override
	public int totalCount(Criteria cri) {
		int result = sqlSessionTemplate.selectOne("board.totalCount", cri);
		
		return result;
	}
}
