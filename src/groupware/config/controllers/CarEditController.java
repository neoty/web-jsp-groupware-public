package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.CarDAO;
import groupware.dto.CarListDTO;

public class CarEditController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		List<CarListDTO> carList = new ArrayList<CarListDTO>();
		carList = CarDAO.getInstance().getCarList();
		request.setAttribute("carList", carList);
		
		return "Car.tiles";
	}

}
