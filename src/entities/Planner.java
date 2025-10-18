package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Planner {
    private final int MAX_ELEMENTS = 5;
    private Event[] events;
    private User[] users;
    private int eventCounter;
    private int userCounter;

    public Planner() {
        this.events = new Event[MAX_ELEMENTS];
        this.users = new User[MAX_ELEMENTS];
        this.eventCounter = 0;
        this.userCounter = 0;
    }

    public void saveUser(String username, String password) {
        if(MAX_ELEMENTS <= userCounter) {
            System.out.println("Nema mjesta za nove korisnike!");
            return;
        }
        User newUser = new User(username, password);
        users[userCounter] = newUser;
        userCounter++;
        System.out.println("Registriran korisnik " + username + ".");
    }

    public void saveEvent(String title, String description, LocalDateTime dueDate, User organizer) {
        if(MAX_ELEMENTS <= eventCounter) {
            System.out.println("Nema mjesta za nove dogadaje!");
            return;
        }
        Event newEvent = new Event(title, description, dueDate, organizer);
        events[eventCounter] = newEvent;
        eventCounter++;
        System.out.println("Dodan novi dogadaj " + title + ".");
    }

    public User findUser(String username) {
        for (int i = 0; i < userCounter; i++) {
            if (users[i].getUsername().equals(username)) {
                return users[i];
            }
        }
        return null;
    }

    //potrebna dorada
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

    public void createUser(Scanner sc) {
        System.out.println("\n=== REGISTRACIJA ===");
        System.out.print("Korisničko ime: ");
        String username = sc.nextLine();
        System.out.print("Lozinka: ");
        String password = sc.nextLine();
        saveUser(username, password);
    }

    public User loginUser(Scanner sc) {
        System.out.println("\n=== PRIJAVA ===");
        System.out.print("Unesite korisničko ime: ");
        String username = sc.nextLine();
        System.out.print("Unesite lozinku: ");
        String password = sc.nextLine();
        User foundUser = findUser(username);

        if (foundUser != null && foundUser.checkPassword(password)) {
            System.out.println("Uspješno prijavljeni kao " + foundUser.getUsername() + "!");
            return foundUser;
        } else {
            System.out.println("Prijava neuspješna: Pogrešno korisničko ime ili lozinka.");
            return null;
        }
    }

    public void createEvent(Scanner sc, User currentUser) {
        System.out.println("\n=== KREIRANJE DOGAĐAJA (Organizator: " + currentUser.getUsername() + ") ===");
        System.out.print("Naslov događaja: ");
        String title = sc.nextLine();
        System.out.print("Opis događaja: ");
        String description = sc.nextLine();
        System.out.print("Datum i vrijeme (D.M.GGGG. HH:MM, npr. 4.5.2023. 16:45): ");
        String date = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy. HH:mm");
        LocalDateTime dueDate = LocalDateTime.parse(date, formatter);
        saveEvent(title, description, dueDate, currentUser);
    }

}
