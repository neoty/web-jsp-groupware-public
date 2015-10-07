package groupware.dto;

import java.util.Date;

public class BoardDTO {
	private int bo_no;
	private String bo_type;
	private String bo_name;
	private String bo_describe;
	private int bo_use;
	private int bo_admin;
	private Date bo_create_date;
	
	/*
	 * 추가 부분
	 */
	private String mb_img;
	private String bo_mb_name;
	private String bo_mb_posi_name;
	private int bo_mb_no;

	public int getBo_no() {
		return bo_no;
	}
	public void setBo_no(int bo_no) {
		this.bo_no = bo_no;
	}
	public String getBo_type() {
		return bo_type;
	}
	public void setBo_type(String bo_type) {
		this.bo_type = bo_type;
	}
	public String getBo_name() {
		return bo_name;
	}
	public void setBo_name(String bo_name) {
		this.bo_name = bo_name;
	}
	public String getBo_describe() {
		return bo_describe;
	}
	public void setBo_describe(String bo_describe) {
		this.bo_describe = bo_describe;
	}
	public int getBo_use() {
		return bo_use;
	}
	public void setBo_use(int bo_use) {
		this.bo_use = bo_use;
	}
	public Date getBo_create_date() {
		return bo_create_date;
	}
	public void setBo_create_date(Date bo_create_date) {
		this.bo_create_date = bo_create_date;
	}
	public int getBo_admin() {
		return bo_admin;
	}
	public void setBo_admin(int bo_admin) {
		this.bo_admin = bo_admin;
	}
	public String getBo_mb_name() {
		return bo_mb_name;
	}
	public void setBo_mb_name(String bo_mb_name) {
		this.bo_mb_name = bo_mb_name;
	}
	public String getBo_mb_posi_name() {
		return bo_mb_posi_name;
	}
	public void setBo_mb_posi_name(String bo_mb_posi_name) {
		this.bo_mb_posi_name = bo_mb_posi_name;
	}
	public int getBo_mb_no() {
		return bo_mb_no;
	}
	public void setBo_mb_no(int bo_mb_no) {
		this.bo_mb_no = bo_mb_no;
	}
	public String getMb_img() {
		return mb_img;
	}
	public void setMb_img(String mb_img) {
		this.mb_img = mb_img;
	}


}
