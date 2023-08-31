<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<div class="container">
	<ul class="list-group list-group-horizontal justify-content-center mt-3">
		<li class="list-group-item">
			<a href="${ctxPath}/admin">홈페이지 관리</a>
		</li>
		<li class="list-group-item">
			<a href="${ctxPath}/mypage/article">회원 관리</a>
		</li>
		<li class="list-group-item">
			<a href="${ctxPath}/mypage/comment">게시판 관리</a>
		</li>
		<li class="list-group-item">
			<a href="${ctxPath}/mypage/articleLike">3</a>
		</li>
		<li class="list-group-item">
			<a href="${ctxPath}/mypage/commentLike">4</a>
		</li>
	</ul>
</div>

	<form class="my-3" id="searchForm" action="${ctxPath}/free/list">
		<div class="d-inline-block">
			<span>회원 찾기</span>			
		</div>
		<div class="d-inline-block col-4">
			<input type="text" name="keyword" value="${p.criteria.memberId}"
				class="form-control">
		</div>
		<div class="d-inline-block">
			<button class="btn btn-primary">검색</button>
		</div>
		<input type="hidden" name="pageNum" value="${p.criteria.memberId}">
	</form>

				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<%@ include file="includes/footer.jsp"%>