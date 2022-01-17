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
	/**
	 *  Error message that will be printed on the screen.
	 */
	private String msg = "Error: The segment searched for does not exist or is not present in our databases. \n"
			+ "The first letter must be uppercase for every genre. \n"
			+ "These are some examples of segments: \n"
			+ "Sports, Music, Miscellaneous.";

	/**
	 *  Default constructor.
	 */
	public segmentParamException() {
		super();
	}

	/**
	 *  constructor with one parameter.
	 * 
	 * @param msg
	 */
	public segmentParamException(String msg) {
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

