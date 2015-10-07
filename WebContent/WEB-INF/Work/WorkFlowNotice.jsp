<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${ctx}/css/Work.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/Community.css" />
<div id="work-main">
	 
	<!-- 결재 리스트 -->
	<div id="work-status-title">
	결재 완료 항목
	</div>

	<div id="work-sub-title">
		<ul class="work-list-subject">
			<li style="width: 160px;">
				결재 번호
			</li>
			<li class="work-subject-date-list" style="width: 380px; text-align:center;">
				결재 제목
			</li>
			<li style="width: 50px;">
				문서
			</li>
			<li style="width: 50px;">
				별첨
			</li>
			<li style="width: 89px;">
				최종 승인자
			</li>
		</ul>
	</div>
	
	
	<c:if test="${empty doc_list}">
		<div id="work-list-blank" style="width: 100%; border-bottom: 1px solid #bdbdbd; height: 20px; line-height: 170%; padding: 5px 0px; text-align: center;">
			결재된 문서가 없습니다.
		</div>
	</c:if>
	<!-- 반복 부분 -->
	<c:if test="${!empty doc_list}">
	<c:forEach var="n" items="${doc_list}">
		<ul class="work-list-workflow">
			<li class="work-flow-progress" style="width:160px;">
				<b>${n.wf_doc_no}</b>
			</li>
			<li class="work-subject-date-list" style="width: 370px !important;">
				${n.wf_subject}
			</li>
			<li class="work-flow-att" style="padding: 2px 0px 8px 0px;">
				<a href="${ctx}/Download.jsp?culumn=first&file=${n.encDocName}">
					<c:choose>
						<c:when test="${n.file_icon.equals('pdf')}">
							<img src="${ctx}/images/icons/pdfico.png" width="25" height="25">
						</c:when>
						<c:when test="${n.file_icon.equals('hwp')}">
							<img src="${ctx}/images/icons/hwpico.png" width="25" height="25">
						</c:when>
						<c:when test="${n.file_icon.equals('odt')}">
							<img src="${ctx}/images/icons/odtico.png" width="25" height="25">
						</c:when>
						<c:when test="${n.file_icon.equals('doc')}">
							<img src="${ctx}/images/icons/docico.png" width="25" height="25">
						</c:when>
						<c:otherwise>
							<img src="${ctx}/images/icons/attachico.png" width="25" height="25">
						</c:otherwise>
					</c:choose>
				</a>
			</li>
			<li class="work-flow-att" style="padding: 2px 0px 8px 0px;">
				<c:choose>
					<c:when test="${n.encFileName == null || n.encFileName.equals('') || n.encFileName.equals('null')}">
						
					</c:when>
					<c:otherwise>
						<a href="${ctx}/Download.jsp?culumn=second&file=${n.encFileName}" style="text-decoration:none;">
							<img src="${ctx}/images/icons/attachico.png" width="25" height="25">
						</a>
					</c:otherwise>
				</c:choose>
			</li>
			<li class="work-flow-date" style="width:89px; font-size:12px !important; font-family: 'Malgun Gothic', '맑은 고딕' !important;">
			<c:choose>
				<c:when test="${n.lastApprove_mb_img == '' || empty n.lastApprove_mb_img || n.lastApprove_mb_img != null}">
					<a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/images/noimg.png">${n.lastApprove_mb_posi_name} ${n.lastApprove_mb_name}</a>
				</c:when>
				<c:otherwise>
					<a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/MemberImg/${n.lastApprove_mb_img}">${n.lastApprove_mb_posi_name} ${n.lastApprove_mb_name}</a>
				</c:otherwise>
			</c:choose>
			</li>
		</ul>
	</c:forEach>
	</c:if>
	<!-- 페이징 -->
	<div id="co-bottom">
		<div id="co-bottom-center" style="padding-top: 20px;">
				<div class="paginate_simple">
				<c:if test="${page >= 11}">
					<a href="${ctx}/Work/WorkFlowNotice.work?page=${page-10}" class="direction"> ‹ <span>Prev</span></a>
				</c:if>
			
				<c:forEach var="i" step="1" begin="0" end="9">
					<c:if test="${start+i <= end}">
						<c:if test="${start+i == page}">
							<strong>${start+i}</strong>
						</c:if>
						<c:if test="${start+i != page}">
							<a href="${ctx}/Work/WorkFlowNotice.work?page=${start+i}">${start+i}</a>
						</c:if>
					</c:if>
				</c:forEach>
				<c:if test="${start+10 < end}">
					<a href="${ctx}/Work/WorkFlowNotice.work?page=${start+10}" class="direction"><span>Next</span> › </a>
				</c:if>
				</div>
		</div>
	</div>
	
</div>