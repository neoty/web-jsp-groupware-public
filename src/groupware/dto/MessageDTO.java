package groupware.dto;

public class MessageDTO {
	// 기본 DB 데이터
	private int mes_no;
	private String mes_subject;
	private String mes_content;
	private int mes_post_mb_no;
	private int mes_get_mb_no;
	private String mes_post_date;
	private String mes_confirm_date;
	private int mes_post_mb_storage;
	private int mes_get_mb_storage;
	private int mes_post_mb_remove;
	private int mes_get_mb_remove;
	private int mes_file_exists;
	
	// 추가 필요 데이터
	private String mes_post_mb_name;
	private int mes_post_mb_posi_no;
	private String mes_post_mb_posi_name;
	private String mes_get_mb_name;
	private int mes_get_mb_posi_no;
	private String mes_get_mb_posi_name;
	private int mes_file_no;
	private String mes_file_real_name;
	private String mes_file_enc_name;
	private int mes_total_cnt;
	private String mes_post_mb_img;
	private String mes_get_mb_img;
	
	/****************************************/
	public int getMes_no() {
		return mes_no;
	}
	public void setMes_no(int mes_no) {
		this.mes_no = mes_no;
	}
	public String getMes_subject() {
		return mes_subject;
	}
	public void setMes_subject(String mes_subject) {
		this.mes_subject = mes_subject;
	}
	public String getMes_content() {
		return mes_content;
	}
	public void setMes_content(String mes_content) {
		this.mes_content = mes_content;
	}
	public int getMes_post_mb_no() {
		return mes_post_mb_no;
	}
	public void setMes_post_mb_no(int mes_post_mb_no) {
		this.mes_post_mb_no = mes_post_mb_no;
	}
	public int getMes_get_mb_no() {
		return mes_get_mb_no;
	}
	public void setMes_get_mb_no(int mes_get_mb_no) {
		this.mes_get_mb_no = mes_get_mb_no;
	}
	public String getMes_post_date() {
		return mes_post_date;
	}
	public void setMes_post_date(String mes_post_date) {
		this.mes_post_date = mes_post_date;
	}
	public String getMes_confirm_date() {
		return mes_confirm_date;
	}
	public void setMes_confirm_date(String mes_confirm_date) {
		this.mes_confirm_date = mes_confirm_date;
	}
	public int getMes_post_mb_storage() {
		return mes_post_mb_storage;
	}
	public void setMes_post_mb_storage(int mes_post_mb_storage) {
		this.mes_post_mb_storage = mes_post_mb_storage;
	}
	public int getMes_get_mb_storage() {
		return mes_get_mb_storage;
	}
	public void setMes_get_mb_storage(int mes_get_mb_storage) {
		this.mes_get_mb_storage = mes_get_mb_storage;
	}
	public int getMes_post_mb_remove() {
		return mes_post_mb_remove;
	}
	public void setMes_post_mb_remove(int mes_post_mb_remove) {
		this.mes_post_mb_remove = mes_post_mb_remove;
	}
	public int getMes_get_mb_remove() {
		return mes_get_mb_remove;
	}
	public void setMes_get_mb_remove(int mes_get_mb_remove) {
		this.mes_get_mb_remove = mes_get_mb_remove;
	}
	public int getMes_file_exists() {
		return mes_file_exists;
	}
	public void setMes_file_exists(int mes_file_exists) {
		this.mes_file_exists = mes_file_exists;
	}
	public String getMes_post_mb_name() {
		return mes_post_mb_name;
	}
	public void setMes_post_mb_name(String mes_post_mb_name) {
		this.mes_post_mb_name = mes_post_mb_name;
	}
	public int getMes_post_mb_posi_no() {
		return mes_post_mb_posi_no;
	}
	public void setMes_post_mb_posi_no(int mes_post_mb_posi_no) {
		this.mes_post_mb_posi_no = mes_post_mb_posi_no;
	}
	public String getMes_post_mb_posi_name() {
		return mes_post_mb_posi_name;
	}
	public void setMes_post_mb_posi_name(String mes_post_mb_posi_name) {
		this.mes_post_mb_posi_name = mes_post_mb_posi_name;
	}
	public String getMes_get_mb_name() {
		return mes_get_mb_name;
	}
	public void setMes_get_mb_name(String mes_get_mb_name) {
		this.mes_get_mb_name = mes_get_mb_name;
	}
	public int getMes_get_mb_posi_no() {
		return mes_get_mb_posi_no;
	}
	public void setMes_get_mb_posi_no(int mes_get_mb_posi_no) {
		this.mes_get_mb_posi_no = mes_get_mb_posi_no;
	}
	public String getMes_get_mb_posi_name() {
		return mes_get_mb_posi_name;
	}
	public void setMes_get_mb_posi_name(String mes_get_mb_posi_name) {
		this.mes_get_mb_posi_name = mes_get_mb_posi_name;
	}
	public int getMes_file_no() {
		return mes_file_no;
	}
	public void setMes_file_no(int mes_file_no) {
		this.mes_file_no = mes_file_no;
	}
	public int getMes_total_cnt() {
		return mes_total_cnt;
	}
	public void setMes_total_cnt(int mes_total_cnt) {
		this.mes_total_cnt = mes_total_cnt;
	}
	public String getMes_post_mb_img() {
		return mes_post_mb_img;
	}
	public void setMes_post_mb_img(String mes_post_mb_img) {
		this.mes_post_mb_img = mes_post_mb_img;
	}
	public String getMes_get_mb_img() {
		return mes_get_mb_img;
	}
	public void setMes_get_mb_img(String mes_get_mb_img) {
		this.mes_get_mb_img = mes_get_mb_img;
	}
	public String getMes_file_real_name() {
		return mes_file_real_name;
	}
	public void setMes_file_real_name(String mes_file_real_name) {
		this.mes_file_real_name = mes_file_real_name;
	}
	public String getMes_file_enc_name() {
		return mes_file_enc_name;
	}
	public void setMes_file_enc_name(String mes_file_enc_name) {
		this.mes_file_enc_name = mes_file_enc_name;
	}
}