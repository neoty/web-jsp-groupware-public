package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.AdminConfigDAO;
import groupware.dao.DepartmentDAO;
import groupware.dto.DepartmentDTO;

public class DepartmentEditController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		List<DepartmentDTO> departmentList = DepartmentDAO.getInstance().GetListDepartment();		
		request.setAttribute("departmentList", departmentList);
		return "Department.tiles";
	}

}
