package groupware.dao;

import groupware.dto.BoardDTO;
import groupware.dto.ConfigDTO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.crypto.spec.PSource;

public class ConfigDao extends CommonDAO {
	private ResultSet rs;
	private int af 				=				 0;
	private static ConfigDao _instance;
	
	public static ConfigDao getInstance() {
		if (_instance == null) {
			_instance = new ConfigDao();
			System.out.println("Config 인스턴스 최초 생성");
		}
			return _instance;
	}
	
	/**
	 * 설치되었는지 체크
	 * @return
	 * @throws SQLException
	 */
	public int SetupCheck() throws SQLException {
		DBConnection();
		String sql = "select count(*) count from gw_config";
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		rs.next();
		int countTmp = rs.getInt("count");
		DBClose();
		System.out.println(countTmp);
		return countTmp;
	}
	
	/**
	 * 설정 테이블 생성
	 * @param ADMIN_ID
	 * @param ADMIN_PASSWORD
	 * @param ADMIN_EMAIL
	 * @param ADMIN_PHONE
	 * @param WORK_START
	 * @param WORK_END
	 * @return
	 * @throws SQLException
	 */
	public int ConfigTable(String ADMIN_ID, String ADMIN_PASSWORD, 
			String ADMIN_EMAIL, String ADMIN_PHONE, String WORK_START, String WORK_END) throws SQLException {
		DBConnection();
		
		/*
		 * create config_table
		 */
		String sql = "CREATE TABLE gw_config("
				+ "cf_no INT NOT NULL, "
				+ "cf_id varchar(255) not null, "
				+ "cf_password char(128) not null, "
				+ "cf_email varchar(255) not null, "
				+ "cf_phone varchar(255) not null, "
				+ "cf_stop int not null, "
				+ "cf_stop_title varchar(255) not null, "
				+ "cf_create_date timestamp, "
				+ "cf_grant_ip varchar(255) null, "
				+ "cf_band_ip text null, "
				+ "cf_use_membership int not null, "
				+ "cf_work_start varchar(255) not null, "
				+ "cf_work_end varchar(255) not null, "
				+ "PRIMARY KEY(cf_no))";

		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		
		/*
		 * 기본 입력값 입력
		 */
		String insert_sql = "INSERT INTO gw_config("
				+ "cf_no, "
				+ "cf_id, "
				+ "cf_password, "
				+ "cf_email, "
				+ "cf_phone, "
				+ "cf_stop, "
				+ "cf_stop_title, "
				+ "cf_grant_ip, "
				+ "cf_band_ip, "
				+ "cf_use_membership, "
				+ "cf_work_start, "
				+ "cf_work_end) values("
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?)";
		psmt = conn.prepareStatement(insert_sql);
		psmt.setInt(1, 1);
		psmt.setString(2, ADMIN_ID);								//관리자 아이디
		psmt.setString(3, ADMIN_PASSWORD);							//관리자 패스워드
		psmt.setString(4, ADMIN_EMAIL);								//관리자 이메일
		psmt.setString(5, ADMIN_PHONE);								//관리자 핸드폰 번호
		psmt.setInt(6, 0);											//사이트 사용여부
		psmt.setString(7, "현재 그룹웨어를 사용할수 없습니다. ");			//사이트 정지시 경고창
		psmt.setString(8, "");										//출퇴근 허용 아이피
		psmt.setString(9, "");										//출퇴근 허용 아이피 대역
		psmt.setInt(10, 1);											//회원 가입 사용여부
		psmt.setString(11, WORK_START);								//일 시작 시간
		psmt.setString(12, WORK_END);								//일 끝나는 시간
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 로그 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int LogTable() throws SQLException {
		DBConnection();
		
		/*
		 * create log_table
		 */
		String sql = "CREATE TABLE gw_log("
				+ "log_no INT NOT NULL AUTO_INCREMENT, "
				+ "log_ip varchar(255) not null, "
				+ "log_member_id varchar(255) null, "
				+ "log_subject text null, "
				+ "log_content text null, "
				+ "log_create_date timestamp, "
				+ "PRIMARY KEY(log_no))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 직책 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int PositionTable() throws SQLException {
		DBConnection();
		
		/*
		 * create positition_table
		 */
		String sql = "CREATE TABLE gw_position("
				+ "posi_level INT NOT NULL, "
				+ "posi_name varchar(255) not null, "
				+ "posi_use int not null, "
				+ "PRIMARY KEY(posi_level))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		
		/*
		 * 기본 입력값 입력
		 */
		sql = "INSERT INTO gw_position("
				+ "posi_level, "
				+ "posi_name, "
				+ "posi_use) values("
				+ "?, "
				+ "?, "
				+ "?)";
		psmt = conn.prepareStatement(sql);
		String[][] position = {{"1","연구원","0"}, 
				{"2","사원","1"}, 
				{"3","주임","1"}, 
				{"4","대리","1"}, 
				{"5","과장","1"}, 
				{"6","차장","1"}, 
				{"7","팀장","1"}, 
				{"8","실장","1"}, 
				{"9","공장장","0"},
				{"10","사무국장","0"}, 
				{"11","부장대리","0"}, 
				{"12","본부장","0"}, 
				{"13","부장","1"}, 
				{"14","이사","1"}, 
				{"15","상무이사","0"}, 
				{"16","전무이사","0"}, 
				{"17","대표이사","1"}, 
				{"18","부사장","1"}, 
				{"19","지사장","0"}, 
				{"20","사장","1"}, 
				{"21","부회장","0"}, 
				{"22","회장","1"}};
		for (int i = 0; i < position.length; i++) {
			int level = Integer.parseInt(position[i][0]);
			String name = position[i][1];
			int use = Integer.parseInt(position[i][2]);
			psmt.setInt(1, level);
			psmt.setString(2, name);
			psmt.setInt(3, use);
			af = setQuery(psmt);
		}
		
		DBClose();
		return af;
	}
	
