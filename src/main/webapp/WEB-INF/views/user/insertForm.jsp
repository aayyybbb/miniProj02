<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
회원가입 페이지

<form id="rForm" method="post">
    <label>아이디:</label> <input type="text" id="user_id" name="user_id" required="required"><br>
    <label>비밀번호:</label> <input type="password" id="pwd" name="pwd" required="required"><br>
    <label>이름:</label> <input type="text" id="name" name="name" required="required"><br>
    <label>주소:</label> <input type="text" id="addr" name="addr" required="required"><br>
    <label>생일:</label> <input type="text" id="birth" name="birth" required="required"><br>
    <label>연락처:</label> <input type="text" id="phone" name="phone" required="required"><br>
    <label>성별:</label>
    <input type="radio" id="male" name="gender" value="male"> <label for="male">남성</label>
    <input type="radio" id="female" name="gender" value="female"> <label for="female">여성</label><br>
    <label>취미:</label><br>
    <c:forEach var="hobby" items="${hobby}">
        <input type="checkbox" id="${hobby.hobby_id}" name="hobby" value="${hobby.hobby_id}">
        <label for="${hobby.hobby_name}">${hobby.hobby_name}</label><br>
    </c:forEach>
    <div>
      <input type="submit" value="등록">
    </div>
</form>
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript">
    const rForm = document.getElementById("rForm");
    rForm.addEventListener("submit", e => {
    	//서버에 form data를 전송하지 않는다
    	e.preventDefault();

		ybFetch("/user/insert", "rForm", json => {
			switch(json.status) {
			case 0:
				//성공
				alert("게시물을 등록 하였습니다");
				location="/";
				break;
			default:
				alert(json.statusMessage);
			}
		});
    });
</script>
</body>
</html>