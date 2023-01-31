<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <!-- CSS only -->
  <!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="css/header.css" rel="stylesheet">
<script src="js/header.js"></script> 
<link href="css/test1.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<nav class="navbar navbar-dark navbar-expand-lg bg-black fixed"><!--1296px-->
  <div class="container">
    <a class="navbar-brand" href="/"><img src="img/logo(black).png" style="width:90px;height:90%;"></img></a>
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
            <li><a class="dropdown-item" href="#">수입</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">캘린더</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">보고서</a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">월 보고서</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">연간 보고서</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">지출분석</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link " aria-current="page" href="#">게시판</a>
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
            <li><a class="dropdown-item" href="#">카카오 로그인</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">페이스북 로그인</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="auth/joinForm">회원가입</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">비밀번호 찾기</a></li>
          </ul>
        </li>
       </ul>
       </c:if>
       <c:if test="${!empty principal}">
      <a class="navbar-nav" href="/logout" style="text-decoration:none;color:rgba(255,255,255,0.55)">로그아웃</a>
	  </c:if>
    </div>
  </div>
</nav>
