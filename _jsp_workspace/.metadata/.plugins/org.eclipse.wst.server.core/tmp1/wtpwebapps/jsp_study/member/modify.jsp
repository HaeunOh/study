<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>mypage</h1>
		<form action="/memb/update" method="post">
		<table border="1">
		<tr>
			<th>id</th>
			<td><input type="text" name="id" value="${mvo.id}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>pwd</th>
			<td><input type="text" name="pwd" value="${mvo.pwd}"></td>
		</tr>
		<tr>
			<th>age</th>
			<td><input type="text" name="age" value="${mvo.age}"></td>
		</tr>
		<tr>
			<th>email</th>
			<td><input type="text" name="email" value="${mvo.email}"></td>
		</tr>
		<tr>
			<th>phone</th>
			<td><input type="text" name="phone" value="${mvo.phone}"></td>
		</tr>
		<tr>
			<th>가입일</th>
			<td>${mvo.regdate }</td>
		</tr>
		<tr>
			<th>최근로그인</th>
			<td>${mvo.lastlogin }</td>
		</tr>
	</table>
	<button type="submit">수정하기</button>
	</form>
	<a href="/memb/delete?id=${mvo.id }"> <button type="submit">회원탈퇴</button> </a>
</body>
</html>