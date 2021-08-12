package com.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.board.beans.BoardInfoBean;

@Repository
public interface TopMenuDao {

	public List<BoardInfoBean> getTopMenuList();

}
