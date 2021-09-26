package com.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.board.beans.BoardInfoBean;

/**
 * @author km
 * @date 2021-08
 */
@Repository
public interface TopMenuDao {

	
	/**
	 * <pre>
	 * 처리내용 : 상단 게시판 메뉴 정보 가져오기
	 */
	public List<BoardInfoBean> getTopMenuList();

}
