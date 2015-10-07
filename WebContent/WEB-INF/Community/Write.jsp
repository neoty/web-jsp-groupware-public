<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/Community.css" />

<div id="co-main">
	<div id="co-write-title">
		이부분에 현재 글쓰기 위치 꽃기 - 글쓰기
	</div>
	<form action="${ctx}/Community/Write.do" method="post" enctype="multipart/form-data">
		<div id="co-write-form">
			<div id="co-write-form-subject">제목</div>
				<div id="co-write-form-input">
					<input type="text" name="subject" class="input-form"/>
				</div>
			
			<div id="co-write-form-subject">첨부파일 #1</div>
				<div id="co-write-form-input">
					<input type="file" class="file-form" name="file1"/>
				</div>
			
			<div id="co-write-form-subject">첨부파일 #2</div>
				<div id="co-write-form-input">
					<input type="file" class="file-form" name="file2"/>
				</div>
			
			<div id="co-write-form-subject">세부 설정</div>
				<div id="co-write-form-input">
						<div style="float: left; padding-left: 5px; padding-right: 5px;">비밀글: </div>
						<div style="float: left; height: 20px;"><input type="checkbox" name="secret" value="1"></div>
						<div style="float: left; padding-left: 30px; padding-right: 5px;">공지사항:  </div>
						<div style="float: left; height: 20px;"><input type="checkbox" name="notice" value="1"></div>
				</div>
			
			<div id="co-write-form-input" style="height: 100%;">
				<textarea class="co-textarea" name="content"></textarea>
			</div>
		</div>
			<input class="backbtn" onclick="javascript:history.go(-1)" type="button"
			value="뒤로 가기" style="float: left; margin-top: 5px;" />
			<input class="writecomplete" type="submit" value="작성 완료" style="float: right;"/>
			<input type="hidden" name="type" value="${param.type}">
			<input type="hidden" name="code" value="${param.code}">
	</form>	
</div>