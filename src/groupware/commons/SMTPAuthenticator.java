package groupware.commons;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator {
	public PasswordAuthentication getPasswordAuthentication() {

		return new PasswordAuthentication("elitegroupware", "tpalsk1234");
	}
}