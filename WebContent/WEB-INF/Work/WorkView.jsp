<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="doc_info" value="${DOC_INFO}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="shortcut icon" href="${ctx}/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${ctx}/css/loading.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/mainform.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/formstyle.css" />
<script src="${ctx}/js/check.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="${ctx}/js/main.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/Work.css" />
<title>결재 문서</title>
</head>
<body class="work-body">
	<div id="work-write-main">
		<form action="${ctx}/Work/WorkConfirm.do" method="post" name="form" id="form">
			<div id="work-write-info">
				<div id="work-status-title">결재 문서 정보</div>
				<div class="work-write-form">문서 번호</div>
				<div class="work-write-content" id="doc-number">${doc_info.wf_doc_no}</div>
				<div class="work-write-form">작성자</div>
				<div class="work-write-content">${doc_info.wf_post_posi_name} ${doc_info.wf_post_mb_name}</div>
				<div class="work-write-form">부  서</div>
				<div class="work-write-content">${doc_info.wf_post_dep_name}</div>
				<div class="work-write-form" style="border-bottom: 2px solid #BDBDBD;">작성일</div>
				<div class="work-write-content" style="font-weight: bold; font-family: verdana !important; font-size: 8px !important; border-bottom: 2px solid #BDBDBD; line-height: 25px !important;">${doc_info.wf_create_date}</div>
				<div style="float:left; width: inherit; border: 0px; text-align: center; color: red; padding: 10px 0px;">
				</div>
				<div id="work-status-title">결재 문서</div>
				<div class="work-status-file" id="approve-doc" style="border-bottom: 2px solid #bdbdbd;">
					<a href="${ctx}/Download.jsp?culumn=first&file=${doc_info.encDocName}">${doc_info.oriDocName}</a>
				</div>
				<div id="work-status-title" style="margin-top: 30px;">첨부 파일</div>
				<div class="work-status-file" id="attach-file">
					<c:choose>
						<c:when test="${doc_info.encFileName == null || doc_info.encFileName.equals('') || doc_info.encFileName.equals('null')}">
							첨부 파일 없음
						</c:when>
						<c:otherwise>
							<a href="${ctx}/Download.jsp?culumn=second&file=${doc_info.encFileName}">${doc_info.encFileName}</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div id="work-approve-view">
				<div id="work-status-title">결재자</div>
				<div class="work-approve-field">
					<div class="work-approve-name">
						<c:choose>
							<c:when test="${doc_info.wf_mb_first != 0}">
								${doc_info.wf_first_dep_name}<br/>
								${doc_info.wf_first_posi_name} ${doc_info.wf_first_mb_name}
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="work-approve-info">
						<c:choose>
							<c:when test="${doc_info.wf_mb_first == sessionScope.MB_NO && (doc_info.wf_first_status == 1 || doc_info.wf_first_status == 2)}">
								<select class="work-approve-info-select" id="select-confirm" name="select-confirm">
									<option value="none">-----------</option>
									<option value="4">결재 승인</option>
									<option value="2">결재 보류</option>
									<option value="3">결재 반려</option>
								</select>
								<input type="hidden" value="1" name="confirm-no">
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${doc_info.wf_first_status == 0}">
									</c:when>
									<c:when test="${doc_info.wf_first_status == 1}">
										<img src="${ctx}/images/work_standby.png" width="80px" height="80px" class="work_img_style">
									</c:when>
									<c:when test="${doc_info.wf_first_status == 2}">
										<img src="${ctx}/images/work_defer.png" width="80px" height="80px" class="work_img_style">
									</c:when>
									<c:when test="${doc_info.wf_first_status == 3}">
										<img src="${ctx}/images/work_return.png" width="80px" height="80px" class="work_img_style">
									</c:when>
									<c:when test="${doc_info.wf_first_status == 4}">
										<img src="${ctx}/images/work_approve.png" width="80px" height="80px" class="work_img_style">
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="work-approve-date">
						<c:choose>
							<c:when test="${doc_info.wf_first_confirm_date == null || doc_info.wf_first_confirm_date == ''}">
							</c:when>
							<c:otherwise>
								${doc_info.wf_first_confirm_date}
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="work-approve-field">
					<div class="work-approve-name">
						<c:choose>
							<c:when test="${doc_info.wf_mb_second != 0}">
								${doc_info.wf_second_dep_name}<br/>
								${doc_info.wf_second_posi_name} ${doc_info.wf_second_mb_name}
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="work-approve-info">
						<c:choose>
							<c:when test="${doc_info.wf_mb_second == sessionScope.MB_NO && (doc_info.wf_second_status == 1 || doc_info.wf_second_status == 2)}">
								<select class="work-approve-info-select" id="select-confirm" name="select-confirm">
									<option value="none">-----------</option>
									<option value="4">결재 승인</option>
									<option value="2">결재 보류</option>
									<option value="3">결재 반려</option>
								</select>
								<input type="hidden" value="2" name="confirm-no">
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${doc_info.wf_second_status == 0}">
									</c:when>
									<c:when test="${doc_info.wf_second_status == 1}">
										<img src="${ctx}/images/work_standby.png" width="80px" height="80px" class="work_img_style">
									</c:when>
									<c:when test="${doc_info.wf_second_status == 2}">
										<img src="${ctx}/images/work_defer.png" width="80px" height="80px" class="work_img_style">
									</c:when>
									<c:when test="${doc_info.wf_second_status == 3}">
										<img src="${ctx}/images/work_return.png" width="80px" height="80px" class="work_img_style">
									</c:when>
									<c:when test="${doc_info.wf_second_status == 4}">
										<img src="${ctx}/images/work_approve.png" width="80px" height="80px" class="work_img_style">
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="work-approve-date">
						<c:choose>
							<c:when test="${doc_info.wf_second_confirm_date == null || doc_info.wf_second_confirm_date == ''}">
							</c:when>
							<c:otherwise>
								${doc_info.wf_second_confirm_date}
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="work-approve-field" style="width: 154px;border-right: 0px !important;">
					<div class="work-approve-name">
						<c:choose>
							<c:when test="${doc_info.wf_mb_third != 0}">
								${doc_info.wf_third_dep_name}<br/>
								${doc_info.wf_third_posi_name} ${doc_info.wf_third_mb_name}
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="work-approve-info">
						<c:choose>
							<c:when test="${doc_info.wf_mb_third == sessionScope.MB_NO && (doc_info.wf_third_status == 1 || doc_info.wf_third_status == 2)}">
								<select class="work-approve-info-select" id="select-confirm" name="select-confirm">
									<option value="none">-----------</option>
									<option value="4">결재 승인</option>
									<option value="2">결재 보류</option>
									<option value="3">결재 반려</option>
								</select>
								<input type="hidden" value="3" name="confirm-no">
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${doc_info.wf_third_status == 0}">
									</c:when>
									<c:when test="${doc_info.wf_third_status == 1}">
										<img src="${ctx}/images/work_standby.png" width="80px" height="80px" class="work_img_style">
									</c:when>
									<c:when test="${doc_info.wf_third_status == 2}">
										<img src="${ctx}/images/work_defer.png" width="80px" height="80px" class="work_img_style">
									</c:when>
									<c:when test="${doc_info.wf_third_status == 3}">
										<img src="${ctx}/images/work_return.png" width="80px" height="80px" class="work_img_style">
									</c:when>
									<c:when test="${doc_info.wf_third_status == 4}">
										<img src="${ctx}/images/work_approve.png" width="80px" height="80px" class="work_img_style">
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="work-approve-date">
						<c:choose>
							<c:when test="${doc_info.wf_third_confirm_date == null || doc_info.wf_third_confirm_date == ''}">
							</c:when>
							<c:otherwise>
								${doc_info.wf_third_confirm_date}
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<c:if test="${(doc_info.wf_mb_first == sessionScope.MB_NO && (doc_info.wf_first_status == 1 || doc_info.wf_first_status == 2)) || (doc_info.wf_mb_second == sessionScope.MB_NO && (doc_info.wf_second_status == 1 || doc_info.wf_second_status == 2)) || (doc_info.wf_mb_third == sessionScope.MB_NO && (doc_info.wf_third_status == 1 || doc_info.wf_third_status == 2))}">
					<div id="work-status-title" style="border-top: 0px; border-bottom: 1px solid #bdbdbd;">
						사 유 작 성
					</div>
					<div style="float: left; width: inherit; border-bottom: 2px solid #bdbdbd;">
						<input type="text" id="confirm-note" name="confirm-note" class="input-form" style="float:left; width: 340px;" placeholder="보류/반려 사유 작성">
						<input type="text" value="${doc_info.wf_no}" name="wf-no" style="display: none;" />
						<input type="button" class="writecomplete" id="confirmBtn" value="결재" style="float:right; margin: 1px 0px;">
					</div>
				</c:if>
				<c:if test="${(doc_info.wf_first_status == 2 || doc_info.wf_first_status == 3) || (doc_info.wf_second_status == 2 || doc_info.wf_second_status == 3) || (doc_info.wf_third_status == 2 || doc_info.wf_third_status == 3)}">
					<div id="work-status-title" style="border-top: 0px; border-bottom: 1px solid #bdbdbd;">
						사 유
					</div>
					<div id="work-status-confirm-note">
						<c:choose>
							<c:when test="${doc_info.wf_first_status == 2 || doc_info.wf_first_status == 3}">
								${doc_info.wf_first_note}
							</c:when>
							<c:when test="${doc_info.wf_second_status == 2 || doc_info.wf_second_status == 3}">
								${doc_info.wf_second_note}
							</c:when>
							<c:when test="${doc_info.wf_third_status == 2 || doc_info.wf_third_status == 3}">
								${doc_info.wf_third_note}
							</c:when>
						</c:choose>
					</div>
				</c:if>
			</div>
		</form>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		
		$('#confirmBtn').click(function(){
			if ($('#select-confirm').val() == 'none') {
				alert('결재 상태를 선택해주세요.');
			} else {
				if ($('#select-confirm').val() == 4) {
					$('#form').submit();
				} else {
					if ($('#confirm-note').val() == '') {
						alert("사유를 적어주세요.");
					} else {
						$('#form').submit();
					}
				}
			}
		});
		
	});
</script>
</html>