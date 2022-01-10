package it.univpm.exam_project.filters;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

/**
 * @author DavideOlivieri
 *
 * Class that filters all events by Country
 */

public class StatesFilter {

	/**
	 * Method that filters all events by State
	 * 
	 * @param stateCode, the countryCode for which to filter events
	 * @param eventstoFilter, all events to be filtered
	 * @return filteredStates, events filtered for the chosen State
	 * @see it.univpm.exam_project.models.Events
	 */
 
	public Vector<Events> stateFilter(String stateCode, Vector<Events> eventstoFilter){

		Vector<Events> filteredStates = new Vector<Events>();

		for(Events event : eventstoFilter) {

			if(stateCode.equals(event.getState_code()))
				filteredStates.add(event);

		}
		return filteredStates;
	}

	public Vector<Events> stateFilter2(String stateCode, String stateCode2, Vector<Events> eventstoFilter){

		Vector<Events> filteredStates = new Vector<Events>();
 
		for(Events event : eventstoFilter) {

			if(stateCode.equals(event.getState_code()) || stateCode2.equals(event.getState_code()) )
				filteredStates.add(event);

		}
		return filteredStates;
	}

}
