<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>step3</title>
</head>
<body>
	<h1>step2에서 보낸 데이터</h1>	
	상품명 : ${param.name } <br>
	원산지 : ${param.addr } <br>
	<hr>
	<h2>
		1. 스크립틀릿 방식으로 파라미터 받기 <br>
		<!-- 잘 쓰지 않는 방식...  -->
		<%= request.getParameter("name") %> <br>
		<%= request.getParameter("addr") %> <br>
		<!-- < %= request.setAttribute(name, o)%> -->
	</h2>
	<hr>
	<h2>
		2. EL 방식으로 파라미터 받기 <br>
		${param.name } <br>
		${param.addr } <br>
	</h2>
	<hr>
	<!-- form tag로 데이터 전송 -->
	<!-- queryString 방식의 전송은 get방식으로 전송 -->
	<!-- form tag 전송방식은 선택 가능, get/post 방식 중 선택 가능 -->
	<h2>form tag로 데이터 전송하기</h2>
	<form action="step-4.jsp">
	이름 : <input type="text" name="name"> <br>
	나이 : <input type="text" name="age"> <br>
	<button type="submit">전송</button>
	</form>
	
	
	
	
	
	
	
	
</body>
</html>