package groupware.community.controllers;

import groupware.dao.CommunityDAO;
import groupware.dto.BoardDTO;

import java.sql.SQLException;
import java.util.List;

public class MenuList {
	public List<BoardDTO> PublicANDDepartment(int mb_no) throws SQLException {
		List<BoardDTO> list;
		
		list = CommunityDAO.getInstance().PublicANDDepartment(mb_no);
		return list;
	}
}
