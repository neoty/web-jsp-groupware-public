package groupware.dto;

public class FileDTO {
	private int file_no;
	private String file_part;
	private int file_number;
	private String file_relname1;
	private String file_encname1;
	private String file_relname2;
	private String file_encname2;
	private String file_create_date;
	
	private String file_number_list;
	
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public String getFile_part() {
		return file_part;
	}
	public void setFile_part(String file_part) {
		this.file_part = file_part;
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
	public String getFile_create_date() {
		return file_create_date;
	}
	public void setFile_create_date(String file_create_date) {
		this.file_create_date = file_create_date;
	}
	public int getFile_number() {
		return file_number;
	}
	public void setFile_number(int file_number) {
		this.file_number = file_number;
	}
	public String getFile_number_list() {
		return file_number_list;
	}
	public void setFile_number_list(String file_number_list) {
		this.file_number_list = file_number_list;
	}
}
