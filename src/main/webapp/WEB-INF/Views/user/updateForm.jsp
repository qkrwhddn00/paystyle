<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../layout/common.jsp"%>
    <%@ include file="../layout/header.jsp"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
body{background-color: #f4f4f4; font-size: small; color: rgba(100,100,100, 0.5); }
div.row {
min-width: 300px;
position:  relative;
border-top: rgba(100,100,100,0.03) solid;
}
div#wrap{
min-width: 500px;
margin: 50px auto;
float: left;
background-color: #fff;
border-radius:5%;
padding: 10px;
position: absolute;
left: 30%;
top:30%;


}
#register-btn-delete{
position:  relative;
left: 350px;
}
</style>
<div id="wrap" style="margin-left: 50px;">
<p style="text-align: center;font-size: medium;">회원정보</p>
<form>
	<input type="hidden" value="${principal.user.id}" id="id">
  <div class="form-group row">
    <label for="userid" class="col-sm-2 col-form-label">아이디</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="userid" value="${principal.user.userid}">
    </div>
  </div>
  <div class="form-group row">
    <label for="username" class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="username" value="${principal.user.username}" >
    </div>
  </div>
  <div class="form-group row">
    <label for="email" class="col-sm-2 col-form-label">이메일</label>
    <div class="col-sm-10">
      <input type="text" class="form-control-plaintext" id="email" value="${principal.user.email}">
    </div>
  </div>
 
  <div class="form-group row">
    <label for="password" class="col-sm-2 col-form-label">비밀번호</label>
    <div class="col-sm-10">
      <input type="password" class="form-control-plaintext" id="password" placeholder="비밀번호를 재설정해주세요">
    </div>
  </div>
  <div class="form-group row">
    <label for="createDate" class="col-sm-2 col-form-label">생성일</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="createDate" value=" <fmt:formatDate value="${principal.user.createDate}" pattern="yyyy-MM-dd"/> ">
    </div>
  </div>
</form>
<button class="btn btn-light" type="submit" id="register-btn-update">수정</button>
<button class="btn btn-light" type="button" id="register-btn-delete">삭제</button>
</div>
<script type="text/javascript" src="/js/user.js"></script>