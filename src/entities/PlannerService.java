package entities;

import java.time.LocalDateTime;

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

    public Event makeEvent(String title, String description, String location, LocalDateTime dueDate, User organizer) {
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

    public User findUser(String username) {
        for (int i = 0; i < userCounter; i++) {
            if (users[i].getUsername().equals(username)) {
                return users[i];
            }
        }
        return null;
    }

    public void printAllEvents() {
        System.out.println("\n=== POPIS SVIH DOGAĐAJA ===");
        if (eventCounter == 0) {
            System.out.println("Trenutno nema kreiranih događaja.");
            return;
        }

        for (int i = 0; i < eventCounter; i++) {
            Event e = events[i];
            System.out.println((i + 1) + ". " + e.toString() + " (Opis: " + e.getDescription() + ")");
        }
        System.out.println("====================================\n");
    }
}
