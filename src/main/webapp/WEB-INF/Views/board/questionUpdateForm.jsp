<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div id=container>
		<div id="Inquiry_wrap">
			<div class="Inquiry_wrap_title">문의사항 수정하기</div>

			<form action="" method="" id="Inquiry_form">
				<div>
					<input type="hidden" id="id" value="${question.id}">
					제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text"
						name="Inquiry_title" id="title" size="50" maxlength="100"
						value="&nbsp;${question.title}">
				</div>

				<div>
					이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text"
						name="Inquiry_name" id="user" size="15" maxlength="100"
						value="&nbsp;${question.user.username}" disabled>
				</div>
				<div id="Inquiry_content">
					<div>문의내용&nbsp;&nbsp;</div>
					<textarea id="contents" rows="18" cols="128">${question.contents}</textarea>
				</div>

				</ul>
			</form>
			<div id="Inquiry_submit">
				<button id="btn-question-update">수정</button>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
	<script type="text/javascript" src="/js/board.js"></script>

</body>
</html>