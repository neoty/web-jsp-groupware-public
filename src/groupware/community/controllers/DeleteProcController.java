package groupware.community.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import groupware.commons.Controller;
import groupware.dao.CommunityDAO;
import groupware.dao.FileDAO;

public class DeleteProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		/*
		 * 아래 보안 로직 추가 할것 (구현 되지 않았음)
		 * 
		 */
		
		HttpSession session = request.getSession();
		int mb_no = Integer.parseInt(session.getAttribute("MB_NO").toString());
		int bc_no = Integer.parseInt(request.getParameter("bc_no"));
		int af = CommunityDAO.getInstance().deleteBoardContent(bc_no, mb_no);
		if (af <= 0) {
			return "js:back:삭제 오류 발생";
		} else {
			af = FileDAO.getInstance().deleteFileFromCommunity(bc_no);
		}
		
		
		
		String type = request.getParameter("type");
		int code = Integer.parseInt(request.getParameter("code"));
		return "js:url:삭제 되었습니다. :/Community/index.community?type="+type+"&code="+code;
	}

}
