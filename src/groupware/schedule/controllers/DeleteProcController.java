package groupware.schedule.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.ScheduleDAO;
import groupware.dto.ScheduleDTO;

public class DeleteProcController implements Controller{
	private int af = 0;
	private String mCategory1 = "";
	private String mCategory2 = "";
	private String mReturnURL = "";
	private Pattern pat = null;
	private Matcher mMat = null;
	private Boolean mB = false;
	private String mTmp_sc_no = "";
	private int sc_no;
	private int mb_no;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		pat = Pattern.compile("^[0-9]*$");
		mTmp_sc_no = request.getParameter("schedule-view-con-no");
		mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		mMat = pat.matcher(mTmp_sc_no);
		mB = mMat.matches();
		try {
			if (!mB) {
				mReturnURL = "js:back:잘못된 접근입니다.";
			} else {
				sc_no = Integer.parseInt(mTmp_sc_no);
				af = ScheduleDAO.getInstance().GetCheckSchedule(sc_no);
				af = ScheduleDAO.getInstance().DelSchedule(sc_no, mb_no, af);
			}
			
			if (af == 0) {
				mReturnURL = "js:back:삭제되지 않았습니다.";
			} else {
				mReturnURL = request.getHeader("REFERER").toString();
				int tmp;
				tmp = mReturnURL.indexOf("/Schedule/");
				mReturnURL = mReturnURL.substring(tmp);
				System.out.println(mReturnURL);
				mReturnURL = "js:url:삭제 되었습니다.:" + mReturnURL;
			}
		} catch (Exception e) {
			mReturnURL = "js:back:잘못된 접근입니다.";
		}
		
		return mReturnURL;
	}
}
