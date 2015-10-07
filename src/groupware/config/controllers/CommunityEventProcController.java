package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.commons.JavaScript;
import groupware.commons.SessionCheck;
import groupware.dao.AdminConfigDAO;
import groupware.dao.CommunityDAO;
import groupware.dto.BoardDTO;

public class CommunityEventProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		
		
		String community = request.getParameter("community");
		if (community == null || community.equals("")) {
			return "js:back:정상적으로 접근해주세요. ";
		}
		if (!(community.equals("PUBLIC") || community.equals("PROJECT") || community.equals("CIRCLES"))) {
			return "js:back:정상적으로 접근해주세요. ";
		}
		
		
		String mode = request.getParameter("mode");
		if (mode == null || mode.equals("")) {
			return "js:back:정상적으로 접근해주세요. ";
		}
		if (!(mode.equals("CREATE") || mode.equals("MODIFY") || mode.equals("DELETE"))) {
			return "js:back:정상적으로 접근해주세요. ";
		}
		
		
		
		/*
		 * mode 가 "DELETE" 라면 아래 로직을 수행한다 
		 */
		if(mode.equals("DELETE")) {
			int bo_no = Integer.parseInt(request.getParameter("bo_no"));
			
			// 나중에 여기에 다른 테이블의 참조 관계 확인 할것
			int af;
			af = CommunityDAO.getInstance().BoardDelete(bo_no);
			if (af < 1) {
				return "js:back:게시판 지우기 수행중 에러가 발생하였습니다. ";
			}
			
			
			return "js:url:정상적으로 게시판이 삭제 하였습니다. :/Config/Community.config";
		}
		
		
		/*
		 * mode 가 "CREATE" 라면 아래 로직을 수행한다 
		 */
		if (mode.equals("CREATE")) {

			String name				= 		request.getParameter("name");
			if (name.equals("") || name == null) {
				return "js:back:게시판 이름을 써주세요. ";
			}
			
			
			
			String Tempadmin 		= 		request.getParameter("admin");
			int admin = -1;
			if (!(community.equals("PUBLIC"))) {
				if (Tempadmin.equals("") || Tempadmin == null) {
					return "js:back:관리자를 지정해주세요. ";
				}
				admin				=		Integer.parseInt(Tempadmin);
			} else if (community.equals("PUBLIC")) {
				admin				=		1;
			}
			
			
			String Tempuse				=		request.getParameter("use");
			int use;
			if (Tempuse == null || Tempuse.equals("")) {
					return "js:back:사용여부를 지정해주세요. ";
			} else {
				use = Integer.parseInt(Tempuse);
			}
			
			
			String describe			=	request.getParameter("describe");
			if (describe == null || describe.equals("")) {
				return "js:back:게시판 설명을 적어주세요. ";
			}
			
			
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setBo_type(community);
			boardDTO.setBo_name(name);
			boardDTO.setBo_describe(describe);
			boardDTO.setBo_use(use);
			boardDTO.setBo_admin(admin);
			
			String where = null;
			if (community.equals("CIRCLES")) {
				where = "CIRCLES";
			} else if (community.equals("PROJECT")) {
				where = "PROJECT";
			} else {
				where = "PUBLIC";
			}
			int af = CommunityDAO.getInstance().AddCommunity(boardDTO, where);
			if (af < 1) {
				return "js:url:커뮤니티 추가 과정에서 에러가 발생하였습니다. :/Config/Community.config";
			} else {
				return "js:url:"+name+" 커뮤니티가 정상적으로 추가 되었습니다. :/Config/Community.config";
			}
			
		}
		
		
		
		
		/*
		 * mode 가 "MODIFY" 라면 아래 로직을 수행한다 
		 */
		if (mode.equals("MODIFY")) {
			
			String Tempno = request.getParameter("bo_no");
			int bo_no;
			if (Tempno == null || Tempno.equals("")) {
				return "js:back:수정하고자 하는 커뮤니티 코드를 입력해주세요. ";
			} else {
				bo_no = Integer.parseInt(Tempno);
			}
			
			String name				= 		request.getParameter("name");
			if (name.equals("") || name == null) {
				return "js:back:게시판 이름을 써주세요. ";
			}
			
			
			
			String Tempadmin 		= 		request.getParameter("admin");
			int admin = -1;
			if (!(community.equals("PUBLIC"))) {
				if (Tempadmin.equals("") || Tempadmin == null) {
					return "js:back:관리자를 지정해주세요. ";
				} else {
					admin			=		Integer.parseInt(Tempadmin);
				}
			} else if (community.equals("PUBLIC")) {
				admin				=		1;
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
				return "js:back:게시판 설명을 적어주세요. ";
			}
			
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setBo_no(bo_no);
			boardDTO.setBo_name(name);
			boardDTO.setBo_describe(describe);
			boardDTO.setBo_use(use);
			boardDTO.setBo_admin(admin);
			
			String where = null;
			if (community.equals("CIRCLES")) {
				where = "CIRCLES";
			} else if (community.equals("PROJECT")) {
				where = "PROJECT";
			}
			
			int af = CommunityDAO.getInstance().UpdateCommunity(boardDTO, where);
			System.out.println(af);
			if (af < 1) {
				return "js:url:"+name+" 커뮤니티 수정 과정에서 에러가 발생하였습니다. :/Config/Community.config ";
			} else {
				return "js:url:"+name+" 커뮤니티가 정상적으로 수정 되었습니다. :/Config/Community.config ";
			}
		}
		
		return "js:back:총체적으로다가 문제입니다. ";
	}
}
