package groupware.dao;

import groupware.dto.CarListDTO;
import groupware.dto.CarStatusDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO extends CommonDAO {
	private ResultSet rs;
	private int af = 0;
	private static CarDAO _instance;

	public static CarDAO getInstance() {
		if (_instance == null) {
			_instance = new CarDAO();
		}
		return _instance;
	}
	
	
	/**
	 * 중복 되는 자동차 리스트 체크
	 * @param DTO
	 * @return
	 * @throws SQLException
	 */
	public int checkCarDup(CarListDTO DTO) throws SQLException {
		DBConnection();
		String sql = "SELECT count(*) count "
				+ "FROM gw_car_list WHERE "
				+ "cl_number = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DTO.getCl_number());
		rs = getQuery(psmt);
		
		if (rs.next()) {
			af = rs.getInt("count");
		}
		
		DBClose();
		return af;
	}
	
	
	/**
	 * 자동차 추가하기
	 * @param DTO
	 * @return
	 * @throws SQLException
	 */
	public int carAdd(CarListDTO DTO) throws SQLException {
		DBConnection();
		String sql = "INSERT INTO gw_car_list("
				+ "cl_number, "
				+ "cl_model, "
				+ "cl_mileage, "
				+ "cl_use) VALUES("
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?)";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DTO.getCl_number());
		psmt.setString(2, DTO.getCl_model());
		psmt.setInt(3, DTO.getCl_mileage());
		psmt.setInt(4, DTO.getCl_use());
		
		af = setQuery(psmt);

		DBClose();
		return af;
	}
	
	
	/**
	 * 자동차 리스트에 대한 정보를 얻어온다.
	 * @return
	 * @throws SQLException
	 */
	public List<CarListDTO> getCarList() throws SQLException {
		DBConnection();
		String sql = "SELECT * FROM gw_car_list";
		psmt = conn.prepareStatement(sql);
		rs = getQuery(psmt);
		
		List<CarListDTO> list = new ArrayList<CarListDTO>();
		while (rs.next()) {
			CarListDTO n = new CarListDTO();
			n.setCl_no(rs.getInt("cl_no"));
			n.setCl_number(rs.getString("cl_number"));
			n.setCl_model(rs.getString("cl_model"));
			n.setCl_mileage(rs.getInt("cl_mileage"));
			n.setCl_use(rs.getInt("cl_use"));
			n.setCl_create_date(rs.getString("cl_create_date"));
			list.add(n);
		}
		DBClose();
		return list;
	}
	
	/**
	 * 자동차에 대한 정보를 얻어온다.
	 * @param cl_no
	 * @return
	 * @throws SQLException
	 */
	public CarListDTO getCarContent(int cl_no) throws SQLException {
		DBConnection();
		String sql = "SELECT * FROM gw_car_list "
				+ "WHERE cl_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, cl_no);
		rs = getQuery(psmt);
		CarListDTO content = new CarListDTO();
		while (rs.next()) {
			content.setCl_no(cl_no);
			content.setCl_number(rs.getString("cl_number"));
			content.setCl_model(rs.getString("cl_model"));
			content.setCl_mileage(rs.getInt("cl_mileage"));
			content.setCl_use(rs.getInt("cl_use"));
			content.setCl_create_date(rs.getString("cl_create_date"));
		}
		
		DBClose();
		return content;
	}
	
	
	/**
	 * 등록된 차량의 정보를 삭제한다.
	 * @param cl_no
	 * @return
	 * @throws SQLException
	 */
	public int deleteCar(int cl_no) throws SQLException {
		DBConnection();
		String sql = "DELETE FROM gw_car_list "
				+ "WHERE cl_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, cl_no);
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	
	
	/**
	 * 자동차에 대한 정보를 업데이트 한다.
	 * @param DTO
	 * @return
	 * @throws SQLException
	 */
	public int updateCar(CarListDTO DTO) throws SQLException {
		DBConnection();
		String sql = "UPDATE gw_car_list SET cl_number = ?, "
				+ "cl_model = ?, cl_mileage = ?, cl_use = ? "
				+ "WHERE cl_no = ?";
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DTO.getCl_number());
		psmt.setString(2, DTO.getCl_model());
		psmt.setInt(3, DTO.getCl_mileage());
		psmt.setInt(4, DTO.getCl_use());
		psmt.setInt(5, DTO.getCl_no());
		af = setQuery(psmt);
		
		DBClose();
		return af;
	}
	/**
	 * 전체적인 자동차의 모든 스케줄을 뽑아낸다. (월을 기준으로)
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public List<CarStatusDTO> getCarSchedule(String date) throws SQLException {
		DBConnection();
		date = date+"-%";
		String sql = "SELECT * FROM "
				+ "gw_car_status WHERE "
				+ "cs_use_date LIKE ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, date);
		rs = getQuery(psmt);
		List<CarStatusDTO> list = new ArrayList<CarStatusDTO>();
		while (rs.next()) {
			CarStatusDTO n = new CarStatusDTO();
			n.setCs_no(rs.getInt("cs_no"));
			n.setCs_cl_number(rs.getInt("cs_cl_number"));
			n.setCs_member_no(rs.getInt("cs_member_no"));
			n.setCs_reason(rs.getString("cs_reason"));
			String fulldate = rs.getString("cs_use_date");
			String[] day = fulldate.split("-");
			n.setCs_use_date(day[2]);
			System.out.println(day[2]);
			list.add(n);
		}
		
		DBClose();
		return list;
	}
	
	/**
	 * 자동차 스케줄 게시판에 해당하는 cs_no 스케줄만 뽑아낸다
	 * @param cs_no
	 * @param year
	 * @param month
	 * @return
	 * @throws SQLException
	 */
	public List<CarStatusDTO> getCarNoSchedule(int cs_no, int year, int month) throws SQLException {
		DBConnection();
		String finddate = year+"-"+month+"-"+"%";
		String sql = "SELECT cs_no ,mb_no, mb_name, posi_name, mb_img, cs_reason, cs_use_date FROM "
				+ "gw_car_status cs "
				+ "LEFT JOIN gw_member m ON cs.cs_member_no = m.mb_no "
				+ "LEFT JOIN gw_position p ON m.mb_position_posi_level = p.posi_level "
				+ "WHERE "
				+ "cs.cs_cl_number = ? AND cs_use_date LIKE ? "
				+ "ORDER BY cs_use_date DESC";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, cs_no);
		psmt.setString(2, finddate);
		
		rs = getQuery(psmt);
		List<CarStatusDTO> list = new ArrayList<CarStatusDTO>();
		while (rs.next()) {
			CarStatusDTO n = new CarStatusDTO();
			n.setCs_no(rs.getInt("cs_no"));
			n.setMb_no(rs.getInt("mb_no"));
			n.setMb_name(rs.getString("mb_name"));
			n.setMb_posi_name(rs.getString("posi_name"));
			n.setMb_img(rs.getString("mb_img"));
			n.setCs_reason(rs.getString("cs_reason"));
			String tempdate = rs.getString("cs_use_date");
			String[] date = tempdate.split("-");
			n.setCs_use_date(date[2]);
			list.add(n);
		}
		DBClose();
		return list;
		
	}
	
	/**
	 * 자동차 스케줄 테이블 값 입력하기
	 * @param cl_no
	 * @param date
	 * @param mb_no
	 * @param reason
	 * @return
	 * @throws SQLException
	 */
	public int insertCarSchedule(int cl_no, String date, int mb_no, String reason) throws SQLException {
		DBConnection();
		String sql = "INSERT INTO gw_car_status(cs_cl_number, cs_member_no, cs_reason, cs_use_date, cs_certify)"
				+ "VALUES(?, ?, ?, ?, ?)";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, cl_no);
		psmt.setInt(2, mb_no);
		psmt.setString(3, reason);
		psmt.setString(4, date);
		psmt.setInt(5, 1);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	/**
	 * 자동차 스케줄 테이블의 로우를 삭제한다.
	 * @param mb_no
	 * @param cs_no
	 * @return
	 * @throws SQLException
	 */
	public int deleteCarSchedule(int mb_no, int cs_no) throws SQLException {
		DBConnection();
		String sql = "DELETE FROM gw_car_status WHERE cs_no = ? AND cs_member_no = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, cs_no);
		psmt.setInt(2, mb_no);
		af = setQuery(psmt);
		DBClose();
		return af;
	}
	
	
	/**
	 * 사용자가 차량을 스케줄에 집어넣기전에 중복되는 값이 있는지 체크한다.
	 * @param cs_cl_number
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	public int checkDupCarSchedule(int cs_cl_number, String date) throws SQLException {
		DBConnection();
		String sql = "SELECT count(*) count from gw_car_status "
				+ "WHERE cs_cl_number = ? AND cs_use_date = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, cs_cl_number);
		psmt.setString(2, date);
		
		rs = getQuery(psmt);
		while (rs.next()) {
			af = rs.getInt("count");
		}
		DBClose();
		return af;
	}
}





















