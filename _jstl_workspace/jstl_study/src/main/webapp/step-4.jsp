<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2> form tag로 전송한 파라미터 받기 </h2>
	<!-- EL 태그 방식은 내부적으로 형변환을 하여 연산이 가능함. -->
	이름 : ${param.name } <br>	
	나이 : ${param.age } <br>
	
	<!-- 스크립틀릿 방식으로 파라미터 가져오기 -->
	<!-- 스크립틀릿 방식은 값을 문자열로 인식, 연산 불가능. 형변환 필요!! -->
	<%= request.getParameter("name") %> <br>
	<%= request.getParameter("age") %>
	
	<hr>
	<!-- c:if 이용하여 나이가 18세 이하이면 미성년자입니다 라고 출력!! -->
	<c:if test="${param.age <= 18 }">
		<h3>미성년자입니다.</h3>
	</c:if>
	
	<hr>
	
	<!-- choose를 사용하여 다중조건 처리 -->
	<!-- age가 19세 이상이면 성인, 15세 이상이면 청소년, 5세 이상이면 어린이, 나머지는 유아 -->
		<!-- c:when (case) / c:otherwise (default) -->
		<!-- choose문 안에 주석이 있으면 오류날 수 있음. -->
	<c:choose>
		<c:when test="${param.age >= 19 }">
		<h3>${param.name }님은 ${param.age }세, 성인입니다.</h3>
		</c:when>
		<c:when test="${param.age >= 15 }">
		<h3>${param.name }님은 ${param.age }세, 청소년입니다.</h3>
		</c:when>
		<c:when test="${param.age >= 5 }">
		<h3>${param.name }님은 ${param.age }세, 어린이입니다.</h3>
		</c:when>
		<c:otherwise>
		<h3>${param.name }님은 ${param.age }세, 유아입니다.</h3>
		</c:otherwise>
	</c:choose>
	
	<h2>formtag -> checkbox를 이용하여 데이터 전송하기</h2>
	<form action="step-5.jsp">
		<input type="checkbox" name="food" value="바나나"> 바나나 <br>
		<input type="checkbox" name="food" value="딸기"> 딸기 <br>
		<input type="checkbox" name="food" value="귤"> 귤 <br>
		<input type="checkbox" name="food" value="사과"> 사과 <br>
		<input type="checkbox" name="food" value="복숭아"> 복숭아 <br>
		<button type="submit">전송</button>
	</form>
	
	
	
	
	
	
</html>