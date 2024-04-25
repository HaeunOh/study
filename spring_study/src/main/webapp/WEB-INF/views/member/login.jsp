<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container-md">
	<jsp:include page="../layout/header.jsp" />
	<h1>Member login page</h1>
	
	<form action="/member/login" method="post">
		<div class="mb-3">
			<label for="i" class="form-label">id</label> <input type="text"
				class="form-control" name="id" id="i" placeholder="id...">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">pw</label> <input type="text"
				class="form-control" name="pw" id="p" placeholder="password...">
		</div>
		
		<button type="submit" class="btn btn-primary">로그인</button>
		</form>
		
	<jsp:include page="../layout/footer.jsp" />
	</div>
	