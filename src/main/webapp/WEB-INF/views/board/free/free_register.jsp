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
				<div>
					자유게시판
					<select class="amount form-control" id="category">
						<c:forEach items="${category}"  var="category">
							<option value="${category}" >${category}</option>
						</c:forEach>					
					</select>
				</div>
				</div>
				<div class="card-body">
					<form class="register" action="${ctxPath}/free/register" method="post">
						<input type="hidden" name="cno" value="${catecory}">
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
							<input class="form-control" name="writer" value="${authInfo.memberId}" readonly="readonly"/>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
					<div class="uploadResultDiv mt-3"> <!-- 파일업로드 결과 보여주기  -->
						<ul class="list-group">
							<li class="list-group-item" data-uuid="radom-uuid">
								<div class="float-left">
									<div class="thumnail d-inline-block mr-3" style="width:40px">
										<img alt="" src="${ctxPath}/resources/images/attach.png" style="width: 100%">
									</div>
									<div class="d-inline-block">
										<a href="#">my_document.txt</a>
									</div>
								</div>
								<div class="float-right">
									<a href="#">삭제</a>
								</div>
							</li>
						</ul>
					</div>
				</div> <!-- panel-body -->
			</div> <!-- panel end -->
		</div> <!-- col end -->
	</div><!-- row end -->	
</div>

<%@ include file="../../includes/footer.jsp" %>
<script src="${ctxPath}/resources/js/free_register.js"></script>

<script>
let register = $('.register')
//카테고리 처리
$('#category').change(function(){
	let cno = $(this).val()
	register.find('[name="cno"]').val(cno)
})
</script>