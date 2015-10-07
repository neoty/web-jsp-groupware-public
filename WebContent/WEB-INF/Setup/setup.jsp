<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	/*
	*	아이디 = uid
	*	비밀번호1 = passwd1
	*	비밀번호2 = passwd2
	*	이메일 = email
	*	핸드폰 = phone
	*	starthour
	*	startmin
	*	endhour
	*	endmin
	*/
%>
<!DOCTYPE html> 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/pace.min.js"></script>
<script type="text/javascript" src="${ctx}/js/check.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/formstyle.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/MemberStyle.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/loading.css" />
<title><!-- 입력:제목들어갈곳  --></title>
</head>
<body>
	<div id="membership-wrapper">
		<div id="membership-center">
			<div id="curPath">
				<div id="Path">
					<img src="${ctx}/images/pathicon.gif"> <font color="#E74C3C"><b>그룹웨어 설치 & 설정<b></font>
				</div>
			</div>
			<form name="SubmitForm" action="${ctx}/Setup/setup.do" method="POST" onsubmit="return formCheck()">
			
			<div id="MemberForm-wrapper">
			<div style="margin-bottom: 10px;"><font style="font-size: 12px; color: #E74C3C; font-weight: bold;">※모든 정보를 필히 기입해주세요. </font></div>
				<div id="subForm">
					<div id="ex">관리자 아이디</div>
					<div id="exSelect">
						<input class="input-form" type="text" name="uid" value="아이디는 4자 이상으로 해주세요" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">관리자 비밀번호</div>
					<div id="exSelect">
						<input class="input-form" type="password" name="passwd1" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">관리자 비밀번호</div>
					<div id="exSelect">
						<input class="input-form" type="password" name="passwd2" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">이 메일</div>
					<div id="exSelect">
						<input class="input-form" type="text" name="email" value="유요한 이메일을 입력해주세요" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">연락처</div>
					<div id="exSelect">
						<input class="input-form" type="text" name="phone" value="유요한 핸드폰 번호를 입력해주세요" onfocus="this.value='';return true;">
					</div>
				</div>
				<div id="subForm">
					<div id="ex">근무 시작 시각</div>
					<div id="exSelect">
						<select name="starthour" style="width: 80px;">
	    					<option value="1">01</option>
   							<option value="2">02</option>
							<option value="3">03</option>
							<option value="4">04</option>
							<option value="5">05</option>
							<option value="6">06</option>
							<option value="7">07</option>
							<option value="8">08</option>
							<option value="9">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
						</select>시 : 
						<select name="startmin" style="width: 80px;">
	    					<option value="00">00</option>
   							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
							<option value="32">32</option>
							<option value="33">33</option>
							<option value="34">34</option>
							<option value="35">35</option>
							<option value="36">36</option>
							<option value="37">37</option>
							<option value="38">38</option>
							<option value="39">39</option>
							<option value="40">40</option>
							<option value="41">41</option>
							<option value="42">42</option>
							<option value="43">43</option>
							<option value="44">44</option>
							<option value="45">45</option>
							<option value="46">46</option>
							<option value="47">47</option>
							<option value="48">48</option>
							<option value="49">49</option>
							<option value="50">50</option>
							<option value="51">51</option>
							<option value="52">52</option>
							<option value="53">53</option>
							<option value="54">54</option>
							<option value="55">55</option>
							<option value="56">56</option>
							<option value="57">57</option>
							<option value="58">58</option>
							<option value="59">59</option>
						</select>분 부터
					</div>
				</div>
				<div id="subForm">
					<div id="exend">근무 종료 시각</div>
					<div id="exSelectend">
						<select name="endhour" style="width: 80px;">
	    					<option value="1">01</option>
   							<option value="2">02</option>
							<option value="3">03</option>
							<option value="4">04</option>
							<option value="5">05</option>
							<option value="6">06</option>
							<option value="7">07</option>
							<option value="8">08</option>
							<option value="9">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
						</select>시 : 
						<select name="endmin" style="width: 80px;">
	    					<option value="00">00</option>
   							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
							<option value="32">32</option>
							<option value="33">33</option>
							<option value="34">34</option>
							<option value="35">35</option>
							<option value="36">36</option>
							<option value="37">37</option>
							<option value="38">38</option>
							<option value="39">39</option>
							<option value="40">40</option>
							<option value="41">41</option>
							<option value="42">42</option>
							<option value="43">43</option>
							<option value="44">44</option>
							<option value="45">45</option>
							<option value="46">46</option>
							<option value="47">47</option>
							<option value="48">48</option>
							<option value="49">49</option>
							<option value="50">50</option>
							<option value="51">51</option>
							<option value="52">52</option>
							<option value="53">53</option>
							<option value="54">54</option>
							<option value="55">55</option>
							<option value="56">56</option>
							<option value="57">57</option>
							<option value="58">58</option>
							<option value="59">59</option>
						</select>분 까지
					</div>
				</div>
				
				<div id="bottomButton">
					<div class="backdiv">
						<input class="backbtn" type="button" value="뒤로 가기" onclick="javascript:history.go(-1)">
					</div>
					<div class="submitdiv">
						<input type="submit" class="writecomplete" value="작성 완료">
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
	<div id="membership-right"></div>
	<div id="bottom">
		<br />
		<cat> <a href="#"> <b>© Copyright Sunchon Corp. rights
				reserved.</b>
		</a> </cat>
	</div>
</body>
</html>