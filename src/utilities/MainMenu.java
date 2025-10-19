package utilities;

import entities.Planner;
import entities.User;
import java.util.Scanner;

public class MainMenu {
    private static User currentUser = null;

    private MainMenu() {}

    public static void runMainMenu(Planner service, Scanner sc) {
        boolean running = true;
        System.out.println("#### APLIKACIJA ZA PLANIRANJE DOGADAJA ####");

        while (running) {
            printMenu();
            int menuChoice = getUserChoice(sc);
            // sredit prvom prilikom
            switch (menuChoice) {
                case 1 -> service.createUser(sc);
                case 2 -> currentUser = service.loginUser(sc);
                case 3 -> handleCreateEvent(service, sc);
                case 4 -> {
                    if (currentUser != null) {
                        currentUser.printUserEvents();
                    } else {
                        System.out.println("Greska: Niste prijavljeni!");
                    }
                }

                case 5 -> handleLogout();
                case 6 -> {
                    System.out.println("\n=== PRETRAZIVANJE DOGADAJA ===");
                    System.out.println("1. Pretraga po organizatoru");
                    System.out.println("2. Pretraga po naslovu");
                    System.out.print("Odaberite opciju: ");
                    int searchChoice = getUserChoice(sc);
                    switch (searchChoice) {
                        case 1 -> service.searchEventsByOrganizer(sc);
                        case 2 -> service.searchEventsByTitle(sc);
                        default -> System.out.println("Nepostojeca opcija!");
                    }
                }
                case 7 -> service.showEventStatistics();
                case 0 -> {
                    System.out.println("Izlaz iz aplikacije.");
                    sc.close();
                    running = false;
                }
                default -> System.out.println("Nepostojeca opcija! Pokusaj ponovno.");
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
        System.out.println("4. Prikazite svoje dogadaje");
        System.out.println("5. Odjava korisnika");
        System.out.println("6. Pretrazite dogadaje (prema organizatoru ili naslovu)");
        System.out.println("7. Prikaz osoba s najmanje ili najvise zadataka");
        System.out.println("0. Izadi");
        System.out.print("Unesite odabir: ");
    }

    private static int getUserChoice(Scanner sc) {
        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            sc.nextLine();
            return choice;
        } else {
            System.out.println("Nepostojeca opcija!");
            sc.nextLine();
            return -1;
        }
    }

    private static void handleCreateEvent(Planner service, Scanner sc) {
        if (currentUser == null) {
            System.out.println("Greska: Morate biti prijavljeni da biste kreirali dogadaj!");
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
