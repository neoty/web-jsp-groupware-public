package groupware.schedule.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.security.jca.GetInstance;
import groupware.commons.Controller;
import groupware.dao.DepartmentDAO;
import groupware.dao.MemberDao;
import groupware.dao.ScheduleDAO;
import groupware.dto.DepartmentDTO;
import groupware.dto.MemberDTO;
import groupware.dto.ScheduleDTO;

public class ViewController implements Controller{
	private String mReturnURL = ""; // 리턴 URL
	private String mCompDate = ""; // 받아올 date 값
	private String[] mTmpViewDate; // 날짜 폼 변경을 위한 임시변수
	private String mViewDate = ""; // 변경된 날짜 데이터 ex) 2014-01-01
	private int mMy_mb_no; // 내 멤버 번호
	private int mMb_no; // 받아온 멤버 번호
	private int mDep_no; // 부서 번호
	private int mSc_no; // 스케줄 번호
	private String mTmp_mb_no = ""; // 임시 멤버 번호
	private String mTmp_dep_no = ""; // 임시 부서 번호
	private String mTmp_sc_no = ""; // 임시 스케줄 번호
	private String mCategory1 = ""; // 카테고리 1
	private String mCategory2 = ""; // 카테고리 2
	private List<ScheduleDTO> list = null; // 스케줄 리스트
	private MemberDTO mMb_Dto = null; // 멤버 정보 저장 변수
	private Matcher mMat = null; // 패턴 체크를 위한 변수
	private boolean mB = false; // 패턴 확인을 위한 변수
	private ScheduleDTO mSc_Dto = null; // 스케줄 정보 dto
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		list = null;
		mMy_mb_no = Integer.parseInt(request.getSession().getAttribute("MB_NO").toString());
		Pattern datePtn = Pattern.compile("^20([0-9]{2})-([0-9]{1,2})-([0-9]{1,2})");
		Pattern dep_and_mb_noPtn = Pattern.compile("^[0-9]*$");
		mCompDate = request.getParameter("date");
		mTmpViewDate = mCompDate.split("-");
		
		mMat = datePtn.matcher(mCompDate);
		mB = mMat.matches();
		if (!mB) {
			
		} else {
			if (mTmpViewDate[1].length() < 2) {
				mTmpViewDate[1] = "0" + mTmpViewDate[1];
			}
			if (mTmpViewDate[2].length() < 2) {
				mTmpViewDate[2] = "0" + mTmpViewDate[2];
			}
			
			mViewDate = mTmpViewDate[0] + "-" + mTmpViewDate[1] + "-" + mTmpViewDate[2];
		}
		
		mTmp_mb_no = request.getParameter("no");
		mTmp_dep_no = request.getParameter("dep");
		mTmp_sc_no = request.getParameter("sc");
		
