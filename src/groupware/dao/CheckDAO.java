package groupware.dao;

import groupware.dto.LogDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.SQLError;

public class CheckDAO extends CommonDAO {
	private ResultSet rs;
	private int af = 0;
	private static CheckDAO _instance;

	public static CheckDAO getInstance() {
		if (_instance == null) {
			_instance = new CheckDAO();
			System.out.println("Config 인스턴스 최초 생성");
		}
		return _instance;
	}
	
	public int CirclesAdminCheck(int mb_no, int code) throws SQLException {
		DBConnection();
		String sql = "select count(*) count FROM gw_circles_rel WHERE "
				+ "cr_circles_no = ? AND cr_member_no = ? AND cr_member_type='ADMIN'";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, code);
		psmt.setInt(2, mb_no);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			af = rs.getInt("count");
		}
		
		DBClose();
		return af;
	}
	
	public int ProjectAdminCheck(int mb_no, int code) throws SQLException {
		DBConnection();
		String sql = "select count(*) count FROM gw_project_rel WHERE "
				+ "pr_project_no = ? AND pr_member_no = ? AND pr_member_type='ADMIN'";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, code);
		psmt.setInt(2, mb_no);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			af = rs.getInt("count");
		}
		
		DBClose();
		return af;
	}
	
	
	
	/**
	 * 각 게시판의 기능별 접근 권한 검사
	 * @param mb_no
	 * @param code
	 * @param boardType
	 * @return
	 * @throws SQLException
	 */
	public boolean communityAuthCheck(int mb_no, int code, String boardType) throws SQLException {
		DBConnection();
		boolean result = false;
		String sql = null;
		if (boardType.equals("PROJECT")) {
		
			sql = "SELECT count(*) count FROM "
					+ "gw_project_rel WHERE "
					+ "pr_project_no = ? AND pr_member_no = ?";
		
		} else if (boardType.equals("CIRCLES")) {
			
			sql = "SELECT count(*) count FROM "
					+ "gw_circles_rel WHERE "
					+ "cr_circles_no = ? AND cr_member_no = ?";
			
		} else {
			return false;
		}

		psmt = conn.prepareStatement(sql);
		
		psmt.setInt(1, code);
		psmt.setInt(2, mb_no);
		
		rs = getQuery(psmt);
		if (rs.next()) {
			int af = rs.getInt("count");
			if (af > 0) {
				result = true;
			}
		}
		
		DBClose();
		return result;
	}
	
	/**
	 * 부서 커뮤니티에 대한 접근 권한 검사
	 * @param mb_no
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public boolean departmentAuthCheck(int mb_no, int code) throws SQLException {
		DBConnection();
		boolean result = false;
		String sql = "SELECT count(*) count FROM "
				+ "gw_board b WHERE "
				+ "b.bo_no = ? AND "
				+ "b.bo_type = 'DEPARTMENT' AND "
				+ "b.bo_name = (SELECT dep_name FROM gw_department WHERE dep_no = "
				+ "(SELECT mb_dep_no FROM gw_member WHERE mb_no = ?))";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, code);
		psmt.setInt(2, mb_no);

		rs = getQuery(psmt);
		if (rs.next()) {
			int af = rs.getInt("count");
			if (af > 0) {
				result = true;
			}
		}
		
		DBClose();
		return result;
	}
	
	public int LOG(LogDTO dto) throws SQLException {
		DBConnection();
		String sql = "INSERT INTO gw_log(log_ip, log_member_id, log_subject, "
				+ "log_content) VALUES(?, ?, ?, ?)";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, dto.getLog_ip());
		psmt.setString(2, dto.getMember_id());
		psmt.setString(3, dto.getLog_subject());
		psmt.setString(4, dto.getLog_content());
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
}
