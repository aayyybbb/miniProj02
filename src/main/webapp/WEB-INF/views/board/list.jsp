<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시물 목록</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .button-container {
            margin-top: 20px;
        }
        .button-container button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
	<h1>게시물목록</h1>
	<h3>로그인 : ${loginUser} </h3>
    <form id="searchForm" action="board.do" method="post" >
    	<label>제목</label>
    	<input type="text" id="searchKey" name="searchKey" value="${param.searchKey}">
    	<input type="submit" value="검색">
    </form>

    <form id="listForm" action="board.do" method="post">
    	<input type="hidden" id="action" name="action" value="view">
    	<input type="hidden" id="id" name="id" >
    </form>

    <table>
        <tr>
            <th>게시물번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        <c:forEach var="board" items="${boardList}">
        <tr>
            <td onclick="jsView('${board.board_id}')"  style="cursor:pointer;">${board.board_id}</td>
            <td><a href="board.do?action=view&id=${board.board_id}">${board.title}</a></td>
            <td>${board.writer}</td>
            <td>${board.created_at}</td>
        </tr>
        </c:forEach>
    </table>
<script>
</script>
    <div class="button-container">
        <a href="/board/insertForm">등록</a>
    </div>
</body>
</html>

