<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/config.css" />
<div id="config-main">

	<div id="config-status-title" style="border-bottom: none;">
		공용 게시판
	</div>
	<div id="config-community-title">
		<div class="community-title-1">
			코드
		</div>
		<div class="community-title-2">
			게시판 이름
		</div>
		<div class="community-title-3">
			게시판 설명
		</div>
		<div class="community-title-4">
			사용 여부
		</div>
		<div class="community-title-5">
			게시판 수정
		</div>
	</div>
	<c:if test="${fn:length(publicList) == 0 }">
	<div style="text-align: center; margin-top: 10px;">생성된 공용 게시판이 없습니다. </div>
	</c:if>
	<c:if test="${fn:length(publicList) > 0 }">
	<c:forEach var="n" items="${publicList}">
	<form action="${ctx}/Config/CommunityMake.config?community=PUBLIC&mode=MODIFY&bo_no=${n.bo_no}" method="post">
		<div id="config-community-content">
			<div class="community-title-1" style="font-size: 9px; font-family: verdana, tahoma; font-weight: bold;">
				${n.bo_no}
			</div>
			<div class="community-title-2">
				${n.bo_name}
			</div>
			<div class="community-title-3">
				${n.bo_describe}
			</div>
			<div class="community-title-4">
				<c:if test="${n.bo_use == 1 }"><b><font color="#E74C3C">사용</font></b></c:if>
				<c:if test="${n.bo_use == 0 }">미사용중</c:if>
			</div>
			<div class="community-title-5">
				<input type="submit" class="writecomplete" value="게시판 수정" 
				style="width: 80px; height: 22px; margin-top: -10px;">
			</div>
		</div>
	</form>
	</c:forEach>
	</c:if>
	
	<br/>
		<div class="create">
		<form action="${ctx}/Config/CommunityMake.config?community=PUBLIC&mode=CREATE" method="post">
			<input type="submit" class="writecomplete" value="공용 게시판 만들기" style="clear: both; width: 200px;">
			<input type="hidden" name="community" value="PUBLIC">
		</form>
	</div>
	<br/>
	<br/>
	
	
	
	<div id="config-status-title" style="border-bottom: none;">
		동아리 게시판
	</div>
	<div id="config-community-title">
		<div class="community-title-1">
			코드
		</div>
		<div class="community-title-2">
			게시판 이름
		</div>
		<div class="community-title-3">
			게시판 설명
		</div>
		<div class="community-title-4">
			사용 여부
		</div>
		<div class="community-title-5">
			게시판 수정
		</div>
	</div>
	<c:if test="${fn:length(circlesList) == 0 }">
	<div style="text-align: center; margin-top: 10px;">생성된 동아리 게시판이 없습니다. </div>
	</c:if>
	<c:if test="${fn:length(circlesList) > 0 }">
	<c:forEach var="n" items="${circlesList}">
	<form action="${ctx}/Config/CommunityMake.config?community=CIRCLES&mode=MODIFY&bo_no=${n.bo_no}" method="post">
		<div id="config-community-content">
			<div class="community-title-1" style="font-size: 9px; font-family: verdana, tahoma; font-weight: bold;">
				${n.bo_no}
			</div>
			<div class="community-title-2">
				${n.bo_name}
			</div>
			<div class="community-title-3">
				${n.bo_describe}
			</div>
			<div class="community-title-4">
				<c:if test="${n.bo_use == 1 }"><b><font color="#E74C3C">사용</font></b></c:if>
				<c:if test="${n.bo_use == 0 }">미사용</c:if>
			</div>
			<div class="community-title-5">
				<input type="submit" class="writecomplete" value="게시판 수정" 
				style="width: 80px; height: 22px; margin-top: -10px;">
			</div>
		</div>
	</form>
	</c:forEach>
	</c:if>
	
	<br/>
		<div class="create">
		<form action="${ctx}/Config/CommunityMake.config?community=CIRCLES&mode=CREATE" method="post">
			<input type="submit" class="writecomplete" value="동아리 게시판 만들기" style="clear: both; width: 200px;">
			<input type="hidden" name="community" value="CIRCLES">
		</form>
	</div>
	<br/>
	<br/>
	
		
	<div id="config-status-title" style="border-bottom: none;">
		프로젝트 게시판
	</div>
	<div id="config-community-title">
		<div class="community-title-1">
			코드
		</div>
		<div class="community-title-2">
			게시판 이름
		</div>
		<div class="community-title-3">
			게시판 설명
		</div>
		<div class="community-title-4">
			사용 여부
		</div>
		<div class="community-title-5">
			게시판 수정
		</div>
	</div>
	
	<c:if test="${fn:length(projectList) == 0 }">
	<div style="text-align: center; margin-top: 10px;">생성된 동아리 게시판이 없습니다. </div>
	</c:if>
	
	<c:if test="${fn:length(projectList) > 0 }">
	<c:forEach var="n" items="${projectList}">
	<form action="${ctx}/Config/CommunityMake.config?community=PROJECT&mode=MODIFY&bo_no=${n.bo_no}" method="post">
		<div id="config-community-content">
			<div class="community-title-1" style="font-size: 9px; font-family: verdana, tahoma; font-weight: bold;">
				${n.bo_no}
			</div>
			<div class="community-title-2">
				${n.bo_name}
			</div>
			<div class="community-title-3">
				${n.bo_describe}
			</div>
			<div class="community-title-4">
				<c:if test="${n.bo_use == 1 }"><b><font color="#E74C3C">사용</font></b></c:if>
				<c:if test="${n.bo_use == 0 }">미사용</c:if>
			</div>
			<div class="community-title-5">
				<input type="submit" class="writecomplete" value="게시판 수정" 
				style="width: 80px; height: 22px; margin-top: -10px;">
			</div>
		</div>
	</form>
	</c:forEach>
	</c:if>
	
	<br/>
		<div class="create">
		<form action="${ctx}/Config/CommunityMake.config?community=PROJECT&mode=CREATE" method="post">
			<input type="submit" class="writecomplete" value="프로젝트 게시판 만들기" style="clear: both; width: 200px;">
		</form>
	</div>
	
	
	
	
	
	
</div>