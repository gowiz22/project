<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1 class="page-header">게시글 수정</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
						<select class="amount form-control" id="category">
							<c:forEach items="${category}" var="c">
								<option value="${c.cno}" ${board.cno eq c.cno ? 'selected':''}>${c.kind}</option>
							</c:forEach>
						</select>
				</div>
				<div class="card-body">
					<form action="${ctxPath}/free/modify" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<div class="form-group">
							<label>글 번호</label>	
							<input class="form-control" name="bno" value="${board.bno}" readonly="readonly"/>
						</div>
						<div class="form-group">
							<label>제 목</label>
							<input class="form-control" name="title" value="${board.title}" />
						</div>
						<div class="form-group">
							<label>내 용</label>
							<textarea class="form-control" rows="10" name="content">${board.content}</textarea>
						</div>
						<div class="form-group">
							<label>작성자</label>
							<input class="form-control" name="writer" value="${board.writer}" readonly="readonly"/>
						</div>
						<div class="form-group">
							<label>글 등록일</label>
							<input class="form-control" readonly="readonly"  name="regDate"
								value="<tf:formatDateTime value="${board.regDate}" pattern="yyyy년MM월dd일 HH시mm분"/>">
						</div>
						<div class="form-group">
							<label>글 수정일</label>
							<input class="form-control" readonly="readonly" name="updateDate"  
								value="<tf:formatDateTime value="${board.updateDate}" pattern="yyyy년MM월dd일 HH시mm분"/>">
						</div>
						<input type="hidden" name="cno" value="${board.cno}"/>
						<button type="button" data-oper='modify' class="btn btn-light">수정</button>
						<button type="button" data-oper='remove' class="btn btn-danger">삭제</button>
						<button type="button" data-oper='list' class="btn btn-info">목록</button>
					</form>						
				</div>
			</div>
		</div>
	</div>

	<div class="row my-5">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<h4>파일 첨부</h4>
				</div>
				<div class="card-body">
					<div class="uploadDiv form-group">
						<input type="file" class="form-control" name="uploadFile" multiple="multiple">
					</div>
					<div class="uploadResultDiv form-group">
						<ul class="list-group"></ul>
					</div>
				</div>
			</div>
		</div>	
	
</div>

<!-- Modal -->
<div class="modal fade" id="modifyModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">확인 창</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                수정 완료하시겠습니까
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" id="certain" class="btn btn-light" data-dismiss="modal">확인</button>
                <button type="button" id="cancel" class="btn btn-danger" data-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>

<script>
$(function(){
	let modifyForm = $('form')
	let type = '${criteria.type}'
	let keyword = '${criteria.keyword}'
	let recommend = '${criteria.recommend}'

	if(type&&keyword){
		modifyForm.append($('<input/>',{type : 'hidden', name : 'type', value : '${criteria.type}'}))
				.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${criteria.keyword}'}))
	}

	if(recommend=="T") {
		modifyForm.append($('<input/>',{type : 'hidden', name : 'recommend', value : '${criteria.recommend}'}))
	}
	
	$('button').click(function(){
		let operation = $(this).data('oper');
		modifyForm.append($('<input/>',{type : 'hidden', name : 'pageNum', value : '${criteria.pageNum}'}))
					.append($('<input/>',{type : 'hidden', name : 'amount', value : '${criteria.amount}'}))	

		if(operation=='list'){ // 목록으로
			$('.form-group').remove()
			modifyForm.attr('action','${ctxPath}/free/list')
						.attr('method','get');
		} else if(operation=='modify'){ // 수정처리

		} else if(operation=='remove'){ // 삭제처리
			modifyForm.attr('action','${ctxPath}/free/remove')
		}
		modifyForm.submit();
	});	
	
	$('#category').change(function(){
		let cno = $(this).val()
		modifyForm.find('[name="cno"]').val(cno)
	})
})
</script>
<script src="${ctxPath}/resources/js/free/modify.js"></script>