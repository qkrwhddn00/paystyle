<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
  Date nowTime = new Date();
  SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
%>
  <link href="css/header.css" rel="stylesheet">
  <link href="css/test1.css" rel="stylesheet">
<nav class="navbar navbar-dark navbar-expand-lg bg-black fixed"><!--1296px-->
  <div class="container">
    <a class="navbar-brand" href="/"><img src="../img/logo(black).png" style="width:90px;height:90%;"></img></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            쓰기
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/withdraw">지출</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/deposit">수입</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/calendar">캘린더</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">보고서</a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">월 보고서</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/annualReport">연간 보고서</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/analysis">지출분석</a></li>
          </ul>
        </li>
         <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">게시판</a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/inquire">게시판</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/board/csboard">공지사항</a></li>
            
          </ul>
        </li>
      </ul>
      <c:if test="${empty principal}">
      <form class="d-flex" role="login" action="/auth/loginProc" method="POST">
        <input class="form-control me-2" tpye="email" placeholder="이메일주소"  id="userid" name="userid" required>
        <input class="form-control me-2" type="password" placeholder="비밀번호" id="password" name="password" required>
        <button class="btn btn-outline-success" type="submit" style="margin-right:10px;">login</button>
      </form>
      
      
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            더보기
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="https://kauth.kakao.com/oauth/authorize?client_id=8389bb661384d76130a94d659e880757&redirect_uri=http://localhost:8010/auth/kakao/callback&response_type=code">카카오 로그인</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">페이스북 로그인</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="auth/joinForm">회원가입</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="auth/findId">아이디 찾기</a></li>
          </ul>
        </li>
       </ul>
       </c:if>
       <c:if test="${!empty principal}">
      <a class="navbar-nav" href="/logout" style="text-decoration:none;color:rgba(255,255,255,0.55)">로그아웃&nbsp;</a>
      <a class="navbar-nav" href="/userUpdate" style="text-decoration:none;color:rgba(255,255,255,0.55)">회원정보&nbsp;</a>
      <sec:authorize access="hasRole('ROLE_USER')">
       <a class="navbar-nav" style="text-decoration:none;color:rgba(255,255,255,0.55)">${principal.user.username} 유저님&nbsp;</a>
       </sec:authorize>
       <sec:authorize access="hasRole('ROLE_ADMIN')">
       <a class="navbar-nav" style="text-decoration:none;color:rgba(255,255,255,0.55)">${principal.user.username} 관리자님&nbsp;</a>
       </sec:authorize>
	  </c:if>
    </div>
  </div>
</nav>
