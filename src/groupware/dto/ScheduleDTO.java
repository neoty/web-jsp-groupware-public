package groupware.dto;

import java.util.Date;

public class ScheduleDTO {
	private int sc_no;
	private int sc_member_no;
	private int sc_dep_no;
	private int sc_progress;
	private String sc_subject;
	private String sc_content;
	private String sc_s_date;
	private String sc_e_date;
	private Date sc_create_date;
	private int sc_team;
	
	private int sc_s_day;
	private int sc_e_day;
	
	public int getSc_no() {
		return sc_no;
	}
	public void setSc_no(int sc_no) {
		this.sc_no = sc_no;
	}
	public int getSc_member_no() {
		return sc_member_no;
	}
	public void setSc_member_no(int sc_member_no) {
		this.sc_member_no = sc_member_no;
	}
	public int getSc_dep_no() {
		return sc_dep_no;
	}
	public void setSc_dep_no(int sc_dep_no) {
		this.sc_dep_no = sc_dep_no;
	}
	public int getSc_progress() {
		return sc_progress;
	}
	public void setSc_progress(int sc_progress) {
		this.sc_progress = sc_progress;
	}
	public String getSc_subject() {
		return sc_subject;
	}
	public void setSc_subject(String sc_subject) {
		this.sc_subject = sc_subject;
	}
	public String getSc_content() {
		return sc_content;
	}
	public void setSc_content(String sc_content) {
		this.sc_content = sc_content;
	}
	public String getSc_s_date() {
		return sc_s_date;
	}
	public void setSc_s_date(String sc_s_date) {
		this.sc_s_date = sc_s_date;
	}
	public String getSc_e_date() {
		return sc_e_date;
	}
	public void setSc_e_date(String sc_e_date) {
		this.sc_e_date = sc_e_date;
	}
	public Date getSc_create_date() {
		return sc_create_date;
	}
	public void setSc_crate_date(Date sc_create_date) {
		this.sc_create_date = sc_create_date;
	}
	public int getSc_s_day() {
		return sc_s_day;
	}
	public void setSc_s_day(int sc_s_day) {
		this.sc_s_day = sc_s_day;
	}
	public int getSc_e_day() {
		return sc_e_day;
	}
	public void setSc_e_day(int sc_e_day) {
		this.sc_e_day = sc_e_day;
	}
	public int getSc_team() {
		return sc_team;
	}
	public void setSc_team(int sc_team) {
		this.sc_team = sc_team;
	}
	
}
