package groupware.schedule.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Controller;
import groupware.dao.MemberDao;
import groupware.dao.ScheduleDAO;
import groupware.dto.ScheduleDTO;
public class WriteProcController implements Controller{
	private int mMb_no = 0;// 멤버 번호
	private int mDep_no = 0;// 멤버의 부서 번호
	private int mAf = 0;// 쿼리 성공 여부 반환 EX) 0:실패 그외:성공
	private String mSubject = "";// 스케줄 제목
	private String mContent = "";// 스케줄 내용
	private String mStartDate = "";// 스케줄 시작 시간
	private String mEndDate = "";// 스케줄 종료 시간
	private int mProgressBar = 0;// 스케줄 진행 정도
	private ScheduleDTO mDto = null;
	private int mDep_sc;// 팀 스케줄 기록

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		mMb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		mDep_no = MemberDao.getInstance().GetDepNo(mMb_no);
		
		mSubject = request.getParameter("subject");
		mContent = request.getParameter("content");
		mContent = mContent.replace("\r\n", "\r\n<br>");
		mStartDate = request.getParameter("start_date");
		mEndDate = request.getParameter("end_date");
		mProgressBar = Integer.parseInt(request.getParameter("progress"));
		mDep_sc = Integer.parseInt(request.getParameter("department").toString());
		
		mDto = new ScheduleDTO();
		mDto.setSc_member_no(mMb_no);
		mDto.setSc_dep_no(mDep_no);
		mDto.setSc_progress(mProgressBar);
		mDto.setSc_subject(mSubject);
		mDto.setSc_content(mContent);
		mDto.setSc_s_date(mStartDate);
		mDto.setSc_e_date(mEndDate);
		mDto.setSc_team(mDep_sc);
		
		mAf = ScheduleDAO.getInstance().WriteSchedule(mDto);
		
		PrintWriter out = response.getWriter();
		if (mAf == 0) {
			out.print("스케줄 등록에 실패했습니다.");
			out.close();
		} else {
			out.print("등록 되었습니다.");
			out.close();
		}
		
		return "void";
	}
}