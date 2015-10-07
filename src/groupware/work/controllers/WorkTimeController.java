package groupware.work.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import groupware.commons.Controller;
import groupware.dao.WorkTimeDAO;
import groupware.dto.AttendanceDTO;

public class WorkTimeController implements Controller {
	Calendar calendar = Calendar.getInstance();
	int startDay;
	int lastDay;
	int requestYear;
	int requestMonth;
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
	int day = calendar.get(Calendar.DAY_OF_MONTH);
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
		
		int queryMonthTmp = month; //쿼리를 위해 문자열 조합
		String queryMonth = ""; //쿼리를 위해 문자열 조합
		HttpSession session = request.getSession();
		int mb_no = Integer.parseInt(session.getAttribute("MB_NO").toString());
		lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 마지막 날
		
		
		/*
		 * 문자열 조합
		 */
		if (queryMonthTmp < 10) {
			queryMonth = year+"-0"+String.valueOf(queryMonthTmp)+"-%";
		} else {
			queryMonth = year+"-"+String.valueOf(queryMonthTmp)+"-%";
		}
		
		
		List<AttendanceDTO> list = new ArrayList<AttendanceDTO>();
		list = WorkTimeDAO.getInstance().getAttList(mb_no, queryMonth);
		

		/**
		 * 초과 시간을 계산하기 위해 config 테이블에서 설정한 정보를 가지고온다.
		 * 판단은 시작 시간이 null 값인지만 체크한다.
		 */
		System.out.println("초과 시간 로직 시작");
		if (session.getAttribute("sTimeLimit") == null) {
			String[] workInfo = WorkTimeDAO.getInstance().WorkTimeInfo();
			session.setAttribute("sTimeLimit", workInfo[0]);
			session.setAttribute("eTimeLimit", workInfo[1]);
		}
		String sTimeLimit = session.getAttribute("sTimeLimit").toString();
		String eTimeLimit = session.getAttribute("eTimeLimit").toString();
		OverWorkProcess.getInstance().caluStartTime(sTimeLimit, list);
		OverWorkProcess.getInstance().caluEndTime(eTimeLimit, list);
		
		
		
		
		
		request.setAttribute("day", day); //오늘날짜 
		request.setAttribute("lastday", lastDay); //해당달의 마지막일
		request.setAttribute("list", list);
		request.setAttribute("YEAR", year);
		request.setAttribute("MONTH", month);
		request.setAttribute("DESC_PATH1", "출근 관리");
		request.setAttribute("DESC_PATH2", "출근 리스트");
		return "Worktime.tiles";
	}

}
