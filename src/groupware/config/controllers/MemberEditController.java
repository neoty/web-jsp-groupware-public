package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.MemberDao;
import groupware.dto.MemberDTO;

public class MemberEditController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		String array = request.getParameter("array");
		if (array == null || array.equals("")) {
			array = "mb_dep_no";
		}
		if (!(array.equals("mb_dep_no") || array.equals("mb_position_posi_level")
				|| array.equals("mb_name") || array.equals("mb_birth") || array.equals("mb_join_date"))) {
			array = "mb_dep_no";
		}
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList = MemberDao.getInstance().GetMemberAll(array);
		request.setAttribute("memberList", memberList);
		
		return "Member.tiles";
	}

}
