package groupware.commons;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionCheck {
	private static SessionCheck _instance;
	private static String[] OpenPage = 
		{ 
		"/index.jsp",
		"/Member/MemberShip.member", 
		"/Member/Login.do",
		"/Setup/setup.config",
		"/Setup/setup.do",
		"/Member/MemberShipProc.do",
		"/Member/Logout.do",
		"/Member/MemberShipProc.do",
		"/Member/FindPassword.member",
		"/Member/FindPassword.do"
		};

	public static SessionCheck getInstance() {
		if (_instance == null) {
			System.out.println("체크 인스턴스 한번만");
			_instance = new SessionCheck();
		}
		return _instance;
	}

	public boolean CommonCheck(HttpServletRequest request, String uri) {
		HttpSession session = request.getSession();
		/* 로그인이 필요없는 프로시저 및 페이지는 위에 배열에 등록해 놓고
		 * 디스패쳐 부분의 호출에 따라 세션이 없는 사람이 들어올경우
		 * 배열에 있는 주소중에 일치하는게 없으면 그대로 false 를 반환하고
		 * 일치하는사람이 있으면 true를 반환함 만약 세션이 있다면  else 문 true
		 * 를 반환함
		 */
		if (session.getAttribute("MB_NO") == null) {
			for (int i = 0; i < OpenPage.length; i++) {
				if (uri.equals(OpenPage[i])) 
					return true;
			}
		} else {
			return true;
		}

		return false;
	}
	
	public boolean AdminCheck(HttpServletRequest request) throws IOException {

		HttpSession session = request.getSession();
		String value = session.getAttribute("MB_WORK_TYPE").toString();
		if (!value.equals("0")) {
			return true;
		}
		return false;
	}
}
