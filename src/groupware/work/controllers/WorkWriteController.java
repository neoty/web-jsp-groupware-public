package groupware.work.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.DepartmentDAO;
import groupware.dao.MemberDao;
import groupware.dao.WorkFlowDAO;
import groupware.dto.DepartmentDTO;
import groupware.dto.MemberDTO;
import groupware.dto.WorkFlowDTO;

public class WorkWriteController implements Controller{
	private int control = 0;
	private int mb_no = 0; 
	private String tmp = "";
	private String returnURL = "";
	private MemberDTO dto = null;
	private List<DepartmentDTO> dep_List = null;
	private List<MemberDTO> mb_List = null;
	private String mDate = "";
	private String mSubject = "";
	private String mSel_tmp = "";
	private String[] mSel_list_tmp;
	private int approval1 = 0;
	private int approval2 = 0;
	private int approval3 = 0;
	private WorkFlowDTO mWdto = null;
	private int mAf = 0;
	private String mDep_name = "";
	private int doc_no = 0; // 결재 문서의 마지막 번호
	private String mDoc_no = ""; // 결재 문서의 이름
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		tmp = request.getParameter("control");
		mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		
		// 작성 페이지 구분
		if (tmp != null) {
			control = 1;
		} else {
			control = 0;
		}
		
		if (control == 0) {
			// 결재자 등록 페이지로 이동
			dto = new MemberDTO();
			dto = MemberDao.getInstance().getMemberInfoAll(mb_no);
			
			dep_List = new ArrayList<DepartmentDTO>();
			dep_List = DepartmentDAO.getInstance().GetSimpleDepList();
			mb_List = new ArrayList<MemberDTO>();
			mb_List = MemberDao.getInstance().GetSimpleMemberList();
			
			Calendar cal = Calendar.getInstance();
			String tmpMonth = (cal.get(Calendar.MONTH) + 1) + "";
			if (tmpMonth.length() < 2) {
				tmpMonth = "0" + tmpMonth;
			}
			
			mDate = cal.get(Calendar.YEAR) + "-" + tmpMonth + "-" + cal.get(Calendar.DATE);
			
			request.setAttribute("DATE", mDate);
			request.setAttribute("MY_INFO", dto);
			request.setAttribute("DEP_LIST", dep_List);
			request.setAttribute("MB_LIST", mb_List);
			returnURL = "/WEB-INF/Work/WorkWrite.jsp";
		} else if (control == 1) {
			// 문서 등록 페이지로 이동
			mSubject = request.getParameter("subject");
			mSel_tmp = request.getParameter("sel_approval1");
			if (!("none".equals(mSel_tmp)) || mSel_tmp != null || !"".equals("")) {
				mSel_list_tmp = mSel_tmp.split(":");
				approval1 = Integer.parseInt(mSel_list_tmp[1]);
			}
			mSel_tmp = request.getParameter("sel_approval2");
			if (!("none".equals(mSel_tmp))) {
				mSel_list_tmp = mSel_tmp.split(":");
				approval2 = Integer.parseInt(mSel_list_tmp[1]);
			}
			mSel_tmp = request.getParameter("sel_approval3");
			if (!("none".equals(mSel_tmp))) {
				mSel_list_tmp = mSel_tmp.split(":");
				approval3 = Integer.parseInt(mSel_list_tmp[1]);
			}
			
			dto = new MemberDTO();
			dto = MemberDao.getInstance().getMemberInfoAll(mb_no);
			
			mDep_name = DepartmentDAO.getInstance().GetDepName(mb_no, dto.getMb_dep_no());
			Calendar cal = Calendar.getInstance();

			mDoc_no = mDep_name + "-" + cal.get(Calendar.YEAR) + "-";
			doc_no = WorkFlowDAO.getInstance().getDocNo(mDoc_no);
			doc_no = doc_no + 1;
			mDoc_no = mDep_name + "-" + cal.get(Calendar.YEAR) + "-" + doc_no;
			
			mWdto = new WorkFlowDTO();
			mWdto.setWf_post_mb(mb_no);
			mWdto.setWf_status(0);
			mWdto.setWf_doc_no(mDoc_no);
			mWdto.setWf_mb_first(approval1);
			if (approval2 != 0) {
				mWdto.setWf_mb_second(approval2);
			}
			if (approval3 != 0) {
				mWdto.setWf_mb_third(approval3);
			}
			mWdto.setWf_subject(mSubject);
			
			mAf = WorkFlowDAO.getInstance().inputDocNo(mWdto);
			
			if (mAf != 0) {
				request.setAttribute("WORK_DOC_NO", mAf);
				request.setAttribute("DOC_NO", mDoc_no);
				returnURL = "/WEB-INF/Work/WorkWrite2.jsp";
			}
			
		} else {
			// 에러
		}
		
		
		return returnURL;
	}
}
