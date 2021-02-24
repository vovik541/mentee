package com.learn.mentee.dao;

import com.learn.mentee.entity.Ticket;
import com.learn.mentee.entity.enumaration.TicketCategory;
import com.learn.mentee.util.converter.impl.TicketListToMapConverter;
import com.learn.mentee.util.deserializer.impl.TicketDeserializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TicketDao {

    @Getter
    private Map<Long, Ticket> ticketMap;

    @Resource
    private TicketListToMapConverter converter;

    @Resource
    private TicketDeserializer deserializer;

    @PostConstruct
    private void init() {
        ticketMap = converter.convert(deserializer.deserialize());
        for (long i = 4; i <= 15; i++) {

            Ticket ticket = new Ticket();
            ticket.setId(i);
            if (i < 7) {
                ticket.setEventId(1);
                ticket.setUserId(1);
                ticket.setTicketCategory(TicketCategory.STANDARD);
            } else if (i < 10) {
                ticket.setEventId(2);
                ticket.setUserId(2);
                ticket.setTicketCategory(TicketCategory.BAR);
            } else {
                ticket.setEventId(3);
                ticket.setUserId(3);
                ticket.setTicketCategory(TicketCategory.PREMIUM);
            }
            ticket.setPlace((int) i);
            ticketMap.put(ticket.getId(), ticket);
        }
        for (long i = 16; i < 200; i++) {
            Ticket ticket = new Ticket();
            ticket.setId(i);
            ticket.setUserId(1L);
            ticket.setPlace((int)i);
            ticket.setEventId(1L);
            ticket.setTicketCategory(TicketCategory.STANDARD);
            ticketMap.put(ticket.getId(), ticket);
        }
    }
}
