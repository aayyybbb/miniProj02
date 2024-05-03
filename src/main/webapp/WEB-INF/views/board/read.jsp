<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시물 상세보기</title>
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

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script>

document.addEventListener('DOMContentLoaded', function() {
    const fileLink = document.querySelector('label[for="file_id"]');
    const fileId = document.getElementById('file_id').value;

    fileLink.addEventListener('click', function() {
        window.location.href = "/board/uploadAndDownload/" + fileId;
    });
});
</script>
    <div>
        <a href="${pageContext.request.contextPath}/board/list">목록</a>
        <a href="/board/updateForm/${board.board_id}">수정</a>
        <a href="/board/delete/${board.board_id}">삭제</a>
    </div>
</body>
</html>

