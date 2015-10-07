package groupware.dto;

public class WorkFlowDTO {
	private int wf_no;
	private int wf_post_mb;
	private int wf_status;
	private String wf_doc_no;
	private int wf_scope;
	
	private int wf_mb_first;
	private int wf_first_status;
	private String wf_first_note;
	private String wf_first_confirm_date;
	
	private int wf_mb_second;
	private int wf_second_status;
	private String wf_second_note;
	private String wf_second_confirm_date;
	
	private int wf_mb_third;
	private int wf_third_status;
	private String wf_third_note;
	private String wf_third_confirm_date;
	
	private String wf_subject;
	private String wf_create_date;
	
	// 추가 필드
	private String wf_post_mb_name;
	private String wf_post_posi_name;
	private String wf_post_dep_name;
	
	private String wf_first_mb_name;
	private String wf_first_posi_name;
	private String wf_first_dep_name;

	private String wf_second_mb_name;
	private String wf_second_posi_name;
	private String wf_second_dep_name;
	
	private String wf_third_mb_name;
	private String wf_third_posi_name;
	private String wf_third_dep_name;
	
	private String oriDocName;
	private String encDocName;
	private String oriFileName;
	private String encFileName;
	
	private String file_icon;
	
	// 게시판쪽 추가 필드
	private String lastApprove_mb_name;
	private int lastApprove_mb_no;
	private String lastApprove_mb_img;
	private String lastApprove_mb_posi_name;
	// *********
	

