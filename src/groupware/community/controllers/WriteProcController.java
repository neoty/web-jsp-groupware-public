package groupware.community.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import groupware.commons.Controller;
import groupware.commons.JavaScript;
import groupware.dao.CommunityDAO;
import groupware.dao.FileDAO;
import groupware.dto.BoardContentDTO;
import groupware.dto.FileDTO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.*;;
public class WriteProcController implements Controller {
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		String uploadPath = "WEB-INF/upload";	// WEB-INF 직접 접근 방지 
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
		
		String subject = mul.getParameter("subject");
		if (subject == null || subject.equals("")) {
			return "js:back:제목을 써주세요. ";
		}
		
		String content = mul.getParameter("content");
		content = content.replace("<", "&lt");
		content = content.replace("\r\n", "<br>");
		content = content.replace("<script>", "");
		content = content.replace("<frame>", "");
		content = content.replace("<iframe>", "");
		content = content.replace("<applet>", "");
		content = content.replace("<object>", "");
		if (content == null || content.equals("")) {
			return "js:back:내용을 써주세요. ";
		}
		
		String type = mul.getParameter("type");
		if (type == null || type.equals("")) {
			return "js:back:비정상적인 접근입니다. ";
		}
		String code = mul.getParameter("code");
		if (code == null || code.equals("")) {
			return "js:back:비정상적인 접근입니다. ";
		}
		
		HttpSession session = request.getSession();
		String mb_no = session.getAttribute("MB_NO").toString();
		
		
		String tempsecret = mul.getParameter("secret");
		int secret = 0;
		if (tempsecret != null && tempsecret.equals("1")) {
			secret = 1;
		}
		
		String tempnotice = mul.getParameter("notice");
		int notice = 0;
		if (tempnotice != null && tempnotice.equals("1")) {
			notice = 1;
		}
		
		String oriFileName1 = mul.getOriginalFileName("file1");
		System.out.println("ori1"+oriFileName1);
		String oriFileName2 = mul.getOriginalFileName("file2");
		System.out.println("ori2"+oriFileName2);
		String encFileName1 = mul.getFilesystemName("file1");
		System.out.println("enc1"+encFileName1);
		String encFileName2 = mul.getFilesystemName("file2");
		System.out.println("enc2"+encFileName2);
		
		
		BoardContentDTO DTO = new BoardContentDTO();
		DTO.setMb_no(Integer.parseInt(mb_no));
		DTO.setBc_type(type);
		DTO.setBc_code(Integer.parseInt(code));
		DTO.setBc_subject(subject);
		DTO.setBc_content(content);
		DTO.setBc_notice(notice);
		DTO.setBc_secret(secret);
		DTO.setBc_read_count(0);
		DTO.setBc_reply_count(0);
		DTO.setBc_file_exists(0);
		if (oriFileName1 != null || oriFileName2 != null) {
			DTO.setBc_file_exists(1);
		}
		
		int af = CommunityDAO.getInstance().write(DTO);
		/*
		 * 이부분에서 보드 내용 저장하기
		 */
		
		/*
		 * 이부분에서 보드 번호 얻기
		 */
		int num = FileDAO.getInstance().getCommunityBoardNum(DTO);
		System.out.println("들어간 파일 넘어 얻기"+num);
		/*
		 * !! 주의 이미 MultipartRequest 를 쓴 이상 부터 파일이 저장되어 올라감
		 */
		FileDTO fDTO = new FileDTO();
		if (oriFileName1 != null || oriFileName2 != null) {
			fDTO.setFile_part("COMMUNITY");
			fDTO.setFile_number(num);
			fDTO.setFile_relname1(oriFileName1);
			fDTO.setFile_encname1(encFileName1);
			fDTO.setFile_relname2(oriFileName2);
			fDTO.setFile_encname2(encFileName2);
			FileDAO.getInstance().allFileUpload(0, fDTO);
		} 
		
		
		String backurl = "/Community/index.community?type="+type+"&code="+code;
		return "js:url:글쓰기가 완료되었습니다:"+backurl;
	}

}
