<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../layout/common.jsp"%>
    <%@ include file="../layout/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/detail.css">
<link rel="stylesheet" href="/css/reset.css">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
</head>
<body>
<div id="container">



		<div id="board_wrap">
			<div class="board_wrap_title">문의 사항</div>
			<div class="board_title">&nbsp; ${question.title}</div>

			<div class="board_subtitle">
				<div>
					&nbsp;&nbsp;글번호 : <span id="id">${question.id}</span> &nbsp; 작성자 :
					${question.user.username}
				</div>
				<div class="board_subtitle_etc">
					<div>&nbsp;&nbsp;</div>
					<div>조회수&nbsp;${question.count}</div>
					<div>
						작성일 &nbsp;
						<fmt:formatDate pattern="yyyy/MM/dd"
							value="${question.createDate}" />
					</div>
				</div>
			</div>

			<div class="board_content_wrap">
				<div class="board_content"><pre>${question.contents}</pre></div>
			</div>

			<div id="reply_title">&nbsp;댓글</div>
  
			<c:forEach var="reply" items="${question.reply}">
				<div class="reply_box">
					<div class="reply_body">
						<div id="reply_info">
							<h4>&nbsp;작성자 : ${reply.users.username}</h4>
							<h4>
								작성시간 :
								<fmt:formatDate pattern="yyyy/MM/dd  hh.mm.ss"
									value="${reply.createDate}" />
							</h4>
							<br>
							<c:if
								test="${reply.users.id==principal.user.id or principal.user.roles=='ADMIN'}">
								<button onClick="index.replyDelete(${question.id},${reply.id})">&nbsp;삭제</button>
							</c:if>
						</div>
						<div id="reply_content">
							<h4>&nbsp;&nbsp;${reply.content}</h4>
						</div>

					</div>
				</div>
			</c:forEach>
            <div id="reply_box">
				<form>
					<input type="hidden" id="questionId" value="${question.id}">
					<div id="reply_body">
						<textarea id="reply-content" class="from_control" rows="5"
							cols="100"></textarea>
					</div>
				</form>

				<button type="button" id="btn-reply-save">등록</button>

			</div>
            <c:choose>
              <c:when test="${question.user.id==principal.user.id or principal.user.roles=='ADMIN'}">
				
				<div id="board_button">
					<a href="/question/${question.id}/questionUpdateForm"><button>수정</button></a>
					<button id="btn-question-delete">삭제</button>

				</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
		   </c:choose>

		</div>

	</div>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
	<script type="text/javascript" src="/js/question.js"></script>
</body>
</html>