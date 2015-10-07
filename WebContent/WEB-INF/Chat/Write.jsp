<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="mb_no" value="${sessionScope.MB_NO}" />
<c:set var="dep_list" value="${DEP_LIST}" />
<c:set var="mb_list" value="${MB_LIST}" />
<c:if test="${!empty RECEIVER}">
	<c:if test="${RECEIVER.mes_post_mb_no != sessionScope.MB_NO}">
		<c:set var="receiver" value="${RECEIVER.mes_post_mb_posi_name} ${RECEIVER.mes_post_mb_name}"/>
		<c:set var="addressNo" value="${RECEIVER.mes_post_mb_no}" />
	</c:if>
	<c:if test="${RECEIVER.mes_get_mb_no != sessionScope.MB_NO}">
		<c:set var="receiver" value="${RECEIVER.mes_get_mb_posi_name} ${RECEIVER.mes_get_mb_name}"/>
		<c:set var="addressNo" value="${RECEIVER.mes_get_mb_no}" />
	</c:if>
</c:if>
<c:if test="${empty RECEIVER}">
	<c:set var="receiver" value=""/>
	<c:set var="addressNo" value="" />
</c:if>

<link rel="stylesheet" type="text/css" href="${ctx}/css/chat.css" />

<div id="me-container">
	<div id="me-header">
		<div id="me-write-header" class="me-title-header">
			쪽지 - 쓰기
		</div>
	</div>
	
	<form action="${ctx}/Chat/Write.do" method="post" enctype="multipart/form-data">
		<div id="me-content">
			<div id="me-write-form">
				<div id="me-write-form-subject" class="me-form-subject">제 목</div>
				<div id="me-write-form-input" class="me-form-input">
					<input type="text" name="subject" class="input-form"/>
				</div>
				<div id="me-write-form-subject" class="me-form-subject">받는 사람</div>
				<div id="me-write-form-input" class="me-form-input">
					<input type="text" name="receiver-name" class="input-form" id="me-write-receiver" value="${receiver}" readonly="readonly"/>
					<input type="hidden" name="address-No" value="${addressNo}"/>
					<input type="button" value="주소록" id="addressBtn" class="backbtn" />
				</div>
				<div id="me-write-form-subject" class="me-form-subject">첨부 파일</div>
				<div id="me-write-form-input" class="me-form-input">
					<input type="file" class="file-form" name="file" />
				</div>
				<div id="me-write-form-input" style="height: 100%;" class="me-form-input">
					<textarea class="me-textarea" name="me-textarea"></textarea>
				</div>
			</div>
		</div>
		
		<div id="me-footer">
			<div id="me-footer-button">
				<input type="button" value="뒤로가기" class="backbtn" onclick="javascript:history.go(-1)"/>
				<input type="submit" value="보내기" class="writecomplete" />
			</div>
		</div>
	</form>
</div>

<div id="address-list">
	<c:forEach items="${dep_list}" var="dep_info" >
		<div id="address-department-list" >
			---------- ${dep_info.dep_name} ----------
		</div>
		<c:forEach items="${mb_list}" var="mb_info">
			<c:if test="${mb_info.mb_dep_no == dep_info.dep_no}">
				<c:if test="${mb_info.mb_no != mb_no}">
					<div id="address-member-info">
						<input type="checkbox" name="receiver-check" value="${mb_info.mb_no}" id="address-mb-check"/>
						<input type="text" name="${mb_info.mb_no}" value="${mb_info.posi_name} ${mb_info.mb_name}" id="address-mb-info" readonly="readonly" style="border: 0px !important;"/>
					</div>
				</c:if>
			</c:if>
		</c:forEach>
	</c:forEach>
	
	<!-- 나중에 지울것. -->
	<c:forEach var="dep_info" begin="1" end="1">
		<div id="address-department-list" >
			---------- 부서 미정 ----------
		</div>
		<c:forEach items="${mb_list}" var="mb_info">
			<c:if test="${mb_info.mb_dep_no == 0}">
				<c:if test="${mb_info.mb_no != mb_no}">
					<div id="address-member-info">
						<input type="checkbox" name="receiver-check" value="${mb_info.mb_no}" id="address-mb-check"/>
						<input type="text" name="${mb_info.mb_no}" value="${mb_info.posi_name} ${mb_info.mb_name}" id="address-mb-info" readonly="readonly" style="border: 0px !important;"/>
					</div>
				</c:if>
			</c:if>
		</c:forEach>
	</c:forEach>
	<!---------------->
</div>

<script type="text/javascript">
	$(function() {
		var isOpen;
		$("#addressBtn").click(function() {
			isOpen = $("#address-list").dialog("isOpen");
			if (isOpen) {
				$("#address-list").dialog("close");
			} else {
				event.preventDefault();
				$("#address-list").dialog("open");
			}
		});
		
		$("#address-list").dialog({
			autoOpen: false, 
			resizable: false, 
			draggable: false, 
			title: "주소록", 
			show: {
				effect : "drop",
				duration : 500
			}, 
			maxHeight: 500, 
			buttons: [{
		        text: "닫기",
		        click: function() {
		        	$(this).dialog("close");
		        },
		    },   {
		    	text: "확인", 
		    	click: function() {
		    		var receiver_list = "";
		    		var Name = "";
		    		var cnt = 0;
		    		
		    		$('input:checkbox[name="receiver-check"]:checked').each(function() {
		    			cnt = cnt + 1;
		    			receiver_list = receiver_list + $(this).val() + ",";
		    			Name = Name + getName($(this).val());
		    			
		    			if (!($('input:checkbox[name="receiver-check"]:checked').length == cnt)) {
		    				Name = Name + ", ";
		    			}
		    		});
		    		
		    		$('input[name="address-No"]').attr('value', receiver_list);
		    		$('input[name="receiver-name"]').attr('value', Name);
		    		$(this).dialog("close");
		    	}
		    }]
		});
	});
	
	/**
	* 선택한 체크박스의 value와
	* 이름을 출력하는 input text의 name을 비교하여
	* 같은 값을 가지는 input text의 value값을 반환
	*/
	function getName(mb_no) {
		return $('input:text[name='+mb_no+']').val();
	}
</script>