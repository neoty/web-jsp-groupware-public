package groupware.main.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.commons.Encryption;
import groupware.commons.Controller;
import groupware.dao.MainDAO;
import groupware.dto.BoardContentDTO;
import groupware.dto.BoardDTO;

public class MainController implements Controller{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, ServletException, IOException, Exception {
		// TODO Auto-generated method stub
		List<BoardDTO> boardNamelist = new ArrayList<BoardDTO>(MainDAO.getInstance().getPublicList());
		int listCnt = boardNamelist.size();
		
		List<BoardContentDTO> boardContentList = new ArrayList<BoardContentDTO>(MainDAO.getInstance().getPublicContent(boardNamelist));
		
		
		request.setAttribute("DESC_PATH", "메인");
		request.setAttribute("boardContentList", boardContentList); // 실제로 들어갈 최근 게시물 각 게시판 마다 6개 기준으로 한다.
		request.setAttribute("boardNamelist", boardNamelist); //PUBLIC 인 게시판의 넘버와 리스트를 구한다.
		request.setAttribute("listcnt", listCnt); 
		return "index.tiles";
	}
}
