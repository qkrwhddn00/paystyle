<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="/css/joinForm.css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!------ Include the above in your HEAD tag ---------->
<div class="body">
    <div class="container">
    <div class="logo-img"><img src="/img/logo-black.png" alt="My Image"></div>
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-body">
					<form role="form" name="joinForm">
						<div class="form-group">
							<h2>회원가입</h2>
						</div>

						<div class="form-group">
							<label class="control-label" for="signupPasswordagain">아이디</label>
							<input id="userid" name="userId" type="text" maxlength="25" class="form-control" oninput="ajaxTest()">
							<!-- userid 중복여부 메세지창 -->
							<div id="Context"></div>		                
						</div>
						<div class="form-group">
							<label class="control-label" for="signupName">이름</label>
							<input id="username" name="name" type="text" maxlength="50" class="form-control">
						</div>
						<div class="form-group">
							<label class="control-label" for="signupEmail">이메일</label>
							<input id="email" name="mail" type="email" maxlength="50" class="form-control">
						</div>
					    <div class="form-group">
							<label class="control-label" for="signupPassword">비밀번호</label>
							<input id="password" name="password1" type="password" maxlength="25" class="form-control" length="40">
						</div>
					    <div class="form-group">
							<label class="control-label" for="signupPassword">비밀번호확인</label>
							<input  type="password" name="password2" maxlength="25" class="form-control" length="40">
						</div>
						<div class="form-group">
							<input type="button" onclick="checkAll();" id="register-btn-save" value="가입하기" class="btn btn-info btn-block">
						</div>
						<p class="form-group">By creating an account, you agree to our <a href="#">Terms of Use</a> and our <a href="#">Privacy Policy</a>.</p>
						<hr>
						<p></p>Already have an account? <a href="/">Sign in</a></p>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="/js/useridCheck.js"></script>
	<script type="text/javascript" src="/js/joinFormValid.js"></script>
	<script type="text/javascript" src="/js/user.js"></script>

