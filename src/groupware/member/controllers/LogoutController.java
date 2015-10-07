package groupware.member.controllers;

import groupware.commons.Controller;
import groupware.commons.JavaScript;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller{

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		
		System.out.println("세션 삭제");
		return "js:url:로그아웃되었습니다. :/index.jsp";
	}

}
