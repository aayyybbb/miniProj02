<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시물 상세보기</title>
    <%@ include file="/WEB-INF/views/include/meta.jsp" %>
    <%@ include file="/WEB-INF/views/include/css.jsp" %>
    <%@ include file="/WEB-INF/views/include/js.jsp" %>
    <style>
        label {
            display: inline-block;
            width: 200px;
        }

        input {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h1>
    게시물 상세보기
</h1>

<label>게시물 번호: ${board.board_id}</label> <br/>
<label>제목 : ${board.title}</label><br/>
<label>내용 : ${board.content}</label><br/>
<label>작성자 : ${board.writer}</label><br/>
<label for="file_id">첨부파일 : ${board.file_origin_name}</label><br/>
<input type="hidden" id="file_id" value="${board.file_id}">
<label>작성일 : ${board.created_at}</label><br/>
<label>조회수 : ${board.viewCount}</label><br/>
<div>
    <button onclick="location.href='${pageContext.request.contextPath}/board/list'">목록</button>
   <button onclick="location.href='/board/updateForm/${board.board_id}'">수정</button>
    <form id="rForm">
        <input type="hidden" id="board_id" name="board_id" value="${board.board_id}"><br>
        <input type="submit" value="삭제">
    </form>
</div>
<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const fileLink = document.querySelector('label[for="file_id"]');
        const fileId = document.getElementById('file_id').value;

        fileLink.addEventListener('click', function () {
            window.location.href = "/board/uploadAndDownload/" + fileId;
        });
    });

    const rForm = document.getElementById("rForm");
    rForm.addEventListener("submit", e => {
        //서버에 form data를 전송하지 않는다
        e.preventDefault();

        ybFetch("/board/delete", "rForm", json => {
            switch (json.status) {
                case 0:
//성공
                    alert("게시물 정보를 삭제 하였습니다");
                    location = "/board/list";
                    break;
                default:
                    alert(json.statusMessage);
            }
        });
    });
</script>
</body>
</html>

