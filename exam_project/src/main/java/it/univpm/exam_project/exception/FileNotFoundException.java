package it.univpm.exam_project.exception;

public class FileNotFoundException extends Exception{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String msg = "Error: File not found";
	
	public FileNotFoundException() {
		super();
	}
	
	public FileNotFoundException(String msg) {
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
