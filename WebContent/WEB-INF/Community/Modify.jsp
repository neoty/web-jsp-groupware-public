<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/Community.css" />

<div id="co-main">
	<div id="co-write-title">
		이부분에 현재 글쓰기 위치 꽃기 - 글쓰기
	</div>
	<form action="${ctx}/Community/Modify.do" method="post" enctype="multipart/form-data">
		<div id="co-write-form">
			<div id="co-write-form-subject">제목</div>
				<div id="co-write-form-input">
					<input type="text" class="input-form" value="${n.bc_subject}" name="subject"/>
				</div>
			
			<div id="co-write-form-subject">첨부파일 #1</div>
			<div id="co-write-form-input">
				<c:if test="${empty n.file_relname1}">
					<input type="file" class="file-form" name="file1"/>
				</c:if>
				<c:if test="${!empty n.file_relname1}">
				#2 ${n.file_relname1}(수정불가)
				</c:if>
			</div>
			<div id="co-write-form-subject">첨부파일 #2</div>
			<div id="co-write-form-input">
				<c:if test="${empty n.file_relname2}">
					<input type="file" class="file-form" name="file2"/>
				</c:if>
				<c:if test="${!empty n.file_relname2}">
				#2 ${n.file_relname2}(수정불가)
				</c:if>
			</div>
			
			<div id="co-write-form-subject">세부 설정</div>
				<div id="co-write-form-input">
						<div style="float: left; padding-left: 5px; padding-right: 5px;">비밀글: </div>
						<div style="float: left; height: 20px;">
							<c:if test="${n.bc_notice == 1}">
								<input type="checkbox" name="secret" value="true" checked="checked">
							</c:if>
							<c:if test="${n.bc_notice == 0}">
								<input type="checkbox" name="secret" value="false">
							</c:if>
						</div>

						<div style="float: left; padding-left: 30px; padding-right: 5px;">공지사항:  </div>
						<div style="float: left; height: 20px;">
						<c:if test="${n.bc_secret == 1}">
							<input type="checkbox" name="notice" value="true" checked="checked">
						</c:if>
						<c:if test="${n.bc_secret == 0}">
							<input type="checkbox" name="notice" value="false">
						</c:if>
						</div>
				</div>
			
			<div id="co-write-form-input" style="height: 100%;">
				<textarea class="co-textarea" name="content">${n.bc_content}</textarea>
			</div>
		</div>
			<input class="backbtn" onclick="javascript:history.go(-1)" type="button"
			value="뒤로 가기" style="float: left; margin-top: 5px;" />
			<input class="writecomplete" type="submit" value="수정 완료" style="float: right;"/>
			<input type="hidden" name="type" value="${param.type}">
			<input type="hidden" name="code" value="${param.code}">
			<input type="hidden" name="bc_no" value="${param.bc_no}">
	</form>		
</div>