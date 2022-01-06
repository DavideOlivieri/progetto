package it.univpm.exam_project.filters;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

/**
 * @author DavideOlivieri
 *
 * Class that filters all events by Country
 */

public class CountryFilter {
	
	/**
	 * Method that filters all events by Country
	 * 
	 * @param countryCode, the countryCode for which to filter events
	 * @param eventstoFilter, all events to be filtered
	 * @return filteredStates, events filtered for the chosen Country
	 * @see it.univpm.exam_project.models.Events
	 */
 
	public Vector<Events> countryFilter(String countryCode, Vector<Events> eventstoFilter){

		Vector<Events> filteredStates = new Vector<Events>();

		for(Events event : eventstoFilter) {

			if(countryCode.equals(event.getCountry_code()))
				filteredStates.add(event);

		}
		return filteredStates;
	}

}
