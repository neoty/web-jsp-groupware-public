<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="doc_list" value="${DOC_LIST}" />
<c:set var="list_status" value="${LIST_STATUS}" />

<link rel="stylesheet" type="text/css" href="${ctx}/css/Work.css" />
<div id="work-main">
	<form action="" method="post" name="sendForm" id="sendForm">
		<!-- 제일 상단 좌우로 버튼 들어갈곳 -->
		<div id="work-status-header">
			<div id="work-status-select">
				<select name="list_status" id="list_status">
					<option value="0">----------</option>
					<option value="1">결재 진행</option>
					<option value="2">결재 보류</option>
					<option value="3">결재 반려</option>
					<option value="4">결재 완료</option>
				</select>
			</div>
			<div id="work-doc-write">
				<input type="button" value="결재 작성" class="writecomplete" onclick="newOpen()">
			</div>
		</div>
		 
		<!-- 결재 리스트 -->
		<div id="work-status-title">
		결재 리스트
		</div>
	
		<div id="work-sub-title">
			<ul class="work-list-subject">
				<li style="width: 80px;">진행 상태</li>
				<li class="work-subject-date-list" style="width: 400px; text-align:center;">
					결재 제목
				</li>
				<li style="width: 50px;">문서</li>
				<li style="width: 50px;">별첨</li>
				<li style="width: 150px;">결재 요청일</li>
			</ul>
		</div>
		
		<c:if test="${empty doc_list}">
			<div id="work-list-blank" style="width: 100%; border-bottom: 1px solid #bdbdbd; height: 20px; line-height: 170%; padding: 5px 0px; text-align: center;">
				결재 문서가 없습니다.
			</div>
		</c:if>
		<c:if test="${!empty doc_list}">
			<c:forEach items="${doc_list}" var="list">
				<ul class="work-list-workflow">
					<li class="work-flow-progress">
						<c:choose>
							<c:when test="${list.wf_status == 0}">
								작성 대기
							</c:when>
							<c:when test="${list.wf_status == 1}">
								결재 진행
							</c:when>
							<c:when test="${list.wf_status == 2}">
								결재 보류
							</c:when>
							<c:when test="${list.wf_status == 3}">
								결재 반려
							</c:when>
							<c:otherwise>
								결재 완료
							</c:otherwise>
						</c:choose>
					</li>
					<li class="work-subject-date-list" >
						<a href="javascript:void(0)" onclick="viewOpen('${list.wf_no}')">${list.wf_subject}</a>
					</li>
					<li class="work-flow-att" style="padding: 2px 0px 8px 0px;">
						<a href="${ctx}/Download.jsp?culumn=first&file=${list.encDocName}">
							<c:choose>
								<c:when test="${list.file_icon.equals('pdf')}">
									<img src="${ctx}/images/icons/pdfico.png" width="25" height="25">
								</c:when>
								<c:when test="${list.file_icon.equals('hwp')}">
									<img src="${ctx}/images/icons/hwpico.png" width="25" height="25">
								</c:when>
								<c:when test="${list.file_icon.equals('odt')}">
									<img src="${ctx}/images/icons/odtico.png" width="25" height="25">
								</c:when>
								<c:when test="${list.file_icon.equals('doc')}">
									<img src="${ctx}/images/icons/docico.png" width="25" height="25">
								</c:when>
								<c:otherwise>
									<img src="${ctx}/images/icons/pdf.png" width="25" height="25">
								</c:otherwise>
							</c:choose>
						</a>
					</li>
					<li class="work-flow-att">
						<c:choose>
							<c:when test="${list.encFileName == null || list.encFileName.equals('') || list.encFileName.equals('null')}">
								별첨
							</c:when>
							<c:otherwise>
								<a href="${ctx}/Download.jsp?culumn=second&file=${list.encFileName}">별첨</a>
							</c:otherwise>
						</c:choose>
					</li>
					<li class="work-flow-date">
						${list.wf_create_date}
					</li>
				</ul>
			</c:forEach>
		</c:if>
		<input type="hidden" id="view_doc_no" name="view_doc_no">
	</form>
	<div class="paginate_simple" style="padding-top:20px; float: left; width: 730px;">
		<c:if test="${page >= 11}">
			<a href="${ctx}/Work/WorkFlowIndex.work?page=${page-10}" class="direction"> ‹ <span>Prev</span></a>
		</c:if>
		
		<c:forEach var="i" step="1" begin="0" end="9">
			<c:if test="${start+i <= end}">
				<c:if test="${start+i == page}">
					<strong>${start+i}</strong>
				</c:if>
				<c:if test="${start+i != page}">
					<a href="${ctx}/Work/WorkFlowIndex.work?page=${start+i}">${start+i}</a>
				</c:if>
			</c:if>
			
		</c:forEach>
		<c:if test="${start+10 < end}">
			<a href="${ctx}/Work/WorkFlowIndex.work?page=${start+10}" class="direction"><span>Next</span> › </a>
		</c:if>
	</div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		$('#list_status').val('${list_status}');
		
		var ctx = "${ctx}";
		$('#list_status').change(function() {
			$('#sendForm').attr('target', '');
			$('#sendForm').attr('action', ctx + "/Work/WorkFlowIndex.work");
			$('#sendForm').submit();
		});
	});

	function newOpen() {
		var ctx = "${ctx}";
		var winName="wrokPopup";
		
		var winWidth = document.body.clientWidth;	// 현재창의 너비 
		var winHeight = document.body.clientHeight;	// 현재창의 높이 
		
		var sw = window.screenX || window.screenLeft;
		var sh = window.screenY || window.screenTop;

		var width = 500;     // 팝업창 너비
		var height = 430;    // 팝업창 높이
		
		var px = sw + (winWidth - width) / 2;
		var py = sh + (winHeight - height) / 2;
		
		var popupUrl = ctx + "/Work/WorkWrite.work";
		var popupOption = "resizeable=no, toolbar=no, menubar=no, location=no, scrollbars=no, status=no, top=" + py + ", left=" + px + ", width=" + width + ", height=" + height;
		var popName = window.open(popupUrl, winName, popupOption);
		window.popName.open();
	}
	
	function viewOpen(no) {
		var ctx = "${ctx}";
		var form = document.sendForm;
		$('#view_doc_no').val(no);
		
		var winWidth = document.body.clientWidth;	// 현재창의 너비 
		var winHeight = document.body.clientHeight;	// 현재창의 높이 
		
		var sw = window.screenX || window.screenLeft;
		var sh = window.screenY || window.screenTop;

		var width = 500;     // 팝업창 너비
		var height = 600;    // 팝업창 높이
		
		var px = sw + (winWidth - width) / 2;
		var py = sh + (winHeight - height) / 2;
		
		//var popupUrl = ctx + "/Work/WorkView.work";
		var popupOption = "resizeable=no, toolbar=no, menubar=no, location=no, scrollbars=yes, status=no, top=" + py + ", left=" + px + ", width=" + width + ", height=" + height;
		var popName = window.open("", "target", popupOption);
		
		form.target = "target";
		form.action = ctx + "/Work/WorkView.work";
		form.submit();		
	}
</script>
