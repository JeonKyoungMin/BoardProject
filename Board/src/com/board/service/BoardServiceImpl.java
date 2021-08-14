package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.beans.ContentBean;
import com.board.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	
	public boolean selectContentList(ContentBean contentBean) {
		ContentBean result = boardDao.selectContentList(contentBean);
		
		if (result == null){
			
			return false;
		} else {
			
			return true;
		}
	}
}
