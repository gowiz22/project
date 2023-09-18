<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>

<div class="container">

	<div class="w-50 mx-auto my-5">
		<h1 class="text-center py-3">회원가입</h1>
		<form:form action="${ctxPath}/member/join" modelAttribute="memberVO">
			<div class="form-groupp row">
			<div class="col-9">
				<form:input class="form-control" path="memberId" placeholder="아이디"/>
			</div>
			<div class="col-3">
				<button type="button" class="idCheck btn btn-outline-info form-control">ID중복확인</button>
			</div>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="memberName" placeholder="이름"/>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="email" placeholder="이메일" readonly="true"/>
			</div>
			<div class="form-group">
				<span class="validMessage"></span>
				<form:password class="form-control password" path="memberPwd" placeholder="비밀번호" maxlength="15"/>
				<span style="font-size: 12px;">비밀번호는 8자리 이상 15자리 미만 숫자+영문+특수문자 조합이어야 합니다.</span>
			</div>
			<button type="button" class="btn btn-outline-primary join">회원가입</button>
		</form:form>
	</div>
</div>
<%@ include file="../includes/footer.jsp"%>


<script>
$(function(){
	let idCheckFlag = false
	let checkPwd = false
	
	$('.idCheck').click(function(){
		let idInput = $('#memberId');
		let memberId = idInput.val();
		
		if(idInput.attr('readonly')){ // 이미 값이 입력된 경우
			idInput.attr('readonly',false);
			idInput.focus();
			$(this).html('ID중복확인');
			idCheckFlag = false;
			return;
		}
		
		if(memberId==''){
			alert('아이디를 입력하세요');
			return;
		}
		
		// 아이디 중복 검사 
		$.ajax({
			type : 'post', 
			url : '${ctxPath}/member/idCheck',
			data : {memberId : memberId}, 
			success :function(result){
				if(result){ // 사용가능한 경우(True) 
					 alert('사용할 수 있는 아이디 입니다.');
					 idCheckFlag = true;
					 $('.idCheck').html('변경');
					 idInput.attr('readonly',true);
				} else { // 중복 되는 경우(False) 
					alert('사용할 수 없는 아이디 입니다.');
					idInput.focus();
				}
			}
		})
	});
	
	$('.password').on('keyup',function(){
		let inputPwd = $(this).val()
		let validMessage = $('.validMessage')
		let reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/

		
		if (!reg.test(inputPwd)) {
			checkPwd = false
			validMessage.html('유효하지않은 비밀번호입니다.')
			validMessage.css('color','red')
			$(this).removeClass('border-success')
					.addClass('border border-danger')
					.css('box-shadow','0 0 0 0.2rem rgba(255,0,0,.25)')
		} else {
			checkPwd = true
			validMessage.html('유효한 비밀번호입니다.')
			validMessage.css('color','green')
			$(this).removeClass('border-danger')
				.addClass('border border-success')	
				.css('box-shadow','0 0 0 0.2rem rgba(0,128,0,.25)')
		}
	})
	
	
	$('.join').click(function(){
		
		if(!idCheckFlag){
			alert('ID중복 확인해주세요!')
			return
		}
		
		if(!checkPwd) {
			alert('비밀번호를 확인해주세요!')
			return
		}
		
		$('#memberVO').submit();			
	})
});
</script>