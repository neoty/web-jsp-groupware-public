package groupware.work.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.WorkFlowDAO;

public class WorkConfirmProcController implements Controller{
	private int mb_no = 0;
	private int work_status = 0;
	private int work_no = 0;
	private String confirm_note = "";
	private int mAf = 0;
	private int confirm_no = 0;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		work_status = Integer.parseInt(request.getParameter("select-confirm"));
		work_no = Integer.parseInt(request.getParameter("wf-no"));
		confirm_note = request.getParameter("confirm-note");
		confirm_no = Integer.parseInt(request.getParameter("confirm-no"));
		
		if (confirm_note.equals("") || confirm_note == null) {
			confirm_note = null;
		}
		
		
		
		if (work_status != 0 && work_no != 0) {
			mAf = WorkFlowDAO.getInstance().confirmDocInfo(mb_no, work_status, work_no, confirm_note, confirm_no);
		}
		
		if (mAf != 0) {
			PrintWriter out=response.getWriter(); 
			response.resetBuffer(); 
			response.setContentType("text/html;charset=utf-8"); 
			out.println("<script language='javascript'>"); 
			out.println("alert('결재 되었습니다.');"); 
			out.println("window.close();"); 
			out.println("parent.opener.location.reload();"); 
			out.println("</script>"); 
			response.flushBuffer(); 
		}
		
		return "void";
	}
}
