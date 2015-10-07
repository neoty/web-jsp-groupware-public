<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="${ctx}/js/pace.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/check.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/formstyle.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/MemberStyle.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/loading.css" />
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body style="background:#efefef;">
<div id="mem-modify-path">
	<img src="${ctx}/images/pathicon.gif"> 내 정보수정
</div>
<form name="SubmitForm" action="${ctx}/Member/MemberShipProc.do?mode=MODIFY" method="POST" onsubmit="return formCheck()" enctype="multipart/form-data">
<div id="mem-modify-frame">
	<div class="mem-modify-field">
		<div class="mem-modify-title">
			비밀번호 변경
		</div>
		<div class="mem-modify-input">
			<input type="password" class="mem-modify-text" name="passwd1">
		</div>
		<div style="clear:both; padding-top: 7px;">
			<hr>
		</div>
	</div>

	<div class="mem-modify-field">
		<div class="mem-modify-title">
			비밀번호 변경 확인
		</div>
		<div class="mem-modify-input">
			<input type="password" class="mem-modify-text" name="passwd2">
		</div>
		<div style="clear:both; padding-top: 7px;">
			<hr>
		</div>
	</div>

	<div class="mem-modify-field">
		<div class="mem-modify-title">
			회원 이미지
		</div>
		<div class="mem-modify-input">
			<input type="file" class="mem-file-input" name="fileimg">
			<input type="hidden" name="imgsrc" value="${meminfo.mb_img}">
		</div>
		<div style="clear:both; padding-top: 7px;">
			<hr>
		</div>
	</div>
	
	
	<div class="mem-modify-field">
		<div class="mem-modify-title">
			이메일
		</div>
		<div class="mem-modify-input">
			<input type="text" class="mem-modify-text" value="${meminfo.mb_email}" name="email">
		</div>
		<div style="clear:both; padding-top: 7px;">
			<hr>
		</div>
	</div>
	
	<div class="mem-modify-field">
		<div class="mem-modify-title">
			핸드폰번호
		</div>
		<div class="mem-modify-input">
			<input type="text" class="mem-modify-text" value="${meminfo.mb_tel}" name="phonenumber">
		</div>
		<div style="clear:both; padding-top: 7px;">
			<hr>
		</div>
	</div>
	
	<div class="mem-modify-field">
		<div class="mem-modify-title">
			직책
		</div>
		<div class="mem-modify-input">
			<select class="mem-modify-text" style="height:22px; margin-top: -2px; width: 229px !important;" name="position">
				<option value="${meminfo.mb_position_posi_level}">
					현재 직책 - ${meminfo.posi_name}
				</option>
				<c:forEach var="n" items="${positionList}">
				<option value="${n.posi_level}">${n.posi_name}</option>
				</c:forEach>
			</select>
		</div>
		<div style="clear:both; padding-top: 7px;">
			<hr>
		</div>
	</div>
	
	<div class="mem-modify-field">
		<div class="mem-modify-title">
			소속 부서
		</div>
		<div class="mem-modify-input">
			<select class="mem-modify-text" style="height:22px; margin-top: -2px; width: 229px !important;" name="department">
				<option value="${meminfo.mb_dep_no}">
					현재 부서 - ${meminfo.dep_name}
				</option>
				<c:forEach var="n" items="${departmentList}">
				<option value="${n.dep_no}">${n.dep_name}</option>
				</c:forEach>
			</select>
		</div>
		<div style="clear:both; padding-top: 7px;">
			<hr>
		</div>
	</div>
	
	
	<div class="mem-modify-field">
		<div class="mem-modify-title">
			비밀번호 찾기 질문
		</div>
		<div class="mem-modify-input">
			<select class="mem-modify-text" style="height:22px; margin-top: -2px; width: 229px !important;" name="passwdq">
				<c:choose>
					<c:when test="${meminfo.mb_password_q == 'Q01'}">
						<option value="Q01">현재질문 - 가장 감명 깊게 본 영화는?</option>
					</c:when>
					<c:when test="${meminfo.mb_password_q == 'Q02'}">
						<option value="Q02">현재질문 - 다시 태어난다면 하고 싶은 것은?</option>
					</c:when>
					<c:when test="${meminfo.mb_password_q == 'Q03'}">
						<option value="Q03">현재질문 - 기억에 남는 추억의 장소는?</option>
					</c:when>
					<c:when test="${meminfo.mb_password_q == 'Q04'}">
						<option value="Q04">현재질문 - 자신의 보물 제1호는?</option>
					</c:when>
				</c:choose>
				<option value="Q01">가장 감명 깊게 본 영화는?</option>
				<option value="Q02">다시 태어나면 하고 싶은 것은?</option>
				<option value="Q03">기억에 남는 추억의 장소는?</option>
				<option value="Q04">자신의 보물 제1호는?</option>
			</select>
		</div>
		<div style="clear:both; padding-top: 7px;">
			<hr>
		</div>
	</div>
	

	<div class="mem-modify-field">
		<div class="mem-modify-title">
			비밀번호 찾기 대답
		</div>
		<div class="mem-modify-input">
			<input type="text" class="mem-modify-text" value="${meminfo.mb_password_a}" name="passwda">
		</div>
		<div style="clear:both; padding-top: 7px;">
			<hr>
			<input class="writecomplete" style="float: right;" type="submit" value="수정 완료" onclick="return confirm('회원 정보를 수정하시겠습니까?');">
		</div>
	</div>
		
</div>
</form>
</body>
</html>