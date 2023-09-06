<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.memberVO" var="authInfo"/>
	<sec:authentication property="principal.memberVO.authList" var="authList"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Petti 홈페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script><script>

let ctxPath = '${ctxPath}'
let duplicateLogin = '${duplicateLogin}'

let memberId = "${authInfo.memberId}"
let auth = "${authList}"

let csrfHeaderName = "${_csrf.headerName}"; 
let csrfTokenValue = "${_csrf.token}"

if(duplicateLogin) {
	alert(duplicateLogin)
}

$(document).ajaxSend(function(e, xhr, options){
	xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
})

function checkExtension(fileName, fileSize){
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");	
	let maxSize = 10485760; // 10MB
	if(fileSize > maxSize) {
		alert('파일크기는 최대 10MB까지 업로드 가능합니다.');
		return false; 
	}
	
	if(regex.test(fileName)) {
		alert('해당 종류의 파일은 업로드 할 수 없습니다.');
		return false; 
	}
	return true;
}	
</script>
</head>
<body>
	<div align="center" class="mt-4 mr-7">
		<div class="d-inline-block">
	        <a class="nav-link" href="${ctxPath == '' ? '/': ctxPath}">
	        <img alt="logo" src="${ctxPath}/resources/images/logo.png" style="width: 100px;">
	        </a>
	    </div>   
	    <div class="d-inline-block col-5.5">    
			<form class="my-3 px-2" id="mainSearch" action="${ctxPath}/mainSearch">
				<div class="d-inline-block">
					<select name="type" class="form-control">
						<option value="T">제목</option>
						<option value="C">내용</option>
						<option value="W">작성자</option>
						<option value="TC" selected="selected">제목+내용</option>
						<option value="TW">제목+작성자</option>
						<option value="TCW">제목+내용+작성자</option>
					</select>
				</div>
				<div class="d-inline-block">
					<input type="text" name="keyword" class="form-control" placeholder="통합 검색">
				</div>
				<div class="d-inline-block">
					<button class="btn btn-warning">검색</button>
				</div>
			</form>
		</div>	
	</div>
	
<nav class="navbar navbar-expand-sm bg-light justify-content-center">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="${ctxPath}/announce/list">공지사항</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${ctxPath}/free/list">자유게시판</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${ctxPath}/product/list">제품 리뷰</a>
        </li>
       	<sec:authorize access="isAuthenticated() and !hasRole('ROLE_ADMIN')"> 
	        <li class="nav-item">
	            <a class="nav-link" href="${ctxPath}/mypage">마이페이지</a>
	        </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        	<li class="nav-item">
	            <a class="nav-link" href="${ctxPath}/admin">관리자페이지</a>
	        </li>
        </sec:authorize>
    </ul>
</nav>
	<div align="right">
		<sec:authorize access="isAnonymous()">
		  	<div class="d-inline-block">
	        	<a class="login" href="${ctxPath}/login">로그인</a>
		    </div>
	    </sec:authorize>
		<sec:authorize access="isAuthenticated()">
		    <div class="d-inline-block mr-2">
			<c:forEach items="${authList}" var="vo">
				<c:if test="${vo.auth == 'ROLE_ADMIN'}">
					<span class="badge badge-pill badge-danger">관리자</span>
				</c:if>
				<c:if test="${vo.auth == 'ROLE_MEMBER'}">
					<span class="badge badge-pill badge-warning">일반회원</span>
				</c:if>
				<c:if test="${vo.auth == 'ROLE_REVIEWER'}">
					<span class="badge badge-pill badge-success">리뷰어</span>
				</c:if>			
			</c:forEach>
		    	<span>${authInfo.memberId}님 반갑습니다.</span>
		    </div>
		    <div class="d-inline-block mr-5">
		    	<a class="logout" href="${ctxPath}/member/logout">로그아웃</a>
		    </div>
	    </sec:authorize> 
  		<sec:authorize access="isAnonymous()"> 
		  	<div class="d-inline-block mr-5">
		        <a class="m_join" href="${ctxPath}/join/step1">회원가입</a>
		    </div>
   	    </sec:authorize>
	</div>


<script>
//검색 이벤트 처리 
let mainSearch = $('#mainSearch');
$('#mainSearch button').click(function(e){
	e.preventDefault();
	if(!mainSearch.find('option:selected').val()){
		alert('검색종류를 선택하세요');
		return; 
	}
	if(!mainSearch.find('[name="keyword"]').val()){
		alert('키워드를 입력하세요');
		return; 
	}
	mainSearch.find('[name="pageNum"]').val(1);
	mainSearch.submit();
});

$(function(){
	$('.logout').click(function(e){
		e.preventDefault();
		let form = $('<form>',{action:$(this).attr('href'), method:'post'});
		form.append($('<input>',{type:'hidden',name:'${_csrf.parameterName}', value:'${_csrf.token}'}))
			.appendTo('body')
			.submit();
	})
})
</script>
<style>
    .image-container {
   	    display: flex; /* Flexbox 사용 */
    	align-items: center; /* 수직 가운데 정렬 */
    	justify-content: center; /* 수평 가운데 정렬 */
        width: 150px; /* 원하는 너비로 설정 */
        height: 100px; /* 원하는 높이로 설정 */
        overflow: hidden; /* 크기를 초과하는 부분을 숨김 */
    }

    .thumbnail-image {
        display: block; /* inline 요소에서 block 요소로 변경 */
  		margin: 0 auto; /* 가운데 정렬을 위한 마진 설정 */
        width: 100%; /* 부모 요소에 맞추어 이미지 너비 설정 */
        height: 100%; /* 부모 요소에 맞추어 이미지 높이 설정 */
        object-fit: cover; /* 이미지를 확대/축소하여 부모 요소를 채움 */
        object-position: center center; /* 이미지를 가운데 정렬 */
    }
</style>