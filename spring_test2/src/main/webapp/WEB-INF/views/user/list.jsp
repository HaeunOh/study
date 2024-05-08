<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<meta charset="UTF-8">
<title>Insert title here</title>
	<div class="container-md">
<jsp:include page="../layout/header.jsp" />
	<h1>User list page</h1>
		<table class="table table-bordered">
			<thead>
			<tr>
				<th>email</th>
				<th>nickName</th>
				<th>regDate</th>
				<th>lastLogin</th>
				<th>auth</th>
			</tr>
		</thead>
		<tbody>
			 <c:forEach items="${list }" var="uvo">
				<tr>
					<td>${uvo.email}</td>
					<td>${uvo.nickName}</td>
					<td>${uvo.regDate}</td>
					<td>${uvo.lastLogin}</td>
					<c:forEach items="${uvo.authList }" var="auth">
					<td>${auth.auth }</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
<jsp:include page="../layout/footer.jsp" />
