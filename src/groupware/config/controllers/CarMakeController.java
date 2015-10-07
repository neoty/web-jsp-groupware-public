package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.CarDAO;
import groupware.dto.CarListDTO;

public class CarMakeController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		String mode = request.getParameter("mode");
		
		if (mode.equals("MODIFY")) {
			String cl_no = request.getParameter("cl_no");
			CarListDTO content = new CarListDTO();
			content = CarDAO.getInstance().getCarContent(Integer.parseInt(cl_no));
			request.setAttribute("content", content);
		}
		
		return "CarMake.tiles";
	}

}
