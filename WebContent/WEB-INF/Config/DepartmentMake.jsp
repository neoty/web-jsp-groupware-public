<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="MODE" value="${param.mode}" />
<c:if test="${MODE == 'MODIFY'}">
<c:set var="dep_nourl" value="?dep_no=${param.dep_no}"/>
<c:set var="title" value="수정"/>
</c:if>
<c:if test="${MODE == 'CREATE'}">
<c:set var="title" value="생성"/>
</c:if>
<link rel="stylesheet" type="text/css" href="${ctx}/css/Community.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/config.css" />
<div id="co-main">
	<div id="co-write-title">
		부서 ${title} 하기
	</div>
	<form action="${ctx}/Config/DepartmentEvent.do${dep_nourl}" method="post">
		<div id="co-write-form">
			<div id="co-write-form-subject">부서 이름</div>
				<div id="co-write-form-input">
					<input type="text" name="name" class="input-form" value="${content.dep_name}"/>
				</div>
			
			<c:if test="${MODE != 'CREATE'}">
			<div id="co-write-form-subject">현재 부서 관리자</div>
			<div id="co-write-form-input" style="text-align: left !important;">
					<c:if test="${empty content.dep_mb_name}">
					&nbsp;&nbsp;현재 지정된 관리자가 없습니다. 
					</c:if>
					<c:if test="${!empty content.dep_mb_name}">
					&nbsp;&nbsp;${content.dep_posi_name} ${content.dep_mb_name}
					</c:if>
			</div>
			<div id="co-write-form-subject">부서 생성일</div>
				<div id="co-write-form-input" style="text-align: left !important;">
					&nbsp;&nbsp;${content.dep_create_date}
			</div>
			</c:if>
			
			<div id="co-write-form-subject">부서 설명</div>
				<div id="co-write-form-input">
					<input type="text" name="describe" class="input-form" value="${content.dep_describe}"/>
			</div>

			<div id="co-write-form-subject">부서 관리자 변경</div>
				<div id="co-write-form-input" style="text-align: left;">
					<select name="admin" class="config-combo-form" style="float: left !important; margin-left: 6px; margin-top: -1px !important; width: 400px;">
					<c:forEach var="b" items="${memberList}">
					<option value="${b.mb_no}">
					부서 : 
					<c:if test="${empty b.dep_name}">
					등록된 부서가 없습니다 
					</c:if>
					<c:if test="${!empty b.dep_name}">
					${b.dep_name} 
					</c:if>
					
					- ${b.posi_name} ${b.mb_name}
					</option>
					</c:forEach>
				</select>
					
			</div>
			
			<div id="co-write-form-subject">부서 사용 여부</div>
				<div id="co-write-form-input">
					<select name="use" class="config-combo-form" style="float: left !important; margin-left: 6px; margin-top: -1px !important;">
					<c:if test="${MODE == 'MODIFY'}">
						<c:set var="use" value="${content.dep_use}" />
							<c:if test="${use == 1}">
								<option value="1" selected>부서 사용</option>
								<option value="0">부서 미사용</option>
							</c:if>
						<c:if test="${use == 0}">
								<option value="0" selected>부서 미사용</option>
								<option value="1">부서 사용</option>
						</c:if>
					</c:if>
					
					<c:if test="${MODE == 'CREATE'}">
							<option value="1" selected>부서 사용</option>
							<option value="0">부서 미사용</option>
					</c:if>
				</select>
			</div>
			
		
		</div>
			<input class="backbtn" onclick="javascript:history.go(-1)" type="button"
			value="뒤로 가기" style="float: left; margin-top: 5px;" />
			<c:if test="${MODE == 'MODIFY'}">
			<input class="writecomplete" type="submit" value="부서 수정" style="float: right;" onclick="return confirm('부서를 수정하시겠습니까?');"/>
			</c:if>
			<c:if test="${MODE == 'CREATE'}">
			<input class="writecomplete" type="submit" value="부서 만들기" style="float: right;"/>
			</c:if>
			<input type="hidden" name="mode" value="${MODE}">
			<input type="hidden" name="timestamp" value="${content.dep_create_date}" >
	</form>	
	
			<c:if test="${MODE == 'MODIFY'}">
			<form action="${ctx}/Config/DepartmentEvent.do" method="post">
			<input type="hidden" name="mode" value="DELETE" >
			<input type="hidden" name="dep_no" value="${param.dep_no}">
			<input class="writecomplete" type="submit" value="게시판 삭제" style="float: right;" onclick="return confirm('부서를 삭제하시겠습니까?');"/>
			</form>
			</c:if>
</div>
