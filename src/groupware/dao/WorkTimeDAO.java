package groupware.dao;

import groupware.dto.AttendanceDTO;
import groupware.dto.BoardContentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WorkTimeDAO extends CommonDAO {
	private ResultSet rs;
	private int af = 0;
	private static WorkTimeDAO _instance;

	public static WorkTimeDAO getInstance() {
		if (_instance == null) {
			_instance = new WorkTimeDAO();
		}
		return _instance;
	}
	
	/**
	 * 출근 도장을 찍기전에 이미 오늘날짜의 출근 시간이 있는지 검사한다.
	 * @param mb_no
	 * @param checkDate
	 * @return
	 * @throws SQLException
	 */
	public int startCheck(int mb_no, String checkDate) throws SQLException {
		DBConnection();
		String sql = "SELECT count(*) count FROM gw_attendance WHERE "
				+ "att_member_no = ? AND att_s_date LIKE ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setString(2, checkDate+"%");
		rs = getQuery(psmt);
		while (rs.next()) {
			af = rs.getInt("count");
		}
		DBClose();
		return af;
	}
	
	
	/**
	 * 출근 날짜 및 시간 쓰기
	 * @param mb_no
	 * @param startTime
	 * @return
	 * @throws SQLException
	 */
	public int startWork(int mb_no, String time) throws SQLException {
		DBConnection();
		String sql = "INSERT INTO gw_attendance(att_member_no, att_s_date) "
				+ "VALUES(?, ?)";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setString(2, time);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	
	/**
	 * 퇴근 시간을 삽입전에 오늘 출근 했는지 검사한다.
	 * @param mb_no
	 * @param time
	 * @return
	 * @throws SQLException
	 */
	public int getAtt_no(int mb_no, String time) throws SQLException {
		DBConnection();
		String sql = "SELECT att_no FROM gw_attendance WHERE "
				+ "att_member_no = ? AND att_s_date LIKE ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setString(2, time+"%");
		rs = getQuery(psmt);
		while (rs.next()) {
			af = rs.getInt("att_no");
		}
		DBClose();
		return af;
	}
	
	public String checkEndTime(int att_no) throws SQLException {
		DBConnection();
		String sql = "SELECT att_e_date FROM gw_attendance WHERE "
				+ "att_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, att_no);
		rs = getQuery(psmt);
		String time = "";
		while (rs.next()) {
			time = rs.getString("att_e_date");
		}
		DBClose();
		System.out.println("넘겨주는값"+time);
		return time;
	}
	
	
	/**
	 * 퇴근 시간을 업데이트 한다.
	 * @param att_no
	 * @param time
	 * @return
	 * @throws SQLException
	 */
	public int setEndTime(int att_no, String time) throws SQLException {
		DBConnection();
		String sql = "UPDATE gw_attendance SET att_e_date = ? "
				+ "WHERE att_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, time);
		psmt.setInt(2, att_no);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	public List<AttendanceDTO> getAttList(int mb_no, String date) throws SQLException {
		DBConnection();
		String sql = "SELECT * FROM "
				+ "gw_attendance WHERE "
				+ "att_member_no = ? AND "
				+ "att_s_date LIKE ? ORDER BY att_no DESC";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setString(2, date);
		rs = getQuery(psmt);
		List<AttendanceDTO> list = new ArrayList<AttendanceDTO>();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		while (rs.next()) {
			AttendanceDTO n = new AttendanceDTO();
			n.setAtt_s_date(format.format(rs.getTimestamp("att_s_date")));
			String dayOfdate = rs.getString("att_s_date").substring(8, 10);
			n.setDayOfdate(dayOfdate);
			if (rs.getString("att_e_date") != null) {
				n.setAtt_e_date(format.format(rs.getTimestamp("att_e_date")));
			} else {
				n.setAtt_e_date(null);
			}
			
			list.add(n);
		}
		DBClose();
		return list;
	}
	
	
	public String[] WorkTimeInfo() throws SQLException {
		DBConnection();
		String sql = "SELECT cf_work_start, cf_work_end FROM gw_config";
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		String[] workInfo = new String[2];
		while (rs.next()) {
			workInfo[0] = rs.getString("cf_work_start");
			workInfo[1] = rs.getString("cf_work_end");
		}
		DBClose();
		return workInfo;
	}

}


















