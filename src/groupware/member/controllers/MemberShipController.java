package groupware.member.controllers;

import groupware.commons.Controller;
import groupware.dao.AdminConfigDAO;
import groupware.dao.DepartmentDAO;
import groupware.dao.MemberDao;
import groupware.dao.PositionDAO;
import groupware.dto.DepartmentDTO;
import groupware.dto.PositionDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberShipController implements Controller{

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		// 회원가입 사용여부 확인
		int af = AdminConfigDAO.getInstance().MemberShipUse();
		if (af == 0) {
			return "js:back:현재 회원 가입이 불가능 합니다. ";
		}
		// 사용가능한 부서 구하기
		List<DepartmentDTO> departmentList = new ArrayList<DepartmentDTO>();
		departmentList = DepartmentDAO.getInstance().GetDepartmentListForMemberShip();
		
		// 사용 가능한 직책 구하기
		List<PositionDTO> posiotionList = new ArrayList<PositionDTO>();
		posiotionList = PositionDAO.getInstance().GetPositionListForMemberShip();
		
		request.setAttribute("departmentList", departmentList);
		request.setAttribute("positionList", posiotionList);
		return "/WEB-INF/Member/MemberShip.jsp";
	}

}
