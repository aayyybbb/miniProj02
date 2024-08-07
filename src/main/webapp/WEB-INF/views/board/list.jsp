<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시물 목록</title>
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
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
<h1>Board List</h1>
<c:if test="${not empty loginUser}">
<h5>login user : ${loginUser} </h5>
</c:if>
<form id="searchForm" action="list">
    <select id="size" name="size">
        <c:forEach var="size" items="${sizes}">
            <option value="${size.page_id}" ${pageResponseVO.size == size.page_id ? 'selected' : ''} >${size.page_num}</option>
        </c:forEach>
    </select>
    <select id="searchBy" name="searchBy">
        <option value="title">title</option>
        <option value="user_id">writer</option>
        <option value="content">content</option>
    </select>
    <input type="text" id="searchKey" name="searchKey" value="${param.searchKey}">
    <input type="submit" value="search">
</form>

<table>
    <tr>
        <th>no</th>
        <th>title</th>
        <th>writer</th>
        <th>views</th>
        <th>date</th>
    </tr>
    <c:forEach var="board" items="${pageResponseVO.list}">
        <tr>
            <td>${board.board_id}</td>
            <td><a href="/board/read/${board.board_id}">
                <c:if test="${board.created_at.plusHours(12) > now }">
                    <img src="/images/new.png" width="50px" alt="new"/>
                </c:if>
                    ${board.title}
            </a></td>
            <td>${board.user_id}</td>
            <td>${board.viewCount}</td>
            <td>${board.created_at}</td>
        </tr>
    </c:forEach>
</table>
<!--  페이지 네비게이션 바 출력  -->
<div class="float-end">
    <ul class="pagination flex-wrap">
        <c:if test="${pageResponseVO.prev}">
            <li class="page-item">
                <a class="page-link" data-num="${pageResponseVO.pageNo -1}">&laquo;</a>
            </li>
        </c:if>

        <c:forEach begin="${pageResponseVO.start}" end="${pageResponseVO.end}" var="num">
            <li class="page-item ${pageResponseVO.pageNo == num ? 'active':''} ">
                <a class="page-link" data-num="${num}">${num}</a></li>
        </c:forEach>

        <c:if test="${pageResponseVO.pageNo != pageResponseVO.end && pageResponseVO.pageNo != 1}">
            <li class="page-item">
                <a class="page-link" data-num="${pageResponseVO.pageNo + 1}">&raquo;</a>
            </li>
        </c:if>
    </ul>
</div>
<div class="button-container">
    <a href="${pageContext.request.contextPath}/board/insertForm">write</a>
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

</script>
</body>
</html>

