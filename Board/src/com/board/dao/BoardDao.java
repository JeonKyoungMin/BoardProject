package com.board.dao;

import org.springframework.stereotype.Repository;

import com.board.beans.ContentBean;

@Repository
public interface BoardDao {

	public ContentBean selectContentList(ContentBean contentBean);
}
