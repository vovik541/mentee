package com.learn.mentee.service.impl;

import com.learn.mentee.dao.TicketDao;
import com.learn.mentee.entity.Event;
import com.learn.mentee.entity.Ticket;
import com.learn.mentee.entity.User;
import com.learn.mentee.entity.enumaration.TicketCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {


    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketDao ticketDaoMock;

    @Spy
    private HashMap<Long, Ticket> ticketMapSpy;

    @Mock
    private Ticket ticketMock;

    @Mock
    private User userMock;

    @Mock
    private Event eventMock;

    private final long id = 1L;
    private final int place = 1;
    private final TicketCategory ticketCategory = TicketCategory.STANDARD;

    @Before
    public void setUp() {
        when(ticketDaoMock.getTicketMap()).thenReturn(ticketMapSpy);
        when(ticketMock.getId()).thenReturn(id);
        when(ticketMock.getEventId()).thenReturn(id);
        when(ticketMock.getUserId()).thenReturn(id);
        when(ticketMock.getTicketCategory()).thenReturn(ticketCategory);
        when(ticketMock.getPlace()).thenReturn(place);
        when(userMock.getId()).thenReturn(id);
        when(eventMock.getId()).thenReturn(id);
        ticketMapSpy.put(id, ticketMock);
    }

    @Test
    public void bookTicketTest() {
        assertNull(ticketService.bookTicket(id, id, place, ticketCategory));
    }

    @Test
    public void getBookedTicketsByUserWithInvalidInput() {
        assertTrue(ticketService.getBookedTicketsByUser(userMock, -1, -1).isEmpty());
    }

    @Test
    public void getBookedTicketsByUserWithValidInputTest() {
        assertFalse(ticketService.getBookedTicketsByUser(userMock, 1, 1).isEmpty());
    }

    @Test
    public void getBookedTicketsByEventWithInvalidInput() {
        assertTrue(ticketService.getBookedTicketsByEvent(eventMock, -1, -1).isEmpty());
    }

    @Test
    public void getBookedTicketsByEventWithValidInputTest() {
        assertFalse(ticketService.getBookedTicketsByEvent(eventMock, 1, 1).isEmpty());
    }

    @Test
    public void cancelTicketTest() {
        assertTrue(ticketService.cancelTicket(id));
    }
}