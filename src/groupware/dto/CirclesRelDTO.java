package groupware.dto;

import java.util.Date;

public class CirclesRelDTO {
	private int cr_circles_no;
	private int cr_member_no;
	private Date cr_create_date;
	
	public int getCr_circles_no() {
		return cr_circles_no;
	}
	public void setCr_circles_no(int cr_circles_no) {
		this.cr_circles_no = cr_circles_no;
	}
	public int getCr_member_no() {
		return cr_member_no;
	}
	public void setCr_member_no(int cr_member_no) {
		this.cr_member_no = cr_member_no;
	}
	public Date getCr_create_date() {
		return cr_create_date;
	}
	public void setCr_create_date(Date cr_create_date) {
		this.cr_create_date = cr_create_date;
	}
}
