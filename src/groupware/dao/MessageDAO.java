package groupware.dao;

import groupware.dto.MessageDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO extends CommonDAO {
	private static MessageDAO _instance;
	private ResultSet rs = null;
	private MessageDTO dto = null;
	private SimpleDateFormat format = null;
	
	public static MessageDAO getInstance() {
		if(_instance == null) {
			_instance = new MessageDAO();
			System.out.println("Message 인스턴스 최초 생성");
		}
		return _instance;
	}
	
	
	
	
	/**
	 * 페이징을 위해 받은 쪽지함 리스트 검색
	 * @param mb_no : 멤버 no
	 * @return cnt
	 * @throws SQLException
	 */
	public int ReceiveMesListCnt(int mb_no) throws SQLException {
		DBConnection();
		String sql = "Select count(*) count "
				+ "FROM gw_message A "
				+ "WHERE A.mes_get_mb_no = ? "
				+ "AND A.mes_get_mb_remove = 0 ";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		rs = getQuery(psmt);
		
		int af = 0;
		if (rs.next()) {
			af = rs.getInt("count");
		}
		
		DBClose();
		return af;
	}
	
	/**
	 * 받은 쪽지함 리스트 검색
	 * @param mb_no : 멤버 no
	 * @return list : 쪽지 내용에 대한 DTO의 list
	 * @throws SQLException
	 */
	public List<MessageDTO> ReceiveMesList(int mb_no, int srow) throws SQLException {
		DBConnection();
		List<MessageDTO> list = new ArrayList<MessageDTO>();
		dto = null;
		String sql = "Select A.mes_no, "
				+ "A.mes_subject, "
				+ "A.mes_post_mb_no, "
				+ "B.mb_name mes_post_mb_name, "
				+ "C.posi_name mes_post_mb_posi_name, "
				+ "B.mb_img mes_post_mb_img, "
				+ "A.mes_get_mb_no, "
				+ "A.mes_post_date, "
				+ "A.mes_confirm_date, "
				+ "A.mes_file_exists "
				+ "FROM gw_message A "
				+ "LEFT JOIN gw_member B "
				+ "ON A.mes_post_mb_no = B.mb_no "
				+ "LEFT JOIN gw_position C "
				+ "ON B.mb_position_posi_level = C.posi_level "
				+ "WHERE A.mes_get_mb_no = ? "
				+ "AND A.mes_get_mb_remove = 0 "
				+ "ORDER BY A.mes_no DESC limit ?, 20";
		
		/**
		 * mes_get_mb_remove = 0 : 삭제 안됨
		 * mes_get_mb_remove = 1 : 삭제
		 */
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, srow);
		rs = getQuery(psmt);
		format = new SimpleDateFormat("MM-dd HH:mm");
		
		if (rs.next()) {
			do {
				dto = new MessageDTO();
				dto.setMes_no(rs.getInt("mes_no"));
				dto.setMes_subject(rs.getString("mes_subject"));
				dto.setMes_post_mb_no(rs.getInt("mes_post_mb_no"));
				dto.setMes_post_mb_name(rs.getString("mes_post_mb_name"));
				dto.setMes_post_mb_posi_name(rs.getString("mes_post_mb_posi_name"));
				dto.setMes_post_mb_img(rs.getString("mes_post_mb_img"));
				dto.setMes_get_mb_no(rs.getInt("mes_get_mb_no"));
				dto.setMes_post_date(format.format(rs.getTimestamp("mes_post_date")));
				dto.setMes_confirm_date(rs.getString("mes_confirm_date"));
				dto.setMes_file_exists(rs.getInt("mes_file_exists"));
				list.add(dto);
			} while (rs.next());
		} else {
			dto = new MessageDTO();
			dto.setMes_no(0);
			dto.setMes_subject("쪽지가 없습니다.");
			list.add(dto);
		}
		
		int cnt = GetCnt(psmt);
		list.get(0).setMes_total_cnt(cnt);
		
		DBClose();
		return list;
	}
	
	/**
	 * 페이징을 위한 보낸 쪽지함 리스트 갯수 
	 * @param mb_no
	 * @return
	 * @throws SQLException
	 */
	public int SendMesListCnt(int mb_no) throws SQLException {
		DBConnection();
		String sql = "Select count(*) count "
				+ "FROM gw_message A "
				+ "WHERE A.mes_post_mb_no = ? "
				+ "AND A.mes_post_mb_remove = 0 ";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		rs = getQuery(psmt);
		int af = 0;
		if (rs.next()) {
			af = rs.getInt("count");
		}
		DBClose();
		return af;
	}
	
	/**
	 * 보낸 쪽지함 리스트 검색
	 * @param mb_no : 멤버 no
	 * @return list : 보낸 편지의 내용이 담긴 DTO list
	 * @throws SQLException
	 */
	public List<MessageDTO> SendMesList(int mb_no, int srow) throws SQLException {
		DBConnection();
		List<MessageDTO> list = new ArrayList<MessageDTO>();
		dto = null;
		String sql = "Select A.mes_no, "
				+ "A.mes_subject, "
				+ "A.mes_post_mb_no, "
				+ "A.mes_get_mb_no, "
				+ "B.mb_name mes_get_mb_name, "
				+ "C.posi_name mes_get_mb_posi_name, "
				+ "B.mb_img mes_get_mb_img, "
				+ "A.mes_post_date, "
				+ "A.mes_confirm_date, "
				+ "A.mes_file_exists "
				+ "FROM gw_message A "
				+ "LEFT JOIN gw_member B "
				+ "ON A.mes_get_mb_no = B.mb_no "
				+ "LEFT JOIN gw_position C "
				+ "ON B.mb_position_posi_level = C.posi_level "
				+ "WHERE A.mes_post_mb_no = ? "
				+ "AND A.mes_post_mb_remove = 0 "
				+ "ORDER BY A.mes_no DESC limit ?, 20";
		
		/**
		 * mes_post_mb_remove = 0 : 삭제 안됨
		 * mes_post_mb_remove = 1 : 삭제
		 */
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, srow);
		rs = getQuery(psmt);
		format = new SimpleDateFormat("MM-dd HH:mm");
		
		if (rs.next()) {
			do {
				dto = new MessageDTO();
				dto.setMes_no(rs.getInt("mes_no"));
				dto.setMes_subject(rs.getString("mes_subject"));
				dto.setMes_post_mb_no(rs.getInt("mes_post_mb_no"));
				dto.setMes_get_mb_no(rs.getInt("mes_get_mb_no"));
				dto.setMes_get_mb_name(rs.getString("mes_get_mb_name"));
				dto.setMes_get_mb_posi_name(rs.getString("mes_get_mb_posi_name"));
				dto.setMes_get_mb_img(rs.getString("mes_get_mb_img"));
				dto.setMes_post_date(format.format(rs.getTimestamp("mes_post_date")));
				dto.setMes_confirm_date(rs.getString("mes_confirm_date"));
				dto.setMes_file_exists(rs.getInt("mes_file_exists"));
				list.add(dto);
			} while (rs.next());
		} else {
			dto = new MessageDTO();
			dto.setMes_no(0);
			dto.setMes_subject("쪽지가 없습니다.");
			list.add(dto);
		}
		
		int cnt = GetCnt(psmt);
		list.get(0).setMes_total_cnt(cnt);
		
		DBClose();
		return list;
	}
	
	
	/**
	 * 페이징을 위한 저장된 쪽지들 갯수
	 * @param mb_no
	 * @return
	 * @throws SQLException
	 */
	public int GetStorageListCnt(int mb_no) throws SQLException {
		DBConnection();
		String sql = "Select count(*) count "
				+ "FROM gw_message A "
				+ "WHERE CASE WHEN A.mes_post_mb_no = ? "
				+ "THEN A.mes_post_mb_storage = 1 "
				+ "WHEN A.mes_get_mb_no = ? "
				+ "THEN A.mes_get_mb_storage = 1 END "
				+ "ORDER BY A.mes_no DESC";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, mb_no);
		rs = getQuery(psmt);
		int af = 0;
		if (rs.next()) {
			af = rs.getInt("count");
		}
		DBClose();
		return af;
	}
	
	/**
	 * 저장된 쪽지들 불러오기
	 * @param mb_no : 내 멤버 no
	 * @return list : 쪽지 정보들이 저장된 dto list
	 * @throws SQLException
	 */
	public List<MessageDTO> GetStorageList(int mb_no, int srow) throws SQLException {
		DBConnection();
		List<MessageDTO> list = new ArrayList<MessageDTO>();
		dto = null;
		String sql = "Select A.mes_no, "
				+ "A.mes_subject, "
				+ "A.mes_post_mb_no, "
				+ "B.mb_name mes_post_mb_name, "
				+ "C.posi_name mes_post_mb_posi_name, "
				+ "B.mb_img mes_post_mb_img, "
				+ "A.mes_get_mb_no, "
				+ "D.mb_name mes_get_mb_name, "
				+ "E.posi_name mes_get_mb_posi_name, "
				+ "D.mb_img mes_get_mb_img, "
				+ "A.mes_post_date, "
				+ "A.mes_confirm_date, "
				+ "A.mes_file_exists "
				+ "FROM gw_message A "
				+ "LEFT JOIN gw_member B "
				+ "ON A.mes_post_mb_no = B.mb_no "
				+ "LEFT JOIN gw_position C "
				+ "ON B.mb_position_posi_level = C.posi_level "
				+ "LEFT JOIN gw_member D "
				+ "ON A.mes_get_mb_no = D.mb_no "
				+ "LEFT JOIN gw_position E "
				+ "ON D.mb_position_posi_level = E.posi_level "
				+ "WHERE CASE WHEN A.mes_post_mb_no = ? "
				+ "THEN A.mes_post_mb_storage = 1 "
				+ "WHEN A.mes_get_mb_no = ? "
				+ "THEN A.mes_get_mb_storage = 1 END "
				+ "ORDER BY A.mes_no DESC limit ?, 20";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		psmt.setInt(2, mb_no);
		psmt.setInt(3, srow);
		rs = getQuery(psmt);
		format = new SimpleDateFormat("MM-dd HH:mm");
		
		if (rs.next()) {
			do {
				dto = new MessageDTO();
				dto.setMes_no(rs.getInt("mes_no"));
				dto.setMes_subject(rs.getString("mes_subject"));
				dto.setMes_post_mb_no(rs.getInt("mes_post_mb_no"));
				dto.setMes_post_mb_name(rs.getString("mes_post_mb_name"));
				dto.setMes_post_mb_posi_name(rs.getString("mes_post_mb_posi_name"));
				dto.setMes_post_mb_img(rs.getString("mes_post_mb_img"));
				dto.setMes_get_mb_no(rs.getInt("mes_get_mb_no"));
				dto.setMes_get_mb_name(rs.getString("mes_get_mb_name"));
				dto.setMes_get_mb_posi_name(rs.getString("mes_get_mb_posi_name"));
				dto.setMes_get_mb_img(rs.getString("mes_get_mb_img"));
				dto.setMes_post_date(format.format(rs.getTimestamp("mes_post_date")));
				dto.setMes_confirm_date(rs.getString("mes_confirm_date"));
				dto.setMes_file_exists(rs.getInt("mes_file_exists"));
				list.add(dto);
			} while (rs.next());
		} else {
			dto = new MessageDTO();
			dto.setMes_no(0);
			dto.setMes_subject("쪽지가 없습니다.");
			list.add(dto);
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 메시지 글 번호 받아서 내용 출력
	 * @param mes_no : 메시지 글 번호
	 * @return dto : 내용이 들어있는 DTO
	 * @throws SQLException
	 */
	public MessageDTO GetView(int mes_no) throws SQLException {
		DBConnection();
		dto = null;
		String sql = "select A.mes_no, "
				+ "A.mes_subject, "
				+ "A.mes_content, "
				+ "A.mes_post_mb_no, "
				+ "B.mb_name mes_post_mb_name, "
				+ "C.posi_name mes_post_mb_posi_name, "
				+ "A.mes_get_mb_no, "
				+ "D.mb_name mes_get_mb_name, "
				+ "E.posi_name mes_get_mb_posi_name, "
				+ "A.mes_file_exists "
				+ "from gw_message A "
				+ "LEFT JOIN gw_member B "
				+ "ON A.mes_post_mb_no = B.mb_no "
				+ "LEFT JOIN gw_position C "
				+ "ON B.mb_position_posi_level = C.posi_level "
				+ "LEFT JOIN gw_member D "
				+ "ON A.mes_get_mb_no = D.mb_no "
				+ "LEFT JOIN gw_position E "
				+ "ON D.mb_position_posi_level = E.posi_level "
				+ "WHERE A.mes_no = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mes_no);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			do {
				dto = new MessageDTO();
				dto.setMes_no(rs.getInt("mes_no"));
				dto.setMes_subject(rs.getString("mes_subject"));
				dto.setMes_content(rs.getString("mes_content"));
				dto.setMes_post_mb_no(rs.getInt("mes_post_mb_no"));
				dto.setMes_post_mb_name(rs.getString("mes_post_mb_name"));
				dto.setMes_post_mb_posi_name(rs.getString("mes_post_mb_posi_name"));
				dto.setMes_get_mb_no(rs.getInt("mes_get_mb_no"));
				dto.setMes_get_mb_name(rs.getString("mes_get_mb_name"));
				dto.setMes_get_mb_posi_name(rs.getString("mes_get_mb_posi_name"));
				dto.setMes_file_exists(rs.getInt("mes_file_exists"));
			} while(rs.next());
		} else {
			dto = new MessageDTO();
			dto.setMes_no(0);
		}
		
		DBClose();
		return dto;
	}
	
	/**
	 * DB에서 저장된 파일 정보 검색
	 * @param mes_no : DB message 번호
	 * @param file_part : 어떤 게시판에서 저장된 것인지 종류
	 * @return dto : 파일 정보다 담긴 dto
	 * @throws SQLException
	 */
	public MessageDTO GetFile(int mes_no, String file_part) throws SQLException {
		DBConnection();
		dto = null;
		String sql = "Select file_relname1,"
				+ "file_encname1 "
				+ "FROM gw_file "
				+ "WHERE file_number = ? AND file_part = ?";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mes_no);
		psmt.setString(2, file_part);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			dto = new MessageDTO();
			dto.setMes_file_real_name(rs.getString("file_relname1"));
			dto.setMes_file_enc_name(rs.getString("file_encname1"));
		}
		
		DBClose();
		return dto;
	}
	
	public int SetFile() throws SQLException {
		int af = 0;
		
		return af;
	}
	
	/**
	 * DB에 쪽지 쓰기
	 * @param getLength : getList의 길이
	 * @param getList : 보내는 사람이 담긴 String 배열
	 * @param getDto : 보낼 쪽지의 정보가 담긴 DTO
	 * @return af : 0 - 쓰기 실패
	 * 		   		나머지 - 쓰기 성공
	 * @throws SQLException
	 */
	public int WriteMessage(int getLength, String[] getList, MessageDTO getDto) throws SQLException {
		DBConnection();
		int af = 0;
		int length = getLength;
		String[] list = getList;
		dto = getDto;
		String inputValue = "";
		for (int i = 0; i < length; i++) {
			inputValue = inputValue + "( "
					+ "'" + dto.getMes_subject() + "', "
					+ "'" + dto.getMes_content() + "', "
					+ dto.getMes_post_mb_no() + ", "
					+ list[i] + ", "
					+ 0 + ", "
					+ 0 + ", "
					+ 0 + ", "
					+ 0 + ", "
					+ dto.getMes_file_exists()
					+ " )";
			
			if (!(i == (length-1))) {
				inputValue = inputValue + ", ";
			}
		}
		
		String sql = "Insert into gw_message( "
				+ "mes_subject, "
				+ "mes_content, "
				+ "mes_post_mb_no, "
				+ "mes_get_mb_no, "
				+ "mes_post_mb_storage, "
				+ "mes_get_mb_storage, "
				+ "mes_post_mb_remove, "
				+ "mes_get_mb_remove, "
				+ "mes_file_exists "
				+ " ) VALUES " + inputValue;
		
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 쪽지 쓰기 주소록 불러오기 
	 * @return list : 사원 DTO 리스트
	 * @throws SQLException
	 */
	public List<MessageDTO> getAddress() throws SQLException {
		DBConnection();
		List<MessageDTO> list = new ArrayList<MessageDTO>();
		String sql = "";
		
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		DBClose();
		return list;
	}

	/**
	 * DB 테이블 count 구하는 메소드
	 * @param re_psmt : count할 테이블
	 * @return cnt : 행 갯수
	 * @throws SQLException
	 */
	public int GetCnt(PreparedStatement re_psmt) throws SQLException {
		int cnt = 0;
		String re_sql = re_psmt.toString().substring(re_psmt.toString().indexOf("Select"));
		String sql = "Select count(*) mes_total_cnt "
				+ "FROM (" + re_sql + ") A";
		
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		rs.next();
		
		cnt = rs.getInt("mes_total_cnt");
		return cnt;
	}
	
	/**
	 * mb_no(member 번호)를 받아서 그에 해당하는 member의
	 * 직책과 이름을 검색하는 메소드
	 * @param mb_no : DB member 번호
	 * @return dto : 검색한 member의 dto
	 * @throws SQLException
	 */
	public MessageDTO GetMember(int mb_no) throws SQLException {
		DBConnection();
		dto = null;
		String sql = "Select A.mb_no mes_get_mb_no, "
				+ "A.mb_name mes_get_mb_name, "
				+ "B.posi_name mes_get_posi_name "
				+ "FROM gw_member A "
				+ "LEFT JOIN gw_position B "
				+ "ON A.mb_position_posi_level = B.posi_level "
				+ "WHERE A.mb_no = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		rs = getQuery(psmt);
		
		rs.next();
		dto = new MessageDTO();
		dto.setMes_get_mb_no(rs.getInt("mes_get_mb_no"));
		dto.setMes_get_mb_name(rs.getString("mes_get_mb_name"));
		dto.setMes_get_mb_posi_name(rs.getString("mes_get_posi_name"));
		
		DBClose();
		return dto;
	}
	
	/**
	 * 메시지 삭제 쿼리
	 * @param mes_no : 메시지 no
	 * @return af : 0 - 삭제 실패
	 * 				나머지 - 삭제 성공
	 * @throws SQLException
	 */
	public int DelMessage(String mes_no_list, String category) throws SQLException {
		DBConnection();
		int af = 0;
		String sql =  "";
		
		if (category.equals("index")) {
			sql = "Update gw_message "
					+ "SET mes_get_mb_remove = 1 "
					+ "WHERE mes_no IN ( "
					+ mes_no_list
					+ " )";
			
		} else if (category.equals("send")) {
			sql = "Update gw_message SET "
					+ "mes_post_mb_remove = 1 "
					+ "WHERE mes_no IN ( "
					+ mes_no_list
					+ " )";
		}
		
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 저장된 메시지 삭제 쿼리
	 * @param mes_no_list : 삭제할 mes_no list
	 * @param mb_no : 내 멤버 no
	 * @return af : 0 - 삭제 실패
	 * 				나머지 - 삭제 성공
	 * @throws SQLException
	 */
	public int DelStorageMessage(String mes_no_list, String mb_no) throws SQLException {
		DBConnection();
		int af = 0;
		String sql =  "Update gw_message "
				+ "SET mes_post_mb_storage = CASE "
				+ "WHEN mes_post_mb_no = ? "
				+ "THEN 0 "
				+ "ELSE mes_post_mb_storage END, "
				+ "mes_get_mb_storage = CASE "
				+ "WHEN mes_get_mb_no = ? "
				+ "THEN 0 "
				+ "ELSE mes_get_mb_storage END "
				+ "WHERE mes_no IN ( "
				+ mes_no_list
				+ " )";
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, mb_no);
		psmt.setString(2, mb_no);
		af = setQuery(psmt);

		DBClose();
		return af;
	}
	
	/**
	 * 메시지 저장 쿼리
	 * @param mes_no : 메시지 no
	 * @return af : 0 - 저장 실패
	 * 				나머지 - 저장 성공
	 * @throws SQLException
	 */
	public int StorageMessage(String mes_no_list, String category) throws SQLException {
		DBConnection();
		int af = 0;
		String sql =  "";
		
		if (category.equals("index")) {
			sql = "Update gw_message "
					+ "SET mes_get_mb_storage = 1 "
					+ "WHERE mes_no IN ( "
					+ mes_no_list
					+ " )";
			
		} else if (category.equals("send")) {
			sql = "Update gw_message SET "
					+ "mes_post_mb_storage = 1 "
					+ "WHERE mes_no IN ( "
					+ mes_no_list
					+ " )";
		}
		
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 받은 사람이 메시지 읽을 경우 읽은 시간 기록
	 * @param mes_no : 메시지 no
	 * @return af : 0 - 저장 실패
	 * 				나머지 - 저장 성공
	 * @throws SQLException
	 */
	public int ReceiverReadMessage(int mes_no) throws SQLException {
		DBConnection();
		int af = 0;
		String sql = "Update gw_message "
				+ "SET mes_confirm_date = now() "
				+ "WHERE mes_no = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mes_no);
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 읽지 않은 쪽지 갯수 읽어오기
	 * @param mb_no : 내 멤버 no
	 * @return cnt : 읽지 않은 쪽지 갯수
	 * @throws SQLException
	 */
	public int GetNotReadMessage(int mb_no) throws SQLException {
		DBConnection();
		int cnt = 0;
		String sql = "Select count(*) cnt "
				+ "FROM gw_message "
				+ "WHERE mes_get_mb_no = ? "
				+ "AND mes_confirm_date is null";
		
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, mb_no);
		
		rs = getQuery(psmt);
		rs.next();
		
		cnt = rs.getInt("cnt");
		
		DBClose();
		return cnt;
	}
	
	/****** 안쓰는 쿼리 ******/
	/**
	 * 자신이 보낸 메시지와 받은 메시지의 mes_no를 구분
	 * @param mes_no : 메시지 no
	 * @param mb_no : 자신의 멤버 no
	 * @return list : 내가 보낸 메시지 mes_no가 담긴 String[] 배열과 
	 * 				  내가 받은 메시지 mes_no가 담긴 String[] 배열이 담긴 list
	 * @throws SQLException
	 */
	@SuppressWarnings("null")
	public List<String[]> MessageDistingush(String mes_no_list, String mb_no) throws SQLException {
		DBConnection();
		List<String[]> list = null;
		String[] post_List = null;
		String[] get_List = null;
		int post_Cnt = 0;
		int get_Cnt = 0;
		
		String sql = "Select mes_no, "
				+ "mes_post_mb_no, "
				+ "mes_get_mb_no "
				+ "FROM gw_message "
				+ "WHERE mes_no IN ( "
				+ mes_no_list
				+ " )";
		
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		if (rs.next()) {
			list = new ArrayList<String[]>();
			do {
				if (rs.getInt("mes_post_mb_no") == Integer.parseInt(mb_no)) {
					post_List[post_Cnt] = rs.getString("mes_no");
					post_Cnt = post_Cnt + 1;
				} else {
					get_List[get_Cnt] = rs.getString("mes_no");
					get_Cnt = get_Cnt + 1;
				}
			} while (rs.next());
			
			list.add(post_List);
			list.add(get_List);
		} else {
			
		}
		
		DBClose();
		return list;
	}
	/**************************************************************/
}
