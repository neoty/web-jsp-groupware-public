package groupware.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationConfigDao extends CommonDAO {
	private ResultSet rs;
	private int af = 0;
	private static RelationConfigDao _instance;
	
	public static RelationConfigDao getInstance() {
		if (_instance == null) {
			_instance = new RelationConfigDao();
			System.out.println("Config 인스턴스 최초 생성");
		}
			return _instance;
	}
	
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
				+ "cf_create_date timestamp not null, "
				+ "cf_grant_ip int null, "
				+ "cf_band_ip text null, "
				+ "cf_use_membership int not null, "
				+ "cf_work_start varchar(255) not null, "
				+ "cf_work_end varchar(255) not null, "
				+ "PRIMARY KEY(cf_no))";
		System.out.println("sql");
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
		psmt.setString(2, ADMIN_ID);
		psmt.setString(3, ADMIN_PASSWORD);
		psmt.setString(4, ADMIN_EMAIL);
		psmt.setString(5, ADMIN_PHONE);
		psmt.setInt(6, 0);
		psmt.setString(7, "none");
		psmt.setInt(8, 0);
		psmt.setString(9, "none");
		psmt.setInt(10, 1);
		psmt.setString(11, WORK_START);
		psmt.setString(12, WORK_END);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	
	public int LogTable() throws SQLException {
		DBConnection();
		
		/*
		 * create log_table
		 */
		String sql = "CREATE TABLE gw_log("
				+ "log_no INT NOT NULL AUTO_INCREMENT, "
				+ "log_ip varchar(255) not null, "
				+ "log_date timestamp not null, "
				+ "member_id varchar(255) null, "
				+ "log_subject text null, "
				+ "log_content text null, "
				+ "PRIMARY KEY(log_no))";
		System.out.println("sql");
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	
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
		System.out.println("sql");
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
		String[][] position = {{"1","연구원","0"}, {"2","사원","1"}, {"3","주임","1"}, {"4","대리","1"}, 
				{"5","과장","1"}, {"6","차장","1"}, {"7","팀장","1"}, {"8","실장","1"}, {"9","공장장","0"},
				{"10","사무국장","0"}, {"11","부장대리","0"}, {"12","본부장","0"}, {"13","부장","1"}, 
				{"14","이사","1"}, {"15","상무이사","0"}, {"16","전무이사","0"}, {"17","대표이사","1"}, 
				{"18","부사장","1"}, {"19","지사장","0"}, {"20","사장","1"}, {"21","부회장","0"}, {"22","회장","1"}};
		for (int i = 0; i < position.length; i++) {
			int level = Integer.parseInt(position[i][0]);
			System.out.println(level);
			String name = position[i][1];
			System.out.println(name);
			int use = Integer.parseInt(position[i][2]);
			System.out.println(use);
			psmt.setInt(1, level);
			psmt.setString(2, name);
			psmt.setInt(3, use);
			af = setQuery(psmt);
		}
		
		DBClose();
		return af;
	}
	
	
	public int RuleTable() throws SQLException {
		DBConnection();
		
		/*
		 * create rule_table
		 */
		String sql = "CREATE TABLE gw_rule("
				+ "rule_chmod INT NOT NULL, "
				+ "rule_describe text not null, "
				+ "PRIMARY KEY(rule_chmod))";
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		System.out.println("sql");
		DBClose();
		return af;
	}
	
	
	public int CarlistTable() throws SQLException {
		DBConnection();
		
		/*
		 * create carlist_table
		 */
		String sql = "CREATE TABLE gw_car_list("
				+ "cl_number INT NOT NULL, "
				+ "cl_model varchar(255), "
				+ "cl_mileage int, "
				+ "cl_use int, "
				+ "PRIMARY KEY(cl_number))";
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		System.out.println("sql");
		DBClose();
		return af;
	}
	
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
				+ "mb_sex int not null, "
				+ "mb_birth varchar(255) not null, "
				+ "mb_join_date timestamp not null, "
				+ "mb_last_ip varchar(255), "
				+ "mb_last_date varchar(255), "
				+ "mb_login_count int, "
				+ "mb_certify int, "
				+ "mb_work_type int, "
				+ "mb_rule_ch_no int, "
				+ "mb_dep_no int, "
				+ "mb_position_posi_level int, "
				+ "mb_use int, "
				+ "PRIMARY KEY (`mb_no`), "
				+ "CONSTRAINT `fk_gw_member_gw_level` "
				+ "FOREIGN KEY (`mb_rule_ch_no`) "
				+ "REFERENCES `gw_rule` (`rule_chmod`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION, "
				+ "CONSTRAINT `fk_gw_member_gw_position2` "
				+ "FOREIGN KEY (`mb_position_posi_level`) "
				+ "REFERENCES `gw_position` (`posi_level`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
		
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		System.out.println("sql");
		DBClose();
		return af;
	}
	
	public int DepartmentTable() throws SQLException {
		DBConnection();
		
		/*
		 * create department_table
		 */
		String sql = "CREATE TABLE gw_department("
				+ "dep_no INT NOT NULL AUTO_INCREMENT, "
				+ "dep_name varchar(255) not null, "
				+ "dep_describe text, "
				+ "dep_use int not null, "
				+ "dep_admin int null, "
				+ "PRIMARY KEY(dep_no))";
		
		System.out.println("sql");
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		
		sql = "INSERT INTO gw_department(dep_name, dep_describe, "
				+ "dep_use, dep_admin) values('asdf','ffsd',1,3)";
		
		DBClose();
		return af;
	}
	
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
				+ "PRIMARY KEY(cs_no), "
				+ "CONSTRAINT `fk_gw_car_status_gw_car_list1` "
				+ "FOREIGN KEY (`cs_cl_number`) "
				+ "REFERENCES `gw_car_list` (`cl_number`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION, "
				+ "CONSTRAINT `fk_gw_car_status_gw_member1` "
				+ "FOREIGN KEY (`cs_member_no`) "
				+ "REFERENCES `gw_member` (`mb_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
		System.out.println("sql");
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
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
				+ "PRIMARY KEY(att_no), "
				+ "CONSTRAINT `fk_gw_attendance_gw_member1` "
				+ "FOREIGN KEY (`att_member_no`) "
				+ "REFERENCES `gw_member` (`mb_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
		System.out.println("sql");
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	public int Message() throws SQLException {
		DBConnection();
		
		/*
		 * create attendance_table
		 */
		String sql = "CREATE TABLE gw_message("
				+ "mes_no INT NOT NULL AUTO_INCREMENT, "
				+ "mes_subject text null, "
				+ "mes_content text null, "
				+ "mes_posi_date timestamp, "
				+ "mes_confirm int not null, "
				+ "mes_confirm_date varchar(255), "
				+ "mes_get_mb_no int not null, "
				+ "mes_post_mb_no int not null, "
				+ "PRIMARY KEY(mes_no), "
				+ "CONSTRAINT `fk_gw_message_gw_member2` "
				+ "FOREIGN KEY (`mes_get_mb_no`) "
				+ "REFERENCES `gw_member` (`mb_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION, "
				+ "CONSTRAINT `fk_gw_message_gw_member1` "
				+ "FOREIGN KEY (`mes_post_mb_no`) "
				+ "REFERENCES `gw_member` (`mb_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
		System.out.println("sql");
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		
		return af;
	}
	
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
				+ "sc_date timestamp, "
				+ "PRIMARY KEY(sc_no), "
				+ "CONSTRAINT `fk_gw_schedule_gw_member1` "
				+ "FOREIGN KEY (`sc_member_no`) "
				+ "REFERENCES `gw_member` (`mb_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION, "
				+ "CONSTRAINT `fk_gw_schedule_gw_department1` "
				+ "FOREIGN KEY (`sc_dep_no`) "
				+ "REFERENCES `gw_department` (`dep_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		System.out.println("sql");
		DBClose();
		return af;
	}
	
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
				+ "bc_date timestamp null, "
				+ "PRIMARY KEY(bc_no), "
				+ "CONSTRAINT `fk_gw_board_content_gw_member1` "
				+ "FOREIGN KEY (`bc_mb_no`) "
				+ "REFERENCES `gw_member` (`mb_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
		System.out.println("sql");
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	public int BoardPublic() throws SQLException {
		DBConnection();
		
		/*
		 * create BoardPublic
		 */
		String sql = "CREATE TABLE gw_board_public("
				+ "bp_no INT NOT NULL AUTO_INCREMENT, "
				+ "bp_name varchar(255) not null, "
				+ "bp_describe text null, "
				+ "bp_use int not null, "
				+ "PRIMARY KEY(bp_no))";
		System.out.println("sql");
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
		
	}
	
	public int File() throws SQLException {
		DBConnection();
	
		/*
		 * create FileTable
		 */
		String sql = "CREATE TABLE gw_file("
				+ "file_no INT NOT NULL AUTO_INCREMENT, "
				+ "file_number int null, "
				+ "gw_bc_no int not null, "
				+ "file_part varchar(255) null, "
				+ "file_realname text not null, "
				+ "file_encname text not null, "
				+ "file_path text not null, "
				+ "file_date timestamp not null, "
				+ "PRIMARY KEY(file_no), "
				+ "CONSTRAINT `fk_table1_gw_board_content1` "
				+ "FOREIGN KEY (`gw_bc_no`) "
				+ "REFERENCES `gw_board_content` (`bc_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
		System.out.println("sql");
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	public int BoardCircles() throws SQLException {
		DBConnection();
	
		/*
		 * create Board_Circles table
		 */
		String sql = "CREATE TABLE gw_board_circles("
				+ "circles_no INT NOT NULL AUTO_INCREMENT, "
				+ "circles_name varchar(255) null, "
				+ "circles_describe text null, "
				+ "circles_use int not null, "
				+ "circles_admin int null, "
				+ "PRIMARY KEY(circles_no), "
				+ "CONSTRAINT `fk_gw_board_circles_gw_member1` "
				+ "FOREIGN KEY (`circles_admin`) "
				+ "REFERENCES `gw_member` (`mb_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
		System.out.println("sql");
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	public int CirclesRel() throws SQLException {
		DBConnection();
		
		/*
		 * create circles_rel table
		 */
		String sql = "CREATE TABLE gw_circles_rel("
				+ "cr_no INT NOT NULL AUTO_INCREMENT, "
				+ "cr_circles_no int not null, "
				+ "cr_member_no int not null, "
				+ "PRIMARY KEY(cr_no), "
				+ "CONSTRAINT `fk_gw_circles_rel_gw_board_circles1` "
				+ "FOREIGN KEY (`cr_circles_no`) "
				+ "REFERENCES `gw_board_circles` (`circles_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION, "
				+ "CONSTRAINT `fk_gw_circles_rel_gw_member1` "
				+ "FOREIGN KEY (`cr_member_no`) "
				+ "REFERENCES `gw_member` (`mb_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		System.out.println("sql");
		DBClose();
		return af;
	}
	
	public int BoardProject() throws SQLException {
		DBConnection();
		
		/*
		 * create board_project table
		 */
		String sql = "CREATE TABLE gw_board_project("
				+ "project_no INT NOT NULL AUTO_INCREMENT, "
				+ "project_name varchar(255) not null, "
				+ "project_describe varchar(255) null, "
				+ "project_use int not null, "
				+ "project_admin int null, "
				+ "PRIMARY KEY(project_no), "
				+ "CONSTRAINT `fk_gw_board_project_gw_member1` "
				+ "FOREIGN KEY (`project_admin`) "
				+ "REFERENCES `gw_member` (`mb_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
		System.out.println("sql");
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	public int PorjectRel() throws SQLException {
		DBConnection();
		
		/*
		 * create projectboard rel table
		 */
		String sql = "CREATE TABLE gw_project_rel("
				+ "pr_no INT NOT NULL AUTO_INCREMENT, "
				+ "pr_project_no int not null, "
				+ "pr_member_no int not null, "
				+ "PRIMARY KEY(pr_no), "
				+ "CONSTRAINT `fk_gw_project_rel_gw_baord_project1` "
				+ "FOREIGN KEY (`pr_project_no`) "
				+ "REFERENCES `gw_board_project` (`project_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION, "
				+ "CONSTRAINT `fk_gw_project_rel_gw_member1` "
				+ "FOREIGN KEY (`pr_member_no`) "
				+ "REFERENCES `gw_member` (`mb_no`) "
				+ "ON DELETE NO ACTION "
				+ "ON UPDATE NO ACTION)";
				
		
		System.out.println(sql);
		psmt = conn.prepareStatement(sql);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
}