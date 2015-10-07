<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="list" value="${RECEIVE_LIST}" />
<c:set var="total" value="${list[0].mes_total_cnt}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/chat.css" />

<div id="me-container">
	<form name="postData" method="post" onsubmit="return false">
		<div id="me-header">
			<div id="me-header-menu">
				<input type="hidden" name="category" value="index">
				<div class="me-header-delete">
					<input class="backbtn" type="button" name="delete" value="삭제" onclick="checkboxCheck(this, '${ctx}')">
				</div>
				<div class="me-header-storage">
					<input class="backbtn" type="button" name="storage" value="보관하기" onclick="checkboxCheck(this, '${ctx}')">
				</div>
				<div class="me-header-answer">
					<input class="backbtn" type="button" name="answer" value="답장" onclick="checkboxCheck(this, '${ctx}')">
				</div>
				<div class="me-header-write">
					<input class="writecomplete" type="button" name="write" value="쪽지쓰기" onclick="checkboxCheck(this, '${ctx}')">
				</div>
			</div>
		</div>
	
		<div id="me-content">
			<div id="me-content-title">
				<div id="me-content-receive-check" class="me-content-check">
					<input class="checkbox" type="checkbox" id="check-all" onclick="allCheck()">
				</div>
				<div id="me-content-receive-sender" class="me-content-sender">보낸 사람</div>
				<div id="me-content-receive-substance" class="me-content-substance">내 용</div>
				<div id="me-content-receive-date" class="me-content-date">수신 날짜</div>
			</div>
			
			<c:forEach items="${list}" var="list" >
				<c:if test="${list.mes_no == 0}">
					<div id="me_empty_message">${list.mes_subject}</div>
				</c:if>
				<c:if test="${list.mes_no != 0}">
					<div id="me-content-detail">
						<div id="me-content-receive-check" class="me-content-check" >
							<input class="checkbox" name="check-delete[]" type="checkbox" value="${list.mes_no}">
						</div>
						<c:choose>
							<c:when test="${list.mes_post_mb_img == '' || empty list.mes_post_mb_img || list.mes_post_mb_img != null}">
								<a href="${ctx}/Chat/Write.chat?no=${list.mes_post_mb_no}" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/images/noimg.png"><div id="me-content-receive-sender" class="me-content-sender">${list.mes_post_mb_posi_name} ${list.mes_post_mb_name}</div></a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/Chat/Write.chat?no=${list.mes_post_mb_no}" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/MemberImg/${list.mes_post_mb_img}"><div id="me-content-receive-sender" class="me-content-sender">${list.mes_post_mb_posi_name} ${list.mes_post_mb_name}</div></a>
							</c:otherwise>
						</c:choose>
						<c:if test="${list.mes_confirm_date == null}">
							<a href="${ctx}/Chat/View.chat?no=${list.mes_no}"><div id="me-content-receive-substance" class="me-content-substance" style="font-weight: bold;">${list.mes_subject}</div></a>
						</c:if>
						<c:if test="${list.mes_confirm_date != null}">
							<a href="${ctx}/Chat/View.chat?no=${list.mes_no}"><div id="me-content-receive-substance" class="me-content-substance">${list.mes_subject}</div></a>
						</c:if>
						
						<div id="me-content-receive-date" class="me-content-date">${list.mes_post_date}</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</form>
	
		<div class="paginate_simple" style="padding-top:20px;">
			<c:if test="${page >= 11}">
				<a href="${ctx}/Chat/index.chat?page=${page-10}" class="direction"> ‹ <span>Prev</span></a>
			</c:if>
			
			<c:forEach var="i" step="1" begin="0" end="9">
				<c:if test="${start+i <= end}">
					<c:if test="${start+i == page}">
						<strong>${start+i}</strong>
					</c:if>
					<c:if test="${start+i != page}">
						<a href="${ctx}/Chat/index.chat?page=${start+i}">${start+i}</a>
					</c:if>
				</c:if>
				
			</c:forEach>
			<c:if test="${start+10 < end}">
				<a href="${ctx}/Chat/index.chat?page=${start+10}" class="direction"><span>Next</span> › </a>
			</c:if>
		</div>
		
</div>