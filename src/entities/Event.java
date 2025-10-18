package entities;

import java.time.LocalDateTime;

public class Event {
    private String title;
    private String description;
    private String location;
    private LocalDateTime dueDate;
    private User organizer;

    public Event(String title, String description, String location, LocalDateTime dueDate, User organizer) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.dueDate = dueDate;
        this.organizer = organizer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }
}
