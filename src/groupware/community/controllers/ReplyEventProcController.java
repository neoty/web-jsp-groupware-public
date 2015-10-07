package groupware.community.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import groupware.commons.Controller;
import groupware.dao.ReplyDAO;
import groupware.dto.ReplyDTO;

public class ReplyEventProcController implements Controller {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub

		String mode = request.getParameter("mode");
		if (!(mode.equals("CREATE") || mode.equals("DELETE"))) {
			return "js:back:잘못된 접근입니다. ";
		}
		String type = request.getParameter("type");
		int bc_no = Integer.parseInt(request.getParameter("bc_no"));
		
		int code = Integer.parseInt(request.getParameter("code"));
		HttpSession session = request.getSession();
		int mb_no = Integer.parseInt(session.getAttribute("MB_NO")
				.toString());
		if (mode.equals("CREATE")) {
			String reply_content = request.getParameter("reply_content");
			if (reply_content == null || reply_content.equals("")) {
				return "js:back:내용을 입력해주세요. ";
			} else {
				ReplyDTO DTO = new ReplyDTO();
				DTO.setRe_mb_no(mb_no);
				DTO.setRe_bc_no(bc_no);
				DTO.setRe_content(reply_content);

				int af = ReplyDAO.getInstance().updateReplyCountAdd(bc_no);
				af = ReplyDAO.getInstance().insertReply(DTO);
				if (af > 0) {
					return "js:url:코멘트가 등록되었습니다. :/Community/View.community?type="
							+ type + "&code=" + code + "&bc_no=" + bc_no + "";
				} else {
					return "js:back:에러 에러입니다. ";
				}
			}

		}

		if (mode.equals("DELETE")) {
			int re_no = Integer.parseInt(request.getParameter("re_no"));
			int af = ReplyDAO.getInstance().updateReplyCountMinus(bc_no);
			af = ReplyDAO.getInstance().deleteReply(re_no, mb_no);
			if (af > 0) {
				return "js:url:코멘트가 삭제되었습니다. :/Community/View.community?type="
						+ type + "&code=" + code + "&bc_no=" + bc_no + "";
			} else {
				return "js:back:자신의 코멘트가 삭제할수 있습니다. ";
			}
		}

		return "js:back:에러입니다. ";
	}

}
