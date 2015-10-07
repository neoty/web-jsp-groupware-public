<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="MODE" value="${param.mode}" />
<c:if test="${MODE == 'MODIFY'}">
<c:set var="dep_nourl" value="?dep_no=${param.dep_no}"/>
<c:set var="title" value="수정"/>
</c:if>
<link rel="stylesheet" type="text/css" href="${ctx}/css/Community.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/config.css" />
<div id="co-main">
	<div id="co-write-title">
		차량 수정
	</div>
	<form action="${ctx}/Config/CarEvent.do" method="post">
		<div id="co-write-form">
			<div id="co-write-form-subject">차량 번호</div>
				<div id="co-write-form-input">
					<c:if test="${param.mode == 'CREATE'}">
						<input type="text" name="number" class="input-form"/>
					</c:if>
					<c:if test="${param.mode == 'MODIFY'}">
						<input type="text" name="number" class="input-form" value="${content.cl_number}"/>
					</c:if>
				</div>
			
			<div id="co-write-form-subject">차량 모델</div>
				<div id="co-write-form-input">
					<c:if test="${param.mode == 'CREATE'}">
						<input type="text" name="model" class="input-form"/>
					</c:if>
					<c:if test="${param.mode == 'MODIFY'}">
						<input type="text" name="model" class="input-form" value="${content.cl_model}"/>
					</c:if>
			</div>

			<div id="co-write-form-subject">차량 킬로수(기본 0)</div>
				<div id="co-write-form-input">
					<c:if test="${param.mode == 'CREATE'}">
						<input type="text" name="mileage" class="input-form"/>
					</c:if>
					<c:if test="${param.mode == 'MODIFY'}">
						<input type="text" name="mileage" class="input-form" value="${content.cl_mileage}"/>
					</c:if>
			</div>
			
			<div id="co-write-form-subject">차량 사용 여부</div>
				<div id="co-write-form-input">
					<select name="use" class="config-combo-form" style="float: left !important; margin-left: 6px; margin-top: -1px !important;">
					<c:if test="${MODE == 'MODIFY'}">
						<c:set var="use" value="${content.cl_use}" />
							<c:if test="${use == 1}">
								<option value="1" selected>차량 사용</option>
								<option value="0">차량 미사용</option>
							</c:if>
						<c:if test="${use == 0}">
								<option value="0" selected>차량 미사용</option>
								<option value="1">차량 사용</option>
						</c:if>
					</c:if>
					
					<c:if test="${param.mode == 'CREATE'}">
							<option value="1" selected>차량 사용</option>
							<option value="0">차량 미사용</option>
					</c:if>
				</select>
			</div>
			
		
		</div>
			<input class="backbtn" onclick="javascript:history.go(-1)" type="button"
			value="뒤로 가기" style="float: left; margin-top: 5px;" />
			<c:if test="${MODE == 'MODIFY'}">
			<input class="writecomplete" type="submit" value="차량 수정" style="float: right;" onclick="return confirm('차량을 수정하시겠습니까?');"/>
			<input type="hidden" name="cl_no" value="${content.cl_no}">
			</c:if>
			<c:if test="${MODE == 'CREATE'}">
			<input class="writecomplete" type="submit" value="차량 추가" style="float: right;"/>
			</c:if>
			<input type="hidden" name="mode" value="${MODE}">
	</form>	
	
			<c:if test="${MODE == 'MODIFY'}">
			<form action="${ctx}/Config/CarEvent.do" method="post">
			<input type="hidden" name="mode" value="DELETE" >
			<input type="hidden" name="cl_no" value="${content.cl_no}">
			<input class="writecomplete" type="submit" value="차량 삭제" style="float: right;" onclick="return confirm('차량을 삭제하시겠습니까?');"/>
			</form>
			</c:if>
</div>
