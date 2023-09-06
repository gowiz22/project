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
	<h4>자유게시판</h4>
		<table class="table table-striped table-bordered table-hover mt-3 ml-5" style="width: 60%">
		<thead>
			<tr>
				<th>카테고리명</th>
				<th>게시물 수</th>
				<th>카테고리 이동</th>
				<th>카테고리 삭제</th>
			</tr>
		</thead>
		<c:forEach items="${category}" var="c" varStatus="loop">
			<tr>
				<td>
					<span>${loop.index +1}.</span>
					<input type="hidden" class="c_cno" value="${c.cno}">
					<input type="hidden" class="c_kind" value="${c.kind}">
					<input type="hidden" class="c_amount" value="${c.count}">
					<input type="text" value="${c.kind}" class="kind">
					<button class="rename">수정</button>
					
				</td>
				<td>
					<span>${c.count==null ? '0' : c.count}개</span>
				</td>
				<td>
					<button class="move">이동</button>
				</td>
				<td>
					<button class="delete">삭제</button>
				</td>
			</tr>
		</c:forEach>
			<tr>
				<td colspan="4">
					<input type="text" class="new_kind" placeholder="추가할 카테고리">
					<button class="new">추가</button>
				</td>
			</tr>

	</table>
</div>

<form class="updateForm" action="${ctxPath}/admin/renameCategory" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<form class="insertForm" action="${ctxPath}/admin/newCategory" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<form class="deleteForm" action="${ctxPath}/admin/removeCategory" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<form class="removeAllForm" action="${ctxPath}/admin/removeBoard" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<form class="reLocateForm" action="${ctxPath}/admin/relocateBoard" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<!-- Modal -->
<div class="modal fade" id="listModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">게시물 이동</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
	            <table>
					<tr>
						<td>
						<select class="categoryList">
				            <c:forEach items="${category}" var="c" varStatus="loop">
								<option value="${c.cno}">${c.kind}</option>
			         	   </c:forEach>
						</select>
							<input type="hidden" class="c_cno" value="${c.cno}">
							<input type="hidden" class="c_kind" value="${c.kind}">
							<input type="hidden" class="c_amount" value="${c.count}">
						</td>
		            </tr>
	            </table>    
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-danger removeAll" data-dismiss="modal">전체 게시물 삭제</button>
                <button type="button" class="btn btn-outline-success change" data-dismiss="modal">변경</button>
                <button type="button" class="btn btn-outline-info" data-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<%@ include file="../includes/footer.jsp"%>

<script>
$(function(){
	let updateForm = $('.updateForm')
	let insertForm = $('.insertForm')
	let deleteForm = $('.deleteForm')
	let removeAllForm = $('.removeAllForm')
	let reLocateForm = $('.reLocateForm')
	let changeForm = $('.ChangeForm')
	
	$('.rename').click(function(e){
		e.preventDefault()
		let searchId = $('.keyword').val()
		
		let cno = $(this).closest('tr').find('.c_cno').val()
		let newKind =$(this).closest('tr').find('.c_kind').val()
		
		updateForm.append($('<input/>',{type : 'hidden', name : 'cno', value : cno}))
		updateForm.append($('<input/>',{type : 'hidden', name : 'kind', value : newKind}))
		updateForm.submit()
	})
	
	$('.delete').click(function(){
		let amount = $(this).closest('tr').find('.c_amount').val()
		let cno = $(this).closest('tr').find('.c_cno').val()
		
		if(amount>0) {
			alert("게시물을 이동해주세요.")
		}else {
			deleteForm.append($('<input/>',{type : 'hidden', name : 'cno', value : cno}))
					  .submit()
		}
	})
	
	$('.move').click(function(){
		let cno = $(this).closest('tr').find('.c_cno').val()
		let kind = $(this).closest('tr').find('.c_kind').val()

		$('#listModal').modal('show')
		$('.modal-title').text("'"+kind+"' 카테고리")

		$('.removeAll').click(function(){
			removeAllForm.append($('<input/>',{type : 'hidden', name : 'cno', value : cno}))
						 .submit()
		})
		$('.change').click(function(e){
			e.preventDefault()
			let newCno = $('.categoryList').val()
			
			if(cno!=newCno) {
				reLocateForm.append($('<input/>',{type : 'hidden', name : 'oldCno', value : cno}))
				reLocateForm.append($('<input/>',{type : 'hidden', name : 'newCno', value : newCno}))
							.submit()
			}
			return
		})
	})
	
	$('.new').click(function(){
		let kind = $('.new_kind').val()
		
		insertForm.append($('<input/>',{type : 'hidden', name : 'kind', value : kind}))
				  .submit()
	})

})
</script>