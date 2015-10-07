<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/config.css" />
<div id="config-main">
	<div id="config-status-title">
	서버 및 관리자 정보
	</div>
	
	<form action="" method="post">
	<div id="config-status-des">Context Name</div>
	<div id="config-status-value">/Elite</div>

	<div id="config-status-des">Server Ip</div>
	<div id="config-status-value">211.227.151.174</div>
	
	<div id="config-status-des">그룹웨어 생성일자</div>
	<div id="config-status-value">2014-02-02 08:32:44</div>
	
	<div id="config-status-des">관리자 아이디</div>
	<div id="config-status-value">Administrator</div>
	
	<div id="config-status-des">관리자 이메일</div>
	<div id="config-status-value">
		<input type="text" class="config-input-form" 
		name="email" value="neoty@naver.com">
	</div>
	
	<div id="config-status-des">관리자 전화번호</div>
	<div id="config-status-value">
		<input type="text" class="config-input-form" 
		name="phone" value="010-4242-3232">
	</div>
	
	<div id="config-status-des">그룹웨어 정지 여부</div>
	<div id="config-status-value">
		<select name="job" class="config-combo-form">
		<option value="1">그룹웨어 사용</option>
		<option value="0" selected>그룹웨어 정지</option>
		</select>
	</div>
	
	<div id="config-status-des">그룹웨어 정지 타이틀</div>
	<div id="config-status-value">
		<input type="text" class="config-input-form" 
		name="stop-title" value="사이트가 정지되었습니다. " style="width: 300px !important;">
	</div>
	
	<div id="config-status-des">출퇴근 허용 IP(미 사용시 공백)</div>
	<div id="config-status-value">
		<input type="text" class="config-input-form" 
		name="allowip1" value="" style="width: 30px !important;"> .
		<input type="text" class="config-input-form" 
		name="allowip2" value="" style="width: 30px !important;"> .
		<input type="text" class="config-input-form" 
		name="allowip3" value="" style="width: 30px !important;"> .
		<input type="text" class="config-input-form" 
		name="allowip4" value="" style="width: 30px !important;">
	</div>
	
	<div id="config-status-des">출퇴근 허용 IP 대역(미 사용시 공백)</div>
	<div id="config-status-value">
		<input type="text" class="config-input-form" 
		name="bandip1" value="" style="width: 30px !important;"> .
		<input type="text" class="config-input-form" 
		name="bandip2" value="" style="width: 30px !important;"> .
		<input type="text" class="config-input-form" 
		name="bandip3" value="" style="width: 30px !important;"> .
		<input type="text" class="config-input-form" 
		name="bandip4" value="" style="width: 30px !important;"> - 
				<input type="text" class="config-input-form" 
		name="bandip5" value="" style="width: 30px !important;"> .
		<input type="text" class="config-input-form" 
		name="bandip6" value="" style="width: 30px !important;"> .
		<input type="text" class="config-input-form" 
		name="bandip7" value="" style="width: 30px !important;"> .
		<input type="text" class="config-input-form" 
		name="bandip8" value="" style="width: 30px !important;">
	</div>
	
	<div id="config-status-des">그룹웨어 가입 기능</div>
	<div id="config-status-value">
		<select name="membership" class="config-combo-form">
		<option value="1">가입 가능</option>
		<option value="0" selected>가입 불가능</option>
		</select>
	</div>
	
	<div id="config-status-des">출근 시간</div>
	<div id="config-status-value">
		<select name="starthour" class="config-combo-form" style="width: 45px !important;">
		<c:forEach var="dit" begin="0" end="9" step="1">
			<option value="0${dit}">0${dit}</option>
		</c:forEach>
		<c:forEach var="dit" begin="10" end="23" step="1">
			<option value="${dit}">${dit}</option>
		</c:forEach>
		
		</select>시 
		<select name="startmin" class="config-combo-form" style="width: 45px !important;">
		<c:forEach var="dit" begin="0" end="9" step="1">
			<option value="0${dit}">0${dit}</option>
		</c:forEach>
		<c:forEach var="dit" begin="10" end="59" step="1">
			<option value="${dit}">${dit}</option>
		</c:forEach>
		</select>분 까지
	</div>

	<div id="config-status-des">퇴근 시간</div>
	<div id="config-status-value">
		<select name="endhour" class="config-combo-form" style="width: 45px !important;">
		<c:forEach var="dit" begin="0" end="9" step="1">
			<option value="0${dit}">0${dit}</option>
		</c:forEach>
		<c:forEach var="dit" begin="10" end="23" step="1">
			<option value="${dit}">${dit}</option>
		</c:forEach>
		
		</select>시 
		<select name="endmin" class="config-combo-form" style="width: 45px !important;">
		<c:forEach var="dit" begin="0" end="9" step="1">
			<option value="0${dit}">0${dit}</option>
		</c:forEach>
		<c:forEach var="dit" begin="10" end="59" step="1">
			<option value="${dit}">${dit}</option>
		</c:forEach>
		</select>분 부터
	</div>
	
	<input type="submit" class="writecomplete" value="정보 수정완료" style="float: right; width: 100px;">
	</form>
	<div id="config-status-title" style="margin-top: 40px;">
	그룹웨어 정보
	</div>
	
	<div id="config-status-des">시스템에 가입한 직원수</div>
	<div id="config-status-value">300 명</div>
	
	<div id="config-status-des">현재 생성된 부서수</div>
	<div id="config-status-value">23</div>
	
	<div id="config-status-des">현재 생성된 동아리수</div>
	<div id="config-status-value">18</div>
	
	<div id="config-status-des">현재 생성된 프로젝트수</div>
	<div id="config-status-value">21</div>
	
	<div id="config-status-des">총 보유 차량수</div>
	<div id="config-status-value">21</div>
	
	<div id="config-status-des">업로드된 파일수</div>
	<div id="config-status-value">3000</div>
</div>