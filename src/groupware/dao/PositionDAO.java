package groupware.dao;
import groupware.dto.MemberDTO;
import groupware.dto.PositionDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO extends CommonDAO {
	private ResultSet rs;
	private static PositionDAO _instance;
	public static PositionDAO getInstance() {
		if (_instance == null) {
			_instance = new PositionDAO();
			System.out.println("Member 인스턴스 최초 생성");
		}
		return _instance;
	}
	
	/**
	 * 사용중인 직책의 이름과 레밸을 반환한다. 멤버쉽과 조직도에서 쓰고 있음
	 * @return
	 * @throws SQLException
	 */
	public List<PositionDTO> GetPositionListForMemberShip() throws SQLException {
		DBConnection();
		String sql = "SELECT posi_level, posi_name FROM gw_position WHERE posi_use = 1";
		
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		while (rs.next()) {
			PositionDTO b = new PositionDTO();
			b.setPosi_level(rs.getInt("posi_level"));
			b.setPosi_name(rs.getString("posi_name"));
			list.add(b);
		}
		
		DBClose();
		return list;
	}
	
	public List<PositionDTO> GetAllPositionInfo() throws SQLException {
		DBConnection();
		String sql = "SELECT * FROM gw_position";
		
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		List<PositionDTO> list = new ArrayList<PositionDTO>();
		while (rs.next()) {
			PositionDTO b = new PositionDTO();
			b.setPosi_level(rs.getInt("posi_level"));
			b.setPosi_name(rs.getString("posi_name"));
			b.setPosi_use(rs.getInt("posi_use"));
			list.add(b);
		}
		
		DBClose();
		return list;
	}
	
	public int UpdatePosition(int posi_level, int posi_use) throws SQLException {
		DBConnection();
		String sql = "UPDATE gw_position SET posi_use = ? WHERE posi_level = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, posi_use);
		psmt.setInt(2, posi_level);
		int af = setQuery(psmt);
		
		DBClose();
		return af;	
		}
	
	
	/**
	 * 해당 직책의 사람 숫자 구하기
	 * @param dep_no
	 * @return 카운터수
	 * @throws SQLException
	 */
	public int positionMemberCount(int position) throws SQLException {
		DBConnection();
		int af = 0;
		String sql = null;
		if (position == 0) {
			sql = "SELECT count(*) count "
					+ "FROM gw_member "
					+ "WHERE mb_position_posi_level > 0";

		} else if (position > 0) {
			sql = "SELECT COUNT(*) count "
					+ "FROM gw_member "
					+ "WHERE mb_position_posi_level = ?";
		}
		psmt = conn.prepareStatement(sql);
		if (position > 1) {
			psmt.setInt(1, position);
		}
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			af = rs.getInt("count");
		}
		DBClose();
		return af;
	}
	
	
	
	
	/**
	 * 요청에 해당하는 직책 리스트 뽑기
	 * @param dep_no
	 * @return
	 * @throws SQLException
	 */
	public List<MemberDTO> getPositionMemberList(int posi_no, int srow) throws SQLException {
		DBConnection();
		String sql = null;
		if (posi_no == 0) {
			sql = "SELECT "
					+ "m.mb_no, "
					+ "m.mb_img, "
					+ "m.mb_name, "
					+ "m.mb_tel, "
					+ "m.mb_email, "
					+ "p.posi_name, "
					+ "d.dep_name "
					+ "FROM gw_member m "
					+ "LEFT JOIN gw_position p ON p.posi_level = m.mb_position_posi_level "
					+ "LEFT JOIN gw_department d ON d.dep_no = m.mb_dep_no "
					+ "WHERE m.mb_position_posi_level > 0 AND m.mb_certify > 0 AND m.mb_use > 0 "
					+ "ORDER BY m.mb_no desc limit ?";
		} else if (posi_no > 0) {
			sql = "SELECT "
					+ "m.mb_no, "
					+ "m.mb_img, "
					+ "m.mb_name, "
					+ "m.mb_tel, "
					+ "m.mb_email, "
					+ "p.posi_name, "
					+ "d.dep_name "
					+ "FROM gw_member m "
					+ "LEFT JOIN gw_position p ON p.posi_level = m.mb_position_posi_level "
					+ "LEFT JOIN gw_department d ON d.dep_no = m.mb_dep_no "
					+ "WHERE m.mb_position_posi_level > 0 AND m.mb_certify > 0 AND m.mb_use > 0 AND m.mb_position_posi_level = ? "
					+ "ORDER BY m.mb_no desc limit ?, ?";
		}
		System.out.println(posi_no);
		psmt = conn.prepareStatement(sql);
		if (posi_no > 0) {
			psmt.setInt(1, posi_no);
			psmt.setInt(2, srow);
			psmt.setInt(3, 12);
		} 
		if (posi_no == 0) {
			psmt.setInt(1, 12);
		}
		
		rs = getQuery(psmt);

		List<MemberDTO> list = new ArrayList<MemberDTO>();
		while (rs.next()) {
			MemberDTO n = new MemberDTO();
			n.setMb_no(rs.getInt("mb_no"));
			n.setMb_img(rs.getString("mb_img"));
			n.setMb_name(rs.getString("mb_name"));
			n.setMb_tel(rs.getString("mb_tel"));
			n.setMb_email(rs.getString("mb_email"));
			n.setPosi_name(rs.getString("posi_name"));
			n.setDep_name(rs.getString("dep_name"));
			list.add(n);
		}
		DBClose();
		return list;
	}
	
}
