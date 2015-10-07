package groupware.member.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.MyfileRenamePolicy;

import groupware.commons.Controller;
import groupware.commons.Encryption;
import groupware.dao.MemberDao;
import groupware.dto.MemberDTO;

public class MemberShipProcController implements Controller {
	String encfilename = null;
	String uid = null;
	String password1 = null;
	String password2 = null;
	String realname = null;
	String gender = null;
	String email = null;
	String phonenumber = null;
	String birth = null;
	String position = null;
	String Tempdepartment = null;
	String passwdq = null;
	String passwda = null;
	Encryption enc = new Encryption();
	String password = "";
	int department = 0;
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		

		
		/* 아이디 중복에 대한 체크 */
		String mode = request.getParameter("mode");
		if (mode == null || mode.equals("")) {
			return "js:back:비정상적인 접근 입니다. ";
		}

		if (mode.equals("ID_CHECK")) {
			String value = request.getParameter("value");
			int af = MemberDao.getInstance().IdDupCheck(value);
			PrintWriter out = response.getWriter();
			if (af > 0) {
				out.print("다른 아이디를 사용하여 주세요. ");
				out.close();
				return "void";
			} else {
				out.print("사용 가능한 아이디 입니다. ");
				out.close();
				return "void";
			}
		}
		
		String uploadPath = "MemberImg";	// WEB-INF 직접 접근 방지 
		ServletContext ctx = request.getServletContext();
		uploadPath = ctx.getRealPath(uploadPath);
		int fileSize = 20*1024*1024; // 20MB 한번에 까지 업로드 가능 아래 TRY CATCH 해야함
		MultipartRequest mul = null;
		try {
			mul = new MultipartRequest(request, uploadPath, 20*1024*1024, "UTF-8", new MyfileRenamePolicy());
		} catch (Exception e) {
			// TODO: handle exception
			return "js:back:파일 업로드문제 (최대 용량은 20MB 입니다.)";
		}
		
		
		if (mode.equals("MODIFY")) {
			HttpSession session = request.getSession();
			int mb_no = Integer.parseInt(session.getAttribute("MB_NO").toString());
			
			password1 = mul.getParameter("passwd1");
			password2 = mul.getParameter("passwd2");
			if (!password1.equals("")) {
				if (!password1.equals(password2)) {
					return "js:back:비밀번호가 같지 않습니다. ";
				} else {
					boolean check = enc.encryption(password1);
					if (check) {
						password = enc.getPassword();
					} else {
						return "js:back:비밀번호 암호화중 에러가 발생하였습니다. ";
					}
				}
			}
			
			encfilename = mul.getFilesystemName("fileimg");
			String imgsrc = mul.getParameter("imgsrc");
			if (encfilename == null || encfilename.equals("") || encfilename.equals("null")) {
				encfilename = imgsrc;
			}	
			
			email = mul.getParameter("email");
			phonenumber = mul.getParameter("phonenumber");
			position = mul.getParameter("position");
			Tempdepartment = mul.getParameter("department");
			department = Integer.parseInt(Tempdepartment);
			passwdq = mul.getParameter("passwdq");
			passwda = mul.getParameter("passwda");
			

			String sql = "";
			int type;
			if (password.equals("")) {
				// 변경될 비밀번호가 없을때
				type = 1;
				sql = "UPDATE gw_member set mb_email = ?, mb_tel = ?, mb_password_q = ?, mb_password_a = ?, "
						+ "mb_dep_no = ?, mb_position_posi_level = ?, mb_img = ? WHERE mb_no = ?";
			} else {
				// 변경될 비밀번호가 있을때
				type = 0;
				sql = "UPDATE gw_member set mb_password = ?, mb_email= ?, mb_tel = ?, mb_password_q = ?, "
						+ "mb_password_a = ?, mb_dep_no = ?, mb_position_posi_level = ?, mb_img = ? WHERE mb_no = ?";
			}
			
			MemberDTO DTO = new MemberDTO();
			DTO.setMb_password(password);
			DTO.setMb_email(email);
			DTO.setMb_tel(phonenumber);
			DTO.setMb_password_q(passwdq);
			DTO.setMb_password_a(passwda);
			DTO.setMb_dep_no(department);
			DTO.setMb_position_posi_level(Integer.parseInt(position));
			DTO.setMb_img(encfilename);
			
			int af = MemberDao.getInstance().modifyMemberInfo(DTO, sql, type, mb_no);
			
			PrintWriter out = response.getWriter();
			out.print("<script language='javascript'>");
			out.print("alert('회원 정보가 수정되었습니다. ');");
			out.print("window.close();");
			out.print("</script>");
			out.close();
			return "void";
			
		}
		
