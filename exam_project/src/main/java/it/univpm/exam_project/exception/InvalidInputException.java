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
	/**
	 *  Error message that will be printed on the screen.
	 */
	private String msg = "Error: Input invalid for seeEvents \n"
			+ "The allowed inputs are: \n"
			+ "not to see the events: no, No, NO, false. \n"
			+ "to see the events: si, Si, SI, yes, true.";

	/**
	 *  Default constructor.
	 */
	public InvalidInputException() {
		super();
	}

	/**
	 *  constructor with one parameter.
	 * 
	 * @param msg
	 */
	public InvalidInputException(String msg) {
		super(msg);
		this.msg=msg;
	}

	/**
	 * 
	 * @return Error message
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * set Error message.
	 * 
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * set and return the message.
	 * 
	 * @param msg
	 * @return msg
	 */
	public String getMsg(String msg) {
		this.msg=msg;
		return this.msg;
	}

}
