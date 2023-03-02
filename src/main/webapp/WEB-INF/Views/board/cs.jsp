<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="../layout/common.jsp"%>
    <%@ include file="../layout/header.jsp"%>

       
       
        <!-- DataTable Styles -->
        <link rel="stylesheet" href="../css/style.css">
        <!-- Demo Styles -->
        <link rel="stylesheet" href="../css/demo.css">
        <link rel="stylesheet" href="../css/question.css">
    </head>
    <body>
        <div class="banner">
          <img class="slide1" src="../img/board-background.jpg">
          <img class="slide1" src="../img/board-background2.jpg">
          <img class="slide1" src="../img/board-background3.jpg">
        </div>
        <div id="body">
        <h1 id="h1">공지사항</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th data-type="date" data-format="YYYY/DD/MM">작성일</th>
                    <th>조회</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="board" items="${boards.content}">
		        <tr>
		            <td>${board.id}</td>
		            <td class="tit"><a href="/board/${board.id}">${board.title}</a></td>
		            <td>${board.user.username}</td>
		            <td><fmt:formatDate pattern = "yyyy/MM/dd" value="${board.createDate}"/></td>
		            <td>${board.count}</td>
		        </tr>                  
		        </c:forEach>
                <c:forEach var="board" items="${board}">
		        <tr>
		            <td>${board.id}</td>
		            <td class="tit"><a href="/board/${board.id}">${board.title}</a></td>
		            <td>${board.user.username}</td>
		            <td><fmt:formatDate pattern = "yyyy/MM/dd" value="${board.createDate}"/></td>
		            <td>${board.count}</td>
		        </tr>      
		        </c:forEach>

            </tbody>
        </table>
        </div>
        <div class="write">
           	<c:if test="${principal.user.roles=='ADMIN'}">
                <a href="/board/saveForm"><button onclick="">글쓰기</button></a>
            </c:if>
        </div>
        <!-- CDN -->
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"></script>
        <!-- Custom Code -->
        <script>
            new window.simpleDatatables.DataTable("table")
        </script>
        <script src="../js/cs.js"></script>
    </body>
</html>
