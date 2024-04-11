<%@ page import="java.util.ArrayList"%>
<%@ page import="jstl_study.CarVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		CarVO car = new CarVO("1234","소나타", 2000);
		request.setAttribute("car", car);
	%>
	
	<!-- car 객체 출력 -->
	${requestScope.car.name } <br>
	${car.num } <br>
	${car.price} <br>
	
	<%
		ArrayList<CarVO> list = new ArrayList<>();
		list.add(car);
		list.add(new CarVO("1111","그랜저",3000));
		list.add(new CarVO("2222","모닝",1500));
		//request.setAttribute("list", list);
		session.setAttribute("list", list);
		//session에 저장하면 다른 페이지에서도 사용 가능!!
	%>
	
	<c:forEach items="${list}" var="carList" varStatus="i">
		 ${i.count}. ${carList.name } / ${carList.num } / ${carList.price }만원 <br><hr>
	</c:forEach>
	
</body>
</html>