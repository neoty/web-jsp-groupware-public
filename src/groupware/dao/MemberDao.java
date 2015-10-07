package groupware.dao;
import groupware.dao.*;
import groupware.dto.DepartmentDTO;
import groupware.dto.MemberDTO;
import groupware.dto.PositionDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends CommonDAO {
	private ResultSet rs;
	int af;
	private static MemberDao _instance;
	public static MemberDao getInstance() {
		if (_instance == null) {
			_instance = new MemberDao();
			System.out.println("Member 인스턴스 최초 생성");
		}
		return _instance;
	}
	
	/**
	 * 회원가입을 위해
	 * @param DTO
	 * @return
	 * @throws SQLException
	 */
	public int MemberJoin(MemberDTO DTO) throws SQLException {
		DBConnection();
		af = 0;
		String sql = "INSERT INTO gw_member("
				+ "mb_id, "
				+ "mb_password, "
				+ "mb_name, "
				+ "mb_email, "
				+ "mb_tel, "
				+ "mb_password_q, "
				+ "mb_password_a, "
				+ "mb_sex, "
				+ "mb_birth, "
				+ "mb_last_ip, "
				+ "mb_last_date, "
				+ "mb_login_count, "
				+ "mb_certify, "
				+ "mb_work_type, "
				+ "mb_rule_ch_no, "
				+ "mb_dep_no, "
				+ "mb_position_posi_level, "
				+ "mb_use, "
				+ "mb_img) VALUES ("
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DTO.getMb_id());
		psmt.setString(2, DTO.getMb_password());
		psmt.setString(3, DTO.getMb_name());
		psmt.setString(4, DTO.getMb_email());
		psmt.setString(5, DTO.getMb_tel());
		psmt.setString(6, DTO.getMb_password_q());
		psmt.setString(7, DTO.getMb_password_a());
		psmt.setString(8, DTO.getMb_sex());
		psmt.setString(9, DTO.getMb_birth());
		psmt.setString(10, DTO.getMb_last_ip());
		psmt.setString(11, DTO.getMb_last_date());
		psmt.setInt(12, DTO.getMb_login_count());
		psmt.setInt(13, DTO.getMb_certify());
		psmt.setInt(14, DTO.getMb_work_type());
		psmt.setInt(15, DTO.getMb_rule_ch_no());
		psmt.setInt(16, DTO.getMb_dep_no());
		psmt.setInt(17, DTO.getMb_position_posi_level());
		psmt.setInt(18, DTO.getMb_use());
		psmt.setString(19, DTO.getMb_img());
		
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 아이디와 암호화된 패스워드를 가지고 일치하는 회원수를 찾는다.
	 * @param ID
	 * @param PASSWORD
	 * @return
	 * @throws SQLException
	 */
	public int LoginCheck(String ID, String PASSWORD) throws SQLException {
		DBConnection();
		
		String sql = "SELECT count(*) count "
				+ "FROM "
				+ "gw_member WHERE "
				+ "mb_id = ? "
				+ "and "
				+ "mb_password = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, ID);
		psmt.setString(2, PASSWORD);
		rs = getQuery(psmt);
		if (rs.next()) {
			af = rs.getInt("count");
		}
		
		DBClose();
		return af;
	}
	
	
	public List<MemberDTO> GetMemberList() throws SQLException {
		DBConnection();
		String sql = "SELECT m.mb_no mb_no, "
				+ "m.mb_name mb_name, "
				+ "p.posi_name posi_name, "
				+ "d.dep_name dep_name "
				+ "FROM gw_member m "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "LEFT JOIN gw_department d ON m.mb_dep_no = d.dep_no "
				+ "ORDER BY d.dep_no ASC";
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		while (rs.next()) {
			MemberDTO b = new MemberDTO();
			b.setMb_no(rs.getInt("mb_no"));
			b.setMb_name(rs.getString("mb_name"));
			b.setPosi_name(rs.getString("posi_name"));
			b.setDep_name(rs.getString("dep_name"));
			list.add(b);
		}
		
		DBClose();
		
		return list;
	}
	
	
	public List<MemberDTO> GetMemberAll(String array) throws SQLException {
		DBConnection();
		String sql = "SELECT m.mb_no mb_no, "
				+ "d.dep_name dep_name,  "
				+ "p.posi_name posi_name, "
				+ "m.mb_name mb_name, "
				+ "m.mb_birth mb_birth, "
				+ "m.mb_tel mb_tel, "
				+ "m.mb_email mb_email, "
				+ "m.mb_join_date mb_join_date, "
				+ "m.mb_certify mb_certify, "
				+ "m.mb_img mb_img "
				+ "FROM gw_member m "
				+ "LEFT JOIN gw_department d ON m.mb_dep_no = d.dep_no "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE m.mb_work_type > 0 "
				+ "ORDER BY "+array;
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		while (rs.next()) {
			MemberDTO b = new MemberDTO();
			b.setMb_no(rs.getInt("mb_no"));
			b.setDep_name(rs.getString("dep_name"));
			b.setPosi_name(rs.getString("posi_name"));
			b.setMb_name(rs.getString("mb_name"));
			b.setMb_birth(rs.getString("mb_birth"));
			b.setMb_tel(rs.getString("mb_tel"));
			b.setMb_email(rs.getString("mb_email"));
			b.setMb_join_date(rs.getDate("mb_join_date"));
			b.setMb_certify(rs.getInt("mb_certify"));
			b.setMb_img(rs.getString("mb_img"));
			list.add(b);
		}
		
		DBClose();
		
		return list;
	}
	
	public int CertifyEdit(int mb_no, int certify_value) throws SQLException {
		DBConnection();
		String sql = "UPDATE gw_member SET mb_certify = ? WHERE mb_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, certify_value);
		psmt.setInt(2, mb_no);
		af = setQuery(psmt);
		
		
		DBClose();
		
		return af;
	}
	
	public int IdDupCheck(String ID) throws SQLException {
		DBConnection();
		String sql = "SELECT count(*) count "
				+ "FROM gw_member "
				+ "WHERE mb_id = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, ID);
		rs = getQuery(psmt);
		if (rs.next()) {
			af = rs.getInt("count");
		}
		
		DBClose();
		return af;
	}
	
	public MemberDTO GetMemberInfo(String ID) throws SQLException {
		DBConnection();
		
		String sql = "SELECT mb_no, mb_work_type, mb_certify, mb_name FROM gw_member WHERE mb_id = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, ID);
		rs = getQuery(psmt);
		MemberDTO memInfo = new MemberDTO();
		if (rs.next()) {
			memInfo.setMb_no(rs.getInt("mb_no"));
			memInfo.setMb_work_type(rs.getInt("mb_work_type"));
			memInfo.setMb_certify(rs.getInt("mb_certify"));
		}
		
		DBClose();
		return memInfo;
	}
	
	public int MemberLoginUpdate(int mb_no, String ip, String date) throws SQLException {
		DBConnection();
		
		String sql = "UPDATE gw_member SET mb_last_ip = ?, mb_last_date = ?, mb_login_count = mb_login_count+1 WHERE mb_no = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, ip);
		psmt.setString(2, date);
		psmt.setInt(3, mb_no);
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 멤버 dep_no, mb_position_posi_level, mb_name 순으로 정렬 후 출력
	 */
	public List<MemberDTO> GetSimpleMemberList() throws SQLException {
		DBConnection();
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		MemberDTO dto = null;
		String sql = "Select A.mb_dep_no, "
				+ "A.mb_no, "
				+ "B.posi_name, "
				+ "A.mb_name "
				+ "FROM gw_member A "
				+ "LEFT JOIN gw_position B "
				+ "ON A.mb_position_posi_level = B.posi_level "
				+ "ORDER BY "
				+ "A.mb_dep_no desc, "
				+ "A.mb_position_posi_level desc, "
				+ "A.mb_name desc";
		
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			do {
				dto = new MemberDTO();
				dto.setMb_dep_no(rs.getInt("mb_dep_no"));
				dto.setMb_no(rs.getInt("mb_no"));
				dto.setPosi_name(rs.getString("posi_name"));
				dto.setMb_name(rs.getString("mb_name"));
				list.add(dto);
			} while (rs.next());
		} else {
			System.out.println("검색 실패.");
		}
		
		DBClose();
		return list;
	}
	
	public List<MemberDTO> GetProjectRelList(int pr_project_no, int mb_no, String array) throws SQLException {
		DBConnection();
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "SELECT m.mb_no, "
				+ "d.dep_name, "
				+ "p.posi_name, "
				+ "m.mb_name, "
				+ "m.mb_tel, "
				+ "m.mb_join_date, "
				+ "m.mb_img, "
				+ "pr.pr_member_no "
				+ "FROM gw_member m "
				+ "LEFT JOIN gw_project_rel pr ON m.mb_no = pr.pr_member_no AND pr.pr_project_no = ? "
				+ "LEFT JOIN gw_department d ON m.mb_dep_no = d.dep_no "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE m.mb_work_type > 0 AND m.mb_no != ? "
				+ "ORDER BY "+array;
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, pr_project_no);
		psmt.setInt(2, mb_no);
		rs = getQuery(psmt);
		
		while (rs.next()) {
			MemberDTO b = new MemberDTO();
			b.setMb_no(rs.getInt("mb_no"));
			b.setDep_name(rs.getString("dep_name"));
			b.setPosi_name(rs.getString("posi_name"));
			b.setMb_name(rs.getString("mb_name"));
			b.setMb_tel(rs.getString("mb_tel"));
			b.setMb_join_date(rs.getDate("mb_join_date"));
			b.setMb_img(rs.getString("mb_img"));
			b.setInvite_no(rs.getInt("pr_member_no"));
			list.add(b);
		}
		
		DBClose();
		return list;
	}
	
	public List<MemberDTO> GetCirclesRelList(int cr_circles_no, int mb_no, String array) throws SQLException {
		DBConnection();
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "SELECT m.mb_no, "
				+ "d.dep_name, "
				+ "p.posi_name, "
				+ "m.mb_name, "
				+ "m.mb_tel, "
				+ "m.mb_join_date, "
				+ "m.mb_img, "
				+ "cr.cr_member_no "
				+ "FROM gw_member m "
				+ "LEFT JOIN gw_circles_rel cr ON m.mb_no = cr.cr_member_no AND cr.cr_circles_no = ? "
				+ "LEFT JOIN gw_department d ON m.mb_dep_no = d.dep_no "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE m.mb_work_type > 0 AND m.mb_no != ? "
				+ "ORDER BY "+array;
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, cr_circles_no);
		psmt.setInt(2, mb_no);
		rs = getQuery(psmt);
		while (rs.next()) {
			MemberDTO b = new MemberDTO();
			b.setMb_no(rs.getInt("mb_no"));
			b.setDep_name(rs.getString("dep_name"));
			b.setPosi_name(rs.getString("posi_name"));
			b.setMb_name(rs.getString("mb_name"));
			b.setMb_tel(rs.getString("mb_tel"));
			b.setMb_join_date(rs.getDate("mb_join_date"));
			b.setMb_img(rs.getString("mb_img"));
			b.setInvite_no(rs.getInt("cr_member_no"));
			list.add(b);
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 멤버 dep_no 검색
	 * @param mb_no : 멤버 no
	 * @return dep_no
	 * @throws SQLException
	 */
	public int GetDepNo(int mb_no) throws SQLException {
		DBConnection();
		af = 0;
		String sql = "SELECT mb_dep_no "
				+ "FROM gw_member "
				+ "WHERE mb_no = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		rs = getQuery(psmt);
		rs.next();
		
		af = rs.getInt("mb_dep_no");
		
		DBClose();
		return af;
	}
	
	/**
	 * 아이디 질문등을 이용해 해당회원이 존재하는지 체크한다.
	 * @param id
	 * @param ques
	 * @param ans
	 * @return
	 * @throws SQLException
	 */
	public int findPassword(String id, String ques, String ans) throws SQLException {
		DBConnection();
		af = 0;
		String sql = "SELECT count(*) count FROM gw_member "
				+ "WHERE mb_id = ? AND mb_password_q = ? AND mb_password_a = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, id);
		psmt.setString(2, ques);
		psmt.setString(3, ans);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			af = rs.getInt("count");
		}
		
		DBClose();
		return af;
	}
	
	/**
	 * 존재하는 회원의 이메일을 흭득한다.
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public String findPasswordGetEmail(String id) throws SQLException {
		DBConnection();
		af = 0;
		String sql = "SELECT mb_email FROM gw_member "
				+ "WHERE mb_id = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, id);
		rs = getQuery(psmt);
		String email = null;
		if (rs.next()) {
			email = rs.getString("mb_email");
		}
		
		DBClose();
		return email;
	}

	public int resetPassword(String mb_id, String password) throws SQLException {
		DBConnection();
		String sql = "UPDATE gw_member SET mb_password = ? "
				+ "WHERE mb_id = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, password);
		psmt.setString(2, mb_id);
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 내 부서원 검색
	 * @param mb_no = 멤버 no
	 * @return
	 * @throws SQLException
	 */
	public List<MemberDTO> GetSameDepMb(int mb_no) throws SQLException {
		DBConnection();
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		MemberDTO dto = null;
		String sql = "SELECT A.mb_no, "
				+ "A.mb_name, "
				+ "B.posi_name "
				+ "FROM gw_member A "
				+ "LEFT JOIN gw_position B "
				+ "ON A.mb_position_posi_level = B.posi_level "
				+ "WHERE A.mb_dep_no = (SELECT mb_dep_no FROM gw_member WHERE mb_no = ?) "
				+ "AND A.mb_no != ? "
				+ "AND mb_use = 1";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, mb_no);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			do {
				dto = new MemberDTO();
				dto.setMb_no(rs.getInt("A.mb_no"));
				dto.setMb_name(rs.getString("A.mb_name"));
				dto.setPosi_name(rs.getString("B.posi_name"));
				list.add(dto);
			} while (rs.next());
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 멤버 이름, 직책 검색
	 * @param mb_no = 멤버 no
	 * @param my_mb_no = 내 멤버 no
	 * @return
	 * @throws SQLException
	 */
	public MemberDTO GetMbName(int mb_no, int my_mb_no) throws SQLException {
		DBConnection();
		MemberDTO dto = null;
		String sql = "SELECT A.mb_name, "
				+ "B.posi_name "
				+ "FROM gw_member A "
				+ "LEFT JOIN gw_position B "
				+ "ON A.mb_position_posi_level = B.posi_level "
				+ "WHERE mb_no = ? "
				+ "AND mb_dep_no = (SELECT mb_dep_no FROM gw_member WHERE mb_no = ?)";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, my_mb_no);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			dto = new MemberDTO();
			dto.setMb_name(rs.getString("A.mb_name"));
			dto.setPosi_name(rs.getString("B.posi_name"));
		}
		
		DBClose();
		return dto;
	}
	
	/**
	 * 회원 정보 수저을 위해 mb_no 를 기준으로 회원 정보를 가져온다.
	 * @param mb_no
	 * @return
	 * @throws SQLException
	 */
	public MemberDTO getMemAsSession(int mb_no) throws SQLException {
		DBConnection();
		
		String sql = "SELECT "
				+ "mb_email, "
				+ "mb_tel, "
				+ "mb_position_posi_level, "
				+ "posi_name, "
				+ "mb_dep_no, "
				+ "dep_name, "
				+ "mb_password_q, "
				+ "mb_password_a, "
				+ "mb_img "
				+ "FROM gw_member m "
				+ "LEFT JOIN gw_position p ON p.posi_level = m.mb_position_posi_level "
				+ "LEFT JOIN gw_department d ON d.dep_no = m.mb_dep_no "
				+ "WHERE m.mb_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		rs = getQuery(psmt);
		
		MemberDTO n = null;
		if (rs.next()) {
			n = new MemberDTO();
			n.setMb_email(rs.getString("mb_email"));
			n.setMb_tel(rs.getString("mb_tel"));
			n.setMb_position_posi_level(rs.getInt("mb_position_posi_level"));
			n.setPosi_name(rs.getString("posi_name"));
			n.setMb_dep_no(rs.getInt("mb_dep_no"));
			n.setDep_name(rs.getString("dep_name"));
			n.setMb_password_q(rs.getString("mb_password_q"));
			n.setMb_password_a(rs.getString("mb_password_a"));
			n.setMb_img(rs.getString("mb_img"));
		}
		
		DBClose();
		return n;
	}
	
	/**
	 * 회원 정보 수정
	 * @param DTO
	 * @param sql
	 * @param type
	 * @param mb_no
	 * @return
	 * @throws SQLException
	 */
	public int modifyMemberInfo(MemberDTO DTO, String sql, int type, int mb_no) throws SQLException {
		DBConnection();
		psmt = conn.prepareStatement(sql);
		if (type == 1) {
			psmt.setString(1, DTO.getMb_email());
			psmt.setString(2, DTO.getMb_tel());
			psmt.setString(3, DTO.getMb_password_q());
			psmt.setString(4, DTO.getMb_password_a());
			psmt.setInt(5, DTO.getMb_dep_no());
			psmt.setInt(6, DTO.getMb_position_posi_level());
			psmt.setString(7, DTO.getMb_img());
			psmt.setInt(8, mb_no);
		} else {
			psmt.setString(1, DTO.getMb_password());
			psmt.setString(2, DTO.getMb_email());
			psmt.setString(3, DTO.getMb_tel());
			psmt.setString(4, DTO.getMb_password_q());
			psmt.setString(5, DTO.getMb_password_a());
			psmt.setInt(6, DTO.getMb_dep_no());
			psmt.setInt(7, DTO.getMb_position_posi_level());
			psmt.setString(8, DTO.getMb_img());
			psmt.setInt(9, mb_no);
		}
		af = setQuery(psmt);
		System.out.println("적용된 로우"+af);
		DBClose();
		return af;
	}
	
	/**
	 * 멤버 정보 검색 (하나 필요할것 같았음.)
	 * @param mb_no : 멤버 번호
	 * @return 멤버 정보 dto
	 * @throws SQLException
	 */
	public MemberDTO getMemberInfoAll(int mb_no) throws SQLException {
		MemberDTO dto = null;
		ResultSet rs = null;
		DBConnection();
		String sql = "SELECT A.mb_no, "
				+ "A.mb_name, "
				+ "A.mb_email, "
				+ "A.mb_tel, "
				+ "A.mb_sex, "
				+ "A.mb_birth, "
				+ "A.mb_work_type, "
				+ "A.mb_dep_no, "
				+ "B.dep_name, "
				+ "A.mb_position_posi_level, "
				+ "C.posi_name "
				+ "FROM gw_member A "
				+ "LEFT JOIN gw_department B "
				+ "ON A.mb_dep_no = B.dep_no "
				+ "LEFT JOIN gw_position C "
				+ "ON A.mb_position_posi_level = C.posi_level "
				+ "WHERE A.mb_no = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			dto = new MemberDTO();
			dto.setMb_no(rs.getInt("A.mb_no"));
			dto.setMb_name(rs.getString("A.mb_name"));
			dto.setMb_email(rs.getString("A.mb_email"));
			dto.setMb_tel(rs.getString("A.mb_tel"));
			dto.setMb_sex(rs.getString("A.mb_sex"));
			dto.setMb_birth(rs.getString("A.mb_birth"));
			dto.setMb_work_type(rs.getInt("A.mb_work_type"));
			dto.setMb_dep_no(rs.getInt("A.mb_dep_no"));
			dto.setDep_name(rs.getString("B.dep_name"));
			dto.setMb_position_posi_level(rs.getInt("A.mb_position_posi_level"));
			dto.setPosi_name(rs.getString("C.posi_name"));
		} else {
			// 에러
		}
		
		DBClose();
		return dto;
	}
}
