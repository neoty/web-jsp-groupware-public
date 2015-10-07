<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="year" value="${P_YEAR}" />
<c:set var="thisYear" value="${P_THISYEAR}" />
<c:set var="month" value="${P_MONTH + 1}" />
<c:set var="thisMonth" value="${P_THISMONTH + 1}" />
<c:set var="today" value="${P_DAY}" />
<c:set var="lastDay" value="${P_LASTDAY}" />
<c:set var="startDay" value="${P_STARTDAY}" />
<c:set var="endDay" value="${P_ENDDAY}" />
<c:set var="todayList" value="${T_SC_LIST}" />
<c:set var="thisMonList" value="${M_SC_LIST}" />
<c:set var="dayofscscnt" value="${DAYOFSCSCNT}" />
<c:set var="dayofscecnt" value="${DAYOFSCECNT}" />
<c:set var="dep_mb_no" value="${DEP_MB_NO}" />
<c:set var="dep_no" value="${DEP_NO}" />

<link rel="stylesheet" type="text/css" href="${ctx}/css/schedule.css" />

<c:choose>
	<c:when test="${month == 12}">
		<c:set var="nextyear" value="${year + 1}" />
		<c:set var="nextmonth" value="1" />
	</c:when>
	<c:when test="${month == 1}">
		<c:set var="nextmonth" value="2" />
		<c:set var="nextyear" value="${year}" />
	</c:when>
	<c:otherwise>
		<c:set var="nextmonth" value="${month + 1}" />
		<c:set var="nextyear" value="${year}" />
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${month == 12}">
		<c:set var="preyear" value="${year}" />
		<c:set var="premonth" value="${month -1}" />
	</c:when>
	<c:when test="${month == 1}">
		<c:set var="preyear" value="${year - 1}" />
		<c:set var="premonth" value="12" />
	</c:when>
	<c:otherwise>
		<c:set var="preyear" value="${year}" />
		<c:set var="premonth" value="${month - 1}" />
	</c:otherwise>
