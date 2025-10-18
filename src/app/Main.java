package app;

import entities.Planner;
import entities.User;
import menu.Menu;
import java.util.Scanner;

public class Main {

    private static Planner service = new Planner();
    private static User currentUser = null;

    public static void main() {
        Scanner sc = new Scanner(System.in);
        Menu.runApp(service, sc, currentUser);
    }
}
