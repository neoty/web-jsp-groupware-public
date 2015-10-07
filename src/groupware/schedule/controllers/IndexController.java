package groupware.schedule.controllers;

import groupware.commons.Controller;
import groupware.dao.DepartmentDAO;
import groupware.dao.MemberDao;
import groupware.dao.ScheduleDAO;
import groupware.dto.MemberDTO;
import groupware.dto.ScheduleDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

public class IndexController implements Controller{
	private Calendar calendar;
	private Calendar cal = null;
	private int mb_no;
	private int my_mb_no;
	private int mDep_no;
	private int getYear;
	private int getMonth;
	private int tmpGetToday;
	private String getToday;
	private int lastDay;
	private int startDay;
	private int endDay;
	private int tmpMonth = 0;
	private static int thisYear = 0;
	private static int thisMonth = 0;
	private List<ScheduleDTO> toList = null;
	private List<ScheduleDTO> monList = null;
	private ArrayList<String> dayofsccntSList;
	private ArrayList<String> dayofsccntEList;
	private MemberDTO mDto = null;
	private String showCategory = "";
	private String showName = "";
	private String mReturnURL = "";
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		my_mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		
		calendar = Calendar.getInstance();
		tmpGetToday = calendar.get(Calendar.DATE);
		
		if (Integer.toString(tmpGetToday).length() < 2) {
			getToday = "0" + Integer.toString(tmpGetToday);
		} else {
			getToday = Integer.toString(tmpGetToday);
		}
		String year = request.getParameter("getYear");
		String month = request.getParameter("getMonth");
		
		if (year == "" || year == null) {
			getYear = calendar.get(Calendar.YEAR);
		} else {
			getYear = Integer.parseInt(year);
		}

		if (month == "" || year == null) {
			getMonth = calendar.get(Calendar.MONTH);
		} else {
			getMonth = Integer.parseInt(month);
				if (getMonth < 1 || getMonth > 12) {
					getMonth = calendar.get(Calendar.MONTH);
				}
			getMonth--;
		}
		
		if (cal == null) {
			cal = Calendar.getInstance();
		}
		thisYear = cal.get(Calendar.YEAR);
		thisMonth = cal.get(Calendar.MONTH);
		
		calendar.set(getYear, getMonth, 1);

		lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		startDay = calendar.get(Calendar.DAY_OF_WEEK);
		
		calendar.set(getYear, getMonth, lastDay);
		endDay = calendar.get(Calendar.DAY_OF_WEEK);
		
		
		if (request.getParameter("dep") != null) {
			mDep_no = Integer.parseInt(request.getParameter("dep").toString());
			showName = DepartmentDAO.getInstance().GetDepName(my_mb_no, mDep_no);
			if (showName.equals("")) {
				mReturnURL = "js:back:다른 부서의 스케줄은 볼 수 없습니다.";
			} else {
				showCategory = "부서 스케줄";
				request.setAttribute("DEP_NO", mDep_no);
				mReturnURL = "index.tiles";
				
				tmpMonth = getMonth + 1;
				
				String reMonth = Integer.toString(tmpMonth);
				
				if (reMonth.length() < 2) {
					reMonth = "0" + reMonth;
				}
				
				monList = ScheduleDAO.getInstance().ReadMonthDepSchedule(mDep_no, getYear + "-" + reMonth, lastDay);
			}
		} else {
			if (request.getParameter("no") != null) {
				mb_no = Integer.parseInt(request.getParameter("no").toString());
				if (mb_no == my_mb_no) {
					mb_no = my_mb_no;
					showCategory = "개인 스케줄";
					showName = "스케줄 관리";
					mReturnURL = "index.tiles";
				} else {
					mDto = MemberDao.getInstance().GetMbName(mb_no, my_mb_no);
					if (mDto == null) {
						mReturnURL = "js:back:다른 부서의 스케줄은 볼 수 없습니다.";
					} else {
						showCategory = "부서원 스케줄";
						showName = mDto.getPosi_name() + " " + mDto.getMb_name();
						request.setAttribute("DEP_MB_NO", mb_no);
						mReturnURL = "index.tiles";
					}
				}
			} else {
				mb_no = my_mb_no;
				showCategory = "개인 스케줄";
				showName = "스케줄 관리";
				mReturnURL = "index.tiles";
			}
			
			tmpMonth = getMonth + 1;
			
			String reMonth = Integer.toString(tmpMonth);
			if (reMonth.length() < 2) {
				reMonth = "0" + reMonth;
			}
			monList = ScheduleDAO.getInstance().ReadMonthSchedule(mb_no, getYear + "-" + reMonth , lastDay);
		}
		
		tmpMonth = thisMonth + 1;
		
		String reMonth = Integer.toString(tmpMonth);
		if (reMonth.length() < 2) {
			reMonth = "0" + reMonth;
		}
		toList = ScheduleDAO.getInstance().TodaySchedule(thisYear + "-" + reMonth + "-" + getToday, my_mb_no);
		
		dayofsccntSList = new ArrayList<String>();
		dayofsccntEList = new ArrayList<String>();
		for (int i = 0; i < monList.size(); i++) {
			dayofsccntSList.add(i, monList.get(i).getSc_s_date());
		}
		for (int i = 0; i < monList.size(); i++) {
			dayofsccntEList.add(i, monList.get(i).getSc_e_date());
		}
		
		request.setAttribute("P_YEAR", getYear);
		request.setAttribute("P_THISYEAR", thisYear);
		request.setAttribute("P_MONTH", getMonth);
		request.setAttribute("P_THISMONTH", thisMonth);
		request.setAttribute("P_DAY", getToday);
		request.setAttribute("P_LASTDAY", lastDay);
		request.setAttribute("P_STARTDAY", startDay);
		request.setAttribute("P_ENDDAY", endDay);
		request.setAttribute("T_SC_LIST", toList);
		request.setAttribute("M_SC_LIST", monList);
		request.setAttribute("DAYOFSCSCNT", dayofsccntSList);
		request.setAttribute("DAYOFSCECNT", dayofsccntEList);
		
		request.setAttribute("DESC_PATH1", showCategory);
		request.setAttribute("DESC_PATH2", showName);
		return mReturnURL;
	}

}
