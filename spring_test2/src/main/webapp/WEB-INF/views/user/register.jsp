<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-md">
	<jsp:include page="../layout/header.jsp" />
	<h1>Member register page</h1>

	<form action="/user/register" method="post">
		<div class="mb-3">
			<label for="e" class="form-label">email</label> <input type="email"
				class="form-control" name="email" id="e" placeholder="example@test.com...">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">pw</label> <input type="text"
				class="form-control" name="pwd" id="p" placeholder="password...">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">nickName</label> <input type="text"
				class="form-control" name="nickName" id="n" placeholder="name...">
		</div>
		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</div>
