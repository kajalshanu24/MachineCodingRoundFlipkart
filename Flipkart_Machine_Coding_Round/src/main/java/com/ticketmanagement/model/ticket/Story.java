package com.ticketmanagement.model.ticket;

import com.ticketmanagement.enums.TicketType;

public class Story extends Ticket {
    public Story(String id, String title) {
        super(id, title, TicketType.STORY);
    }
}
