<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="list" value="${SEND_LIST}" />
<c:set var="total" value="${list[0].mes_total_cnt}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/chat.css" />

<div id="me-container">
	<form name="postData" method="post">
		<div id="me-header">
			<div id="me-header-menu">
				<input type="hidden" name="category" value="send">
				<div class="me-header-delete">
					<input class="backbtn" type="button" name="delete" value="삭제" onclick="checkboxCheck(this, '${ctx}')">
				</div>
				<div class="me-header-storage">
					<input class="backbtn" type="button" name="storage" value="보관하기" onclick="checkboxCheck(this, '${ctx}')">
				</div>
			</div>
		</div>
	
		<div id="me-content">
			<div id="me-content-title">
				<div id="me-content-send-check" class="me-content-check">
					<input class="checkbox" type="checkbox" id="check-all" onclick="allCheck()">
				</div>
				<div id="me-content-send-receiver" class="me-content-receiver">받는 사람</div>
				<div id="me-content-send-substance" class="me-content-substance">내 용</div>
				<div id="me-content-send-date" class="me-content-date">보낸 날짜</div>
			</div>
			
			<c:forEach items="${list}" var="list">
				<c:if test="${list.mes_no == 0}">
					<div id="me_empty_message">${list.mes_subject}</div>
				</c:if>
				<c:if test="${list.mes_no != 0}">
					<div id="me-content-detail">
						<div id="me-content-send-check" class="me-content-check">
							<input class="checkbox" name="check-delete[]" type="checkbox" value="${list.mes_no}">
						</div>
						<c:choose>
							<c:when test="${list.mes_get_mb_img == '' || empty list.mes_get_mb_img || list.mes_get_mb_img != null}">
								<a href="${ctx}/Chat/Write.chat?no=${list.mes_get_mb_no}" class="screenshot" rel="${ctx}/images/noimg.png"><div id="me-content-send-receiver" class="me-content-receiver">${list.mes_get_mb_posi_name} ${list.mes_get_mb_name}</div></a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/Chat/Write.chat?no=${list.mes_get_mb_no}" class="screenshot" rel="${ctx}/MemberImg/${list.mes_get_mb_img}"><div id="me-content-send-receiver" class="me-content-receiver">${list.mes_get_mb_posi_name} ${list.mes_get_mb_name}</div></a>
							</c:otherwise>
						</c:choose>
						<a href="${ctx}/Chat/View.chat?no=${list.mes_no}"><div id="me-content-send-substance" class="me-content-substance">${list.mes_subject}</div></a>
						<div id="me-content-send-date" class="me-content-date">${list.mes_post_date}</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</form>
	
		<div class="paginate_simple" style="padding-top:20px;">
			<c:if test="${page >= 11}">
				<a href="${ctx}/Chat/Send.chat?page=${page-10}" class="direction"> ‹ <span>Prev</span></a>
			</c:if>
			
			<c:forEach var="i" step="1" begin="0" end="9">
				<c:if test="${start+i <= end}">
					<c:if test="${start+i == page}">
						<strong>${start+i}</strong>
					</c:if>
					<c:if test="${start+i != page}">
						<a href="${ctx}/Chat/Send.chat?page=${start+i}">${start+i}</a>
					</c:if>
				</c:if>
				
			</c:forEach>
			<c:if test="${start+10 < end}">
				<a href="${ctx}/Chat/Send.chat?page=${start+10}" class="direction"><span>Next</span> › </a>
			</c:if>
		</div>
</div>