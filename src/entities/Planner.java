package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Planner {
    private static final int MAX_ELEMENTS = 5;
    private User[] users;
    private int userCounter;

    public Planner() {
        this.users = new User[MAX_ELEMENTS];
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

    public User findUser(String username) {
        for (int i = 0; i < userCounter; i++) {
            if (users[i].getUsername().equals(username)) {
                return users[i];
            }
        }
        return null;
    }

    public void createUser(Scanner sc) {
        System.out.println("\n==== REGISTRACIJA ====");
        System.out.print("Korisničko ime: ");
        String username = sc.nextLine();
        System.out.print("Lozinka: ");
        String password = sc.nextLine();
        saveUser(username, password);
    }

    public User loginUser(Scanner sc) {
        System.out.println("\n==== PRIJAVA ====");
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
        System.out.println("\n==== KREIRANJE DOGAĐAJA (Organizator: " + currentUser.getUsername() + ") ====");
        System.out.print("Naslov događaja: ");
        String title = sc.nextLine();
        System.out.print("Opis događaja: ");
        String description = sc.nextLine();
        System.out.print("Datum i vrijeme (D.M.GGGG. HH:MM, npr. 4.5.2023. 16:45): ");
        String date = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy. HH:mm");
        LocalDateTime dueDate = LocalDateTime.parse(date, formatter);

        Event newEvent = new Event(title, description, dueDate, currentUser);
        if(currentUser.addEvent(newEvent)) {
            System.out.println("Dodan novi dogadaj: " + title);
        }
    }
}
