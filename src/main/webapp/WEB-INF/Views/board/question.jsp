<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="../layout/common.jsp"%>
    <%@ include file="../layout/header.jsp"%>

        
        <link rel="stylesheet" href="../css/style.css">
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
        <h1 id="h1">게시판</h1>
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
            <c:forEach var="question" items="${questions.content}">
                <tr>
                    <td>${question.id}</td>
                    <td class="tit"><a href="/question/${question.id}">${question.title}</a></td>
                    <td>${question.user.username}</td>
                    <td><fmt:formatDate pattern = "yyyy/MM/dd" value="${question.createDate}"/></td>
                    <td>${question.count}</td>
                </tr>         
            </c:forEach>
            </tbody>
        </table>
          <div id="write">
        	  <a href="/board/questionSaveForm" class="btn btn-default" style= color="white">작성하기</a>
          </div>
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
