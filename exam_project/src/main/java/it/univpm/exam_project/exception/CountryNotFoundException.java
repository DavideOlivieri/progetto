package it.univpm.exam_project.exception;

public class CountryNotFoundException extends Exception{
	
	private String msg = "Error: Country_Code not found";
	
	public CountryNotFoundException() {
		super();
	}
	
	public CountryNotFoundException(String msg) {
		super(msg);
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsg(String msg) {
		this.msg=msg;
		return this.msg;
	}

}
