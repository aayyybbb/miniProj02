<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <link rel="stylesheet" href="/css/bootstrap.min.css">

</head>
<body>
<c:choose>
    <c:when test="${principal.authorities eq '[ROLE_ADMIN]'}">
        <h1>
            User Detail
        </h1>
    </c:when>
    <c:otherwise>
        <h1>myPage</h1>
    </c:otherwise>
</c:choose>
<form id="rForm">
<input type="hidden" id="user_id" name="user_id" value="${user.user_id}">
<label>아이디 : ${user.user_id}</label>
<label>이름: ${user.name}</label><br/>
<label>주소: ${user.addr}</label><br/>
<label>연락처: ${user.phone}</label><br/>
<label>생일: ${user.birth}</label>
<label>성별: ${user.gender}</label><br/>
취미:
<c:forEach var="hobby" items="${hobbyList}">
    <label for="${hobby.hobby_name}">${hobby.hobby_name}</label><br/>
</c:forEach>
    <c:choose>
        <c:when test="${principal.authorities eq '[ROLE_ADMIN]'}">
            <label>권한 선택:</label><br>
            <input type="checkbox" id="admin" name="roles"
                   value="ADMIN" ${user.role.contains('ADMIN') ? 'checked' : ''}>
            <label for="admin">관리자</label><br>
            <input type="checkbox" id="user" name="roles" value="USER" ${user.role.contains('USER') ? 'checked' : ''}>
            <label for="user">사용자</label><br>
            <label>계정 잠금 상태:</label>
            <input type="radio" id="locked" name="locked_at" ${not empty user.locked_at ? 'checked' : ''}> <label
                for="locked">잠금</label>
            <input type="radio" id="unlocked" name="locked_at" ${empty user.locked_at ? 'checked' : ''}> <label
                for="unlocked">해제</label><br>
            <div>
                <input type="submit" name="submitAction" value="수정">
                <input type="submit" name="submitAction" value="delete">
                <button onclick="history.go(-1)">취소</button>
            </div>
        </c:when>
        <c:otherwise>
            <input type="submit" name="submitAction" value="수정">
            <input type="submit" name="submitAction" value="delete">
            <button onclick="history.go(-1)">취소</button>
        </c:otherwise>
    </c:choose>
</form>

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript">
    document.getElementById('locked').value = new Date().toISOString().slice(0, -1);
    document.getElementById('unlocked').value = null;

    const rForm = document.getElementById("rForm");
    rForm.addEventListener("submit", e => {
        e.preventDefault();

        const submitAction = e.submitter.value;

            switch (submitAction) {
                case "수정":
                    rForm.action = "/user/updateForm";
                    break;
                case "delete":
                    rForm.action = "/user/delete";
                    break;
                default:
                    break;
            }

        ybFetch(rForm.action, "rForm", json => {
            switch (json.status) {
                case 0:
                    //성공
                    alert("회원정보를 수정 하였습니다");
                    location = "/user/myPage";
                    break;
                case 1:
                    alert("회원정보를 삭제 하였습니다")
                    location = "/";
                    break
                default:
                    alert(json.statusMessage);
            }
        });
    });

</script>
</body>
</html>