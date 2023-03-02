<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/common.jsp"%>
<c:if test="${empty principal}">
    <%@ include file="not_login.jsp"%>
</c:if>
<c:if test="${!empty principal}">
    <%@ include file="after_login.jsp"%>
</c:if>