<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<c:set var="year" value="${YEAR}"/>
<c:set var="month" value="${MONTH}"/>
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
<script>
function printTime() {
              var clock = document.getElementById("clock");            // 출력할 장소 선택
              var now = new Date();                                                  // 현재시간
              var nowTime = now.getFullYear() + "년 " + (now.getMonth()+1) + "월 " + now.getDate() + "일 " + now.getHours() + "시 " + now.getMinutes() + "분 ";
              clock.innerHTML = nowTime;           // 현재시간을 출력
              setTimeout("printTime()",1000);         // setTimeout(“실행할함수”,시간) 시간은1초의 경우 1000
}
window.onload = function() {                         // 페이지가 로딩되면 실행
              printTime();
}
</script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/Work.css" />
<script src="${ctx}/js/clock.js"></script>
<div id="work-main">
	<div id="car-status-title">
	배차현황 및 신청
	</div>

		<div class="clocks">
			<canvas id="canvas" width="300" height="300"></canvas>
		</div>
		
		<div style="float: left;">
			<div class="work-confirm">
				<form action="${ctx}/Work/WorkTimeEventProc.do" method="post">
					<input type="submit" class="writecomplete" value="출근 하기" 
					style="clear: both; width: 150px; height: 50px;">
					<input type="hidden" name="type" value="start">
				</form>
			</div>
			
			<div class="work-confirm">
				<form action="${ctx}/Work/WorkTimeEventProc.do" method="post">
					<input type="submit" class="writecomplete" value="퇴근 하기" 
					style="background: #3F5765; width: 150px; height: 50px;">
					<input type="hidden" name="type" value="end">
				</form>
			</div>
			
			<div class="work-confirm" style="font-size: 25px; height: 58px; line-height: 58px;">
				<span id="clock"></span>
			</div>
				<span style="font-size: 8pt;color: #E74C3C; font-weight: bold;">※위의 시간과 실제 기록되는 시간은 오차가 있을 수 있습니다.</span>
		</div>
		
		
		<div class="att-list-main">
			<div id="work-status-title">
				<div id="work-status-title-month-1">
					<a href="${ctx}/Work/WorkTime.work?year=${preyear}&month=${premonth}">
						이전달
					</a>
				</div>
				<div id="work-status-title-month">
					${YEAR}년 ${MONTH}월
				</div>
				<div id="work-status-title-month-2">
					<a href="${ctx}/Work/WorkTime.work?year=${nextyear}&month=${nextmonth}">
						다음달
					</a>
				</div>
			</div>
		</div>
		
		<div class="att-list-title-main">
			<div class="att-list-title-main-title1">
				날짜
			</div>
			
			<div class="att-list-title-main-title2">
				출근시간  및 초과시간
			</div>
			
			<div class="att-list-title-main-title3">
				퇴근시간 및 초과 시간
			</div>
		</div>
		
		<c:forEach var="lastday" begin="1" end="${lastday}" step="1">
			<c:set value="${lastday}" var="datecount"/>
			<c:if test="${datecount < 10}">
				<c:set value="0${lastday}" var="datecount"/>
			</c:if>
			<c:set var="i" value="1"/>
			<c:forEach var="n" items="${list}">
				<c:if test="${datecount == n.dayOfdate}">
					<div class="att-list-title-main1">
						<div class="att-list-title-main-content1">
							<c:choose>
								<c:when test="${lastday == day}">
									<font color="#E74C3C">Today - ${lastday}</font>
								</c:when>
								<c:otherwise>
									${lastday}
								</c:otherwise>
							</c:choose>
						</div>
				
						<div class="att-list-title-main-content2">
							<div class="att-list-title-main-content2-sub1">
								${n.att_s_date}
							</div>
							<div class="att-list-title-main-content2-sub2">
								${n.over_s}
							</div>
						</div>
					
						<div class="att-list-title-main-content3">
							<div class="att-list-title-main-content2-sub1">
								${n.att_e_date}
							</div>
							<div class="att-list-title-main-content2-sub2">
								${n.over_e}
							</div>
						</div>
					</div>
				<c:set var="i" value="0"/>
				</c:if>
			</c:forEach>
			<c:if test="${i==1}">
					<div class="att-list-title-main1">
						<div class="att-list-title-main-content1">
							<c:choose>
								<c:when test="${lastday == day}">
									<font color="#E74C3C">${lastday}</font>
								</c:when>
								<c:otherwise>
									${lastday}
								</c:otherwise>
							</c:choose>
						</div>
				
						<div class="att-list-title-main-content2">
							${n.att_s_date}
						</div>
					
						<div class="att-list-title-main-content3">
							${n.att_e_date}
						</div>
					</div>
			</c:if>
		</c:forEach>
</div>