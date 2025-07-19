import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainExecute {
    public static void main(String[] args) {
        EventDescription eventDesc = new EventDescription();
        EventReminder eventReminder = new EventReminder();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (true) {
            System.out.println("\nEvent Reminder System");
            System.out.println("1. Add Event");
            System.out.println("2. View All Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Check Reminders");
            System.out.println("6. Search Event by Name");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Event ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Event Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Event DateTime (yyyy-MM-dd HH:mm): ");
                    String dateTimeStr = scanner.nextLine();
                    System.out.print("Enter Event Description: ");
                    String description = scanner.nextLine();
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
                        boolean added = eventDesc.addEvent(id, name, dateTime, description);
                        if (added) {
                            eventReminder.addEvent(eventDesc.getEvent(id));
                            System.out.println("Event added successfully.");
                        } else {
                            System.out.println("Event ID already exists!");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Use yyyy-MM-dd HH:mm");
                    }
                    break;

                case 2:
                    eventReminder.viewEvents();
                    break;

                case 3:
                    System.out.print("Enter Event ID to update: ");
                    id = scanner.nextLine();
                    System.out.print("Enter New Event Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter New Event DateTime (yyyy-MM-dd HH:mm): ");
                    dateTimeStr = scanner.nextLine();
                    System.out.print("Enter New Event Description: ");
                    description = scanner.nextLine();
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
                        EventDescription.Event event = eventDesc.getEvent(id);
                        if (event != null) {
                            eventReminder.removeEvent(event); // Remove from queue
                            boolean updated = eventDesc.updateEvent(id, name, dateTime, description);
                            if (updated) {
                                eventReminder.addEvent(event); // Re-add to queue
                                System.out.println("Event updated successfully.");
                            } else {
                                System.out.println("Event not found.");
                            }
                        } else {
                            System.out.println("Event not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Use yyyy-MM-dd HH:mm");
                    }
                    break;

                case 4:
                    System.out.print("Enter Event ID to delete: ");
                    id = scanner.nextLine();
                    EventDescription.Event event = eventDesc.getEvent(id);
                    if (event != null) {
                        eventReminder.removeEvent(event);
                        boolean deleted = eventDesc.deleteEvent(id);
                        if (deleted) {
                            System.out.println("Event deleted successfully.");
                        } else {
                            System.out.println("Event not found.");
                        }
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;

                case 5:
                    eventReminder.checkReminders();
                    break;

                case 6:
                    System.out.print("Enter Event Name to search: ");
                    name = scanner.nextLine();
                    eventDesc.searchEventByName(name);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}