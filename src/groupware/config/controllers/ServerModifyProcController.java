package groupware.config.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.commons.JavaScript;
import groupware.dao.ConfigDao;
import groupware.dto.ConfigDTO;

public class ServerModifyProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String stop = request.getParameter("stop");
		String stop_title = request.getParameter("stop-title");
		
		
		
		String allowip1 = request.getParameter("allowip1");
		String allowip2 = request.getParameter("allowip2");
		String allowip3 = request.getParameter("allowip3");
		String allowip4 = request.getParameter("allowip4");
		StringBuilder allowip = new StringBuilder("");
		if (!allowip1.equals("") && !allowip2.equals("") &&
				!allowip3.equals("") && !allowip4.equals("")) {
			allowip.append(allowip1);
			allowip.append(".");
			allowip.append(allowip2);
			allowip.append(".");
			allowip.append(allowip3);
			allowip.append(".");
			allowip.append(allowip4);
		}

		
		
		String bandip1 = request.getParameter("bandip1");
		String bandip2 = request.getParameter("bandip2");
		String bandip3 = request.getParameter("bandip3");
		String bandip4 = request.getParameter("bandip4");
		String bandip5 = request.getParameter("bandip5");
		String bandip6 = request.getParameter("bandip6");
		String bandip7 = request.getParameter("bandip7");
		String bandip8 = request.getParameter("bandip8");
		StringBuilder bandip = new StringBuilder("");
		if (!bandip1.equals("") && !bandip2.equals("") && !bandip3.equals("") 
				&& !bandip4.equals("") && !bandip5.equals("") && !bandip6.equals("")
				&& !bandip7.equals("") && !bandip8.equals("")) {
			bandip.append(bandip1);
			bandip.append(".");
			bandip.append(bandip2);
			bandip.append(".");
			bandip.append(bandip3);
			bandip.append(".");
			bandip.append(bandip4);
			bandip.append(" - ");
			bandip.append(bandip5);
			bandip.append(".");
			bandip.append(bandip6);
			bandip.append(".");
			bandip.append(bandip7);
			bandip.append(".");
			bandip.append(bandip8);
		}
		
		
		
		String membership = request.getParameter("membership");

		
		
		String starthour = request.getParameter("starthour");
		String startmin = request.getParameter("startmin");
		StringBuilder start = new StringBuilder("");
		start.append(starthour);
		start.append(":");
		start.append(startmin);
		
		
		
		String endhour = request.getParameter("endhour");
		String endmin = request.getParameter("endmin");
		StringBuilder end = new StringBuilder("");
		end.append(endhour);
		end.append(":");
		end.append(endmin);
		
		
		ConfigDTO DTO = new ConfigDTO();
		DTO.setCf_email(email);
		DTO.setCf_phone(phone);
		DTO.setCf_stop(Integer.parseInt(stop));
		DTO.setCf_stop_title(stop_title);
		DTO.setCf_grant_ip(allowip.toString());
		DTO.setCf_band_ip(bandip.toString());
		DTO.setCf_use_membership(Integer.parseInt(membership));
		DTO.setCf_work_start(start.toString());
		DTO.setCf_work_end(end.toString());
		
		
		int af = ConfigDao.getInstance().updateConfig(DTO);
		if (af > 0) {
			JavaScript.getIntance().HistoryBack(request, response, "정상적으로 수정되었습니다. ");
		} else {
			JavaScript.getIntance().HistoryBack(request, response, "수정중 오류가 발생하였습니다. ");
		}
		
		System.out.println("===========================================");
		System.out.println("이메일"+email);
		System.out.println("핸드폰"+phone);
		System.out.println("정지여부"+stop);
		System.out.println("정지 제목"+ stop_title);
		System.out.println("아이피"+ allowip);
		System.out.println("대역아이피"+ bandip);
		System.out.println("멤버쉽 가능 여부" + membership);
		System.out.println("s1" + start);
		System.out.println("s2" + end);
		System.out.println("===========================================");
		
		return "void";
	}

}
