package it.univpm.exam_project.filters;

import java.util.Vector;

import it.univpm.exam_project.models.Events;

public class GenreFilter {

	public static Vector<Events> genFilter(String genre, Vector<Events> eventstoFilter){

		Vector<Events> filteredEvents = new Vector<Events>();

		for(Events event : eventstoFilter) {

			if(genre.equals(event.getGenre()))
				filteredEvents.add(event);

		}
		return filteredEvents;
	}
}
