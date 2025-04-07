<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<link rel="shortcut icon" href="img/favicon.ico">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/apt.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!DOCTYPE html>

<header id="index_header" class="jumbotron text-center mb-1">
	<a href="/${root}"><img id="logo" src="/img/happyhouse.png"></a>
</header>
<div align="center">
	<c:if test="${empty userinfo}">
		<a href="${root}/user/register">회원가입</a>
		<br />
		<a href="${root}/user/login">로그인</a>
		<br />
	</c:if>
	<c:if test="${!empty userinfo}">
		<strong>${userinfo.userName}</strong> (${userinfo.userId})님 안녕하세요.<br />
		<a href="${root}/user/logout">로그아웃</a>
		<br />
	</c:if>
	<c:if test="${userinfo.userId eq 'admin'}">
		<a href="${root}/user/list">회원목록</a>
		<br />
	</c:if>

</div>
