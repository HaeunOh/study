<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board modify Page</h1>
	<form action="/brd/update" method="post">
	<table border="1">
		<tr>
			<th>bno</th>
			<td><input type="text" name="bno" value="${bvo.bno}" readonly="readonly"></td>
		</tr>
		<tr>
			<th>titile</th>
			<td><input type="text" name="title" value="${bvo.title }"></td>
		</tr>
		<tr>
			<th>writer</th>
			<td>${bvo.writer }</td>
		</tr>
		<tr>
			<th>regdate</th>
			<td>${bvo.regdate }</td>
		</tr>
		<tr>
			<th>moddate</th>
			<td>${bvo.moddate }</td>
		</tr>
		<tr>
			<th>content</th>
			<td> <textarea rows="10" cols="30" name="content"> ${bvo.content } </textarea> </td>
		</tr>
	</table>
	<button type="submit">확인</button>
	</form>
	<br>
	<a href="/brd/list"> <button>돌아가기</button> </a>
</body>
</html>