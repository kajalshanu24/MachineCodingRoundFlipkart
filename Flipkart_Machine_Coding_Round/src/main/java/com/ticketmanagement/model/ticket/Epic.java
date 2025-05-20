package com.ticketmanagement.model.ticket;

import com.ticketmanagement.enums.TicketType;

public class Epic extends Ticket{
    public Epic(String id, String title) {
        super(id, title, TicketType.EPIC);
    }
}
