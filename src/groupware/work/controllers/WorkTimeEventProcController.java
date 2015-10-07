package groupware.work.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import groupware.commons.Controller;
import groupware.commons.JavaScript;
import groupware.dao.WorkTimeDAO;

public class WorkTimeEventProcController implements Controller {

	private SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private int af = 0;
	private long long_time = System.currentTimeMillis();
	private String time = formatTime.format(new Date(long_time));
	private String checkDate = time.substring(0, 10);
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		HttpSession session = request.getSession();
		int mb_no = Integer.parseInt(session.getAttribute("MB_NO").toString());
		
		
		if (type.equals("start")) {
			
			af = WorkTimeDAO.getInstance().startCheck(mb_no, checkDate);
			if (af < 1) {
				af = WorkTimeDAO.getInstance().startWork(mb_no, time);
				return "js:url:출근 하였습니다. :/Work/WorkTime.work";
			} else {
				// printstream 으로 줄것인지?
				return "js:url:이미 출근 도장을 찍으셨습니다. :/Work/WorkTime.work";
			
			}
		}

		
		if (type.equals("end")) {
			af = WorkTimeDAO.getInstance().getAtt_no(mb_no, checkDate);
			if (af < 1) {
				return "js:url:오늘 출근 기록이 없습니다. :/Work/WorkTime.work";
			} else {
				int att_no = af;
				String endCheck = WorkTimeDAO.getInstance().checkEndTime(att_no);
				if (endCheck != null) {
					return "js:url:이미 퇴근 도장을 찍으셨습니다. :/Work/WorkTime.work";
				} else {
					af = WorkTimeDAO.getInstance().setEndTime(att_no, time);
					return "js:url:퇴근 하셨습니다. :/Work/WorkTime.work";
				}
			}
		}
		
		return "void";
	}

}
