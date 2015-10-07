<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ses_mb_no" value="${sessionScope.MB_NO}"/>
<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 공통
</li>
<c:forEach var="n" items="${menuList}">
	<c:if test="${n.bo_type == 'PUBLIC'}">
		<a href="${ctx}/Community/index.community?type=PUBLIC&code=${n.bo_no}" class="subM">
			<li>${n.bo_name}</li>
		</a>
	</c:if>
</c:forEach>

<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 부서
</li>
<c:set var="dep_count" value="0"/>
<c:forEach var="n" items="${menuList}">
	<c:if test="${n.bo_type == 'DEPARTMENT'}">
		<a href="${ctx}/Community/index.community?type=DEPARTMENT&code=${n.bo_no}" class="subM">
			<c:if test="${n.bo_admin == ses_mb_no}">
				<li><b>M - ${n.bo_name}</b></li>
			</c:if>
			<c:if test="${ses_mb_no != n.bo_admin}">
				<li>${n.bo_name}</li>
			</c:if>
		
		</a>
		<c:set var="dep_count" value="${dep_count + 1}"/>
	</c:if>
</c:forEach>
<c:if test="${dep_count == '0'}">
	<li>해당 부서 없음 </li>
</c:if>


<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 프로젝트
</li>
<c:set var="pro_count" value="0"/>
<c:forEach var="n" items="${menuList1}">
	<c:if test="${n.bo_type == 'PROJECT'}">
		<a href="${ctx}/Community/index.community?type=PROJECT&code=${n.bo_no}" class="subM">
			<c:if test="${n.bo_admin == ses_mb_no}">
				<li><b>M - ${n.bo_name}</b></li>
			</c:if>
			<c:if test="${ses_mb_no != n.bo_admin}">
				<li>${n.bo_name}</li>
			</c:if>
		</a>
		<c:set var="pro_count" value="${pro_count + 1}"/>
	</c:if>
</c:forEach>
<c:if test="${pro_count == '0'}">
	<li>해당 프로젝트 없음 </li>
</c:if>


<li
	style="text-align: left; background: #2B3A42; font-size: 13px !important;">
	<img src="${ctx}/images/pathicon.gif"> 동아리
</li>
<c:set var="circles_count" value="0"/>
<c:forEach var="n" items="${menuList1}">
	<c:if test="${n.bo_type == 'CIRCLES'}">
		<a href="${ctx}/Community/index.community?type=CIRCLES&code=${n.bo_no}" class="subM">
			<c:if test="${n.bo_admin == ses_mb_no}">
				<li><b>M - ${n.bo_name}</b></li>
			</c:if>
			<c:if test="${ses_mb_no != n.bo_admin}">
				<li>${n.bo_name}</li>
			</c:if>
		</a>
		<c:set var="circles_count" value="${circles_count + 1}"/>
	</c:if>
</c:forEach>
<c:if test="${circles_count == '0'}">
	<li>해당 동아리 없음 </li>
</c:if>

