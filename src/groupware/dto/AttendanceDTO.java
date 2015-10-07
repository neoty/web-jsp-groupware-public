package groupware.dto;

public class AttendanceDTO {
	private int att_no;
	private int att_member_no;
	private String att_s_date;
	private String att_e_date;
	
	
	// 추가 필드
	private String over_s;
	private String over_e;
	private String dayOfdate;
	
	
	public int getAtt_no() {
		return att_no;
	}
	public void setAtt_no(int att_no) {
		this.att_no = att_no;
	}
	public int getAtt_member_no() {
		return att_member_no;
	}
	public void setAtt_member_no(int att_member_no) {
		this.att_member_no = att_member_no;
	}
	public String getAtt_s_date() {
		return att_s_date;
	}
	public void setAtt_s_date(String att_s_date) {
		this.att_s_date = att_s_date;
	}
	public String getAtt_e_date() {
		return att_e_date;
	}
	public void setAtt_e_date(String att_e_date) {
		this.att_e_date = att_e_date;
	}
	public String getDayOfdate() {
		return dayOfdate;
	}
	public void setDayOfdate(String dayOfdate) {
		this.dayOfdate = dayOfdate;
	}
	public String getOver_s() {
		return over_s;
	}
	public void setOver_s(String over_s) {
		this.over_s = over_s;
	}
	public String getOver_e() {
		return over_e;
	}
	public void setOver_e(String over_e) {
		this.over_e = over_e;
	}
	
}
