<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
</head>
<body>
	<c:if test="${!empty list}">
		<div>
			<table>
				<tr>
					<th>번호</th>
					<th>등록한사람</th>
					<th>제목</th>
					<th>내용</th>
				</tr>
				<c:forEach var="notice" items="${list}">
					<tr>
						<td>${notice.notice_id}</td>
						<td>${notice.user_id}</td>
						<td>${notice.title}</td>
						<td>${notice.content}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	<a href="${root}/notice/regist">추가 등록</a>
</body>
</html>
