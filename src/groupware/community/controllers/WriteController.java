package groupware.community.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;

public class WriteController implements Controller{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub

		request.setAttribute("DESC_PATH1", "글쓰기");
		
		return "Write.tiles";
	}

}
