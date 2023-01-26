<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="/css/joinForm.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<div class="body">
    <div class="container">
    <div class="logo-img"><img src="/img/logo-black.png" alt="My Image"></div>
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-body">
					<form role="form">
						<div class="form-group">
							<h2>회원가입</h2>
						</div>
						<div class="form-group">
							<label class="control-label" for="signupPasswordagain">아이디</label>
							<input id="userid" type="text" maxlength="25" class="form-control">
						</div>
						<div class="form-group">
							<label class="control-label" for="signupName">이름</label>
							<input id="username" type="text" maxlength="50" class="form-control">
						</div>
						<div class="form-group">
							<label class="control-label" for="signupEmail">이메일</label>
							<input id="email" type="email" maxlength="50" class="form-control">
						</div>
					    <div class="form-group">
							<label class="control-label" for="signupPassword">비밀번호</label>
							<input id="password" type="password" maxlength="25" class="form-control" length="40">
						</div>
						<div class="form-group">
							<button type="submit" type="submit" id="register-btn-save" value="Join" class="btn btn-info btn-block">가입하기</button>
						</div>
						<p class="form-group">By creating an account, you agree to our <a href="#">Terms of Use</a> and our <a href="#">Privacy Policy</a>.</p>
						<hr>
						<p></p>Already have an account? <a href="#">Sign in</a></p>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="/js/user.js"></script>
