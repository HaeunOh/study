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
	<h1>Board modify page</h1>
	<form action="/board/modify" method="post">
			<div class="mb-3">
				<label for="n" class="form-label">bno</label> 
				<input type="text" class="form-control" name="bno" value= "${bvo.bno}" id="n" placeholder="bno..." readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="t" class="form-label">title</label> 
				<input type="text" class="form-control" name="title" value= "${bvo.title}"id="t" placeholder="title..." >
			</div>
			<div class="mb-3">
				<label for="w" class="form-label">writer</label> 
				<input type="text" class="form-control" name="writer" value= "${bvo.writer }"id="w" placeholder="wrtier..." readonly="readonly">
			</div>
			<div class="mb-3">
				<label for="r" class="form-label">reg_date</label> 
				<input type="text" class="form-control" name="reg_date" value= "${bvo.reg_date}" id="r" placeholder="reg_date" readonly="readonly"> 
			</div>
			<div class="mb-3">
  			<span class="input-group-text">With textarea</span>
  			<textarea class="form-control"  name="content" id="c" aria-label="With textarea">"${bvo.content}"</textarea>
			</div>
			<button type="submit" class="btn btn-warning">수정</button>
			<a><button type="button" class="btn btn-primary">리스트</button></a>
			</form>
	
	</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>