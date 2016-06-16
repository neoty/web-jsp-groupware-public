package groupware.commons;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator {
	public PasswordAuthentication getPasswordAuthentication() {

		return new PasswordAuthentication("@gmail.com 제외한 이름", "비밀번호");
	}
}