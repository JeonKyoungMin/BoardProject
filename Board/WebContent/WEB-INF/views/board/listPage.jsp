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
			
			<div class="d-none d-md-block">
				<ul class="pagination justify-content-center">
					<!-- prev 버튼 -->
					<li class="page-item" id="page-prev">
						<a href="listPage${pageMaker.makeQuery(pageMaker.startPage-1)}&boardInfoIdx=${boardInfoIdx}" aria-label="Prev">
							<span aria-hidden="true">«</span>
						</a>
					</li>
			
					<!-- 페이지 번호 (시작 페이지 번호부터 끝 페이지 번호까지) -->
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
					    <li class="page-item" id="page${idx}">
				    		<a href="listPage${pageMaker.makeQuery(idx)}&boardInfoIdx=${boardInfoIdx}">
				    		<!-- 시각 장애인을 위한 추가 -->
				      			<span>${idx}<span class="sr-only">(current)</span></span>
				    		</a>
			    		</li>
					</c:forEach>
			
					<!-- next 버튼 -->
					<li class="page-item" id="page-next">
					    <a href="listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}&boardInfoIdx=${boardInfoIdx}" aria-label="Next">
					    	<span aria-hidden="true">»</span>
					    </a>
					</li>
				</ul>
			</div>
			
			<div class="text-right">
				<a href="${root }board/write?boardInfoIdx=${boardInfoIdx }" class="btn btn-primary">글쓰기</a>
			</div>
			
		</div>
	</div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>