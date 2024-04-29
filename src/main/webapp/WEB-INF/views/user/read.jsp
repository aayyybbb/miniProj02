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
<h1>
   회원 상세 페이지
</h1>

<label>아이디 : ${user.user_id}</label> <br/>
<label>이름: ${user.name}</label><br/>
<label>주소: ${user.addr}</label><br/>
<label>연락처: ${user.phone}</label><br/>
<label>생일: ${user.birth}</label>
<label>성별: ${user.gender}</label><br/>
취미:
<c:forEach var="hobby" items="${hobbyList}">
    <label for="${hobby.hobby_name}">${hobby.hobby_name}</label><br/>
</c:forEach>

<a href="/user/updateForm">수정</a><br/>
<a href="/">취소</a>

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
				alert("회원가입을 완료 하였습니다");
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