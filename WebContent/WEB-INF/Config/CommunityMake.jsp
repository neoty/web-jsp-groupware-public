<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="MODE" value="${param.mode}" />
<c:if test="${MODE == 'MODIFY'}">
<c:set var="bo_nourl" value="?bo_no=${param.bo_no}"/>
</c:if>
<c:set var="FROM_PART" value="NONE" />
<c:set var="HIDDEN_PART" value="NONE" />
<c:if test="${param.community == 'PUBLIC'}">
	<c:set var="FROM_PART" value="공용" />
	<c:set var="HIDDEN_PART" value="PUBLIC" />
</c:if>
<c:if test="${param.community == 'CIRCLES'}">
	<c:set var="FROM_PART" value="동아리" />
	<c:set var="HIDDEN_PART" value="CIRCLES" />
</c:if>
<c:if test="${param.community == 'PROJECT'}">
	<c:set var="FROM_PART" value="프로젝트" />
	<c:set var="HIDDEN_PART" value="PROJECT" />
</c:if>
<c:if test="${param.community == 'DEPARTMENT'}">
	<c:set var="FROM_PART" value="부서" />
	<c:set var="HIDDEN_PART" value="DEPARTMENT" />
</c:if>

<link rel="stylesheet" type="text/css" href="${ctx}/css/Community.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/config.css" />
<div id="co-main">
	<div id="co-write-title">
		${FROM_PART} 게시판 생성 하기
	</div>
	<form action="${ctx}/Config/CommunityEvent.do${bo_nourl}" method="post">
		<div id="co-write-form">
			<div id="co-write-form-subject">게시판 이름</div>
				<div id="co-write-form-input">
					<input type="text" name="name" class="input-form" value="${content.bo_name}"/>
				</div>
			<c:if test="${MODE != 'CREATE'}">
			<div id="co-write-form-subject">현재 부서 관리자</div>
			<div id="co-write-form-input" style="text-align: left !important;">
					<c:if test="${HIDDEN_PART == 'PUBLIC'}">
					 &nbsp;&nbsp;그룹웨어 시스템 관리자 입니다.
					</c:if>
					
					<c:if test="${HIDDEN_PART != 'PUBLIC'}">
					<c:if test="${empty content.bo_mb_name}">
					&nbsp;&nbsp;현재 지정된 관리자가 없습니다. 
					</c:if>
					<c:if test="${!empty content.bo_mb_name}">
					&nbsp;&nbsp;${content.bo_mb_posi_name} <a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/MemberImg/${content.mb_img}">${content.bo_mb_name}</a>
					</c:if>
					</c:if>
			</div>
			
			<div id="co-write-form-subject">게시판 생성일</div>
				<div id="co-write-form-input" style="text-align: left !important;">
					&nbsp;&nbsp;${content.bo_create_date}
			</div>
			</c:if>
			<div id="co-write-form-subject">게시판 설명</div>
				<div id="co-write-form-input">
					<input type="text" name="describe" class="input-form" value="${content.bo_describe}"/>
			</div>
			
			<div id="co-write-form-subject">게시판 관리자 변경</div>
				<div id="co-write-form-input" style="text-align: left;">
					<c:if test="${HIDDEN_PART != 'PUBLIC'}">
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
					</c:if>
					
					<c:if test="${HIDDEN_PART == 'PUBLIC'}">
					 &nbsp;&nbsp;공용 게시판의 관리자는 그룹웨어 시스템 관리자로 한정됩니다. 
					</c:if>
			</div>
			
			<div id="co-write-form-subject">게시판 사용 여부</div>
				<div id="co-write-form-input">
					<select name="use" class="config-combo-form" style="float: left !important; margin-left: 6px; margin-top: -1px !important;">
					<c:if test="${MODE == 'MODIFY'}">
						<c:set var="use" value="${content.bo_use}" />
							<c:if test="${use == 1}">
								<option value="1" selected>게시판 사용</option>
								<option value="0">게시판 미사용</option>
							</c:if>
						<c:if test="${use == 0}">
								<option value="0" selected>게시판 미사용</option>
								<option value="1">게시판 사용</option>
						</c:if>
					</c:if>
					
					<c:if test="${MODE == 'CREATE'}">
							<option value="1" selected>게시판 사용</option>
							<option value="0">게시판 미사용</option>
					</c:if>
				</select>
			</div>
			
		
		</div>
			<input class="backbtn" onclick="javascript:history.go(-1)" type="button"
			value="뒤로 가기" style="float: left; margin-top: 5px;" />
			<c:if test="${MODE == 'MODIFY'}">
			<input class="writecomplete" type="submit" value="게시판 수정" style="float: right;" onclick="return confirm('게시판을 수정하시겠습니까?');"/>
			</c:if>
			<c:if test="${MODE == 'CREATE'}">
			<input class="writecomplete" type="submit" value="게시판 만들기" style="float: right;"/>
			</c:if>
			<input type="hidden" name="community" value="${HIDDEN_PART}">
			<input type="hidden" name="mode" value="${MODE}">
	</form>	
	
			<c:if test="${MODE == 'MODIFY'}">
			<form action="${ctx}/Config/CommunityEvent.do" method="post">
			<input type="hidden" name="community" value="${HIDDEN_PART}" >
			<input type="hidden" name="mode" value="DELETE" >
			<input type="hidden" name="bo_no" value="${param.bo_no}">
			<input class="writecomplete" type="submit" value="게시판 삭제" style="float: right;" onclick="return confirm('게시판을 삭제하시겠습니까?');"/>
			</form>
			</c:if>
</div>












