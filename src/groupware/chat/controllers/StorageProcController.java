package groupware.chat.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.MessageDAO;

public class StorageProcController implements Controller{
	private String category = "";
	private String url = "";
	private String msg = "";
	private String[] mes_no;
	private String mes_no_list = "";
	private int af = 0;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		category = request.getParameter("category");
		mes_no = request.getParameterValues("check-delete[]");
		
		for (int i = 0; i < mes_no.length; i++) {
			mes_no_list = mes_no_list + mes_no[i];
			if (i != (mes_no.length-1)) {
				mes_no_list = mes_no_list + ", ";
			}
		}
		af = MessageDAO.getInstance().StorageMessage(mes_no_list, category);
		
		if (af == 0) {
			msg = "저장하지 못했습니다.";
		} else {
			msg = "저장 하였습니다.";
		}
		
		if (category.equals("index")) {
			url = "/Chat/index.chat";
		} else if (category.equals("send")) {
			url = "/Chat/Send.chat";
		} 
		
		return "js:url:" + msg + ":" + url;
	}
}
