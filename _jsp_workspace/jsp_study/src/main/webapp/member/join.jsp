<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>join page</h1>
	<form action="/memb/register" method="post">
	id <input type="text" name="id"> <br>
	pw <input type="password" name="pwd"> <br>
	age <input type="text" name="age"> <br>
	email <input type="text" name="email"> <br>
	phone <input type="text" name="phone"> <br>
	<button type="submit">회원가입</button>
	</form>
</body>
</html>