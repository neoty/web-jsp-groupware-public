package groupware.dao;

import groupware.dto.BoardContentDTO;
import groupware.dto.BoardDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainDAO extends CommonDAO {
	private ResultSet rs;
	private int af = 0;
	private static MainDAO _instance;

	public static MainDAO getInstance() {
		if (_instance == null) {
			_instance = new MainDAO();
			System.out.println("Main 인스턴스 최초 생성");
		}
		return _instance;
	}
	
	
	/**
	 * 최근 게시물의 동적인 변화를 위해 총 생성된 PUBLIC 보드의 리스트를 가져온다 bo_no, bo_name
	 * @return
	 * @throws SQLException
	 */
	public List<BoardDTO> getPublicList() throws SQLException {
		DBConnection();
		String sql = "SELECT bo_no, bo_name FROM gw_board "
				+ "WHERE bo_type = 'PUBLIC'";
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		while (rs.next()) {
			BoardDTO n = new BoardDTO();
			n.setBo_no(rs.getInt("bo_no"));
			n.setBo_name(rs.getString("bo_name"));
			list.add(n);
		}
		DBClose();
		return list;
	}
	
	
	/**
	 * 동적으로 가져온 PUBLIC 리스트에 대한 각 속성에 대한 값을 뽑아 낸다 6개 기준
	 * @param boardNamelist
	 * @return
	 * @throws SQLException
	 */
	public List<BoardContentDTO> getPublicContent(List<BoardDTO> boardNamelist) throws SQLException {
		DBConnection();
		/**
		 * 나중에 연구가 필요한 sql group by 로 다중행 가져 오기 리미트 걸어서 
		 */
		List<BoardDTO> boardNamebcList = new ArrayList<BoardDTO>(boardNamelist);
		int[] nameArr = new int[boardNamebcList.size()];
		
		for (int i = 0; i < boardNamebcList.size(); i++) {
			nameArr[i] = boardNamebcList.get(i).getBo_no();
		}
		/*
		 * 배열 길이 만큼(게시판수) 돌며 리스트에 축적한다.
		 */
		List<BoardContentDTO> boardContentList = new ArrayList<BoardContentDTO>();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		for (int i = 0; i < nameArr.length; i++) {
			String sql = "SELECT * FROM gw_board_content WHERE bc_code = "+nameArr[i]+" ORDER BY bc_no desc limit 6";
			psmt = conn.prepareStatement(sql);
			rs = getQuery(psmt);
			while (rs.next()) {
				BoardContentDTO n = new BoardContentDTO();
				n.setBc_no(rs.getInt("bc_no"));
				n.setBc_code(rs.getInt("bc_code"));
				n.setBc_subject(rs.getString("bc_subject"));
				n.setBc_date(format.format(rs.getTimestamp("bc_date")));
				boardContentList.add(n);
			}
		}
		
		DBClose();
		return boardContentList;
	}
}
