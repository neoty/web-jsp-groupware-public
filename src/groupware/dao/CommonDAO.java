/*
 *  써놓을 데가 없어서 여기다가 써놓음 나 중에 컨트롤러 단에서 모든 인자값들을 넘겨줘야함 컨트롤러 단에서 request 객체를 받으므로 		
 *  HttpSession ses = request.getSession();
 *	System.out.println(ses.getAttribute("ses"));
 *	을 이용하여 세션 값 얻을것
 */
package groupware.dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

public class CommonDAO {
	public Connection conn = null;
	public PreparedStatement psmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;

	// DB 접속
	public void DBConnection() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			/*
			 * 나중에 에러 페이지로 포워딩 할것
			 */
			System.out.println("DB  연결 실패 : " + e);
		}
	}

	// DB 종료
	public void DBClose() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// select 쿼리
	public ResultSet getQuery(PreparedStatement sql) throws SQLException {
		try {
			psmt = sql;
			rs = psmt.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("데이터 베이스 ㅋㅋㅋ익셉션 걸림");
		}
		return rs;
	}
	
	public int setQuery(PreparedStatement sql) throws SQLException {
		int af = 0;
		try {
			psmt = sql;
			af = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("데이터 베이스 ㅋㅋㅋ엑셉션 걸림");
		}
		return af;
	}
}
