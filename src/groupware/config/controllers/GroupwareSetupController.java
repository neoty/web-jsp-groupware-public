package groupware.config.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.commons.JavaScript;
import groupware.dao.ConfigDao;

public class GroupwareSetupController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		
		//////////////////////////////////////
		//////////// 전체 테이블 삭제 ///////////
		//		try {
		//			int af;
		//			af = ConfigDao.getInstance().DeleteTable();
		//		} catch (Exception e) {
		//			// TODO: handle exception
		//			return "/WEB-INF/Setup/setup.jsp";
		//		}
		/////////// 나중에 삭제할것 ////////////
		////////////////////////////////////
		
		
		int count;
		try {
			count = ConfigDao.getInstance().SetupCheck();
			if (count >= 1) {
				JavaScript.getIntance().MessageBackToUrl(request, response,
						"이미 설치 하였습니다. ", "/index.jsp");
				return "void";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "/WEB-INF/Setup/setup.jsp";
		}
		return "void";
	}
}
