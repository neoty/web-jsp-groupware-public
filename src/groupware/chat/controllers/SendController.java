package groupware.chat.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.MessageDAO;
import groupware.dto.MessageDTO;

public class SendController implements Controller{
	private int mb_no = 0;
	private List<MessageDTO> list = null;
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
		mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		
		String pg = request.getParameter("page");
		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}
		cnt = MessageDAO.getInstance().SendMesListCnt(mb_no);
		start = page - (page - 1) % 10;
		end = cnt / 20 + (cnt % 20 == 0?0:1);
		srow = 0 + (page - 1) * 20;
		request.setAttribute("totalCnt", cnt);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("page", page);
		
		list = new ArrayList<MessageDTO>();
		list = MessageDAO.getInstance().SendMesList(mb_no, srow);
		
		request.setAttribute("SEND_LIST", list);
		request.setAttribute("DESC_PATH1", "쪽지함");
		request.setAttribute("DESC_PATH2", "보낸 쪽지함");
		request.setAttribute("NAVIGATION_VALUE", "CHAT");
		
		return "Send.tiles";
	}
}

