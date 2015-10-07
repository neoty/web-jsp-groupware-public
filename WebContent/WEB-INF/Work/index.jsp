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

<link rel="stylesheet" type="text/css" href="${ctx}/css/Work.css" />
<div id="work-main">
	<div id="work-status-title">
	배차현황 및 신청
	</div>
	<div id="work-status-title">
		<div id="work-status-title-month-1">
			<a href="${ctx}/Work/index.work?year=${preyear}&month=${premonth}">
				이전달
			</a>
		</div>
		<div id="work-status-title-month">
			${YEAR}년 ${MONTH}월
		</div>
		<div id="work-status-title-month-2">
			<a href="${ctx}/Work/index.work?year=${nextyear}&month=${nextmonth}">
				다음달
			</a>
		</div>
	</div>
	<div id="work-sub-title">
		<ul class="work-list-subject">
			<li style="width: 60px;margin-left: 7px;">차량번호</li>
			<li class="work-subject-date-list">
				<c:set var="cnt" value="${SAT}"/>
				<c:set var="cnt1" value="${SAT+1}"/>
					<c:forEach var="a" step="1" end="${LAST_DAY}" begin="1">
						<c:choose>
							<c:when test="${a == SAT || a%cnt == 0}">
								<div style="float: left; width: ${DAY_WIDTH}%; background: #12EAFF; text-align: center">${a}</div>
								<c:set var="cnt" value="${cnt+7}"/>
							</c:when>
							<c:when test="${a == SAT+1||a%cnt1 == 0}">
								<div style="float: left; width: ${DAY_WIDTH}%; background: #F15F5F; text-align: center">${a}</div>
								<c:set var="cnt1" value="${cnt1+7}"/>
							</c:when>
							<c:otherwise>
								<div style="float: left; width: ${DAY_WIDTH}%; text-align: center">${a}</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</li>
			<li style="width: 72px;">신청</li>
		</ul>
	</div>
	
	<c:forEach var="n" items="${carlist}">
		<form action="${ctx}/Work/CarWriteAndList.work" method="post">
		<div id="work-list-content">
				<div style="width: 60px;margin-left: 7px; float: left; line-height: 15px;">${n.cl_number}</div>
					<div style="width: 590px; float: left; text-align: right;">
						<c:forEach var="b" step="1" end="${LAST_DAY}" begin="1">
								<c:set var="is" value="1"/>
								<c:forEach var="c" items="${list}">
									<c:choose>
										<c:when test="${b == c.cs_use_date && n.cl_no == c.cs_cl_number}">
											<div style="float: left; width: ${DAY_WIDTH}%; text-align: center">
												<input type="checkbox" checked disabled name="disable">
											</div>
											<c:set var="is" value="0"/>
										</c:when>
									</c:choose>
								</c:forEach>
								<c:if test="${is == 1}">
									<div style="float: left; width: ${DAY_WIDTH}%; text-align: center">
										<input type="checkbox" disabled name="disable">
									</div>
									<c:set var="is" value="1"/>	
								</c:if>
						</c:forEach>
					</div>
				<div style="width: 72px; float: left;">
					<input type="submit" value="신청" style="width: 35px;">
						<input type="hidden" name="cl_no" value="${n.cl_no}">
						<input type="hidden" name="year" value="${year}">
						<input type="hidden" name="month" value="${month}">
	
				</div>
				<div style="clear: left;"></div>
		</div>
		</form>
	</c:forEach>
	<c:if test="${empty carlist}">
		<div style="text-align: center">사용 가능한 차량이 없습니다. 관리자에게 문의 해주세요. </div>
	</c:if>
</div>