	/**
	 * 권한에 대한 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int RuleTable() throws SQLException {
		DBConnection();
		
		/*
		 * create rule_table
		 */
		String sql = "CREATE TABLE gw_rule("
				+ "rule_chmod INT NOT NULL, "
				+ "rule_describe text not null, "
				+ "PRIMARY KEY(rule_chmod))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 자동차 리스트에 대한 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int CarlistTable() throws SQLException {
		DBConnection();
		
		/*
		 * create carlist_table
		 */
		String sql = "CREATE TABLE gw_car_list("
				+ "cl_no INT NOT NULL AUTO_INCREMENT, "
				+ "cl_number varchar(255) UNIQUE NOT NULL, "
				+ "cl_model varchar(255), "
				+ "cl_mileage int, "
				+ "cl_use int, "
				+ "cl_create_date timestamp, "
				+ "PRIMARY KEY(cl_no))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 회원 정보 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int MemberTable () throws SQLException {
		DBConnection();
		
		/*
		 * create member_table
		 */
		String sql = "CREATE TABLE gw_member("
				+ "mb_no INT NOT NULL AUTO_INCREMENT, "
				+ "mb_id varchar(255) not null, "
				+ "mb_password char(128) not null, "
				+ "mb_name varchar(255) not null, "
				+ "mb_email varchar(255) not null, "
				+ "mb_tel varchar(255) not null, "
				+ "mb_password_q varchar(255) not null, "
				+ "mb_password_a varchar(255) not null, "
				+ "mb_sex varchar(255) not null, "
				+ "mb_birth varchar(255) not null, "
				+ "mb_last_ip varchar(255), "
				+ "mb_last_date varchar(255), "
				+ "mb_login_count int, "
				+ "mb_certify int, "
				+ "mb_work_type int, "
				+ "mb_rule_ch_no int, "
				+ "mb_dep_no int, "
				+ "mb_position_posi_level int, "
				+ "mb_use int, "
				+ "mb_join_date timestamp, "
				+ "mb_img text, "
				+ "PRIMARY KEY (`mb_no`))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 부서 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int DepartmentTable() throws SQLException {
		DBConnection();
		
