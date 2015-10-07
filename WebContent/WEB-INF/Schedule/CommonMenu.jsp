<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="mem_list" value="${MEM_LIST}" />
<c:set var="dep_no" value="${MY_DEP_NO}" />

<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 개인 스케줄
</li>
<a href="${ctx}/Schedule/index.schedule" class="subM">
	<li>스케줄 관리</li>
</a>

<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 부서 스케줄
</li>
<a href="${ctx}/Schedule/index.schedule?dep=${dep_no}" class="subM">
	<li>부서 스케줄</li>
</a>

<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 부서원 스케줄
</li>
<c:forEach items="${mem_list}" var="memb_list">
	<a href="${ctx}/Schedule/index.schedule?no=${memb_list.mb_no}" class="subM">
		<li>${memb_list.posi_name} ${memb_list.mb_name}</li>
	</a>
</c:forEach>