<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h3 class="page-header">공지사항</h3>
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
									<a class="move" href="${board.bno}">${board.title}</a>
								</td>
								<td>${board.writer}</td>
								<td><tf:formatDateTime value="${board.regDate}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td><tf:formatDateTime value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<form class="my-3" id="searchForm" action="${ctxPath}/announce/list">
						<div class="d-inline-block">
							<select name="type" class="form-control">
								<option value="" ${p.criteria.type == null ? 'selected' : '' }>------</option>
								<option value="T" ${p.criteria.type eq 'T' ? 'selected' : '' }>제목</option>
								<option value="C" ${p.criteria.type eq 'C' ? 'selected' : '' }>내용</option>
								<option value="W" ${p.criteria.type eq 'W' ? 'selected' : '' }>작성자</option>
								<option value="TC" ${p.criteria.type eq 'TC' ? 'selected' : '' }>제목+내용</option>
								<option value="TW" ${p.criteria.type eq 'TW' ? 'selected' : '' }>제목+작성자</option>
								<option value="TCW" ${p.criteria.type eq 'TCW' ? 'selected' : '' }>제목+내용+작성자</option>
							</select>
						</div>
						<div class="d-inline-block col-4">
							<input type="text" name="keyword" value="${p.criteria.keyword}" class="form-control">
						</div>
						<div class="d-inline-block">
							<button class="btn btn-primary">검색</button>
						</div>
						<div class="d-inline-block">
							<a href="${ctxPath}/announce/list" class="btn btn-outline-info">새로고침</a>
						</div>
						<input type="hidden" name="pageNum" value="${p.criteria.pageNum}">
						<input type="hidden" name="amount" value="${p.criteria.amount}">
					</form>
			        <sec:authorize access="hasRole('ROLE_ADMIN')">
						<div class="float-right d-flex">
							<button id="regBtn" class="btn btn-xs btn-primary">공지사항 등록</button>
						</div>
					</sec:authorize>	
					<ul class="pagination justify-content-center">
						<c:if test="${p.prev }">
							<li class="page-item">
								<a class="page-link" href="${p.startPage-p.displayPageNum}">이전페이지</a>
							</li>
						</c:if>
						<c:forEach begin="${p.startPage}" end="${p.endPage }" var="pagelink">
							<li class="page-item ${ pagelink == p.criteria.pageNum ? 'active':''}">
								<a href="${pagelink}" class="page-link ">${pagelink}</a>
							</li>
						</c:forEach>
						<c:if test="${p.next }">
							<li class="page-item">
								<a class="page-link" href="${p.endPage+1}">다음페이지</a>
							</li>
						</c:if>
					</ul>
					<form id="listForm" action="${ctxPath}/announce/list" method="get">
						<input type="hidden" name="pageNum" value="${p.criteria.pageNum}">
						<input type="hidden" name="amount" value="${p.criteria.amount}">
					</form>
				</div> <!-- card body end -->
			</div> <!-- card end -->
		</div> <!-- col end -->
	</div>
</div>

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
	let searchForm = $('#searchForm')
	let searchCondition = function(){
		if(searchForm.find('option:selected').val() && searchForm.find('[name="keyword"]')){ // 검색 조건이 있을 때
			listForm.append($('<input/>',{type : 'hidden', name : 'type', value : '${criteria.type}'}))
					.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${criteria.keyword}'}))
		}		
	} 
	
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
	
	// 페이지 이동
	$('.pagination a').click(function(e){
		e.preventDefault();
		let pageNum = $(this).attr('href');
		listForm.find('input[name="pageNum"]').val(pageNum)
		searchCondition();
		listForm.submit();
	});	

	// 조회 페이지로 이동 
		$('.move').click(function(e){
			e.preventDefault();
			let bnoValue = $(this).attr('href');
			searchCondition();
			listForm.append($('<input/>',{type : 'hidden', name : 'bno', value : bnoValue}))
					.attr('action','${ctxPath}/announce/get')
					.submit();
		});
	
		// 검색 이벤트 처리 
		
		$('#searchForm button').click(function(e){
			e.preventDefault();
			if(!searchForm.find('option:selected').val()){
				alert('검색종류를 선택하세요');
				return; 
			}
			if(!searchForm.find('[name="keyword"]').val()){
				alert('키워드를 입력하세요');
				return; 
			}
			searchForm.find('[name="pageNum"]').val(1); 
			searchForm.submit();
		});	
})
</script>