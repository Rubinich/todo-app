package entities;

public class User {
    private String username;
    private String password;
    //private Event[] events;

    public User(String username, Event[] events, String password) {
        this.username = username;
        this.events = events;
        this.password = password;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
