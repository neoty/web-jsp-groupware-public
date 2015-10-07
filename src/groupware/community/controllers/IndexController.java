package groupware.community.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import groupware.commons.Controller;
import groupware.dao.CommunityDAO;
import groupware.dto.BoardContentDTO;
import groupware.dto.BoardDTO;

public class IndexController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String temMb_no = session.getAttribute("MB_NO").toString();
		
		String tempType = request.getParameter("type");
		String type = "PUBLIC";
		if (tempType != null) {
			type = tempType;
		}
		
		String path1 = "공통 게시판";
		if (type.equals("CIRCLES")) {
			path1 = "동아리 게시판";
		} else if (type.equals("PROJECT")) {
			path1 = "프로젝트 게시판";
		} else if (type.equals("DEPARTMENT")) {
			path1 = "부서 게시판";
		}
		request.setAttribute("DESC_PATH1", path1);
		
		
		String bc_code = request.getParameter("code");
		int code = 1;
		if (bc_code != null) {
			code = Integer.parseInt(bc_code);
		}
		
		/*
		 * DEPARTMENT, CIRCLES, PROJECT 에 대한 REL 에 대한 보안 검사
		 */
		System.out.println("부서 보안검사 통과");
		if (type.equals("DEPARTMENT")) {
			int adminNo = CommunityDAO.getInstance().checkDepartment(code);
			request.setAttribute("adminNo", adminNo);
		}
		System.out.println("서클 보안검사");
		if (type.equals("CIRCLES")) {
			String admin = CommunityDAO.getInstance().checkCircles(code, Integer.parseInt(temMb_no));
			request.setAttribute("adminPart", admin);
		}
		System.out.println("프로젝트 보안검사");
		if (type.equals("PROJECT")) {
			String admin = CommunityDAO.getInstance().checkProject(code, Integer.parseInt(temMb_no));
			request.setAttribute("adminPart", admin);
		}
		
		
		
		
		int page = 1;
		String pg = request.getParameter("page");
		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}

		int cnt = 0;
		
		String mode = request.getParameter("mode");
		String search = "";
		if (mode == null) {
			cnt = CommunityDAO.getInstance().getCountBoard(type, code);
		} else if (mode.equals("search")) {
			search = request.getParameter("search_value");
			cnt = CommunityDAO.getInstance().getSearchCountBoard(type, code, search);
			System.out.println("총 게시글 숫자" + cnt);
		}
		
		int start = page - (page - 1) % 10;
		int end = cnt / 20 + (cnt % 20 == 0?0:1);
		int srow = 0 + (page - 1) * 20;
		
		List<BoardContentDTO> contentList;
		List<BoardContentDTO> noticeList;
		if (mode == null) {
			contentList = CommunityDAO.getInstance().getList(type, code, srow);
			request.setAttribute("List", contentList);
			noticeList = CommunityDAO.getInstance().getNoticeList(type, code);
			request.setAttribute("noticeList", noticeList);
		} else if (mode.equals("search")) {
		
			contentList = CommunityDAO.getInstance().getSearchList(type, code, srow, search);
			request.setAttribute("List", contentList);
		}

		request.setAttribute("total", cnt);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("page", page);
		
		return "index.tiles";
	}

}
