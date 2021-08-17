package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.beans.ContentBean;
import com.board.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Override
	public void addContentInfo(ContentBean contentBean) {
		
		System.out.println(contentBean.getContentCont());
		System.out.println(contentBean.getContentTtl());
		System.out.println(contentBean.getUploadFile().getSize());
		
	}
}
