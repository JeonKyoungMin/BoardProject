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
	
	@Override
	public void insertContent(ContentBean contentBean) {
		sqlSessionTemplate.insert("board.insertContent", contentBean);
	}
	
	@Override
	public String getBoardInfo(int boardInfoIdx) {
		String result = sqlSessionTemplate.selectOne("board.getBoardInfo", boardInfoIdx);
		
		return result;
	}
	
	@Override
	public List<ContentBean> getContentList(int boardInfoIdx, RowBounds rowBounds) {
		List<ContentBean> result = sqlSessionTemplate.selectList("board.getContentList", boardInfoIdx, rowBounds);
		
		return result;
	}
	
	@Override
	public ContentBean getContentInfo(int contentIdx) {
		ContentBean result = sqlSessionTemplate.selectOne("board.getContentInfo", contentIdx);
		
		return result;
	}
	
	@Override
	public void modifyContent(ContentBean contentBean) {
		
		sqlSessionTemplate.update("board.modifyContent", contentBean);
	}
	
	@Override
	public void deleteContent(int contentIdx) {
		
		sqlSessionTemplate.delete("board.deleteContent", contentIdx);
	}
	
	@Override
	public int getContentCnt(int contentBoardIdx) {
		int result = sqlSessionTemplate.selectOne("board.getContentCnt", contentBoardIdx);
		
		return result;
	}
	
	@Override
	public void hitByIdx(ContentBean contentBean) {
		sqlSessionTemplate.update("board.hitByIdx", contentBean);
	}
	
	@Override
	public Criteria selectPrevNextNum(Criteria cri) {
		Criteria result = sqlSessionTemplate.selectOne("board.selectPrevNextNum", cri);
		
		return result;
	}
	
//	페이징 연습
	
	@Override
	public List<ContentBean> listPage(Criteria cri) {
		
		List<ContentBean> result = sqlSessionTemplate.selectList("board.listPage", cri);
		
		return result;
	}
	
	@Override
	public int totalCount(Criteria cri) {
		int result = sqlSessionTemplate.selectOne("board.totalCount", cri);
		
		return result;
	}
}
