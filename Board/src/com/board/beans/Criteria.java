package com.board.beans;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {
	private int page;
	private int perPageNum;
	private int boardInfoIdx;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
    
	//pageStart를 반환
	public int getPageStart() {
		return (this.page - 1)*perPageNum;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
		}else {
			this.page = page;
		}
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <=0 || perPageNum > 100) {
			this.perPageNum = 10;
		}else {
			this.perPageNum = perPageNum;
		}
	}
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	public String makeQuery() {
		return UriComponentsBuilder.newInstance()
						.queryParam("page", page)
						.queryParam("perPageNum", perPageNum)
						.build().encode().toString();
	}
}