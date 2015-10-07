<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 공통
</li>


<a href="${ctx}/Config/index.config" class="subM">
	<li>정보수정 및 서버 상태</li>
</a>
<a href="${ctx}/Config/Community.config" class="subM">
	<li>커뮤니티 수정</li>
</a>
<a href="${ctx}/Config/Department.config" class="subM">
	<li>부서 수정</li>
</a>
<a href="${ctx}/Config/Position.config" class="subM">
	<li>직책 수정</li>
</a>
<a href="${ctx}/Config/Member.config" class="subM">
	<li>회원정보 수정</li>
</a>
<a href="${ctx}/Config/Car.config" class="subM">
	<li>차량 수정</li>
</a>
