package groupware.work.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.WorkFlowDAO;
import groupware.dto.WorkFlowDTO;

public class WorkFlowNoticeController implements Controller {
	int page = 1;
	int cnt;
	int start;
	int end;
	int srow;
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		/**
		 * 결재 완료된 문서 옮기는곳
		 */
		String pg = request.getParameter("page");
		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}
		
		cnt = WorkFlowDAO.getInstance().getPublicNoticeCnt();
		start = page - (page - 1) % 10;
		end = cnt / 20 + (cnt % 20 == 0?0:1);
		srow = 0 + (page - 1) * 20;
		
		List<WorkFlowDTO> list = new ArrayList<WorkFlowDTO>();
		list = WorkFlowDAO.getInstance().getPublicNoticeList(srow);
		
		request.setAttribute("doc_list", list);
		request.setAttribute("totalCnt", cnt);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("page", page);
		request.setAttribute("DESC_PATH1", "결재 공지");
		return "WorkFlowNotice.tiles";
	}

}
