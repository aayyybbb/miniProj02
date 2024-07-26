<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <style>
        .centered-content {
            display: flex;
            flex-direction: column; /* 수직으로 요소를 배열 */
            justify-content: center; /* 수직 가운데 정렬 */
            align-items: center; /* 수평 가운데 정렬 */
            height: 100vh; /* 화면 높이의 100%를 차지 */
        }
    </style>
</head>
<body>

<div class="centered-content">
    <div class="right-aligned">
        <div class="bs-tether-element-attached-right">I can help your business to</div>
        <div><h1 class="display-3 fw-bolder mb-5"><span class="text-gradient d-inline">Get online and grow fast</span></h1><div>
    </div>
    <div>
        <a class="btn btn-primary btn-lg px-5 py-3 me-sm-3 fs-6 fw-bolder"
           href="<c:url value='/login/loginForm'/>">login</a>
        <a class="btn btn-outline-dark btn-lg px-5 py-3 fs-6 fw-bolder"
           href="<c:url value='/board/list'/>">community</a>
    </div>
</div>
</body>
</html>