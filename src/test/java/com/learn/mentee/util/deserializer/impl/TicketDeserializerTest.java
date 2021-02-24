package com.learn.mentee.util.deserializer.impl;

import com.learn.mentee.util.deserializer.impl.TicketDeserializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketDeserializerTest {

    @Spy
    private TicketDeserializer ticketDeserializer;

    @Test
    public void deserializeTest() {
        when(ticketDeserializer.getPath()).thenReturn("C:/Games/mentee/src/main/resources/entities/tickets");
        assertFalse(ticketDeserializer.deserialize().isEmpty());
    }
}