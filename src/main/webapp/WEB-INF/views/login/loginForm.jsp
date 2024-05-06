<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<!-- 오그인시 오류 메시지 출력 -->
<h1>Sign In</h1>
<form action="/login" method="post">
    <%-- csrf 토큰 설정 --%>
    <sec:csrfInput/>
    아이디 : <input type="text" name="user_id"/><br/>
    비밀번호 : <input type="password" name="pwd"/><br/>
    <a href="#" id="forgotPasswordLink">비밀번호를 잊으셨나요?</a>
    <input type="submit" value="로그인">
</form>
<!-- 모달을 표시할 HTML 코드 -->
<div class="modal-backdrop" id="forgotPasswordModal" style="display: none;">
    <div class="modal">
        <div class="modal-content">
            <!-- 모달 내용 -->
            <h2>비밀번호 찾기</h2>
            <form action="/forgotPassword" method="post">
                <%-- CSRF 토큰 설정 --%>
                <sec:csrfInput/>
                아이디 : <input type="text" name="user_id"/><br/>
                <!-- 이메일 입력란 등 추가 필요한 정보 입력 -->
                <input type="submit" value="비밀번호 재설정 이메일 보내기">
            </form>
            <span class="modal-close" onclick="closeModal()">닫기</span>
        </div>
    </div>
</div>

<script>
    msg = "${error ? exception : ''}";
    if (msg !== "") {
        alert(msg);
    }
    // 비밀번호 찾기 모달 표시
    document.getElementById("forgotPasswordLink").addEventListener("click", function (event) {
        event.preventDefault();
        document.getElementById("forgotPasswordModal").style.display = "block";
    });

    // 비밀번호 찾기 모달 숨김
    document.addEventListener("click", function (event) {
        if (event.target === document.getElementById("forgotPasswordModal")) {
            document.getElementById("forgotPasswordModal").style.display = "none";
        }
    });

    function closeModal() {
        document.getElementById("forgotPasswordModal").style.display = "none";
    }
</script>
</body>
</html>