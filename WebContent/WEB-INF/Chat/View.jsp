<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="view_info" value="${VIEW_INFO}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/chat.css" />


<div id="me-container">
	<div id="me-header">
		<div id="me-view-header" class="me-title-header">
			쪽지 - 보기
		</div>
	</div>
	
	<form action="${ctx}/Chat/Write.chat" method="post">
		<div id="me-content">
			<div id="me-view-form">
				<div id="me-view-form-subject" class="me-form-subject">제 목</div>
				<div id="me-view-form-input" class="me-form-input">
					${view_info.mes_subject}
				</div>
				<div id="me-view-form-subject" class="me-form-subject">보낸 사람</div>
				<div id="me-view-form-input" class="me-form-input">
					${view_info.mes_post_mb_posi_name} ${view_info.mes_post_mb_name}
				</div>
				<div id="me-view-form-subject" class="me-form-subject">받는 사람</div>
				<div id="me-view-form-input" class="me-form-input">
					${view_info.mes_get_mb_posi_name} ${view_info.mes_get_mb_name}
				</div>
				<div id="me-view-form-subject" class="me-form-subject">첨부 파일</div>
				<div id="me-view-form-input" class="me-form-input">
					<c:if test="${empty FILE_INFO}">
						첨부 파일 없음
					</c:if>
					<c:if test="${!empty FILE_INFO}">
						<a href="${ctx}/Download.jsp?culumn=first&file=${FILE_INFO.mes_file_enc_name}">[ ${FILE_INFO.mes_file_real_name} ]</a>
					</c:if>
				</div>
				<div id="me-view-form-cont-view" class="me-form-input">
					${view_info.mes_content}
				</div>
			</div>
		</div>
		
		<div id="me-footer">
			<div id="me-footer-button">
				<input type="button" value="뒤로가기" class="backbtn" onclick="javascript:history.go(-1)"/>
				<input type="hidden" value="${view_info.mes_post_mb_no}" name="mes_post_mb_no" />
				<input type="hidden" value="${view_info.mes_post_mb_posi_name}" name="mes_post_mb_posi_name" />
				<input type="hidden" value="${view_info.mes_post_mb_name}" name="mes_post_mb_name"/>
				<input type="hidden" value="${view_info.mes_get_mb_no}" name="mes_get_mb_no" />
				<input type="hidden" value="${view_info.mes_get_mb_posi_name}" name="mes_get_mb_posi_name" />
				<input type="hidden" value="${view_info.mes_get_mb_name}" name="mes_get_mb_name"/>
				<input type="hidden" value="answer" name="answer_btn"/>
				<input type="submit" value="답장하기" class="writecomplete" />
			</div>
		</div>
	</form>
</div>