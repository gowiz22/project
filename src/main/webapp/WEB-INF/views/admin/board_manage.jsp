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

<div>
	<span>자유게시판</span>
		<table class="table table-striped table-bordered table-hover mt-3 ml-5" style="width: 60%">
		<thead>
			<tr>
				<th>카테고리명</th>
				<th>게시물 수</th>
				<th>변경사항</th>
			</tr>
		</thead>
		<c:forEach items="${category}" var="c">
			<tr>
				<td>
					<span>${c.cno}.</span>
					<input type="hidden" class="cno" value="${c.cno}">
					<input type="text" value="${c.kind}" class="kind">
					<button class="rename">수정</button>
					<button class="rename">이동</button>
				</td>
				<td>
					<span>${c.count}개</span>
				</td>
			</tr>
		</c:forEach>
			<tr>
				<td>
			</tr>
	</table>
</div>

<form class="updateForm" action="${ctxPath}/admin/renameCategory" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<%@ include file="../includes/footer.jsp"%>

<script>
$(function(){
	let updateForm = $('.updateForm')
	let changeForm = $('.ChangeForm')
	
	$('.rename').click(function(e){
		e.preventDefault()
		let searchId = $('.keyword').val()
		
		let cno = $(this).closest('tr').find('.cno').val()
		let newKind =$(this).closest('tr').find('.kind').val()
		
		console.log(newKind)
		console.log(cno)
		
		updateForm.append($('<input/>',{type : 'hidden', name : 'cno', value : cno}))
		updateForm.append($('<input/>',{type : 'hidden', name : 'kind', value : newKind}))
		updateForm.submit()
	})

})
</script>