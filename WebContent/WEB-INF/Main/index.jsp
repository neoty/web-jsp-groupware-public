<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/Main.css" />
<!-- 2 / n 으로 순환 구조 돌것 -->
<br/>
	<b>공용 게시판에 추가 되면 자동으로 추가 됩니다.</b>
<br/>
<div id="main-frame">
	<c:forEach  var="name" items="${boardNamelist}">
		<div id="newest-list">
			<ul class="newest-ul">
				<li class="newest-li">
					<a href="${ctx}/Community/index.community?type=PUBLIC&code=${name.bo_no}">
					<img src="${ctx}/images/pathicon.gif"> 
					<font size="3pt">${name.bo_name}</font></a>
				</li>
				
				<c:forEach var="n" items="${boardContentList}">
					<c:if test="${n.bc_code == name.bo_no}">
							<a href="${ctx}/Community/View.community?type=PUBLIC&code=${name.bo_no}&bc_no=${n.bc_code}">
								<li class="newest-li"> 
								&nbsp&nbsp - ${n.bc_subject} 
								<span class="main-list-date">(${n.bc_date})</span>
								</li>
							</a>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</c:forEach>
</div>



