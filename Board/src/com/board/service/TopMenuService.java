package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.beans.BoardInfoBean;

@Service
public interface TopMenuService {

	public List<BoardInfoBean> getTopMenuList();

}
