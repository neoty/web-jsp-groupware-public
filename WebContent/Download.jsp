<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="groupware.commons.JavaScript"%>
<%@page import="groupware.dao.FileDAO"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="groupware.commons.DownloadController" %>
<%@ page import="java.net.URLEncoder" %>
<%
	response.reset();
	String culumn = request.getParameter("culumn");
	if(!(culumn.equals("first") || culumn.equals("second"))) {
		out.print("<script>");
		out.print("alert('정상적으로 이용해주세요. ')");
		out.print("</script>");
		out.close();
		return;
	}
	String realName = null;
	String file = request.getParameter("file");
	if(file == null || file.equals("")) {
		out.print("<script>");
		out.print("alert('해당파일은 존재 하지 않습니다. ')");
		out.print("</script>");
		out.close();
		return;
	} else {
		realName = FileDAO.getInstance().getFileName(file, culumn);
		realName = URLEncoder.encode(realName, "UTF-8");
		realName = realName.replaceAll("\\+", "%20");
		if (realName == null) {
			out.print("<script>");
			out.print("alert('해당파일은 존재 하지 않습니다. ')");
			out.print("</script>");
			out.close();
			return;
		}
	}
	
	String uploadPath = "WEB-INF/upload"; // WEB-INF 직접 접근 방지
	ServletContext ctx = request.getServletContext();
	uploadPath = ctx.getRealPath(uploadPath);

	OutputStream os = null;
	File fp = new File(uploadPath + "\\" + file);
	FileInputStream is = null;
	try {
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+realName+";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		// 오류로 인해 사용 안함.
		// DownloadController.fStream(file, response.getOutputStream() ,uploadPath, request, response);
		
		is = new FileInputStream(fp);
		os = response.getOutputStream();
		byte[] data = new byte[1024];
		int len = -1;
		while ((len = is.read(data)) != -1) {
		 	os.write(data, 0, len);
		}
		os.flush();
		os.close();
	} catch(Exception e){
		
	} finally {
		try { 
			if (is != null) is.close(); 
		} catch (Exception ex) { 
			
		} finally {
			is = null;
		}
	}
%>