package it.univpm.exam_project.models;


/**
 * Class that contains all the parameters chosen to describe the events
 * 
 * @author DavideOlivieri
 * @author JacopoColoccioni
 * 
 */

public class Events extends EventsUE{
	
    //  **************************************************************************************************
	//                                       PARAMETERS
	//   **************************************************************************************************
	private String state_code;           // State code where the event will take place
	private String state;                // name of the State where the event will take place

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
	public Events(String event_name, String event_id, String event_url, String localDate, 
			String local_time, String country_code, String state_code, String city, 
			String state, String country_name, String genre, String subgenre, String segment) {
		super(event_name, event_id, event_url, localDate, local_time,
				country_code, city, country_name, genre, subgenre, segment);
		this.state_code=state_code;
		this.state=state;
	}


    //  **************************************************************************************************
	//                                      GETTER AND SETTER
	//   **************************************************************************************************


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

}
