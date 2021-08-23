<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value='${pageContext.request.contextPath }/'/>
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
	$(document).ready(function() {

		$(".list_btn").on("click", function() {
			location.href = "/board/main?boardInfoIdx=${boardInfoIdx}";
		});
		
		$(".update_btn").on("click", function() {
			location.href = "/board/modify?contentIdx=${contentIdx}&boardInfoIdx=${boardInfoIdx}"
		})
		
		$(".delete_btn").on("click", function() {
			location.href = "/board/delete?contentIdx=${contentIdx}&boardInfoIdx=${boardInfoIdx}"
		})
	})
	
	
</script>

</head>
<body>
	
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<div class="form-group">
						<label for="board_writer_name">작성자</label>
						<input type="text" id="board_writer_name" name="board_writer_name" class="form-control" value="${result.contentWriterName }" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_date">작성날짜</label>
						<input type="text" id="board_date" name="board_date" class="form-control" value="${result.contentYmd }" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_subject">제목</label>
						<input type="text" id="board_subject" name="board_subject" class="form-control" value="${result.contentTtl }" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_content">내용</label>
						<textarea id="board_content" name="board_content" class="form-control" rows="10" style="resize:none" disabled="disabled">${result.contentCont }</textarea>
					</div>
					
					<c:if test="${result.contentFile != null }">
						<div class="form-group">
							<label for="board_file">첨부 이미지</label>
							<img src="${root }upload/${result.contentFile}" width="80%"/>						
						</div>
					</c:if>
					
					<div class="form-group">
						<div class="text-right">
								<button type="button" class="list_btn btn btn-primary">목록</button>
								<c:if test="${loginUserBean.userIdx == result.contentWriterIdx }">
									<button type="button" class="update_btn btn btn-warning">수정</button>
									<button type="button" class="delete_btn btn btn-danger">삭제</button>
								</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>


<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>
    