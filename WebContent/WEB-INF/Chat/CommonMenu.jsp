<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="unread" value="${UnreadMessage}" />

<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 메시지
</li>


<a href="${ctx}/Chat/index.chat" class="subM">
	<c:if test="${unread == 0}">
		<li>받은 쪽지함</li>
	</c:if>
	<c:if test="${unread != 0}">
		<li>받은 쪽지함 ( ${unread} )</li>
	</c:if>
	
</a>
<a href="${ctx}/Chat/Send.chat" class="subM">
	<li>보낸 쪽지함</li>
</a>
<a href="${ctx}/Chat/Storage.chat" class="subM">
	<li>보관함</li>
</a>