</c:choose>
<div id="schedule_form">
	<div id="schedule-navi">
		<div id="navi-sc1">
				<a href="${ctx}/Schedule/index.schedule?<c:if test="${!empty dep_mb_no}">no=${dep_mb_no}&</c:if><c:if test="${!empty dep_no}">dep=${dep_no}&</c:if>getYear=${preyear}&getMonth=${premonth}"
					class="sc-link"> 이전달</a>
			</div>
			<div id="navi-sc2">${year}년${month}월</div>
			<div id="navi-sc3">
				<a href="${ctx}/Schedule/index.schedule?<c:if test="${!empty dep_mb_no}">no=${dep_mb_no}&</c:if><c:if test="${!empty dep_no}">dep=${dep_no}&</c:if>getYear=${nextyear}&getMonth=${nextmonth}"
					class="sc-link"> 다음달</a>
		</div>
	</div>
	<div id="schedule-con">
		<div id="firstweek" style="">Sun</div>
		<div id="week">Mon</div>
		<div id="week">Tue</div>
		<div id="week">Wed</div>
		<div id="week">Thu</div>
		<div id="week">Fri</div>
		<div id="lastweek">Sat</div>
	</div>
	<div id="sc-content">
		<c:set var="color" value="none" />
		<c:set var="count" value="0" />
		<c:forEach begin="1" end="${lastDay + startDay - 1}" step="1" var="varthis">
			<c:set var="writeDay" value="${varthis - startDay + 1}"/>
			<c:if test="${varthis < startDay}">
				<div id="sc-sun" style="float: left !important;"></div>
			</c:if>

			<c:if test="${varthis >= startDay}">
				<c:choose>
					<c:when test="${varthis == 1 && writeDay == 1}">
						<div id="sc-sunday" style="float: left !important; <c:if test='${writeDay == today && month == thisMonth}'> background: #CEF279 !important;</c:if>">
							<div id = "show-today">
								<font color="#E74C3C"><b>${writeDay}</b></font>
							</div>
							<div id = "schedule-write">
								<sc-w-ic id="write-url" href="#" year="${year}" month="${month}" day="${writeDay}">
									<c:if test="${empty DEP_MB_NO || DEP_MB_NO == sessionScope.MB_NO}">
										<img src="${ctx}/images/icons/table_edit.png">
									</c:if>
								</sc-w-ic>
							</div>
							
							<c:set var="compDate" value="${year}${month}${writeDay}"></c:set>
							<c:set var="btnCnt" value="0" />
							<c:forEach items="${dayofscscnt}" var="list">
								<c:if test="${list == compDate}">
									<c:set var="btnCnt" value="${btnCnt + 1}" />
								</c:if>
							</c:forEach>
							<c:forEach items="${dayofscecnt}" var="list">
								<c:if test="${list == compDate}">
									<c:set var="btnCnt" value="${btnCnt + 1}" />
								</c:if>
							</c:forEach>
							
							<c:if test="${btnCnt != 0}">
								<a href="${ctx}/Schedule/View.schedule?<c:if test="${!empty dep_mb_no}">no=${dep_mb_no}&</c:if><c:if test="${!empty dep_no}">dep=${dep_no}&</c:if>date=${year}-${month}-${writeDay}" id="schedule-link">
							</c:if>
							<div id="schedule-view">
								<c:forEach items="${thisMonList}" var="list">
									<c:choose>
										<c:when test="${list.sc_s_date == compDate && list.sc_e_date != compDate}">
											<div id="schedule-view-title">
												<img src="${ctx}/images/icons/sc_s_day.png" id="schedule_image">
												${list.sc_subject}
											</div>
										</c:when>
										
										<c:when test="${list.sc_e_date == compDate && list.sc_s_date != compDate}">
											<div id="schedule-view-title"> 
												<img src="${ctx}/images/icons/sc_e_day.png" id="schedule_image">
												${list.sc_subject}
											</div>
										</c:when>
										
										<c:when test="${list.sc_s_date == compDate && list.sc_e_date == compDate}">
											<div id="schedule-view-title"> 
												<img src="${ctx}/images/icons/sc_t_day.png" id="schedule_image">
												${list.sc_subject}
											</div>
										</c:when>
									</c:choose>
								</c:forEach>
							</div>
							<c:if test="${btnCnt != 0}">
								</a>
							</c:if>
							<c:set var="btnCnt" value="0" />
						</div>
						<c:set var="color" value="none" />
					</c:when>
					
					<c:when test="${varthis %7 == 0 }">
						<div id="sc-satday" style="float: left !important; <c:if test='${writeDay == today && month == thisMonth}'> background: #CEF279 !important;</c:if>">
							<div id = "show-today">
								<font color="#3DB7CC"><b>${writeDay}</b></font>
							</div>
							<div id = "schedule-write">
								<sc-w-ic id="write-url" href="#" year="${year}" month="${month}" day="${writeDay}">
									<c:if test="${empty DEP_MB_NO || DEP_MB_NO == sessionScope.MB_NO}">
										<img src="${ctx}/images/icons/table_edit.png">
									</c:if>
								</sc-w-ic>
							</div>
							
							<c:set var="compDate" value="${year}${month}${writeDay}"></c:set>
							<c:set var="btnCnt" value="0" />
							<c:forEach items="${dayofscscnt}" var="list">
								<c:if test="${list == compDate}">
									<c:set var="btnCnt" value="${btnCnt + 1}" />
								</c:if>
							</c:forEach>
							<c:forEach items="${dayofscecnt}" var="list">
								<c:if test="${list == compDate}">
									<c:set var="btnCnt" value="${btnCnt + 1}" />
								</c:if>
							</c:forEach>
							
							<c:if test="${btnCnt != 0}">
								<a href="${ctx}/Schedule/View.schedule?<c:if test="${!empty dep_mb_no}">no=${dep_mb_no}&</c:if><c:if test="${!empty dep_no}">dep=${dep_no}&</c:if>date=${year}-${month}-${writeDay}" id="schedule-link">
							</c:if>
							<div id="schedule-view">
								<c:forEach items="${thisMonList}" var="list">
									<c:if test="${count < 5}">
										<c:choose>
											<c:when test="${list.sc_s_date == compDate && list.sc_e_date != compDate}">
												<div id="schedule-view-title">
													<img src="${ctx}/images/icons/sc_s_day.png" id="schedule_image">
													${list.sc_subject}
												</div>
												<c:set var="count" value="${count + 1}" />
											</c:when>
											
											<c:when test="${list.sc_e_date == compDate && list.sc_s_date != compDate}">
												<div id="schedule-view-title"> 
													<img src="${ctx}/images/icons/sc_e_day.png" id="schedule_image">
													${list.sc_subject}
												</div>
												<c:set var="count" value="${count + 1}" />
											</c:when>
											
											<c:when test="${list.sc_s_date == compDate && list.sc_e_date == compDate}">
												<div id="schedule-view-title"> 
													<img src="${ctx}/images/icons/sc_t_day.png" id="schedule_image">
													${list.sc_subject}
												</div>
												<c:set var="count" value="${count + 1}" />
											</c:when>
										</c:choose>
									</c:if>
								</c:forEach>
								<c:set var="count" value="0"/>
							</div>
							<c:if test="${btnCnt != 0}">
								</a>
							</c:if>
							<c:set var="btnCnt" value="0" />
						</div>
						<c:set var="color" value="Saturday" />
					</c:when>
					
					<c:when test="${color == 'Saturday'}">
						<div id="sc-sunday" style="float: left !important; <c:if test='${writeDay == today && month == thisMonth}'> background: #CEF279 !important;</c:if>">
							<div id = "show-today">
								<font color="#E74C3C"><b>${writeDay}</b></font>
							</div>
							<div id = "schedule-write">
								<sc-w-ic id="write-url" href="#" year="${year}" month="${month}" day="${writeDay}">
									<c:if test="${empty DEP_MB_NO || DEP_MB_NO == sessionScope.MB_NO}">
										<img src="${ctx}/images/icons/table_edit.png">
									</c:if>
								</sc-w-ic>
							</div>
							
							<c:set var="compDate" value="${year}${month}${writeDay}"></c:set>
							<c:set var="btnCnt" value="0" />
							<c:forEach items="${dayofscscnt}" var="list">
								<c:if test="${list == compDate}">
									<c:set var="btnCnt" value="${btnCnt + 1}" />
								</c:if>
							</c:forEach>
							<c:forEach items="${dayofscecnt}" var="list">
								<c:if test="${list == compDate}">
									<c:set var="btnCnt" value="${btnCnt + 1}" />
								</c:if>
							</c:forEach>
							
							<c:if test="${btnCnt != 0}">
								<a href="${ctx}/Schedule/View.schedule?<c:if test="${!empty dep_mb_no}">no=${dep_mb_no}&</c:if><c:if test="${!empty dep_no}">dep=${dep_no}&</c:if>date=${year}-${month}-${writeDay}" id="schedule-link">
							</c:if>
							<div id="schedule-view">
								<c:forEach items="${thisMonList}" var="list">
									<c:if test="${count < 5}">
										<c:choose>
											<c:when test="${list.sc_s_date == compDate && list.sc_e_date != compDate}">
												<div id="schedule-view-title">
													<img src="${ctx}/images/icons/sc_s_day.png" id="schedule_image">
													${list.sc_subject}
												</div>
												<c:set var="count" value="${count + 1}" />
											</c:when>
											
											<c:when test="${list.sc_e_date == compDate && list.sc_s_date != compDate}">
												<div id="schedule-view-title"> 
													<img src="${ctx}/images/icons/sc_e_day.png" id="schedule_image">
													${list.sc_subject}
												</div>
												<c:set var="count" value="${count + 1}" />
											</c:when>
											
											<c:when test="${list.sc_s_date == compDate && list.sc_e_date == compDate}">
												<div id="schedule-view-title"> 
													<img src="${ctx}/images/icons/sc_t_day.png" id="schedule_image">
													${list.sc_subject}
												</div>
												<c:set var="count" value="${count + 1}" />
											</c:when>
										</c:choose>
									</c:if>
								</c:forEach>
								<c:set var="count" value="0"/>
							</div>
							<c:if test="${btnCnt != 0}">
								</a>
							</c:if>
							<c:set var="btnCnt" value="0" />
						</div>
						<c:set var="color" value="none" />
					</c:when>
					
					<c:otherwise>
						<div id="sc-sun" style="float: left !important; <c:if test='${writeDay == today && month == thisMonth}'> background: #CEF279 !important;</c:if>">
							<div id = "show-today">
								<font color="#3F5765"><b>${writeDay}</b></font>
							</div>
							<div id = "schedule-write">
								<sc-w-ic id="write-url" href="#" year="${year}" month="${month}" day="${writeDay}" >
									<c:if test="${empty DEP_MB_NO || DEP_MB_NO == sessionScope.MB_NO}">
										<img src="${ctx}/images/icons/table_edit.png">
									</c:if>
								</sc-w-ic>
							</div>
							
							<c:set var="compDate" value="${year}${month}${writeDay}"></c:set>
							<c:set var="btnCnt" value="0" />
							<c:forEach items="${dayofscscnt}" var="list">
								<c:if test="${list == compDate}">
									<c:set var="btnCnt" value="${btnCnt + 1}" />
								</c:if>
							</c:forEach>
							<c:forEach items="${dayofscecnt}" var="list">
								<c:if test="${list == compDate}">
									<c:set var="btnCnt" value="${btnCnt + 1}" />
								</c:if>
							</c:forEach>
							
							<c:if test="${btnCnt != 0}">
								<a href="${ctx}/Schedule/View.schedule?<c:if test="${!empty dep_mb_no}">no=${dep_mb_no}&</c:if><c:if test="${!empty dep_no}">dep=${dep_no}&</c:if>date=${year}-${month}-${writeDay}" id="schedule-link">
							</c:if>
							<div id="schedule-view">
								<c:forEach items="${thisMonList}" var="list">
									<c:if test="${count < 5}">
										<c:choose>
											<c:when test="${list.sc_s_date == compDate && list.sc_e_date != compDate}">
												<div id="schedule-view-title">
													<img src="${ctx}/images/icons/sc_s_day.png" id="schedule_image">
													${list.sc_subject}
												</div>
												<c:set var="count" value="${count + 1}" />
											</c:when>
											
											<c:when test="${list.sc_e_date == compDate && list.sc_s_date != compDate}">
												<div id="schedule-view-title"> 
													<img src="${ctx}/images/icons/sc_e_day.png" id="schedule_image">
													${list.sc_subject}
												</div>
												<c:set var="count" value="${count + 1}" />
											</c:when>
											
											<c:when test="${list.sc_s_date == compDate && list.sc_e_date == compDate}">
												<div id="schedule-view-title"> 
													<img src="${ctx}/images/icons/sc_t_day.png" id="schedule_image">
													${list.sc_subject}
												</div>
												<c:set var="count" value="${count + 1}" />
											</c:when>
										</c:choose>
									</c:if>
								</c:forEach>
								<c:set var="count" value="0"/>
							</div>
							<c:if test="${btnCnt != 0}">
								</a>
							</c:if>
							<c:set var="btnCnt" value="0" />
						</div>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>
		
		<c:if test="${7-endDay != 0}">
			<c:set var="end" value="${7-endDay}"/>
			<c:forEach begin="1" end="${7-endDay}" step="1" var="endvar">
				<c:if test="${endvar < end}">
					<div id="sc-sun" style="float: left"></div>
				</c:if>
				<c:if test="${endvar == end}">
					<div id="sc-satday" style="float: left"></div>
				</c:if>
			</c:forEach>
		</c:if>
	</div>
	<hr color="#3F5765" width="100%" size="8">

