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
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <link rel="stylesheet" href="/css/bootstrap.min.css">

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

        .float-end {
            float: none !important; /* float 해제 */
            display: flex;
            justify-content: center; /* 가로 중앙 정렬 */
        }
    </style>
</head>
<body>
<h1>User List</h1>

<form id="searchForm" action="list">
    <select id="size" name="size">
        <c:forEach var="size" items="${sizes}">
            <option value="${size.page_id}" ${pageResponseVO.size == size.page_id ? 'selected' : ''} >${size.page_num}</option>
        </c:forEach>
    </select>
    <select id="searchBy" name="searchBy">
        <option value="user_id">id</option>
        <option value="name">name</option>
        <option value="role">role</option>
    </select>
    <input type="text" id="searchKey" name="searchKey" value="${param.searchKey}">
    <input type="submit" value="검색">
</form>

<form id="rForm">
    <table>
        <tr>
            <th>check</th>
            <th>id</th>
            <th>name</th>
            <th>phone</th>
            <th>role</th>
            <th>gender</th>
            <th>lock</th>
        </tr>
        <c:forEach var="user" items="${pageResponseVO.list}">
            <tr>
                <td><input type="checkbox" id="usersId" name="usersId" value="${user.user_id}"></td>
                <td>${user.user_id}</td>
                <td><a href="/admin/read/${user.user_id}">${user.name}</a></td>
                <td>${user.phone}</td>
                <td>${user.role}</td>
                <td>${user.gender}</td>
                <td>${empty user.locked_at ? 'unlocked' : 'locked'}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="submitAction" value="lock">
    <input type="submit" name="submitAction" value="unlock">
    <input type="submit" name="submitAction" value="delete">

</form>
<div class="float-end">
    <ul class="pagination flex-wrap">
        <c:if test="${pageResponseVO.prev}">
            <li class="page-item">
                <a class="page-link" data-num="${pageResponseVO.pageNo -1}">prev</a>
            </li>
        </c:if>

        <c:forEach begin="${pageResponseVO.start}" end="${pageResponseVO.end}" var="num">
            <li class="page-item ${pageResponseVO.pageNo == num ? 'active':''} ">
                <a class="page-link" data-num="${num}">${num}</a></li>
        </c:forEach>

        <c:if test="${pageResponseVO.pageNo != pageResponseVO.end && pageResponseVO.pageNo != 1}">
            <li class="page-item">
                <a class="page-link" data-num="${pageResponseVO.pageNo + 1}">next</a>
            </li>
        </c:if>
    </ul>
</div>
<script>
    const searchForm = document.getElementById("searchForm");

    document.querySelector(".pagination").addEventListener("click", function (e) {
        e.preventDefault()

        const target = e.target

        if (target.tagName !== 'A') {
            return
        }
        //dataset 프로퍼티로 접근 또는 속성 접근 메서드 getAttribute() 사용 하여 접근 가능
        //const num = target.getAttribute("data-num")
        const num = target.dataset["num"];

        //페이지번호 설정
        searchForm.innerHTML += `<input type='hidden' name='pageNo' value='\${num}'>`;
        searchForm.submit();
    });


    document.querySelector("#size").addEventListener("change", e => {
        searchForm.submit();
    });

    rForm.addEventListener("submit", e => {
        e.preventDefault();

        const submitAction = e.submitter.value;

        switch (submitAction) {
            case "lock":
                rForm.action = "/admin/lockUsers";
                break;
            case "unlock":
                rForm.action = "/admin/unLockUsers";
                break;
            case "delete":
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

