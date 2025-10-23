package entities;

import java.time.LocalDateTime;

public class Event {
    private final String title;
    private final String description;
    private final User organizer;
    private final LocalDateTime dueDate;
    private final String category;

    private Event(EventBuilder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.organizer = builder.organizer;
        this.dueDate = builder.dueDate;
        this.category = builder.category;
    }

    public static class EventBuilder{
        private final String title;
        private final String description;
        private final User organizer;
        private final LocalDateTime dueDate;
        private String category = "generalno";

        public EventBuilder(String title, String description, User organizer, LocalDateTime dueDate) {
            this.title = title;
            this.description = description;
            this.organizer = organizer;
            this.dueDate = dueDate;
        }

        public EventBuilder category(String category) {
            this.category = category;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDateTime getDueDate() { return dueDate; }
    public String getCategory() { return category; }
    public User getOrganizer() { return organizer; }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", category='" + category + '\'' +
                ", organizer=" + organizer.getUsername() + '}';
    }
}
