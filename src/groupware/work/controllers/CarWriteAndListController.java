package groupware.work.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.CarDAO;
import groupware.dto.CarStatusDTO;

public class CarWriteAndListController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		
		
		String temp__cl_no = request.getParameter("cl_no");
		if (temp__cl_no == null || temp__cl_no.equals("")) {
			return "js:back:잘못된 접근입니다. ";
		}

		String temp__year = request.getParameter("year");
		if (temp__year == null || temp__year.equals("")) {
			return "js:back:잘못된 접근입니다.";
		}
		
		String temp__month = request.getParameter("month");
		if (temp__month == null || temp__month.equals("")) {
			return "js:back:잘못된 접근입니다. ";
		}
		int cl_no = Integer.parseInt(temp__cl_no);
		int year = Integer.parseInt(temp__year);
		int month = Integer.parseInt(temp__month);

		request.setAttribute("DESC_PATH1", "배차");
		request.setAttribute("DESC_PATH2", "스케줄 쓰기");
		request.setAttribute("cl_no", cl_no);
		List<CarStatusDTO> list = new ArrayList<CarStatusDTO>(CarDAO.getInstance().getCarNoSchedule(cl_no, year, month));
		request.setAttribute("LIST", list);
		return "CarWriteList.tiles";
	}

}
