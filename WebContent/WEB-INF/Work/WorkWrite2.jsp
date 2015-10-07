<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="doc_no" value="${DOC_NO}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="shortcut icon" href="${ctx}/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${ctx}/css/loading.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/mainform.css" />
<script src="${ctx}/js/check.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="${ctx}/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/Work.css" />
<title>결재 문서 작성</title>
</head>
<body class="work-body">
	<div id="work-write-main">
		<form action="${ctx}/Work/WorkWrite.do" method="post" name="form" id="form">
			<div id="work-write-info">
				<div id="work-status-title">결재 문서 등록</div>
				<div class="work-write-form">문서 번호</div>
				<div class="work-write-content" id="doc-number">${doc_no}</div>
				<div style="float:left; width: inherit; border: 0px; text-align: center; color: red; padding: 10px 0px;">
					문서 번호를 결재 문서에 기입한 후 파일을 등록해주세요.
				</div>
				<div id="work-status-title">문서 등록</div>
				<input type="file" name="approve-doc" id="approve-doc" style="width: 100%; height: 30px;">
				<div id="work-status-title" style="margin-top: 30px;">첨부 파일</div>
				<input type="file" name="attach-file" id="attach-file" style="width: 100%; height: 30px;">
			</div>
			
			<div id="work-write-button">
				<input type="button" value="등록 취소" class="backbtn" style="float: left;" onclick="pageCont('${ctx}')">
				<input type="button" value="완료" id="completeBtn" class="writecomplete" style="float: right;">
				<input type="hidden" value="${WORK_DOC_NO}" name="work_doc_no">
				<input type="hidden" value="${doc_no}" name="doc_no">
			</div>
		</form>
	</div>
</body>

<script type="text/javascript">
	//등록된 문서번호 삭제 페이지로 이동
	function pageCont(ctx) {
		if(confirm('등록을 취소하시겠습니까?')!=0) {
			var doc = document.getElementById('doc-number');
			var docNo = doc.innerHTML;
			
			var form = document.form;
			form.action = ctx + '/Work/WorkDelete.do';
			
			var input_id = document.createElement('input');
			input_id.setAttribute("type", "hidden");
			input_id.setAttribute("name", "docNo");
			input_id.setAttribute("value", docNo);
			form.appendChild(input_id);
			
			form.submit();
		} else {
			
		}
	}
	
	$(document).ready(function() {
		$('#completeBtn').click(function() {
			var file = $('#approve-doc').val();
			if (file.length < 1) {
				alert("결재 문서를 등록해 주세요.");
			} else {
				$('#form').attr('enctype', 'multipart/form-data');
				$('#form').submit();
			}
		});
	});
</script>
</html>