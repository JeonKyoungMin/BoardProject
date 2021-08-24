<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<c:set var='root' value='${pageContext.request.contextPath }/'/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>
<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form:form action='${root }board/modify_pro' method='post' name="contentBean" enctype="multipart/form-data"> 
						<form:hidden path="contentIdx"/>
						<form:hidden path="contentBoardIdx"/>
						<div class="form-group">
							<form:label path="contentWriterName">작성자</form:label>
							<form:input path="contentWriterName" class='form-control' readonly="true"/>
						</div>
						<div class="form-group">
							<form:label path="contentYmd">작성날짜</form:label>
							<form:input path="contentYmd" class='form-control' readonly="true"/>
						</div>
						<div class="form-group">
							<form:label path="contentTtl">제목</form:label>
							<form:input path="contentTtl" class='form-control'/>
							<form:errors path='contentTtl' style='color:red'/>
						</div>
						<div class="form-group">
							<form:label path="contentCont">내용</form:label>
							<form:textarea path="contentCont" class='form-control' style='resize:none' rows='10'/>
							<form:errors path='contentCont' style='color:red'/> 
						</div>
						<div class="form-group">
							<form:label path="contentFile">첨부파일</form:label>
							<c:if test="${contentBean.content_file != null }">
								<img src="${root }upload/${contentBean.contentFile}" width="80%"/>	
								<form:hidden path="content_file"/>
							</c:if>
							<form:input path="uploadFile" type='file' class='form-control' accept= 'image/*'/>
						</div>
						<div class="form-group">
							<div class="text-right">
								<form:button class='btn btn-primary'>수정완료</form:button>							
								<a href="${root }board/read?boardInfoIdx=${boardInfoIdx}&contentIdx=${contentIdx}" class="btn btn-info">취소</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>