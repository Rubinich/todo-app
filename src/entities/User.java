package entities;

public class User {
    private static final int MAX_EVENTS = 5;

    private String username;
    private String password;
    private Event[] events;
    private int eventCount;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.events = new Event[MAX_EVENTS];
        this.eventCount = 0;
    }

    public String getUsername() { return username; }

    public Event[] getEvents() { return events; }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    public boolean addEvent(Event newEvent) {
        if (eventCount >= MAX_EVENTS) {
            System.out.println("Korisnik " + username + " je dosegao maksimum dogadaja (5)!");
            return false;
        }
        events[eventCount++] = newEvent;
        return true;
    }

    public void printUserEvents() {
        System.out.println("\n==== Dogadaji korisnika " + username + " ====");
        if (eventCount == 0) {
            System.out.println("Nema dogadaja.");
            return;
        }
        for (int i = 0; i < eventCount; i++) {
            System.out.println((i + 1) + ". " + events[i].getTitle() + " " + events[i].getDueDate());
        }
    }
}
