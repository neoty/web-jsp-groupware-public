package groupware.people.controllers;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.DepartmentDAO;
import groupware.dto.DepartmentDTO;
import groupware.dto.MemberDTO;

public class IndexController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub

		List<DepartmentDTO> dep_list = new ArrayList<DepartmentDTO>();
		dep_list = DepartmentDAO.getInstance().getDepartmentList();
		request.setAttribute("dep_list", dep_list);

		int page = 1;
		String pg = request.getParameter("page");
		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}

		/*
		 * array 로 넘어온 정렬 에서 한글 처리 문제와 뜯어 놓기 부분
		 */
		int dep_no = 0;
		String temp = request.getParameter("array");
		String[] depName = null;
		if (temp != null && !temp.equals("")) {
			temp = new String(request.getParameter("array").getBytes("8859_1"),
					"UTF-8");
			depName = temp.split("-");
			request.setAttribute("depname", depName[1]);
			dep_no = Integer.parseInt(depName[0]);
		}

		/*
		 * 아마 나중에 이부분 고쳐야 될듯 회원 20명이상 추가 됐을때 버그 일어날듯
		 */
		int cnt = DepartmentDAO.getInstance().departmentMemberCount(dep_no);
		int start = page - (page - 1) % 10;
		int end = cnt / 20 + (cnt % 20 == 0 ? 0 : 1);
		int srow = 0 + (page - 1) * 20;
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList = DepartmentDAO.getInstance().getDepartmentMemberList(
				dep_no, srow);

		request.setAttribute("cnt", cnt);
		request.setAttribute("DESC_PATH", "부서별 보기");
		request.setAttribute("page", page);
		request.setAttribute("count", cnt);
		request.setAttribute("memberList", memberList);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		return "index.tiles";
	}
}
