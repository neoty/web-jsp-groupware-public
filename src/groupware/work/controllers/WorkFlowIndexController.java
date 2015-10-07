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

public class WorkFlowIndexController implements Controller {
	private List<WorkFlowDTO> list = null;
	private int mb_no = 0;
	private String tmp_status = "";
	private int status = 0;
	private String pg = "";
	private int page = 1;
	private int cnt;
	private int start;
	private int end;
	private int srow;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		tmp_status = request.getParameter("list_status");
		if (tmp_status == null || tmp_status.equals("")) {
			status = 0;
		} else {
			status = Integer.parseInt(tmp_status);
		}

		pg = request.getParameter("page");
		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}
		
		cnt = WorkFlowDAO.getInstance().getWorkListCnt(mb_no, status);
		start = page - (page - 1) % 10;
		end = cnt / 20 + (cnt % 20 == 0?0:1);
		srow = 0 + (page - 1) * 20;
		request.setAttribute("totalCnt", cnt);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("page", page);
		
		list = new ArrayList<WorkFlowDTO>();
		list = WorkFlowDAO.getInstance().getWorkList(mb_no, status, srow);
		if (list != null) {
			request.setAttribute("DOC_LIST", list);
		}
		
		
		/*
					select * from gw_workflow where 
					(wf_mb_first='회원번호' AND wf_first_status=1) 
					OR 
					(wf_mb_second='회원번호' AND wf_second_status=1) 
					OR 
					(wf_mb_third='회원번호' AND wf_third_status=1);
					
					// 두번째 사람이 받고 승인을 할시 select 쿼리를 이용하여 결재관리를 정보를 가져온후
					(wf_mb_first, wf_mb_second, wf_mb_third 를 차례로 검사하여 자신의 회원번호와 일치하는 필드를 알아낸다) 
					그다음 사람의 wf_mb_단계 가 있을경우 wf_단계_status 를 1로 바꾸어준다.
					만약 그다음 단계의 mb 가 없거나 이미 wf_third_mb 단계 일경우 그대로 결재 알림 으로 전송한다.
		 */
		
		
		request.setAttribute("LIST_STATUS", status);
		request.setAttribute("DESC_PATH1", "결재 관리");
		request.setAttribute("DESC_PATH2", "결재 리스트");
		request.setAttribute("NAVIGATION_VALUE", "WORK");
		return "WorkFlowIndex.tiles";
	}

}
