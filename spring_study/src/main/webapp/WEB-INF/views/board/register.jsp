<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
	<h1>Board register page</h1>
		<form action="/board/insert" method="post">
			<div class="mb-3">
				<label for="t" class="form-label">title</label> 
				<input type="text" class="form-control" name="title" id="t" placeholder="title...">
			</div>
			<div class="mb-3">
				<label for="w" class="form-label">writer</label> 
				<input type="text" class="form-control" name="writer" id="w" value="${ses.id }" placeholder="wrtier...">
			</div>
			<div class="mb-3">
  			<span class="input-group-text">With textarea</span>
  			<textarea class="form-control"  name="content" id="c" aria-label="With textarea"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">등록</button>
		</form>
	</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>