<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 공통
</li>


<a href="${ctx}/People/index.people" class="subM">
	<li>부서별 정렬</li>
</a>
<a href="${ctx}/People/position.people" class="subM">
	<li>직책별 정렬</li>
</a>
