package groupware.dao;

import groupware.dto.BoardContentDTO;
import groupware.dto.BoardDTO;
import groupware.dto.DepartmentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommunityDAO extends CommonDAO {
	private ResultSet rs;
	private int af = 0;
	private static CommunityDAO _instance;

	public static CommunityDAO getInstance() {
		if (_instance == null) {
			_instance = new CommunityDAO();
			System.out.println("Config 인스턴스 최초 생성");
		}
		return _instance;
	}
	
	public List<BoardDTO> GetListBoard(String bo_type) throws SQLException {
		DBConnection();
		
		String sql = "SELECT * FROM gw_board WHERE bo_type = ? AND bo_use BETWEEN 0 AND 1";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, bo_type);
		rs = getQuery(psmt);
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		while (rs.next()) {
			BoardDTO b = new BoardDTO();
			b.setBo_no(rs.getInt("bo_no"));
			b.setBo_name(rs.getString("bo_name"));
			b.setBo_describe(rs.getString("bo_describe"));
			b.setBo_use(rs.getInt("bo_use"));
			
			list.add(b);
		}
		DBClose();
		return list;
	}
	
	
	public BoardDTO GetBoardStatus(int bo_no) throws SQLException {
		DBConnection();
		
		
		String sql ="SELECT b.bo_no bo_no, "
				+ "b.bo_name bo_name, "
				+ "b.bo_type, "
				+ "b.bo_use bo_use, "
				+ "b.bo_describe bo_describe, "
				+ "m.mb_no mb_no, "
				+ "m.mb_name mb_name, "
				+ "m.mb_img mb_img, "
				+ "b.bo_create_date bo_create_date, "
				+ "p.posi_name posi_name "
				+ "FROM gw_board b "
				+ "LEFT JOIN gw_member m ON m.mb_no = b.bo_admin "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE b.bo_no = ?";
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bo_no);
		rs = getQuery(psmt);
		
		BoardDTO b = new BoardDTO();

		while (rs.next()) {
			b.setBo_no(rs.getInt("bo_no"));
			b.setBo_type(rs.getString("bo_type"));
			b.setBo_name(rs.getString("bo_name"));
			b.setBo_describe(rs.getString("bo_describe"));
			b.setBo_use(rs.getInt("bo_use"));
			b.setBo_mb_no(rs.getInt("mb_no"));
			b.setBo_mb_name(rs.getString("mb_name"));
			b.setBo_mb_posi_name(rs.getString("posi_name"));
			b.setMb_img(rs.getString("mb_img"));
			b.setBo_create_date(rs.getDate("bo_create_date"));
		}
		
		DBClose();
		return b;
	}
	
	
	public int AddCommunity(BoardDTO DTO, String where) throws SQLException {

		DBConnection();
		
		
		String sql = "INSERT INTO gw_board("
				+ "bo_type, "
				+ "bo_name, "
				+ "bo_describe, "
				+ "bo_use, "
				+ "bo_admin) VALUES("
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?)";
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DTO.getBo_type());
		psmt.setString(2, DTO.getBo_name());
		psmt.setString(3, DTO.getBo_describe());
		psmt.setInt(4, DTO.getBo_use());
		psmt.setInt(5, DTO.getBo_admin());
		
		af = setQuery(psmt);
		
		/*
		 * where 이 CIRCLES 나 PROJECT 로 넘어 올시 권한 릴레이션 테이블에 넣어준다.
		 * PUBLIC, DEPARTMENT 는 실행 되지 않음
		 */
		if (where.equals("CIRCLES") || where.equals("PROJECT")) {
			String getNumberSql = "SELECT bo_no FROM gw_board WHERE "
					+ "bo_type = ? and "
					+ "bo_name = ? and "
					+ "bo_describe = ? and "
					+ "bo_admin = ?";
			
			psmt = conn.prepareStatement(getNumberSql);
			psmt.setString(1, DTO.getBo_type());
			psmt.setString(2, DTO.getBo_name());
			psmt.setString(3, DTO.getBo_describe());
			psmt.setInt(4, DTO.getBo_admin());
			rs = getQuery(psmt);
			
			int bo_no = 0;
			if (rs.next()) {
				bo_no = rs.getInt("bo_no");
			}

			String RelSql = null;

			if (where.equals("CIRCLES")) {
				RelSql = "INSERT INTO gw_circles_rel(cr_circles_no, cr_member_no, cr_member_type) VALUES(?, ?, ?)";
				psmt = conn.prepareStatement(RelSql);
				psmt.setInt(1, bo_no);
				psmt.setInt(2, DTO.getBo_admin());
				psmt.setString(3, "ADMIN");
				af = setQuery(psmt);

			} else if (where.equals("PROJECT")) {
				RelSql = "INSERT INTO gw_project_rel(pr_project_no, pr_member_no, pr_member_type) VALUES(?, ?, ?)";
				psmt = conn.prepareStatement(RelSql);
				psmt.setInt(1, bo_no);
				psmt.setInt(2, DTO.getBo_admin());
				psmt.setString(3, "ADMIN");
				af = setQuery(psmt);
			}
		}
		
		
		DBClose();
		return af;
	}
	
	
	public int BoardDelete(int bo_no) throws SQLException {
		DBConnection();

		String sql = "DELETE FROM gw_board WHERE bo_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bo_no);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	public int getUpdateDepartmentBoNO(String timestamp) throws SQLException {
		DBConnection();
		String sql = "SELECT bo_no "
				+ "FROM gw_board "
				+ "WHERE bo_create_date = ?";
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, timestamp);
		rs = getQuery(psmt);
		
		while (rs.next()) {
			af = rs.getInt("bo_no");
		}
		DBClose();
		
		return af;
	}

	public int UpdateCommunity(BoardDTO DTO, String where) throws SQLException {
		DBConnection();
		
		String sql = "UPDATE gw_board SET bo_name=?, "
				+ "bo_describe=?, bo_use=?, bo_admin=? WHERE bo_no=?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DTO.getBo_name());
		psmt.setString(2, DTO.getBo_describe());
		psmt.setInt(3, DTO.getBo_use());
		psmt.setInt(4, DTO.getBo_admin());
		psmt.setInt(5, DTO.getBo_no());
		af = setQuery(psmt);
		/*
		 * where 이 CIRCLES 나 PROJECT 로 넘어 올시 권한 릴레이션 테이블에 넣어준다.
		 */
		if (where.equals("CIRCLES") || where.equals("PROJECT")) {
			String RelSql;
			if (where.equals("CIRCLES")) {
				RelSql = "UPDATE gw_circles_rel SET cr_member_no = ? WHERE cr_circles_no = ? AND cr_member_type = ?";
				psmt = conn.prepareStatement(RelSql);

				psmt.setInt(1, DTO.getBo_admin());
				psmt.setInt(2, DTO.getBo_no());
				psmt.setString(3, "ADMIN");
				af = setQuery(psmt);
				return af;
			} else if (where.equals("PROJECT")) {
				RelSql = "UPDATE gw_project_rel SET pr_member_no = ? WHERE pr_project_no = ? AND pr_member_type = ?";
				psmt = conn.prepareStatement(RelSql);
				
				psmt.setInt(1, DTO.getBo_admin());
				psmt.setInt(2, DTO.getBo_no());
				psmt.setString(3, "ADMIN");
				af = setQuery(psmt);
				return af;
			}
		}
		DBClose();
		
		return af;
	}
	
	/**
	 * 보드가 존재하는지 체크한다.
	 * @param bo_no
	 * @return
	 * @throws SQLException
	 */
	public int BoardExistsCheck(int bo_no) throws SQLException {
		DBConnection();
		int total = 0;
		String sql = "SELECT count(*) count FROM gw_board WHERE bo_no=?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bo_no);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			total = rs.getInt("count");
		}
		
		DBClose();
		return total;
	}
	
	
	/**
	 * 검색하고자 하는 내용의 글갯수를 구한다.
	 * @param type
	 * @param code
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public int getSearchCountBoard(String type, int code, String search) throws SQLException {
		DBConnection();
		int total = 0;
		search = "%"+search+"%";
		String sql = "SELECT count(*) count FROM gw_board_content WHERE bc_type = ? AND bc_code = ? AND "
				+ "bc_subject LIKE ? OR bc_content LIKE ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, type);
		psmt.setInt(2, code);
		psmt.setString(3, search);
		psmt.setString(4, search);
		rs = getQuery(psmt);
		if (rs.next()) {
			total = rs.getInt("count");
		}
		DBClose();
		return total;
		
	}
	
	/**
	 * 원하는 보드의 글갯수를 구한다.
	 * @param type
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public int getCountBoard(String type, int code) throws SQLException {
		DBConnection();
		int total = 0;
		String sql = "SELECT count(*) count FROM gw_board_content WHERE bc_type = ? AND bc_code = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, type);
		psmt.setInt(2, code);
		rs = getQuery(psmt);
		if (rs.next()) {
			total = rs.getInt("count");
		}
		DBClose();
		return total;
		
	}
	
	
	public List<BoardContentDTO> getSearchList(String type, int code, int srow, String search) throws SQLException {
		DBConnection();
		search = "%"+search+"%";
		String sql = "SELECT "
				+ "bc_no, "
				+ "bc_subject, "
				+ "bc_date, "
				+ "bc_read_count, "
				+ "bc_reply_count, "
				+ "mb_no, "
				+ "posi_name, "
				+ "mb_img, "
				+ "mb_name "
				+ "FROM gw_board_content bc "
				+ "LEFT JOIN gw_member m ON bc.bc_mb_no = m.mb_no "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE bc_type = ? AND bc_code = ? AND bc_subject LIKE ? OR bc_content LIKE ? "
				+ "ORDER BY bc_no desc limit ?, 20";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, type);
		psmt.setInt(2, code);
		psmt.setString(3, search);
		psmt.setString(4, search);
		psmt.setInt(5, srow);
		rs = getQuery(psmt);
		List<BoardContentDTO> list = new ArrayList<BoardContentDTO>();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		while (rs.next()) {
			BoardContentDTO n = new BoardContentDTO();
			n.setBc_no(rs.getInt("bc_no"));
			n.setBc_subject(rs.getString("bc_subject"));
			n.setBc_date(format.format(rs.getTimestamp("bc_date")));
			n.setBc_read_count(rs.getInt("bc_read_count"));
			n.setBc_reply_count(rs.getInt("bc_reply_count"));
			n.setMb_no(rs.getInt("mb_no"));
			n.setPosi_name(rs.getString("posi_name"));
			n.setMb_name(rs.getString("mb_name"));
			n.setMb_img(rs.getString("mb_img"));
			list.add(n);
		}
		DBClose();
		return list;
	}
	
	
	
	/**
	 * 게시판 글 목록 가져오기
	 * @param type
	 * @param code
	 * @param srow
	 * @return
	 * @throws SQLException
	 */
	public List<BoardContentDTO> getList(String type, int code, int srow) throws SQLException {
		DBConnection();
		String sql = "SELECT "
				+ "bc_no, "
				+ "bc_subject, "
				+ "bc_date, "
				+ "bc_read_count, "
				+ "bc_reply_count, "
				+ "mb_no, "
				+ "posi_name, "
				+ "mb_img, "
				+ "mb_name "
				+ "FROM gw_board_content bc "
				+ "LEFT JOIN gw_member m ON bc.bc_mb_no = m.mb_no "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE bc_type = ? AND bc_code = ? ORDER BY bc_no desc limit ?, ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, type);
		psmt.setInt(2, code);
		psmt.setInt(3, srow);
		psmt.setInt(4, 20);
		rs = getQuery(psmt);
		List<BoardContentDTO> list = new ArrayList<BoardContentDTO>();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		while (rs.next()) {
			BoardContentDTO n = new BoardContentDTO();
			n.setBc_no(rs.getInt("bc_no"));
			n.setBc_subject(rs.getString("bc_subject"));
			n.setBc_date(format.format(rs.getTimestamp("bc_date")));
			n.setBc_read_count(rs.getInt("bc_read_count"));
			n.setBc_reply_count(rs.getInt("bc_reply_count"));
			n.setMb_no(rs.getInt("mb_no"));
			n.setPosi_name(rs.getString("posi_name"));
			n.setMb_name(rs.getString("mb_name"));
			n.setMb_img(rs.getString("mb_img"));
			list.add(n);
		}
		DBClose();
		return list;
	}
	
	
	public List<BoardContentDTO> getNoticeList(String type, int code) throws SQLException {
		DBConnection();
		String sql = "SELECT "
				+ "bc_no, "
				+ "bc_subject, "
				+ "bc_date, "
				+ "bc_read_count, "
				+ "bc_reply_count, "
				+ "mb_no, "
				+ "posi_name, "
				+ "mb_img, "
				+ "mb_name "
				+ "FROM gw_board_content bc "
				+ "LEFT JOIN gw_member m ON bc.bc_mb_no = m.mb_no "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE bc_type = ? AND bc_code = ? AND bc_notice = 1 ORDER BY bc_no desc limit 5";
		psmt = conn.prepareStatement(sql);
		System.out.println("노시트스가져오기 sql"+sql);
		psmt.setString(1, type);
		psmt.setInt(2, code);
		rs = getQuery(psmt);
		List<BoardContentDTO> list = new ArrayList<BoardContentDTO>();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		while (rs.next()) {
			BoardContentDTO n = new BoardContentDTO();
			n.setBc_no(rs.getInt("bc_no"));
			n.setBc_subject(rs.getString("bc_subject"));
			n.setBc_date(format.format(rs.getTimestamp("bc_date")));
			n.setBc_read_count(rs.getInt("bc_read_count"));
			n.setBc_reply_count(rs.getInt("bc_reply_count"));
			n.setMb_no(rs.getInt("mb_no"));
			n.setPosi_name(rs.getString("posi_name"));
			n.setMb_name(rs.getString("mb_name"));
			n.setMb_img(rs.getString("mb_img"));
			list.add(n);
		}
		DBClose();
		return list;
	}
	
	
	
	public List<BoardDTO> PublicANDDepartment(int mb_no) throws SQLException {
		DBConnection();
		
		String sql = "SELECT "
				+ "b.bo_no bo_no, "
				+ "b.bo_type bo_type, "
				+ "b.bo_name bo_name, "
				+ "b.bo_describe bo_describe, "
				+ "b.bo_use bo_use, "
				+ "b.bo_admin bo_admin "
				+ "FROM gw_board b "
				+ "LEFT JOIN gw_department d ON b.bo_name = d.dep_name "
				+ "LEFT JOIN gw_member m ON d.dep_no = m.mb_dep_no "
				+ "WHERE b.bo_type = 'PUBLIC' OR b.bo_type = 'DEPARTMENT' "
				+ "AND b.bo_use = 1 AND m.mb_no = ?";
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		rs = getQuery(psmt);
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		while (rs.next()) {
			BoardDTO n = new BoardDTO();
			n.setBo_no(rs.getInt("bo_no"));
			n.setBo_type(rs.getString("bo_type"));
			n.setBo_name(rs.getString("bo_name"));
			n.setBo_admin(rs.getInt("bo_admin"));
			list.add(n);
		}

		DBClose();
		return list;
	}
	
	public List<BoardDTO> CirclesANDProject(int mb_no) throws SQLException {
		DBConnection();
		
		String sql = "SELECT DISTINCT "
				+ "b.bo_no bo_no, "
				+ "b.bo_type bo_type, "
				+ "b.bo_name bo_name, "
				+ "b.bo_admin bo_admin "
				+ "FROM gw_board b, gw_project_rel p, gw_circles_rel c "
				+ "WHERE b.bo_use = 1 "
				+ "AND p.pr_member_no = ? "
				+ "AND b.bo_type = 'PROJECT' "
				+ "AND b.bo_no = p.pr_project_no "
				+ "OR c.cr_member_no = ? "
				+ "AND b.bo_type = 'CIRCLES' "
				+ "AND b.bo_no = c.cr_circles_no";		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, mb_no);
		rs = getQuery(psmt);
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		while (rs.next()) {
			BoardDTO n = new BoardDTO();
			n.setBo_no(rs.getInt("bo_no"));
			n.setBo_type(rs.getString("bo_type"));
			n.setBo_name(rs.getString("bo_name"));
			n.setBo_admin(rs.getInt("bo_admin"));
			list.add(n);
		}

		DBClose();
		return list;
	}
	
	public int checkDepartment(int code) throws SQLException {
		DBConnection();
		String sql = "SELECT bo_admin "
				+ "FROM gw_board "
				+ "WHERE bo_type='DEPARTMENT' "
				+ "AND bo_no = ?";
		psmt = conn.prepareStatement(sql);
		
		psmt.setInt(1, code);
		rs = getQuery(psmt);
		if (rs.next()) {
			af = rs.getInt("bo_admin");
		}
		
		DBClose();
		return af;
	}
	
	public String checkProject(int code, int mb_no) throws SQLException {
		DBConnection();
		String sql = "SELECT pr_member_type "
				+ "FROM gw_project_rel "
				+ "WHERE pr_project_no = ? "
				+ "AND pr_member_no = ?";
		psmt = conn.prepareStatement(sql);
		
		psmt.setInt(1, code);
		psmt.setInt(2, mb_no);
		rs = getQuery(psmt);
		String admin = null;
		if (rs.next()) {
			admin = rs.getString("pr_member_type");
		}
		
		DBClose();
		return admin;
	}
	
	
	public String checkCircles(int code, int mb_no) throws SQLException {
		DBConnection();
		String sql = "SELECT cr_member_type "
				+ "FROM gw_circles_rel "
				+ "WHERE cr_circles_no = ? "
				+ "AND cr_member_no = ?";
		
		psmt = conn.prepareStatement(sql);
		
		psmt.setInt(1, code);
		psmt.setInt(2, mb_no);
		rs = getQuery(psmt);
		String admin = null;
		if (rs.next()) {
			admin = rs.getString("cr_member_type");
		}
		
		DBClose();
		return admin;
	}
	
	/**
	 * 해당 회원 을 커뮤니티 사용자에 추가 시킨다.
	 * @param code
	 * @param mb_no
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public int RelInsert(int code, int mb_no, String tableName) throws SQLException {
		DBConnection();
		String sql = "INSERT INTO "+tableName+" VALUES("
				+ "?, ?, 'MEMBER', null)";
		psmt = conn.prepareStatement(sql);

		psmt.setInt(1, code);
		psmt.setInt(2, mb_no);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 
	 * @param code gw_board 에 저장된 게시판 번호
	 * @param mb_no 지우고자 하는 회원 번호
	 * @return 지워진 갯수 정상 = 1
	 * @throws SQLException
	 */
	public int ProjectRelDelete(int code, int mb_no) throws SQLException {
		DBConnection();
		String sql = "DELETE FROM gw_project_rel WHERE "
				+ "pr_project_no = ? "
				+ "AND pr_member_no = ? "
				+ "AND pr_member_type = 'MEMBER'";
		psmt = conn.prepareStatement(sql);
		
		psmt.setInt(1, mb_no);
		psmt.setInt(2, code);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 
	 * @param code gw_bord 저장된 게시판 번호
	 * @param mb_no 지우고자 하는 회원 번호
	 * @return 지워진 갯수 정상 = 1
	 * @throws SQLException
	 */
	public int CirclesRelDelete(int code, int mb_no) throws SQLException {
		DBConnection();
		String sql = "DELETE FROM gw_circles_rel WHERE "
				+ "cr_member_no = ? "
				+ "AND cr_circles_no = ? "
				+ "AND cr_member_type = 'MEMBER'";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, code);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 커뮤니티 쪽 글쓰기
	 * @param DTO
	 * @return 글써진 갯수
	 * @throws SQLException
	 */
	public int write(BoardContentDTO DTO) throws SQLException {
		DBConnection();
		String sql = "INSERT INTO gw_board_content("
				+ "bc_mb_no, "
				+ "bc_type, "
				+ "bc_code, "
				+ "bc_subject, "
				+ "bc_content, "
				+ "bc_notice, "
				+ "bc_secret, "
				+ "bc_read_count, "
				+ "bc_reply_count, "
				+ "bc_file_exists) VALUES("
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, DTO.getMb_no());
		psmt.setString(2, DTO.getBc_type());
		psmt.setInt(3, DTO.getBc_code());
		psmt.setString(4, DTO.getBc_subject());
		psmt.setString(5, DTO.getBc_content());
		psmt.setInt(6, DTO.getBc_notice());
		psmt.setInt(7, DTO.getBc_secret());
		psmt.setInt(8, DTO.getBc_read_count());
		psmt.setInt(9, DTO.getBc_reply_count());
		psmt.setInt(10, DTO.getBc_file_exists());
		
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 글목록을 보여주기 위함
	 * @param bc_no
	 * @return
	 * @throws SQLException
	 */
	public BoardContentDTO view(int bc_no) throws SQLException {
		DBConnection();
		
		String sql = "SELECT "
				+ "m.mb_name, "
				+ "m.mb_img, "
				+ "m.mb_no, "
				+ "p.posi_name, "
				+ "d.dep_name, "
				+ "bc_no, "
				+ "bc_mb_no, "
				+ "bc_type, "
				+ "bc_code, "
				+ "bc_subject, "
				+ "bc_content, "
				+ "bc_notice, "
				+ "bc_secret, "
				+ "bc_file_exists, "
				+ "bc_date, "
				+ "f.file_relname1, "
				+ "f.file_encname1, "
				+ "f.file_relname2, "
				+ "f.file_encname2 "
				+ "FROM gw_board_content bc "
				+ "LEFT JOIN gw_member m ON bc_mb_no = m.mb_no "
				+ "LEFT JOIN gw_position p ON p.posi_level = m.mb_position_posi_level "
				+ "LEFT JOIN gw_file f ON f.file_part = 'COMMUNITY' AND file_number = ? "
				+ "LEFT JOIN gw_department d ON m.mb_dep_no = d.dep_no "
				+ "WHERE bc.bc_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bc_no);
		psmt.setInt(2, bc_no);
		rs = getQuery(psmt);
		BoardContentDTO n = new BoardContentDTO();
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		if (rs.next()) {
			n.setMb_name(rs.getString("mb_name"));
			n.setMb_img(rs.getString("mb_img"));
			n.setMb_no(rs.getInt("mb_no"));
			n.setPosi_name(rs.getString("posi_name"));
			n.setDep_name(rs.getString("dep_name"));
			n.setBc_no(rs.getInt("bc_no"));
			n.setBc_mb_no(rs.getInt("bc_mb_no"));
			n.setBc_type(rs.getString("bc_type"));
			n.setBc_code(rs.getInt("bc_code"));
			n.setBc_subject(rs.getString("bc_subject"));
			n.setBc_content(rs.getString("bc_content"));
			n.setBc_notice(rs.getInt("bc_notice"));
			n.setBc_secret(rs.getInt("bc_secret"));
			n.setBc_file_exists(rs.getInt("bc_file_exists"));
			n.setBc_date(format.format(rs.getTimestamp("bc_date")));
			n.setFile_relname1(rs.getString("file_relname1"));
			n.setFile_encname1(rs.getString("file_encname1"));
			n.setFile_relname2(rs.getString("file_relname2"));
			n.setFile_encname2(rs.getString("file_encname2"));
		}
		
		DBClose();
		return n;
	}
	
	
	/**
	 * 
	 * @param bc_no gw_board_content 에서 삭제하기 위한 bc_no 번호
	 * @param mb_no gw_board_content 에서 삭제하기 위한 bc_mb_no 번호
	 * @return 삭제된 갯수 정상 = 1
	 * @throws SQLException
	 */
	public int deleteBoardContent(int bc_no, int mb_no) throws SQLException {
		DBConnection();
		String sql = "DELETE FROM gw_board_content WHERE bc_no = ? AND bc_mb_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bc_no);
		psmt.setInt(2, mb_no);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 
	 * @param bc_no 해당하는 bc_no 의 bc_read_count 를 1만큼 증가 시킨다.
	 * @return 적용된수
	 * @throws SQLException
	 */
	public int incrementReadCount(int bc_no) throws SQLException {
		DBConnection();
		String sql = "UPDATE gw_board_content "
				+ "SET bc_read_count=bc_read_count+1 "
				+ "WHERE bc_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bc_no);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	
	/**
	 * 커뮤니티 글 수정
	 * @param DTO
	 * @return 글써진 갯수
	 * @throws SQLException
	 */
	public int modify(BoardContentDTO DTO) throws SQLException {
		DBConnection();
		String sql = "UPDATE gw_board_content SET "
				+ "bc_subject = ?, "
				+ "bc_content = ?, "
				+ "bc_notice = ?, "
				+ "bc_secret = ?, "
				+ "bc_file_exists = ? "
				+ "WHERE bc_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DTO.getBc_subject());
		psmt.setString(2, DTO.getBc_content());
		psmt.setInt(3, DTO.getBc_notice());
		psmt.setInt(4, DTO.getBc_secret());
		psmt.setInt(5, DTO.getBc_file_exists());
		psmt.setInt(6, DTO.getBc_no());
		
		
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
}
