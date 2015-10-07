package groupware.dao;

import groupware.dto.ReplyDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReplyDAO extends CommonDAO {
	private ResultSet rs;
	private int af = 0;
	private static ReplyDAO _instance;

	public static ReplyDAO getInstance() {
		if (_instance == null) {
			_instance = new ReplyDAO();
			System.out.println("리플레이 인스턴스 최초 생성");
		}
		return _instance;
	}
	
	public int insertReply(ReplyDTO DTO) throws SQLException {
		DBConnection();
		String sql = "INSERT INTO gw_reply("
				+ "re_bc_no, "
				+ "re_content, "
				+ "re_mb_no) VALUES ("
				+ "?, ?, ?)";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, DTO.getRe_bc_no());
		psmt.setString(2, DTO.getRe_content());
		psmt.setInt(3, DTO.getRe_mb_no());
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 
	 * @param bc_no
	 * @return
	 * @throws SQLException
	 */
	public int updateReplyCountAdd(int bc_no) throws SQLException {
		DBConnection();
		String sql = "UPDATE gw_board_content "
				+ "SET bc_reply_count = bc_reply_count+1 WHERE "
				+ "bc_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bc_no);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 
	 * @param bc_no
	 * @return 
	 * @throws SQLException
	 */
	public int updateReplyCountMinus(int bc_no) throws SQLException {
		DBConnection();
		String sql = "UPDATE gw_board_content "
				+ "SET bc_reply_count = bc_reply_count-1 WHERE "
				+ "bc_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bc_no);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	public List<ReplyDTO> getReply(int bc_no) throws SQLException {
		DBConnection();
		String sql = "SELECT "
				+ "re_no, "
				+ "re_content, "
				+ "re_create_date, "
				+ "mb_no, "
				+ "mb_name, "
				+ "mb_img, "
				+ "posi_name "
				+ "FROM gw_reply r "
				+ "LEFT JOIN gw_member m ON r.re_mb_no = m.mb_no "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE re_bc_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bc_no);
		rs = getQuery(psmt);
		
		List<ReplyDTO> list = new ArrayList<ReplyDTO>();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		while (rs.next()) {
			 ReplyDTO n = new ReplyDTO();
			 n.setRe_no(rs.getInt("re_no"));
			 n.setRe_content(rs.getString("re_content"));
			 n.setRe_create_date(format.format(rs.getTimestamp("re_create_date")));
			 n.setMb_no(rs.getInt("mb_no"));
			 n.setMb_name(rs.getString("mb_name"));
			 n.setMb_img(rs.getString("mb_img"));
			 n.setMb_posi_name(rs.getString("posi_name"));
			 
			 list.add(n);
		}
		
		DBClose();
		return list;
	}
	
	public int deleteReply(int bc_no, int mb_no) throws SQLException {
		DBConnection();
		String safesql = "set SQL_SAFE_UPDATES=0";
		psmt = conn.prepareStatement(safesql);
		af = setQuery(psmt);
		String sql = "DELETE FROM gw_reply "
				+ "WHERE re_no = ? "
				+ "AND re_mb_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bc_no);
		psmt.setInt(2, mb_no);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
}
