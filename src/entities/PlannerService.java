package entities;

import java.time.LocalDate;

public class PlannerService {
    private static final int MAX_ELEMENTS = 5;

    private Event[] events;
    private User[] users;

    private int eventCounter;
    private  int userCounter;

    public PlannerService() {
        this.events = new Event[MAX_ELEMENTS];
        this.users = new User[MAX_ELEMENTS];

        this.eventCounter = 0;
        this.userCounter = 0;
    }

    public User registerUser(String username, String password, String email) {
        if(MAX_ELEMENTS <= userCounter) {
            System.out.println("Nema mjesta za nove korisnike!");
            return null;
        }

        User newUser = new User(username, password, email);
        users[userCounter] = newUser;
        userCounter++;
        System.out.println("Registriran korisnik " + username + ".");
        return newUser;
    }

    public Event makeEvent(String title, String description, String location, LocalDate dueDate, User organizer) {
        if(MAX_ELEMENTS <= eventCounter) {
            System.out.println("Nema mjesta za nove dogadaje!");
            return null;
        }

        Event newEvent = new Event(title, description, location, dueDate, organizer);
        events[eventCounter] = newEvent;
        eventCounter++;
        System.out.println("Dodan novi dogadaj " + title + ".");
        return newEvent;
    }
}
