package groupware.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import groupware.dao.*;

public class TestDao extends CommonDAO {
	private ResultSet rs;
	private static TestDao _instance;

	public static TestDao getInstance() {
		/*
		 * 최초 접속시 생성
		 */
		if (_instance == null) {
			_instance = new TestDao();
			System.out.println("최초 접속");
		}
		return _instance;
	}

	public ResultSet getItem() throws SQLException {
		DBConnection();

		String sql = "insert into test(test2) values(?)";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, 123);

		int af = setQuery(psmt);
		System.out.println(af);
		// rs = getQuery(psmt);
		// while (rs.next()) {
		//
		// }

		DBClose();
		return null;
	}
}
