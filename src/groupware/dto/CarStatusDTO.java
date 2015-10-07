package groupware.dto;

import java.util.Date;

public class CarStatusDTO {
	private int cs_no;
	private int cs_cl_number;
	private int cs_member_no;
	private String cs_reason;
	private String cs_use_date;
	private int cs_certify;
	private Date cs_create_date;
	
	// 추가 필드
	private String mb_img;
	private String mb_name;
	private String mb_posi_name;
	private int mb_no;
	
	public int getCs_no() {
		return cs_no;
	}
	public void setCs_no(int cs_no) {
		this.cs_no = cs_no;
	}
	public int getCs_cl_number() {
		return cs_cl_number;
	}
	public void setCs_cl_number(int cs_cl_number) {
		this.cs_cl_number = cs_cl_number;
	}
	public int getCs_member_no() {
		return cs_member_no;
	}
	public void setCs_member_no(int cs_member_no) {
		this.cs_member_no = cs_member_no;
	}
	public String getCs_reason() {
		return cs_reason;
	}
	public void setCs_reason(String cs_reason) {
		this.cs_reason = cs_reason;
	}
	public String getCs_use_date() {
		return cs_use_date;
	}
	public void setCs_use_date(String cs_use_date) {
		this.cs_use_date = cs_use_date;
	}
	public int getCs_certify() {
		return cs_certify;
	}
	public void setCs_certify(int cs_certify) {
		this.cs_certify = cs_certify;
	}
	public Date getCs_create_date() {
		return cs_create_date;
	}
	public void setCs_create_date(Date cs_create_date) {
		this.cs_create_date = cs_create_date;
	}
	public String getMb_img() {
		return mb_img;
	}
	public void setMb_img(String mb_img) {
		this.mb_img = mb_img;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_posi_name() {
		return mb_posi_name;
	}
	public void setMb_posi_name(String mb_posi_name) {
		this.mb_posi_name = mb_posi_name;
	}
	public int getMb_no() {
		return mb_no;
	}
	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}
}
