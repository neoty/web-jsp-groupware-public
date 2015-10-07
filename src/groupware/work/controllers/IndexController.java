package groupware.work.controllers;

import groupware.commons.Controller;
import groupware.dao.CarDAO;
import groupware.dto.CarListDTO;
import groupware.dto.CarStatusDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController implements Controller {

	Calendar calendar = Calendar.getInstance();
	int startDay;
	int lastDay;
	int requestYear;
	int requestMonth;
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		String tempYear = request.getParameter("year");
		if (tempYear != null) {
			year = Integer.parseInt(tempYear);
		}
		String tempMonth = request.getParameter("month");
		if (tempMonth != null) {
			month = Integer.parseInt(tempMonth);
		}
		
		calendar.set(year, month-1, 1);
		/* 해당월의 마지막 날 구하기 정수 ex) 28,30,31 */
		lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		startDay = calendar.get(Calendar.DAY_OF_WEEK);
		request.setAttribute("LAST_DAY", lastDay);
		
		
		
		/* 자동차 리스트 뽑기 */
		List<CarListDTO> CarDto = new ArrayList<CarListDTO>();
		CarDto = CarDAO.getInstance().getCarList();
		request.setAttribute("carlist", CarDto);
		/* 각 자동차 리스트에 대한 스케줄 상황 뽑기 */
		String getDate = year + "-" + month;

		List<CarStatusDTO> list = new ArrayList<CarStatusDTO>();
		list = CarDAO.getInstance().getCarSchedule(getDate);
		request.setAttribute("list", list);
		
		/* 각 날짜 칸들의 크기를 맞추기 위해 */
		double dayWidth = (double)100 / lastDay;
		int sun = 8-startDay;
		int sat = sun++;
		
		
		request.setAttribute("DESC_PATH1", "배차 관리");
		request.setAttribute("DESC_PATH2", "차량 스케줄보기");
		request.setAttribute("YEAR", year);
		request.setAttribute("MONTH", month);
		request.setAttribute("SAT", sat);
		request.setAttribute("DAY_WIDTH", dayWidth);
		
		return "index.tiles";
	}

}
