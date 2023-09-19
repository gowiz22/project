<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<style>
/* 테이블 경계선 스타일 지정 */
table {
    border-collapse: collapse;
    width: 50%;
}

table, th, td {
    border: 1px solid black;
}

/* <th> 태그 사이 간격 조정 */
th {
    padding: 5px; /* 원하는 간격 크기 조정 */
}

</style>

<div class="container">
	<h3>인기 게시글</h3>
	<table>
		<tr>
			<th>
				<a href="${ctxPath}">실시간</a>
			</th>
			<th>
				<a href="${ctxPath}">일간</a>
			</th>
			<th>
				<a href="${ctxPath}">주간</a>
			</th>
			<th>
				<a href="${ctxPath}">월간</a>
			</th>
		</tr>
		<div class="List"></div>
		<tr>
			<td colspan="4">
				gd
			</td>
		</tr>
	</table>
	
</div>
<%@ include file="includes/footer.jsp"%>

<script>
window.onload = function() {
	let validMessage = "${checkValid}"
	if(validMessage) {
		alert(validMessage)
	}
}
</script>