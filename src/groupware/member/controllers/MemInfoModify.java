package groupware.member.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import groupware.commons.Controller;
import groupware.dao.DepartmentDAO;
import groupware.dao.MemberDao;
import groupware.dao.PositionDAO;
import groupware.dto.DepartmentDTO;
import groupware.dto.MemberDTO;
import groupware.dto.PositionDTO;

public class MemInfoModify implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		
		
		
		HttpSession session = request.getSession();
		int mb_no = Integer.parseInt(session.getAttribute("MB_NO").toString());
		MemberDTO meminfo = new MemberDTO();
		meminfo = MemberDao.getInstance().getMemAsSession(mb_no);
		request.setAttribute("meminfo", meminfo);
		
		// 사용가능한 부서 구하기
		List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
		departmentList = DepartmentDAO.getInstance().GetDepartmentListForMemberShip();
				
		// 사용 가능한 직책 구하기
		List<PositionDTO> posiotionList = new ArrayList<PositionDTO>();
		posiotionList = PositionDAO.getInstance().GetPositionListForMemberShip();
				
		return "/WEB-INF/Member/MemInfoModify.jsp";
	}

}
