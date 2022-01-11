package it.univpm.exam_project.exception;

public class StateNotFoundException extends Exception{
	
	private String msg = "Error: State_Code not found";
	
	public StateNotFoundException() {
		super();
	}
	
	public StateNotFoundException(String msg) {
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
