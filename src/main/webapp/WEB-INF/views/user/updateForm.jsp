<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
    <%@ include file="/WEB-INF/views/include/js.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
</head>
<body>
회원정보 수정 페이지

    <form id="rForm" method="post">
    <input type="hidden" name="action" value="update">
    <label>아이디:</label> <input type="text" id="user_id" name="user_id" value="${user.user_id}"  readonly="readonly"><br>
    <label>비밀번호:</label> <input type="password" id="pwd" name="pwd" value="${user.pwd}" required="required"><br>
    <label>이름:</label> <input type="text" id="name" name="name" value="${user.name}" required="required"><br>
    <label>주소:</label> <input type="text" id="addr" name="addr" value="${user.addr}" required="required"><br
    <label>생일:</label> <input type="text" id="birth" name="birth" value="${user.birth}" required="required"><br>
    <label>연락처:</label> <input type="text" id="phone" name="phone" value="${user.phone}" required="required"><br>
<label>성별:</label>
<input type="radio" id="male" name="gender" value="male" ${user.gender eq 'male' ? 'checked' : ''}> <label for="male">남성</label>
<input type="radio" id="female" name="gender" value="female" ${user.gender eq 'female' ? 'checked' : ''}> <label for="female">여성</label><br>
  <label>취미:</label><br>
<c:forEach var="hobby" items="${hobby}">
    <c:set var="isChecked" value="false"/>
    <c:forEach var="hobbyList" items="${hobbyList}">
        <c:if test="${hobbyList.hobby_id eq hobby.hobby_id}">
            <c:set var="isChecked" value="true"/>
        </c:if>
    </c:forEach>
    <input type="checkbox" id="${hobby.hobby_id}" name="hobby" value="${hobby.hobby_id}" ${isChecked ? 'checked' : ''}>
    <label for="${hobby.hobby_id}">${hobby.hobby_name}</label><br>
</c:forEach>
    <div>
        <input type="submit" value="등록">
        <a href="/user/myPage">취소</a>
    </div>
</form>

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript">
    const rForm = document.getElementById("rForm");
    rForm.addEventListener("submit", e => {
    	//서버에 form data를 전송하지 않는다
    	e.preventDefault();

		ybFetch("/user/update", "rForm", json => {
			switch(json.status) {
			case 0:
				//성공
				alert("회원정보를 수정 하였습니다");
				location="/user/myPage";
				break;
			default:
				alert(json.statusMessage);
			}
		});
    });
</script>
</body>
</html>