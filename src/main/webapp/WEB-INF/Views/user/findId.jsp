<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="/css/joinForm.css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!------ Include the above in your HEAD tag ---------->
<div class="body">
    <div class="container">
    <div class="logo-img"><img src="/img/logo-black.png" alt="My Image"></div>
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-body">
					<form role="form">
						<div class="form-group">
							<h2>아이디찾기</h2>
						</div>
						<c:if test="${email==null}">
						<div class="form-group emailInput">
							<label class="control-label" for="signupEmail">이메일</label>
							<input id="email" type="email" maxlength="50" class="form-control">
						</div>
						</c:if>
						<c:forEach items="${email}" var="email">
						<label class="control-label" for="signupEmail">입력한 이메일</label>
						<input type="text" value="${email.email}" maxlength="50" class="form-control emailOutput">
						
						<div class="form-group">
							<label class="control-label" for="signupPasswordagain">아이디</label>
							<input type="text" maxlength="25" class="form-control" value="${email.userid}">
							
						</div>
						
						</c:forEach>
						<div class="form-group">
							<input type="button" onclick="findId();" value="아이디찾기" class="btn btn-info btn-block">
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
	 <script type="text/javascript">
	   function findId(){
		  
		     
	   	   let email = $("#email").val();
	  	   location.href = '/auth/findId/' + email ;
	   	if($(".emailInput").css("display") == "none"){
	   	
	   	    $(".emailInput").hide(); 
	   	}
  	
	   }
	   </script>
