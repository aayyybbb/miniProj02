<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
	<h1>회원 목록</h1>

    <form id="searchForm" action="member.do" method="post" >
    	<input type="hidden" id="action" name="action" value="list">
    	<label>제목</label>
    	<input type="text" id="searchKey" name="searchKey" value="${param.searchKey}">
    	<input type="submit" value="검색">
    </form>

    <form id="listForm" action="boards" method="post">
    	<input type="hidden" id="action" name="action" value="view">
    	<input type="hidden" id="bno" name="bno" >
    </form>

    <table border="1">
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>연락처</th>
            <th>성별</th>
        </tr>
        <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.user_id}</td>
            <td><a href="/read${user.user_id}">${user.name}</a></td>
            <td>${user.phone}</td>
            <td>${user.gender}</td>
        </tr>
        </c:forEach>
    </table>
<script>
function jsView(bno) {
	//인자의 값을 설정한다
	bno.value = bno;

	//양식을 통해서 서버의 URL로 값을 전달한다
	listForm.submit();

}
</script>
    <div class="button-container">
        <a href="boards?action=insertForm">등록</a>
    </div>
</body>
</html>

