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

        /* 모달 스타일 */
        .modal {
            display: none; /* 기본적으로 숨김 */
            position: fixed; /* 고정 위치 */
            z-index: 1; /* 위로 표시 */
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto; /* 스크롤 허용 */
            background-color: rgba(0, 0, 0, 0.4); /* 반투명 배경 */
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
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
<label>작성자 : ${board.user_id}</label><br/>
<label for="file_id">첨부파일 : ${board.file_origin_name}</label><br/>
<input type="hidden" id="file_id" value="${board.file_id}">
<label>작성일 : ${board.created_at}</label><br/>
<label>조회수 : ${board.viewCount}</label><br/>
<div>
    <button onclick="location.href='${pageContext.request.contextPath}/board/list'">목록</button>
    <button onclick="location.href='/board/updateForm/${board.board_id}'">수정</button>
<c:if test="${empty board.pwd}">
    <form id="rForm">
        <input type="hidden" name="board_id" value="${board.board_id}"><br>
        <input type="submit" value="삭제">
    </form>
</c:if>
    <c:if test="${not empty board.pwd}">
        <button id="deleteBtn">삭제</button>
    </c:if>
</div>

<!-- 모달 -->
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>비밀번호 확인</h2>
        <form id="passwordForm">
            <input type="hidden" id="board_id" name="board_id" value="${board.board_id}"><br>
            <label for="pwd">비밀번호 :</label>
            <input type="password" id="pwd" name="pwd" required><br/>
            <input type="submit" value="확인">
        </form>
    </div>
</div>

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script>
    const rForm = document.getElementById("rForm");
    document.addEventListener('DOMContentLoaded', function () {
        const fileLink = document.querySelector('label[for="file_id"]');
        const fileId = document.getElementById('file_id').value;

        fileLink.addEventListener('click', function () {
            window.location.href = "/board/uploadAndDownload/" + fileId;
        });
    });

    const modal = document.getElementById('myModal');

    const deleteBtn = document.getElementById('deleteBtn');

        if (deleteBtn) { // 삭제 버튼이 존재하는 경우에만
            deleteBtn.addEventListener('click', function() {
                const modal = document.getElementById('myModal');
                if (modal) { // 모달이 존재하는 경우에만
                    modal.style.display = "block"; // 모달 표시
                }
            });
        }

        // 모달 닫기 버튼
        const closeBtn = document.querySelector('.close');
        if (closeBtn) {
            closeBtn.addEventListener('click', function() {
                const modal = document.getElementById('myModal');
                if (modal) {
                    modal.style.display = "none"; // 모달 숨김
                }
            });
        }

        // 모달 외부 클릭 시 모달 숨김
        window.addEventListener('click', function(event) {
            const modal = document.getElementById('myModal');
            if (modal && event.target == modal) {
                modal.style.display = "none";
            }
        });

    const passwordForm = document.getElementById('passwordForm');

    // 비밀번호 폼 제출 시
    if(passwordForm){
        passwordForm.addEventListener('submit', function (e) {
            e.preventDefault(); // 폼 전송 막기

            // 서버로 비밀번호와 함께 삭제 요청 보내기
            ybFetch("/board/delete", "passwordForm", json => {
                switch (json.status) {
                    case 0:
                        alert("게시물 정보를 삭제하였습니다");
                        location = "/board/list";
                        break;
                    default:
                        alert(json.statusMessage);
                }
            });
        });
    }

    if(rForm){
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
    }
</script>
</body>
</html>

