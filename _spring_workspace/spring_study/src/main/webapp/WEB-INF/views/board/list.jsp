<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<meta charset="UTF-8">
<title>Insert title here</title>
<div class="container-md">
	<jsp:include page="../layout/header.jsp" />
	<h1>Board list page</h1>
	<!-- 검색라인 -->
	<form action="">
    <div class="row g-3 justify-content-center">
      <div class="col-sm-7">
        <input
          type="text"
          class="form-control"
          placeholder="검색어를 입력하세요"
          aria-label="City"
        />
      </div>
      <div class="col-sm">
        <select class="form-select" id="specificSizeSelect">
          <option selected="">Choose...</option>
          <option value="1">글제목</option>
          <option value="2">작성자</option>
          <option value="3">글내용</option>
        </select>
      </div>
      <div class="col-sm">
        <button type="submit" class="btn btn-primary">search</button>
      </div>
    </div>
	</form>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td>${bvo.bno}</td>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.title}</a></td>
					<td>${bvo.writer}</td>
					<td>${bvo.reg_date}</td>
					<td>${bvo.read_count}</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<!-- 페이지네이션 라인 -->
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</nav>

</div>
<jsp:include page="../layout/footer.jsp" />
