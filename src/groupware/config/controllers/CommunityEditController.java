package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.CommunityDAO;
import groupware.dao.DepartmentDAO;
import groupware.dto.BoardDTO;

public class CommunityEditController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		List<BoardDTO> publicList = CommunityDAO.getInstance().GetListBoard("PUBLIC");
		request.setAttribute("publicList", publicList);
		
		List<BoardDTO> circlesList = CommunityDAO.getInstance().GetListBoard("CIRCLES");
		request.setAttribute("circlesList", circlesList);

		List<BoardDTO> projectList = CommunityDAO.getInstance().GetListBoard("PROJECT");
		request.setAttribute("projectList", projectList);
		
		return "Community.tiles";
	}

}
