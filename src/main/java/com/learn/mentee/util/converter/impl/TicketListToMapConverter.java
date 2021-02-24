package com.learn.mentee.util.converter.impl;

import com.learn.mentee.entity.Ticket;
import com.learn.mentee.util.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TicketListToMapConverter implements Converter<List<Ticket>, Map<Long, Ticket>> {

    @Override
    public Map<Long, Ticket> convert(List<Ticket> tickets) {
        return tickets.stream()
                .collect(Collectors
                        .toMap(Ticket::getId, x -> x));
    }
}
