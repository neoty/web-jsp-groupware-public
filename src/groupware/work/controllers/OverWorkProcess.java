package groupware.work.controllers;

import groupware.dto.AttendanceDTO;

import java.util.List;

public class OverWorkProcess {
	private static OverWorkProcess _instance;
	public static OverWorkProcess getInstance() {
		if (_instance == null) {
			_instance = new OverWorkProcess();
		}
		return _instance;
	}
	
	public List<AttendanceDTO> caluStartTime(String sTimeLimit, List<AttendanceDTO> list) {
		String[] limit = sTimeLimit.split("\\:");
		int hLimit = Integer.parseInt(limit[0]);
		int mLimit = Integer.parseInt(limit[1]);
		
		for (int i = 0; i < list.size(); i++) {
			String get_time = list.get(i).getAtt_s_date();
			String[] spl_time = get_time.split("\\:");
			int arriveHour = Integer.parseInt(spl_time[0]);
			int arriveMin = Integer.parseInt(spl_time[1]);
			int total = 0;
			if (hLimit <= arriveHour) {
				if (hLimit < arriveHour) {
					total = total + ((arriveHour - hLimit)*60);
				}
				if (mLimit <= arriveMin) {
					if (mLimit < arriveMin) {
						total = total + (arriveMin - mLimit);
					}
				}
			}
			
			String over_s = "+";
			if (60 <= total) {
				int h = 0;
				int m = 0;
				
				h = total % 60;
				int tmpH = (total / 60);
				if (h == 0) {
					// 분을 60으로 나눴는데 나머지 0 일경우 초과 시간에서 분은 없는걸로 간주한다.
					over_s += tmpH + "시간";
				} else {
					over_s += tmpH + "시간 " + h +"분";
				}

			} else {
				// total 이 60보다 작으므로 초과 시간은 없고 초과 분만 있는것으로 간주한다.
				over_s += total + "분";
			}
			list.get(i).setOver_s(over_s);
		}
		
		return list;
	}
	
	public List<AttendanceDTO> caluEndTime(String eTimeLimit, List<AttendanceDTO> list) {
		String[] limit = eTimeLimit.split("\\:");
		int hLimit = Integer.parseInt(limit[0]);
		int mLimit = Integer.parseInt(limit[1]);
		
		for (int i = 0; i < list.size(); i++) {
			String get_time = list.get(i).getAtt_e_date();
			if (get_time != null) {
				
			
			String[] spl_time = get_time.split("\\:");
			int arriveHour = Integer.parseInt(spl_time[0]);
			int arriveMin = Integer.parseInt(spl_time[1]);
			int total = 0;
			if (hLimit <= arriveHour) {
				if (hLimit < arriveHour) {
					total = total + ((arriveHour - hLimit)*60);
				}
				if (mLimit <= arriveMin) {
					if (mLimit < arriveMin) {
						total = total + (arriveMin - mLimit);
					}
				}
			}
			
			String over_s = "+";
			if (60 <= total) {
				int h = 0;
				int m = 0;
				
				h = total % 60;
				int tmpH = (total / 60);
				if (h == 0) {
					// 분을 60으로 나눴는데 나머지 0 일경우 초과 시간에서 분은 없는걸로 간주한다.
					over_s += tmpH + "시간";
				} else {
					over_s += tmpH + "시간 " + h +"분";
				}

			} else {
				// total 이 60보다 작으므로 초과 시간은 없고 초과 분만 있는것으로 간주한다.
				over_s += total + "분";
			}
			
			list.get(i).setOver_e(over_s);
			}
		}
		
		return list;
	}
}
