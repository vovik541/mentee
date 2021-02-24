package com.learn.mentee.service;

import com.learn.mentee.entity.Event;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface EventService {

    /**
     * Gets event by its id.
     * @return Event or null if there is no event with selected id.
     */
    Event getEventById(long eventId);

    /**
     * Get list of events by matching title.
     * @param title Event title.
     * @param pageSize Number of events to return on a page.
     * @param pageNum Number of the page to return.
     * @return List of events or empty list if nothing was found.
     */
    List<Event> getEventByTitle(String title, int pageSize, int pageNum);

    /**
     * Get list of events for specified day.
     * @param day Date object from which day information is extracted.
     * @param pageSize Pagination param. Number of events to return on a page.
     * @param pageNum Pagination param. Number of the page to return. Starts from 1.
     * @return List of events or empty list if nothing was found.
     */
    List<Event> getEventsForDay(Date day, int pageSize, int pageNum);

    /**
     * Creates new event.
     * @param event Event data.
     * @return null if event was created.
     */
    Event createEvent(Event event);

    /**
     * Updates event using given data.
     * @param event Event data for update.
     * @return Updated Event object or null if there was no event found.
     */
    Event updateEvent(Event event);

    /**
     * Deletes event by its id.
     * @param eventId Event id.
     * @return Flag that shows whether event has been deleted.
     */
    boolean deleteEvent(long eventId);
}
