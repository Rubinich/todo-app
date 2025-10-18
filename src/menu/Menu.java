package menu;

import entities.User;

public class Menu {
    public static void showMenu(User currentUser) {
        System.out.println("\n=== GLAVNI IZBORNIK ===");
        String status = (currentUser != null) ? "Prijavljeni kao: " + currentUser.getUsername() : "Niste prijavljeni.";
        System.out.println("Status: " + status);
        System.out.println("1. Registriraj novog korisnika (Max 5)");
        System.out.println("2. Prijava (Postavi trenutnog korisnika)");
        System.out.println("3. Kreiraj novi događaj (Za prijavljenog korisnika, Max 5)");
        System.out.println("4. Prikaži sve događaje");
        System.out.println("0. Izađi");
        System.out.print("Unesite odabir: ");
    }
}
