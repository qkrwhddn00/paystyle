<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
   <%@ include file="../layout/common.jsp"%>
    <%@ include file="../layout/header.jsp"%>
    
<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/detail.css">
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


	<div id="container">
		<div id="board_wrap">
			<div class="board_wrap_title">공지 사항</div>
			<div class="board_title">&nbsp; ${board.title}</div>
			<div class="board_subtitle">
				<div>
					&nbsp;&nbsp;글번호 : <span id="id">${board.id}</span> &nbsp; 작성자 :
					${board.users}
				</div>
				<div class="board_subtitle_etc">

					<div>&nbsp;&nbsp;</div>
					<div>조회수&nbsp;${board.count}</div>
					<div>
						작성일 &nbsp;
						<fmt:formatDate pattern="yyyy/MM/dd" value="${board.createDate}" />
					</div>
				</div>
			</div>
			
			
			<div class="board_content_wrap">
				<div class="board_content"><pre>${board.contents}</pre></div>
			</div>

			<c:if test="${principal.user.roles=='ADMIN'}">
				<div id="board_button">
					<a href="/board/${board.id}/updateForm"><button>수정</button></a>
					<button id="btn-delete">삭제</button>

				</div>
			</c:if>

		</div>

	</div>


	<%@ include file="../layout/footer.jsp"%>
	<script type="text/javascript" src="/js/board.js"></script>


</body>
</html>