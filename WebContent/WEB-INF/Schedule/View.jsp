<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="view_schedule_list" value="${VIEW_SCHEDULE_LIST}" />
<c:set var="view_date" value="${VIEW_DATE}" />
<c:set var="view_schedule" value="${VIEW_TODAY_SCHEDULE}" />
<c:set var="today" value="${TODAY_DATE}" />
<c:set var="check_mb_no" value="${CHECK_MB_NO}" />

 <c:choose>
 	<c:when test="${empty view_schedule_list}">
 		<c:set var="sc_view" value="${view_schedule}"></c:set>
 	</c:when>
 	<c:when test="${!empty view_schedule_list}">
 		<c:set var="sc_list" value="${view_schedule_list}"></c:set>
 	</c:when>
 </c:choose>
 
<link rel="stylesheet" type="text/css" href="${ctx}/css/schedule.css" />

<div id="schedule_form">
	<div id="schedule-header">
		<div id="schedule-view-header">
			<c:if test="${empty today}">
				${view_date}
			</c:if>
			<c:if test="${!empty today}">
				${today}
			</c:if>
		</div>
	</div>
	
	<div id="schedule-con">
		<c:if test="${empty view_schedule}">
			<c:forEach items="${sc_list}" var="loop">
				<div id="schedule-view-con">
					<form action="" method="post" name="submitForm${loop.sc_no}">
						<input type="hidden" name="schedule-view-con-no" value="${loop.sc_no}">
						<div id="schedule-view-con-title">
							<div id="title-${loop.sc_no}">
								${loop.sc_subject}
							</div>
						</div>
						<div id="schedule-view-con-sday">
							<div class="view-con-day-title">
								시작일
							</div>
							<div class="view-con-day-content">
								<div id="sday-${loop.sc_no}">
									${loop.sc_s_date}
								</div>
							</div>
						</div>
						<div id="schedule-view-con-eday">
							<div class="view-con-day-title">
								종료일
							</div>
							<div class="view-con-day-content">
								<div id="eday-${loop.sc_no}">
									${loop.sc_e_date}
								</div>
							</div>
						</div>
						<div id="schedule-view-con-progress">
							<c:choose>
								<c:when test="${check_mb_no == sessionScope.MB_NO || empty check_mb_no}">
									<input type="range" name="range" id="range-${loop.sc_no}" min="0" value="${loop.sc_progress}" max="100" step="25" onchange="rangVal.value=value + ' %'" style="float: left; width: 180px; margin-left: 10px;">
									<output id="rangVal" style="float: right; margin-right: 10px;<c:if test="${loop.sc_progress == 100}">color: #E74C3C; font-weight: bold;</c:if>">${loop.sc_progress} %</output>
								</c:when>
								<c:otherwise>
									<progress value="${loop.sc_progress}" max="100" style="float: left; width: 180px; margin-top: 4px; margin-left: 10px;"></progress>
									<output id="progressVal" style="float: right; margin-right: 10px;<c:if test="${loop.sc_progress == 100}">color: #E74C3C; font-weight: bold;</c:if>">${loop.sc_progress} %</output>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="schedule-view-con-content">
							<div id="content-${loop.sc_no}">
								${loop.sc_content}
							</div>
						</div>
						<div id="shcedule-view-con-btn">
							<c:if test="${check_mb_no == sessionScope.MB_NO || empty check_mb_no}">
								<input type="submit" class="backbtn" name="scheduledelete" value="삭제" style="float: left;" onclick="buttonCheck(this, '${ctx}', '${loop.sc_no}')">
								<input type="submit" class="writecomplete" name="realtimemodify" value="저장" style="float: right;" onclick="buttonCheck(this, '${ctx}', '${loop.sc_no}')">
								<input type="button" id="modify_btn" name="${loop.sc_no}" class="writecomplete" value="내용 수정" style="float: right;">
							</c:if>
						</div>
					</form>
				</div>
			</c:forEach>
		</c:if>
		
		<c:if test="${!empty view_schedule}">
			<div id="schedule-view-con">
				<form action="" method="post" name="submitForm${sc_view.sc_no}">
					<input type="hidden" name="schedule-view-con-no" value="${sc_view.sc_no}">
					<div id="schedule-view-con-title">
						<div id="title-${sc_view.sc_no}">
							${sc_view.sc_subject}
						</div>
					</div>
					<div id="schedule-view-con-sday">
						<div class="view-con-day-title">
							시작일
						</div>
						<div class="view-con-day-content">
							<div id="sday-${sc_view.sc_no}">
								${sc_view.sc_s_date}
							</div>
						</div>
					</div>
					<div id="schedule-view-con-eday">
						<div class="view-con-day-title">
							종료일
						</div>
						<div class="view-con-day-content">
							<div id="eday-${sc_view.sc_no}">
								${sc_view.sc_e_date}
							</div>
						</div>
					</div>
					<div id="schedule-view-con-progress">
						<c:choose>
							<c:when test="${check_mb_no == sessionScope.MB_NO || empty check_mb_no}">
								<input type="range" name="range" id="range-${sc_view.sc_no}" min="0" value="${sc_view.sc_progress}" max="100" step="25" onchange="rangVal.value=value + ' %'" style="float: left; width: 180px; margin-left: 10px;">
								<output id="rangVal" style="float: right; margin-right: 10px;<c:if test="${sc_view.sc_progress == 100}">color: #E74C3C; font-weight: bold;</c:if>">${sc_view.sc_progress} %</output>
							</c:when>
							<c:otherwise>
								<progress value="${sc_view.sc_progress}" max="100" style="float: left; width: 180px; margin-top: 4px; margin-left: 10px;"></progress>
								<output id="progressVal" style="float: right; margin-right: 10px;<c:if test="${sc_view.sc_progress == 100}">color: #E74C3C; font-weight: bold;</c:if>">${sc_view.sc_progress} %</output>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="schedule-view-con-content">
						<div id="content-${sc_view.sc_no}">
							${sc_view.sc_content}
						</div>
					</div>
					<div id="shcedule-view-con-btn">
						<c:if test="${check_mb_no == sessionScope.MB_NO || empty check_mb_no}">
							<input type="submit" class="backbtn" name="scheduledelete" value="삭제" style="float: left;" onclick="buttonCheck(this, '${ctx}', '${sc_view.sc_no}')">
							<input type="submit" class="writecomplete" name="realtimemodify" value="저장" style="float: right;" onclick="buttonCheck(this, '${ctx}', '${sc_view.sc_no}')">
							<input type="button" id="modify_btn" name="${sc_view.sc_no}" class="writecomplete" value="내용 수정" style="float: right;">
						</c:if>
					</div>
				</form>
			</div>
		</c:if>
	</div>
