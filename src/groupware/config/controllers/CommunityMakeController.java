package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.commons.JavaScript;
import groupware.dao.AdminConfigDAO;
import groupware.dao.CommunityDAO;
import groupware.dao.MemberDao;
import groupware.dto.BoardDTO;
import groupware.dto.DepartmentDTO;
import groupware.dto.MemberDTO;

public class CommunityMakeController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		
		/*
		 * 여기서 관리자 세션 체크 할것
		 */
		
		String community = request.getParameter("community");
		if (community == null || community.equals("")) {
			return "js:back:정상적으로 접근해주세요. ";
		}
		
		if (!(community.equals("PUBLIC") || community.equals("PROJECT") || community.equals("CIRCLES"))) {
			return "js:back:정상적으로 접근해주세요. ";
		}
		
		String mode = request.getParameter("mode");
		if (mode == null || mode.equals("")) {
			System.out.println("여긴");
			return "js:back:정상적으로 접근해주세요. ";
		}
		if (!(mode.equals("CREATE") || mode.equals("MODIFY") || mode.equals("DELETE"))) {
			return "js:back:정상적으로 접근해주세요. ";
		}
		
		/*
		 * mode 가 "MODIFY" 로들어 왔다면 아래 로직을 수행후 리턴한다.
		 * 아니라면 즉 CREATE 로 들어왔다면 아래 로직은 건너 띄고 리턴한다.
		 */
		if (mode.equals("MODIFY")) {
			/*
			 * 게시판에 대한 세부 정보 뽑아내기
			 */
			
			int bo_no = Integer.parseInt(request.getParameter("bo_no"));
			BoardDTO content = new BoardDTO();
			content = CommunityDAO.getInstance().GetBoardStatus(bo_no);
			
			int check = CommunityDAO.getInstance().BoardExistsCheck(bo_no);
			
			if (check < 1) {
				return "js:back:해당 게시판이 존재하지 않습니다. ";
			}
			
			/*
			 * 게시판의 총 사용자수 뽑아내기
			 */
			int total = 0;
			if (community.equals("CIRCLES")) {
				total = AdminConfigDAO.getInstance().GetRelTableTotal("gw_circles_rel", bo_no);
			} else if (community.equals("PROJECT")) {
				total = AdminConfigDAO.getInstance().GetRelTableTotal("gw_project_rel", bo_no);
			}
			
			
			request.setAttribute("content", content);
			request.setAttribute("total", total);
		} 
		
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList = MemberDao.getInstance().GetMemberList();
		request.setAttribute("memberList", memberList);
		
		return "CommunityMake.tiles";
	}

}
