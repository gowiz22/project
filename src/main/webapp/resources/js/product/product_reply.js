$(function(){
	
	let pnoValue = $('[name="pno"]').val()
	let replyContainer = $('.chat');
	let pageNum = 1; // 기본 페이지 번호
	let paginationWrap = $('.pagination_wrap');  
	let showReplyPage = function(replyCount){
		let endNum = Math.ceil(pageNum/10.0)*10;
		let startNum = endNum - 9;
		let tempEndNum = Math.ceil(replyCount/10.0);
		
		let prev = startNum !=1; 
		let next = endNum < tempEndNum;
		if(endNum>tempEndNum) endNum = tempEndNum;
		
		let pagination = '<ul class="pagination">';
		
		if(prev){ // 이전 버튼 
			pagination += `<li class="page-item">
					<a class="page-link" href="${startNum-1}">이전</a></li>`
		}
		for(let pageLink=startNum; pageLink<= endNum ; pageLink++){ // 페이지 버튼
			let active = (pageNum==pageLink) ? 'active':''; // 현재페이지버튼 활성화
			pagination += `<li class="page-item ${active}">
					<a class="page-link" href="${pageLink}">${pageLink}</a></li>`
		}
		if(next){ // 다음 버튼 
			pagination += `<li class="page-item">
					<a class="page-link" href="${endNum+1}">다음</a></li>`
		}
		pagination += '</ul>'
		paginationWrap.html(pagination);
	} 
	
	let showList = function(page){
		let param = {pno:pnoValue, page : page||1};
		replyService.getList(param,function(replyCount,list){
			
			if(page == -1){ // 글 작성후 마지막 페이지 호출
				pageNum = Math.ceil(replyCount/10.0);
				showList(pageNum); 
				return;
			}
			if(list==null||list.length==0) {
				replyContainer.html('등록된 댓글이 없습니다.');
				return;
			};
		
			let replyList='';
			$.each(list,function(idx,elem){
				replyList += `<li class="list-group-item" data-rno="${elem.rno}" >
					<div class="d-flex justify-content-between">
					  <div class="d-flex">
					    <div class="user_image mr-3" style="width: 75px">
					      <img class="rounded-circle" src="${ctxPath}/resources/images/userImage.png" style="width: 100%">
					    </div>
					    <div class="comment_wrap">
					      <div class="comment_info">
					        <span class="user_score badge badge-pill badge-warning mr-2">${elem.score}점</span>
					        <span class="userName badge badge-pill badge-info mr-2">${elem.reviewer}</span>
					        <span class="badge badge-dark">${elem.regDate}</span>
					      </div>
					      <div class="comment_content py-2">${elem.r_comment}</div>
					    </div>
					  </div>`
					  
				if(memberId==elem.reviewer || auth.includes('ROLE_ADMIN')){
				replyList +=					  
					  `<div class="reply_modify">
					    <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">변경</button>
					    <div class="dropdown-menu">						   
					      <a class="dropdown-item" href="modify">수정</a>
					      <a class="dropdown-item" href="delete">삭제</a>
					    </div>
					  </div>`
					  }
				replyList += 
					 `</div>
					</li>`
			});
			replyContainer.html(replyList);
			showReplyPage(replyCount);
		});
	} // showList function end
	showList(1);
	
	// 페이지 이동 이벤트
	paginationWrap.on('click','li a', function(e){
		e.preventDefault();
		let targetPageNum = $(this).attr('href');
		pageNum = targetPageNum;
		showList(pageNum);
	});

	// 댓글 추가 
	$('.submit button').click(function(){	
		let pno = {
			pno : pnoValue
		}
		
			// 댓글 작성 확인
		replyService.getReviewerList(pno, function (reviewers) {
			if(reviewers.includes(memberId)) {
				alert("이미 작성한 리뷰가 있습니다.")
			} else {
			
				let reply = { // 입력 데이터 객체  
					pno : pnoValue, // 게시물 번호 
					r_comment : $('.r_comment').val(), // 내용
					reviewer : $('.reviewer').html(),	// 작성자
					score : $('.score').val()
				}
					
				replyService.add(reply,function(result){ // 댓글 추가 처리 
					if(result=='success'){
						alert('댓글을 등록하였습니다.');
					} else {
						alert('댓글 등록 실패');
					}
					$('.replyContent').val(''); // 댓글입력창 초기화 
					showList(1); // 목록 갱신		
				});
			}
		})
	});	
	
	// 댓글 수정 및 삭제 처리 
	$('.chat').on('click','.reply_modify a',function(e){
		e.preventDefault();// a태그  기본동작 금지
		
		let reviewer = $(this).closest('li').find('.userName').text();
		// 작성자가 일치하지 않거나 관리자가 아니면
		if(reviewer!=memberId && !auth.includes('ROLE_ADMIN')){
			return;
		}
		
		let rno = $(this).closest('li').data('rno'); // 댓글 번호 가져오기
		let operation = $(this).attr('href');// 수정/삭제 동작 결정
		
		if(operation=='delete'){ // 삭제 처리 
			replyService.remove(rno,function(result){
				if(result=='success'){
					alert(rno+'번 댓글을 삭제하였습니다.');
					showList(pageNum); // 목록 갱신
				} else {
					alert('댓글 삭제 실패');
				}
			});
			return; 
		}
	
		if(operation=='modify'){ // 수정처리 
					
			let replyUpdateForm = $('.replyWriterForm').clone(); // 댓글쓰기폼 복사.
			replyUpdateForm.attr('class','replyUpdateForm'); // 클래스명 변경
			
			let updateBtn = replyUpdateForm.find('.submit button').html('수정'); // 수정처리 버튼
			
			let listTag = $(this).closest('li'); // 현재 댓글의 li 태그를 찾음 
			let replyUpdateFormLength = listTag.find('.replyUpdateForm').length; // 댓글수정폼 존재 여부
			if(replyUpdateFormLength>0) { // 댓글수정폼이 추가되어있다면 
				listTag.find('.replyUpdateForm').remove(); // 기존의 수정폼 삭제
				$(this).html('수정'); // 취소 버튼을 수정버튼으로 
				$(this).next().show(); // 삭제 버튼 다시 보이게 
				return;
			} 
			
			// 조회 메소드 호출 :  댓글 조회 후 수정폼에 나타냄 
			replyService.get(rno,function(data){
				console.log(data)
				replyUpdateForm.find('.r_comment').val(data.r_comment)
				replyUpdateForm.find('.reviewer').html(data.reviewer)
				replyUpdateForm.find('#score').val(data.score)
				replyUpdateForm.find('.score').val(data.score)
				
			// 수정 테이블에서 score 드롭다운 변경시
				$('#score').change(function(){
					let score = $(this).val()
					$('.score').val(score)
				})
			})	
			
			$(this).closest('li').append(replyUpdateForm); // 아래에 추가
			$(this).html('취소'); // 수정 버튼을 취소버튼으로 변경
			$(this).next().hide(); // 삭제 버튼 숨김
			
			updateBtn.click(function(){ // 수정 처리 이벤트 
				let replyVO = { // 수정 처리 메소드 매개값
					rno :  rno,
					r_comment : replyUpdateForm.find('.r_comment').val(),
					score : replyUpdateForm.find('.score').val()
				} 
				// 수정 처리  메소드 호출
				replyService.update(replyVO, function(result){
					alert(result);
					showList(pageNum); // 목록 갱신
				});
			})			
			return; 
		}
		
	});
});