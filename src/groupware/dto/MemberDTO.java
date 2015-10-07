package groupware.dto;

import java.util.Date;

public class MemberDTO {
	private int mb_no; 
	private String mb_id;
	private String mb_password;
	private String mb_name;
	private String mb_email;
	private String mb_tel;
	private String mb_password_q;
	private String mb_password_a;
	private String mb_sex;
	private String mb_birth;
	private String mb_last_ip;
	private String mb_last_date;
	private String mb_img;
	private int mb_login_count;
	private int mb_certify;
	private int mb_work_type;
	private int mb_rule_ch_no;
	private int mb_dep_no;
	private int mb_position_posi_level;
	private int mb_use;
	private Date mb_join_date;
	
	/*
	 * 추가 부분
	 */
	private String dep_name;
	private String posi_name;
	private int invite_no;
	
	public int getMb_no() {
		return mb_no;
	}
	public void setMb_no(int mb_no) {
		this.mb_no = mb_no;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_password() {
		return mb_password;
	}
	public void setMb_password(String mb_password) {
		this.mb_password = mb_password;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_email() {
		return mb_email;
	}
	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}
	public String getMb_tel() {
		return mb_tel;
	}
	public void setMb_tel(String mb_tel) {
		this.mb_tel = mb_tel;
	}
	public String getMb_password_q() {
		return mb_password_q;
	}
	public void setMb_password_q(String mb_password_q) {
		this.mb_password_q = mb_password_q;
	}
	public String getMb_password_a() {
		return mb_password_a;
	}
	public void setMb_password_a(String mb_password_a) {
		this.mb_password_a = mb_password_a;
	}
	public String getMb_sex() {
		return mb_sex;
	}
	public void setMb_sex(String mb_sex) {
		this.mb_sex = mb_sex;
	}
	public String getMb_birth() {
		return mb_birth;
	}
	public void setMb_birth(String mb_birth) {
		this.mb_birth = mb_birth;
	}
	public Date getMb_join_date() {
		return mb_join_date;
	}
	public void setMb_join_date(Date mb_join_date) {
		this.mb_join_date = mb_join_date;
	}
	public String getMb_last_ip() {
		return mb_last_ip;
	}
	public void setMb_last_ip(String mb_last_ip) {
		this.mb_last_ip = mb_last_ip;
	}
	public String getMb_last_date() {
		return mb_last_date;
	}
	public void setMb_last_date(String mb_last_date) {
		this.mb_last_date = mb_last_date;
	}
	public int getMb_login_count() {
		return mb_login_count;
	}
	public void setMb_login_count(int i) {
		this.mb_login_count = i;
	}
	public int getMb_certify() {
		return mb_certify;
	}
	public void setMb_certify(int mb_certify) {
		this.mb_certify = mb_certify;
	}
	public int getMb_work_type() {
		return mb_work_type;
	}
	public void setMb_work_type(int mb_work_type) {
		this.mb_work_type = mb_work_type;
	}
	public int getMb_rule_ch_no() {
		return mb_rule_ch_no;
	}
	public void setMb_rule_ch_no(int mb_rule_ch_no) {
		this.mb_rule_ch_no = mb_rule_ch_no;
	}
	public int getMb_dep_no() {
		return mb_dep_no;
	}
	public void setMb_dep_no(int mb_dep_no) {
		this.mb_dep_no = mb_dep_no;
	}
	public int getMb_position_posi_level() {
		return mb_position_posi_level;
	}
	public void setMb_position_posi_level(int mb_position_posi_level) {
		this.mb_position_posi_level = mb_position_posi_level;
	}
	public int getMb_use() {
		return mb_use;
	}
	public void setMb_use(int mb_use) {
		this.mb_use = mb_use;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public String getPosi_name() {
		return posi_name;
	}
	public void setPosi_name(String posi_name) {
		this.posi_name = posi_name;
	}
	public String getMb_img() {
		return mb_img;
	}
	public void setMb_img(String mb_img) {
		this.mb_img = mb_img;
	}
	public int getInvite_no() {
		return invite_no;
	}
	public void setInvite_no(int invite_no) {
		this.invite_no = invite_no;
	}

}
