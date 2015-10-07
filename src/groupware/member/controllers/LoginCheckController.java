package groupware.member.controllers;

import groupware.commons.Common;
import groupware.commons.Controller;
import groupware.commons.Encryption;
import groupware.commons.JavaScript;
import groupware.dao.ConfigDao;
import groupware.dao.MemberDao;
import groupware.dto.ConfigDTO;
import groupware.dto.MemberDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import sun.print.resources.serviceui;

public class LoginCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String id = request.getParameter("MB_ID");
		if (id == null || id.equals("")) {
			return "js:back:아이디를 입력해주세요. ";
		}
		String password = request.getParameter("MB_PASSWORD");
		if (password == null || password.equals("")) {
			return "js:back:비밀번호를 입력해주세요. ";
		}
		
		Encryption enc = new Encryption();
		enc.encryption(password);
		password = enc.getPassword();
		int af = MemberDao.getInstance().LoginCheck(id, password);
		if (af == 0) {
			return "js:back:아이디 혹은 비밀번호가 틀립니다. ";
		} else if (af == 1) {
			
			MemberDTO memInfo = new MemberDTO();
			memInfo = MemberDao.getInstance().GetMemberInfo(id);
			int mb_no = memInfo.getMb_no();
			int mb_work_type = memInfo.getMb_work_type();
			int mb_certify = memInfo.getMb_certify();
			String mb_name = memInfo.getMb_name();
			if (mb_certify == 0) {
				return "js:back:아직 승인되지 않았습니다. ";
			} 
			
			
			String ip = request.getRemoteAddr();
			String date = Common.getIntance().Time_yyyy_mm_dd_hh_mm_ss();
			
			af = MemberDao.getInstance().MemberLoginUpdate(mb_no, ip, date);
			
			System.out.println(mb_no);
			System.out.println(mb_work_type);
			

			if (mb_work_type == 1) {
				ConfigDTO DTO = ConfigDao.getInstance().groupUseStatus();
				
				if (DTO.getCf_stop() == 1) {
					/* 사이트 정지여부가 1일 경우 정지 타이틀을 리턴 시킨다. */
					return "js:back:"+DTO.getCf_stop_title();
				}
				session.setAttribute("MB_NO", mb_no);
				session.setAttribute("MB_WORK_TYPE", mb_work_type);
				session.setAttribute("MB_NAME", mb_name);
				return "/Main/index.main";
			}  else if (mb_work_type == 0) {
				//관리자로 인식한다.
				session.setAttribute("MB_NO", mb_no);
				session.setAttribute("MB_WORK_TYPE", mb_work_type);
				session.setAttribute("MB_NAME", mb_name);
				return "js:url:관리자 로그인이 되었습니다. :/Main/index.main";
			}
						
		} else {
			return "js:back:에러 입니다. ";
		}

		return "js:url:로그인 되었습니다.:/Main/index.main";
	}

}
