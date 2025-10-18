package entities;

import java.time.LocalDate;

public class Event {
    private String title;
    private String description;
    private String location;
    private LocalDate dueDate;
    private User organizer;

    public Event(String title, String description, String location, LocalDate dueDate, User organizer) {
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }
}
