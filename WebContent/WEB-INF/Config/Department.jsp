<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/config.css" />
<div id="config-main">

	<div id="config-status-title" style="border-bottom: none;">
		부서 수정
	</div>
	<div id="config-community-title">
		<div class="community-title-1">
			코드
		</div>
		<div class="community-title-2">
			부서명 이름
		</div>
		<div class="community-title-3">
			부서 설명
		</div>
		<div class="community-title-4">
			사용 여부
		</div>
		<div class="community-title-5">
			부서 수정
		</div>
	</div>
	<c:if test="${fn:length(departmentList) == 0 }">
	<div style="text-align: center; margin-top: 10px;">생성된 부서가 없습니다. </div>
	</c:if>
	<c:if test="${fn:length(departmentList) > 0 }">
	<c:forEach var="n" items="${departmentList}">
	<form action="${ctx}/Config/DepartmentMake.config?mode=MODIFY&dep_no=${n.dep_no}" method="post">
		<div id="config-community-content">
			<div class="community-title-1" style="font-size: 9px; font-family: verdana, tahoma; font-weight: bold;">
				${n.dep_no}
			</div>
			<div class="community-title-2">
				${n.dep_name}
			</div>
			<div class="community-title-3">
				${n.dep_describe}
			</div>
			<div class="community-title-4">
				<c:if test="${n.dep_use == 1 }"><b><font color="#E74C3C">사용</font></b></c:if>
				<c:if test="${n.dep_use == 0 }">미사용중</c:if>
			</div>
			<div class="community-title-5">
				<input type="submit" class="writecomplete" value="부서 수정" 
				style="width: 80px; height: 22px; margin-top: -10px;">
			</div>
		</div>
	</form>
	</c:forEach>
	</c:if>
	
	<br/>
		<div class="create">
		<form action="${ctx}/Config/DepartmentMake.config?mode=CREATE" method="post">
			<input type="submit" class="writecomplete" value="부서 만들기" style="clear: both; width: 200px;">
			<input type="hidden" name="community" value="PUBLIC">
		</form>
	</div>
	
</div>