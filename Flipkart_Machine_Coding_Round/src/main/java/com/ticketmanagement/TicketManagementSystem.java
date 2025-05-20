package com.ticketmanagement;

import com.ticketmanagement.enums.Status;
import com.ticketmanagement.enums.TicketType;
import com.ticketmanagement.service.TicketManager;

import java.util.Scanner;

public class TicketManagementSystem {

    public static void main(String[] args) {
        TicketManager manager = new TicketManager();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Ticket System!");

        while (true) {
            System.out.println("""
                1. Create Ticket
                2. Add Subtask
                3. Update Ticket Status
                4. Update Subtask Status
                5. Add to Sprint
                6. Remove from Sprint
                7. View Sprint
                8. Exit
                """);

            int choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Ticket ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Type (STORY/EPIC/ON_CALL): ");
                        TicketType type = TicketType.valueOf(sc.nextLine().toUpperCase());
                        manager.createTicket(id, title, type);
                        System.out.println("Created Ticket: " + id + " with title: " + title);
                    }
                    case 2 -> {
                        System.out.print("Enter Ticket ID: ");
                        String ticketId = sc.nextLine();
                        System.out.print("Enter SubTask ID: ");
                        String subTaskId = sc.nextLine();
                        System.out.print("Enter SubTask Title: ");
                        String title = sc.nextLine();
                        manager.addSubTask(ticketId, subTaskId, title);
                        System.out.println("SubTask Added Successfully!!");
                    }
                    case 3 -> {
                        System.out.print("Enter Ticket ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter New Status: ");
                        Status status = Status.valueOf(sc.nextLine().toUpperCase());
                        boolean isUpdateTicketStatus = manager.updateTicketStatus(id, status);
                        String updatedTicketStatus = isUpdateTicketStatus ? "Status updated to " + status : "Please provide valid status to be updated.";
                        System.out.println(updatedTicketStatus);
                    }
                    case 4 -> {
                        System.out.print("Enter SubTask ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter New Status: ");
                        Status status = Status.valueOf(sc.nextLine().toUpperCase());
                        String updatedTicketStatus = manager.updateSubTaskStatus(id, status) ? "Status updated to " + status : "Please provide valid status to be updated.";
                        System.out.println(updatedTicketStatus);
                    }
                    case 5 -> {
                        System.out.print("Enter Story Ticket ID: ");
                        String id = sc.nextLine();
                        manager.addToSprint(id);
                        System.out.println("Story added to sprint successfully!!");
                    }
                    case 6 -> {
                        System.out.print("Enter Story Ticket ID to remove: ");
                        String id = sc.nextLine();
                        String removedFromSprint = manager.removeFromSprint(id) ? "Removed from sprint successfully!!" : "Please provide valid ticket ID to remove.";
                        System.out.println(removedFromSprint);
                    }
                    case 7 -> {
                        System.out.println("Sprint Stories: " + manager.getSprintStoryIds());
                    }
                    case 8 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid Option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}