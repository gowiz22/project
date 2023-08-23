<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1 class="page-header">제품 상세 페이지</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					제품
				</div>
				<div class="card-body">
					<div class="form-group">
						<label>번호</label>	
						<input class="form-control" name="pno" value="${product.pno}" readonly="readonly"/>
					</div>
					<div class="form-group">
						<label>제목</label>
						<input class="form-control" name="p_name" value="${product.p_name}" readonly="readonly"/>
					</div>
					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="10" name="detail" readonly="readonly">${product.detail}</textarea>
					</div>
					<div class="form-group">
						<label>작성자</label>
						<input class="form-control" name="writer" value="${product.writer}" readonly="readonly"/>
					</div>
					<sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
						<button data-oper='modify' class="btn btn-light modify">수정페이지</button>
					</sec:authorize>
					<button data-oper='list' class="btn btn-info list">목록으로</button>						
				</div>
			</div>
		</div>
	</div>
</div>

<form>
	<input type="hidden" name="pno"  value="${product.pno}">
</form>

<%@ include file="../../includes/footer.jsp" %>

<script>
$(function(){
	let getForm = $('form')
	let operration = $(this).data('oper')
	let type = '${criteria.type}'
	let keyword = '${criteria.keyword}'
	
	getForm.append($('<input/>',{type : 'hidden', name : 'pageNum', value : '${criteria.pageNum}'}))
			.append($('<input/>',{type : 'hidden', name : 'amount', value : '${criteria.amount}'}))
			.attr('method','get')
			
	if(type&&keyword){
		getForm.append($('<input/>',{type : 'hidden', name : 'type', value : '${criteria.type}'}))
				.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${criteria.keyword}'}))
	}

	// 수정페이지 
	$('.modify').click(function(){
		getForm.attr('action','${ctxPath}/product/modify')
				.attr('method','get')
				.submit();
	});
	
	// 목록으로
	$('.list').click(function(){
		getForm.find('[name="pno"]').remove();
		getForm.attr('action','${ctxPath}/product/list')
				.attr('method','get')
				.submit();
	});
});
</script>