package com.learn.mentee.util.converter.impl;

import com.learn.mentee.entity.Ticket;
import com.learn.mentee.util.converter.impl.TicketListToMapConverter;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TicketListToMapConverterTest {

    @Test
    public void convertTest() {
        TicketListToMapConverter ticketListToMapConverter = new TicketListToMapConverter();
        Ticket ticket = new Ticket();
        Map<Long, Ticket> expectedMap = new HashMap<>();
        expectedMap.put(ticket.getId(), ticket);
        Map<Long, Ticket> actualMap = ticketListToMapConverter.convert(Collections.singletonList(ticket));
        assertEquals(expectedMap, actualMap);
    }
}