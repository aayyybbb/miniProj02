<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
</h1>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>

<c:choose>
	<c:when test="${empty principal}">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/login/loginForm">로그인</a></li>
			<li class="nav-item"><a class="nav-link" href="/user/insertForm">회원가입</a></li>
		</ul>
	</c:when>
	<c:otherwise>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/user/myPage">회원정보</a></li>
			<li class="nav-item"><a class="nav-link" href="/login/logout">로그아웃</a></li>
			<li class="nav-item"><a class="nav-link" href="/board/list">게시물 목록</a></li>
			<c:if test="${principal.authorities eq '[ROLE_ADMIN]'}">
				<li class="nav-item"><a class="nav-link" href="/admin/list">관리자 메뉴</a></li>
			</c:if>
		</ul>
	</c:otherwise>
</c:choose>

</body>
</html>