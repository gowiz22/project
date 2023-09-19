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
						<label>제품 번호</label>	
						<input class="form-control" name="pno" value="${product.pno}" readonly="readonly"/>
					</div>
					<div class="form-group">
						<label>제품명</label>
						<input class="form-control" name="p_name" value="${product.p_name}" readonly="readonly"/>
					</div>
					<div class="form-group">
							<label>제품 금액</label>
							<input class="form-control" name="price" value="${product.price}" readonly="readonly"/>
						</div>
					<div class="form-group">
						<label>제품 상세</label>
						<textarea class="form-control" rows="10" name="detail" readonly="readonly">${product.detail}</textarea>
					</div>
					<div class="form-group">
						<label>작성자</label>
						<input class="form-control" name="writer" value="${product.writer}" readonly="readonly"/>
					</div>
					<sec:authorize access="isAuthenticated() and hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER')">
						<button data-oper='modify' class="btn btn-light modify">수정페이지</button>
					</sec:authorize>
					<button data-oper='list' class="btn btn-info list">목록으로</button>						
				</div>
			</div>
		</div>
	</div>
	
	<div class="row my-5">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-header">
					<h4>첨부 파일</h4>
				</div>
				<div class="card-body">
					<div class="uploadResultDiv mt-3"> <!-- 파일업로드 결과 보여주기  -->
						<ul class="list-group"></ul>
					</div>
				</div> <!-- card-body -->
			</div> <!-- caard end -->
		</div> <!-- col end -->
	</div><!-- row end -->
	
	<h3 class="mt-5">댓글</h3>
	<div class="row">
		<div class="col-12">
			<ul class="list-group chat">
			</ul>		
		</div>
	</div>

	<!-- 댓글작성 -->	
	<div class="my-3 replyWriterForm">
		<sec:authorize access="isAnonymous()">
			<textarea  rows="6" placeholder="로그인한 사용자만 댓글을 쓸 수 있습니다." readonly="readonly" 
				maxlength="400" class="replyContent form-control"></textarea>		
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<div style="display: flex; align-items: center; justify-content: flex-end;">
			<span style="width: 80px;">제품 평점</span>
				<select class="amount form-control ml-2" id="score" style="width: 80px;">
					<c:forEach begin="1" end="10" var="s">
						<option value="${s}">${s}</option>
					</c:forEach>
				</select>
			</div>
			<textarea  rows="6" placeholder="댓글을 작성해주세요" 
				maxlength="400" class="r_comment form-control"></textarea>
			<div class="text-right">
				<div class="submit p-2">
					<span class="btn btn-outline-info col-2 reviewer">${authInfo.memberId}</span>
					<button class="btn btn-outline-primary col-3">등록</button>
				</div>
			</div>
		<input type="hidden" class="score" value="1">
		</sec:authorize>
	</div>	
</div>

<form>
	<input type="hidden" name="pno"  value="${product.pno}">
</form>

<!-- Modal -->
<div class="modal fade" id="showImage">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">원본 이미지 보기</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body"></div>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>

<script>
$(function(){
	let getForm = $('form')
	let operration = $(this).data('oper')
	let type = '${criteria.type}'
	let keyword = '${criteria.keyword}'
	let memberId = '${authInfo.memberId}'
	let radio = '${criteria.radio}'
	
	getForm.append($('<input/>',{type : 'hidden', name : 'pageNum', value : '${criteria.pageNum}'}))
			.append($('<input/>',{type : 'hidden', name : 'amount', value : '${criteria.amount}'}))
			.attr('method','get')
			
	if(type&&keyword){
		getForm.append($('<input/>',{type : 'hidden', name : 'type', value : '${criteria.type}'}))
				.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${criteria.keyword}'}))
	}
	if(radio!==0){
		getForm.append($('<input/>',{type : 'hidden', name : 'radio', value : '${criteria.radio}'}))
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
	
	// 제품 평점 드롭다운
	$('#score').change(function(){
		let score = $(this).val()
		console.log(score)
		$('.score').val(score)
	})
});
</script>
<script src="${ctxPath}/resources/js/product/product_get.js"></script>
<script src="${ctxPath}/resources/js/product/product_replyService.js"></script>
<script src="${ctxPath}/resources/js/product/product_reply.js"></script>