		if (mCompDate == null || mCompDate.equals("")) {
			// date 값이 없을 경우
			mReturnURL = "js:back:잘못된 접근입니다.";
		} else {
			// date 값이 있을 경우
			mMat = datePtn.matcher(mCompDate);
			mB = mMat.matches();
			
			if (!mB) {
				mReturnURL = "js:back:잘못된 접근입니다.";
			} else {
				if (mTmp_dep_no == null) {
					// dep 값이 없을 경우
					if (mTmp_mb_no == null) {
						// no값이 없을 경우
						mMb_no = mMy_mb_no;
						
						if (mTmp_sc_no == null) {
							// sc 값이 없을 경우
							list = ScheduleDAO.getInstance().GetTodayScList(mMb_no, 9999, mCompDate, mMb_no);
							
							if (list == null) {
								mReturnURL = "js:back:잘못된 접근입니다.";
							} else {
								mCategory1 = "개인 스케줄";
								mCategory2 = "스케줄 보기";
								mReturnURL = "View.tiles";
							}
						} else if (mTmp_sc_no.equals("")) {
							// sc 값이 ""일경우
							mReturnURL = "js:back:잘못된 접근입니다.";
						} else {
							// sc 값이 있을 경우
							mMat = dep_and_mb_noPtn.matcher(mTmp_sc_no);
							mB = mMat.matches();
							
							if (!mB) {
								mReturnURL = "js:back:잘못된 접근입니다.";
							} else {
								mSc_no = Integer.parseInt(mTmp_sc_no);
								mSc_Dto = ScheduleDAO.getInstance().GetSelSchedule(mSc_no, mMb_no);
								
								if (mSc_Dto == null) {
									System.out.println("여기");
									mReturnURL = "js:back:잘못된 접근입니다.";
								} else {
									Calendar cal = Calendar.getInstance();
									Date date = cal.getTime();
									SimpleDateFormat fom = new SimpleDateFormat("yyyy-MM-dd");
									request.setAttribute("TODAY_DATE", fom.format(date).toString());
									request.setAttribute("VIEW_TODAY_SCHEDULE", mSc_Dto);
									mCategory1 = "개인 스케줄";
									mCategory2 = "스케줄 보기";
									mReturnURL = "View.tiles";
								}
							}
						}
					} else if(mTmp_mb_no.equals("")) {
						// no값이 "" 일 경우
						mReturnURL = "js:back:잘못된 접근입니다.";
					} else {
						// no값이 있을 경우
						mMat = dep_and_mb_noPtn.matcher(mTmp_mb_no);
						mB = mMat.matches();
						
						if (!mB) {
							mReturnURL = "js:back:잘못된 접근입니다.";
						} else {
							mMb_no = Integer.parseInt(mTmp_mb_no);
							list = ScheduleDAO.getInstance().GetTodayScList(mMb_no, 9999, mCompDate, mMy_mb_no);
							
							if (mMb_no == mMy_mb_no) {
								if (list == null) {
									mReturnURL = "js:back:잘못된 접근입니다.";
								} else {
									if (mTmp_sc_no == null) {
										// sc 값이 없을 경우
										if (list == null) {
											mReturnURL = "js:back:잘못된 접근입니다.";
										} else {
											mCategory1 = "개인 스케줄";
											mCategory2 = "스케줄 보기";
											mReturnURL = "View.tiles";
										}
									} else if (mTmp_sc_no.equals("")) {
										// sc 값이 ""일경우
										mReturnURL = "js:back:잘못된 접근입니다.";
									} else {
										// sc 값이 있을 경우
										mMat = dep_and_mb_noPtn.matcher(mTmp_sc_no);
										mB = mMat.matches();
										
										if (!mB) {
											mReturnURL = "js:back:잘못된 접근입니다.";
										} else {
											mSc_no = Integer.parseInt(mTmp_sc_no);
											mSc_Dto = ScheduleDAO.getInstance().GetSelSchedule(mSc_no, mMb_no);
											
											if (mSc_Dto == null) {
												mReturnURL = "js:back:잘못된 접근입니다.";
											} else {
												Calendar cal = Calendar.getInstance();
												Date date = cal.getTime();
												SimpleDateFormat fom = new SimpleDateFormat("yyyy-MM-dd");
												request.setAttribute("TODAY_DATE", fom.format(date).toString());
												request.setAttribute("VIEW_TODAY_SCHEDULE", mSc_Dto);
												mCategory1 = "개인 스케줄";
												mCategory2 = "스케줄 보기";
												mReturnURL = "View.tiles";
											}
										}
									}
								}
							} else {
								if (list == null) {
									mReturnURL = "js:back:잘못된 접근입니다.";
								} else {
									mMb_Dto = MemberDao.getInstance().GetMbName(mMb_no, mMy_mb_no);
									
									mCategory1 = "부서원 스케줄";
									mCategory2 = mMb_Dto.getPosi_name() + " " + mMb_Dto.getMb_name();
									mReturnURL = "View.tiles";
								}
							}
						}
					}
				} else if (mTmp_dep_no.equals("")) {
					// dep 값이 "" 일 경우
					mReturnURL = "js:back:잘못된 접근입니다.";
				} else {
					// dep 값이 있을 경우
					mMat = dep_and_mb_noPtn.matcher(mTmp_dep_no);
					mB = mMat.matches();
					
					if (!mB) {
						mReturnURL = "js:back:잘못된 접근입니다.";
					} else {
						mDep_no = Integer.parseInt(mTmp_dep_no);
						list = ScheduleDAO.getInstance().GetTodayScList(mMb_no, mDep_no, mCompDate, mMy_mb_no);
						
						if (list == null) {
							mReturnURL = "js:back:잘못된 접근입니다.";
						} else {
							request.setAttribute("DEP_NO", mDep_no);
							mCategory1 = "부서 스케줄";
							mCategory2 = DepartmentDAO.getInstance().GetDepName(mMy_mb_no, mDep_no);;
							mReturnURL = "View.tiles";
						}
					}
				}
			}
		}
		
		request.setAttribute("CHECK_MB_NO", mMb_no);
		request.setAttribute("VIEW_SCHEDULE_LIST", list);
		request.setAttribute("VIEW_DATE", mViewDate);
		
		request.setAttribute("DESC_PATH1", mCategory1);
		request.setAttribute("DESC_PATH2", mCategory2);
		
		return mReturnURL;
	}
}
