<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="shortcut icon" href="${ctx}/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<title>Error 404</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body>
	<div align="center" style="padding-top: 150px;">
		<img src='${ctx}/images/laziel.com.gif'
			alt='Error 404'> <br>
	</div>
		<div id="bottom">
		<br />
		<cat>
			<a href="#">
				<b>© Copyright Sunchon Corp. rights reserved.</b>
			</a> 
		</cat>
	</div>
</body>
</html>