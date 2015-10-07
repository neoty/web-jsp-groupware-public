function formCheck() {
	var check = document.login;
	
	/* 아이디 공백 체크 */
	if (check.MB_ID.value == "") {
		check.MB_ID.focus();
		alert("아이디를 입력해주세요. ");
		return false;
	}
	/* 비밀번호 공백 체크 */
	if (check.MB_PASSWORD.value == "") {
		check.MB_PASSWORD.focus();
		alert("비밀번호를 입력해주세요. ");
		return false;
	}
	
	
	return true;
}