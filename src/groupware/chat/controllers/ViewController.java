package groupware.chat.controllers;

import groupware.commons.Controller;
import groupware.dao.MessageDAO;
import groupware.dto.MessageDTO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class ViewController implements Controller {
	private int mb_no = 0;
	private int af = 0;
	private String returnURL = "";
	private int mes_no = 0;
	private MessageDTO dto = null;
	private MessageDTO fileDto = null;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNumeric(request.getParameter("no").toString())) {
			mes_no = Integer.parseInt(request.getParameter("no"));
			af = MessageDAO.getInstance().ReceiverReadMessage(mes_no);
			
			dto = MessageDAO.getInstance().GetView(mes_no);
			mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
			
			if (dto.getMes_post_mb_no() == mb_no || dto.getMes_get_mb_no() == mb_no) {
				if (dto.getMes_file_exists() > 0) {
					fileDto = MessageDAO.getInstance().GetFile(dto.getMes_no(), "CHAT");
					request.setAttribute("FILE_INFO", fileDto);
				}
				
				request.setAttribute("VIEW_INFO", dto);
				request.setAttribute("DESC_PATH1", "쪽지함");
				request.setAttribute("DESC_PATH2", "쪽지 보기");
				request.setAttribute("NAVIGATION_VALUE", "CHAT");
				
				returnURL = "View.tiles";
			} else {
				returnURL = "js:back:잘못된 접근입니다.";
			}
		} else {
			returnURL = "js:back:잘못된 접근입니다.";
		}
		
		return returnURL;
	}
}
