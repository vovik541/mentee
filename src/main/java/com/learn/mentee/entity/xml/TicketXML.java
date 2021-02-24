package com.learn.mentee.entity.xml;

import com.learn.mentee.entity.enumaration.TicketCategory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class TicketXML {

    @XmlElement
    public long id;
    @XmlElement
    public long eventId;
    @XmlElement
    public long userId;
    @XmlElement
    public TicketCategory ticketCategory;
    @XmlElement
    public int place;
}
