<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/Community.css" />
<c:set var="ses_mb_no" value="${sessionScope.MB_NO}"/>
<!-- 
	필요 변수 
	type = 

 -->
<div id="co-main">
	<c:if test="${param.type == 'PROJECT' || param.type == 'CIRCLES'}">
		<c:if test="${requestScope.adminPart == 'ADMIN' && !empty requestScope.adminPart}">
			<div id="co-top-admin" style="float: left !important; color: #E74C3C;">
				<form name="manageForm" action="${ctx}/Community/Manage.community" method="post">
					<input type="hidden" name="type" value="${param.type}">
					<input type="hidden" name="code" value="${param.code}">
					<a href="javascript:document.manageForm.submit();"><b>Admin</b></a>
				</form>
			</div>
		</c:if>
	</c:if>
	<div id="co-top-total">
	Total : ${total}
	</div>
	<div id="co-middle">
		<div id="co-middle-title">
			<div id="co-middle-number">번호</div>
			<div id="co-middle-subject">제목</div>
			<div id="co-middle-writer">글쓴이</div>
			<div id="co-middle-date" style="float: left;">등록일</div>
			<div id="co-middle-count">조회수</div>
		</div>
		<!-- 공지 사항 리스트 -->
		<c:forEach var="a" items="${noticeList}">
		<a href="${ctx}/Community/View.community?type=${param.type}&code=${param.code}&bc_no=${a.bc_no}"><div id="co-middle-content" style="background: #F6F6F6 !important; font-weight: bold !important; border-bottom: 1px solid #E74C3C;">
			<div id="co-middle-number"><img src="${ctx}/images/icons/icon_notice.gif"></div>
			<div id="co-middle-subject" style="color: #E74C3C !important;">${a.bc_subject} 
			<c:if test="${a.bc_reply_count != '0'}">
			<font style="font-size: 8px; font-family: verdana; font-weight: bold;">(${a.bc_reply_count})</font>
			</c:if>
			</div>
		</a>
			<a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/MemberImg/${a.mb_img}"><div id="co-middle-writer">${a.posi_name} ${a.mb_name}</div></a>
			<div id="co-middle-date" style="color: #E74C3C !important; float: left;">${a.bc_date}</div>
			<div id="co-middle-count" style="color: #E74C3C !important;">${a.bc_read_count}</div>
		</div>
		</c:forEach>
		<!--  일반 게시물 리스트 -->
		<c:forEach var="n" items="${List}">
		<div id="co-middle-content">
		<a href="${ctx}/Community/View.community?type=${param.type}&code=${param.code}&page=${param.page}&bc_no=${n.bc_no}">
			<div id="co-middle-number">${n.bc_no}</div>
			<div id="co-middle-subject">${n.bc_subject} 
			<c:if test="${n.bc_reply_count != '0'}">
			<font style="font-size: 8px; font-family: verdana; font-weight: bold;">(${n.bc_reply_count})</font>
			</c:if>
			</div>
		</a>
			
			<c:if test="${n.mb_img == '' || empty n.mb_img}">
				<a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/images/noimg.png"><div id="co-middle-writer">${n.posi_name} ${n.mb_name}</div></a>
			</c:if>
			
			<c:if test="${n.mb_img != '' && !empty n.mb_img && n.mb_img != null}">
				<a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/MemberImg/${n.mb_img}"><div id="co-middle-writer">${n.posi_name} ${n.mb_name}</div></a>
			</c:if>
			
			<div id="co-middle-date" style="float: left;">${n.bc_date}</div>
			<div id="co-middle-count">${n.bc_read_count}</div>
		</div>
		</c:forEach>
		
	</div>
	<div id="co-bottom">
		<div id="co-bottom-center">

				<div class="paginate_simple">
						<c:if test="${page >= 11}">
							<a href="${ctx}/Community/index.community?type=${param.type}&code=${param.code}&page=${page-10}" class="direction"> ‹ <span>Prev</span></a>
						</c:if>
						
						<c:forEach var="i" step="1" begin="0" end="9">
							<c:if test="${start+i <= end}">
								<c:if test="${start+i == page}">
									<strong>${start+i}</strong>
								</c:if>
								<c:if test="${start+i != page}">
									<a href="${ctx}/Community/index.community?type=${param.type}&code=${param.code}&page=${start+i}">${start+i}</a>
								</c:if>
							</c:if>
							
						</c:forEach>
						<c:if test="${start+10 < end}">
							<a href="${ctx}/Community/index.community?type=${param.type}&code=${param.code}&page=${start+10}" class="direction"><span>Next</span> › </a>
						</c:if>
				</div>
		</div>
	</div>
	
	<div id="co-bottom-menu" style="float: left;">
		<div id="co-search-form" style="float: left;">
			<form action="${ctx}/Community/index.community" method="post">
			<input type="hidden" name="type" value="${param.type}">
			<input type="hidden" name="code" value="${param.code}">
			<input type="hidden" name="mode" value="search">
			<input name="search_value" class="search-form" type="text" value="내용+제목" onfocus="this.value=''"/>
			<input type="submit" class="writecomplete" value="검색" style="width: 40px !important; height: 28px !important;">
			</form>
		</div>
		<form action="${ctx}/Community/Write.community" method="post">
		<input type="hidden" name="type" value="${param.type}">
		<input type="hidden" name="code" value="${param.code}">
		<input type="hidden" name="mode" value="write">
		<input type="submit" class="writecomplete" value="글 작성" style="float: right;">
		</form>
	</div>
</div>
