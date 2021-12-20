package it.univpm.exam_project.filters;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

/**
 * @author DavideOlivieri
 * 
 * Class that filters all events by genre
 */

public class GenreFilter {

	/**
	 * Method that filters all events by genre
	 * 
	 * @param genre, the genre for which to filter events
	 * @param eventstoFilter, all events to be filtered
	 * @return filteredEvents, events filtered for the chosen genre
	 * @see it.univpm.exam_project.models.Events
	 */
	public Vector<Events> genFilter(String genre, Vector<Events> eventstoFilter){

		Vector<Events> filteredEvents = new Vector<Events>();

		for(Events event : eventstoFilter) {

			if(genre.equals(event.getSegment()))
				filteredEvents.add(event);

		}
		return filteredEvents;
	}
}
