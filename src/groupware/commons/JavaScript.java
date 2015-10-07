package groupware.commons;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JavaScript {
	private static JavaScript _instance;

	public static JavaScript getIntance() {
		if (_instance == null) {
			System.out.println("자바스크립트 인스턴스 생성 완료ㅋㅋㅋ");
			_instance = new JavaScript();
		}
		return _instance;
	}

	public void HistoryBack(HttpServletRequest request,
			HttpServletResponse response, String msg) throws IOException {

		response.setContentType("text/html;charset=utf-8");
		String ctx = request.getContextPath();
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('" + msg + "');");
		out.println("history.back(-1);");
		out.println("</script>");
		out.flush();
	}

	public void MessageBackToUrl(HttpServletRequest request,
			HttpServletResponse response, String msg, String uri)
			throws IOException {

		response.setContentType("text/html;charset=utf-8");
		String ctx = request.getContextPath();
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('" + msg + "');");
		out.println("location.replace('" + ctx + uri + "');");
		out.println("</script>");
		out.flush();
	}
}
