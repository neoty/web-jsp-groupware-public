<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/config.css" />
<div id="config-main">

	<div id="config-status-title" style="border-bottom: none;">
		차량 수정
	</div>
	<div id="config-community-title">
		<div class="community-title-1">
			코드
		</div>
		<div class="community-title-2">
			차량 번호판
		</div>
		<div class="community-title-3">
			차량 모델
		</div>
		<div class="community-title-4">
			차량 킬로수
		</div>
		<div class="community-title-5">
			차량 사용 여부
		</div>
	</div>
	<c:if test="${fn:length(carList) == 0 }">
	<div style="text-align: center; margin-top: 10px;">생성된 차량 정보가 없습니다. </div>
	</c:if>
	
	<c:if test="${fn:length(carList) > 0 }">
	<c:forEach var="n" items="${carList}">
	<form action="${ctx}/Config/CarMake.config" method="post">
	<input type="hidden" name="mode" value="MODIFY">
	<input type="hidden" name="cl_no" value="${n.cl_no}">
		<div id="config-community-content">
			<div class="community-title-1" style="font-size: 9px; font-family: verdana, tahoma; font-weight: bold;">
				${n.cl_no}
			</div>
			<div class="community-title-2">
				${n.cl_number}
			</div>
			<div class="community-title-3">
				${n.cl_model}
			</div>
			<div class="community-title-4">
				<span style="font-family: vardana, tahoma; font-size: 8pt; font-weight: bold;">${n.cl_mileage} km</span>
				<!-- <c:if test="${n.cl_use == 1 }"><b><font color="#E74C3C">사용</font></b></c:if>
				<c:if test="${n.cl_use == 0 }">미사용중</c:if>
				-->
				
			</div>
			<div class="community-title-5">
				<input type="submit" class="writecomplete" value="차량정보 수정" 
				style="width: 80px; height: 22px; margin-top: -10px;">
			</div>
		</div>
	</form>
	</c:forEach>
	</c:if>
	<br/>
		<div class="create">
		<form action="${ctx}/Config/CarMake.config" method="post">
			<input type="submit" class="writecomplete" value="차량 추가" style="clear: both; width: 200px;">
			<input type="hidden" name="mode" value="CREATE">
		</form>
	</div>
	<br/>
	<br/>
</div>