		/*
		 * create department_table
		 */
		String sql = "CREATE TABLE gw_department("
				+ "dep_no INT NOT NULL AUTO_INCREMENT, "
				+ "dep_name varchar(255) not null, "
				+ "dep_describe text, "
				+ "dep_admin int null, "
				+ "dep_use int not null, "
				+ "dep_create_date timestamp, "
				+ "PRIMARY KEY(dep_no))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		
		sql = "INSERT INTO gw_department("
				+ "dep_name, "
				+ "dep_describe, "
				+ "dep_admin, "
				+ "dep_use"
				+ ") VALUES ("
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?)";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, "관리자");
		psmt.setString(2, "관리자 전용 부서");
		psmt.setInt(3, 1);
		psmt.setInt(4, -1);
		af = setQuery(psmt);
		
		
		DBClose();
		return af;
	}
	
	/**
	 * 회원과 자동차간의 사용 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int CarStatus() throws SQLException {
		DBConnection();
		
		/*
		 * create department_table
		 */
		String sql = "CREATE TABLE gw_car_status("
				+ "cs_no INT NOT NULL AUTO_INCREMENT, "
				+ "cs_cl_number int not null, "
				+ "cs_member_no int not null, "
				+ "cs_reason text null, "
				+ "cs_use_date varchar(255) null, "
				+ "cs_certify int not null, "
				+ "cs_create_date timestamp, "
				+ "PRIMARY KEY(cs_no))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 출퇴근 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int Attendance() throws SQLException {
		DBConnection();
		
		/*
		 * create attendance_table
		 */
		String sql = "CREATE TABLE gw_attendance("
				+ "att_no INT NOT NULL AUTO_INCREMENT, "
				+ "att_member_no int not null, "
				+ "att_s_date varchar(255), "
				+ "att_e_date varchar(255), "
				+ "PRIMARY KEY(att_no))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 메시지 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int Message() throws SQLException {
		DBConnection();
		
		/*
		 * create attendance_table
		 */
		String sql = "CREATE TABLE gw_message("
				+ "mes_no INT NOT NULL AUTO_INCREMENT, "
				+ "mes_subject text null, "
				+ "mes_content text null, "
				+ "mes_post_mb_no int not null, "
				+ "mes_get_mb_no int not null, "
				+ "mes_post_date timestamp, "
				+ "mes_confirm_date varchar(255), "
				+ "mes_post_mb_storage int not null, "
				+ "mes_get_mb_storage int not null, "
				+ "mes_post_mb_remove int not null, "
				+ "mes_get_mb_remove int not null, "
				+ "mes_file_exists int not null, "
				+ "PRIMARY KEY(mes_no))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		
		return af;
	}
	
	/**
	 * 스케줄 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int Schedule() throws SQLException {
		DBConnection();
		
		/*
		 * create attendance_table
		 */
		String sql = "CREATE TABLE gw_schedule("
				+ "sc_no INT NOT NULL AUTO_INCREMENT, "
				+ "sc_member_no int not null, "
				+ "sc_dep_no int not null, "
				+ "sc_progress int, "
				+ "sc_subject text, "
				+ "sc_content text, "
				+ "sc_s_date varchar(255), "
				+ "sc_e_date varchar(255), "
				+ "sc_create_date timestamp, "
				+ "sc_team int(11) default 0, "
				+ "PRIMARY KEY(sc_no))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 게시판 내용 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int BoardContent() throws SQLException {
		DBConnection();
		
		/*
		 * create BoardContent
		 */
		String sql = "CREATE TABLE gw_board_content("
				+ "bc_no INT NOT NULL AUTO_INCREMENT, "
				+ "bc_mb_no int not null, "
				+ "bc_type varchar(255) not null, "
				+ "bc_code int not null, "
				+ "bc_subject text not null, "
				+ "bc_content text not null, "
				+ "bc_notice int not null, "
				+ "bc_secret int not null, "
				+ "bc_file_exists int not null, "
				+ "bc_read_count int, "
				+ "bc_reply_count int, "
				+ "bc_date timestamp, "
				+ "PRIMARY KEY(bc_no))";

		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}

	
	/**
	 * 보드 게시판 만들기
	 * @return
	 * @throws SQLException
	 */
	public int Board() throws SQLException {
		DBConnection();
		
		/*
		 * create BoardPublic
		 */
		String sql = "CREATE TABLE gw_board("
				+ "bo_no INT NOT NULL AUTO_INCREMENT, "
				+ "bo_type varchar(255) not null, "
				+ "bo_name varchar(255) not null, "
				+ "bo_describe text null, "
				+ "bo_use int not null, "
				+ "bo_admin int null, "
				+ "bo_create_date timestamp, "
				+ "PRIMARY KEY(bo_no))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
		
	}
	
	/**
	 * 기본 보드 테이블에 들어갈 기본 게시판 입력
	 * @return
	 * @throws SQLException
	 */
	public int insertBasicBoard(String type, String name, String describe) throws SQLException {
		DBConnection();
		String sql ="INSERT INTO gw_board("
				+ "bo_type, "
				+ "bo_name, "
				+ "bo_describe, "
				+ "bo_use, "
				+ "bo_admin) VALUES("
				+ "?, ?, ?, ?, ?)";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, type);
		psmt.setString(2, name);
		psmt.setString(3, describe);
		psmt.setInt(4, 1);
		psmt.setInt(5, 1);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	
	/**
	 * 파일 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int File() throws SQLException {
		DBConnection();
	
		/*
		 * create FileTable
		 */
		String sql = "CREATE TABLE gw_file("
				+ "file_no INT NOT NULL AUTO_INCREMENT, "
				+ "file_part varchar(255), "
				+ "file_number int, "
				+ "file_relname1 text, "
				+ "file_encname1 text, "
				+ "file_relname2 text, "
				+ "file_encname2 text, "
				+ "file_create_date timestamp, "
				+ "PRIMARY KEY(file_no))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	
	/**
	 * 동아리와 회원관 권한에 대한 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int CirclesRel() throws SQLException {
		DBConnection();
		
		/*
		 * create circles_rel table
		 */
		String sql = "CREATE TABLE gw_circles_rel("
				+ "cr_circles_no int not null, "
				+ "cr_member_no int not null, "
				+ "cr_member_type varchar(255), "
				+ "cr_create_date timestamp, "
				+ "PRIMARY KEY(cr_circles_no, cr_member_no, cr_member_type))";
		
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		System.out.println(sql);
		DBClose();
		return af;
	}
	
	/**
	 * 프로젝트와 회원관 권한에 대한 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int PorjectRel() throws SQLException {
		DBConnection();
		
		/*
		 * create projectboard rel table
		 */
		String sql = "CREATE TABLE gw_project_rel("
				+ "pr_project_no int not null, "
				+ "pr_member_no int not null, "
				+ "pr_member_type varchar(255), "
				+ "pr_create_date timestamp, "
				+ "PRIMARY KEY(pr_project_no, pr_member_no, pr_member_type))";
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 전자결재 테이블 을 생성한다.
	 * @return
	 * @throws SQLException
	 */
	public int WorkFlow() throws SQLException {
		DBConnection();
		
		/*
		 * create workflow table
		 */
		String sql = "CREATE TABLE gw_workflow("
				+ "wf_no int not null AUTO_INCREMENT, "
				+ "wf_post_mb int not null, "
				+ "wf_status int not null, "
				+ "wf_doc_no varchar(255) not null, "
				+ "wf_mb_first int not null default '0', "
				+ "wf_first_status int not null default '0', "
				+ "wf_first_note varchar(255), "
				+ "wf_first_confirm_date varchar(255), "
				+ "wf_mb_second int not null default '0', "
				+ "wf_second_status int not null default '0', "
				+ "wf_second_note varchar(255), "
				+ "wf_second_confirm_date varchar(255), "
				+ "wf_mb_third int not null default '0', "
				+ "wf_third_status int not null default '0', "
				+ "wf_third_note varchar(255), "
				+ "wf_third_confirm_date varchar(255), "
				+ "wf_subject text, "
				+ "wf_create_date timestamp, "
				+ "PRIMARY KEY(wf_no))";
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	/**
	 * 모든 테이블 지우기
	 * @return
	 * @throws SQLException
	 */
	public int DeleteTable() throws SQLException {
		DBConnection();
		
		/*
		 * create projectboard rel table
		 */
		String[] sql = {
			"gw_attendance",
			"gw_board_content",
			"gw_board_public",
			"gw_car_list",
			"gw_car_status",
			"gw_circles_rel",
			"gw_config",
			"gw_department",
			"gw_file",
			"gw_log",
			"gw_member",
			"gw_message",
			"gw_position",
			"gw_project_rel",
			"gw_rule",
			"gw_schedule",
			"gw_board",
		};
		
		for (int i = 0; i < sql.length; i++) {
			String DeleteSQL = "DROP TABLE "+sql[i];
			psmt = conn.prepareStatement(DeleteSQL);
			af = setQuery(psmt);
		}
		
		DBClose();
		return af;
	}
	
	/**
	 * 관리자에 대한 정보 입력
	 * @param ID
	 * @param PASSWORD
	 * @param EMAIL
	 * @param PHONE
	 * @return
	 * @throws SQLException
	 */
	public int InsertAdminInfo(String ID, String PASSWORD, String EMAIL, String PHONE) throws SQLException {
	DBConnection();
		
		/*
		 * create projectboard rel table
		 */
	
		String sql = "INSERT INTO gw_member("
				+ "mb_id, "
				+ "mb_name, "
				+ "mb_password, "
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
				+ "mb_use) VALUES("
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, ID);
		psmt.setString(2, "관리자");
		psmt.setString(3, PASSWORD);
		psmt.setString(4, EMAIL);
		psmt.setString(5, PHONE);
		psmt.setString(6, "NOTHING");
		psmt.setString(7, "NOTHING");
		psmt.setString(8, "관리자");
		psmt.setString(9, "관리자");
		psmt.setString(10, "0.0.0.0");
		psmt.setString(11, "마지막 접속일자");
		psmt.setInt(12, 1);
		psmt.setInt(13, 1);
		psmt.setInt(14, 0);
		psmt.setInt(15, 0);
		psmt.setInt(16, 1);
		psmt.setInt(17, 0);
		psmt.setInt(18, 1);

		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 리플 테이블 생성
	 * @return
	 * @throws SQLException
	 */
	public int Comment() throws SQLException {
		DBConnection();
		
		/*
		 * create replytable table
		 */
		String sql = "CREATE TABLE gw_reply("
				+ "re_no INT NOT NULL AUTO_INCREMENT, "
				+ "re_bc_no int, "
				+ "re_content text, "
				+ "re_mb_no int, "
				+ "re_create_date timestamp, "
				+ "PRIMARY KEY(re_no))";		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 서버 상태 가져오기
	 * @return
	 * @throws SQLException
	 */
	public ConfigDTO serverStatus() throws SQLException {
		DBConnection();
		String sql = "SELECT * FROM gw_config";
		
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		ConfigDTO DTO = new ConfigDTO();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (rs.next()) {
			DTO.setCf_id(rs.getString("cf_id"));
			DTO.setCf_email(rs.getString("cf_email"));
			DTO.setCf_phone(rs.getString("cf_phone"));
			DTO.setCf_stop(rs.getInt("cf_stop"));
			DTO.setCf_stop_title(rs.getString("cf_stop_title"));
			DTO.setCf_grant_ip(rs.getString("cf_grant_ip"));
			DTO.setCf_band_ip(rs.getString("cf_band_ip"));
			DTO.setCf_use_membership(rs.getInt("cf_use_membership"));
			DTO.setCf_work_start(rs.getString("cf_work_start"));
			DTO.setCf_work_end(rs.getString("cf_work_end"));
			DTO.setCf_create_date(format.format(rs.getTimestamp("cf_create_date")));
		}
		DBClose();
		return DTO;
	}
	
	/**
	 * 서버 상태의 카운터들을 구해온다
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer, Integer> serverCount() throws SQLException {
		DBConnection();
		
		String sql = "SELECT COUNT(*) count FROM gw_member WHERE mb_use = 1 AND mb_work_type > 0 "
				+ "UNION ALL "
				+ "SELECT COUNT(*) FROM gw_department "
				+ "UNION ALL "
				+ "SELECT COUNT(*) FROM gw_board WHERE bo_type = 'CIRCLES' "
				+ "UNION ALL "
				+ "SELECT COUNT(*) FROM gw_board WHERE bo_type = 'PROJECT' "
				+ "UNION ALL "
				+ "SELECT COUNT(*) FROM gw_car_list "
				+ "UNION ALL "
				+ "SELECT COUNT(*) FROM gw_file";
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		/*
		 *  1 = 회원수
		 *  2 = 부서수 
		 *  3 = CIRCLES 보드 수
		 *  4 = PROJECT 보드 수
		 *  5 = 자동차수 
		 *  6 = 파일수
		 */
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 1 ; rs.next() ; i++) {
			map.put(i, rs.getInt("count"));
		}
		
		DBClose();
		return map;
	}
	
	/**
	 * 그룹웨어 사이트 정지여부
	 * @return
	 * @throws SQLException
	 */
	public ConfigDTO groupUseStatus() throws SQLException {
		DBConnection();
		String sql = "SELECT cf_stop, cf_stop_title FROM gw_config";
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		ConfigDTO DTO = new ConfigDTO();
		if (rs.next()) {
			DTO.setCf_stop(rs.getInt("cf_stop"));
			DTO.setCf_stop_title(rs.getString("cf_stop_title"));
		}
		
		DBClose();
		return DTO;
	}
	
	/**
	 * 그룹웨어 설정 업데이트
	 * @param DTO
	 * @return
	 * @throws SQLException
	 */
	public int updateConfig(ConfigDTO DTO) throws SQLException {
		DBConnection();
		String sql = "UPDATE gw_config SET cf_email = ?, cf_phone = ?, "
				+ "cf_stop = ?, cf_stop_title = ?, cf_grant_ip = ?, "
				+ "cf_band_ip = ?, cf_use_membership = ?, cf_work_start = ?, "
				+ "cf_work_end = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DTO.getCf_email());
		psmt.setString(2, DTO.getCf_phone());
		psmt.setInt(3, DTO.getCf_stop());
		psmt.setString(4, DTO.getCf_stop_title());
		psmt.setString(5, DTO.getCf_grant_ip());
		psmt.setString(6, DTO.getCf_band_ip());
		psmt.setInt(7, DTO.getCf_use_membership());
		psmt.setString(8, DTO.getCf_work_start());
		psmt.setString(9, DTO.getCf_work_end());
		
		af = setQuery(psmt);
		DBClose();
		return af;
	}
}




