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
	private String msg = "Error: The country_code searched for does not exist or is not present in our databases";
	
	public countryParamException() {
		super();
	}
	
	public countryParamException(String msg) {
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
