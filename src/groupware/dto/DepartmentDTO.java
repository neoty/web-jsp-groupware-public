package groupware.dto;

import java.util.Date;

public class DepartmentDTO {
	private int dep_no;
	private String dep_name;
	private String dep_describe;
	private int dep_admin;
	private int dep_use;
	private String dep_create_date;
	
	/*
	 * 추가 햇음
	 */
	private String dep_posi_name;
	private String dep_mb_name;
	private int dep_mb_no;
	
	public int getDep_no() {
		return dep_no;
	}
	public void setDep_no(int dep_no) {
		this.dep_no = dep_no;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public String getDep_describe() {
		return dep_describe;
	}
	public void setDep_describe(String dep_describe) {
		this.dep_describe = dep_describe;
	}
	public int getDep_admin() {
		return dep_admin;
	}
	public void setDep_admin(int dep_admin) {
		this.dep_admin = dep_admin;
	}
	public int getDep_use() {
		return dep_use;
	}
	public void setDep_use(int dep_use) {
		this.dep_use = dep_use;
	}
	public String getDep_create_date() {
		return dep_create_date;
	}
	public void setDep_create_date(String string) {
		this.dep_create_date = string;
	}
	public String getDep_posi_name() {
		return dep_posi_name;
	}
	public void setDep_posi_name(String dep_posi_name) {
		this.dep_posi_name = dep_posi_name;
	}
	public String getDep_mb_name() {
		return dep_mb_name;
	}
	public void setDep_mb_name(String dep_mb_name) {
		this.dep_mb_name = dep_mb_name;
	}
	public int getDep_mb_no() {
		return dep_mb_no;
	}
	public void setDep_mb_no(int dep_mb_no) {
		this.dep_mb_no = dep_mb_no;
	}

}
