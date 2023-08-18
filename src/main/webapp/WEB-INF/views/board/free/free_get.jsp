<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../includes/header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-12">
			<h1 class="page-header">자유 게시판</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-header">
					<c:forEach items="${category}" var="c">
						<c:if test="${board.cno eq c.cno}">
						[${c.kind}]		
						</c:if>
					</c:forEach>
				</div>
				<div class="card-body">
					<div class="form-group">
						<label>게시물 번호</label>	
						<input class="form-control" name="bno" value="${board.bno}" readonly="readonly"/>
					</div>
					<div class="form-group">
						<label>제목</label>
						<input class="form-control" name="title" value="${board.title}" readonly="readonly"/>
					</div>
					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="10" name="content" readonly="readonly">${board.content}</textarea>
					</div>
					<div class="form-group">
						<label>작성자</label>
						<input class="form-control" name="writer" value="${board.writer}" readonly="readonly"/>
					</div>
					<div class="getBtns">
					<sec:authorize access="isAuthenticated() and principal.username== #board.writer or hasRole('ROLE_ADMIN')">
						<button data-oper='modify' class="btn btn-light modify">수정페이지</button>
					</sec:authorize>	
						<button data-oper='list' class="btn btn-info list">목록으로</button>
						<div class="text-center">
							<sec:authorize access="isAuthenticated()">
							    <c:choose>
						            <c:when test="${authInfo.memberId == likeUser}">
						                <a class="btn btn-outline-danger like">추천 취소</a>
						            </c:when>
						            <c:otherwise>
						                <a class="btn btn-outline-primary like">추천</a>
						            </c:otherwise>
							    </c:choose>
							</sec:authorize>						
						</div>
					</div>				
				</div>
			</div>
		</div>
	</div>
	
	<h3 class="mt-5">댓글</h3>
	<div class="row">
		<div class="col-12">
			<ul class="list-group chat">
				<li class="list-group-item" data-rno="댓글번호" >
					<div class="d-flex justify-content-between">
					  <div class="d-flex">
					    <div class="user_image mr-3" style="width: 75px">
					      <img class="rounded-circle" src="${ctxPath}/resources/images/userImage.png" style="width: 100%">
					    </div>
					    <div class="comment_wrap">
					      <div class="comment_info">
					        <span class="userName badge badge-pill badge-info mr-2"></span>
					        <span class="badge badge-dark">2023-06-20 09:30</span>
					      </div>
					      <div class="comment_content py-2">댓글 내용입니다. </div>
					    </div>
					  </div>
					  <div class="reply_modify">
					    <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">변경</button>
					    <div class="dropdown-menu">						   
					      <a class="dropdown-item" href="modify">수정</a>
					      <a class="dropdown-item" href="delete">삭제</a>
					    </div>
					  </div>
					 </div>
				</li>
			</ul>		
		</div>
	</div>
	
	<div class="row mt-3">
		<div class="col-12 pagination_wrap"></div>
	</div>
		
	<!-- 댓글작성 -->	
	<div class="my-3 replyWriterForm">
		<sec:authorize access="isAnonymous()">
			<textarea  rows="6" placeholder="로그인한 사용자만 댓글을 쓸 수 있습니다." readonly="readonly" 
				maxlength="400" class="replyContent form-control"></textarea>		
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<textarea  rows="6" placeholder="댓글을 작성해주세요" 
				maxlength="400" class="replyContent form-control"></textarea>
			<div class="text-right">
				<div class="submit p-2">
					<span class="btn btn-outline-info col-2 replyer">${authInfo.memberId}</span>
					<button class="btn btn-outline-primary col-3">등록</button>
				</div>
			</div>
		</sec:authorize>
	</div>	
</div>

<form>
	<input type="hidden" name="bno"  value="${board.bno}">
</form>

<%@ include file="../../includes/footer.jsp" %>
<script>
$(function(){
	let getForm = $('form')
	
	$('.getBtns button').click(function(){
		
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


		if(operration=='list'){
			getForm.find('#bno').remove();
			getForm.attr('action','${ctxPath}/free/list')
		} else if(operration=='modify'){
			getForm.attr('action','${ctxPath}/free/modify')
		}
		getForm.submit();
	})

	$('.like').click(function(e){
		e.preventDefault()
		let bno = $('[name="bno"]').val()
		$.ajax({
			type : 'post',
			url : '${ctxPath}/free/like',
			data : { memberId : memberId,
					 bno : bno
					},
			success : function(message) {
				alert(message)
			}
		})
	})
	
})
</script>

<script src="${ctxPath}/resources/js/free_replyService.js"></script>
<script src="${ctxPath}/resources/js/free_reply.js"></script>