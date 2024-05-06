<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
    <%@ include file="/WEB-INF/views/include/js.jsp" %>
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

<form id="searchForm" action="member.do" method="post">
    <input type="hidden" id="action" name="action" value="list">
    <label>제목</label>
    <input type="text" id="searchKey" name="searchKey" value="${param.searchKey}">
    <input type="submit" value="검색">
</form>

<form id="rForm">
    <table>
        <tr>
            <th>구분</th>
            <th>아이디</th>
            <th>이름</th>
            <th>연락처</th>
            <th>권한</th>
            <th>성별</th>
            <th>잠금 상태</th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td><input type="checkbox" id="usersId" name="usersId" value="${user.user_id}"></td>
                <td>${user.user_id}</td>
                <td><a href="/admin/read/${user.user_id}">${user.name}</a></td>
                <td>${user.phone}</td>
                <td>${user.role}</td>
                <td>${user.gender}</td>
                <td>${empty user.locked_at ? 'F' : 'T'}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="submitAction" value="계정 잠금">
    <input type="submit" name="submitAction" value="잠금 해제">
    <input type="submit" name="submitAction" value="계정 삭제">

</form>
<script>
    rForm.addEventListener("submit", e => {
        e.preventDefault();

        const submitAction = e.submitter.value;

        switch (submitAction) {
            case "계정 잠금":
                rForm.action = "/admin/lockUsers";
                break;
            case "잠금 해제":
                rForm.action = "/admin/unLockUsers";
                break;
            case "계정 삭제":
                rForm.action = "/admin/deleteUsers";
                break;
            default:
                break;
        }

        ybFetch(rForm.action, "rForm", json => {
            switch (json.status) {
                case 0:
                    //성공
                    alert("회원정보를 수정 하였습니다");
                    location = "/admin/list";
                    break;
                default:
                    alert(json.statusMessage);
            }
        });
    });

</script>
</body>
</html>

