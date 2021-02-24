package com.learn.mentee.entity;

import com.learn.mentee.entity.enumaration.TicketCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private long eventId;
    private long userId;
    private TicketCategory ticketCategory;
    private int place;
}
