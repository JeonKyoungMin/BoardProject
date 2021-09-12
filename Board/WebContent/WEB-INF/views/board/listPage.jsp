<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath }/"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시판 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		var canPrev = '${pageMaker.prev}';
		if(canPrev !== 'true'){
			$('#page-prev').addClass('disabled');
		}
		
		//next 버튼 활성화, 비활성화 처리
		var canNext = '${pageMaker.next}';
		if(canNext !== 'true'){
			$('#page-next').addClass('disabled');
		}
		
		//현재 페이지 파란색으로 활성화
		var thisPage = '${pageMaker.cri.page}';
		//매번 refresh 되므로 다른 페이지 removeClass 할 필요는 없음->Ajax 이용시엔 해야함
		$('#page'+thisPage).addClass('active');
		
	})
	
	function setPerPageNumSelect(){
		var perPageNum = '${pageMaker.cri.perPageNum}';
		var thisPage = '${pageMaker.cri.page}';
		
		$perPageSel.val(perPageNum).prop("selected",true);
		$perPageSel.on('change',function(){
			window.location.href = "listPage?page="+thisPage+"&perPageNum=";
		})
	}
	
	function setSearchTypeSelect(){
		var $searchTypeSel = $('#searchTypeSel');
		var $keyword = $('#keyWord');
		
		$searchTypeSel.val('${pageMaker.cri.searchType}').prop("selected",true);
		//검색 버튼이 눌리면
		$('#searchBtn').on('click',function(){
			var searchTypeVal = $searchTypeSel.val();
			var keyWordVal = $keyWord.val();
			//검색 조건 입력 안했으면 경고창 
			if(!searchTypeVal){
				alert("검색 조건을 선택하세요!");
				$searchTypeSel.focus();
				return;
			//검색어 입력 안했으면 검색창
			}else if(!keyWordVal){
				alert("검색어를 입력하세요!");
				$('#keyWord').focus();
				return;
			}
			var url = "listPage?page=1"
				+ "&perPageNum=" + "${pageMaker.cri.perPageNum}"
				+ "&searchType=" + searchTypeVal
				+ "&keyWord=" + encodeURIComponent(keyWordVal);
			window.location.href = url;
		})
	}
</script>

</head>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<!-- 게시글 리스트 -->
<div class="container" style="margin-top:100px">
	<div class="card shadow">
		<div class="card-body">
			<h4 class="card-title">${boardInfoName }</h4>
			
			<table class="table table-hover" id='board_list'>
				<thead>
					<tr>
						<th class="text-center d-none d-md-table-cell">글번호</th>
						<th class="w-50">제목</th>
						<th class="text-center d-none d-md-table-cell">작성자</th>
						<th class="text-center d-none d-md-table-cell">작성날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="obj" items="${contentList }">
						<tr>
							<td class="text-center d-none d-md-table-cell">${obj.contentIdx }</td>
							<td><a href='${root }board/readPage?${pageMaker.makeQuery(pageMaker.cri.page)}&boardInfoIdx=${boardInfoIdx}&contentIdx=${obj.contentIdx}'>${obj.contentTtl }</a></td>
							<td class="text-center d-none d-md-table-cell">${obj.contentWriterName }</td>
							<td class="text-center d-none d-md-table-cell">${obj.contentYmd }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="col-md-11">
				<div class="form-inline">
					<select id="searchTypeSel" name="searchType">
						<option value="">검색조건</option>
						<option value="t">제목</option> 
						<option value="c">내용</option>
						<option value="tc">제목+내용</option>
						<option value="all">전체조건</option>
					</select>
					
					<input class="form-control" type="text" id="keyWord" name="keyWord" 
						value="${pageMaker.cri.keyWord}" placeholder="검색어를 입력하세요"/>
					<button id="searchBtn" class="btn btn-primary">Search</button>
				</div>
			</div>
			
			<div class="text-center">
				<nav aria-label="pagination">
					<ul class="pagination">
						<!-- prev 버튼 -->
						<li class="page-item" id="page-prev">
							<a class="page-link" href="listPage${pageMaker.makeQuery(pageMaker.startPage-1)}&boardInfoIdx=${boardInfoIdx}" aria-label="Prev">
								<span aria-hidden="true">«</span>
							</a>
						</li>
				
						<!-- 페이지 번호 (시작 페이지 번호부터 끝 페이지 번호까지) -->
						<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
						    <li id="page${idx}">
					    		<a class="page-link" href="listPage${pageMaker.makeQuery(idx)}&boardInfoIdx=${boardInfoIdx}">
					      			<span>${idx}<span class="sr-only">(current)</span></span>
					    		</a>
				    		</li>
						</c:forEach>
				
						<!-- next 버튼 -->
						<li class="page-item" id="page-next">
						    <a class="page-link" href="listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}&boardInfoIdx=${boardInfoIdx}" aria-label="Next">
						    	<span aria-hidden="true">»</span>
						    </a>
						</li>
					</ul>
				</nav>
			</div>
			
			<div class="text-right">
				<a href="${root }board/write?boardInfoIdx=${boardInfoIdx }" class="btn btn-primary">글쓰기</a>
				<a href="${root }board/listPage?${cri.makeQuery()}&boardInfoIdx=${boardInfoIdx}" class="btn btn-danger">목록으로</a>
			</div>
			
		</div>
	</div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>