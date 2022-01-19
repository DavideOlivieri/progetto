package it.univpm.exam_project.models;

public class EventsUE {
	
    //  **************************************************************************************************
	//                                       PARAMETERS
	//   **************************************************************************************************
	private String event_name;           // name of the event
	private String event_id;             // event identification number
	private String event_url;            // event URL
	private int month;                   // month in which the event will take place
	private String local_date;           // date on which the event will take place
	private String local_time;           // exact time of the start of the event
	private String country_code;         // Country code where the event will take place
	private String city;                 // name of the City where the event will take place
	private String country_name;         // name of the Country where the event will take place
	private String segment;              // segment of the event (Grouping of genres)
	private String genre;                // genre of the event

    //  **************************************************************************************************
	//                                        CONSTRUCTOR
	//   **************************************************************************************************
	/**
	 * 
	 * @param event_name
	 * @param event_id
	 * @param event_url
	 * @param localDate
	 * @param local_time
	 * @param country_code
	 * @param state_code
	 * @param city
	 * @param state
	 * @param country_name
	 * @param genre
	 * @param subgenre
	 * @param segment
	 */
	public EventsUE(String event_name, String event_id, String event_url, String localDate, 
			String local_time, String country_code, String city, String country_name, String genre, String subgenre, String segment) {
		this.event_name=event_name;
		this.event_id=event_id;
		this.event_url=event_url;
		this.local_date=localDate;
		this.local_time=local_time;
		this.country_code=country_code;
		this.city=city;
		this.country_name=country_name;
		this.genre=genre;
		this.subgenre=subgenre;
		this.segment=segment;
		String str[] = localDate.split("-");
		setMonth(Integer.parseInt(str[1]));
	}


    //  **************************************************************************************************
	//                                      GETTER AND SETTER
	//   **************************************************************************************************

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


	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int i) {
		this.month = i;
	}
}

