package com.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.beans.ContentBean;

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
	public List<ContentBean> getContentList(int boardInfoIdx) {
		List<ContentBean> result = sqlSessionTemplate.selectList("board.getContentList", boardInfoIdx);
		
		return result;
	}
	
}
