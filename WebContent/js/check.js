	/*
	*	아이디=uid
	*	패스워드1=passwd1
	*	패스워드2=passwd2
	*	이름=realname
	*	성별=gender
	*	이메일=email
	*	핸드폰번호=phonenumber
	*	직책=position
	*	부서=department
	*	비밀번호 찾기용 질문=passwdq
	*	비밀번호 찾기용 대답=passwda
	*/

function formCheck() 
{
	
	var check = document.SubmitForm;
	
	/* 아이디 공백 체크 */
	if (check.uid.value == "") {
		check.uid.focus();
		alert("아이디를 입력해주세요. ");
		return false;
	}
	
	
	/* 아이디 유효성 검증 */
	var strReg = /^[A-Za-z0-9]+$/;
	if(!strReg.test(check.uid.value)) {
		check.uid.focus();
		alert("아이디는 영문과 숫자만 가능합니다. ");
		return false;
	}


	

	/* 확인용 패스워드1 공백 체크 */
	if (check.passwd1.value == "") {
		check.passwd1.focus();
		alert("패스워드를 입력해주세요. ");
		return false;
	}
	
	/* passwd1 변수 */
	var minLen = 8;
	var maxLen = 16;
	var lengthMessage = '패스워드의 길이는 최소 8자 이상 16자 이하 입니다.';
	
	/* passwd1 길이 체크 */
	var passwd1 = check.passwd1.value;
	var length1 = passwd1.length;
	if (parseInt(length1) < parseInt(minLen) || parseInt(length1) > parseInt(maxLen)) {
		alert(lengthMessage);
		check.passwd1.focus();
		return false;
	}
	
	/* 확인용 패스워드2 공백 체크 */
	if (check.passwd2.value == "") {
		check.passwd2.focus();
		alert("확인용 패스워드를 입력해주세요. ");
		return false;
	}
	
	/* passwd2 길이 체크 */
	var passwd2 = check.passwd2.value;
	var length2 = passwd2.length;
	if (parseInt(length2) < parseInt(minLen) || parseInt(length2) > parseInt(maxLen)) {
		alert(lengthMessage);
		check.passwd1.focus();
		return false;
	}
	
	/* 패스워드 동일 여부 확인 */
	if ((check.passwd1.value) != (check.passwd2.value)) {
		check.passwd2.focus();
		alert("패스워드가 서로 일치하지 않습니다. ");
		return false;
	}
	
	
	/* 성명 공백 체크 */
	if (check.realname.value == "") {
		check.realname.focus();
		alert("이름을 입력해주세요. ");
		return false;
	}
	
	
	/* 이메일 공백 체크 및 정규식 검사*/
	if (check.email.value == "") {
		check.email.focus();
		alert("이메일을 입력해주세요. ");
		return false;
	} else {
		var email = check.email.value;
		var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
		if (!email.match(regExp)) {
			check.email.focus();
			alert("이메일 형식에 맞지 않습니다.");
			return false;
			}
	}

	/* 전화번호 공백 체크 */
	if (check.phonenumber.value == "") {
		check.phonenumber.focus();
		alert("핸드폰 번호를 입력해주세요. ");
		return false;
	} else {
		// 나중에 이부분 고칠것
		var phonenumber = check.phonenumber.value;
		var regexMdn =  "/^[0-9]{3}-[0-9]{3,4}-[0-9]{4}/";
		if (!regexMdn.test(phonenumber)) {
			check.phonenumber.focus();
			alert("핸드폰번호가 형식에 맞지 않습니다. ");
			return false;
		}
	}
	
	if (check.birth.value == "") {
		check.birth.focus();
		alert("생년 월일을 입력해주세요. ");
		return false;
	} else {
		var birth = check.birth.value;
		var birthrex = "/^0\d{4}-[1-9]\d{2}-\d{2}$/";
		if (!birthrex.test(birth)) {
			check.birth.focus();
			alert("생년 월일이 형식에 맞지 않습니다. ");
			return false;
		}
	}
	
	/* 직책 공백 체크 */
	if (check.position.value == "none") {
		check.position.focus();
		alert("직책을 선택해주세요. ");
		return false;
	}
	
	
	/* 부서 공백 체크 */
	if (check.department.value == "none") {
		check.department.focus();
		alert("부서를 선택해주세요. ");
		return false;
	}
	
	
	/* 비밀번호 찾기용 질문 공백 체크 */
	if (check.passwdq.value == "none") {
		check.passwdq.focus();
		alert("비밀번호 찾기용 질문을 선택해주세요. ");
		return false;
	}
	
	
	/* 비밀번호 찾기용 대답 공백 체크 */
	if (check.passwda.value == "") {
		check.passwda.focus();
		alert("비밀번호 찾기용 답을 입력해주세요. ");
		return false;
	}
	
	return true;
}



