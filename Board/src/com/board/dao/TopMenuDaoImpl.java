package com.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.beans.BoardInfoBean;

@Repository
public class TopMenuDaoImpl implements TopMenuDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<BoardInfoBean> getTopMenuList() {
		List<BoardInfoBean> topMenuList = sqlSessionTemplate.selectList("topMenu.get_topmenu_list");
		
		return topMenuList;
	}
}