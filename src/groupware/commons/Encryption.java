package groupware.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	private String userPassword;

	/*
	 * SHA-512 암호화
	 * 출처 : http://darrysea.tistory.com/50
	 */
	public boolean encryption(String userPassword) {
		MessageDigest md;
		boolean isSuccess;
		String tempPassword = "";

		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(userPassword.getBytes());
			byte[] mb = md.digest();
			for (int i = 0; i < mb.length; i++) {
				byte temp = mb[i];
				String s = Integer.toHexString(new Byte(temp));
				while (s.length() < 2) {
					s = "0" + s;
				}
				s = s.substring(s.length() - 2);
				tempPassword += s;
			}
			this.userPassword = tempPassword;
			isSuccess = true;
		} catch (NoSuchAlgorithmException e) {
			isSuccess = false;
			return isSuccess;
		}
		return isSuccess;
	}

	public String getPassword() {
		return userPassword;
	}
}
