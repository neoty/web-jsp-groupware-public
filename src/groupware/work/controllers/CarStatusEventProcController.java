package groupware.work.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import groupware.commons.Controller;
import groupware.commons.JavaScript;
import groupware.dao.CarDAO;

public class CarStatusEventProcController implements Controller {
	HttpSession session = null;
	int af;
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		
		String type = request.getParameter("type");
		if (type == null || type.equals("")) {
			JavaScript.getIntance().HistoryBack(request, response, "잘못 접근하였습니다. ");
		}
		
		session = request.getSession();
		int mb_no = Integer.parseInt(session.getAttribute("MB_NO").toString());
		
		/**
		 * 차량 스케줄 넣기 
		 */
		if (type.equals("write")) {
			int cl_no = Integer.parseInt(request.getParameter("cl_no"));
			String date = request.getParameter("car_submit_date");
			String reason = request.getParameter("reason");
			int year = Integer.parseInt(request.getParameter("year"));
			int month = Integer.parseInt(request.getParameter("month"));
			
			
			af = CarDAO.getInstance().checkDupCarSchedule(cl_no, date);
			if (af < 1) {
				af = CarDAO.getInstance().insertCarSchedule(cl_no, date, mb_no, reason);
			} else {
				JavaScript.getIntance().MessageBackToUrl(request, response, "차량 스케줄이 중복됩니다. ", "/Work/CarWriteAndList.work?cl_no="+cl_no+"&year="+year+"&month="+month);
			}

			JavaScript.getIntance().MessageBackToUrl(request, response, "차량 스케줄을 등록 하셨습니다. ", "/Work/CarWriteAndList.work?cl_no="+cl_no+"&year="+year+"&month="+month);
		}
		
		
		/**
		 * 차량 스케줄 삭제
		 */
		if (type.equals("delete")) {
			int cs_no = Integer.parseInt(request.getParameter("cs_no"));
			af = CarDAO.getInstance().deleteCarSchedule(mb_no, cs_no);
			if (af > 0) {
				int cl_no = Integer.parseInt(request.getParameter("cl_no"));
				int year = Integer.parseInt(request.getParameter("year"));
				int month = Integer.parseInt(request.getParameter("month"));
				JavaScript.getIntance().MessageBackToUrl(request, response, "차량 스케줄을 삭제하였습니다.", "/Work/CarWriteAndList.work?cl_no="+cl_no+"&year="+year+"&month="+month);
			}
		}
		
		
		
		
		return "void";
	}

}
