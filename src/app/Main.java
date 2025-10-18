package app;

import entities.PlannerService;
import entities.User;
import menu.Menu;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    private static PlannerService service = new PlannerService();
    private static User trenutniKorisnik = null;

    public static void main(String[] args) {
        Scanner skener = new Scanner(System.in);
        int odabir;

        System.out.println("### APLIKACIJA ZA PLANIRANJE DOGAĐAJA ###");
        while (true) {
            Menu.showMenu(trenutniKorisnik);

            if (skener.hasNextInt()) {
                odabir = skener.nextInt();
                skener.nextLine();
            } else {
                System.out.println("Pogrešan unos! Unesite broj.");
                skener.nextLine();
                continue;
            }

            switch (odabir) {
                case 1:
                    registrirajNovogKorisnika(skener);
                    break;
                case 2:
                    prijavaKorisnika(skener);
                    break;
                case 3:
                    if (trenutniKorisnik != null) {
                        kreirajNoviDogadaj(skener);
                    } else {
                        System.out.println("Greska: Morate biti prijavljeni da biste kreirali događaj! Odaberite opciju 2 za prijavu.");
                    }
                    break;
                case 4:
                    service.printAllEvents();
                    break;
                case 0:
                    System.out.println("Izlaz iz aplikacije. Doviđenja!");
                    skener.close();
                    return;
                default:
                    System.out.println("Nepostojeća opcija. Pokušajte ponovno.");
            }
        }
    }

    private static void registrirajNovogKorisnika(Scanner skener) {
        System.out.println("\n=== REGISTRACIJA ===");
        System.out.print("Korisničko ime: ");
        String ime = skener.nextLine();

        System.out.print("Lozinka: ");
        String lozinka = skener.nextLine();

        System.out.print("Email: ");
        String email = skener.nextLine();

        service.registerUser(ime, lozinka, email);
    }

    private static void prijavaKorisnika(Scanner skener) {
        System.out.println("\n=== PRIJAVA ===");
        System.out.print("Unesite korisničko ime: ");
        String ime = skener.nextLine();
        System.out.print("Unesite lozinku: ");
        String lozinka = skener.nextLine();

        User pronadjeni = service.findUser(ime);

        if (pronadjeni != null && pronadjeni.checkPassword(lozinka)) {
            trenutniKorisnik = pronadjeni;
            System.out.println("Uspješno prijavljeni kao " + trenutniKorisnik.getUsername() + "!");
        } else {
            System.out.println("Prijava neuspješna: Pogrešno korisničko ime ili lozinka.");
        }
    }

    private static void kreirajNoviDogadaj(Scanner skener) {
        System.out.println("\n=== KREIRANJE DOGAĐAJA (Organizator: " + trenutniKorisnik.getUsername() + ") ===");
        System.out.print("Naziv događaja: ");
        String naziv = skener.nextLine();
        System.out.print("Lokacija: ");
        String lokacija = skener.nextLine();
        System.out.print("Opis događaja: ");
        String opis = skener.nextLine();

        System.out.print("Datum i vrijeme (YYYY-MM-DDTHH:MM, npr. 2025-12-31T19:00): ");
        String datumVrijemeStr = skener.nextLine();

        try {
            LocalDateTime datumVrijeme = LocalDateTime.parse(datumVrijemeStr);
            service.makeEvent(naziv, opis, lokacija, datumVrijeme, trenutniKorisnik);

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Greška u formatu datuma/vremena! Koristite YYYY-MM-DDTHH:MM format.");
        } catch (Exception e) {
            System.out.println("Došlo je do neočekivane greške prilikom kreiranja događaja.");
        }
    }
}
