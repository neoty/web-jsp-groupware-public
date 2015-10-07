package groupware.commons;

import java.sql.SQLException;

import groupware.dao.ConfigDao;
import groupware.dto.ConfigDTO;

import com.sun.jndi.cosnaming.IiopUrl.Address;

public class IPCheck {
	private static IPCheck _instance;

	public static IPCheck getIntance() {
		if (_instance == null) {
			System.out.println("ip check instance");
			_instance = new IPCheck();
		}
		return _instance;
	}
	public boolean checkIp(String clientIp) throws SQLException {
		Long requestIp = ipToInt(clientIp);

		ConfigDTO DTO = new ConfigDTO();
		DTO = ConfigDao.getInstance().serverStatus();
		if ((DTO.getCf_grant_ip() == null || DTO.getCf_grant_ip().equals("")) && (DTO.getCf_band_ip() == null || DTO.getCf_band_ip().equals(""))) {
			// 아이피에 대한 정보가 전부 없을때
			return true;
		} else if(!DTO.getCf_grant_ip().equals("")) {
			// grant_ip 가 공백이거나 null 값이 아닐때 (band_ip 의 입력은 입력 단계에서부터 입력하지 못하게 한다.)
			Long grant_ip = ipToInt(DTO.getCf_grant_ip());
			
			if (requestIp.equals(grant_ip)) {
				return true;
			} else {
				return false;
			}
		}  else if (!DTO.getCf_band_ip().equals("")) {
			String tmpBandIp = DTO.getCf_band_ip();
			String[] tmp1 = tmpBandIp.split(" - ");
			Long start_ip = ipToInt(tmp1[0]);
			Long end_ip =  ipToInt(tmp1[1]);
			if ((requestIp >= start_ip) && (requestIp <= end_ip)) {
				return true;
			}
		}
		return false;
	}

	public Long ipToInt(String ip) {
		String[] addrArray = ip.split("\\.");
		long num = 0;
		for (int i = 0; i < addrArray.length; i++) {
			int power = 3-i;
			num += ((Integer.parseInt(addrArray[i])%256 * Math.pow(256,power)));
		}
		return num;
	}
}
