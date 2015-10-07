package groupware.dao;

import groupware.dto.BoardContentDTO;
import groupware.dto.FileDTO;
import groupware.dto.MessageDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileDAO extends ConfigDao {
	private ResultSet rs;
	private int af = 0;
	private static FileDAO _instance;

	public static FileDAO getInstance() {
		if (_instance == null) {
			_instance = new FileDAO();
			System.out.println("File 인스턴스 최초 생성");
		}
		return _instance;
	}
	
	
	public int allFileUpload(int getLength, FileDTO DTO) throws SQLException {
		DBConnection();
		int length = getLength;
		System.out.println("length : "+ length);
		String inputValue = "";
		
		if (length > 1) {
			String[] fNumList = DTO.getFile_number_list().split(",");
			
			for (int i = 0; i < length; i++) {
				inputValue = inputValue 
						+ "("
						+ "'" + DTO.getFile_part() + "', "
						+ fNumList[i] + ", "
						+ "'" + DTO.getFile_relname1() + "', "
						+ "'" + DTO.getFile_encname1() + "', "
						+ "null, "
						+ "null"
						+ ")";
				
				if (i != (length - 1)) {
					inputValue = inputValue + ", ";
				}
			}
		} else {
			inputValue = "("
					+ "'" +DTO.getFile_part() + "', "
					+ DTO.getFile_number() + ", "
					+ "'" +DTO.getFile_relname1() + "', "
					+ "'" +DTO.getFile_encname1() + "', "
					+ "'" +DTO.getFile_relname2() + "', "
					+ "'" +DTO.getFile_encname2()
					+ "')";
		}
		
		String sql = "INSERT INTO gw_file("
				+ "file_part, "
				+ "file_number, "
				+ "file_relname1, "
				+ "file_encname1, "
				+ "file_relname2, "
				+ "file_encname2) VALUES " + inputValue;
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	public int getCommunityBoardNum(BoardContentDTO DTO) throws SQLException {
		DBConnection();

		String sql = "SELECT bc_no FROM "
				+ "gw_board_content WHERE "
				+ "bc_mb_no = ? AND "
				+ "bc_type = ? AND "
				+ "bc_code = ? AND "
				+ "bc_subject = ? AND "
				+ "bc_content = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, DTO.getMb_no());
		psmt.setString(2, DTO.getBc_type());
		psmt.setInt(3, DTO.getBc_code());
		psmt.setString(4, DTO.getBc_subject());
		psmt.setString(5, DTO.getBc_content());
		rs = getQuery(psmt);
		rs.next();
		int af = rs.getInt("bc_no");
		DBClose();
		return af;
	}
	
	/**
	 * 해당 파일을 보낸 mes_no를 검색
	 * @param dto
	 * @return
	 * @throws SQLException
	 */
	public String getMessageNo(int length, MessageDTO dto) throws SQLException {
		DBConnection();
		rs = null;
		int cnt = 0;
		String result = "";
		String sql = "Select mes_no "
				+ "FROM gw_message "
				+ "WHERE mes_subject = ? "
				+ "AND mes_content = ? "
				+ "AND mes_post_mb_no = ? "
				+ "ORDER BY mes_no desc "
				+ "limit ?";
		
		psmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		psmt.setString(1, dto.getMes_subject());
		psmt.setString(2, dto.getMes_content());
		psmt.setInt(3, dto.getMes_post_mb_no());
		psmt.setInt(4, length);
		
		rs = getQuery(psmt);
		rs.last();
		cnt = rs.getRow();
		rs.beforeFirst();
		
		if (rs.next()) {
			do {
				result = result + rs.getInt("mes_no");
				if (rs.getRow() != cnt) {
					result = result + ", ";
				}
			} while (rs.next());
		} else {
			result = "false";
		}
		
		DBClose();
		return result;
	}
	
	public String getFileName(String file, String column) throws SQLException {
		DBConnection();
		String sql = null;
		if (column.equals("first")) {
			sql = "SELECT file_relname1 "
					+ "FROM gw_file "
					+ "WHERE "
					+ "file_encname1 = ?";
		} else if (column.equals("second")) {
			sql = "SELECT file_relname2 "
					+ "FROM gw_file "
					+ "WHERE "
					+ "file_encname2 = ?";
		}
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, file);
		rs = getQuery(psmt);
		String realName = null;
		if (rs.next()) {
			 realName =  rs.getString(1);
		}
		
		DBClose();
		return realName;
	}
	
	/**
	 * 
	 * @param bc_no 파일테이블에서 커뮤니티쪽 자료를 삭제하기 위한 bc_no
	 * @return 삭제된 갯수
	 * @throws SQLException
	 */
	public int deleteFileFromCommunity(int bc_no) throws SQLException {
		DBConnection();
		String sql = "DELETE FROM gw_file WHERE file_number = ? AND file_part = 'COMMUNITY'";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, bc_no);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
}
