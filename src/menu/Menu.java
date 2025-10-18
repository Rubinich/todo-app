package menu;

import entities.Planner;
import entities.User;
import java.util.Scanner;

public class Menu {
    private static User currentUser = null;

    private Menu() {}

    public static void runMenu(Planner service, Scanner sc) {
        boolean running = true;
        System.out.println("#### APLIKACIJA ZA PLANIRANJE DOGAĐAJA ####");

        while (running) {
            printMenu();
            int menuChoice = getUserChoice(sc);
            switch (menuChoice) {
                case 1 -> service.createUser(sc);
                case 2 -> currentUser = service.loginUser(sc);
                case 3 -> handleCreateEvent(service, sc);
                case 4 -> {
                    if (currentUser != null) {
                        currentUser.printUserEvents();
                    } else {
                        System.out.println("Greška: Niste prijavljeni! Prijavite se da biste vidjeli svoje događaje.");
                    }
                }

                case 5 -> handleLogout();
                case 0 -> {
                    System.out.println("Izlaz iz aplikacije. Doviđenja!");
                    sc.close();
                    running = false;
                }
                default -> System.out.println("Nepostojeća opcija. Pokušajte ponovno.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n==== GLAVNI IZBORNIK ====");
        String status = (currentUser != null)
                ? ("Prijavljeni kao: " + currentUser.getUsername())
                : ("Niste prijavljeni.");
        System.out.println("Status: " + status);
        System.out.println("1. Registriraj novog korisnika (Max 5)");
        System.out.println("2. Prijava korisnika");
        System.out.println("3. Kreiraj novi događaj (Za prijavljenog korisnika, Max 5)");
        System.out.println("4. Prikažite svoje događaje");
        System.out.println("5. Odjava korisnika");
        System.out.println("0. Izađi");
        System.out.print("Unesite odabir: ");
    }

    private static int getUserChoice(Scanner sc) {
        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            sc.nextLine();
            return choice;
        } else {
            System.out.println("Pogrešan unos! Unesite broj.");
            sc.nextLine();
            return -1;
        }
    }

    private static void handleCreateEvent(Planner service, Scanner sc) {
        if (currentUser == null) {
            System.out.println("Greška: Morate biti prijavljeni da biste kreirali događaj!");
            return;
        }
        service.createEvent(sc, currentUser);
    }

    private static void handleLogout() {
        if (currentUser == null) {
            System.out.println("Niste prijavljeni!");
            return;
        }
        System.out.println("Korisnik " + currentUser.getUsername() + " je odjavljen.");
        currentUser = null;
    }
}
