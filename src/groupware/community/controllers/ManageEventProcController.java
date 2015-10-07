package groupware.community.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.CommunityDAO;

public class ManageEventProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		String value = request.getParameter("value");
		String type = request.getParameter("type");
		String code = request.getParameter("code");
		String[] temp = value.split(":");
		int mb_no = Integer.parseInt(temp[0]);
		int guide = Integer.parseInt(temp[1]);
		
		/*
		 * 보안 로직 추가 할것 여기에
		 */
		
		
		
		String tableName = null;
		if (type.equals("PROJECT")) {
			tableName = "gw_project_rel";
		} else if (type.equals("CIRCLES")) {
			tableName = "gw_circles_rel";
		}
		if (guide == 1) {
			CommunityDAO.getInstance().RelInsert(Integer.parseInt(code), mb_no, tableName);
		} else if (guide == 0) {
			if (type.equals("PROJECT")) {
				CommunityDAO.getInstance().ProjectRelDelete(Integer.parseInt(code), mb_no);
			} else if (type.equals("CIRCLES")) {
				CommunityDAO.getInstance().CirclesRelDelete(Integer.parseInt(code), mb_no);
			}
		}
		
		
		
		
		
		
		System.out.println("값"+value);
		System.out.println("형태"+type);
		System.out.println("코드"+code);
		
		
		
		
		return null;
	}

}
