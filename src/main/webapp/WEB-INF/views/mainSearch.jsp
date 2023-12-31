<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<div class="container">
	<h3>"${criteria.keyword}" 검색 결과입니다.</h3>
	<div class="card-header">공지사항</div>
	<div class="card-body">
		<table>
			<c:choose>
				<c:when test="${empty result_anno}">
		            <tr>
		                <td colspan="5">
		                    <h5>검색 결과가 없습니다.</h5>
		                </td>
		            </tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${result_anno}" var="board" varStatus="loop">
					    <c:if test="${loop.index < 5}">
					        <tr>
					            <td>${board.bno}</td>
					            <td>
					                <a class="move" href="announce/get?bno=${board.bno}">${board.title}</a>
					            </td>
					            <td>${board.writer}</td>
					            <td><tf:formatDateTime value="${board.regDate}" pattern="yyyy-MM-dd HH:mm"/></td>
					            <td><tf:formatDateTime value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm"/></td>
					        </tr>
					    </c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	
	<div class="card-header">자유게시판</div>
	<div class="card-body">
		<table>
			<c:choose>
				<c:when test="${empty result_free}">
		            <tr>
		                <td colspan="5">
		                    <h5>검색 결과가 없습니다.</h5>
		                </td>
		            </tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${result_free}" var="board" varStatus="loop">
					    <c:if test="${loop.index < 5}">
					        <tr>
					            <td>${board.bno}</td>
					            <td>
					                <a class="move" href="free/get?bno=${board.bno}">${board.title}</a>
					            </td>
					            <td>${board.writer}</td>
					            <td><tf:formatDateTime value="${board.regDate}" pattern="yyyy-MM-dd HH:mm"/></td>
					            <td><tf:formatDateTime value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm"/></td>
					        </tr>
					    </c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	
	<div class="card-header">제품 리뷰</div>
	<div class="card-body">
		<table>
			<c:choose>
				<c:when test="${empty result_product}">
		            <tr>
		                <td colspan="5">
		                    <h5>검색 결과가 없습니다.</h5>
		                </td>
		            </tr>
				</c:when>
				<c:otherwise>		
				<c:forEach items="${result_product}" var="product" varStatus="loop">
				    <c:if test="${loop.index < 5}">
				        <tr>
				            <td rowspan="2">
								<c:forEach items="${Thumbnail}" var="thumb">
									<c:if test="${product.pno == thumb.pno}">
							            <c:set var="filePath" value="${thumb.uploadPath}/${thumb.uuid}_${thumb.fileName}" />
										<div class="image-container">
											<img class="thumbnail-image" alt="썸네일" 
												src="${ctxPath}/product/files/display?fileName=${filePath}" onclick="imageClick()">
										</div>								
									</c:if> 
								</c:forEach>
	
							</td>
				            <td>
				                <a class="move" href="product/get?pno=${product.pno}">${product.p_name}</a>
				            </td>
				            <td>${product.writer}</td>
				            <td><tf:formatDateTime value="${product.regDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				            <td><tf:formatDateTime value="${product.updateDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				        </tr>
				        <tr>
				        	<td>${product.detail}</td>
				        </tr>
				    </c:if>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</div>

	<h2></h2>
		<h3></h3>
</div>
<%@ include file="includes/footer.jsp"%>