package groupware.chat.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.MessageDAO;

public class DeleteProcController implements Controller {
	private String mb_no = "";
	private int af = 0;
	private String category = "";
	private String[] mes_no;
	private String mes_no_list = "";
	private String url = "";
	private String msg = "";
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		category = request.getParameter("category");
		mb_no = request.getSession().getAttribute("MB_NO").toString();
		mes_no = request.getParameterValues("check-delete[]");
		
		for (int i = 0; i < mes_no.length; i++) {
			mes_no_list = mes_no_list + mes_no[i];
			if (i != (mes_no.length-1)) {
				mes_no_list = mes_no_list + ", ";
			}
		}
		
		if (category.equals("storage")) {
			af = MessageDAO.getInstance().DelStorageMessage(mes_no_list, mb_no);
			url = "/Chat/Storage.chat";
			if (af == 0) {
				msg = "삭제에 실패했습니다.";
			} else {
				msg = "보관함에서 삭제되었습니다.";
			}
		} else {
			af = MessageDAO.getInstance().DelMessage(mes_no_list, category);
			if (af == 0) {
				msg = "삭제에 실패했습니다.";
				if (category.equals("index")) {
					url = "/Chat/index.chat";
				} else {
					url = "/Chat/Send.chat";
				}
			} else {
				if (category.equals("index")) {
					msg = "받은 쪽지함에서 삭제되었습니다.";
					url = "/Chat/index.chat";
				} else if (category.equals("send")) {
					msg = "보낸 쪽지함에서 삭제되었습니다.";
					url = "/Chat/Send.chat";
				}
			}
		}
		
		return "js:url:" + msg + ":" + url;
	}
}