/**
 * 체크박스 체크 확인 및 버튼 이벤트.
 */
function checkboxCheck(obj, ctx) {
	var sendForm = document.postData;
	var btn = obj.name;
	var checkB = document.getElementsByName("check-delete[]");
	var checkBlength = checkB.length;
	var checkCnt = 0;
	var text = "";
	
	/*
		체크박스 checked 확인
		checkCnt : 체크된 체크박스의 수
		text : 체크된 체크박스들의 값
	*/
	for (var i=0; i < checkBlength; i++) {
		if (checkB[i].checked) {
			text = text + checkB[i].value + "/";
			checkCnt = checkCnt + 1;
		}
	}
	
	switch (btn) {
	case "delete":
		messageDelete(checkCnt, sendForm, ctx);
		break;
	case "storage":
		messageStorage(checkCnt, sendForm, ctx);
		break;
	case "answer":
		messageAnswer(checkCnt, sendForm, ctx);
		break;
	case "write":
		messageWrite(sendForm, ctx);
		break;
	}
}

/**
 * 체크박스 전부 체크.
 */
function allCheck() {
	var checkAll = document.getElementById("check-all");
	var checkB = document.getElementsByName("check-delete[]");
	var checkBlength = checkB.length;
	if (!checkAll.checked) {
		for (var i=0; i < checkBlength; i++) {
			checkB[i].checked = false;
		}
	} else {
		for (var i=0; i < checkBlength; i++) {
			checkB[i].checked = true;
		}
	}
}

/**
 * 메시지 삭제 버튼 클릭 시 체크 된 갯수 확인 
 * 및 삭제 proc으로 post 전송 메소드
 * @param checkCnt : 선택된 체크박스 갯수
 * @param sendForm : 해당 form
 * @param ctx : context 경로
 */
function messageDelete(checkCnt, sendForm, ctx) {
	if (checkCnt == 0) {
		alert("삭제할 쪽지를 선택해주세요.");
	} else {
		confirmDialog(sendForm, "삭제하시겠습니까?", ctx+"/Chat/Delete.do");
	}
}

/**
 * 메시지 저장 버튼 처리 메소드
 * @param checkCnt : 선택된 체크박스 갯수
 * @param sendForm : 해당 form
 * @param ctx : context 경로
 */
function messageStorage(checkCnt, sendForm, ctx) {
	if (checkCnt == 0) {
		alert("보관할 쪽지를 선택해주세요.");
	} else {
		sendForm.action = ctx + "/Chat/Storage.do";
		sendForm.submit();
	}
}

/**
 * 체크된 메시지를 보낸 또는 받는 사람에게 답장 쓰기 메소드
 * @param checkCnt : 체크된 체크박스 갯수
 * @param sendForm : 전송할 form
 * @param ctx : context 경로
 */
function messageAnswer(checkCnt, sendForm, ctx) {
	if (checkCnt == 0) {
		alert("답장 할 쪽지를 선택해주세요.");
	} else if (checkCnt > 1) {
		alert("하나만 선택해주세요.");
	} else {
		sendForm.action = ctx + "/Chat/Write.chat";
		sendForm.submit();
	}
}

/**
 * 메시지 쓰기 메소드
 * @param sendForm : 전송할 form
 * @param ctx : context 경로
 */
function messageWrite(sendForm, ctx) {
	sendForm.action = ctx + "/Chat/Write.chat";
	sendForm.submit();
}

/**
 * 확인 버튼 다이얼 로그 띄우고 확인 버튼 누를 시 post 전송
 * @param sendForm : 전송할 form
 * @param msg : 다이얼로그에 띄울 메시지
 * @param url : 전송할 url
 */
function confirmDialog(sendForm, msg, url) {
	if(confirm(msg)!=0) {
		sendForm.action = url;
		sendForm.submit();
	} else {
		
	}
}
/****************************************************************/