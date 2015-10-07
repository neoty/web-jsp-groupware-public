package groupware.community.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.print.resources.serviceui;
import groupware.commons.Controller;
import groupware.dao.CommunityDAO;
import groupware.dao.ReplyDAO;
import groupware.dto.BoardContentDTO;
import groupware.dto.ReplyDTO;

public class ViewController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		String bc_no = request.getParameter("bc_no");
		if (bc_no.equals("") || bc_no == null) {
			return "js:back:잘못된 접근입니다. ";
		}
		
		String code = request.getParameter("code");
		if (code.equals("") || code == null) {
			return "js:back:잘못된 접근입니다. ";
		}
		String boardType = request.getParameter("type");
		if (boardType.equals("") || boardType == null) {
			return "js:back:잘못된 접근입니다. ";
		}
		
		
		boolean authCheck = false;
		authCheck = CommunityAuthCheck.getInstance().authCheck(request, Integer.parseInt(code), boardType);
		if (authCheck == false) {
			return "js:back:권한이 없습니다. ";
		}
		
		
		BoardContentDTO view = null;
		List<ReplyDTO> commentList = new ArrayList<ReplyDTO>();
		view = CommunityDAO.getInstance().view(Integer.parseInt(bc_no));
		if (view == null) {
			return "js:back:해당 게시물이 존재 하지 않습니다. ";
		} else {
			CommunityDAO.getInstance().incrementReadCount(Integer.parseInt(bc_no));
			request.setAttribute("n", view);
			commentList = ReplyDAO.getInstance().getReply(Integer.parseInt(bc_no));
			request.setAttribute("commentList", commentList);
		}

		request.setAttribute("DESC_PATH1", "글 보기");
		return "View.tiles";
	}

}
