package groupware.member.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.commons.Encryption;
import groupware.commons.Mail;
import groupware.dao.MemberDao;

public class FindPasswordProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String ques = request.getParameter("queslist");
		String ans = request.getParameter("ans");
		/* 나중에 검증 절차 주가 할것  */
		
		int af = MemberDao.getInstance().findPassword(id, ques, ans);
		PrintWriter out = response.getWriter();
		if (af > 0) {
			String email = null;
			/* 패스워드 초기화 실행후 멤버의 패스워드를 초기화 한다.  */
			int ran = (int) (Math.random()*62321);
			String resetPassword = "newpassword"+ran;
			String Password = resetPassword;
			Encryption enc = new Encryption();
			enc.encryption(resetPassword);
			resetPassword = enc.getPassword();
			MemberDao.getInstance().resetPassword(id, resetPassword);
			
			/* 초기화된 패스워드를 등록된 멤버의 메일로 송신한다.  */
			email = MemberDao.getInstance().findPasswordGetEmail(id);
			Mail mail = new Mail();
			mail.send(email, "elitegroupware@gmail.com", Password);
			
			out.print("<script>");
			out.print("alert('등록한 이메일("+email+")로 패스워드가 전송 되었습니다. ');");
			out.print("window.open('about:blank', '_self').close();");
			out.print("</script>");
			out.close();
		} else {
			return "js:back:회원정보를 찾지 못하였습니다. ";
		}
		
		return "void";
	}

}
