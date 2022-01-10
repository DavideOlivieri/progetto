package it.univpm.exam_project.models;


/**
 * @author DavideOlivieri
 * @author JacopoColoccioni
 * 
 * Class that defines events
 */

public class Events {
	/**
	 * parameters
	 */
	private String event_name;
	private String event_id;
	private String event_url;
	private int month;
	private String local_date;
	private String local_time;
	private String country_code;
	private String state_code;
	private String city;
	private String state;
	private String country_name;
	private String segment;
	private String genre;
	
	/**
	 * constructor
	 *
	 */
	public Events(String event_name, String event_id, String event_url, String localDate, 
			      String local_time, String country_code, String state_code, String city, 
			      String state, String country_name, String genre, String subgenre, String segment) {
		this.event_name=event_name;
		this.event_id=event_id;
		this.event_url=event_url;
		this.local_date=localDate;
		this.local_time=local_time;
		this.country_code=country_code;
		this.state_code=state_code;
		this.city=city;
		this.state=state;
		this.country_name=country_name;
		this.genre=genre;
		this.subgenre=subgenre;
		this.segment=segment;
		String str[] = localDate.split("-");
		setMonth(Integer.parseInt(str[1]));
	}


	/**
	 * Getters and setters
	 * 
	 */
	
	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSubgenre() {
		return subgenre;
	}

	public void setSubgenre(String subgenre) {
		this.subgenre = subgenre;
	}

	private String subgenre;
	
	public String getName() {
		return event_name;
	}

	public void setName(String name) {
		this.event_name = name;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvent_url() {
		return event_url;
	}

	public void setEvent_url(String event_url) {
		this.event_url = event_url;
	}

	public String getLocal_date() {
		return local_date;
	}

	public void setLocal_date(String local_date) {
		this.local_date = local_date;
	}

	public String getLocal_time() {
		return local_time;
	}

	public void setLocal_time(String local_time) {
		this.local_time = local_time;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int i) {
		this.month = i;
	}
}
