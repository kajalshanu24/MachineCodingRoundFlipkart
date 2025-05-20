package com.ticketmanagement.model;

import com.ticketmanagement.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubTask {
    private final String id;
    private final String title;
    private Status status;

    public SubTask(String id, String title) {
        this.id = id;
        this.title = title;
        this.status = Status.OPEN;
    }

    public void updateStatus(Status newStatus) {
        this.status = newStatus;
    }
}
