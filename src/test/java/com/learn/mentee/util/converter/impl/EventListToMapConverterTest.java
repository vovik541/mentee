package com.learn.mentee.util.converter.impl;

import com.learn.mentee.entity.Event;
import com.learn.mentee.util.converter.impl.EventListToMapConverter;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class EventListToMapConverterTest {

    @Test
    public void convertTest() {
        EventListToMapConverter eventListToMapConverter = new EventListToMapConverter();
        Event event = new Event();
        Map<Long, Event> expectedMap = new HashMap<>();
        expectedMap.put(event.getId(), event);
        Map<Long, Event> actualMap = eventListToMapConverter.convert(Collections.singletonList(event));
        assertEquals(expectedMap, actualMap);
    }
}