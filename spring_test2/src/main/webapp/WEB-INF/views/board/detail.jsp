<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../layout/header.jsp" />
	<div class="container-md">
		<h1>Board detail page</h1>
		<c:set value="${ bdto.bvo}" var="bvo"></c:set>
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
				aria-label="With textarea">${bvo.content}</textarea>
		</div>
		
		<!-- image 파일 띄우는 곳 -->
		<c:set value="${bdto.flist}" var="flist"></c:set>
		<div class="mb-3">
			<ul class="list-group list-group-flush">
			<!-- 파일 개수만큼 li를 반복하여 파일 표시 / 타입이 1인 경우만 -->
			<!-- li > div > img -->
			<!--    > div > 파일이름, 작성일, 사이즈, span size -->
			<c:forEach items="${bdto.flist }" var="fvo">
				<li class="list-group-item">
					<c:choose>
						<c:when test="${fvo.fileType > 0 }">
							<div>
								<img alt="" src="/upload/${fvo.saveDir }/${fvo.uuid}_${fvo.fileName}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<!-- file_type == 0 인 경우 => 아이콘 -->
								<!--  파일 다운로드 -->
							<a href="/upload/${fvo.saveDir }/${fvo.uuid}_${fvo.fileName}" download="${fvo.fileName}">	
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-folder2" viewBox="0 0 16 16">
  									<path d="M1 3.5A1.5 1.5 0 0 1 2.5 2h2.764c.958 0 1.76.56 2.311 1.184C7.985 3.648 8.48 4 9 4h4.5A1.5 1.5 0 0 1 15 5.5v7a1.5 1.5 0 0 1-1.5 1.5h-11A1.5 1.5 0 0 1 1 12.5zM2.5 3a.5.5 0 0 0-.5.5V6h12v-.5a.5.5 0 0 0-.5-.5H9c-.964 0-1.71-.629-2.174-1.154C6.374 3.334 5.82 3 5.264 3zM14 7H2v5.5a.5.5 0 0 0 .5.5h11a.5.5 0 0 0 .5-.5z"/>
								</svg>	
							</a>
							</div>
						</c:otherwise>
					</c:choose>
					<div>
						<!-- 파일이름, 작성일, 사이즈, span size -->
						<div>${fvo.fileName }</div>
						${fvo.regDate }
						<span class="badge text-bg-warning">${fvo.fileSize }byte</span>
					</div>
				</li>
			</c:forEach>
  			</ul> 
	</div> 
		<!-- comment -->
		<!-- 댓글 등록 라인  -->
		<sec:authentication property="principal.uvo.nickName" var="authNick"/>
		 <br>
		<hr>
		<div class="input-group mb-3">
			<span class="input-group-text" id="cmtWriter">${authNick }</span> <input
				type="text" class="form-control" placeholder="Add comment..."
				id="cmtText" aria-label="Username" aria-describedby="basic-addon1">
				
				<!-- 내가 에이태그 추가함 -->
			<button type="button" id="cmtAddBtn" class="btn btn-secondary">post</button>
		</div>

		<!-- 댓글 출력 라인  -->
		<div class="input-group mb-3">
		<ul class="list-group list-group-flush" id="cmtListArea">
  			<li class="list-group-item">
				<div class="input-group mb-3">
					<div class="fw-bold">writer</div> 
					content
				</div>
				<span class="badge rounded-pill text-bg-dark">regDate</span>
			</li>
		</ul>	
		</div>
		<!-- 댓글 더보기 버튼 -->
		<div>
			<button type="button" class="btn btn-light" id="moreBtn" data-page="1" style="visibility: hidden"> MORE + </button>
		</div>	
		<!-- 모달창 라인 -->
		<div class="modal" id="myModal" tabindex="-1">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Writer</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <input type="text" class="form-control" id="cmtTextMod">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" id="cmtModBtn" >modify</button>
		      </div>
		    </div>
		  </div>
		</div>
			<br>
			<hr>

			 <a href="/board/modify?bno=${bvo.bno}"><button type="button" class="btn btn-warning">수정</button></a> 
			 <a href="/board/remove?bno=${bvo.bno}"><button type="button" class="btn btn-danger" id="cmtDelBtn">삭제</button></a>
			 <%-- </c:if>  --%>
			 <a href="/board/list"><button type="button" class="btn btn-primary">리스트</button></a>

		</div>  
		<script type="text/javascript"> 
		const bnoVal = `<c:out value="${bvo.bno}"/>`;
		
		</script>
		<script type="text/javascript" src="/resources/js/boardDetailComment.js"></script>
		 <script type="text/javascript"> spreadCommentList(bnoVal); </script>
		<jsp:include page="../layout/footer.jsp"></jsp:include>
		
		
		
		
		
		
		
		
		