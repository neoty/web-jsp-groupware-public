<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<img src="${ctx}/images/pathicon.gif"> 메시지 > ${DESC_PATH1} > 

<c:if test="${!empty DESC_PATH2}">
<font color="#E74C3C"><b>${DESC_PATH2}</b></font>
</c:if>