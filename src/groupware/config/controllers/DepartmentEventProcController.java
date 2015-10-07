package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.AdminConfigDAO;
import groupware.dao.CommunityDAO;
import groupware.dao.DepartmentDAO;
import groupware.dto.BoardDTO;
import groupware.dto.DepartmentDTO;

public class DepartmentEventProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		

		String mode = request.getParameter("mode");
		if (mode == null || mode.equals("")) {
			return "js:back:정상적으로 접근해주세요. ";
		}
		
		
		/*
		 * mode 가 "DELETE" 라면 아래 로직을 수행한다 
		 */
		if (mode.equals("DELETE")) {
			int dep_no = Integer.parseInt(request.getParameter("dep_no"));
			// 나중에 여기서 다른 테이블의 참조 관계 확인 할것
			int af;
			af = DepartmentDAO.getInstance().DepartmentDelete(dep_no);
			if (af < 1) {
				return "js:back:부서 지우기 수행중 에러가 발생하였습니다. ";
			}
			
			
			return "js:url:정상적으로 삭제 하였습니다. :/Config/Department.config";
		}
		
		/*
		 * mode 가 "CREATE" 라면 아래 로직을 수행한다 
		 */
		if (mode.equals("CREATE")) {
			int dupCheck;
			String name = request.getParameter("name");
			if (name == null || name.equals("")) {
				return "js:back:부서 이름을 입력하지 않았습니다. ";
			}
			
			dupCheck = DepartmentDAO.getInstance().getDepartmentDupCheck(name);
			if (dupCheck > 0) {
				return "js:back:중복되는 이름의 부서가 있습니다. ";
			} else {
				
				String Tempuse				=		request.getParameter("use");
				int use;
				if (Tempuse == null || Tempuse.equals("")) {
						return "js:back:사용여부를 지정해주세요. ";
				} else {
					use = Integer.parseInt(Tempuse);
				}
				String describe			=	request.getParameter("describe");
				if (describe == null || describe.equals("")) {
					return "js:back:부서 설명을 적어주세요. ";
				}
				
				String Tempadmin 		= 		request.getParameter("admin");
				int admin = -1;
					if (Tempadmin.equals("") || Tempadmin == null) {
						return "js:back:부서관리자를 지정해주세요. ";
					}
					admin				=		Integer.parseInt(Tempadmin);
				
				DepartmentDTO DTO = new DepartmentDTO();
				DTO.setDep_name(name);
				DTO.setDep_use(use);
				DTO.setDep_describe(describe);
				DTO.setDep_admin(admin);
				int af = DepartmentDAO.getInstance().createDepartment(DTO);
				
				if (af < 1) {
					return "js:back:부서추가중 데이터베이스 오류";
				} else {
					BoardDTO boardDTO = new BoardDTO();
					boardDTO.setBo_type("DEPARTMENT");
					boardDTO.setBo_name(name);
					boardDTO.setBo_describe(describe);
					boardDTO.setBo_use(use);
					boardDTO.setBo_admin(admin);
					af = CommunityDAO.getInstance().AddCommunity(boardDTO, "DEPARTMENT");
					if (af < 1) {
						return "js:back:부서추가중 데이터베이스 오류";
					}
					return "js:url:부서가 정상적으로 추가 되었습니다.:/Config/Department.config";
				}
				
			}
		}
		
		/*
		 * mode 가 "MODIFY" 라면 아래 로직을 수행한다 
		 */
		if (mode.equals("MODIFY")) {
			String Tempno = request.getParameter("dep_no");
			int dep_no;
			if (Tempno == null || Tempno.equals("")) {
				return "js:back:수정하고자 하는 부서 코드를 입력해주세요. ";
			} else {
				dep_no = Integer.parseInt(Tempno);
			}
			
			String name				= 		request.getParameter("name");
			if (name.equals("") || name == null) {
				return "js:back:부서 이름을 써주세요. ";
			}
			
			String Tempadmin 		= 		request.getParameter("admin");
			int admin;
			if (Tempadmin == null || Tempadmin.equals("")) {
				return "js:back:관리자를 지정해주세요. ";
			} else {
				admin				=		Integer.parseInt(Tempadmin);
			}
			
			String Tempuse			=		request.getParameter("use");
			int use;
			if (Tempuse == null || Tempuse.equals("")) {
					return "js:back:사용여부를 지정해주세요. ";
			} else {
				use = Integer.parseInt(Tempuse);
			}
			
			String describe			=	request.getParameter("describe");
			if (describe == null || describe.equals("")) {
				return "js:back:부서 설명을 적어주세요. ";
			}
			
			DepartmentDTO departmentDTO = new DepartmentDTO();
			departmentDTO.setDep_no(dep_no);
			departmentDTO.setDep_name(name);
			departmentDTO.setDep_describe(describe);
			departmentDTO.setDep_use(use);
			departmentDTO.setDep_admin(admin);
			
			int af = DepartmentDAO.getInstance().UpdateDepartment(departmentDTO);
			if (af < 1) {
				return "js:url:"+name+" 부서 수정 과정에서 에러가 발생하였습니다. :/Config/Department.config";
			} else {
				String timestamp = request.getParameter("timestamp");
				int tempBoNo = CommunityDAO.getInstance().getUpdateDepartmentBoNO(timestamp);
				
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setBo_no(tempBoNo);
				boardDTO.setBo_name(name);
				boardDTO.setBo_describe(describe);
				boardDTO.setBo_use(use);
				boardDTO.setBo_admin(admin);
				
				CommunityDAO.getInstance().UpdateCommunity(boardDTO, "DEPARTMENT");
				
				return "js:url:"+name+" 부서가 정상적으로 수정 되었습니다. :/Config/Department.config";
			}
		}
		
		return "js:back:총체적으로다가 문제입니다. ";
	}

}
