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
					<div class="board-entry" style="display: flex; justify-content: space-between;">
					    <div class="d-inline-block">
					        <c:forEach items="${category}" var="c">
					            <c:if test="${board.cno eq c.cno}">
					                [${c.kind}]
					            </c:if>
					        </c:forEach>
					    </div>
					    <div class="d-inline-block">
					        no.${board.bno}
					    </div>
					    <div class="d-inline-block" style="text-align: center;">
					        ${board.title}       
					    </div>
					    <div class="d-inline-block" style="margin-left: auto;">
					        ${board.writer}
					    </div>
					</div>
				</div>
				<div class="card-body">
					<div class="form-group">
						<textarea class="form-control" rows="10" name="content" readonly="readonly">${board.content}</textarea>
					</div>
					<div class="getBtns">
					<sec:authorize access="isAuthenticated() and principal.username== #board.writer or hasRole('ROLE_ADMIN')">
						<button data-oper='modify' class="btn btn-light modify">수정페이지</button>
					</sec:authorize>	
						<button data-oper='list' class="btn btn-info list">목록으로</button>
						<div class="text-center">
							<sec:authorize access="isAuthenticated()">
							    <c:forEach items="${likeUser}" var="user">
							    	<c:choose>	
							            <c:when test="${authInfo.memberId == user}">
							                <a class="btn btn-outline-danger like" data-oper="unlike">추천 취소</a>
							            </c:when>
						            </c:choose>
					            </c:forEach>
									<c:choose>
							            <c:when test="${not likeUser.contains(authInfo.memberId)}">
							                <a class="btn btn-outline-primary like" data-oper="like">추천</a>
							            </c:when>
						            </c:choose>
							</sec:authorize>						
						</div>
					</div>				
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
	
	$('.getBtns button').click(function(){
		
		let operration = $(this).data('oper')
		let type = '${criteria.type}'
		let keyword = '${criteria.keyword}'
		let recommend = '${criteria.recommend}'
		
		getForm.append($('<input/>',{type : 'hidden', name : 'pageNum', value : '${criteria.pageNum}'}))
				.append($('<input/>',{type : 'hidden', name : 'amount', value : '${criteria.amount}'}))
				.attr('method','get')
				
		if(type&&keyword){
			getForm.append($('<input/>',{type : 'hidden', name : 'type', value : '${criteria.type}'}))
					.append($('<input/>',{type : 'hidden', name : 'keyword', value : '${criteria.keyword}'}))
		}

		if(recommend=="T") {
			getForm.append($('<input/>',{type : 'hidden', name : 'recommend', value : '${criteria.recommend}'}))
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
		var oper = this.getAttribute("data-oper")
		
		if(oper === "unlike") {
			this.textContent = "추천";
			this.classList.remove("btn-outline-danger");
			this.classList.add("btn-outline-primary");
			this.setAttribute("data-oper", "like");
	    } else if (oper === "like") {
	    	this.textContent = "추천 취소";
	    	this.classList.remove("btn-outline-primary");
	    	this.classList.add("btn-outline-danger");
	    	this.setAttribute("data-oper", "unlike");
	    }
	})
	
})
</script>

<script src="${ctxPath}/resources/js/free/free_get.js"></script>
<script src="${ctxPath}/resources/js/free/free_replyService.js"></script>
<script src="${ctxPath}/resources/js/free/free_reply.js"></script>