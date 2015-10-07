package groupware.dto;

import java.util.Date;

public class ProjectRelDTO {
	private int pr_project_no;
	private int pr_member_no;
	private Date pr_create_date;
	
	public int getPr_project_no() {
		return pr_project_no;
	}
	public void setPr_project_no(int pr_project_no) {
		this.pr_project_no = pr_project_no;
	}
	public int getPr_member_no() {
		return pr_member_no;
	}
	public void setPr_member_no(int pr_member_no) {
		this.pr_member_no = pr_member_no;
	}
	public Date getPr_create_date() {
		return pr_create_date;
	}
	public void setPr_create_date(Date pr_create_date) {
		this.pr_create_date = pr_create_date;
	}
}
