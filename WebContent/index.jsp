<%@page import="groupware.dao.MemberDao"%>
<%@page import="groupware.dao.TestDao"%>
<%@page import="groupware.commons.Controller"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if (session.getAttribute("MB_NO") != null) {
		System.out.println("로그인 되어있음");
	}

%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Groupware System - test</title>
<link rel="shortcut icon" href="${ctx}/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${ctx}/Login/css/style.css" />
<script src="${ctx}/Login/js/modernizr.custom.63321.js"></script>
<script src="${ctx}/Login/js/check.js"></script>
<!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
</head>
<body>
	<div class="container"
		style="position: fixed; top: 50%; margin-top: -250px;">
		<div
			style="text-align: center; color: #8c8c8c; position: relative; text-shadow: 1px 1px #ffffff; font-size: 20px">
			<b></b>
		</div>
		<section class="main">
			<form class="form-1" name="login" action="${ctx}/Member/Login.do"
				method="POST">

				<p class="field">
					<input type="text" name="MB_ID" placeholder="아이디를 입력해주세요">
					<i class="icon-user icon-large"></i>
				</p>
				<p class="field">
					<input type="password" name="MB_PASSWORD" placeholder="비밀번호를 입력해주세요">
					<i class="icon-lock icon-large"></i>
				</p>
				<p class="submit">
					<button type="submit" name="submit" onclick="return formCheck();">
						<i class="icon-arrow-right icon-large"></i>
					</button>
				</p>
			</form>
		</section>
		<div id="dcenter" style="height: 200px;">
			<a href="${ctx}/Member/MemberShip.member"><div id="dbutton"
					style="width: 260px; margin-bottom: 10px;">그룹웨어 시스템 가입하기</div></a> <a
				href="${ctx}/Member/FindPassword.member" onclick="window.open(this.href,'${ctx}/Member/MemberFind.member', 'width=250,height=155,resizable=no,scrollbars=no,status=no,top=200px;'); return false;" onkeypress="this.oncilck()"><div id="dbutton" style="width: 260px;">아이디
					및 비밀번호 찾기</div></a>
		</div>
	</div>
	<div id="bottom" style="position: fixed; bottom: 0;">
		<br />
		<cat> <a href="#"> <b>© Copyright Sunchon Corp. rights
				reserved.</b>
		</a> </cat>
	</div>
</body>
</html>