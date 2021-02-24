package com.learn.mentee.util.converter.impl;

import com.learn.mentee.entity.Ticket;
import com.learn.mentee.entity.xml.TicketXML;
import com.learn.mentee.util.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TicketXMLToTicketConverter implements Converter<TicketXML, Ticket> {

    @Override
    public Ticket convert(TicketXML ticketXML) {
        return new Ticket(ticketXML.id, ticketXML.eventId, ticketXML.userId, ticketXML.ticketCategory, ticketXML.place);
    }
}
