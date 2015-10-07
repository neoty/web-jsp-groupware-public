package groupware.work.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.WorkFlowDAO;
import groupware.dto.WorkFlowDTO;

public class WorkViewController implements Controller {
	private int wf_no = 0;
	private int mb_no = 0;
	private WorkFlowDTO mWfDTO = null;
	private String tmp = "";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		wf_no = Integer.parseInt(request.getParameter("view_doc_no"));
		mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		mWfDTO = new WorkFlowDTO();
		mWfDTO = WorkFlowDAO.getInstance().getWorkDoc(wf_no, mb_no);
		
		request.setAttribute("DOC_INFO", mWfDTO);
		request.setAttribute("DESC_PATH1", "결재 관리");
		request.setAttribute("DESC_PATH2", "결재 리스트");
		request.setAttribute("NAVIGATION_VALUE", "WORK");
		return "/WEB-INF/Work/WorkView.jsp";
	}
}
