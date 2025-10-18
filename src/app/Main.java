package app;

import entities.Planner;
import menu.Menu;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Planner service = new Planner();

        Menu.runMeni(service, sc);
    }
}
