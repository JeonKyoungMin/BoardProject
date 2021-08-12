package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.beans.BoardInfoBean;
import com.board.dao.TopMenuDao;

@Service
public class TopMenuServiceImpl implements TopMenuService{

	@Autowired
	private TopMenuDao topMenuDao;
	
	@Override
	public List<BoardInfoBean> getTopMenuList() {
		List<BoardInfoBean> topMenuList = topMenuDao.getTopMenuList();
		
		return topMenuList;
	}
}