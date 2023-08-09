<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1 class="page-header">공지사항</h1>
		</div>
	</div>
	
	<div class="row mb-3">
		<div class="col-12">
			<div class="card">
				<div class="card-header">BoardList Page</div>
				<div class="card-body">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>#번호</th> 
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>수정일</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${list}" var="board">
							<tr>
								<td>${board.bno}</td>
								<td>
								<a href="${ctxPath}/announce/get?bno=${board.bno}">${board.title}</a>
								</td>
								<td>${board.writer}</td>
								<td><tf:formatDateTime value="${board.regDate}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td><tf:formatDateTime value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="float-right d-flex">
		<button id="regBtn" class="btn btn-xs btn-primary">공지사항 등록</button>
	</div>
</div>

	<form id="listForm" action="${ctxPath}/board/list"></form>

<%@ include file="../../includes/footer.jsp"%>

<!-- Modal -->
<div class="modal fade" id="listModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">처리 결과</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                처리가 완료되었습니다.
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">Save Changes</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
$(function(){
	let listForm = $('#listForm')
	let result = "${result}"
	checkModal(result)
	
	function checkModal(result){
		if(result=='') return; // 값이 없으면 함수 종료
		let operation = "${operation}"
		if(operation=='register'){
			$('.modal-body').html(result+'번 게시글을 등록하였습니다.');
		} else if(operation=='modify'){
			$('.modal-body').html(result+'번 게시글을 수정하였습니다.');
		} else if(operation=='remove') {
			$('.modal-body').html(result+'번 게시글을 삭제하였습니다.');
		}
		$('#listModal').modal('show'); // 값이 있으면 모달창 열기
	}
	
	// 글 작성 페이지로 이동
	$('#regBtn').click(function(){
		listForm.attr('action', '${ctxPath}/announce/register')
				.submit()
	})
})
</script>