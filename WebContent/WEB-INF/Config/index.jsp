<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/config.css" />
<div id="config-main">
	<div id="config-status-title">
	관리자 정보 및 그룹웨어 수정
	</div>
	
	<form action="${ctx}/Config/ServerModify.do" method="post" name="serverform">
	<div id="config-status-des">Context Name</div>
	<div id="config-status-value">${CONTEXT_NAME}</div>

	<div id="config-status-des">Server Ip</div>
	<div id="config-status-value">${SERVER_IP}</div>
	
	<div id="config-status-des">그룹웨어 생성일자</div>
	<div id="config-status-value">${CREATE_TIME}</div>
	
	<div id="config-status-des">관리자 아이디</div>
	<div id="config-status-value">${ADMIN_ID}</div>
	
	
	
	<div id="config-status-des">관리자 이메일</div>
	<div id="config-status-value">
		<input type="text" class="config-input-form" 
		name="email" value="${EMAIL}" style="width: 300px;">
	</div>
	
	<div id="config-status-des">관리자 전화번호</div>
	<div id="config-status-value">
		<input type="text" class="config-input-form" 
		name="phone" value="${PHONE}" style="width: 300px;">
	</div>
	
	<div id="config-status-des">그룹웨어 정지 여부</div>
	<div id="config-status-value">
		<select name="stop" class="config-combo-form" style="width: 300px;">
		<c:if test="${GROUPWARE_STOP == '0'}">
			<option value="0" selected>그룹웨어 사용</option>
			<option value="1">그룹웨어 정지</option>
		</c:if>

		<c:if test="${GROUPWARE_STOP == '1'}">
			<option value="0">그룹웨어 사용</option>
			<option value="1" selected>그룹웨어 정지</option>
		</c:if>
		</select>
	</div>
	
	<div id="config-status-des">그룹웨어 정지 타이틀</div>
	<div id="config-status-value">
		<input type="text" class="config-input-form" 
		name="stop-title" value="${GROUPWARE_STOP_TITLE}" style="width: 300px !important;">
	</div>
	
	<div id="config-status-des">출퇴근 허용 IP(미 사용시 공백)</div>
	<div id="config-status-value">
		<input type="text" class="config-input-form" 
		name="allowip1" value="${GRANT_IP1}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();"> .
		<input type="text" class="config-input-form" 
		name="allowip2" value="${GRANT_IP2}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();"> .
		<input type="text" class="config-input-form" 
		name="allowip3" value="${GRANT_IP3}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();"> .
		<input type="text" class="config-input-form" 
		name="allowip4" value="${GRANT_IP4}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();">
	</div>
	
	<div id="config-status-des">출퇴근 허용 IP 대역(미 사용시 공백)</div>
	<div id="config-status-value">
		<input type="text" class="config-input-form" 
		name="bandip1" value="${BAND_IP1}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();"> .
		<input type="text" class="config-input-form" 
		name="bandip2" value="${BAND_IP2}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();"> .
		<input type="text" class="config-input-form" 
		name="bandip3" value="${BAND_IP3}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();"> .
		<input type="text" class="config-input-form" 
		name="bandip4" value="${BAND_IP4}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();"> - 
				<input type="text" class="config-input-form" 
		name="bandip5" value="${BAND_IP5}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();"> .
		<input type="text" class="config-input-form" 
		name="bandip6" value="${BAND_IP6}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();"> .
		<input type="text" class="config-input-form" 
		name="bandip7" value="${BAND_IP7}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();"> .
		<input type="text" class="config-input-form" 
		name="bandip8" value="${BAND_IP8}" style="width: 30px !important;" maxlength="3" onchange="javascript:valueCheck();">
	</div>
	
	<div id="config-status-des">그룹웨어 가입 기능</div>
	<div id="config-status-value">
		<select name="membership" class="config-combo-form" style="width: 300px;">
		<c:if test="${GROUPWARE_MEMBERSHIP == '1'}">
			<option value="1" selected>가입 가능</option>
			<option value="0">가입 불가능</option>
		</c:if>
		<c:if test="${GROUPWARE_MEMBERSHIP == '0'}">
			<option value="1">가입 가능</option>
			<option value="0" selected>가입 불가능</option>
		</c:if>
	
		</select>
	</div>
	
	<div id="config-status-des">출근 시간</div>
	<div id="config-status-value">
		<select name="starthour" class="config-combo-form" style="width: 50px !important;">
			<c:if test="${S_H_TIME > 9}">
				<option value="${S_H_TIME}">${S_H_TIME}</option>
			</c:if>
			<c:if test="${S_H_TIME < 10}">
				<option value="${S_H_TIME}">0${S_H_TIME}</option>
			</c:if>
		
			<c:forEach var="dit" begin="1" end="9" step="1">
				<option value="${dit}">0${dit}</option>
			</c:forEach>
			<c:forEach var="dit" begin="10" end="24" step="1">
				<option value="${dit}">${dit}</option>
			</c:forEach>
		
		</select>시 
		<select name="startmin" class="config-combo-form" style="width: 50px !important;">
			<c:if test="${S_M_TIME > 9}">
				<option value="${S_M_TIME}">${S_M_TIME}</option>
			</c:if>
			<c:if test="${S_M_TIME == '00'}">
				<option value="${S_M_TIME}">${S_M_TIME}</option>
			</c:if>
			<c:if test="${S_M_TIME < 10 && S_M_TIME > 0}">
				<option value="0${S_M_TIME}">0${S_M_TIME}</option>
			</c:if>
			
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
		<select name="endhour" class="config-combo-form" style="width: 50px !important;">
			<c:if test="${E_H_TIME > 9}">
				<option value="${E_H_TIME}">${E_H_TIME}</option>
			</c:if>
			<c:if test="${E_H_TIME < 10}">
				<option value="${E_H_TIME}">0${E_H_TIME}</option>
			</c:if>
			
			<c:forEach var="dit" begin="1" end="9" step="1">
				<option value="${dit}">0${dit}</option>
			</c:forEach>
			<c:forEach var="dit" begin="10" end="24" step="1">
				<option value="${dit}">${dit}</option>
			</c:forEach>
			
		</select>시 
		<select name="endmin" class="config-combo-form" style="width: 50px !important;">
			<c:if test="${E_M_TIME > 9}">
				<option value="${E_M_TIME}">${E_M_TIME}</option>
			</c:if>
			<c:if test="${E_M_TIME == '00'}">
				<option value="${E_M_TIME}">${E_M_TIME}</option>
			</c:if>
			<c:if test="${E_M_TIME < 10 && E_M_TIME > 0}">
				<option value="0${E_M_TIME}">0${E_M_TIME}</option>
			</c:if>
			
			<c:forEach var="dit" begin="0" end="9" step="1">
				<option value="0${dit}">0${dit}</option>
			</c:forEach>
			<c:forEach var="dit" begin="10" end="59" step="1">
				<option value="${dit}">${dit}</option>
			</c:forEach>
		</select>분 부터
	</div>
	
	<input type="submit" class="writecomplete" value="정보 수정완료" style="float: right; width: 100px;" onclick="return confirm('수정하시겠습니까?');">
	</form>
	<div id="config-status-title" style="margin-top: 40px;">
	그룹웨어 정보
	</div>
	
	<div id="config-status-des">시스템에 가입한 직원수</div>
	<div id="config-status-value">${MEMBER_COUNT} 명</div>
	
	<div id="config-status-des">현재 생성된 부서수</div>
	<div id="config-status-value">${DEPARTMENT_COUNT} 개</div>
	
	<div id="config-status-des">현재 생성된 동아리수</div>
	<div id="config-status-value">${CIRCLES_COUNT} 개</div>
	
	<div id="config-status-des">현재 생성된 프로젝트수</div>
	<div id="config-status-value">${PROJECT_COUNT} 개</div>
	
	<div id="config-status-des">총 보유 차량수</div>
	<div id="config-status-value">${CAR_COUNT} 개</div>
	
	<div id="config-status-des">업로드된 파일수</div>
	<div id="config-status-value">${FILE_COUNT} 개</div>
