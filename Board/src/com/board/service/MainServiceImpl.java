package com.board.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.beans.ContentBean;
import com.board.dao.BoardDao;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private BoardDao boardDao;

	/**
	 * <pre>
	 * 처리내용 : 페이지 메인에 보여줄 게시글 리스트 가져오기 
	 */
	@Override
	public List<ContentBean> getMainList(int boardInfoIdx) {
		RowBounds rowBounds = new RowBounds(0, 5);
		
		return boardDao.getContentList(boardInfoIdx, rowBounds);
	}
}
