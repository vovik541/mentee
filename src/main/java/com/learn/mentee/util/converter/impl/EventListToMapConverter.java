package com.learn.mentee.util.converter.impl;

import com.learn.mentee.entity.Event;
import com.learn.mentee.util.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EventListToMapConverter implements Converter<List<Event>, Map<Long, Event>> {

    @Override
    public Map<Long, Event> convert(List<Event> events) {
        return events.stream()
                .collect(Collectors
                        .toMap(Event::getId, x -> x));
    }
}
