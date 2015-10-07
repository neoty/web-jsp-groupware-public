package groupware.dto;

import java.util.Date;

public class BoardContentDTO {
	private int bc_no;
	private int bc_mb_no;
	private String bc_type;
	private int bc_code;
	private String bc_subject;
	private String bc_content;
	private String bc_date;
	private int bc_notice;
	private int bc_secret;
	private int bc_read_count;
	private int bc_reply_count;
	private int bc_file_exists;
	
	// 회원정보 추가 필드
	private int mb_no;
	private String posi_name;
	private String dep_name;
	private String mb_name;
	private String mb_img;
	
	// 파일 관련 추가 필드
	private String file_relname1;
	private String file_encname1;
	private String file_relname2;
	private String file_encname2;

	
	public int getBc_no() {
		return bc_no;
	}
	public void setBc_no(int bc_no) {
		this.bc_no = bc_no;
	}
	public int getBc_mb_no() {
		return bc_mb_no;
	}
	public void setBc_mb_no(int bc_mb_no) {
		this.bc_mb_no = bc_mb_no;
	}
	public String getBc_type() {
		return bc_type;
	}
	public void setBc_type(String bc_type) {
		this.bc_type = bc_type;
	}
	public int getBc_code() {
		return bc_code;
	}
	public void setBc_code(int bc_code) {
		this.bc_code = bc_code;
	}
	public String getBc_subject() {
		return bc_subject;
	}
	public void setBc_subject(String bc_subject) {
		this.bc_subject = bc_subject;
	}
	public String getBc_content() {
		return bc_content;
	}
	public void setBc_content(String bc_content) {
		this.bc_content = bc_content;
	}
	public String getBc_date() {
		return bc_date;
	}
	public void setBc_date(String string) {
		this.bc_date = string;
	}
	public int getMb_no() {
		return mb_no;
	}
	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}
	public String getPosi_name() {
		return posi_name;
	}
	public void setPosi_name(String posi_name) {
		this.posi_name = posi_name;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public int getBc_notice() {
		return bc_notice;
	}
	public void setBc_notice(int bc_notice) {
		this.bc_notice = bc_notice;
	}
	public int getBc_secret() {
		return bc_secret;
	}
	public void setBc_secret(int bc_secret) {
		this.bc_secret = bc_secret;
	}
	public int getBc_file_exists() {
		return bc_file_exists;
	}
	public void setBc_file_exists(int bc_file_exists) {
		this.bc_file_exists = bc_file_exists;
	}
	public String getMb_img() {
		return mb_img;
	}
	public void setMb_img(String mb_img) {
		this.mb_img = mb_img;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public String getFile_relname1() {
		return file_relname1;
	}
	public void setFile_relname1(String file_relname1) {
		this.file_relname1 = file_relname1;
	}
	public String getFile_encname1() {
		return file_encname1;
	}
	public void setFile_encname1(String file_encname1) {
		this.file_encname1 = file_encname1;
	}
	public String getFile_relname2() {
		return file_relname2;
	}
	public void setFile_relname2(String file_relname2) {
		this.file_relname2 = file_relname2;
	}
	public String getFile_encname2() {
		return file_encname2;
	}
	public void setFile_encname2(String file_encname2) {
		this.file_encname2 = file_encname2;
	}
	public int getBc_read_count() {
		return bc_read_count;
	}
	public void setBc_read_count(int bc_read_count) {
		this.bc_read_count = bc_read_count;
	}
	public int getBc_reply_count() {
		return bc_reply_count;
	}
	public void setBc_reply_count(int bc_reply_count) {
		this.bc_reply_count = bc_reply_count;
	}
}
