package groupware.community.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.CommunityDAO;
import groupware.dto.BoardContentDTO;

public class ModifyController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		String type = request.getParameter("type");
		String code = request.getParameter("code");
		String bc_no = request.getParameter("bc_no");
		

		BoardContentDTO view = null;
		view = CommunityDAO.getInstance().view(Integer.parseInt(bc_no));
		if (view == null) {
			return "js:back:해당 게시물이 존재 하지 않습니다. ";
		} else {
			view.setBc_content(view.getBc_content().replace("<br>", "\r\n"));
			request.setAttribute("n", view);
		}
		
		return "Modify.tiles";
	}

}
