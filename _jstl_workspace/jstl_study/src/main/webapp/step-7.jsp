<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
    //post방식의 한글처리
    request.setCharacterEncoding("utf-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>주문서</h1>
 	<h3>
 	주문자명 : ${param.name } </h3> <br>
 	<h3> 주문한 메뉴 </h3>
 	<c:forEach items="${paramValues.food }" var="orderName" varStatus="i">
 		${i.count}. ${orderName }<br>
 	</c:forEach>
 
 	<a href="step-8.jsp"> step-8.jsp </a>
</body>
</html>