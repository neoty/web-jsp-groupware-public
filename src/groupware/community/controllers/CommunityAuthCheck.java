package groupware.community.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import groupware.dao.CheckDAO;
import groupware.dao.CommunityDAO;

public class CommunityAuthCheck {
	private static CommunityAuthCheck _instance;

	public static CommunityAuthCheck getInstance() {
		if (_instance == null) {
			_instance = new CommunityAuthCheck();
			System.out.println("커뮤니티 권한체크  인스턴스 최초 생성");
		}
		return _instance;
	}
	
	/**
	 * 게시판 접근 존재 여부 및 접근 권한 체크
	 * @param request
	 * @param code
	 * @param boardType
	 * @return
	 * @throws SQLException
	 */
	public boolean authCheck(HttpServletRequest request, int code, String boardType) throws SQLException {
		
		boolean authCheck = false;
		
		/*
		 * 코드에 해당 하는 게시판이 존재 하는지 검사
		 */
		int af = CommunityDAO.getInstance().BoardExistsCheck(code);
		if (af <= 0) {
			return authCheck;
		}
		
		/*
		 * 각 타입 게시판에 대한 접근 권한 검사
		 */
		HttpSession session = request.getSession();
		int mb_no = Integer.parseInt(session.getAttribute("MB_NO").toString());
		
		if (boardType.equals("CIRCLES") || boardType.equals("PROJECT")) {
			authCheck = CheckDAO.getInstance().communityAuthCheck(mb_no, code, boardType);
		} else if (boardType.equals("PUBLIC")) {
			authCheck = true;
		} else if (boardType.equals("DEPARTMENT")) {
			authCheck = CheckDAO.getInstance().departmentAuthCheck(mb_no, code);
		}
		return authCheck;
	}
	
}
