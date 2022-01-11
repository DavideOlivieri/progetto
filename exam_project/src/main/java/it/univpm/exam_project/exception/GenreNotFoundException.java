package it.univpm.exam_project.exception;

public class GenreNotFoundException extends Exception{

private String msg = "Error: Genre not found";
	
	public GenreNotFoundException() {
		super();
	}
	
	public GenreNotFoundException(String msg) {
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

