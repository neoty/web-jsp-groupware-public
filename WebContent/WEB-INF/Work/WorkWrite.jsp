<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="my_info" value="${MY_INFO}" />
<c:set var="dep_list" value="${DEP_LIST}" />
<c:set var="mb_list" value="${MB_LIST}" />
<c:set var="date" value="${DATE}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="shortcut icon" href="${ctx}/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${ctx}/css/loading.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/formstyle.css" />
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
		<form action="${ctx}/Work/WorkWrite.work" method="post" id="nextForm">
			<div id="work-write-info">
				<div id="work-status-title">결재 정보 등록</div>
				<div class="work-write-form">작성자</div>
				<div class="work-write-content">${my_info.mb_name}</div>
				<div class="work-write-form">부  서</div>
				<div class="work-write-content">${my_info.dep_name}</div>
				<div class="work-write-form">작성일</div>
				<div class="work-write-content" style="font-weight: bold; font-family: verdana !important; font-size: 8px !important;">${date}</div>
				<div class="work-write-form" id="work-write-endline">제 목</div>
				<input type="text" id="work-write-endline" class="input-form" name="subject" value="">
				<input type="text" style="display: none;" />
			</div>
			<div id="work-write-approval">
				<div id="work-status-title">결재 담당자 선택</div>
				<div id="work-write-selbox">
					<div class="work-write-select">
						<select class="work-write-select-form" id="work-write-select-1" name="sel_approval1">
							<option value="none">선택 안함</option>
							<c:forEach items="${dep_list}" var="dep_info">
								<c:if test="${dep_info.dep_name != '관리자'}">
									<option value="none"></option>
									<option value="none" style="color: red;">>-- ${dep_info.dep_name} --<</option>
									<c:forEach items="${mb_list}" var="mb_info">
										<c:if test="${mb_info.mb_dep_no == dep_info.dep_no}">
											<c:if test="${mb_info.mb_no != sessionScope.MB_NO}">
												<option value="${dep_info.dep_name}:${mb_info.mb_no}">${mb_info.posi_name} ${mb_info.mb_name}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
						</select>
						<div class="work-write-select-name">
							<div id="work-write-select-name-1" style="padding-top: 30px;">
							</div>
						</div>
					</div>
					
					<div class="work-write-select">
						<select class="work-write-select-form" id="work-write-select-2" name="sel_approval2">
							<option value="none">선택 안함</option>
							<c:forEach items="${dep_list}" var="dep_info">
								<c:if test="${dep_info.dep_name != '관리자'}">
									<option value="none"></option>
									<option value="none" style="color: red;">>-- ${dep_info.dep_name} --<</option>
									<c:forEach items="${mb_list}" var="mb_info">
										<c:if test="${mb_info.mb_dep_no == dep_info.dep_no}">
											<c:if test="${mb_info.mb_no != sessionScope.MB_NO}">
												<option value="${dep_info.dep_name}:${mb_info.mb_no}">${mb_info.posi_name} ${mb_info.mb_name}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
						</select>
						<div class="work-write-select-name">
							<div id="work-write-select-name-2" style="padding-top: 30px;">
							</div>
						</div>
					</div>
					
					<div class="work-write-select" style="border: 0px; width: 153px !important;">
						<select class="work-write-select-form" id="work-write-select-3" name="sel_approval3">
							<option value="none">선택 안함</option>
							<c:forEach items="${dep_list}" var="dep_info">
								<c:if test="${dep_info.dep_name != '관리자'}">
									<option value="none"></option>
									<option value="none" style="color: red;">>-- ${dep_info.dep_name} --<</option>
									<c:forEach items="${mb_list}" var="mb_info">
										<c:if test="${mb_info.mb_dep_no == dep_info.dep_no}">
											<c:if test="${mb_info.mb_no != sessionScope.MB_NO}">
												<option value="${dep_info.dep_name}:${mb_info.mb_no}">${mb_info.posi_name} ${mb_info.mb_name}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
						</select>
						<div class="work-write-select-name">
							<div id="work-write-select-name-3" style="padding-top: 30px;">
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div id="work-write-button">
				<input type="button" value="취소" class="backbtn" style="float: left;" onclick="cancelPage()">
				<input type="hidden" value="1" name="control">
				<input type="button" value="다음" id="nextBtn" class="writecomplete" style="float: right;">
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	// 현재 페이지 닫기
	function cancelPage() {
		window.close();
	}
	
	$(document).ready(function() {
		$('#nextBtn').click(function() {
			var text = $('input#work-write-endline').val();
			if (text == null || text == '') {
				alert('제목을 입력해 주세요.');
			} else {
				if ($('#work-write-select-1').val() == 'none') {
					alert('결재 담당자를 선택해주세요.');
				} else {
					$('#nextForm').submit();
				}
			}
		});
		
		
		// 셀렉트 박스 값이 선택될 경우
		$('#work-write-select-1').change(function(){
			$('#work-write-select-name-1').text('');
			var value = $('#work-write-select-1 option:selected').val();
			if (value != 'none') {
				var valArray = value.split(':');
				
				var content = $('#work-write-select-1 option:selected').text();
				var contArray = content.split(' ');
				
				$('#work-write-select-name-1').append(valArray[0] + "<br/>" + contArray[0] + "<br/>" + contArray[1]);
			}
		});
		
		$('#work-write-select-2').change(function(){
			$('#work-write-select-name-2').text('');
			var value = $('#work-write-select-2 option:selected').val();
			if (value != 'none') {
				var valArray = value.split(':');
				
				var content = $('#work-write-select-2 option:selected').text();
				var contArray = content.split(' ');
				
				$('#work-write-select-name-2').append(valArray[0] + "<br/>" + contArray[0] + "<br/>" + contArray[1]);
			}
		});
		
		$('#work-write-select-3').change(function(){
			$('#work-write-select-name-3').text('');
			var value = $('#work-write-select-3 option:selected').val();
			if (value != 'none') {
				var valArray = value.split(':');
				
				var content = $('#work-write-select-3 option:selected').text();
				var contArray = content.split(' ');
				
				$('#work-write-select-name-3').append(valArray[0] + "<br/>" + contArray[0] + "<br/>" + contArray[1]);
			}
		});
	});
</script>
</html>

