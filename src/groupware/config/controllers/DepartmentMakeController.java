package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.DepartmentDAO;
import groupware.dao.MemberDao;
import groupware.dto.DepartmentDTO;
import groupware.dto.MemberDTO;

public class DepartmentMakeController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		String mode = request.getParameter("mode");
		if (mode.equals("MODIFY")) {
			String Tempdep_no = request.getParameter("dep_no");
			int dep_no;
			if (Tempdep_no == null || Tempdep_no.equals("")) {
				return "js:back:잘못접근 하였습니다. ";
			} else {
				dep_no = Integer.parseInt(Tempdep_no);
			}

			int Exists = DepartmentDAO.getInstance().DepartmentExistsCheck(dep_no);
			if (Exists < 1) {
				return "js:back:부서가 존재 하지 않습니다. ";
			} else {
				DepartmentDTO content = new DepartmentDTO();
				content = DepartmentDAO.getInstance().GetDepartmentStatus(dep_no);
				request.setAttribute("content", content);
			}
			
		}
		
		
		//멤버 리스트에 대한 정보를 얻는다.
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList = MemberDao.getInstance().GetMemberList();
		request.setAttribute("memberList", memberList);
		
		
		return "DepartmentMake.tiles";
	}

}
