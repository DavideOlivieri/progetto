package it.univpm.exam_project.exception;

public class stateParamException extends Exception{


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg = "Error: The state_code searched for does not exist or is not present in our databases";
	
	public stateParamException() {
		super();
	}
	
	public stateParamException(String msg) {
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
