<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/MemberStyle.css" />
<script type="text/javascript">
function formCheck() 
{
	var check = document.form;
	if (check.id.value == "" || check.id.value == "아이디를 입력해주세요. ")
	{
		alert('아이디를 입력해주세요. ');
		return false;
	}
	
	if (check.queslist.value == "" || check.queslist.value == "none")
	{
		alert('질문을 선택해주세요.  ');
		return false;
	}
	
	if (check.ans.value == "" || check.ans.value == "질문에 대한 답변을 적어주세요. ")
	{
		alert('답변을 적어주세요.  ');
		return false;
	} else {
		return true;
	}
}
</script>
</head>
<body>
<div id="findpasswd">
	<div style="text-align: center;">
		<form name="form" action="${ctx}/Member/FindPassword.do" method="post" onsubmit="return formCheck()">
			<input class="normal-form" type="text" value="아이디를 입력해주세요. " name="id"
			style="width: 240px; height: 30px;" onfocus="this.value='';return true;">
		
			<select name="queslist" id="queslist">
		    <option value="none">비밀번호 찾기 질문을 선택해주세요</option>
  	 		<option value="Q01">가장 감명 깊게 본 영화는?</option>
  		 	<option value="Q02">다시 태어나면 하고 싶은 것은?</option>
  		 	<option value="Q03">기억에 남는 추억의 장소는?</option>
 	 	 	<option value="Q04">자신의 보물 제1호는?</option>
  			</select>
  		
  			<input class="normal-form" type="text" value="질문에 대한 답변을 적어주세요. " name="ans"
			style="width: 240px; height: 30px;" onfocus="this.value='';return true;">
		
			<input class="writecomplete" style="width: 90px; height: 30px;" type="submit" value="비밀번호 찾기">
		</form>	
	</div>
</div>
</body>
</html>