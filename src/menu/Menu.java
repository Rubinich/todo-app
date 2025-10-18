package menu;

import entities.Planner;
import entities.User;
import java.util.Scanner;

public class Menu {
    private static User currentUser = null;

    private Menu() {}

    public static void runApp(Planner service, Scanner sc) {
        int menuChoice;

        System.out.println("### APLIKACIJA ZA PLANIRANJE DOGAĐAJA ###");

        while (true) {
            System.out.println("\n=== GLAVNI IZBORNIK ===");
            String status = (currentUser != null) ? "Prijavljeni kao: " + currentUser.getUsername() : "Niste prijavljeni.";
            System.out.println("Status: " + status);
            System.out.println("1. Registriraj novog korisnika (Max 5)");
            System.out.println("2. Prijava (Postavi trenutnog korisnika)");
            System.out.println("3. Kreiraj novi događaj (Za prijavljenog korisnika, Max 5)");
            System.out.println("4. Prikaži sve događaje");
            System.out.println("0. Izađi");
            System.out.print("Unesite odabir: ");

            if (sc.hasNextInt()) {
                menuChoice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Pogrešan unos! Unesite broj.");
                sc.nextLine();
                continue;
            }

            switch (menuChoice) {
                case 1 -> service.createUser(sc);
                case 2 -> currentUser = service.loginUser(sc);
                case 3 -> {
                    if (currentUser != null) {
                        service.createEvent(sc, currentUser);
                    } else {
                        System.out.println("Greska: Morate biti prijavljeni da biste kreirali događaj! Odaberite opciju 2 za prijavu.");
                    }
                }
                case 4 -> service.printAllEvents();
                case 0 -> {
                    System.out.println("Izlaz iz aplikacije. Doviđenja!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Nepostojeća opcija. Pokušajte ponovno.");
            }
        }
    }
}
