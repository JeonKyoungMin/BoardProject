package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.beans.ContentBean;

@Service
public interface MainService {

	public List<ContentBean> getMainList(int boardInfoIdx);
	
}