<!-- 오늘 스케줄 표시 -->
	<c:if test="${!empty todayList}">
		<div id="sc-today">
			<div id="sc-today-title"><img src="${ctx}/images/pathicon.gif"> 금일 개인 스케줄( ${thisMonth}월 ${today}일 )</div>
			<div id="sc-today-title-subject">제목</div>
			<div id="sc-today-title-progress">진행도</div>
			<div id="sc-today-title-date">종료일</div>
			
			<c:forEach items="${todayList}" var="today_List">
				<a href="${ctx}/Schedule/View.schedule?date=${thisYear}-${thisMonth}-${today}&sc=${today_List.sc_no}">
					<div id="sc-today-content">
						<div id="sc-today-subject">${today_List.sc_subject}</div>
						<div id="sc-today-progress"><progress value="${today_List.sc_progress}" max="100" style="width: 150px;"></progress></div>
						<div id="sc-today-date">${today_List.sc_e_date}</div>
					</div>
				</a>
			</c:forEach>
		</div>
	</c:if>
</div>
<!-- ---------------- -->

<!--  글쓰기 다이어로그 -->
<div id="write-dialog">
	<form id="write-form" name="checkform">
		<div id="write-form-subject" style="width: 440px; margin-bottom: 5px;">
			<label for="name" style="float:left; width: 100%; margin-bottom: 3px;">제 목</label>
			<input type="text" name="subject" id="dialog-subject" class="text ui-widget-content" style="width: 100%; padding: 5px !important;">
		</div>
		<div id="write-form-date" style="width: 440px; margin-bottom: 5px;">
			<label for="name" style="float: left; width: 100%; margin-bottom: 3px;">일 정</label>
			<input type="text" id="start_date" name="start_date" readonly="readonly" style="padding: 5px !important; font-weight: bold; font-family: verdana; color: #5D5D5D; border: 1px solid #aaaaaa;">
			 ~ 
			<input type="text" id="end_date" name="end_date" readonly="readonly" style="padding: 5px !important; font-weight: bold; font-family: verdana; color: #5D5D5D; border: 1px solid #aaaaaa;">
		</div>
		<div>
			<label for="name" style="float: left; width: 100%; margin-bottom: 3px;">진행도</label>
			<input type="range" name="progress" min="0" value="0" max="100" step="25" onchange="rangValue.value=value + ' %'" style="width: 400px">
			<output id="rangValue">0 %</output>
			<c:choose>
				<c:when test="${!empty dep_no}">
					<input type="hidden" name="department" value="1">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="department" value="0">
				</c:otherwise>
			</c:choose>
		</div>
		<div id="write-form-content" style="width: 440px;">
			<label for="name" style="float:left; width: 100%; margin-bottom: 3px;">내 용</label>
			<textarea name="content" id="dialog-content" class="text ui-widget-content" style="width: 100%; height: 300px; padding: 5px !important;"></textarea>
		</div>
	</form>
