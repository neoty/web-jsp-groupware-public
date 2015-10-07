package groupware.chat.controllers;

import groupware.commons.Controller;
import groupware.dao.FileDAO;
import groupware.dao.MessageDAO;
import groupware.dto.FileDTO;
import groupware.dto.MessageDTO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.MyfileRenamePolicy;

public class WriteProcController implements Controller{
	private String returnURL = "";
	private String url = "";
	private String msg = "";
	private String subject = "";
	private String receiver_Name = "";
	private String receiverNO = "";
	private String[] receiver_No;
	private String file = "";
	private String content = "";
	private MessageDTO dto = null;
	private int result = 0;
	private int mb_no;
	private String upPath = "WEB-INF/upload";
	private String uploadPath = "";    // WEB-INF 직접 접근 방지
	private ServletContext ctx = null;
	private int fileSize = 20 * 1024 * 1024; // 20MB 까지만 업로드 가능
	private MultipartRequest mul = null;
	private String oriFileName = "";
	private String encFileName = "";
	private FileDTO fDTO = null;
	private String fNumList = "";
	private int af = 0;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		
		ctx = request.getServletContext();
		uploadPath = ctx.getRealPath(upPath);
		
		try {
			mul = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new MyfileRenamePolicy());
		} catch (Exception e) {
			// TODO: handle exception
			return "js:back:파일 업로드 문제(최대 용량은 20MB 입니다.)";
		}
		
		subject = mul.getParameter("subject");
		receiver_Name = mul.getParameter("receiver-name");
		receiverNO = mul.getParameter("address-No");
		file = mul.getFilesystemName("file");
		content = mul.getParameter("me-textarea");
		content = content.replace("\r\n", "<br>");
		
		if (receiverNO.equals("") || receiverNO == null) {
			msg = "받는 사람을 입력해 주세요.";
			returnURL = "js:back:" + msg;
		} else {
			dto = new MessageDTO();
			receiver_No = receiverNO.split(",");
			
			if (subject.equals("") || subject == null || content.equals("") || content == null || file == null) {
				if (subject.equals("") || subject == null) {
					if (content.equals("") || content == null) {
						if (file == null) {
							subject = "제목 없음";
							content = "내용 없음";
							dto.setMes_file_exists(0);
						} else {
							subject = "제목 없음";
							content = "내용 없음";
							dto.setMes_file_exists(1);
						}
					} else if (file == null) {
						subject = "제목 없음";
						dto.setMes_file_exists(0);
					} else {
						subject = "제목 없음";
						dto.setMes_file_exists(1);
					}
				} else if (content.equals("") || content == null) {
					if (file == null) {
						content = "내용 없음";
						dto.setMes_file_exists(0);
					} else {
						content = "내용 없음";
						dto.setMes_file_exists(1);
					}
				} else if (file == null) {
					dto.setMes_file_exists(0);
				}
			} else {
				dto.setMes_file_exists(1);
			}
			
			dto.setMes_subject(subject);
			dto.setMes_content(content);
			dto.setMes_post_mb_no(mb_no);
			
			result = MessageDAO.getInstance().WriteMessage(receiver_No.length, receiver_No, dto);
			
			if (result == 0) {
				msg = "전송에 실패했습니다.";
				returnURL = "js:back:" + msg;
			} else {
				if (file != null) {
					fDTO = new FileDTO();
					oriFileName = mul.getOriginalFileName("file");
					encFileName = mul.getFilesystemName("file");
					fNumList = FileDAO.getInstance().getMessageNo(receiver_No.length, dto);
					
					if (receiver_No.length < 2) {
						fDTO.setFile_number(Integer.parseInt(fNumList));
					} else {
						fDTO.setFile_number_list(fNumList);
					}
					
					fDTO.setFile_part("CHAT");
					fDTO.setFile_relname1(oriFileName);
					fDTO.setFile_encname1(encFileName);
					af = FileDAO.getInstance().allFileUpload(fileSplit(fNumList), fDTO);
					System.out.println("파일 업로드 : " + af);
				}
				
				url = "/Chat/index.chat";
				msg = receiver_Name + " 님께 전송 되었습니다.";
				returnURL = "js:url:" + msg + ":" + url;
			}
		}
		
		return returnURL;
	}
	
	private int fileSplit(String str) {
		int length = 0;
		System.out.println("fNumList : " + str);
		String[] splitStr = str.split(",");
		length = splitStr.length;
		
		return length;
	}
}
