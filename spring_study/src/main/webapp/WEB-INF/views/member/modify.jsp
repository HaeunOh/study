<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="container-md">
	<jsp:include page="../layout/header.jsp" />
	<h1>Member modify page</h1>

	<form action="/member/modify" method="post">
		<div class="mb-3">
			<label for="i" class="form-label">ID</label> <input type="text"
				class="form-control" name="id" id="i" value="${ses.id }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="r" class="form-label">가입일</label> <input type="text"
				class="form-control" name="reg_date" id="r" value="${ses.reg_date }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="r" class="form-label">마지막 접속일자</label> <input type="text"
				class="form-control" name="last_login" id="l" value="${ses.last_login }" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="p" class="form-label">PW</label> <input type="text"
				class="form-control" name="pw" id="p" placeholder="password...">
		</div>
		<div class="mb-3">
			<label for="n" class="form-label">Name</label> <input type="text"
				class="form-control" name="name" id="n" value="${ses.name }">
		</div>
		<div class="mb-3">
			<label for="a" class="form-label">Age</label> <input type="text"
				class="form-control" name="age" id="a" value="${ses.age }">
		</div>
		<div class="mb-3">
			<label for="h" class="form-label">Home</label> <input type="text"
				class="form-control" name="home" id="h" value="${ses.home }">
		</div>
		<div class="mb-3">
			<label for="e" class="form-label">Email</label> <input type="email"
				class="form-control" name="email" id="e" value="${ses.email }">
		</div>
		
		<button type="submit" class="btn btn-primary">수정</button>
		<a href="/member/remove?id=${ses.id }"><button type="button" class="btn btn-danger">회원탈퇴</button></a>
	</form>



	<jsp:include page="../layout/footer.jsp"></jsp:include>
</div>
    