	public int getWf_no() {
		return wf_no;
	}
	public void setWf_no(int wf_no) {
		this.wf_no = wf_no;
	}
	public int getWf_post_mb() {
		return wf_post_mb;
	}
	public void setWf_post_mb(int wf_post_mb) {
		this.wf_post_mb = wf_post_mb;
	}
	public int getWf_status() {
		return wf_status;
	}
	public void setWf_status(int wf_status) {
		this.wf_status = wf_status;
	}
	public String getWf_doc_no() {
		return wf_doc_no;
	}
	public void setWf_doc_no(String wf_doc_no) {
		this.wf_doc_no = wf_doc_no;
	}
	public int getWf_scope() {
		return wf_scope;
	}
	public void setWf_scope(int wf_scope) {
		this.wf_scope = wf_scope;
	}
	public int getWf_mb_first() {
		return wf_mb_first;
	}
	public void setWf_mb_first(int wf_mb_first) {
		this.wf_mb_first = wf_mb_first;
	}
	public int getWf_first_status() {
		return wf_first_status;
	}
	public void setWf_first_status(int wf_first_status) {
		this.wf_first_status = wf_first_status;
	}
	public String getWf_first_note() {
		return wf_first_note;
	}
	public void setWf_first_note(String wf_first_note) {
		this.wf_first_note = wf_first_note;
	}
	public String getWf_first_confirm_date() {
		return wf_first_confirm_date;
	}
	public void setWf_first_confirm_date(String wf_first_confirm_date) {
		this.wf_first_confirm_date = wf_first_confirm_date;
	}
	public int getWf_mb_second() {
		return wf_mb_second;
	}
	public void setWf_mb_second(int wf_mb_second) {
		this.wf_mb_second = wf_mb_second;
	}
	public int getWf_second_status() {
		return wf_second_status;
	}
	public void setWf_second_status(int wf_second_status) {
		this.wf_second_status = wf_second_status;
	}
	public String getWf_second_note() {
		return wf_second_note;
	}
	public void setWf_second_note(String wf_second_note) {
		this.wf_second_note = wf_second_note;
	}
	public String getWf_second_confirm_date() {
		return wf_second_confirm_date;
	}
	public void setWf_second_confirm_date(String wf_second_confirm_date) {
		this.wf_second_confirm_date = wf_second_confirm_date;
	}
	public int getWf_mb_third() {
		return wf_mb_third;
	}
	public void setWf_mb_third(int wf_mb_third) {
		this.wf_mb_third = wf_mb_third;
	}
	public int getWf_third_status() {
		return wf_third_status;
	}
	public void setWf_third_status(int wf_third_status) {
		this.wf_third_status = wf_third_status;
	}
	public String getWf_third_note() {
		return wf_third_note;
	}
	public void setWf_third_note(String wf_third_note) {
		this.wf_third_note = wf_third_note;
	}
	public String getWf_third_confirm_date() {
		return wf_third_confirm_date;
	}
	public void setWf_third_confirm_date(String wf_third_confirm_date) {
		this.wf_third_confirm_date = wf_third_confirm_date;
	}
	public String getWf_subject() {
		return wf_subject;
	}
	public void setWf_subject(String wf_subject) {
		this.wf_subject = wf_subject;
	}
	public String getWf_create_date() {
		return wf_create_date;
	}
	public void setWf_create_date(String wf_create_date) {
		this.wf_create_date = wf_create_date;
	}
	public String getWf_post_mb_name() {
		return wf_post_mb_name;
	}
	public void setWf_post_mb_name(String wf_post_mb_name) {
		this.wf_post_mb_name = wf_post_mb_name;
	}
	public String getWf_post_posi_name() {
		return wf_post_posi_name;
	}
	public void setWf_post_posi_name(String wf_post_posi_name) {
		this.wf_post_posi_name = wf_post_posi_name;
	}
	public String getWf_post_dep_name() {
		return wf_post_dep_name;
	}
	public void setWf_post_dep_name(String wf_post_dep_name) {
		this.wf_post_dep_name = wf_post_dep_name;
	}
	public String getWf_first_mb_name() {
		return wf_first_mb_name;
	}
	public void setWf_first_mb_name(String wf_first_mb_name) {
		this.wf_first_mb_name = wf_first_mb_name;
	}
	public String getWf_first_posi_name() {
		return wf_first_posi_name;
	}
	public void setWf_first_posi_name(String wf_first_posi_name) {
		this.wf_first_posi_name = wf_first_posi_name;
	}
	public String getWf_first_dep_name() {
		return wf_first_dep_name;
	}
	public void setWf_first_dep_name(String wf_first_dep_name) {
		this.wf_first_dep_name = wf_first_dep_name;
	}
	public String getWf_second_mb_name() {
		return wf_second_mb_name;
	}
	public void setWf_second_mb_name(String wf_second_mb_name) {
		this.wf_second_mb_name = wf_second_mb_name;
	}
	public String getWf_second_posi_name() {
		return wf_second_posi_name;
	}
	public void setWf_second_posi_name(String wf_second_posi_name) {
		this.wf_second_posi_name = wf_second_posi_name;
	}
	public String getWf_second_dep_name() {
		return wf_second_dep_name;
	}
	public void setWf_second_dep_name(String wf_second_dep_name) {
		this.wf_second_dep_name = wf_second_dep_name;
	}
	public String getWf_third_mb_name() {
		return wf_third_mb_name;
	}
	public void setWf_third_mb_name(String wf_third_mb_name) {
		this.wf_third_mb_name = wf_third_mb_name;
	}
	public String getWf_third_posi_name() {
		return wf_third_posi_name;
	}
	public void setWf_third_posi_name(String wf_third_posi_name) {
		this.wf_third_posi_name = wf_third_posi_name;
	}
	public String getWf_third_dep_name() {
		return wf_third_dep_name;
	}
	public void setWf_third_dep_name(String wf_third_dep_name) {
		this.wf_third_dep_name = wf_third_dep_name;
	}
	public String getOriDocName() {
		return oriDocName;
	}
	public void setOriDocName(String oriDocName) {
		this.oriDocName = oriDocName;
	}
	public String getEncDocName() {
		return encDocName;
	}
	public void setEncDocName(String encDocName) {
		this.encDocName = encDocName;
	}
	public String getOriFileName() {
		return oriFileName;
	}
	public void setOriFileName(String oriFileName) {
		this.oriFileName = oriFileName;
	}
	public String getEncFileName() {
		return encFileName;
	}
	public void setEncFileName(String encFileName) {
		this.encFileName = encFileName;
	}
	public String getFile_icon() {
		return file_icon;
	}
	public void setFile_icon(String file_icon) {
		this.file_icon = file_icon;
	}
	public String getLastApprove_mb_name() {
		return lastApprove_mb_name;
	}
	public void setLastApprove_mb_name(String lastApprove_mb_name) {
		this.lastApprove_mb_name = lastApprove_mb_name;
	}
	public int getLastApprove_mb_no() {
		return lastApprove_mb_no;
	}
	public void setLastApprove_mb_no(int lastApprove_mb_no) {
		this.lastApprove_mb_no = lastApprove_mb_no;
	}
	public String getLastApprove_mb_img() {
		return lastApprove_mb_img;
	}
	public void setLastApprove_mb_img(String lastApprove_mb_img) {
		this.lastApprove_mb_img = lastApprove_mb_img;
	}
	public String getLastApprove_mb_posi_name() {
		return lastApprove_mb_posi_name;
	}
	public void setLastApprove_mb_posi_name(String lastApprove_mb_posi_name) {
		this.lastApprove_mb_posi_name = lastApprove_mb_posi_name;
	}
}
