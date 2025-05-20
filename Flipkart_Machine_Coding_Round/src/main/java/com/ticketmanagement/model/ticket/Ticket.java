package com.ticketmanagement.model.ticket;

import com.ticketmanagement.enums.Status;
import com.ticketmanagement.enums.TicketType;
import com.ticketmanagement.model.SubTask;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class Ticket {

    protected final String id;
    protected final String title;
    protected Status status;
    protected final List<SubTask> subTasks;
    protected final TicketType type;

    protected Ticket(String id, String title, TicketType type) {
        this.id = id;
        this.title = title;
        this.status = Status.OPEN;
        this.type = type;
        this.subTasks = new ArrayList<>();
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
    }

    public void removeSubTask(String subTaskId) {
        subTasks.removeIf(t -> t.getId().equals(subTaskId));
    }

    public boolean allSubTasksCompleted() {
        return subTasks.stream().allMatch(t -> t.getStatus() == this.status);
    }

    public void updateStatus(Status newStatus) {
        this.status = newStatus;
        subTasks.forEach(st -> st.updateStatus(newStatus));
    }
}
