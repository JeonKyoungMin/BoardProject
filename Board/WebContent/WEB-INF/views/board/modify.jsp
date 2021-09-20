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
<script type="text/javascript">
	$(document).ready(function() {
		$(".list_btn").on("click", function() {
			location.href="${root }board/readPage${cri.makeQuery()}&boardInfoIdx=${boardInfoIdx}&contentIdx=${contentIdx}&page=${page}";
		});
	})
	
	function modifyPro() {
		if (document.modifyForm.contentTtl.value == ""){
			document.modifyForm.contentTtl.focus();
			alert("제목을 작성해주세요")
			return false;
		} else if (document.modifyForm.contentCont.value == "") {
			document.modifyForm.contentCont.focus();
			alert("내용을 작성해주세요")
			return false;
		} else {
			if (confirm("글을 수정하시겠습니까?") == true) {
				document.modifyForm.submit();
			} else {
				return false;
			}
		}
	}
</script>

<body>
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>
<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form:form action='${root }board/modify_pro' method='post' name ="modifyForm" modelAttribute="contentBean" enctype="multipart/form-data"> 
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
							<c:if test="${contentBean.contentFile != null }">
								<img src="${root }upload/${contentBean.contentFile}" width="80%"/>	
								<form:hidden path="contentFile"/>
							</c:if>
							<form:input path="uploadFile" type='file' class='form-control' accept= 'image/*'/>
						</div>
					</form:form>
						<div class="form-group">
							<div class="text-right">
								<button class="btn btn-primary" onclick="modifyPro()">수정완료</button>
								<button type="button" class="list_btn btn btn-primary">목록</button>
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