package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.beans.BoardInfoBean;

/**
 * @author km
 * @date 2021-08
 */
@Service
public interface TopMenuService {

	/**
	 * <p>상단 게시판 메뉴 정보 가져오기</p>
	 */
	public List<BoardInfoBean> getTopMenuList();

}
