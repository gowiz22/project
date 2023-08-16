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
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Web Site</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
	var ctxPath = '${ctxPath}'
	let duplicateLogin = '${duplicateLogin}'
	
	if(duplicateLogin) {
		alert(duplicateLogin)
	}
</script>
</head>
<body>
	<div align="center">
		<div class="d-inline-block">
	        <a class="nav-link" href="${ctxPath == '' ? '/': ctxPath}">petti</a>
	    </div>   
	    <div class="d-inline-block col-8">    
			<form class="my-3" id="mainSearch" action="${ctxPath}/mainSearch">
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
				<div class="d-inline-block col-4">
					<input type="text" name="keyword" class="form-control" placeholder="통합 검색">
				</div>
				<div class="d-inline-block">
					<button class="btn btn-primary">검색</button>
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
            <a class="nav-link" href="${ctxPath}/review/list">제품 리뷰</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${ctxPath}/mypage">마이페이지</a>
        </li>
    </ul>
</nav>
<nav>
	<div align="right">
	  <ul class="navbar-nav">
		<sec:authorize access="isAnonymous()">
		  	<li class="nav-item">
		          <a class="nav-link" href="${ctxPath}/login">로그인</a>
		    </li>
	    </sec:authorize>
		<sec:authorize access="isAuthenticated()">
		    <li class="nav-item">
		       <a class="nav-link logout" href="${ctxPath}/member/logout">로그아웃</a>
		    </li>
	    </sec:authorize> 
  		<sec:authorize access="isAnonymous()"> 
		  	<li class="nav-item">
		          <a class="nav-link" href="${ctxPath}/member/join">회원가입</a>
		    </li>
   	    </sec:authorize>
	  </ul>
	</div>
</nav>

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
	});
})
</script>