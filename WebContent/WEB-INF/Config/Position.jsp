<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/config.css" />
<div id="config-main">

	<div id="config-status-title" style="border-bottom: none;">
		직책 수정
	</div>
	<div id="config-community-title">
		<div class="community-title-1" style="width: 60px;">
			직책 레밸
		</div>
		<div class="community-title-2" style="width: 560px;">
			직책 이름
		</div>
		<div class="community-title-4" style="width: 105px;">
			사용 여부
		</div>
	</div>
			<c:forEach var="n" items="${positionList}">
			<div id="config-community-content">
			<div class="community-title-1" style="font-size: 9px; font-family: verdana, tahoma; font-weight: bold; width: 60px;">
				${n.posi_level}
			</div>
			<div class="community-title-2" style="width: 560px; font-weight: bold;">
				${n.posi_name}
			</div>
			<div class="community-title-4" style="width: 105px;">
				<c:if test="${n.posi_use == '1'}">
					<select name="use" id="use" onchange="position_use_change(this.value);" class="config-combo-form" style="width: 105px;">
						<option value="${n.posi_level}:1" selected>사용 중</option>
						<option value="${n.posi_level}:0">미 사용중</option>
					</select>
				</c:if>
				<c:if test="${n.posi_use == '0'}">
					<select name="use" id="use" onchange="position_use_change(this.value);" class="config-combo-form" style="width: 105px;">
						<option value="${n.posi_level}:0" selected>미 사용중</option>
						<option value="${n.posi_level}:1">사용중</option>
					</select>
				</c:if>
			</div>
			</div>
			</c:forEach>
	<br/>
</div>

<script>
function position_use_change(value) {
	var get = value;
	
	$.ajax({
		type: "POST",
		async:false,
		url: "${ctx}/Config/PositionEvent.do?mode=USE_EDIT&value="+value,
		success: function(msg) {
				alert(msg);
		}
	});
	
}
</script>