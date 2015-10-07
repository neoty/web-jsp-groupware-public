package groupware.config.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.MemberDao;
import groupware.dao.PositionDAO;

public class PositionEventProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		String mode = request.getParameter("mode");
		if (mode == null || mode.equals("")) {
			return "js:back:비정상적인 접근입니다. ";
		}
		

		if (mode.equals("USE_EDIT")) {
			PrintWriter out = response.getWriter();
			String tempCertify = request.getParameter("value");
			if (tempCertify == null || tempCertify.equals("")) {
				out.print("정상 적으로 접근 해주세요. ");
				out.close();
			}
			
			String[] temp = tempCertify.split(":");
			System.out.println(temp[0]); //멤버 넘버
			System.out.println(temp[1]); //0 = 미승인 1 = 승인
			
			int af = PositionDAO.getInstance().UpdatePosition(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
			if (af > 0) {
				out.print("정상적으로 수정되었습니다. ");
				out.close();
			} else {
				out.print("에러가 발생 하였습니다. ");
				out.close();
			}
			
		}
		
		return "void";
	}

}
