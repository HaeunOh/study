<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
	<h1>Board modify page</h1>
	<%-- <c:set value="${bdto.bvo}" var="bvo"></c:set> --%>
	 <c:set value="${bvo}" var="bvo"></c:set>
	<form action="/board/modify" method="post" >
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
				<input type="text" class="form-control" name="reg_date" value= "${bvo.regDate}" id="r" placeholder="reg_date" readonly="readonly"> 
			</div>
			<div class="mb-3">
  			<span class="input-group-text">With textarea</span>
  			<textarea class="form-control"  name="content" id="c" aria-label="With textarea">${bvo.content}</textarea>
			</div>
			
			<!-- 파일 표시 -->
				<%-- <c:set value="${bdto.flist}" var="flist"></c:set>
		<div class="mb-3">
			<ul class="list-group list-group-flush">
			<!-- 파일 개수만큼 li를 반복하여 파일 표시 / 타입이 1인 경우만 -->
			<!-- li > div > img -->
			<!--    > div > 파일이름, 작성일, 사이즈, span size -->
			<c:forEach items="${flist }" var="fvo">
				<li class="list-group-item">
					<c:choose>
						<c:when test="${fvo.file_type > 0 }">
							<div>
								<img alt="" src="/upload/${fvo.save_dir }/${fvo.uuid}_${fvo.file_name}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<!-- file_type == 0 인 경우 -->
								
							</div>
						</c:otherwise>
					</c:choose>
					<div>
						<!-- 파일이름, 작성일, 사이즈, span size -->
						<div>${fvo.file_name }</div>
						${fvo.reg_date }
						<span class="badge text-bg-warning">${fvo.file_size }byte</span>
						<button type="button" data-uuid="${fvo.uuid }" class="btn btn-danger btn-sm file-x">X</button>
					</div>
				</li>
			</c:forEach>
  			</ul>
  			</div> --%>
  			<!-- 파일 추가 -->
  			<!-- 파일 입력 라인  -->
			<!-- <div class="mb-3">
				<label for="file" class="form-label">파일 추가하기</label> 
				<input type="file" class="form-control" name="files" id="file" multiple="multiple" style="display : none">
				multiple > 여러개의 파일 업로드 가능
				<br>
				<button type="button" class="btn btn-secondary" id="trigger">FileUpload</button>
				file 표시되는 영역
				<div class="mb-3" id="fileZone">
				
				</div>
			 -->
			<button type="submit" class="btn btn-warning" id="regBtn">수정</button>
			<a><button type="button" class="btn btn-primary">리스트</button></a>	
			</form>
		
			
	
	</div>

<!-- 	<script type="text/javascript" src="/resources/js/boardModify.js"></script>
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script> -->
	<jsp:include page="../layout/footer.jsp"></jsp:include>