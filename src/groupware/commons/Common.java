package groupware.commons;

import groupware.community.controllers.MenuList;
import groupware.dao.CommunityDAO;
import groupware.dao.MessageDAO;
import groupware.dto.BoardDTO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Common {
	
	private static Common _instance;

	public static Common getIntance() {
		if (_instance == null) {
			System.out.println("공통함수 인스턴스");
			_instance = new Common();
		}
		return _instance;
	}
	
	public String Time_yyyy_mm_dd_hh_mm_ss() {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = tempDate.format(new Date());
		return date;
	}
	
	public void GetCommunityMenu(HttpServletRequest request) throws SQLException {
		/*
		 * 게시판 리스트 뿌리기 로직 시작
		 */
		HttpSession session = request.getSession();
		String temp = session.getAttribute("MB_NO").toString();
		int mb_no = Integer.parseInt(temp);
		List<BoardDTO> menuList;
		menuList = new MenuList().PublicANDDepartment(mb_no);
		request.setAttribute("menuList", menuList);
		
		List<BoardDTO> resList;
		resList = CommunityDAO.getInstance().CirclesANDProject(mb_no);
		request.setAttribute("menuList1", resList);
		/*
		 * 게시판 리스트 뿌리기 로직 끝
		 */		
	}
	
	/**
	 * 읽지 않은 쪽지 갯수 뿌리기 로직
	 * @param mb_no : 멤버 no
	 * @throws SQLException
	 */
	public void GetUnreadMessage(HttpServletRequest request) throws SQLException {
		int mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		int cnt = MessageDAO.getInstance().GetNotReadMessage(mb_no);
		
		request.setAttribute("UnreadMessage", cnt);
	}
	
	
}
