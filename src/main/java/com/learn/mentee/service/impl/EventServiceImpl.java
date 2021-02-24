package com.learn.mentee.service.impl;

import com.learn.mentee.dao.EventDao;
import com.learn.mentee.entity.Event;
import com.learn.mentee.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Resource
    private EventDao eventDao;

    @Override
    public Event getEventById(long eventId) {
        return eventDao.getEventMap().get(eventId);
    }

    @Override
    public List<Event> getEventByTitle(String title, int pageSize, int pageNum) {
        if (pageSize < 1 || pageNum < 1) {
            return Collections.emptyList();
        }

        int from = pageSize * (pageNum - 1);
        int to = pageNum * pageSize;

        List<Event> events = eventDao.getEventMap().values().stream()
                .filter(e -> e.getTitle().equals(title))
                .collect(Collectors.toList());

        return events.subList(from, Math.min(to, events.size()));
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        if (pageSize < 1 || pageNum < 1) {
            return Collections.emptyList();
        }

        int from = pageSize * (pageNum - 1);
        int to = pageNum * pageSize;

        List<Event> eventsForDay = eventDao.getEventMap().values().stream()
                .filter(e -> e.getDate().equals(day))
                .collect(Collectors.toList());

        return eventsForDay.subList(from, Math.min(to, eventsForDay.size()));
    }

    @Override
    public Event createEvent(Event event) {
        eventDao.getEventMap().keySet().stream()
                .max(Long::compareTo)
                .ifPresent(x -> event.setId(x + 1));
        return eventDao.getEventMap().putIfAbsent(event.getId(), event);
    }

    @Override
    public Event updateEvent(Event event) {
        return Optional.ofNullable(eventDao.getEventMap()
                .computeIfPresent(event.getId(), (k, v) -> {
                    v.setDate(event.getDate());
                    v.setTitle(event.getTitle());
                    return v;
                }))
                .orElse(eventDao.getEventMap().get(event.getId()));
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventDao.getEventMap().remove(eventId) != null;
    }
}