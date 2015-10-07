<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="HOME_NAVIGATION" value="${NAVIGATION_VALUE}" /> <!-- 나중에 가져와서 바꿀것 -->

<c:if test="${HOME_NAVIGATION=='MAIN'}">
	<c:set var="HOME_NAVIGATION_POSITION" value="12"/>
</c:if>
<c:if test="${HOME_NAVIGATION=='COMMUNITY'}">
	<c:set var="HOME_NAVIGATION_POSITION" value="90"/>
</c:if>
<c:if test="${HOME_NAVIGATION=='SCHEDULE'}">
	<c:set var="HOME_NAVIGATION_POSITION" value="162"/>
</c:if>
<c:if test="${HOME_NAVIGATION=='WORK'}">
	<c:set var="HOME_NAVIGATION_POSITION" value="236"/>
</c:if>
<c:if test="${HOME_NAVIGATION=='CHAT'}">
	<c:set var="HOME_NAVIGATION_POSITION" value="316"/>
</c:if>
<c:if test="${HOME_NAVIGATION=='PEOPLE'}">
	<c:set var="HOME_NAVIGATION_POSITION" value="384"/>
</c:if>
<c:if test="${HOME_NAVIGATION=='CONFIG'}">
	<c:set var="HOME_NAVIGATION_POSITION" value="455"/>
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="${ctx}/js/pace.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="shortcut icon" href="${ctx}/images/favicon.ico">
<link rel="stylesheet" type="text/css" href="${ctx}/css/loading.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/mainform.css" />
<script src="${ctx}/js/check.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="${ctx}/js/main.js"></script>
<script>
$(function() {
    $( document ).tooltip({
    	position: {
        my: "center bottom-20",
        at: "center top",
        using: function( position, feedback ) {
          $( this ).css( position );
          $( "<div>" )
            .addClass( "arrow" )
            .addClass( feedback.vertical )
            .addClass( feedback.horizontal )
            .appendTo( this );
        }
      }
    });
  });
</script>
<style type="text/css">

/* 메뉴 내려 오기 */
@keyframes myfirst
{
0%   { left:0px; top:-1000px;}
100% { left:0px; top:${HOME_NAVIGATION_POSITION}px;}
} 
@-webkit-keyframes myfirst
{
0%   { left:0px; top:-1000px;}
100% { left:0px; top:${HOME_NAVIGATION_POSITION}px;}
}
</style>
<title>${sessionScope.MB_NAME}</title>
</head>
<body>
	<div id="wrapper">
		<div id="fixMenu">
			<div id="fixContent">
				<ul>
					<li><a href="${ctx}/Main/index.main" class="home" title="홈으로가기"></a></li>
					<li><a href="${ctx}/Community/index.community?type=PUBLIC&code=1" class=community title="커뮤니티"></a></li>
					<li><a href="${ctx}/Schedule/index.schedule" class="calendar" title="일정 관리"></a></li>
					<li><a href="${ctx}/Work/WorkTime.work" class="gun" title="근무 관리"></a></li>
					<li><a href="${ctx}/Chat/index.chat" class="message" title="메시지 및 메신저"></a></li>
					<li><a href="${ctx}/People/index.people" class="jo" title="조직도보기"></a></li>
					<c:if test="${sessionScope.MB_WORK_TYPE == '0'}">
					<li><a href="${ctx}/Config/index.config" class="admin" title="관리자탭"></a></li>
					</c:if>
				</ul>
			</div>
		</div>

		<div id="subMenu">
			<div id="subContent">
				<ul>
					<tiles:insertAttribute name="commonmenu"/>
				</ul>
			</div>
		</div>
		<div id="content">
			<div id="curPath">
				<div id="Path">
					<tiles:insertAttribute name="pagepath"/>
				</div>
			</div>
			<div id="content1">
				<tiles:insertAttribute name="body"/>
			</div>
		</div>
	<div id="footer">	
		<div id="right">
			<div id="d_inform">
				<ul>
					<li style="text-decoration: none;"><a href="javascript:meminfoedit();"
						class="settingsBtn" title="정보수정" style="text-decoration: none;"></a>
					</li>
					<li style="text-decoration: none;"><a href="${ctx}/Member/Logout.do"
						class="logoutBtn" title="로그 아웃" style="text-decoration: none;"></a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div id="bottom">
		<br />
		<cat>
			<a href="#">
				<b>© Copyright Sunchon Corp. rights reserved.</b>
			</a> 
		</cat>
	</div>
	</div>
</body>
<script type="text/javascript">

function meminfoedit(){
	var popUrl = "${ctx}/Member/MemberInfoModify.member";	//팝업창에 출력될 페이지 URL
	var popOption = "width=370, height=380px, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
	}
</script>
</html>