		/*
		 * 포
		 */
		if (mode.equals("JOIN")) {
			/* 사진 업로드 처리 로직 */
			encfilename = mul.getFilesystemName("fileimg");
			
			uid = mul.getParameter("uid");
			if (uid == null || uid.equals("")) {
				return "js:back:아이디를 입력해주세요. ";
			}

			password1 = mul.getParameter("passwd1");
			if (password1 == null || password1.equals("")) {
				return "js:back:패스워드를 입력해주세요. ";
			}

			password2 = mul.getParameter("passwd2");
			if (password2 == null || password2.equals("")) {
				return "js:back:패스워드를 입력해주세요. ";
			}
			if (!(password1.equals(password2))) {
				return "js:back:패스워드가 일치하지 않습니다. ";
			}

			realname = mul.getParameter("realname");
			if (realname == null || realname.equals("")) {
				return "js:back:이름을 입력해주세요. ";
			}

			gender = mul.getParameter("gender");
			if (gender == null || gender.equals("")) {
				return "js:back:성별을 선택해주세요. ";
			}

			email = mul.getParameter("email");
			if (email == null || email.equals("")) {
				return "js:back:이메일을 입력해주세요. ";
			}

			phonenumber = mul.getParameter("phonenumber");
			if (phonenumber == null || phonenumber.equals("")) {
				return "js:back:핸드폰번호를 입력해주세요. ";
			}

			birth = mul.getParameter("birth");
			if (birth == null || birth.equals("")) {
				return "js:back:생년월일을 입력해주세요. ";
			}
			if (birth.length() > 10 || birth.length() < 10) {
				return "js:back:생년월일을 양식에 맞게 작성해주세요. ";
			}

			position = mul.getParameter("position");
			if (position == null || position.equals("")
					|| position.equals("none")) {
				return "js:back:직책을 선택해주세요. ";
			}

			Tempdepartment = mul.getParameter("department");
			if (Tempdepartment == null || Tempdepartment.equals("")) {
				return "js:back:부서를 선택해주세요. ";
			}
			if (Tempdepartment.equals("none")) {
				department = -1;
			} else {
				department = Integer.parseInt(Tempdepartment);
			}

			passwdq = mul.getParameter("passwdq");
			if (passwdq == null || passwdq.equals("")) {
				return "js:back:비밀번호찾기용 질문을 선택해주세요. ";
			}

			String passwda = mul.getParameter("passwda");
			if (passwda == null || passwda.equals("")) {
				return "js:back:비밀번호 찾기용 답을 입력해주세요. ";
			}

			/*
			 * uid password1 password2 realname gender email phonenumber birth
			 * position department passwdq passwda
			 */

			
			
			boolean check = enc.encryption(password1);
			if (check) {
				password = enc.getPassword();
			} else {
				return "js:back:비밀번호 암호화중 에러가 발생하였습니다. ";
			}

			int DupCheckId = MemberDao.getInstance().IdDupCheck(uid);
			if (DupCheckId > 0) {
				return "js:back:중복 되는 아이디가 있습니다. ";
			} else {
				
				
				SimpleDateFormat tempDate = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String ip = request.getRemoteAddr();
				String date = tempDate.format(new Date());

				MemberDTO DTO = new MemberDTO();
				DTO.setMb_id(uid);
				DTO.setMb_password(password);
				DTO.setMb_name(realname);
				DTO.setMb_email(email);
				DTO.setMb_tel(phonenumber);
				DTO.setMb_birth(birth);
				DTO.setMb_password_q(passwdq);
				DTO.setMb_password_a(passwda);
				DTO.setMb_sex(gender);
				DTO.setMb_last_ip(ip);
				DTO.setMb_last_date(date);
				DTO.setMb_login_count(1);
				DTO.setMb_certify(0); // 확인되지 않은 사용자므로 초기값은 0으로 준다. 0 == 사이트 이용
										// 불가 1 == 사이트 이용 가능
				DTO.setMb_img(encfilename);
				DTO.setMb_work_type(1);
				DTO.setMb_rule_ch_no(0);
				DTO.setMb_dep_no(department);
				DTO.setMb_position_posi_level(Integer.parseInt(position));
				DTO.setMb_use(1);

				int af = MemberDao.getInstance().MemberJoin(DTO);
				if (af < 1) {
					return "js:back:데이터베이스 오류. ";
				}
			}
		}
		
		
		return "js:url:회원가입이 정상적으로 이루어 졌습니다. 관리자의 승인후에 사이트의 이용이 가능합니다. :/";
	}
}
