<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<body>
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<h4>${exception.getMessage() }</h4>

<ul>
	<c:forEach items="${exception.getStackTrace() }" var="stack">
		<li> ${stack.toString() }</li>
	</c:forEach>
</ul>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>