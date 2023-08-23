<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1 class="page-header">Tables</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">상품 등록</div>
				<div class="card-body">
					<form action="${ctxPath}/product/register" method="post">
						<div class="form-group">
							<label>상품명 </label>
							<input class="form-control" name="p_name"/>
						</div>
						<div class="form-group">
							<label>상품 정보 </label>
							<textarea class="form-control" rows="10" name="detail"></textarea>
						</div>
						<div class="form-group">
							<label>작성자 </label>
							<input class="form-control" name="writer" value="${authInfo.memberId}" readonly="readonly"/>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<button class="btn btn-light register">Submit Button</button>						
						<button class="btn btn-light list">List</button>						
					</form>
				</div>
			</div>
		</div>
	</div>
	
</div>
<%@ include file="../../includes/footer.jsp" %>

<script>
$(function(){
	$('.register').click(function(){
		let form = $('form');
		form.submit();	
	});
	
	// 게시물 목록
	$('.list').click(function(){
		let form = $('form');
		form.attr('action','${ctxPath}/product/list')
			.attr('method', 'get')
			.append($('[name="pageNum"]'))
			.append($('[name="amount"]'))
			.appendTo('body')
			.submit();
	})
})
</script>