package com.learn.mentee.service.impl;

import com.learn.mentee.dao.EventDao;
import com.learn.mentee.entity.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventDao eventDaoMock;

    @Spy
    private HashMap<Long, Event> eventMapSpy;

    @Mock
    private Event eventMock;

    @Mock
    private Date dateMock;

    @Before
    public void setUp() {
        when(eventDaoMock.getEventMap()).thenReturn(eventMapSpy);
        when(eventMapSpy.get(anyLong())).thenReturn(eventMock);
        when(eventMapSpy.values()).thenReturn(Collections.singletonList(eventMock));
    }

    @Test
    public void getEventByIdTest() {
        assertNotNull(eventService.getEventById(1));
    }

    @Test
    public void getEventByTitleTest() {
        when(eventMock.getTitle()).thenReturn("");
        assertFalse(eventService.getEventByTitle("", 1, 1).isEmpty());
        assertTrue(eventService.getEventByTitle("t", -1, 1).isEmpty());
    }

    @Test
    public void getEventsForDayWithIncorrectInputTest() {
        assertTrue(eventService.getEventsForDay(dateMock, -1, -2).isEmpty());
    }

    @Test
    public void getEventsForDayTest() {
        when(eventMock.getDate()).thenReturn(dateMock);
        assertFalse(eventService.getEventsForDay(dateMock, 1, 1).isEmpty());
    }

    @Test
    public void createEventTest() {
        assertNull(eventService.createEvent(eventMock));
    }

    @Test
    public void updateNonExistentEventTest() {
        assertEquals(eventMock, eventService.updateEvent(new Event()));
    }

    @Test
    public void updateExistentEventTest() {
        assertEquals(eventMock, eventService.updateEvent(eventMock));
    }

    @Test
    public void deleteExistentEventTest() {
        eventMapSpy.put(1L, new Event());
        assertTrue(eventService.deleteEvent(1L));
    }

    @Test
    public void deleteNonExistentEventTest() {
        assertFalse(eventService.deleteEvent(1L));
    }
}