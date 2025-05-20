package com.ticketmanagement.service;

import com.ticketmanagement.enums.*;
import com.ticketmanagement.model.*;
import com.ticketmanagement.model.ticket.Epic;
import com.ticketmanagement.model.ticket.OnCall;
import com.ticketmanagement.model.ticket.Story;
import com.ticketmanagement.model.ticket.Ticket;

import java.util.*;

public class TicketManager {
    private final Map<String, Ticket> tickets = new HashMap<>();
    private final Map<String, SubTask> subTasks = new HashMap<>();
    private final Sprint sprint = new Sprint();
    private final StatusFlowValidator validator = new StatusFlowValidator();

    public Ticket createTicket(String id, String title, TicketType type) {
        Ticket ticket = switch (type) {
            case STORY -> new Story(id, title);
            case EPIC -> new Epic(id, title);
            case ON_CALL -> new OnCall(id, title);
        };
        tickets.put(id, ticket);
        return ticket;
    }

    public void updateTicketStatus(String id, Status newStatus) {
        Ticket t = tickets.get(id);
        if (t != null && validator.isValidTransition(t.getType(), t.getStatus(), newStatus)) {
            if (t instanceof Story && !t.allSubTasksCompleted())
                throw new IllegalStateException("Cannot close story with incomplete subtasks");
            t.updateStatus(newStatus);
        }
    }

    public void addToSprint(String ticketId) {
        Ticket t = tickets.get(ticketId);
        if (t instanceof Story s) sprint.addStory(s);
        else throw new IllegalArgumentException("Only stories allowed in sprint");
    }

    public void removeFromSprint(String ticketId) {
        sprint.removeStory(ticketId);
    }

    public List<String> getSprintStoryIds() {
        return new ArrayList<>(sprint.getStoryIds());
    }

    public void addSubTask(String ticketId, String subTaskId, String title) {
        Ticket t = tickets.get(ticketId);
        SubTask st = new SubTask(subTaskId, title);
        subTasks.put(subTaskId, st);
        t.addSubTask(st);
    }

    public void removeSubTask(String ticketId, String subTaskId) {
        Ticket t = tickets.get(ticketId);
        t.removeSubTask(subTaskId);
        subTasks.remove(subTaskId);
    }

    public void updateSubTaskStatus(String subTaskId, Status newStatus) {
        SubTask st = subTasks.get(subTaskId);
        if (st != null) st.updateStatus(newStatus);
    }

    public Ticket getTicket(String id) {
        return tickets.get(id);
    }
}
