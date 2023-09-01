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

	<form class="my-3" id="searchForm" action="${ctxPath}/admin" method="post">
		<div class="d-inline-block">
			<span>회원 찾기</span>			
		</div>
		<div class="d-inline-block col-4">
			<input type="text" name="keyword" value="${memberId}"
				class="form-control" placeholder="회원 Id 입력">
		</div>
		<div class="d-inline-block">
			<button class="btn btn-primary search">검색</button>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" name="memberId"/>
	</form>

	<div>
		<div>
			<span> 검색 결과 :
				<c:choose>
					<c:when test="${list != '[]'}">
						${list}
					</c:when>
					<c:when test="${empty list}">
						존재하지 않는 회원 정보입니다.
					</c:when>
				</c:choose>
			</span>
		</div>
	</div>

<%@ include file="includes/footer.jsp"%>

<script>
$(function(){
	let searchForm = $('#searchForm')
			let memberId = $('[name="keyword"]').val()
			console.log(memberId)
// 			searchForm.append($('<input/>',{type : 'hidden', name : 'memberId', value : memberId}))

	$('.search').click(function(e){
// 		e.preventDefault()
		if(!searchForm.('[name="keyword"]').val()) {
			alert('회원 아이디를 입력하세요')
		}
	})
})
</script>