</div>

<!--  글쓰기 다이어로그 -->
<div id="write-dialog">
	<form id="write-form" name="checkform">
		<div id="write-form-subject" style="width: 440px; margin-bottom: 5px;">
			<label for="name" style="float:left; width: 100%; margin-bottom: 3px;">제 목</label>
			<input type="text" name="subject" id="dialog-subject" class="text ui-widget-content" style="width: 100%; padding: 5px !important;">
			<input type="hidden" id="contentNo" name="contentNo">
		</div>
		<div id="write-form-date" style="width: 440px; margin-bottom: 5px;">
			<label for="name" style="float: left; width: 100%; margin-bottom: 3px;">일 정</label>
			<input type="text" id="start_date" name="start_date" readonly="readonly" style="padding: 5px !important; font-weight: bold; font-family: verdana; color: #5D5D5D; border: 1px solid #aaaaaa;">
			 ~ 
			<input type="text" id="end_date" name="end_date" readonly="readonly" style="padding: 5px !important; font-weight: bold; font-family: verdana; color: #5D5D5D; border: 1px solid #aaaaaa;">
		</div>
		<div>
			<label for="name" style="float: left; width: 100%; margin-bottom: 3px;">진행도</label>
			<input type="range" id="progress-range" name="progress" min="0" value="0" max="100" step="25" onchange="rangValue.value=value + ' %'" style="width: 400px" >
			<output id="rangValue"></output>
		</div>
		<div id="write-form-content" style="width: 440px;">
			<label for="name" style="float:left; width: 100%; margin-bottom: 3px;">내 용</label>
			<textarea name="content" id="dialog-content" class="text ui-widget-content" style="width: 100%; height: 300px; padding: 5px !important;"></textarea>
		</div>
	</form>
</div>
<!--  글쓰기 다이어로그 -->

