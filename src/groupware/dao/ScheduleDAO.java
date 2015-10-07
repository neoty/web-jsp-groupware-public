package groupware.dao;

import groupware.dto.ScheduleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO extends CommonDAO {
	private static ScheduleDAO _instance;
	private int mAf = 0;
	private ResultSet rs = null;
	private SimpleDateFormat format = null;
	
	public static ScheduleDAO getInstance() {
		if (_instance == null) {
			_instance = new ScheduleDAO();
			System.out.println("스케줄 DAO 최초 생성");
		}
		return _instance;
	}
	
	/**
	 * 스케줄 등록
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int WriteSchedule(ScheduleDTO dto) throws SQLException {
		DBConnection();
		mAf = 0;
		String sql = "INSERT INTO gw_schedule "
				+ "( "
				+ "sc_member_no, "
				+ "sc_dep_no, "
				+ "sc_progress, "
				+ "sc_subject, "
				+ "sc_content, "
				+ "sc_s_date, "
				+ "sc_e_date, "
				+ "sc_team "
				+ " ) "
				+ "VALUES "
				+ "( "
				+ "?, ?, ?, ?, ?, ?, ?, ?"
				+ " )";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, dto.getSc_member_no());
		psmt.setInt(2, dto.getSc_dep_no());
		psmt.setInt(3, dto.getSc_progress());
		psmt.setString(4, dto.getSc_subject());
		psmt.setString(5, dto.getSc_content());
		psmt.setString(6, dto.getSc_s_date());
		psmt.setString(7, dto.getSc_e_date());
		psmt.setInt(8, dto.getSc_team());
		
		mAf = setQuery(psmt);
		
		DBClose();
		return mAf;
	}
	
	/**
	 * 스케줄 변경
	 * @param dto
	 * @param check : 1 - 내용 전부 수정
	 * 				  2 - 프로그래스만 수정
	 * @return
	 * @throws SQLException
	 */
	public int ModifySchedule(ScheduleDTO dto, int check) throws SQLException {
		DBConnection();
		mAf = 0;
		if (check == 1) {
			String sql = "UPDATE gw_schedule "
					+ "SET "
					+ "sc_progress = ?, "
					+ "sc_subject = ?, "
					+ "sc_content = ?, "
					+ "sc_s_date = ?, "
					+ "sc_e_date = ? "
					+ "WHERE "
					+ "sc_no = ? "
					+ "AND sc_dep_no = ?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getSc_progress());
			psmt.setString(2, dto.getSc_subject());
			psmt.setString(3, dto.getSc_content());
			psmt.setString(4, dto.getSc_s_date());
			psmt.setString(5, dto.getSc_e_date());
			psmt.setInt(6, dto.getSc_no());
			psmt.setInt(7, dto.getSc_dep_no());
		} else if (check == 2) {
			String sql = "UPDATE gw_schedule "
					+ "SET "
					+ "sc_progress = ? "
					+ "WHERE "
					+ "sc_no = ? "
					+ "AND sc_dep_no = ?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getSc_progress());
			psmt.setInt(2, dto.getSc_no());
			psmt.setInt(3, dto.getSc_dep_no());
		}
		
		
		mAf = setQuery(psmt);
		
		DBClose();
		return mAf;
	}
	
	/**
	 * 캘린더 표시 개인 스케줄 로드
	 * @return 스케줄 list
	 * @throws SQLException
	 */
	public List<ScheduleDTO> ReadMonthSchedule(int mb_no, String date, int e_day) throws SQLException {
		DBConnection();
		List<ScheduleDTO> list = new ArrayList<ScheduleDTO>();
		ScheduleDTO dto = null;
		String subStr = "";
		String tmpStr = "";
		String perDate = date + "-%%";
		String sql = "SELECT sc_subject,"
				+ "sc_s_date, "
				+ "sc_e_date "
				+ "FROM gw_schedule "
				+ "WHERE sc_member_no = ? "
				+ "AND sc_team = 0 "
				+ "AND (sc_s_date Like ? "
				+ "OR (sc_s_date <= ? AND sc_e_date >= ?))";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setString(2, perDate);
		psmt.setString(3, date);
		psmt.setString(4, date);
		
		System.out.println(psmt.toString());
		rs = getQuery(psmt);
		
		if (rs.next()) {
			do {
				dto = new ScheduleDTO();
				dto.setSc_subject(rs.getString("sc_subject"));
				tmpStr = rs.getString("sc_s_date");
				tmpStr = tmpStr.replace("-0", "");
				tmpStr = tmpStr.replace("-", "");
				dto.setSc_s_date(tmpStr);
				tmpStr = rs.getString("sc_e_date");
				tmpStr = tmpStr.replace("-0", "");
				tmpStr = tmpStr.replace("-", "");
				dto.setSc_e_date(tmpStr);
				
//				tmpStr = rs.getString("sc_s_date");
//				subStr = tmpStr.substring(0, 6);
//				if (subStr.equals(date)) {
//					dto.setSc_s_day(Integer.parseInt(tmpStr.substring(8, 9)));
//				} else {
//					dto.setSc_s_day(1);
//				}
//				
//				tmpStr = rs.getString("sc_e_date");
//				subStr = tmpStr.substring(0, 6);
//				
//				if (subStr.equals(date)) {
//					dto.setSc_e_day(Integer.parseInt(tmpStr.substring(8, 9)));
//				} else {
//					dto.setSc_e_day(e_day);
//				}
				
				list.add(dto);
			} while (rs.next());
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 캘린더 표시 부서 스케줄 로드
	 * @return 스케줄 list
	 * @throws SQLException
	 */
	public List<ScheduleDTO> ReadMonthDepSchedule(int dep_no, String date, int e_day) throws SQLException {
		DBConnection();
		List<ScheduleDTO> list = new ArrayList<ScheduleDTO>();
		ScheduleDTO dto = null;
		String subStr = "";
		String tmpStr = "";
		String perDate = date + "-%%";
		String sql = "SELECT sc_member_no, "
				+ "sc_subject, "
				+ "sc_s_date, "
				+ "sc_e_date "
				+ "FROM gw_schedule "
				+ "WHERE sc_dep_no = ? "
				+ "AND sc_team = 1 "
				+ "AND (sc_s_date Like ? "
				+ "OR (sc_s_date <= ? AND sc_e_date >= ?))";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, dep_no);
		psmt.setString(2, perDate);
		psmt.setString(3, date);
		psmt.setString(4, date);
		
		System.out.println(psmt.toString());
		rs = getQuery(psmt);
		
		if (rs.next()) {
			do {
				dto = new ScheduleDTO();
				dto.setSc_member_no(rs.getInt("sc_member_no"));
				dto.setSc_subject(rs.getString("sc_subject"));
				tmpStr = rs.getString("sc_s_date");
				tmpStr = tmpStr.replace("-0", "");
				tmpStr = tmpStr.replace("-", "");
				dto.setSc_s_date(tmpStr);
				tmpStr = rs.getString("sc_e_date");
				tmpStr = tmpStr.replace("-0", "");
				tmpStr = tmpStr.replace("-", "");
				dto.setSc_e_date(tmpStr);
				
//				tmpStr = rs.getString("sc_s_date");
//				subStr = tmpStr.substring(0, 6);
//				if (subStr.equals(date)) {
//					dto.setSc_s_day(Integer.parseInt(tmpStr.substring(8, 9)));
//				} else {
//					dto.setSc_s_day(1);
//				}
//				
//				tmpStr = rs.getString("sc_e_date");
//				subStr = tmpStr.substring(0, 6);
//				
//				if (subStr.equals(date)) {
//					dto.setSc_e_day(Integer.parseInt(tmpStr.substring(8, 9)));
//				} else {
//					dto.setSc_e_day(e_day);
//				}
				
				list.add(dto);
			} while (rs.next());
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 오늘 스케줄 로드
	 * @param today : 오늘 날짜 ex) 2014-01-01
	 * @return 스케줄 리스트
	 * @throws SQLException
	 */
	public List<ScheduleDTO> TodaySchedule(String today, int mb_no) throws SQLException {
		DBConnection();
		List<ScheduleDTO> list = new ArrayList<ScheduleDTO>();
		ScheduleDTO dto = null;
		String sql = "SELECT sc_no, "
				+ "sc_subject, "
				+ "sc_progress, "
				+ "sc_e_date "
				+ "FROM gw_schedule "
				+ "WHERE sc_member_no = ? "
				+ "AND sc_s_date <= ? "
				+ "AND sc_e_date >= ? "
				+ "AND sc_team = 0";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setString(2, today);
		psmt.setString(3, today);
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			do {
				dto = new ScheduleDTO();
				dto.setSc_no(rs.getInt("sc_no"));
				dto.setSc_subject(rs.getString("sc_subject"));
				dto.setSc_progress(rs.getInt("sc_progress"));
				dto.setSc_e_date(format.format(rs.getTimestamp("sc_e_date")));
				list.add(dto);
			} while (rs.next());
		} else {
			
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 해당 날짜의 뷰 리스트
	 * @param mb_no = 멤버 no
	 * @param dep_no = 부서 no (9999일 경우 개인스케줄 검색)
	 * @param date = 날짜
	 * @return
	 * @throws SQLException
	 */
	public List<ScheduleDTO> GetTodayScList(int mb_no, int dep_no, String date, int my_mb_no) throws SQLException {
		DBConnection();
		List<ScheduleDTO> list = new ArrayList<ScheduleDTO>();
		ScheduleDTO dto = null;
		String[] tmpDateList = date.split("-");
		if (tmpDateList[1].length() < 2) {
			tmpDateList[1] = "0" + tmpDateList[1];
		}
		if (tmpDateList[2].length() < 2) {
			tmpDateList[2] = "0" + tmpDateList[2];
		}
		
		if (dep_no == 9999) {
			String sql = "SELECT sc_no, "
					+ "sc_dep_no, "
					+ "sc_progress, "
					+ "sc_subject, "
					+ "sc_content, "
					+ "sc_s_date, "
					+ "sc_e_date "
					+ "FROM gw_schedule "
					+ "WHERE sc_member_no = ? "
					+ "AND sc_dep_no = ( "
					+ "SELECT mb_dep_no FROM gw_member "
					+ "WHERE mb_no = ? "
					+ " ) "
					+ "AND (sc_s_date <= ? "
					+ "AND sc_e_date >= ?) "
					+ "AND sc_team = 0 "
					+ "ORDER BY sc_e_date asc";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, mb_no);
			psmt.setInt(2, my_mb_no);
			psmt.setString(3, tmpDateList[0] + "-" + tmpDateList[1] + "-" + tmpDateList[2]);
			psmt.setString(4, tmpDateList[0] + "-" + tmpDateList[1] + "-" + tmpDateList[2]);
		} else {
			String sql = "SELECT sc_no, "
					+ "sc_dep_no, "
					+ "sc_progress, "
					+ "sc_subject, "
					+ "sc_content, "
					+ "sc_s_date, "
					+ "sc_e_date "
					+ "FROM gw_schedule "
					+ "WHERE sc_dep_no = ? "
					+ "AND (sc_s_date <= ? "
					+ "AND sc_e_date >= ?) "
					+ "AND sc_team = 1 "
					+ "ORDER BY sc_e_date asc";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dep_no);
			psmt.setString(2, tmpDateList[0] + "-" + tmpDateList[1] + "-" + tmpDateList[2]);
			psmt.setString(3, tmpDateList[0] + "-" + tmpDateList[1] + "-" + tmpDateList[2]);
		}
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			do {
				dto = new ScheduleDTO();
				dto.setSc_no(rs.getInt("sc_no"));
				dto.setSc_dep_no(rs.getInt("sc_dep_no"));
				dto.setSc_progress(rs.getInt("sc_progress"));
				dto.setSc_subject(rs.getString("sc_subject"));
				dto.setSc_content(rs.getString("sc_content"));
				dto.setSc_s_date(rs.getString("sc_s_date"));
				dto.setSc_e_date(rs.getString("sc_e_date"));
				list.add(dto);
			} while (rs.next());
		} else {
			list = null;
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 단일 스케줄 로드
	 * @param sc_no
	 * @param mb_no
	 * @return
	 * @throws SQLException
	 */
	public ScheduleDTO GetSelSchedule(int sc_no, int mb_no) throws SQLException {
		DBConnection();
		System.out.println(sc_no + " " + mb_no);
		ScheduleDTO dto;
		String sql = "SELECT sc_no, "
				+ "sc_subject, "
				+ "sc_content, "
				+ "sc_s_date, "
				+ "sc_e_date, "
				+ "sc_progress "
				+ "FROM gw_schedule "
				+ "WHERE sc_no = ? "
				+ "AND sc_dep_no = ( "
				+ "SELECT mb_dep_no FROM gw_member "
				+ "WHERE mb_no = ? "
				+ " ) "
				+ "AND sc_member_no = ? ";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, sc_no);
		psmt.setInt(2, mb_no);
		psmt.setInt(3, mb_no);
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			dto = new ScheduleDTO();
			dto.setSc_no(rs.getInt("sc_no"));
			dto.setSc_subject(rs.getString("sc_subject"));
			dto.setSc_content(rs.getString("sc_content"));
			dto.setSc_progress(rs.getInt("sc_progress"));
			dto.setSc_s_date(rs.getString("sc_s_date"));
			dto.setSc_e_date(rs.getString("sc_e_date"));
		} else {
			System.out.println("여기1");
			dto = null;
		}
		
		DBClose();
		return dto;
	}
	
	/**
	 * 해당 글의 개인글 또는 팀글 구분 확인
	 * @param sc_no
	 * @return
	 * @throws SQLException
	 */
	public int GetCheckSchedule(int sc_no) throws SQLException {
		DBConnection();
		int af = 0;
		String sql = "SELECT sc_team FROM gw_schedule "
				+ "WHERE sc_no = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, sc_no);
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			af = rs.getInt("sc_team");
		} else {
			af = -1;
		}
		
		DBClose();
		return af;
	}
	
	/**
	 * 스케줄 삭제
	 * @param sc_no
	 * @param mb_no
	 * @return
	 * @throws SQLException
	 */
	public int DelSchedule(int sc_no, int mb_no, int sc_team) throws SQLException {
		DBConnection();
		int af = 0;
		if (sc_team == 0) {
			String sql = "DELETE FROM gw_schedule "
					+ "WHERE sc_no = ? "
					+ "AND sc_member_no = ? "
					+ "AND sc_dep_no = ( "
					+ "SELECT mb_dep_no FROM gw_member "
					+ "WHERE mb_no = ? "
					+ " ) "
					+ "AND sc_team = ?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, sc_no);
			psmt.setInt(2, mb_no);
			psmt.setInt(3, mb_no);
			psmt.setInt(4, sc_team);
		} else if (sc_team == 1) {
			String sql = "DELETE FROM gw_schedule "
					+ "WHERE sc_no = ? "
					+ "AND sc_dep_no = ( "
					+ "SELECT mb_dep_no FROM gw_member "
					+ "WHERE mb_no = ? "
					+ " ) "
					+ "AND sc_team = ?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, sc_no);
			psmt.setInt(2, mb_no);
			psmt.setInt(3, sc_team);
		}
		
		
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
}
