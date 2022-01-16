package it.univpm.exam_project.exception;

/**
 * Exception that is called if the entered state_code is incorrect.
 * 
 * @author Davide Olivieri
 * @author Jacopo Coloccioni
 *
 */
public class stateParamException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg = "Error: The state_code searched for does not exist or is not present in our databases. \n"
			+ "The two letter must be uppercase for every State code. \n"
			+ "These are some examples of State codes: \n"
			+ "for US: AL, AK, AZ, CA, CO, IN, LA, NY. \n"
			+ "for CA: AB, BC, NB, NL, NS, ON, PE, QC.";

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
