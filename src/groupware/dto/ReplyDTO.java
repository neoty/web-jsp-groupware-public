package groupware.dto;

public class ReplyDTO {
	int re_no;
	int re_bc_no;
	String re_content;
	int re_mb_no;
	String re_create_date;
	
	
	// 추가 필드
	int mb_no;
	String mb_name;
	String mb_img;
	String mb_posi_name;
	
	public int getRe_no() {
		return re_no;
	}
	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}
	public int getRe_bc_no() {
		return re_bc_no;
	}
	public void setRe_bc_no(int re_bc_no) {
		this.re_bc_no = re_bc_no;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public int getRe_mb_no() {
		return re_mb_no;
	}
	public void setRe_mb_no(int re_mb_no) {
		this.re_mb_no = re_mb_no;
	}
	public String getRe_create_date() {
		return re_create_date;
	}
	public void setRe_create_date(String re_create_date) {
		this.re_create_date = re_create_date;
	}
	public int getMb_no() {
		return mb_no;
	}
	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_img() {
		return mb_img;
	}
	public void setMb_img(String mb_img) {
		this.mb_img = mb_img;
	}
	public String getMb_posi_name() {
		return mb_posi_name;
	}
	public void setMb_posi_name(String mb_posi_name) {
		this.mb_posi_name = mb_posi_name;
	}
}
