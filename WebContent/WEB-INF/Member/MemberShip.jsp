<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	/*
	*	폼 name 주석 
	*	아이디=uid
	*	패스워드1=passwd1
	*	패스워드2=passwd2
	*	이름=realname
	*	성별=gender
	*	이메일=email
	*	핸드폰번호=phonenumber
	*	직책=position
	*	부서=department
	*	비밀번호 찾기용 질문=passwdq
	*	비밀번호 찾기용 대답=passwda
	*/
%>

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
<title><!-- 입력:제목들어갈곳  --></title>
<script>
function dupcheck() {

	var check = document.SubmitForm;

	if (check.uid.value == "") {
		check.uid.focus();
		alert("아이디를 입력해주세요. ");
		return false;
	}
	var strReg = /^[A-Za-z0-9]+$/;
	if(!strReg.test(check.uid.value)) {
		check.uid.focus();
		alert("아이디는 영문과 숫자만 가능합니다. ");
		return false;
	} else {
		var uid = check.uid.value;
		$.ajax({
			type: "POST",
			async:false,
			url: "${ctx}/Member/MemberShipProc.do?mode=ID_CHECK&value="+uid,
			success: function(msg) {
					alert(msg);
			}
		});
	}
	
}
</script>
</head>
<body>
	<div id="membership-wrapper">
		<div id="membership-center">
			<div id="curPath">
				<div id="Path">
					<img src="${ctx}/images/pathicon.gif"> <font color="#E74C3C"><b>그룹웨어 가입<b></font>
				</div>
			</div>
			<form name="SubmitForm" action="${ctx}/Member/MemberShipProc.do?mode=JOIN" method="POST" onsubmit="return formCheck()" enctype="multipart/form-data">
			
			<div id="MemberForm-wrapper">
			<div style="margin-bottom: 10px;"><font style="font-size: 12px; color: #E74C3C; font-weight: bold;">※모든 정보를 필히 기입해주세요. </font></div>
				<div id="subForm">
					<div id="ex">아이디</div>
					<div id="exSelect">
						<input class="input-form" style="width: 315px;" type="text" name="uid" value="아이디는 4자 이상으로 해주세요" onfocus="this.value='';return true;">
						<input class="backbtn" type="button" value="중복확인" onclick="dupcheck();" style="float: center;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">비밀번호</div>
					<div id="exSelect">
						<input class="input-form" type="password" name="passwd1" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">비밀번호 확인</div>
					<div id="exSelect">
						<input class="input-form" type="password" name="passwd2" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">이름(실명)</div>
					<div id="exSelect">
						<input class="input-form" type="text" name="realname" value="실명을 입력해주세요" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">사진</div>
					<div id="exSelect">
						<input type="file" class="file-form" name="fileimg" style="width: 420px;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">성 별</div>
					<div id="exSelect">
						<div style="height: 12px;">
							남자 - <input type="radio" name="gender" value="남" checked="check">&nbsp;&nbsp;&nbsp;&nbsp;여자
							- <input type="radio" name="gender" value="여">
						</div>
					</div>
				</div>
				<div id="subForm">
					<div id="ex">이 메일</div>
					<div id="exSelect">
						<input class="input-form" type="text" name="email" value="유요한 이메일을 입력해주세요" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">핸드폰 번호</div>
					<div id="exSelect">
						<input class="input-form" type="text" name="phonenumber" value="유요한 핸드폰 번호를 입력해주세요 '-' 는빼고 입력해주세요." onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">생년월일</div>
					<div id="exSelect">
						<input class="input-form" type="text" name="birth" value="ex)2014-05-23" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">직책</div>
					<div id="exSelect">
						<select class="select-form" name="position">
							<option value="none">직책을 선택해주세요</option>
								<c:forEach var="n" items="${positionList}">
								<option value="${n.posi_level}">${n.posi_name}</option>
								</c:forEach>
						</select>
					</div>
				</div>
				<div id="subForm">
					<div id="ex">소속 부서</div>
					<div id="exSelect">
						<select class="select-form" name="department">
							<c:if test="${fn:length(departmentList) < 1 }">
							<option value="none">사용 가능한 부서가 없습니다. </option>
							</c:if>
							<c:if test="${fn:length(departmentList) > 0 }">
							<c:forEach var="n" items="${departmentList}">
							<option value="${n.dep_no}">${n.dep_name}</option>
							</c:forEach>
							</c:if>
						</select>
					</div>
				</div>
				<div id="subForm">
					<div id="ex">비밀번호 찾기 질문</div>
					<div id="exSelect">
						<select class="select-form" name="passwdq">
							<option value="none">비밀번호 찾기 질문을 선택해주세요</option>
							<option value="Q01">가장 감명 깊게 본 영화는?</option>
							<option value="Q02">다시 태어나면 하고 싶은 것은?</option>
							<option value="Q03">기억에 남는 추억의 장소는?</option>
							<option value="Q04">자신의 보물 제1호는?</option>
						</select>
					</div>
				</div>
				<div id="subForm">
					<div id="exend">비밀번호 찾기 대답</div>
					<div id="exSelectend">
						<input class="input-form" type="text" name="passwda" value="패스워드 찾기용으로 이용됩니다. 신중하게 해주세요" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="bottomButton">
					<div class="backdiv">
						<input class="backbtn" type="button" value="뒤로 가기" onclick="javascript:history.go(-1)">
					</div>
					<div class="submitdiv">
						<input type="submit" class="writecomplete" value="작성 완료">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
	<div id="membership-right"></div>
	<div id="bottom">
		<br />
		<cat> <a href="#"> <b>© Copyright Sunchon Corp. rights
				reserved.</b>
		</a> </cat>
	</div>
</body>
</html>