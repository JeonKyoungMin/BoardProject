package com.board.beans;

import lombok.Getter;

@Getter
public class PageBean {

	private int min;
	private int max;
	private int prevPage;
	private int nextPage;
	private int pageCnt;
	private int currentPage;
	
	// contentCnt : 전체 글 개수, currentPage : 현재 글 번호, contentPageCnt : 페이지당 글 개수, paginationCnt : 페이지버튼 개수
	public PageBean(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
		// 현재 페이지 번호
		this.currentPage = currentPage;
		
		//전체 페이지 개수
		pageCnt = contentCnt / contentPageCnt;
		
		if (contentCnt % contentPageCnt > 0) {
			pageCnt++;
		} 		
		
		min = ( (currentPage -1) / contentPageCnt ) * contentPageCnt + 1;
		
		max = min + paginationCnt - 1;
		
		if (max > pageCnt) {
			max = pageCnt;
		}
		
		prevPage = min - 1;
		nextPage = max + 1;
		if (nextPage > pageCnt) {
			nextPage = pageCnt;
		}

	}
}
