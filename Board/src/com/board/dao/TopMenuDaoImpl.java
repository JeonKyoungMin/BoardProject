package com.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.beans.BoardInfoBean;

/**
 * @author 전경민
 * @Date 2021-08
 */
@Repository
public class TopMenuDaoImpl implements TopMenuDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * <p>상단 메뉴 정보 읽기</p>
	 */
	@Override
	public List<BoardInfoBean> getTopMenuList() {
		List<BoardInfoBean> topMenuList = sqlSessionTemplate.selectList("topMenu.get_topmenu_list");
		
		return topMenuList;
	}
}