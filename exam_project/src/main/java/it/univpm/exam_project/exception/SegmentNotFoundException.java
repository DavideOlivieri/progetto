package it.univpm.exam_project.exception;

public class SegmentNotFoundException extends Exception{

private String msg = "Error: Segment not found";
	
	public SegmentNotFoundException() {
		super();
	}
	
	public SegmentNotFoundException(String msg) {
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
