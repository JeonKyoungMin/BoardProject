package com.board.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.beans.ContentBean;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public ContentBean selectContentList(ContentBean contentBean) {
		ContentBean result = sqlSessionTemplate.selectOne("board.selectContentList",contentBean);
		return result;
	}
}
