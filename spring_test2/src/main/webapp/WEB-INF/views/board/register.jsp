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
		<form action="/board/insert" method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="t" class="form-label">title</label> 
				<input type="text" class="form-control" name="title" id="t" placeholder="title...">
			</div>
			<div class="mb-3">
				<label for="w" class="form-label">writer</label> 
				<input type="text" class="form-control" name="writer" id="w" placeholder="wrtier...">
			</div>
			<div class="mb-3">
  			<span class="input-group-text">With textarea</span>
  			<textarea class="form-control"  name="content" id="c" aria-label="With textarea"></textarea>
			</div>
			
				<!-- 파일 입력 라인 추가 -->
				<div class="mb-3">
				<label for="file" class="form-label">files</label> 
				<input type="file" class="form-control" name="files" id="file" multiple="multiple" style="display : none">
				<!-- multiple > 여러개의 파일 업로드 가능 -->
				<br>
				<button type="button" class="btn btn-secondary" id="trigger">FileUpload</button>
				<!-- file 표시되는 영역 -->
				<div class="mb-3" id="fileZone">
				
				</div>
			</div> 
			<button type="submit" class="btn btn-primary" id="regBtn">등록</button>
		</form>
	</div>
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>