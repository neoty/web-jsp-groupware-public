package groupware.work.controllers;

import groupware.commons.Controller;
import groupware.dao.FileDAO;
import groupware.dao.WorkFlowDAO;
import groupware.dto.FileDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.MyfileRenamePolicy;

public class WorkWriteProcController implements Controller{
	private String upPath = "WEB-INF/upload";
	private String uploadPath = "";
	private ServletContext ctx;
	private int fileSize = 20*1024*1024;
	private MultipartRequest mul = null;
	private int mb_no = 0;
	private int work_doc_no = 0;
	private String doc_no = "";
	private String oriDocName = "";
	private String encDocName = "";
	private String oriFileName = "";
	private String encFileName = "";
	private int mAf = 0;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		ctx = request.getServletContext();
		uploadPath = ctx.getRealPath(upPath);
		try {
			mul = new MultipartRequest(request, uploadPath, fileSize, "UTF-8" , new MyfileRenamePolicy());
		} catch (Exception e) {
			return "js:back:파일 업로드문제 (최대 용량은 20MB 입니다.)";
		}
		
		mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		work_doc_no = Integer.parseInt(mul.getParameter("work_doc_no"));
		doc_no = mul.getParameter("doc_no");
		
		oriDocName = mul.getOriginalFileName("approve-doc");
		oriFileName = mul.getOriginalFileName("attach-file");
		encDocName = mul.getFilesystemName("approve-doc");
		encFileName = mul.getFilesystemName("attach-file");
		
		FileDTO fDTO = new FileDTO();
		fDTO.setFile_part("WORK");
		fDTO.setFile_number(work_doc_no);
		fDTO.setFile_relname1(oriDocName);
		fDTO.setFile_encname1(encDocName);
		fDTO.setFile_relname2(oriFileName);
		fDTO.setFile_encname2(encFileName);
		FileDAO.getInstance().allFileUpload(0, fDTO);
		
		mAf = WorkFlowDAO.getInstance().writeAppoveDoc(work_doc_no, mb_no, doc_no);
		
		if (mAf != 0) {
			PrintWriter out=response.getWriter(); 
			response.resetBuffer(); 
			response.setContentType("text/html;charset=utf-8"); 
			out.println("<script language='javascript'>"); 
			out.print("window.close();"); 
			out.println("parent.opener.location.reload();"); 
			out.println("</script>"); 
			response.flushBuffer(); 
		}
		
		return "void";
	}

}