</div>
<!--  글쓰기 다이어로그 -->
	
<!-- jquery -->
<script type="text/javascript">
	var year="";
	var month="";
	var day="";
	var date="asdf";
	$(function() {
		$("sc-w-ic").click(function() {
			$("#write-dialog").dialog("close");
			event.preventDefault();
			year = $(this).attr("year");
			month = $(this).attr("month");
			day = $(this).attr("day");
			if (month.length == 1) {
				month = "0" + month;
			}
			if (day.length == 1) {
				day = "0" + day;
			}
			
			$("#start_date").val(year + "-" + month + "-" + day);
			$("#write-dialog").dialog("open");
			$("span.ui-dialog-title").text(year+"년 "+month+"월 "+day+"일");
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
				"스케줄 쓰기": function() {
					var subject = $('#dialog-subject').val();
					var endDate = $('#end_date').val();
					if (subject != '') {
						if (endDate == "" || endDate == null) {
							$('#end_date').val($('#start_date').val());
						}
						$.ajax({
							type: "POST",
							async: false, 
							url: "${ctx}/Schedule/ScheduleWrite.do",
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
	        		$('#dialog-subject').val('');
	        		$('#start_date').val('');
	        		$('#end_date').val('');
	        		$('#dialog-content').val('');
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
</script>
<!-- jquery -->