<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="list" value="${STORAGE_LIST}" />
<c:set var="total" value="${list[0].mes_total_cnt}" />
<c:set var="mb_no" value="${sessionScope.MB_NO}"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/chat.css" />
<div id="me-container">
	<form name="postData" method="post">
		<div id="me-header">
			<div id="me-header-menu">
				<input type="hidden" name="category" value="storage">
				<div id="me-header-delete" class="me-header-delete">
					<input class="backbtn" type="button" name="delete" value="삭제" onclick="checkboxCheck(this, '${ctx}')">
				</div>
			</div>
		</div>
	
		<div id="me-content">
			<div id="me-content-title">
				<div id="me-content-storage-check" class="me-content-check">
					<input class="checkbox" type="checkbox" id="check-all" onclick="allCheck()">
				</div>
				<div id="me-content-storage-sender" class="me-content-sender">보낸 사람</div>
				<div id="me-content-storage-receiver" class="me-content-receiver">받는 사람</div>
				<div id="me-content-storage-substance" class="me-content-substance">내 용</div>
				<div id="me-content-storage-date" class="me-content-date">날 짜</div>
			</div>
			<c:forEach items="${list}" var="list">
				<c:if test="${list.mes_no == 0}">
					<div id="me_empty_message">${list.mes_subject}</div>
				</c:if>
				<c:if test="${list.mes_no != 0}">
					<div id="me-content-detail">
						<div id="me-content-storage-check" class="me-content-check">
							<input class="checkbox" name="check-delete[]" type="checkbox" value="${list.mes_no}">
						</div>
						<c:if test="${list.mes_post_mb_no != mb_no}">
							<c:choose>
								<c:when test="${list.mes_post_mb_img == '' || empty list.mes_post_mb_img || list.mes_post_mb_img != null}">
									<a href="${ctx}/Chat/Write.chat?no=${list.mes_post_mb_no}" class="screenshot" rel="${ctx}/images/noimg.png">
								</c:when>
								<c:otherwise>
									<a href="${ctx}/Chat/Write.chat?no=${list.mes_post_mb_no}" class="screenshot" rel="${ctx}/MemberImg/${list.mes_post_mb_img}">
								</c:otherwise>
							</c:choose>
						</c:if><div id="me-content-storage-sender" class="me-content-sender">${list.mes_post_mb_posi_name} ${list.mes_post_mb_name}</div></a>
						
						<c:if test="${list.mes_get_mb_no != mb_no}">
							<c:choose>
								<c:when test="${list.mes_get_mb_img == '' || empty list.mes_get_mb_img || list.mes_get_mb_img != null}">
									<a href="${ctx}/Chat/Write.chat?no=${list.mes_get_mb_no}" class="screenshot" rel="${ctx}/images/noimg.png">
								</c:when>
								<c:otherwise>
									<a href="${ctx}/Chat/Write.chat?no=${list.mes_get_mb_no}" class="screenshot" rel="${ctx}/MemberImg/${list.mes_get_mb_img}">
								</c:otherwise>
							</c:choose>
						</c:if><div id="me-content-storage-receiver" class="me-content-receiver">${list.mes_get_mb_posi_name} ${list.mes_get_mb_name}</div></a>
						
						
						<a href="${ctx}/Chat/View.chat?no=${list.mes_no}"><div id="me-content-storage-substance" class="me-content-substance">${list.mes_subject}</div></a>
						<div id="me-content-storage-date" class="me-content-date">${list.mes_post_date}</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</form>
	
		<div class="paginate_simple" style="padding-top:20px;">
			<c:if test="${page >= 11}">
				<a href="${ctx}/Chat/Storage.chat?page=${page-10}" class="direction"> ‹ <span>Prev</span></a>
			</c:if>
			
			<c:forEach var="i" step="1" begin="0" end="9">
				<c:if test="${start+i <= end}">
					<c:if test="${start+i == page}">
						<strong>${start+i}</strong>
					</c:if>
					<c:if test="${start+i != page}">
						<a href="${ctx}/Chat/Storage.chat?page=${start+i}">${start+i}</a>
					</c:if>
				</c:if>
				
			</c:forEach>
			<c:if test="${start+10 < end}">
				<a href="${ctx}/Chat/Storage.chat?page=${start+10}" class="direction"><span>Next</span> › </a>
			</c:if>
		</div>
</div>