package com.learn.mentee.dao;

import com.learn.mentee.entity.Event;
import com.learn.mentee.util.dataparser.DateParser;
import com.learn.mentee.util.deserializer.impl.EventDeserializer;
import com.learn.mentee.util.converter.impl.EventListToMapConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EventDao {

    @Getter
    private Map<Long, Event> eventMap;

    @Resource
    private EventListToMapConverter converter;

    @Resource
    private EventDeserializer deserializer;

    @Resource
    private DateParser dateParser;

    @PostConstruct
    private void init() {
        eventMap = converter.convert(deserializer.deserialize());

        for (long i = 4; i <= 15; i++) {
            Event e = new Event();
            e.setId(i);
            e.setTitle("Original Title");
            e.setDate(dateParser.parseDate("2021-02-07"));
            eventMap.put(i, e);
        }
        for (long i = 16; i <= 32; i++) {
            Event e = new Event();
            e.setId(i);
            e.setTitle("Not Original Title");
            e.setDate(dateParser.parseDate("2021-02-07"));
            eventMap.put(i, e);
        }
    }
}
