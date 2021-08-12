<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath }/" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<script>
	$(document).ready(function(){
		$(document).on("keyup", "#userNum", function() { 
		    $(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^[0-9]{3})([0-9]{4})([0-9]{4})$/,"$1-$2-$3").replace("--", "-","-") );
		    
		 }); 
	})

	function checkDelete() {
		if (!confirm("탈퇴하시겠습니까?")) {
			return false;
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
				<form:form action="${root }user/delete_pro" method='post' modelAttribute="userBean">
					<form:hidden path="userIdx"/>
					<div class="form-group">
						<form:label path="userName">이름</form:label>
						<form:input path="userName" class='form-control' readonly='true'/>
						<form:errors path="userName" style='color:red'/>
					</div>
					<div class="form-group">
						<form:label path="userId">아이디</form:label>
						<form:input path="userId" class='form-control' readonly='true' />
						<form:errors path="userId" style='color:red'/>
					</div>
					<div class="form-group">
						<form:label path="userNum">전화번호</form:label>
						<form:input path="userNum" name="userNum" id="userNum" class='form-control' maxlength="11" />
					</div>
					<div class="form-group">
						<form:label path="userPw">비밀번호</form:label>
						<form:password path="userPw" class='form-control'/>
						<form:errors path='userPw' style='color:red'/>
					</div>
					<div class="form-group">
						<form:label path="userPw2">비밀번호 확인</form:label>
						<form:password path="userPw2" class='form-control'/>
						<form:errors path='userPw2' style='color:red'/>
					</div>
					
					<c:if test="${check == 1 }">
						<label style="color : red;">일치하는 회원이 없습니다.</label> <br>
						<label style="color : red;">정보를 다시 확인해주세요.</label>
					</c:if>
					
						
					<div class="form-group">
						<div class="text-center">
							<form:button class='btn btn-primary' onclick="checkDelete()">회원탈퇴</form:button>
						</div>
					</div>
					
					</form:form>
						<div class="text-center">
							<button class="list_btn btn-danger" onclick="location='${root}main'">목록</button>
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