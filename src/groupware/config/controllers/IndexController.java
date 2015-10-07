package groupware.config.controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.security.provider.PolicyParser.GrantEntry;
import groupware.commons.Controller;
import groupware.dao.ConfigDao;
import groupware.dto.ConfigDTO;

public class IndexController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		
		/* 서버 상태 가져오기  */
		ConfigDTO status = ConfigDao.getInstance().serverStatus();
		
		/* 그룹웨어 생성 날짜  */
		request.setAttribute("CREATE_TIME", status.getCf_create_date());
		
		/* 관리자 아이디 */
		request.setAttribute("ADMIN_ID", status.getCf_id());
		
		/* 관리자 이메일  */
		request.setAttribute("EMAIL", status.getCf_email());
		
		/* 관리자 전화번호 */
		request.setAttribute("PHONE", status.getCf_phone());
		
		/* 그룹웨어 정지여부  */
		request.setAttribute("GROUPWARE_STOP", status.getCf_stop());
		
		/*  그룹웨어 정지 제목 */
		request.setAttribute("GROUPWARE_STOP_TITLE", status.getCf_stop_title());

		/* 컨텍스트명 */
		request.setAttribute("CONTEXT_NAME", request.getContextPath());
		
		/* 서버 아이피  */
		request.setAttribute("SERVER_IP", InetAddress.getLocalHost().getHostAddress());
		
		/*  출퇴근 허용 아이피(단일) */
		if (status.getCf_grant_ip() == null || status.getCf_grant_ip().equals("")) {
			request.setAttribute("GRANT_IP1", "");
			request.setAttribute("GRANT_IP2", "");
			request.setAttribute("GRANT_IP3", "");
			request.setAttribute("GRANT_IP4", "");
		} else {
			String[] grant_ip = status.getCf_grant_ip().split("\\.");
			request.setAttribute("GRANT_IP1", grant_ip[0]);
			request.setAttribute("GRANT_IP2", grant_ip[1]);
			request.setAttribute("GRANT_IP3", grant_ip[2]);
			request.setAttribute("GRANT_IP4", grant_ip[3]);
		}
		
		/* 출퇴근 허용 아이피(범위형) */
		if (status.getCf_band_ip() == null || status.getCf_band_ip().equals("")) {
			request.setAttribute("BAND_IP1", "");
			request.setAttribute("BAND_IP2", "");
			request.setAttribute("BAND_IP3", "");
			request.setAttribute("BAND_IP4", "");
			request.setAttribute("BAND_IP5", "");
			request.setAttribute("BAND_IP6", "");
			request.setAttribute("BAND_IP7", "");
			request.setAttribute("BAND_IP8", "");
		} else {
			String[] band_ip = status.getCf_band_ip().split(" - ");
			String[] start_ip = band_ip[0].split("\\.");
			String[] end_ip = band_ip[1].split("\\.");
			request.setAttribute("BAND_IP1", start_ip[0]);
			request.setAttribute("BAND_IP2", start_ip[1]);
			request.setAttribute("BAND_IP3", start_ip[2]);
			request.setAttribute("BAND_IP4", start_ip[3]);
			request.setAttribute("BAND_IP5", end_ip[0]);
			request.setAttribute("BAND_IP6", end_ip[1]);
			request.setAttribute("BAND_IP7", end_ip[2]);
			request.setAttribute("BAND_IP8", end_ip[3]);
		}
		
		/* 회원 가입 가능 여부 설정  */
		request.setAttribute("GROUPWARE_MEMBERSHIP", status.getCf_use_membership());
		
		/* 출근 시간  */
		String[] s_time = status.getCf_work_start().split(":");
		request.setAttribute("S_H_TIME", s_time[0]);
		request.setAttribute("S_M_TIME", s_time[1]);
		
		/* 퇴근 시간 */
		String[] e_time = status.getCf_work_end().split(":");
		request.setAttribute("E_H_TIME", e_time[0]);
		request.setAttribute("E_M_TIME", e_time[1]);
		
		/* 각종 카운터 구하기 */
		/*
		 *  1 = 회원수
		 *  2 = 부서수 
		 *  3 = CIRCLES 보드 수
		 *  4 = PROJECT 보드 수
		 *  5 = 자동차수 
		 *  6 = 파일수
		 */
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map = ConfigDao.getInstance().serverCount();
		request.setAttribute("MEMBER_COUNT", map.get(1));
		request.setAttribute("DEPARTMENT_COUNT", map.get(2));
		request.setAttribute("CIRCLES_COUNT", map.get(3));
		request.setAttribute("PROJECT_COUNT", map.get(4));
		request.setAttribute("CAR_COUNT", map.get(5));
		request.setAttribute("FILE_COUNT", map.get(6));
				
		
		return "index.tiles";
	}
}
