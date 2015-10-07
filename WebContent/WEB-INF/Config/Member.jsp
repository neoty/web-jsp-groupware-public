<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/config.css" />

<div id="config-main">

	<div id="config-status-title" style="border-bottom: none; padding-left: 125px;">
		멤버 정보 수정
			<div style="float: right; padding-right: 5px;">
			<select name="to" id="to" onchange="toMethod(this.value);" class="config-combo-form" style="width: 130px;">	
				   	<c:choose>
    				<c:when test="${param.array == 'mb_dep_no'}">
  				        <c:set var="arrayName" value="부서순"/>
     				</c:when>
       				<c:when test="${param.array == 'mb_position_posi_level'}">
           				<c:set var="arrayName" value="직책순"/>
       				</c:when>
    				<c:when test="${param.array == 'mb_name'}">
           				<c:set var="arrayName" value="이름순"/>
   					</c:when>
   					<c:when test="${param.array == 'mb_birth'}">
           				<c:set var="arrayName" value="생일순"/>
   					</c:when>
   					<c:when test="${param.array == 'mb_join_date'}">
           				<c:set var="arrayName" value="가입일순"/>
   					</c:when>
   					<c:otherwise>
   			       		 <c:set var="arrayName" value="부서순"/>
   					</c:otherwise>
 				  	</c:choose>
					<option value="" selected>정렬 순서 - ${arrayName}</option>
					<option value="mb_dep_no">부서순</option>
					<option value="mb_position_posi_level">직책순</option>
					<option value="mb_name">이름순</option>
					<option value="mb_birth">생일순</option>
					<option value="mb_join_date">가입일순</option>
			</select>		
			</div>
	</div>
	<div id="config-community-title">
		<div class="community-title-1">
			번호
		</div>
		<div class="community-title-2">
			소속 부서명
		</div>
		<div class="community-title-3" style="width: 60px !important;">
			직책
		</div>
		<div class="community-title-4">
			이름
		</div>
		<div class="community-title-4" style="width: 80px !important;">
			생일
		</div>
		<div class="community-title-4" style="width: 100px !important;">
			연락처
		</div>
		<div class="community-title-4" style="width: 100px !important;">
			가입 일자
		</div>
		<div class="community-title-5">
			승인 여부
		</div>
	</div>
	<c:if test="${fn:length(memberList) == 0 }">
	<div style="text-align: center; margin-top: 10px;">가입한 회원이 없습니다. </div>
	</c:if>
	<c:if test="${fn:length(memberList) > 0 }">
		<c:forEach var="n" items="${memberList}" varStatus="status">
			<c:set var="number" value="1"/>
		<div id="config-community-content">
			<div class="community-title-1" style="font-size: 9px; font-family: verdana, tahoma; font-weight: bold;">
				${status.count}
			</div>
			<div class="community-title-2" >
				<c:if test="${empty n.dep_name}">
					부서 없음
				</c:if>
				<c:if test="${!empty n.dep_name}">
					${n.dep_name}
				</c:if>
				
			</div>
			<div class="community-title-3" style="width: 60px !important; font-weight: bold; color: #E74C3C;">
				${n.posi_name}
			</div>
			<div class="community-title-4">
				<c:choose>
					<c:when test="${n.mb_img == '' || empty n.mb_img || n.mb_img == null}">
						<a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/images/noimg.png">${n.mb_name}</a>
					</c:when>
					<c:otherwise>
						<a href="#" class="screenshot" style="color: #3F5765; font-weight: bold;" rel="${ctx}/MemberImg/${n.mb_img}">${n.mb_name}</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="community-title-4" style="width: 80px !important; font-weight: bold; font-size: 9px; font-family: verdana, tahoma;">
				${n.mb_birth}
			</div>
			<div class="community-title-4" style="width: 100px !important; font-weight: bold; font-size: 9px; font-family: verdana, tahoma;">
				${n.mb_tel}
			</div>
			<div class="community-title-4" style="width: 100px !important; font-weight: bold; font-size: 9px; font-family: verdana, tahoma;">
				${n.mb_join_date}
			</div>
			<div class="community-title-5">
				<c:if test="${n.mb_certify == '1'}">
					<select name="certify" id="certify" onchange="certify_change(this.value);" class="config-combo-form" style="width: 105px;">
						<option value="${n.mb_no}:1" selected>승인됨</option>
						<option value="${n.mb_no}:0">승인 않됨</option>
					</select>
				</c:if>
				<c:if test="${n.mb_certify == '0'}">
					<select name="certify" id="certify" onchange="certify_change(this.value);" class="config-combo-form" style="width: 105px;">
						<option value="${n.mb_no}:0" selected>승인 않됨</option>
						<option value="${n.mb_no}:1">승인됨</option>
					</select>
				</c:if>
			</div>
		</div>
		</c:forEach>
				<br/>
				<br/>
	</c:if>
</div>


<script>
	function toMethod(value) {
		var get = value;
		var url = "${ctx}/Config/Member.config?array="+value;
		location.href =  url;
	}
	
	function certify_change(value) {
		var get = value;
		
		$.ajax({
			type: "POST",
			async:false,
			url: "${ctx}/Config/MemberEvent.do?mode=CERTIFY_EDIT&value="+value,
			success: function(msg) {
					alert(msg);
			}
		});
		
	}
</script>


