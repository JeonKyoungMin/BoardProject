<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }/" />

<script>
	alert("작성되었습니다.")
	location.href="${root}board/listPage?${cri.makeQuery()}&boardInfoIdx=${contentBean.contentBoardIdx}&page=1"
</script>