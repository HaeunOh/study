<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
		<h1>Board detail page</h1>
		<c:set value="${bvo }" var="bvo"></c:set>
		<div class="mb-3">
			<label for="n" class="form-label">bno</label> <input type="text"
				class="form-control" name="bno" value="${bvo.bno}" id="n"
				placeholder="bno..." readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="t" class="form-label">title</label> <input type="text"
				class="form-control" name="title" value="${bvo.title}" id="t"
				placeholder="title..." readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="w" class="form-label">writer</label> <input type="text"
				class="form-control" name="writer" value="${bvo.writer }" id="w"
				placeholder="wrtier..." readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="r" class="form-label">reg_date</label> <input type="text"
				class="form-control" name="reg_date" value="${bvo.regDate}" id="r"
				placeholder="reg_date" readonly="readonly">
		</div>
		<div class="mb-3">
			<span class="input-group-text">With textarea</span>
			<textarea class="form-control" name="content" id="c"
				aria-label="With textarea">"${bvo.content}"</textarea>
		</div>
		
		<!-- image 파일 띄우는 곳 -->
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
					</div>
				</li>
			</c:forEach>
  			</ul> --%>
		</div>
		<!-- comment -->
		<!-- 댓글 등록 라인  -->
		<%-- <br>
		<hr>
		<div class="input-group mb-3">
			<span class="input-group-text" id="cmtWriter">${ses.id }</span> <input
				type="text" class="form-control" placeholder="Add comment..."
				id="cmtText" aria-label="Username" aria-describedby="basic-addon1">
				
				<!-- 내가 에이태그 추가함 -->
			<a href="/board/cmtCnt"><button type="button" id="cmtAddBtn" class="btn btn-secondary">post</button></a>
		</div>
 --%>
		<!-- 댓글 출력 라인  -->

	<%-- 	<div class="accordion" id="accordionExample">
			
			<div class="accordion-item">
				<h2 class="accordion-header">
					<button class="accordion-button" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseOne"
						aria-expanded="true" aria-controls="collapseOne">
						cno, writer, reg_date </button>
				</h2>
				<div id="collapseOne" class="accordion-collapse collapse show"
					data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<strong>댓글 내용 표시</strong>
					</div>
				</div>
			</div>
		</div>

			<br>
			<hr>
			<c:if test="${ses.id eq bvo.writer }"> --%>
			 <a href="/board/modify?bno=${bvo.bno}"><button type="button" class="btn btn-warning">수정</button></a> 
			 <a href="/board/remove?bno=${bvo.bno}"><button type="button" class="btn btn-danger">삭제</button></a>
			 <%-- </c:if>  --%>
			 <a href="/board/list"><button type="button" class="btn btn-primary">리스트</button></a>

		<!-- </div>  -->
		<!-- <script type="text/javascript"> 
		const bnoVal = `<c:out value="${bvo.bno}"/>`;
		const id = `<c:out value="${ses.id }" />`;
		</script>
		<script type="text/javascript" src="/resources/js/boardDetailComment.js"></script>
		<script type="text/javascript"> spreadCommentList(bnoVal); </script> -->
		<jsp:include page="../layout/footer.jsp"></jsp:include>
		
		
		
		
		
		
		
		
		