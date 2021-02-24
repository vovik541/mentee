package com.learn.mentee.service.impl;

import com.learn.mentee.dao.TicketDao;
import com.learn.mentee.entity.Event;
import com.learn.mentee.entity.Ticket;
import com.learn.mentee.entity.User;
import com.learn.mentee.entity.enumaration.TicketCategory;
import com.learn.mentee.service.TicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Resource
    private TicketDao ticketDao;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, TicketCategory ticketCategory) {
        Ticket ticket = new Ticket();
        ticketDao.getTicketMap().keySet()
                .stream()
                .max(Long::compareTo)
                .ifPresent(x -> ticket.setId(x + 1));
        ticket.setUserId(userId);
        ticket.setEventId(eventId);
        ticket.setPlace(place);
        ticket.setTicketCategory(ticketCategory);
        return ticketDao.getTicketMap().putIfAbsent(ticket.getId(), ticket);
    }

    @Override
    public List<Ticket> getBookedTicketsByUser(User user, int pageSize, int pageNum) {
        if (pageSize < 1 || pageNum < 1) {
            return Collections.emptyList();
        }

        int from = pageSize * (pageNum - 1);
        int to = pageNum * pageSize;

        List<Ticket> ticketsByUser = ticketDao.getTicketMap().values().stream()
                .filter(t -> t.getUserId() == user.getId())
                .collect(Collectors.toList());

        return ticketsByUser.subList(from, Math.min(to, ticketsByUser.size()));
    }

    @Override
    public List<Ticket> getBookedTicketsByEvent(Event event, int pageSize, int pageNum) {
        if (pageSize < 1 || pageNum < 1) {
            return Collections.emptyList();
        }

        int from = pageSize * (pageNum - 1);
        int to = pageNum * pageSize;

        List<Ticket> ticketsByEvent = ticketDao.getTicketMap().values().stream()
                .filter(t -> t.getEventId() == event.getId())
                .collect(Collectors.toList());
        int ticketsByEventSize = ticketsByEvent.size();
        return from > ticketsByEventSize
                ? Collections.emptyList()
                : ticketsByEvent.subList(from, Math.min(to, ticketsByEventSize));
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketDao.getTicketMap().remove(ticketId) != null;
    }
}
