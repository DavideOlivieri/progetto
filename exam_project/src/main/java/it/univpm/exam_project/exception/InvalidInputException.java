package it.univpm.exam_project.exception;

public class InvalidInputException extends Exception{

private String msg = "Error: Input invalid";
	
	public InvalidInputException() {
		super();
	}
	
	public InvalidInputException(String msg) {
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
