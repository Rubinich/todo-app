package app;

import entities.Task;
import entities.User;
import entities.Event;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfUsers = 5;
        Scanner sc = new Scanner(System.in);
        User[] users = new User[numberOfUsers];

        System.out.println("--- Pokretanje unosa za " + numberOfUsers + " korisnika (1 Event & 1 Task po korisniku) ---");

        for (int i = 0; i < numberOfUsers; i++) {
            System.out.println("\n===== UNOS KORISNIKA #" + (i + 1) + " =====");

            // 1. Unos podataka za Korisnika
            System.out.print("Unesite korisničko ime: ");
            String username = sc.nextLine();
            System.out.print("Unesite lozinku: ");
            String password = sc.nextLine();

            // 2. Unos podataka za Zadatak (Task)
            System.out.println("\n--- Unos Zadatka ---");
            System.out.print("Unesite naslov zadatka: ");
            String taskTitle = sc.nextLine();
            System.out.print("Unesite opis zadatka: ");
            String taskDescription = sc.nextLine();

            // Koristimo današnji datum za rok (DueDate) radi jednostavnosti
            LocalDate dueDate = LocalDate.now().plusDays(7);

            // Kreiramo objekt Task
            Task singleTask = new Task(taskTitle, dueDate, false, taskDescription);


            // 3. Unos podataka za Događaj (Event)
            System.out.println("\n--- Unos Događaja ---");
            System.out.print("Unesite naslov događaja: ");
            String eventTitle = sc.nextLine();

            // Kreiramo objekt Event: Događaj sadrži točno 1 Task (u nizu)
            Event singleEvent = new Event(eventTitle, new Task[]{singleTask});

            // 4. Kreiramo objekt User: Korisnik sadrži točno 1 Event (u nizu)
            users[i] = new User(username, new Event[]{singleEvent}, password);

            System.out.println("Korisnik '" + username + "' uspješno kreiran.");
        }

        System.out.println("\n--- UNOS ZAVRŠEN ---");
        System.out.println("Ukupno je kreirano " + users.length + " korisnika.");

        // Primjer ispisa i provjere:
        for (User user : users) {
            Event event = user.getEvents()[0]; // Sigurno je 0 jer znamo da postoji točno 1
            Task task = event.getTasks()[0];   // Sigurno je 0 jer znamo da postoji točno 1

            System.out.println("\n[Detalji]");
            System.out.println("Korisnik: " + user.getUsername());
            System.out.println("Događaj: " + event.getTitle());
            System.out.println("Zadatak: " + task.getTitle() + " (Opis: " + task.getDescription() + ")");
        }

        sc.close();
    }
}
