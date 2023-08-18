<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp"%>

<div class="container login_area d-flex justify-content-center align-items-center">
	<div class="w-50">
		<h1 class="text-center py-3">로그인 페이지</h1>
		<form method="post" action="${ctxPath}/user/login">
			<div class="form-group">
				<input type="text" class="form-control" name="memberId" value="${memberId}" placeholder="아이디">
			</div>
			<div class="form-group">
				<input type="password" class="form-control"  name="memberPwd" placeholder="비밀번호">
			</div>
			<c:if test="${not empty LoginFail}">
				<p style="color: red; font-size: 10px">${LoginFail}</p>
			</c:if>
			<label>
				<input type="checkbox" name="remember-me" class="mr-2">자동 로그인
			</label>
			<button class="form-control btn btn-outline-primary" >로그인</button>
			<a href="${ctxPath}/findMemberInfo" class="py-3">아이디 찾기 또는 비밀번호 재발급</a>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>
</div>

<style>
.login_area {
	height: 50vh
}	
</style>