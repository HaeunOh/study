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
	<form action="/board/list" method="get">
    <div class="row g-3 justify-content-center">
      <div class="col-sm-7">
        <input
          type="text"
          name="keyword"
          class="form-control"
          placeholder="검색어를 입력하세요"
          aria-label="City"
          value="${ph.pgvo.keyword }"
        />
        <input type="hidden" name="pageNo" value="1">
        <input type="hidden" name="qty" value="10">
      </div>
      <div class="col-sm">
        <select class="form-select" name="type" id="specificSizeSelect">
          <option ${ph.pgvo.type == null ? 'selected' : '' } selected>Choose...</option>
          <option value="t" ${ph.pgvo.type eq 't' ? 'selected' : '' }>글제목</option>
          <option value="w" ${ph.pgvo.type eq 'w' ? 'selected' : '' }>작성자</option>
          <option value="c" ${ph.pgvo.type eq 'c' ? 'selected' : '' }>글내용</option>
          <option value="tc" ${ph.pgvo.type eq 'tc' ? 'selected' : '' }>글제목+내용</option>
          <option value="wc" ${ph.pgvo.type eq 'wc' ? 'selected' : '' }>작성자+내용</option>
          <option value="tw" ${ph.pgvo.type eq 'tw' ? 'selected' : '' }>제목+작성자</option>
          <option value="twc" ${ph.pgvo.type eq 'twc' ? 'selected' : '' }>전체검색</option>
        </select>
      </div>
      <div class="col-sm">
        <button type="submit" class="btn btn-primary">search </button>
    <span class="visually-hidden">unread messages</span>        
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
				<th>댓글</th>
				<th>파일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td>${bvo.bno}</td>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.title}</a></td>
					<td>${bvo.writer}</td>
					<td>${bvo.regDate}</td>
					<td>${bvo.readCount}</td>
					<td>${bvo.cmtQty}</td>
					<td>${bvo.hasFile}</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<!-- 페이지네이션 라인 -->
	 <nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
		
			<li class="page-item ${ph.prev eq false ? 'disabled' : '' }">
			<a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qyt=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
				aria-label="Previous">
				 <span aria-hidden="true">&laquo;</span>
			</a>
			</li>
	
			<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
				<li class="page-item ${ph.pgvo.pageNo eq i ? 'active' : '' } }"><a class="page-link" href="/board/list?pageNo=${i }&qyt=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
			</c:forEach>
			
			
			<li class="page-item ${ph.next eq false ? 'disabled' : '' }" >
			<a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qyt=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next"> 
				<span aria-hidden="true">&raquo;</span>
			</a>
			</li>
	
		</ul>
	</nav> 

</div>
<jsp:include page="../layout/footer.jsp" />
