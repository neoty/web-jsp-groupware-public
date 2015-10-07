package groupware.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import groupware.dto.WorkFlowDTO;

import com.mysql.jdbc.PreparedStatement;

public class WorkFlowDAO extends CommonDAO {
	private ResultSet rs;
	private int af = 0;
	private static WorkFlowDAO _instance;

	public static WorkFlowDAO getInstance() {
		if (_instance == null) {
			_instance = new WorkFlowDAO();
		}
		return _instance;
	}
	/**************************/
	/**
	 * wf_status 상태
	 * 0 = 작성 대기
	 * 1 = 결재 진행
	 * 2 = 결재 보류
	 * 3 = 결재 반려
	 * 4 = 결재 완료
	 */
	/**************************/
	
	/**************************/
	
	/**
	 * wf_mb_XXXXXX 상태
	 * 0 = 결재 대기(아직 결재 차례가 안됨)
	 * 1 = 결재 준비(결재 가능한 상태)
	 * 2 = 결재 보류(결재 보류함)
	 * 3 = 결재 반려
	 * 4 = 결재 승인
	 */
	/**************************/
	
	/**
	 * work_status = 4 인 항목들의 숫자
	 * @return
	 * @throws SQLException
	 */
	public int getPublicNoticeCnt() throws SQLException {
		DBConnection();
		String sql = "SELECT count(*) count FROM gw_workflow A "
				+ "WHERE wf_status = 4";
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			af = rs.getInt("count");
		}
		DBClose();
		return af;
	}
	
	/**
	 * 결재 공지 부분 뽑기
	 * @param srow
	 * @return
	 * @throws SQLException
	 */
	public List<WorkFlowDTO> getPublicNoticeList(int srow) throws SQLException {
		String file_icon = "";
		String[] file_icon_extension;
		DBConnection();
		String sql = "SELECT "
				+ "a.wf_mb_first no1, "
				+ "m1.mb_name name1, "
				+ "m1.mb_img img1, "
				+ "p1.posi_name posi1, "
				+ "a.wf_mb_second no2, "
				+ "m2.mb_name name2, "
				+ "m2.mb_img img2, "
				+ "p2.posi_name posi2, "
				+ "a.wf_mb_third no3, "
				+ "m3.mb_name name3, "
				+ "m3.mb_img img3, "
				+ "p3.posi_name posi3, "
				+ "a.wf_no, "
				+ "a.wf_doc_no, "
				+ "a.wf_subject, "
				+ "f.file_relname1, "
				+ "f.file_encname1, "
				+ "f.file_relname2, "
				+ "f.file_encname2 "
				+ "FROM gw_workflow a "
				+ "LEFT JOIN gw_file f ON f.file_part = 'WORK' AND f.file_number = a.wf_no "
				+ "LEFT JOIN gw_member m1 ON m1.mb_no = a.wf_mb_first "
				+ "LEFT JOIN gw_member m2 ON m2.mb_no = a.wf_mb_second "
				+ "LEFT JOIN gw_member m3 ON m3.mb_no = a.wf_mb_third "
				+ "LEFT JOIN gw_position p1 ON p1.posi_level = m1.mb_position_posi_level "
				+ "LEFT JOIN gw_position p2 ON p2.posi_level = m2.mb_position_posi_level "
				+ "LEFT JOIN gw_position p3 ON p3.posi_level = m3.mb_position_posi_level "
				+ "WHERE a.wf_status = 4 ORDER BY wf_no LIMIT ?, 20";
		psmt  = conn.prepareStatement(sql);
		psmt.setInt(1, srow);
		rs = getQuery(psmt);
		List<WorkFlowDTO> list = new ArrayList<WorkFlowDTO>();
		while (rs.next()) {
			WorkFlowDTO dto = new WorkFlowDTO();
			if (rs.getInt("no3") > 0) {
				dto.setLastApprove_mb_name(rs.getString("name3"));
				dto.setLastApprove_mb_no(rs.getInt("no3"));
				dto.setLastApprove_mb_img(rs.getString("img3"));
				dto.setLastApprove_mb_posi_name(rs.getString("posi3"));
			} else if (rs.getInt("no2") > 0) {
				dto.setLastApprove_mb_name(rs.getString("name2"));
				dto.setLastApprove_mb_no(rs.getInt("no2"));
				dto.setLastApprove_mb_img(rs.getString("img2"));
				dto.setLastApprove_mb_posi_name(rs.getString("posi2"));
			} else {
				dto.setLastApprove_mb_name(rs.getString("name1"));
				dto.setLastApprove_mb_no(rs.getInt("no1"));
				dto.setLastApprove_mb_img(rs.getString("img1"));
				dto.setLastApprove_mb_posi_name(rs.getString("posi1"));
			}
			dto.setWf_no(rs.getInt("wf_no"));
			dto.setWf_doc_no(rs.getString("wf_doc_no"));
			dto.setWf_subject(rs.getString("wf_subject"));
			dto.setOriDocName(rs.getString("file_relname1"));
			dto.setEncDocName(rs.getString("file_encname1"));
			dto.setOriFileName(rs.getString("file_relname2"));
			dto.setEncFileName(rs.getString("file_encname2"));
			file_icon = rs.getString("f.file_relname1");
			file_icon_extension = file_icon.split("[.]");
			dto.setFile_icon(file_icon_extension[file_icon_extension.length - 1]);
			list.add(dto);
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 결재 정보 DB 등록 
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public int inputDocNo(WorkFlowDTO dto) throws SQLException {
		int af = 0;
		ResultSet rs = null;
		DBConnection();
		String sql = "INSERT INTO gw_workflow ( "
				+ "wf_post_mb, "
				+ "wf_status, "
				+ "wf_doc_no, "
				+ "wf_mb_first, "
				+ "wf_mb_second, "
				+ "wf_mb_third, "
				+ "wf_subject "
				+ " ) "
				+ " VALUES ( ?,?,?,?,?,?,? )";
		
		psmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		psmt.setInt(1, dto.getWf_post_mb());
		psmt.setInt(2, dto.getWf_status());
		psmt.setString(3, dto.getWf_doc_no());
		psmt.setInt(4, dto.getWf_mb_first());
		psmt.setInt(5, dto.getWf_mb_second());
		psmt.setInt(6, dto.getWf_mb_third());
		psmt.setString(7, dto.getWf_subject());
		
		setQuery(psmt);
		rs = psmt.getGeneratedKeys();
		if (rs.next()) {
			af = rs.getInt(1);
		}
		
		DBClose();
		return af;
	}
	
	/**
	 * 해당 부서의 다음 문서 번호 검색
	 * @param mDoc_no : 부서명-년도-
	 * @return doc_no : 문서 번호
	 * @throws SQLException
	 */
	public int getDocNo(String mDoc_no) throws SQLException {
		int doc_no = 0;
		ResultSet rs = null;
		String[] tmp;
		DBConnection();
		String sql = "SELECT wf_doc_no "
				+ "FROM gw_workflow "
				+ "WHERE wf_doc_no like ? "
				+ "order by wf_no desc "
				+ "limit 1";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, mDoc_no + "%");
		
		rs = getQuery(psmt);
		if (rs.next()) {
			tmp = rs.getString("wf_doc_no").split("-");
			doc_no = Integer.parseInt(tmp[2]);
		} else {
			doc_no = 0;
		}
		
		DBClose();
		return doc_no;
	}
	
	/**
	 * 결재 문서 작성 취소시
	 * 해당 정보를 디비에서 삭제
	 * @param mb_no
	 * @param doc_no
	 * @param work_doc_no
	 * @return
	 * @throws SQLException
	 */
	public int delDocInfo(int mb_no, int work_doc_no, String doc_no) throws SQLException {
		int af = 0;
		DBConnection();
		String sql = "DELETE FROM gw_workflow "
				+ "WHERE wf_no = ? "
				+ "AND wf_post_mb = ? "
				+ "AND wf_doc_no = ? ";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, work_doc_no);
		psmt.setInt(2, mb_no);
		psmt.setString(3, doc_no);
		
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 작성 완료버튼 누를 시 첫번째 결재자 상태를 1로 변경
	 * @param work_doc_no
	 * @param mb_no
	 * @param doc_no
	 * @return
	 * @throws SQLException
	 */
	public int writeAppoveDoc(int work_doc_no, int mb_no, String doc_no) throws SQLException {
		int af = 0;
		DBConnection();
		String sql = "UPDATE gw_workflow "
				+ "SET wf_first_status = 1, "
				+ "wf_status = 1 "
				+ "WHERE wf_no = ? "
				+ "AND wf_post_mb = ? "
				+ "AND wf_doc_no = ? ";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, work_doc_no);
		psmt.setInt(2, mb_no);
		psmt.setString(3, doc_no);
		
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 결재 리스트 가져오기
	 * @param mb_no
	 * @param search_list : 가져올 결재 상태
	 * @param srow : 페이징 목록 시작부분
	 * @return
	 * @throws SQLException
	 */
	public List<WorkFlowDTO> getWorkList(int mb_no, int search_list, int srow) throws SQLException {
		List<WorkFlowDTO> list = new ArrayList<WorkFlowDTO>();
		ResultSet rs = null;
		WorkFlowDTO dto = null;
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		String file_icon = "";
		String[] file_icon_extension;
		DBConnection();
		String sql = "SELECT A.wf_no, "
				+ "A.wf_post_mb, "
				+ "A.wf_status, "
				+ "A.wf_doc_no, "
				+ "A.wf_mb_first, "
				+ "A.wf_first_status, "
				+ "A.wf_first_confirm_date, "
				+ "A.wf_mb_second, "
				+ "A.wf_second_status, "
				+ "A.wf_second_confirm_date, "
				+ "A.wf_mb_third, "
				+ "A.wf_third_status, "
				+ "A.wf_third_confirm_date, "
				+ "A.wf_subject, "
				+ "A.wf_create_date, "
				+ "B.file_relname1, "
				+ "B.file_encname1, "
				+ "B.file_relname2, "
				+ "B.file_encname2 "
				+ "FROM gw_workflow A "
				+ "LEFT JOIN gw_file B "
				+ "ON B.file_part = 'WORK' AND B.file_number = A.wf_no "
				+ "WHERE (A.wf_post_mb = ? "
				+ "OR ( "
				+ "A.wf_mb_first = ? AND wf_first_status > 0 "
				+ " ) "
				+ "OR ( "
				+ "A.wf_mb_second = ? AND wf_second_status > 0 "
				+ " ) "
				+ "OR ( "
				+ "A.wf_mb_third = ? AND wf_third_status > 0 "
				+ " )) ";
		if (search_list == 0) {
			sql = sql + "ORDER BY A.wf_no desc limit ?, 20";
		} else {
			sql = sql + "AND wf_status = " + search_list + " "
					+ "ORDER BY A.wf_no desc limit ?, 20";
		}
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, mb_no);
		psmt.setInt(3, mb_no);
		psmt.setInt(4, mb_no);
		psmt.setInt(5, srow);
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			do {
				dto = new WorkFlowDTO();
				dto.setWf_no(rs.getInt("A.wf_no"));
				dto.setWf_post_mb(rs.getInt("A.wf_post_mb"));
				dto.setWf_status(rs.getInt("A.wf_status"));
				dto.setWf_doc_no(rs.getString("A.wf_doc_no"));
				dto.setWf_mb_first(rs.getInt("A.wf_mb_first"));
				dto.setWf_first_status(rs.getInt("A.wf_first_status"));
				dto.setWf_first_confirm_date(subStrConfirmDate(rs.getString("A.wf_first_confirm_date")));
				dto.setWf_mb_second(rs.getInt("A.wf_mb_second"));
				dto.setWf_second_status(rs.getInt("A.wf_second_status"));
				dto.setWf_second_confirm_date(subStrConfirmDate(rs.getString("A.wf_second_confirm_date")));
				dto.setWf_mb_third(rs.getInt("A.wf_mb_third"));
				dto.setWf_third_status(rs.getInt("A.wf_third_status"));
				dto.setWf_third_confirm_date(subStrConfirmDate(rs.getString("A.wf_third_confirm_date")));
				dto.setWf_subject(rs.getString("A.wf_subject"));
				dto.setWf_create_date(format.format(rs.getTimestamp("A.wf_create_date")));
				dto.setOriDocName(rs.getString("B.file_relname1"));
				dto.setEncDocName(rs.getString("B.file_encname1"));
				dto.setOriFileName(rs.getString("B.file_relname2"));
				dto.setEncFileName(rs.getString("B.file_encname2"));
				
				file_icon = rs.getString("B.file_relname1");
				file_icon_extension = file_icon.split("[.]");
				dto.setFile_icon(file_icon_extension[file_icon_extension.length - 1]);
				
				list.add(dto);
			} while (rs.next());
		} else {
			// error
			list = null;
		}
		
		DBClose();
		return list;
	}
	
	
	/**
	 * 결재 리스트 카운트
	 * @param mb_no
	 * @return
	 * @throws SQLException
	 */
	public int getWorkListCnt(int mb_no, int search_list) throws SQLException {
		int cnt = 0;
		ResultSet rs = null;
		DBConnection();
		String sql = "SELECT COUNT(*) cnt "
				+ "FROM gw_workflow A "
				+ "LEFT JOIN gw_file B "
				+ "ON B.file_part = 'WORK' AND B.file_number = A.wf_no "
				+ "WHERE (A.wf_post_mb = ? "
				+ "OR ( "
				+ "A.wf_mb_first = ? AND wf_first_status > 0 "
				+ " ) "
				+ "OR ( "
				+ "A.wf_mb_second = ? AND wf_second_status > 0 "
				+ " ) "
				+ "OR ( "
				+ "A.wf_mb_third = ? AND wf_third_status > 0 "
				+ " )) ";
		if (search_list == 0) {
			sql = sql + "ORDER BY A.wf_no desc";
		} else {
			sql = sql + "AND wf_status = " + search_list + " "
					+ "ORDER BY A.wf_no desc";
		}
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, mb_no);
		psmt.setInt(3, mb_no);
		psmt.setInt(4, mb_no);
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			cnt = rs.getInt("cnt");
		} else {
			// error
		}
		
		DBClose();
		return cnt;
	}
	
	
	/**
	 * Confirm Date 날짜가 있을 경우 데이터 형 변환
	 * ex) 2014-07-07 12:35:11 --> 2014-07-07 12:35
	 * @param str
	 * @return
	 */
	private String subStrConfirmDate(String str) {
		String subStr = null;
		if (str == null || "".equals(str)) {
			subStr = str;
		} else {
			subStr = str.substring(0, 16);
		}
		
		return subStr;
	}
	
	
	/**
	 * 결재 문서 정보 가져오기.
	 * @param wf_no
	 * @param mb_no
	 * @return
	 * @throws SQLException
	 */
	public WorkFlowDTO getWorkDoc(int wf_no, int mb_no) throws SQLException {
		WorkFlowDTO dto = null;
		ResultSet rs = null;
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		DBConnection();
		String sql = "SELECT A.wf_no, "
				+ "A.wf_post_mb, "
				+ "B.mb_name, "
				+ "C.posi_name, "
				+ "D.dep_name, "
				+ "A.wf_status, "
				+ "A.wf_doc_no, "
				+ "A.wf_mb_first, "
				+ "E.mb_name, "
				+ "F.posi_name, "
				+ "G.dep_name, "
				+ "A.wf_first_status, "
				+ "A.wf_first_note, "
				+ "A.wf_first_confirm_date, "
				+ "A.wf_mb_second, "
				+ "H.mb_name, "
				+ "I.posi_name, "
				+ "J.dep_name, "
				+ "A.wf_second_status, "
				+ "A.wf_second_note, "
				+ "A.wf_second_confirm_date, "
				+ "A.wf_mb_third, "
				+ "K.mb_name, "
				+ "L.posi_name, "
				+ "M.dep_name, "
				+ "A.wf_third_status, "
				+ "A.wf_third_note, "
				+ "A.wf_third_confirm_date, "
				+ "A.wf_subject, "
				+ "A.wf_create_date, "
				+ "N.file_relname1, "
				+ "N.file_encname1, "
				+ "N.file_relname2, "
				+ "N.file_encname2 "
				+ "FROM gw_workflow A "
				+ "LEFT JOIN gw_member B "
				+ "ON A.wf_post_mb = B.mb_no "
				+ "LEFT JOIN gw_position C "
				+ "ON C.posi_level = B.mb_position_posi_level "
				+ "LEFT JOIN gw_department D "
				+ "ON D.dep_no = B.mb_dep_no "
				+ "LEFT JOIN gw_member E "
				+ "ON E.mb_no = A.wf_mb_first "
				+ "LEFT JOIN gw_position F "
				+ "ON F.posi_level = E.mb_position_posi_level "
				+ "LEFT JOIN gw_department G "
				+ "ON G.dep_no = E.mb_dep_no "
				+ "LEFT JOIN gw_member H "
				+ "ON H.mb_no = A.wf_mb_second "
				+ "LEFT JOIN gw_position I "
				+ "ON I.posi_level = H.mb_position_posi_level "
				+ "LEFT JOIN gw_department J "
				+ "ON J.dep_no = H.mb_dep_no "
				+ "LEFT JOIN gw_member K "
				+ "ON K.mb_no = A.wf_mb_third "
				+ "LEFT JOIN gw_position L "
				+ "ON L.posi_level = K.mb_position_posi_level "
				+ "LEFT JOIN gw_department M "
				+ "ON M.dep_no = K.mb_dep_no "
				+ "LEFT JOIN gw_file N "
				+ "ON N.file_part = 'WORK' AND N.file_number = A.wf_no "
				+ "WHERE A.wf_no = ? "
				+ "AND (A.wf_post_mb = ? "
				+ "OR ( "
				+ "A.wf_mb_first = ? AND wf_first_status > 0 "
				+ " ) "
				+ "OR ( "
				+ "A.wf_mb_second = ? AND wf_second_status > 0 "
				+ " ) "
				+ "OR ( "
				+ "A.wf_mb_third = ? AND wf_third_status > 0 "
				+ " )) ";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, wf_no);
		psmt.setInt(2, mb_no);
		psmt.setInt(3, mb_no);
		psmt.setInt(4, mb_no);
		psmt.setInt(5, mb_no);
		
		rs = getQuery(psmt);
		
		if (rs.next()) {
			dto = new WorkFlowDTO();
			dto.setWf_no(rs.getInt("A.wf_no"));
			dto.setWf_post_mb(rs.getInt("A.wf_post_mb"));
			dto.setWf_post_mb_name(rs.getString("B.mb_name"));
			dto.setWf_post_posi_name(rs.getString("C.posi_name"));
			dto.setWf_post_dep_name(rs.getString("D.dep_name"));
			dto.setWf_status(rs.getInt("A.wf_status"));
			dto.setWf_doc_no(rs.getString("A.wf_doc_no"));
			dto.setWf_mb_first(rs.getInt("A.wf_mb_first"));
			dto.setWf_first_mb_name(rs.getString("E.mb_name"));
			dto.setWf_first_posi_name(rs.getString("F.posi_name"));
			dto.setWf_first_dep_name(rs.getString("G.dep_name"));
			dto.setWf_first_status(rs.getInt("A.wf_first_status"));
			dto.setWf_first_note(rs.getString("A.wf_first_note"));
			dto.setWf_first_confirm_date(subStrConfirmDate(rs.getString("A.wf_first_confirm_date")));
			dto.setWf_mb_second(rs.getInt("A.wf_mb_second"));
			dto.setWf_second_mb_name(rs.getString("H.mb_name"));
			dto.setWf_second_posi_name(rs.getString("I.posi_name"));
			dto.setWf_second_dep_name(rs.getString("J.dep_name"));
			dto.setWf_second_status(rs.getInt("A.wf_second_status"));
			dto.setWf_second_note(rs.getString("A.wf_second_note"));
			dto.setWf_second_confirm_date(subStrConfirmDate(rs.getString("A.wf_second_confirm_date")));
			dto.setWf_mb_third(rs.getInt("A.wf_mb_third"));
			dto.setWf_third_mb_name(rs.getString("K.mb_name"));
			dto.setWf_third_posi_name(rs.getString("L.posi_name"));
			dto.setWf_third_dep_name(rs.getString("M.dep_name"));
			dto.setWf_third_status(rs.getInt("A.wf_third_status"));
			dto.setWf_third_note(rs.getString("A.wf_third_note"));
			dto.setWf_third_confirm_date(subStrConfirmDate(rs.getString("A.wf_third_confirm_date")));
			dto.setWf_subject(rs.getString("A.wf_subject"));
			dto.setWf_create_date(format.format(rs.getTimestamp("A.wf_create_date")));
			dto.setOriDocName(rs.getString("N.file_relname1"));
			dto.setEncDocName(rs.getString("N.file_encname1"));
			dto.setOriFileName(rs.getString("N.file_relname2"));
			dto.setEncFileName(rs.getString("N.file_encname2"));
		} else {
			// error
		}
		
		DBClose();
		return dto;
	}
	
	/**
	 * 결재 선택시 상태 변경
	 * @param mb_no
	 * @param wf_status 사용자가 선택한 상태
	 * @param wf_no
	 * @param confirm_note
	 * @param confirm_no
	 * @return
	 * @throws SQLException
	 */
	public int confirmDocInfo(int mb_no, int wf_status, int wf_no, String confirm_note, int confirm_no) throws SQLException {
		int af = 0;
		DBConnection();
		String sql = "";
		int input_next_status = 0;
		int input_wf_status = 0;
		
		switch (confirm_no) {
		case 1:
			if (wf_status == 4) {
				input_next_status = 1;
			} else {
				input_next_status = 0;
			}
			
			if (wf_status == 2) {
				input_wf_status = 2;
			} else if (wf_status == 3) {
				input_wf_status = 3;
			} else if (wf_status == 4) {
				input_wf_status = 1;
			}
			
			sql = "UPDATE gw_workflow "
					+ "SET wf_first_status = ?, "
					+ "wf_first_note = ?, "
					+ "wf_second_status = "
					+ "if ( "
					+ "wf_mb_second = 0, "
					+ "0, "
					+ input_next_status + " "
					+ " ), "
					+ "wf_first_confirm_date = now(), "
					+ "wf_status = CASE "
					+ "WHEN wf_mb_second = 0 "
					+ "THEN " + wf_status + " "
					+ "WHEN wf_mb_second != 0 "
					+ "THEN " + input_wf_status + " "
					+ "END "
					+ "WHERE wf_no = ? "
					+ "AND wf_mb_first = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, wf_status);
			psmt.setString(2, confirm_note);
			psmt.setInt(3, wf_no);
			psmt.setInt(4, mb_no);
			break;
		case 2:
			if (wf_status == 4) {
				input_next_status = 1;
			} else {
				input_next_status = 0;
			}
			
			if (wf_status == 2) {
				input_wf_status = 2;
			} else if (wf_status == 3) {
				input_wf_status = 3;
			} else if (wf_status == 4) {
				input_wf_status = 1;
			}
			
			sql = "UPDATE gw_workflow "
					+ "SET wf_second_status = ?, "
					+ "wf_second_note = ?, "
					+ "wf_third_status = "
					+ "if ( "
					+ "wf_mb_third = 0, "
					+ "0, "
					+ input_next_status + " "
					+ " ), "
					+ "wf_second_confirm_date = now(), "
					+ "wf_status = CASE "
					+ "WHEN wf_mb_third = 0 "
					+ "THEN " + wf_status + " "
					+ "WHEN wf_mb_third != 0 "
					+ "THEN " + input_wf_status + " "
					+ "END "
					+ "WHERE wf_no = ? "
					+ "AND wf_mb_second = ? ";
			
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, wf_status);
			psmt.setString(2, confirm_note);
			psmt.setInt(3, wf_no);
			psmt.setInt(4, mb_no);
			break;
		case 3:
			sql = "UPDATE gw_workflow "
					+ "SET wf_third_status = ?, "
					+ "wf_third_note = ?, "
					+ "wf_third_confirm_date = now(), "
					+ "wf_status = "
					+ wf_status + " "
					+ "WHERE wf_no = ? "
					+ "AND wf_mb_third = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, wf_status);
			psmt.setString(2, confirm_note);
			psmt.setInt(3, wf_no);
			psmt.setInt(4, mb_no);
			break;
		}
		
		af = setQuery(psmt);
		DBClose();
		return af;
	}
}
