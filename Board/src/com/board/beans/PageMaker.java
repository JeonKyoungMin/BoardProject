package com.board.beans;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class PageMaker {

	private int displayPageCnt = 10;
	
	private int totalDataCount;
	
	private int startPage;
	
	private int endPage;
	
	private boolean prev;
	
	private boolean next;
	
	private Criteria cri;
	
	public PageMaker(Criteria cri) {
		this.cri = cri;
	}
	
	//전체 게시물의 수를 입력 받음 
	public void setTotalCount(int totalDataCount) {
		this.totalDataCount = totalDataCount;
		calcData(); 
	}
	
	//startPage, endPage, prev, next 를 계산
	public void calcData() {
		int page = this.cri.getPage();
		int perPageNum = this.cri.getPerPageNum();
		
		//예: 현재 페이지가 13이면 13/10 = 1.3 올림-> 2 끝페이지는 2*10=20
		this.endPage = (int)(Math.ceil(page/(double)displayPageCnt)*displayPageCnt);
		
        //예: 현재 페이지가 13이면 20-10 +1 = 11 
		this.startPage = (this.endPage-displayPageCnt) + 1;
		
        //실제로 사용되는 페이지의 수
        //예: 전체 게시물 수가 88개이면 88/10=8.8 올림-> 9
		int tempEndPage = (int)(Math.ceil(totalDataCount / (double) perPageNum));
		
		//만약 계산된 끝 페이지 번호보다 실제 사용되는 페이지 수가 더 작으면 실제 사용될 페이지 번호만 보여줌
		if(this.endPage > tempEndPage) {
			this.endPage = tempEndPage;
		}
		
		this.prev = (startPage != 1); // startPage 1이 아니면 false
		this.next = (endPage * perPageNum < totalDataCount); //아직 더 보여질 페이지가 있으면 true 
	}
	
	public String makeQuery(int page) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", this.cri.getPerPageNum());
		
		if (this.cri.getSearchType() != null) {
			uriComponentsBuilder
					.queryParam("searchType", this.cri.getSearchType())
					.queryParam("keyWord", this.cri.getKeyWord());
		}
		
		return uriComponentsBuilder.build().encode().toString();
	}
}
