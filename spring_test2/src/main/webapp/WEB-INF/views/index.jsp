<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>
	<div class="container-md">
		<h1>
		 my first spring project
		</h1>
		<br>
	</div>
	<script>
	 const msg_remove = `<c:out value="${deleteMsg}}"/>`;
	 if(msg_remove === "1"){
		 alert("회원 탈퇴가 완료되었습니다.");
	 }
	</script>
<jsp:include page="layout/footer.jsp"/>