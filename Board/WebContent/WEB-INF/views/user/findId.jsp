<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value='${pageContext.request.contextPath }/' />

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
</script>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />


	<div class="container" style="margin-top: 100px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div>
					 <h3 align="center">아이디 찾기 </h3>  
				 </div>
				<div class="card shadow">
					<div class="card-body">
						<form:form action="${root }user/findId_pro" method='post' modelAttribute="userBean">
							<div class="form-group">
								<form:label path="userName">이름</form:label>
								<form:input path="userName" class='form-control'/>
							</div>
							<div class="form-group">
								<form:label path="userNum">전화번호</form:label>
								<form:input path="userNum" name="userNum" id="userNum" maxlength="11" class='form-control' />
							</div>
							
							<c:choose>
								<c:when test="${check == 1 }">
									<label style="color:red;">일치하는 정보가 없습니다.</label><br>
									<label style="color:red;">정보를 다시 입력해주세요.</label>
								</c:when>
								<c:when test="${check == 0 }">
									<label style="color : red;">회원님의 아이디는 ${result } 입니다.</label>
								</c:when>
							</c:choose>
							
							<div class="form-group">
								<div class="text-left">
									<form:button class='btn btn-primary'>아이디 찾기</form:button>
									<a href='${root }user/findPw' class='btn btn-info'>비밀번호 찾기</a>
									<br>
									<div class="text-left">
										<a href='${root }user/login' class='btn btn-success'>로그인</a>
									</div> 
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>