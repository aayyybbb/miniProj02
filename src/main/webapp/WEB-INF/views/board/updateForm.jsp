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
    <script src="https://cdn.ckeditor.com/ckeditor5/12.4.0/classic/ckeditor.js"></script>
   	<script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
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

    <form id="rForm" method="post" enctype="multipart/form-data">
        <input type="hidden" name="board_id" value="${board.board_id}">
        <label>제목 : </label><input type="text" id="title" name="title" value="${board.title}" required="required"><br/>
        <textarea id="content" name="content" required="required">${board.content}</textarea>
        <div id="div_file">
            <input  type='file' id="file" name='file' />
        </div>
            <div>
                <input type="submit" value="수정">
                <button onclick="history.go(-1)">취소</button>
            </div>
        <input type="hidden" id="writer" name="writer" value="${loginUser}"><br/>
    </form>

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>

<script type="text/javascript">
    const csrfParameter = document.querySelector("meta[name='_csrf_parameter']").content;
    const csrfToken = document.querySelector("meta[name='_csrf']").content;
    const board_image_url = "<c:url value='/board/ckUpload?'/>" + csrfParameter + "=" + csrfToken;
    const fileInput = document.getElementById("file");
    ClassicEditor
        .create(document.querySelector('#content'), {
            ckfinder: {
                uploadUrl: board_image_url
            }
        })
        .then( editor => {
            console.log(editor);
        })
        .catch( error => {
            console.error(error);
        });

    const rForm = document.getElementById("rForm");
    rForm.addEventListener("submit", e => {
    	//서버에 form data를 전송하지 않는다
    	e.preventDefault();
        if (fileInput.files.length === 0) {
                    // 파일이 선택되지 않은 경우
                    // FormData에서 파일 필드를 제거
            fileInput.remove();
            console.log("파일이 선택되지 않았습니다. FormData에서 파일 필드를 제거합니다.");
        }
		ybFileFetch("/board/update", "rForm", json => {
			if(json.status == 0) {
				//성공
				alert("게시물을 등록 하였습니다");
				location = "/board/list";
			} else {
				alert(json.statusMessage);
			}
		});
    });
    document.addEventListener('DOMContentLoaded', function() {
        const fileLink = document.querySelector('label[for="file_id"]');
        const fileId = document.getElementById('file_id').value;

        fileLink.addEventListener('click', function() {
            window.location.href = "/board/uploadAndDownload/" + fileId;
        });
    });
</script>

</body>
</html>
