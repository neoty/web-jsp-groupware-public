package groupware.dto;

import java.util.Date;

public class LogDTO {
	private int log_no;
	private String log_ip;
	private String log_member_id;
	private String log_subject;
	private String log_content;
	private Date log_create_date;
	
	public int getLog_no() {
		return log_no;
	}
	public void setLog_no(int log_no) {
		this.log_no = log_no;
	}
	public String getLog_ip() {
		return log_ip;
	}
	public void setLog_ip(String log_ip) {
		this.log_ip = log_ip;
	}
	public String getMember_id() {
		return log_member_id;
	}
	public void setMember_id(String member_ip) {
		this.log_member_id = member_ip;
	}
	public String getLog_subject() {
		return log_subject;
	}
	public void setLog_subject(String log_subject) {
		this.log_subject = log_subject;
	}
	public String getLog_content() {
		return log_content;
	}
	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}
	public Date getLog_create_date() {
		return log_create_date;
	}
	public void setLog_create_date(Date log_create_date) {
		this.log_create_date = log_create_date;
	}
	
}
