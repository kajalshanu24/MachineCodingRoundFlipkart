package com.ticketmanagement.service;

import com.ticketmanagement.enums.Status;
import com.ticketmanagement.enums.TicketType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusFlowValidator {

    private final Map<TicketType, Map<Status, List<Status>>> flowMap = new HashMap<>();

    public StatusFlowValidator() {
        Map<Status, List<Status>> storyFlow = new HashMap<>();
        storyFlow.put(Status.OPEN, List.of(Status.IN_PROGRESS));
        storyFlow.put(Status.IN_PROGRESS, List.of(Status.TESTING));
        storyFlow.put(Status.TESTING, List.of(Status.IN_REVIEW));
        storyFlow.put(Status.IN_REVIEW, List.of(Status.DEPLOYED));
        storyFlow.put(Status.DEPLOYED, List.of());

        Map<Status, List<Status>> epicFlow = new HashMap<>();
        epicFlow.put(Status.OPEN, List.of(Status.IN_PROGRESS));
        epicFlow.put(Status.IN_PROGRESS, List.of(Status.COMPLETED));
        epicFlow.put(Status.COMPLETED, List.of());

        Map<Status, List<Status>> onCallFlow = new HashMap<>();
        onCallFlow.put(Status.OPEN, List.of(Status.IN_PROGRESS));
        onCallFlow.put(Status.IN_PROGRESS, List.of(Status.RESOLVED));
        onCallFlow.put(Status.RESOLVED, List.of());

        flowMap.put(TicketType.STORY, storyFlow);
        flowMap.put(TicketType.EPIC, epicFlow);
        flowMap.put(TicketType.ON_CALL, onCallFlow);
    }

    public boolean isValidTransition(TicketType type, Status current, Status next) {
        Map<Status, List<Status>> allowed = flowMap.get(type);
        return allowed.containsKey(current) && allowed.get(current).contains(next);
    }
}
