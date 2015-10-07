package groupware.community.controllers;

import groupware.commons.Controller;
import groupware.dao.CheckDAO;
import groupware.dao.MemberDao;
import groupware.dto.MemberDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManageController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String tempses = session.getAttribute("MB_NO").toString();
		int mb_no = Integer.parseInt(tempses);
		
		String type = request.getParameter("type");
		if (type == null) {
			return "js:back:잘못된 접근입니다. ";
		}
		String code = request.getParameter("code");
		if (code == null) {
			return "js:back:잘못된 접근입니다. ";
		}
		
		
		
		String array = request.getParameter("array");
		if (array == null || array.equals("")) {
			array = "mb_dep_no";
		}
		if (!(array.equals("mb_dep_no") || array.equals("mb_position_posi_level")
				|| array.equals("mb_name") || array.equals("mb_birth") || array.equals("mb_join_date"))) {
			array = "mb_dep_no";
		}
		
		/*
		 * 권한 체크
		 */
		int check = 0;
		if (type.equals("PROJECT")) {
			check = CheckDAO.getInstance().ProjectAdminCheck(mb_no, Integer.parseInt(code));
		} else if (type.equals("CIRCLES")) {
			check = CheckDAO.getInstance().CirclesAdminCheck(mb_no, Integer.parseInt(code));
		} else {
			return "js:back:(CODE-001)잘못된접근입니다. ";
		}
		if (check == 0) {
			return "js:back:(CODE-002)권한이 없습니다. ";
		}
		
		
		
		if (type.equals("PROJECT")) {
			List<MemberDTO> memberList = new ArrayList<MemberDTO>();
			memberList = MemberDao.getInstance().GetProjectRelList(Integer.parseInt(code), mb_no, array);
			request.setAttribute("memberList", memberList);
		} else if (type.equals("CIRCLES")) {
			List<MemberDTO> memberList = new ArrayList<MemberDTO>();
			memberList = MemberDao.getInstance().GetCirclesRelList(Integer.parseInt(code), mb_no, array);
			request.setAttribute("memberList", memberList);
		}
		
		
		return "Manage.tiles";
	}

}
