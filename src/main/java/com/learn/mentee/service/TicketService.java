package com.learn.mentee.service;

import com.learn.mentee.entity.Event;
import com.learn.mentee.entity.Ticket;
import com.learn.mentee.entity.User;
import com.learn.mentee.entity.enumaration.TicketCategory;

import java.util.List;

public interface TicketService {

    /**
     * Book ticket for a specified event on behalf of specified user.
     * @param userId User Id.
     * @param eventId Event Id.
     * @param place Place number.
     * @param ticketCategory Ticket category category.
     * @return Booked ticket object.
     */
    Ticket bookTicket(long userId, long eventId, int place, TicketCategory ticketCategory);

    /**
     * Get all booked tickets for specified user.
     * @param user User
     * @param pageSize Number of tickets to return on a page.
     * @param pageNum Number of the page to return.
     * @return List of Ticket objects or empty list if nothing was found.
     */
    List<Ticket> getBookedTicketsByUser(User user, int pageSize, int pageNum);

    /**
     * Get all booked tickets for specified event.
     * @param event Event
     * @param pageSize Number of tickets to return on a page.
     * @param pageNum Number of the page to return.
     * @return List of Ticket objects or empty list if nothing was found.
     */
    List<Ticket> getBookedTicketsByEvent(Event event, int pageSize, int pageNum);

    boolean cancelTicket(long ticketId);
}