</div>

<script>
	function valueCheck() {
		var obj = event.srcElement; 
		var obj_str = obj.value; 
		var num_regx=/^[0-9]*$/;
		
		if(!num_regx.test(obj_str)) {
			obj.value = '';
			alert('숫자만 입력 가능합니다. ');
			return false;
		}
		
		if(obj_str > 255 || obj_str < 0) {
			obj.value = '';
			alert('0~255 의 값만 가능합니다. ');
			return false;
		}
		
			var checkvalue = document.serverform;
		if(obj.name == 'allowip1' || obj.name == 'allowip2' || obj.name == 'allowip3' || obj.name == 'allowip4') {
			checkvalue.bandip1.value = '';
			checkvalue.bandip2.value = '';
			checkvalue.bandip3.value = '';
			checkvalue.bandip4.value = '';
			checkvalue.bandip5.value = '';
			checkvalue.bandip6.value = '';
			checkvalue.bandip7.value = '';
			checkvalue.bandip8.value = '';
			return true;
		}
		
		if(obj.name == 'bandip1' || obj.name == 'bandip2' || obj.name == 'bandip3' || obj.name == 'bandip4' || obj.name == 'bandip5' ||
				obj.name == 'bandip6' || obj.name == 'bandip7' || obj.name == 'bandip8') {
			checkvalue.allowip1.value = '';
			checkvalue.allowip2.value = '';
			checkvalue.allowip3.value = '';
			checkvalue.allowip4.value = '';
			return true;
		}
	}
</script>