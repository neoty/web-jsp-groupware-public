package groupware.work.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.WorkFlowDAO;

public class WorkDeleteProcController implements Controller {
	private int mb_no = 0;
	private int work_doc_no = 0; // 해당 문서의 db 번호
	private String doc_no = ""; // 문서 번호 풀네임
	private int mAf = 0;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		work_doc_no = Integer.parseInt(request.getParameter("work_doc_no"));
		doc_no = request.getParameter("doc_no");
		
		mAf = WorkFlowDAO.getInstance().delDocInfo(mb_no, work_doc_no, doc_no);
		
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
