package groupware.dao;

import groupware.dto.BoardDTO;
import groupware.dto.DepartmentDTO;
import groupware.dto.MemberDTO;
import groupware.dto.MessageDTO;
import groupware.dto.PositionDTO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO extends CommonDAO {
	private ResultSet rs;
	private static DepartmentDAO _instance;

	public static DepartmentDAO getInstance() {
		if (_instance == null) {
			_instance = new DepartmentDAO();
			System.out.println("Member 인스턴스 최초 생성");
		}
		return _instance;
	}
	
	public List<DepartmentDTO> GetDepartmentListForMemberShip() throws SQLException {
		DBConnection();
		String sql = "SELECT dep_no, dep_name FROM gw_department WHERE dep_use = 1";
		
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		List<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
		
		while (rs.next()) {
			DepartmentDTO b = new DepartmentDTO();
			b.setDep_no(rs.getInt("dep_no"));
			b.setDep_name(rs.getString("dep_name"));
			list.add(b);
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 부서 중복 검사
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public int getDepartmentDupCheck(String name) throws SQLException {
		DBConnection();
		int af = -1;
		
		String sql = "SELECT count(*) count FROM gw_department WHERE dep_name = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, name);
		rs = getQuery(psmt);
		
		while (rs.next()) {
			af = rs.getInt("count");
		}
		
		DBClose();
		return af;
	}
	
	/**
	 * 부서 생성
	 * @param DTO
	 * @return
	 * @throws SQLException
	 */
	public int createDepartment(DepartmentDTO DTO) throws SQLException {
		DBConnection();
		String sql = "INSERT INTO gw_department(dep_name, dep_describe, dep_admin, dep_use) "
				+ "VALUES(?, ?, ?, ?)";
		int af = 0;
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DTO.getDep_name());
		psmt.setString(2, DTO.getDep_describe());
		psmt.setInt(3, DTO.getDep_admin());
		psmt.setInt(4, DTO.getDep_use());

		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 부서가 이미 존재 하는지 검사
	 * @param dep_no
	 * @return
	 * @throws SQLException
	 */
	public int DepartmentExistsCheck(int dep_no) throws SQLException {
		DBConnection();
		int total = 0;
		String sql = "SELECT count(*) count FROM gw_department WHERE dep_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, dep_no);
		rs = getQuery(psmt);
		if (rs.next()) {
			total = rs.getInt("count");
		}
		DBClose();
		return total;
	}
	
	public DepartmentDTO GetDepartmentStatus(int dep_no) throws SQLException{
		DBConnection();
		
		String sql ="SELECT d.dep_no dep_no, "
				+ "d.dep_name dep_name, "
				+ "d.dep_use dep_use, "
				+ "d.dep_describe dep_describe, "
				+ "m.mb_no mb_no, "
				+ "m.mb_name mb_name, "
				+ "d.dep_create_date dep_create_date, "
				+ "p.posi_name posi_name "
				+ "FROM gw_department d "
				+ "LEFT JOIN gw_member m ON m.mb_no = d.dep_admin "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE d.dep_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, dep_no);
		rs = getQuery(psmt);
		
		DepartmentDTO b = new DepartmentDTO();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while (rs.next()) {
			b.setDep_no(rs.getInt("dep_no"));
			b.setDep_name(rs.getString("dep_name"));
			b.setDep_describe(rs.getString("dep_describe"));
			b.setDep_use(rs.getInt("dep_use"));
			b.setDep_mb_no(rs.getInt("mb_no"));
			b.setDep_mb_name(rs.getString("mb_name"));
			b.setDep_posi_name(rs.getString("posi_name"));
			b.setDep_create_date(format.format(rs.getTimestamp("dep_create_date")));
		}
		
		DBClose();
		return b;
	}
	
	
	public int UpdateDepartment(DepartmentDTO DTO) throws SQLException {
		DBConnection();
		int af;
		String sql = "UPDATE gw_department SET dep_name=?, "
				+ "dep_describe=?, dep_use=?, dep_admin=? WHERE dep_no=?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DTO.getDep_name());
		psmt.setString(2, DTO.getDep_describe());
		psmt.setInt(3, DTO.getDep_use());
		psmt.setInt(4, DTO.getDep_admin());
		psmt.setInt(5, DTO.getDep_no());
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	public int DepartmentDelete(int dep_no) throws SQLException {
		int af;
		DBConnection();
		String sql = "DELETE FROM gw_department WHERE dep_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, dep_no);
		af = setQuery(psmt);
		DBClose();
		return af;
		
	}
	
	public List<DepartmentDTO> GetListDepartment() throws SQLException {
		DBConnection();
		
		String sql = "SELECT "
				+ "d.dep_no, "
				+ "d.dep_name, "
				+ "d.dep_describe, "
				+ "d.dep_use, "
				+ "p.posi_name, "
				+ "m.mb_name, "
				+ "m.mb_no "
				+ "FROM gw_department d "
				+ "LEFT JOIN gw_member m ON m.mb_no = d.dep_admin "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE d.dep_use BETWEEN 0 AND 1";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		List<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
		while (rs.next()) {
			DepartmentDTO b = new DepartmentDTO();
			b.setDep_no(rs.getInt("dep_no"));
			b.setDep_name(rs.getString("dep_name"));
			b.setDep_describe(rs.getString("dep_describe"));
			b.setDep_use(rs.getInt("dep_use"));
			b.setDep_posi_name(rs.getString("posi_name"));
			b.setDep_mb_name(rs.getString("mb_name"));
			b.setDep_mb_no(rs.getInt("mb_no"));
			list.add(b);
		}
		DBClose();
		return list;
	}
	
	/**
	 * dep_no와 dep_name만 뽑아옴
	 */
	public List<DepartmentDTO> GetSimpleDepList() throws SQLException {
		DBConnection();
		List<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
		DepartmentDTO dto = null;
		String sql = "Select dep_no, "
				+ "dep_name "
				+ "FROM gw_department "
				+ "ORDER BY dep_no asc";
		
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			do {
				dto = new DepartmentDTO();
				dto.setDep_no(rs.getInt("dep_no"));
				dto.setDep_name(rs.getString("dep_name"));
				list.add(dto);
			} while (rs.next());
		} else {
			System.out.println("검색 실패.");
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 중복되는 코드가 이미 있지만 이것은 조직도에서 부서리스트를 뽑아주기 위함임
	 * @return
	 * @throws SQLException
	 */
	public List<DepartmentDTO> getDepartmentList() throws SQLException {
		DBConnection();
		List<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
		DepartmentDTO dto = null;
		String sql = "SELECT * FROM "
				+ "gw_department "
				+ "WHERE dep_use = 1";
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		while (rs.next()) {
			dto = new DepartmentDTO();
			dto.setDep_no(rs.getInt("dep_no"));
			dto.setDep_name(rs.getString("dep_name"));
			dto.setDep_describe(rs.getString("dep_describe"));
			dto.setDep_admin(rs.getInt("dep_admin"));
			list.add(dto);
		}
		
		DBClose();
		return list;
	}
	
	
	/**
	 * 해당 부서 의 사람 숫자 구하기
	 * @param dep_no
	 * @return 카운터수
	 * @throws SQLException
	 */
	public int departmentMemberCount(int dep_no) throws SQLException {
		DBConnection();
		int af = 0;
		String sql = null;
		if (dep_no == 0) {
			sql = "SELECT count(*) count "
					+ "FROM gw_member "
					+ "WHERE mb_dep_no > 1";

		} else if (dep_no > 1) {
			sql = "SELECT COUNT(*) count "
					+ "FROM gw_member "
					+ "WHERE mb_dep_no = ?";
		}
		psmt = conn.prepareStatement(sql);
		if (dep_no > 1) {
			psmt.setInt(1, dep_no);
		}
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			af = rs.getInt("count");
		}
		DBClose();
		return af;
	}
	
	/**
	 * 요청에 해당하는 회원 리스트 뽑기
	 * @param dep_no
	 * @return
	 * @throws SQLException
	 */
	public List<MemberDTO> getDepartmentMemberList(int dep_no, int srow) throws SQLException {
		DBConnection();
		String sql = null;
		if (dep_no == 0) {
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
		} else if (dep_no > 1) {
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
					+ "WHERE m.mb_position_posi_level > 0 AND m.mb_certify > 0 AND m.mb_use > 0 AND m.mb_dep_no = ? "
					+ "ORDER BY m.mb_no desc limit ?, ?";
		}

		psmt = conn.prepareStatement(sql);
		if (dep_no > 1) {
			psmt.setInt(1, dep_no);
			psmt.setInt(2, srow);
			psmt.setInt(3, 12);
		} 
		if (dep_no == 0) {
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
	
	/**
	 * 자신의 부서명 검색
	 * @param dep_no = 부서 코드
	 * @return 부서명(String)
	 * @throws SQLException
	 */
	public String GetDepName(int mb_no, int dep_no) throws SQLException {
		DBConnection();
		String dep_name = "";
		String sql = "SELECT B.dep_name "
				+ "FROM gw_member A "
				+ "LEFT JOIN gw_department B "
				+ "ON A.mb_dep_no = B.dep_no "
				+ "WHERE A.mb_no = ? "
				+ "AND A.mb_dep_no = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, dep_no);
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			dep_name = rs.getString("B.dep_name");
		}
		
		DBClose();
		return dep_name;
	}

}