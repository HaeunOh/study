<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  메뉴판을 체크박스 형태로 만들어서 step7로 전송 -->
	<!--  step7에서 주문자명과 주문메뉴 출력! --> 
	<form action="step-7.jsp" method="post">
	<h1>메뉴판</h1>
	주문자명 : <input type="text" name="name"> <br>
	<input type="checkbox" name="food" value="부대찌개"> 부대찌개 <br>
	<input type="checkbox" name="food" value="김치찌개"> 김치찌개 <br>
	<input type="checkbox" name="food" value="순두부찌개"> 순두부찌개 <br>
	<input type="checkbox" name="food" value="사이다"> 사이다 <br>
	<input type="checkbox" name="food" value="콜라"> 콜라 <br>
	<button type="submit">주문하기</button>
	</form>
	
</body>
</html>