<!-- jquery && Javascript -->
<script type="text/javascript">
	// jQuery //
	var subject;
	var content;
	var startdate;
	var enddate;
	var progress;
	var contentNo;
	
	$(function() {
		$('input#modify_btn').click(function() {
			$('#write-dialog').dialog('close');
			event.preventDefault();
			
			contentNo = $(this).attr('name');
			
			$('#dialog-subject').val("");
			$('#contentNo').val("");
			$('#start-_date').val("");
			$('#end_date').val("");
			$('#progress-range').val(0);
			$('#dialog-content').val("");
			
			subject = $.trim($("#title-" + contentNo).text());
			content = $.trim($("#content-" + contentNo).text());
			startdate = $.trim($("#sday-" + contentNo).text());
			enddate = $.trim($("#eday-" + contentNo).text());
			progress = $("#range-" + contentNo).val();
			
			$('#dialog-subject').val(subject);
			$('#contentNo').val(contentNo);
			$('#start_date').val(startdate);
			$('#end_date').val(enddate);
			$('#progress-range').val(progress);
			$('#rangValue').val(progress + " %");
			$('#dialog-content').val(content);
			
			$('#write-dialog').dialog('open');
		});
		
		$("#write-dialog").dialog({
			autoOpen: false,
		    resizable: false,
			width: 478,
			show: {
				effect: "fold",
				duration: 500
			},
			buttons: {
				"스케줄 수정": function() {
					var subject = $('#dialog-subject').val();
					if (subject != '') {
						$.ajax({
							type: "POST",
							async: false, 
							url: "${ctx}/Schedule/ScheduleModify.do",
							data: $('#write-form').serialize(),
							success: function(msg) {
								alert(msg);
							}
						});
						
						$( this ).dialog( "close" );
						document.location.reload();
					} else {
						alert('제목을 꼭 써주세요');
					}
						
					    return false;
				},
	        	닫기: function() {
	        		$('#dialog-subject').val("");
	        		$('#contentNo').val("");
	    			$('#start-_date').val("");
	    			$('#end_date').val("");
	    			$('#progress-range').val(0);
	    			$('#dialog-content').val("");
	            	$( this ).dialog( "close" );
	            }
			}
		});
	});

	$(document).ready(function() {
		$('#start_date').datepicker({
			dateFormat: "yy-mm-dd",
			showAnim: "slide",
			showMonthAfterYear: true ,
			dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
			monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ], 
			numberOfMonths: [ 1, 3 ],
			showCurrentAtPos: 1,
			stepMonths: 3,
			nextText: "차월", 
			prevText: "전월", 
			gotoCurrent: true,
			onSelect: function() {
				var start = $('#start_date').val();
				var end = $('#end_date').val();
				
				if (end.length>0) {
					var start_date = parseInt(start.replace(/-/g, ''));
					var end_date = parseInt(end.replace(/-/g, ''));

					if (end_date<start_date) {
						alert("종료일이 시작일보다 빠를 수 없습니다.");
						$('#start_date').val('');
					}
				}
			}
		});
		
		$('#end_date').datepicker({
			dateFormat: "yy-mm-dd",
			showAnim: "slide",
			showMonthAfterYear: true ,
			dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
			monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ], 
			numberOfMonths: [ 1, 3 ],
			showCurrentAtPos: 1,
			stepMonths: 3,
			nextText: "차월", 
			prevText: "전월", 
			gotoCurrent: true,
			onSelect: function() {
				var start = $('#start_date').val();
				var end = $('#end_date').val();
				
				if (start.length>0) {
					var start_date = parseInt(start.replace(/-/g, ''));
					var end_date = parseInt(end.replace(/-/g, ''));

					if (end_date<start_date) {
						alert("종료일이 시작일보다 빠를 수 없습니다.");
						$('#end_date').val('');
					}
				}
			}
		});
	});
	// jQuery //
	
	
	// javascript //
	/**	
	*	저장 & 삭제 버튼 체크 후
	*	관련 작업 수행
	**/	
	function buttonCheck(obj, ctx, no) {
		var name = "submitForm" + no;
		var subForm = document.forms[name];
		var btn = obj.name;
		var msg = "";
		var url;
		
		if (btn == "scheduledelete") {
			url = ctx + "/Schedule/ScheduleDelete.do";
			msg = "삭제 하시겠습니까?";
			confirmDialog(subForm, msg, url);
		} else if (btn == "realtimemodify") {
			subForm.action = ctx + "/Schedule/ScheduleModify.do";
			subForm.submit();
		}
	}
	// javascript // 
</script>
<!-- jquery -->