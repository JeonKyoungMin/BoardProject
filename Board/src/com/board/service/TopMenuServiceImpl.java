package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.beans.BoardInfoBean;
import com.board.dao.TopMenuDao;

/**
 * @author 전경민
 * @Date 2021-08
 */
@Service
public class TopMenuServiceImpl implements TopMenuService{

	@Autowired
	private TopMenuDao topMenuDao;
	
	/**
	 * <p>상단 메뉴 정보 읽기</p>
	 */
	@Override
	public List<BoardInfoBean> getTopMenuList() {
		List<BoardInfoBean> topMenuList = topMenuDao.getTopMenuList();
		
		return topMenuList;
	}
}