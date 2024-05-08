<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class="container-md">
	<jsp:include page="../layout/header.jsp" />
	<h1>Member modify page</h1>
	<h5> ✓ 닉네임과 비밀번호만 변경 가능합니다.</h5>
	<form action="/user/modify" method="post">
	 <sec:authentication property="principal.uvo.email" var="authEmail"/>
     <sec:authentication property="principal.uvo.nickName" var="authNick"/>
     <sec:authentication property="principal.uvo.regDate" var="regDate"/>
     <sec:authentication property="principal.uvo.lastLogin" var="lastLogin"/>
		<div class="mb-3">
			<label for="e" class="form-label">Email</label> 
			<input type="email" class="form-control" name="email" id="e" value="${authEmail }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">nickName</label> 
			<input type="text" class="form-control" name="nickName" id="n" value="${authNick }">
		</div>
		
		<div class="mb-3">
			<label for="p" class="form-label">pwd</label> 
			<input type="text" class="form-control" name="pwd" id="p" placeholder="변경할 비밀번호를 입력하세요..." >
		</div>
		
		<div class="mb-3">
			<label for="r" class="form-label">regDate</label> 
			<input type="text" class="form-control" name="regDate" id="r" value="${regDate }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="r" class="form-label">lastLogin</label>
			 <input type="text" class="form-control" name="lastLogin" id="l" value="${lastLogin }" readonly="readonly">
		</div>
		<button type="submit" class="btn btn-primary">수정</button>
		<a href="/user/remove?email=${authEmail }"><button type="button" class="btn btn-danger">회원탈퇴</button></a>
	</form>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</div>
    