package com.board.beans;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {
	
	private int page;
	
	private int perPageNum;
	
	private String searchType;
	
	private String keyWord;
	
	private int boardInfoIdx;
	
	private int contentIdx;
	
    private int prevIdx;

    private int nextIdx;
    
    private String prevTtl;
    
    private String nextTtl;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
		this.searchType = null;
		this.keyWord = null;
	}
    
	//pageStart를 반환
	public int getPageStart() {
		return (this.page - 1)*perPageNum;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <=0 || perPageNum > 100) {
			this.perPageNum = 10;
		} else {
			this.perPageNum = perPageNum;
		}
	}
	
	public String makeQuery() {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
							.queryParam("page", page)
							.queryParam("perPageNum", this.perPageNum);
		
		if (searchType != null) {
			uriComponentsBuilder
							.queryParam("searchType", this.searchType)
							.queryParam("keyWord", this.keyWord);
		}
		return uriComponentsBuilder.build().encode().toString();
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", searchType=" + searchType + ", keyWord="
				+ keyWord + ", boardInfoIdx=" + boardInfoIdx + ", contentIdx=" + contentIdx + ", prevIdx=" + prevIdx
				+ ", nextIdx=" + nextIdx + ", prevTtl=" + prevTtl + ", nextTtl=" + nextTtl + "]";
	}
	
}