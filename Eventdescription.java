import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class EventDescription {
    // Inner Event class to encapsulate event details
    public static class Event {
        private String id;
        private String name;
        private LocalDateTime dateTime;
        private String description;

        public Event(String id, String name, LocalDateTime dateTime, String description) {
            this.id = id;
            this.name = name;
            this.dateTime = dateTime;
            this.description = description;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public LocalDateTime getDateTime() { return dateTime; }
        public String getDescription() { return description; }

        public void setName(String name) { this.name = name; }
        public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
        public void setDescription(String description) { this.description = description; }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", DateTime: " + 
                   dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + 
                   ", Description: " + description;
        }
    }

    private HashMap<String, Event> eventMap;

    public EventDescription() {
        eventMap = new HashMap<>();
    }

    // Add a new event
    public boolean addEvent(String id, String name, LocalDateTime dateTime, String description) {
        if (eventMap.containsKey(id)) {
            return false; // ID already exists
        }
        Event event = new Event(id, name, dateTime, description);
        eventMap.put(id, event);
        return true;
    }

    // Update an existing event
    public boolean updateEvent(String id, String name, LocalDateTime dateTime, String description) {
        Event event = eventMap.get(id);
        if (event == null) {
            return false; // Event not found
        }
        event.setName(name);
        event.setDateTime(dateTime);
        event.setDescription(description);
        return true;
    }

    // Delete an event
    public boolean deleteEvent(String id) {
        return eventMap.remove(id) != null;
    }

    // Search events by name
    public void searchEventByName(String name) {
        boolean found = false;
        for (Event event : eventMap.values()) {
            if (event.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No events found with name containing: " + name);
        }
    }

    // Get event by ID (for EventReminder module)
    public Event getEvent(String id) {
        return eventMap.get(id);
    }
}