<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/people.css" />

<div id="people-main">

	<div id="people-status-title" style="border-bottom: none; padding-left: 125px;">
		직책별 보기
			<div style="float: right; padding-right: 5px;">
			<select name="to" id="to" onchange="toMethod(this.value);" class="people-combo-form" style="width: 130px;">	
					<c:if test="${param.array == null}">
						<option value="" selected>전체 보기</option>
					</c:if>
					<c:if test="${param.array != null}">
						<c:if test="${param.array == ''}">
							<option value="" selected>직책보기 - 전체보기</option>
						</c:if>
						<c:if test="${param.array != ''}">
							<option value="" selected>부서보기 - ${posiname}</option>
						</c:if>
						<option value="">전체보기</option>
					</c:if>
					
					<c:forEach var="n" items="${posi_list}">
						<option value="${n.posi_level}-${n.posi_name}">${n.posi_name}</option>
					</c:forEach>
			</select>		
			</div>
	</div>
	<div id="people-community-title">
		<c:if test="${cnt > 0}">
			<span class="people-cnt">${cnt}</span>&nbsp;명 검색
		</c:if>
	</div>
	<div id="people-body">
									<!-- 아랫 부분에 회원 넣기 730/4 로 자동으로 x by 4 로 들어감 -->
		<c:forEach var="n" items="${memberList}">
			<div id="people-content">
				<div class="people-img">
				<c:choose>
					<c:when test="${n.mb_img == '' || empty n.mb_img || n.mb_img == null}">
						<img src="${ctx}/images/noimg.png" width="150px" height="180px">
					</c:when>
					<c:otherwise>
						<img src="${ctx}/MemberImg/${n.mb_img}" width="150px" height="180px">
					</c:otherwise>
				</c:choose>
				</div>
				<div class="people-dep">${n.dep_name}</div>
				<div class="people-phone">${n.mb_tel}</div>
				<div class="people-email">${n.mb_email}</div>
				<div class="people-nameandposi"><a href="${ctx}/Chat/Write.chat?no=${n.mb_no}" style="color: #2B3A42;"><b>${n.posi_name} ${n.mb_name}</b></a></div>
			</div>
		</c:forEach>
		<c:if test="${empty memberList}">
			<div style="text-align: center; padding: 15px; font-weight: bold;">
				해당되는 회원이 없습니다.
			</div>
		</c:if>
	</div>
		<div id="paging">
			<div class="paginate_simple">
						<c:if test="${page > 11}">
							<a href="${ctx}/People/position.people?type=${param.type}&code=${param.code}&page=${page-10}" class="direction"> ‹ <span>Prev</span></a>
						</c:if>
						
						<c:forEach var="i" step="1" begin="0" end="9">
							<c:if test="${start+i <= end}">
								<c:if test="${start+i == page}">
									<strong>${start+i}</strong>
								</c:if>
								<c:if test="${start+i != page}">
									<a href="${ctx}/People/position.people?type=${param.type}&code=${param.code}&page=${start+i}">${start+i}</a>
								</c:if>
							</c:if>
							
						</c:forEach>
						<c:if test="${start+10 < end}">
							<a href="${ctx}/People/position.people?type=${param.type}&code=${param.code}&page=${start+10}" class="direction"><span>Next</span> › </a>
						</c:if>
				</div>
		</div>
</div>

<script>
	function toMethod(value) {
		var get = value;
		value = encodeURI(value);
		var url = "${ctx}/People/position.people?array="+value;
		location.href =  url;
	}
</script>

