<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>등록화면</title>
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
    <%@ include file="/WEB-INF/views/include/js.jsp" %>
    <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
    <style>
        label {
            display: inline-block;

            width: 120px;
        }
        input {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <h1>
        게시물 등록양식
    </h1>
	<h3>로그인 : ${loginUser} </h3>

    <form id="rForm">
        <label>제목 : </label><input type="text" id="title" name="title" required="required"><br/>
        <label>내용 : </label><input type="text" id="content" name="content" required="required"><br/>
        <input type="hidden" id="writer" name="writer" value="${loginUser}"><br/>
    <div>
        <input type="submit" value="등록">
        <a href="javascript:history(-1)">취소</a>
    </div>

    </form>

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>

<script type="text/javascript">
    var ckeditor_config = {
        filebrowserUploadUrl: '/board/upload',
        extraPlugins : 'autogrow'
       };
    CKEDITOR.replace('content');
    const rForm = document.getElementById("rForm");
    rForm.addEventListener("submit", e => {
    	//서버에 form data를 전송하지 않는다
    	e.preventDefault();

		ybFetch("board.do", "rForm", json => {
			if(json.status === 0) {
				//성공
				alert("게시물을 등록 하였습니다");
				location = "board.do?action=list";
			} else {
				alert(json.statusMessage);
			}
		});
    });

</script>

</body>
</html>
