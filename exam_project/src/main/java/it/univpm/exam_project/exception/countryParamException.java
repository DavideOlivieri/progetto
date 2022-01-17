package it.univpm.exam_project.exception;

/**
 * Exception that is called if the entered country_code is incorrect.
 * 
 * @author Jacopo Coloccioni
 * @author Davide Olivieri
 *
 */
public class countryParamException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  Error message that will be printed on the screen.
	 */
	private String msg = "Error: The country_code searched for does not exist or is not present in our databases \n"
			+ "The two letter must be uppercase for every Country code. \n"
			+ "These are some examples of country codes : \n"
			+ "US, CA, PL";
	
	/**
	 *  Default constructor.
	 */
	public countryParamException() {
		super();
	}
	
	/**
	 *  constructor with one parameter.
	 * 
	 * @param msg
	 */
	public countryParamException(String msg) {
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
