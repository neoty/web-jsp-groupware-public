package groupware.dto;

import java.util.Date;

public class CarListDTO {
	private int cl_no;
	private String cl_number;
	private String cl_model;
	private int cl_mileage;
	private int cl_use;
	private String cl_create_date;
	

	public String getCl_model() {
		return cl_model;
	}
	public void setCl_model(String cl_model) {
		this.cl_model = cl_model;
	}
	public int getCl_mileage() {
		return cl_mileage;
	}
	public void setCl_mileage(int cl_mileage) {
		this.cl_mileage = cl_mileage;
	}
	public int getCl_use() {
		return cl_use;
	}
	public void setCl_use(int cl_use) {
		this.cl_use = cl_use;
	}
	public String getCl_create_date() {
		return cl_create_date;
	}
	public void setCl_create_date(String cl_create_date) {
		this.cl_create_date = cl_create_date;
	}
	public int getCl_no() {
		return cl_no;
	}
	public void setCl_no(int cl_no) {
		this.cl_no = cl_no;
	}
	public String getCl_number() {
		return cl_number;
	}
	public void setCl_number(String cl_number) {
		this.cl_number = cl_number;
	}
}
