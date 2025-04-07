<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
<%@include file="/WEB-INF/views/include/header.jsp" %>
</head>
<body>
<form action="${root}/notice/regist" method="post">
<input type="text" placeholder="제목" required = "required" name="title">
<br>
<input type="text" placeholder="내용" required="required" name="content">
<input type="submit" value="등록">
</form>
</body>
</html>