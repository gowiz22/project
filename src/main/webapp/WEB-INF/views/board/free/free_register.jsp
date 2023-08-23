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
					<div class="card-header">
						<select class="amount form-control" id="category">
							<c:forEach items="${category}" var="c">
									<option value="${c.cno}">${c.kind}</option>
							</c:forEach>
						</select>
					</div>
					<div class="card-body">
					<form class="regForm" action="${ctxPath}/free/register" method="post">
						<div class="form-group">
							<label>Title </label>
							<input class="form-control" name="title"/>
						</div>
						<div class="form-group">
							<label>Text area </label>
							<textarea class="form-control" rows="15" name="content"></textarea>
						</div>
						<div class="form-group">
							<label>Writer </label>
							<input class="form-control" name="writer" value="${authInfo.memberId}" readonly="readonly"/>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="cno" value="1"/>
						<button type="button" class="btn btn-outline-primary register">Submit Button</button>
						<button type="button" class="btn btn-outline-info list">List</button>					
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row my-5">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-header">
					<h4>파일 첨부</h4>
				</div>
				<div class="card-body">
					<div class="uploadDiv">
						<input type="file" name="uploadFile" multiple="multiple">
					</div>
					<div class="uploadResultDiv form-group">
						<ul class="list-group"></ul>
					</div>
				</div> <!-- panel-body -->
			</div> <!-- panel end -->
		</div> <!-- col end -->
	</div><!-- row end -->	
	
</div>

<input type="hidden" name="pageNum" value="${param.pageNum}" >
<input type="hidden" name="amount" value="${param.amount}" >
<input type="hidden" name="type" value="${param.type}" >
<input type="hidden" name="keyword" value="${param.keyword}" >

<script src="${ctxPath}/resources/js/free/free_register.js"></script>
<script>
$(function(){
	let registerForm = $('.regForm')
	$('#category').change(function(){
		let cno = $(this).val()
		registerForm.find('[name="cno"]').val(cno)
	})
})
</script>
<%@ include file="../../includes/footer.jsp" %>