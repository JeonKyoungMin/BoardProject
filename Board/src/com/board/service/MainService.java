package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.beans.ContentBean;

/**
 * @author km
 * @date 2021-08
 */
@Service
public interface MainService {

	/**
	 * <p>페이지 메인에 보여줄 게시글 리스트 가져오기</p>
	 */
	public List<ContentBean> getMainList(int boardInfoIdx);
	
}
