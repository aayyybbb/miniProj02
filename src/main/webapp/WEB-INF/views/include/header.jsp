<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%-- 로그인 사용자 정보 --%>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarColor01">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/'/>" id="home_link">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/board/list'/>" id="board_link">Board</a>
        </li>
        <li class="nav-item">
                  <a class="nav-link" href="<c:url value='/chat'/>" id="chat_link">Chat</a>
                </li>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
          <li class="nav-item">
            <a class="nav-link" href="<c:url value='/admin/list'/>" id="member_link">Admin</a>
          </li>
        </sec:authorize>
        <c:choose>
          <c:when test="${empty principal}">
            <li class="nav-item">
              <a class="nav-link" href="<c:url value='/login/loginForm'/>" id="login_link">Login</a>
            </li>
          </c:when>
          <c:otherwise>
            <li class="nav-item">
              <a class="nav-link" href="<c:url value='/login/logout'/>" id="logout_link">Logout</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<c:url value='/user/myPage'/>" id="mypage_link">My Page</a>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>
      <form class="d-flex">
        <input class="form-control me-sm-2" type="search" placeholder="Search">
        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
