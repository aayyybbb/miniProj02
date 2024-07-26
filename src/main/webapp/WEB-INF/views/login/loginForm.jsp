<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <style>
        /*body {*/
        /*    display: flex;*/
        /*    justify-content: center;*/
        /*    align-items: center;*/
        /*    height: 100vh;*/
        /*    margin: 0;*/
        /*}*/

        .container {
            display: flex;
            flex-direction: column; /* 수직으로 요소를 배열 */
            text-align: center;
            height: 100vh; /* 화면 높이의 100%를 차지 */
            justify-content: center; /* 수직 가운데 정렬 */
        }

        .input-group {
            margin-bottom: 10px;
            display: inline-block;
            justify-content: center;
            align-items: center; /* 수직 가운데 정렬 */
        }


        .input-group label {
            display: inline-block; /* inline 태그이므로 inline-block 속성으로 변경 */
            width: 100px; /* 원하는 너비로 조정 */
            text-align: right; /* 텍스트를 오른쪽 정렬 */
            margin-right: 10px; /* 라벨과 입력란 사이 간격 설정 */
            font-weight: bold; /* 라벨 텍스트 굵게 */
        }


        .input-group input {
            padding: 5px;
            width: 200px;
            margin-right: 20px; /* 입력란과 다음 요소 사이 간격 설정 */
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Login</h1>
    <form action="/login" method="post">
        <sec:csrfInput/>
        <div class="input-group">
            <label for="user_id">id: </label>
            <input type="text" name="user_id" id="user_id">
        </div>
        <div class="input-group">
            <label for="pwd">password: </label>
            <input type="password" name="pwd" id="pwd">
        </div>
        <input type="submit" value="Login">
    </form>
</div>

<script>
    msg = "${error ? exception : ''}";
    if (msg !== "") {
        alert(msg);
    }
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>