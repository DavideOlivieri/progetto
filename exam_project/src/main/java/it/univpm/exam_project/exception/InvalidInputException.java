package it.univpm.exam_project.exception;

/**
 * Exception that is called if a generic input value is incorrect.
 * 
 * @author Jacopo Coloccioni
 * @author Davide Olivieri
 *
 */
public class InvalidInputException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg = "Error: Input invalid \n"
			+ "The allowed inputs are: \n"
			+ "not to see the events: no, No, NO, false. \n"
			+ "to see the events: si, Si, SI, yes, true.";

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
