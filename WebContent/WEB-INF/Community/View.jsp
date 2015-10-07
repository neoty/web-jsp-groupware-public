<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/Community.css" />

<div id="co-main">
	<div id="co-write-title">
		이부분에 현재 글쓰기 위치 꽃기 - 글보기
	</div>
		<div id="co-write-form">
			<div id="co-write-form-subject">제목</div>
				<div id="co-write-form-input" style="text-align: left !important;">
					&nbsp;&nbsp;
					<c:if test="${n.bc_notice == '1'}"><img src="${ctx}/images/icons/icon_notice.gif"></c:if>
					<c:if test="${n.bc_secret == '1'}"><b><font color="red">[비밀글]</font></b></c:if>
					${n.bc_subject}
				</div>
			<div id="co-write-form-subject">글쓴이</div>
				<div id="co-write-form-input" style="text-align: left !important;">
					<a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/MemberImg/${n.mb_img}">
					&nbsp;&nbsp;${n.dep_name} - ${n.posi_name} ${n.mb_name}</a>
				</div>
			<div id="co-write-form-subject">글쓴 시각</div>
				<div id="co-write-form-input" style="text-align: left !important;">
					&nbsp;&nbsp;${n.bc_date}
				</div>
			
			<div id="co-write-form-subject">첨부파일 #1</div>
				<div id="co-write-form-input" style="text-align: left !important;">
					&nbsp;&nbsp;
					<c:choose>
						<c:when test="${n.file_relname1 == 'null' || empty n.file_relname1}">
							첨부파일 #1 없음
						</c:when>
						<c:otherwise>
							<a href="${ctx}/Download.jsp?culumn=first&file=${n.file_encname1}">	
								#1 ${n.file_relname1}
							</a>
						</c:otherwise>
					</c:choose>
				</div>
			
			<div id="co-write-form-subject">첨부파일 #2</div>
				<div id="co-write-form-input" style="text-align: left !important;">
					&nbsp;&nbsp;
					<c:choose>
						<c:when test="${n.file_relname2 == 'null' || empty n.file_relname2}">
							첨부파일 #2 없음
						</c:when>
						<c:otherwise>
							<a href="${ctx}/Download.jsp?culumn=second&file=${n.file_encname2}">	
								#2 ${n.file_relname2}
							</a>
						</c:otherwise>
					</c:choose>
				</div>
				
			<div id="co-write-form-input" style="height: 100%; text-align: left; min-height: 500px;">
				${n.bc_content}
			</div>
		</div>
			<input class="backbtn" onclick="javascript:location.href='${ctx}/Community/index.community?type=${param.type}&code=${param.code}'" type="button"
			value="뒤로 가기" style="margin-right: 4px; margin-top: 5px; float: left;"/>
			<c:if test="${sessionScope.MB_NO == n.mb_no}">
			<form action="${ctx}/Community/Delete.do" method="post">
				<input class="writecomplete" type="submit" value="글 삭제" style="float: right !important;" onclick="return confirm('삭제 하시겠습니까?');"/>
				<input type="hidden" name="type" value="${param.type}"> <!-- 현재 글번호 -->
				<input type="hidden" name="code" value="${param.code}"> <!-- 게시판번호 -->
				<input type="hidden" name="bc_no" value="${param.bc_no}"> <!-- 현재 글번호 -->
			</form>
			
			<form action="${ctx}/Community/Modify.community" method="post">
				<input class="writecomplete" type="submit" value="글 수정" style="float: right !important;"/>
				<input type="hidden" name="type" value="${param.type}"> <!-- 현재 게시판 기억하기 -->
				<input type="hidden" name="code" value="${param.code}"> <!-- 현재 글번호 -->
				<input type="hidden" name="bc_no" value="${param.bc_no}"> <!-- 현재 글번호 -->
			</form>
			</c:if>
</div>
<div id="reply">
	<form action="${ctx}/Community/Reply.do?mode=CREATE" method="post">
			<input type="hidden" name="type" value="${param.type}"> <!-- 현재 글번호 -->
			<input type="hidden" name="code" value="${param.code}"> <!-- 현재 글번호 -->
			<input type="hidden" name="bc_no" value="${param.bc_no}"> <!-- 현재 글번호 -->
		<div id="reply-content">
			<textarea cols="65" rows="5" name="reply_content" class="reply-comment"></textarea>
		</div>
		<div id="writeok">
			<input type="submit" value="리플 쓰기" class="reply-submit">
		</div>
	</form>
	<c:forEach var="n" items="${commentList}">
		<div id="reply-writer">
			<a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/MemberImg/${n.mb_img}">
			<div id="co-middle-writer">${n.mb_posi_name} ${n.mb_name}</div>
			</a>
		</div>
		<div id="reply-view">
			${n.re_content}
		</div>
		<div id="reply-date">
			${n.re_create_date}&nbsp;
			
			<c:if test="${sessionScope.MB_NO == n.mb_no}">
			<a href="${ctx}/Community/Reply.do?mode=DELETE&re_no=${n.re_no}&type=${param.type}&code=${param.code}&bc_no=${param.bc_no}"><img src="${ctx}/images/icons/rel_delete.gif"></a>
			</c:if>
		
		</div>
	</c:forEach>
</div>