<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<c:set var='root' value='${pageContext.request.contextPath }/'/><!DOCTYPE html>
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
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>

<script type="text/javascript">
	function modifyPw() {
		if (document.modifyPwForm.userPw.value == ""){
			alert("비밀번호를 입력해주세요");
			document.modifyPwForm.userPw.focus();
		} else if (document.modifyPwForm.userPw.value != document.modifyPwForm.userPw2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			document.modifyPwForm.userPw2.focus();
		} else {
			document.modifyPwForm.action = "modifyPw_pro";
			document.modifyPwForm.submit();
			alert("비밀번호가 변경되었습니다.");
		}
	}
</script>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp"/>


<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div>
				<h3 align='center'>비밀번호 재설정</h3>
			 </div>
			<div class="card shadow">
				<div class="card-body">
				<form:form action="${root }user/modifyPw_pro" method='post' modelAttribute="modifyUserBean" name="modifyPwForm">
					<div class="form-group">
						<form:label path="userName">이름</form:label>
						<form:input path="userName" class='form-control' readonly='true'/>
					</div>
					<div class="form-group">
						<form:label path="userId">아이디</form:label>
						<form:input path="userId" class='form-control' readonly='true' />
					</div>
					<div class="form-group">
						<form:label path="userPw">비밀번호</form:label>
						<form:password path="userPw" class='form-control' minlength="4"/>
					</div>
					<div class="form-group">
						<form:label path="userPw2">비밀번호 확인</form:label>
						<form:password path="userPw2" class='form-control' minlength="4"/>
					</div>
					
					<div class="form-group">
						<div class="text-right">
							<form:button class='btn btn-primary' onclick="modifyPw()">비밀번호수정</form:button>
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