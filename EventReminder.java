import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.Comparator;

public class EventReminder {
    private PriorityQueue<EventDescription.Event> eventQueue;

    public EventReminder() {
        // Initialize PriorityQueue with custom comparator for sorting by date/time
        eventQueue = new PriorityQueue<>(Comparator.comparing(EventDescription.Event::getDateTime));
    }

    // Add event to the priority queue
    public void addEvent(EventDescription.Event event) {
        eventQueue.add(event);
    }

    // Remove event from the priority queue
    public void removeEvent(EventDescription.Event event) {
        eventQueue.remove(event); // O(n) operation
    }

    // View all events in chronological order
    public void viewEvents() {
        if (eventQueue.isEmpty()) {
            System.out.println("No events found.");
            return;
        }
        System.out.println("All Events (sorted by date/time):");
        for (EventDescription.Event event : eventQueue) {
            System.out.println(event);
        }
    }

    // Check for upcoming events (within 24 hours)
    public void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.plusHours(24);
        System.out.println("Upcoming Events (within 24 hours):");
        boolean found = false;
        for (EventDescription.Event event : eventQueue) {
            if (event.getDateTime().isBefore(threshold) && event.getDateTime().isAfter(now)) {
                System.out.println(event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No upcoming events in the next 24 hours.");
        }
    }
}