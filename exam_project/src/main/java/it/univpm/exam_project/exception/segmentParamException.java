package it.univpm.exam_project.exception;

/**
 * Exception that is called if the entered segment is incorrect. 
 * 
 * @author Davide Olivieri
 * @author Jacopo Coloccioni
 *
 */
public class segmentParamException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg = "Error: The segment searched for does not exist or is not present in our databases";

	public segmentParamException() {
		super();
	}

	public segmentParamException(String msg) {
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

