package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.PositionDAO;
import groupware.dto.PositionDTO;

public class PositionEditController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		List<PositionDTO> positionList;
		positionList = PositionDAO.getInstance().GetAllPositionInfo();
		
		request.setAttribute("positionList", positionList);
		return "Position.tiles";
	}

}
