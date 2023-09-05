<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>
<div class="container">
	<ul class="list-group list-group-horizontal justify-content-center mt-3">
		<li class="list-group-item">
			<a href="${ctxPath}/admin">회원 관리</a>
		</li>
		<li class="list-group-item">
			<a href="${ctxPath}/admin/article">홈페이지 현황</a>
		</li>
		<li class="list-group-item">
			<a href="${ctxPath}/admin/board">게시판 관리</a>
		</li>
	</ul>
</div>

	<form class="my-3" id="searchForm" action="${ctxPath}/admin" method="post">
		<div class="d-inline-block">
			<span>회원 찾기</span>			
		</div>
		<div class="d-inline-block col-4">
			<input type="text" value="${memberId}"
				class="form-control keyword" placeholder="회원 Id 입력">
		</div>
		<div class="d-inline-block">
			<button class="btn btn-primary search">검색</button>
			<button class="btn btn-primary reset">새로고침</button>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

	<div>
		<div>
			<span> 검색 결과 </span>
				<table class="table table-striped table-bordered table-hover mt-3 ml-5" style="width: 80%">
					<thead>
						<tr>
							<th>회원 Id</th>
							<th>가입일</th>
							<th>권한</th>
							<th>권한 관리</th>
						</tr>
					</thead>
				<c:choose>
					<c:when test="${list != '[]'}">
							<c:forEach items="${list}" var="member">
								<tr>
									<td class="memberId">${member.memberId}</td>
									<td><tf:formatDateTime value="${member.regDate}"
											pattern="yyyy-MM-dd HH:mm"/></td>
									<td>
										<c:forEach items="${member.authList}" var="vo">
											<c:if test="${vo.auth == 'ROLE_ADMIN'}">
												<span class="badge badge-pill badge-danger">관리자</span>
											</c:if>
											<c:if test="${vo.auth == 'ROLE_MEMBER'}">
												<span class="badge badge-pill badge-warning">일반회원</span>
											</c:if>
											<c:if test="${vo.auth == 'ROLE_REVIEWER'}">
												<span class="badge badge-pill badge-success">리뷰어</span>
											</c:if>
										</c:forEach>
									</td>
									<td>
										<c:if test="${member.memberId != authInfo.memberId && member.memberId != 'admin'}">
											<select class="roleSelect">
												<option value="ROLE_ADMIN" ${member.authList[0].auth eq 'ROLE_ADMIN'? 'selected' : ''}>관리자</option>
												<option value="ROLE_REVIEWER" ${member.authList[0].auth eq 'ROLE_REVIEWER'? 'selected' : ''}>리뷰어</option>
												<option value="ROLE_MEMBER" ${member.authList[0].auth eq 'ROLE_MEMBER'? 'selected' : ''}>일반회원</option>
											</select>
											<button class="RoleChange">변경</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
					</c:when>
					<c:when test="${empty list}">
						<tr>
							<td colspan="4">존재하지 않는 회원입니다.</td>
						</tr>
					</c:when>
				</c:choose>
			</table>
		</div>
	</div>

	<form class="ChangeForm" action="${ctxPath}/admin/changeRole" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

<%@ include file="../includes/footer.jsp"%>

<script>
$(function(){
	let searchForm = $('#searchForm')
	let changeForm = $('.ChangeForm')
	
	$('.search').click(function(e){
		e.preventDefault()
		let searchId = $('.keyword').val()
		searchForm.append($('<input/>',{type : 'hidden', name : 'memberId', value : searchId}))
		searchForm.submit()
	})
	
	$('.RoleChange').click(function(){
		let memberId =$(this).closest('tr').find('.memberId').text()
		let roleSelect = $(this).closest('tr').find('.roleSelect').val()
		console.log(memberId)
		console.log(roleSelect)
		changeForm.append($('<input/>',{type : 'hidden', name : 'memberId', value : memberId}))
		changeForm.append($('<input/>',{type : 'hidden', name : 'auth', value : roleSelect}))
		
		changeForm.submit()
	})
})
</script>