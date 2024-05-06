<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
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
<form id="rForm">
<c:choose>
    <c:when test="${principal.authorities eq '[ROLE_ADMIN]'}">
        <input type="hidden" id="user_id" name="user_id" value="${user.user_id}">
        <label>권한 선택:</label><br>
            <input type="checkbox" id="admin" name="roles" value="ADMIN" ${user.role eq 'ADMIN' ? 'checked' : ''}>
            <label for="admin">관리자</label><br>
            <input type="checkbox" id="user" name="roles" value="USER" ${user.role eq 'USER' ? 'checked' : ''}>
            <label for="user">사용자</label><br>
        <label>계정 잠금 상태:</label>
        <input type="radio" id="locked" name="locked_at" ${not empty user.locked_at ? 'checked' : ''}> <label for="locked">잠금</label>
        <input type="radio" id="unlocked" name="locked_at" ${empty user.locked_at ? 'checked' : ''}> <label for="unlocked">해제</label><br>
        <div>
              <input type="submit" value="수정">
              <button onclick="history.go(-1)">취소</button >
            </div>
    </c:when>
    <c:otherwise>
        <a href="/user/updateForm">수정</a><br/>
        <button onclick="history.go(-1)">취소</button>
    </c:otherwise>
</c:choose>
</form>
<!--  페이지 네비게이션 바 출력  -->
   <div class="float-end">
       <ul class="pagination flex-wrap">
           <c:if test="${pageResponseVO.prev}">
               <li class="page-item">
                   <a class="page-link" data-num="${pageResponseVO.start -1}">이전</a>
               </li>
           </c:if>

           <c:forEach begin="${pageResponseVO.start}" end="${pageResponseVO.end}" var="num">
               <li class="page-item ${pageResponseVO.pageNo == num ? 'active':''} ">
                   <a class="page-link"  data-num="${num}">${num}</a></li>
           </c:forEach>

           <c:if test="${pageResponseVO.next}">
               <li class="page-item">
                   <a class="page-link"  data-num="${pageResponseVO.end + 1}">다음</a>
               </li>
           </c:if>
       </ul>

   </div>
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript">
    document.getElementById('locked').value= new Date().toISOString().slice(0, -1);
    document.getElementById('unlocked').value= null;

    const rForm = document.getElementById("rForm");
    rForm.addEventListener("submit", e => {
    	//서버에 form data를 전송하지 않는다
    	e.preventDefault();
		ybFetch("/admin/updateUser", "rForm", json => {
			switch(json.status) {
			case 0:
				//성공
				alert("회원정보를 수정 하였습니다");
				location="/admin/list";
				break;
			default:
				alert(json.statusMessage);
			}
		});
    });
</script>
</body>
</html>