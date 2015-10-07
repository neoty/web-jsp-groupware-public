package groupware.dao;

import groupware.dto.BoardDTO;
import groupware.dto.CirclesRelDTO;
import groupware.dto.DepartmentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminConfigDAO extends CommonDAO {
	private ResultSet rs;
	private int af = 0;
	private static AdminConfigDAO _instance;
	
	public static AdminConfigDAO getInstance() {
		if (_instance == null) {
			_instance = new AdminConfigDAO();
			System.out.println("Config_ADMIN 인스턴스 최초 생성");
		}
			return _instance;
	}
	
	
	public int GetRelTableTotal(String tableName, int no) throws SQLException {
		DBConnection();
		int total = 0;
		String sql = null;
		if (tableName.equals("gw_circles_rel")) {
			sql = "SELECT count(*) count FROM "
					+ "gw_circles_rel WHERE cr_circles_no=?";
		} else if (tableName.equals("gw_project_rel")) {
			sql = "SELECT count(*) count FROM "
					+ "gw_project_rel WHERE pr_project_no=?";
		}
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, no);
		rs = getQuery(psmt);
		if (rs.next()) {
			total = rs.getInt("count");
		}
		
		DBClose();
		return total;
		
	}
	
	public int MemberShipUse() throws SQLException {
		
		DBConnection();
		
		String sql = "SELECT cf_use_membership FROM gw_config";
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		int result = 0;
		if (rs.next()) {
			result = rs.getInt("cf_use_membership");
		}
		
		DBClose();
		return result;
		
	}

	
}
