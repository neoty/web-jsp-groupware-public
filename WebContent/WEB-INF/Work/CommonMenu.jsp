<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 공통
</li>


<a href="WorkTime.work" class="subM">
	<li>출근 관리</li>
</a>
<a href="index.work" class="subM">
	<li>배차 관리</li>
</a>
<a href="WorkFlowIndex.work" class="subM">
	<li>결재 관리</li>
</a>
<a href="WorkFlowNotice.work" class="subM">
	<li>결재 공지</li>
</a>
