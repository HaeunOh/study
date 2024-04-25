<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div  class="container-md">
<h1>
	Hello world!  
</h1>
	
	<c:if test="${ses.id ne null }">
	
		<p> ${ses.id} 님이 로그인하셨습니다! 
			<span class="badge text-bg-info">${ses.last_login}</span>
		</p>
	</c:if>

	<script type="text/javascript">
		 const msg_login = `<c:out value="${msg_login}"/>`;
		 if(msg_login === "1"){
			alert("로그인 실패!");
		 }
		 
		 const msg_logout = `<c:out value="${msg_logout}"/>`;
		 if(msg_logout === "1"){
			 alert("로그아웃 되었습니다.");
		 }
	</script>
<jsp:include page="layout/footer.jsp"/>

</div>