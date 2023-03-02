<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../layout/common.jsp"%>
<%@ include file="../layout/header.jsp"%>

      <form action="/auth/loginProc" method="POST" class="login-form" >
          <input type="text" placeholder="ID" id="userid" name="userid">
          <input type="password" placeholder="PASSWORD" id="password" name="password">
        <button type="submit" id="btn-login">LOGIN</button>
      </form>

