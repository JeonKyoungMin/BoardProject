package com.board.service;

import org.springframework.stereotype.Service;

import com.board.beans.ContentBean;

@Service
public interface BoardService {

	public boolean selectContentList(ContentBean contentBean);
}
