package entities;

import java.time.LocalDateTime;

public class Event {
    private final String title;
    private final String description;
    private final LocalDateTime dueDate;
    private final EventInfo info;

    private Event(EventBuilder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.dueDate = builder.dueDate;
        this.info = new EventInfo(builder.organizer, builder.category);
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
    public EventInfo getInfo() { return info; }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", category='" + info.category() + '\'' +
                ", organizer=" + info.organizer().getUsername() + '}';
    }
}
