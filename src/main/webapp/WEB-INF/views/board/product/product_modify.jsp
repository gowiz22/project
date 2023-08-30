<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1 class="page-header">제품 수정 페이지</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					Board Modify Page
				</div>
				<div class="card-body">
					<form class="modifyForm" action="${ctxPath}/product/modify" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<div class="form-group">
							<label>제품 번호</label>	
							<input class="form-control" name="pno" value="${product.pno}" readonly="readonly"/>
						</div>
						<div class="form-group">
							<label>제품명</label>
							<input class="form-control" name="p_name" value="${product.p_name}" />
						</div>
						<div class="form-group">
							<label>제품 금액</label>
							<input class="form-control" name="price" value="${product.price}"/>
						</div>
						<div class="form-group">
							<label>제품 상세 </label>
							<textarea class="form-control" rows="10" name="detail">${product.detail}</textarea>
						</div>
						<div class="form-group">
							<label>Writer </label>
							<input class="form-control" name="writer" value="${product.writer}" readonly="readonly"/>
						</div>
						<div class="form-group">
							<label>Register Date</label>
							<input class="form-control" readonly="readonly"  name="regDate"
								value="<tf:formatDateTime value="${product.regDate}" pattern="yyyy년MM월dd일 HH시mm분"/>">
						</div>
						<div class="form-group">
							<label>Update Date</label>
							<input class="form-control" readonly="readonly" name="updateDate"  
								value="<tf:formatDateTime value="${product.updateDate}" pattern="yyyy년MM월dd일 HH시mm분"/>">
						</div>
						<button type="button" data-oper='modify' class="btn btn-light">수정</button>
						<button type="button" data-oper='remove' class="btn btn-danger">삭제</button>
						<button type="button" data-oper='list' class="btn btn-info">목록</button>
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
						<ul class="list-group"></ul>
					</div>
				</div> <!-- card-body -->
			</div> <!-- card end -->
		</div> <!-- col end -->
	</div><!-- row end -->
</div>
<!-- 원본 이미지 -->
<div class="modal fade" id="showImage">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
	        <div class="modal-header">
	            <h4 class="modal-title">원본 이미지 보기</h4>
	            <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        <div class="modal-body"></div>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>

<script>
	let formObj = $('.modifyForm')
	let type = '${criteria.type}'
	let keyword = '${criteria.keyword}'
	let radio = '${criteria.radio}'
	
	let addCriteria = function() {	
		if(type&&keyword){
			formObj.append($('<input/>',{type : 'hidden', name : 'type', value : '${criteria.type}'}))
					.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${criteria.keyword}'}))
		}
		if(radio!==0){
			formObj.append($('<input/>',{type : 'hidden', name : 'radio', value : '${criteria.radio}'}))
			}

	}
</script>
<script src="${ctxPath}/resources/js/product/product_modify.js"></script>