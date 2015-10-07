package groupware.chat.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.DepartmentDAO;
import groupware.dao.MemberDao;
import groupware.dao.MessageDAO;
import groupware.dto.DepartmentDTO;
import groupware.dto.MemberDTO;
import groupware.dto.MessageDTO;

public class WriteController implements Controller{
	private int receiver = 0;
	private MessageDTO mes_DTO = null;
	private List<DepartmentDTO> dep_List = null;
	private List<MemberDTO> mb_List = null;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		if (request.getParameter("no") != null) {
			receiver = Integer.parseInt(request.getParameter("no"));
			
			mes_DTO = MessageDAO.getInstance().GetMember(receiver);
		} else if (request.getParameter("answer_btn") != null) {
			mes_DTO = new MessageDTO();
			mes_DTO.setMes_post_mb_no(Integer.parseInt(request.getParameter("mes_post_mb_no")));
			mes_DTO.setMes_post_mb_posi_name(request.getParameter("mes_post_mb_posi_name"));
			mes_DTO.setMes_post_mb_name(request.getParameter("mes_post_mb_name"));
			mes_DTO.setMes_get_mb_no(Integer.parseInt(request.getParameter("mes_get_mb_no")));
			mes_DTO.setMes_get_mb_posi_name(request.getParameter("mes_get_mb_posi_name"));
			mes_DTO.setMes_get_mb_name(request.getParameter("mes_get_mb_name"));
		} else {
			mes_DTO = null;
		}
		
		dep_List = DepartmentDAO.getInstance().GetSimpleDepList();
		mb_List = MemberDao.getInstance().GetSimpleMemberList();
		
		request.setAttribute("RECEIVER", mes_DTO);
		request.setAttribute("DEP_LIST", dep_List);
		request.setAttribute("MB_LIST", mb_List);
		request.setAttribute("DESC_PATH1", "쪽지함");
		request.setAttribute("DESC_PATH2", "쪽지 쓰기");
		
		return "Write.tiles";
	}
}
