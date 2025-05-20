package com.ticketmanagement.model.ticket;

import com.ticketmanagement.enums.TicketType;

public class OnCall extends Ticket{
    public OnCall(String id, String title) {
        super(id, title, TicketType.ON_CALL);
    }
}
