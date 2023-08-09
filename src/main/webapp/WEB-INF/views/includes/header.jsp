<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Web Site</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="">
		<div class="d-inline-block">
	        <a class="nav-link" href="${ctxPath == '' ? '/': ctxPath}">petti</a>
	    </div>       
	    <div class="d-inline-block">
	         <input type="text" name="main_search" class="" placeholder="검색할 내용을 입력해주세요">
		</div>
		<div class="d-inline-block">
			<button class="btn btn-primary">검색</button>
		</div>
	</div>
<nav class="navbar navbar-expand-sm bg-light">
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
            <a class="nav-link" href="${ctxPath}/member/mypage">마이페이지</a>
        </li>
    </ul>
</nav>