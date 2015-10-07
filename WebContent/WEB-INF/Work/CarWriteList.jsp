<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script>
$(function() {
  $( "#car_submit_date" ).datepicker({
    dateFormat: 'yy-m-dd',
    showAnim: "slide",
	showMonthAfterYear: true ,
	dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
	monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ], 
	numberOfMonths: [ 1, 3 ],
	showCurrentAtPos: 1,
	stepMonths: 3,
	nextText: "차월", 
	prevText: "전월", 
  });
});
</script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/Work.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/Community.css" />
<div id="co-main">
	<div id="co-write-title">
		이부분에 현재 글쓰기 위치 꽃기 - 글쓰기
	</div>
	<form action="${ctx}/Work/CarEventProc.do" method="post">
		<div id="co-write-form">
			<div id="co-write-form-subject">배차 날짜 선택</div>
				<div id="co-write-form-input">
					<input type="text" name="car_submit_date" id="car_submit_date" class="input-form" readonly="readonly"/>
				</div>
			
			
			<div id="co-write-form-input" style="height: 100%;">
				<textarea class="co-textarea" name="reason" style="height: 100px;" onclick="this.value=''">
					배차 신청서의 이유는 수정이 불가능합니다. 삭제만 가능합니다.${cs_no}
				</textarea>
			</div>
		</div>
			<input class="backbtn" onclick="javascript:history.go(-1)" type="button"
			value="뒤로 가기" style="float: left; margin-top: 5px;" />
			<input class="writecomplete" type="submit" value="작성 완료" style="float: right;"/>
			<input type="hidden" name="type" value="write">
			<input type="hidden" name="cl_no" value="${cl_no}">
			<input type="hidden" name="year" value="${param.year}">
			<input type="hidden" name="month" value="${param.month}">
	</form>
	
	<div id="co-write-title" style="margin-top: 50px;">
		이 자동차에 대한 신청 현황
	</div>
	<c:forEach var="n" items="${LIST}">
		<div id="work-car-calc">
			<div id="work-car-calc-date">
				${n.cs_use_date}
			</div>
			<div id="work-car-calc-schedule">
			<c:choose>
				<c:when test="${n.mb_img == '' || empty n.mb_img || n.mb_img != null}">
					&nbsp;&nbsp;${n.cs_reason} 신청자 - <a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/images/noimg.png">${n.mb_posi_name} ${n.mb_name}</a>
				</c:when>
				<c:otherwise>
					&nbsp;&nbsp;${n.cs_reason} 신청자 - <a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/MemberImg/${n.mb_img}">${n.mb_posi_name} ${n.mb_name}</a>
				</c:otherwise>
			</c:choose>
			</div>
			<div id="work-car-calc-delete">
				<c:if test="${n.mb_no == sessionScope.MB_NO}">
				<form action="${ctx}/Work/CarEventProc.do">
					<input class="writecomplete" type="submit" value="삭제" style="width: 35px !important; height: 19px;"/>
					<input type="hidden" name="cs_no" value="${n.cs_no}">
					<input type="hidden" name="type" value="delete">
					<input type="hidden" name="cl_no" value="${param.cl_no}">
					<input type="hidden" name="year" value="${param.year}">
					<input type="hidden" name="month" value="${param.month}">
				</form>
				</c:if>
				<c:if test="${n.mb_no != sessionScope.MB_NO}">
					&nbsp;
				</c:if>
			</div>
		</div>
	</c:forEach>
	
</div>









