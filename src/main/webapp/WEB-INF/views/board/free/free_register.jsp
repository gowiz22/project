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
				<div class="card-header">공지사항 등록</div>
				<div class="card-body">
					<form action="${ctxPath}/announce/register" method="post">
						<div class="form-group">
							<label>Title </label>
							<input class="form-control" name="title"/>
						</div>
						<div class="form-group">
							<label>Text area </label>
							<textarea class="form-control" rows="10" name="content"></textarea>
						</div>
						<div class="form-group">
							<label>Writer </label>
							<input class="form-control" name="writer"/>
						</div>
						<button class="btn btn-light">Submit Button</button>						
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../../includes/footer.jsp" %>