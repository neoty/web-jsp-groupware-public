package groupware.config.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

import groupware.commons.Controller;
import groupware.commons.Encryption;
import groupware.commons.JavaScript;
import groupware.dao.ConfigDao;

public class GroupwareSetupDoController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub

		// form.submit(); 접근 하지 않을시 뒤로 튕기기
		String referer = request.getHeader("referer");
		if (referer == null) {
			JavaScript.getIntance().MessageBackToUrl(request, response, "정상적인 경로로 접근해주세요. ", "/Config/setup.config");
			return "void";
		}
		
		
		String ID = request.getParameter("uid");
		if (ID.length() < 8) {
			JavaScript.getIntance().HistoryBack(request, response,
					"아이디는 8자 이상을 해주세요");
			return "void";
		}

		String PASSWD1 = request.getParameter("passwd1");
		String PASSWD2 = request.getParameter("passwd2");
		if (!(PASSWD1.equals(PASSWD2))) {
			JavaScript.getIntance().HistoryBack(request, response,
					"패스워드가 같지 않습니다.");
			return "void";
		} else if (PASSWD1.length() < 12) {
			JavaScript.getIntance().HistoryBack(request, response,
					"패스워드는 12자 이상해주세요");
			return "void";
		}
		
		// 여기에서 패스워드 함호화 할것
		Encryption enc = new Encryption();
		enc.encryption(PASSWD1);
		String ADMIN_PASSWORD = enc.getPassword();

		String EMAIL			 =			 request.getParameter("email");
		String PHONE			 =			 request.getParameter("phone");

		// 나중엔 split 메서드를 이용해서 분리 할것 ex string.split(":");
		String START_HOUR		 =			 request.getParameter("starthour");
		String START_MIN		 = 			 request.getParameter("startmin");
		String WORK_START		 =			 START_HOUR + ":" + START_MIN;
		String END_HOUR			 =			 request.getParameter("endhour");
		String END_MIN			 =			 request.getParameter("endmin");
		String WORK_END			 =			 END_HOUR + ":" + END_MIN;
		int af					 =			 0;
		
		
		/*
		 * 각 테이블 생성 시작
		 */
		
		af = ConfigDao.getInstance().ConfigTable(ID, ADMIN_PASSWORD, EMAIL,
				PHONE, WORK_START, WORK_END);
		System.out.println("설정 테이블 생성 완료");
		
		af = ConfigDao.getInstance().LogTable();
		System.out.println("로그 테이블 생성 완료");
		
		af = ConfigDao.getInstance().PositionTable();
		System.out.println("직책 테이블 생성 완료");
		
		af = ConfigDao.getInstance().RuleTable();
		System.out.println("권한 테이블 생성 완료");
		
		af = ConfigDao.getInstance().CarlistTable();
		System.out.println("자동차 리스트 테이블 생성 완료");
		
		af = ConfigDao.getInstance().MemberTable();
		System.out.println("멤버 테이블 생성 완료");
		
		af = ConfigDao.getInstance().DepartmentTable();
		System.out.println("부서 테이블 생성 완료");
		
		af = ConfigDao.getInstance().CarStatus();
		System.out.println("차량 상태 테이블 생성 완료");
		
		af = ConfigDao.getInstance().Attendance();
		System.out.println("출근 테이블 생성 완료");
		
		af = ConfigDao.getInstance().Message();
		System.out.println("메 시지 테이블 생성 완료");
		
		af = ConfigDao.getInstance().Schedule();
		System.out.println("스케줄 테이블 생성 완료");
		
		af = ConfigDao.getInstance().BoardContent();
		System.out.println("보드내용 테이블 생성 완료");
		
		af = ConfigDao.getInstance().Board();
		System.out.println("보드 테이블 생성 완료");
		
		af = ConfigDao.getInstance().insertBasicBoard("PUBLIC", "공지사항", "공지사항 게시판입니다. ");
		af = ConfigDao.getInstance().insertBasicBoard("PUBLIC", "자유 게시판", "자유 게시판입니다. ");
		af = ConfigDao.getInstance().insertBasicBoard("PUBLIC", "문서 게시판", "문서게시판 게시판입니다. ");
		af = ConfigDao.getInstance().insertBasicBoard("PUBLIC", "자료실 게시판", "자료실 게시판입니다. ");
		
		af = ConfigDao.getInstance().File();
		System.out.println("파일 테이블 생성 완료");
		
		af = ConfigDao.getInstance().CirclesRel();
		System.out.println("동아리 회원간 관계 테이블 생성 완료");
		
		af = ConfigDao.getInstance().PorjectRel();
		System.out.println("프로젝트 회원관 관계 테이블 생성 완료");
		
		af = ConfigDao.getInstance().Comment();
		System.out.println("코멘트 테이블 생성 완료");
		
		af = ConfigDao.getInstance().InsertAdminInfo(ID, ADMIN_PASSWORD, EMAIL,
				PHONE);
		System.out.println("관리자 정보 일반 회원 테이블 추가 완료");
		/*
		 * 각 테이블 생성 끝
		 */

		JavaScript.getIntance().MessageBackToUrl(request, response,
				"설치가 완료되었습니다. 로그인을 해주세요", "/index.jsp");

		return "void";
	}

}
