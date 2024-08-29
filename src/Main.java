import entities.User;
import services.UserManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Display menu options
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Créer un nouvel utilisateur");
            System.out.println("2. Afficher tous les utilisateurs");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");

            char choice = scanner.next().charAt(0);

            switch (choice) {
                case '1':
                    UserManagement.createUser(scanner);
                    break;
                case '2':
                    UserManagement.displayUsers();
                    break;
                case '3':
                    UserManagement.updateUser(scanner);
                    break;
                case '4':
                    UserManagement.deleteUser(scanner);
                    break;
                case 7:
                    exit = true;
                    System.out.println("Merci d'avoir utilisé l'application !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }

        scanner.close();

    }


}
