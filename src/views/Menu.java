package views;

import services.UserManagement;
import java.util.Scanner;

public class Menu {



    public static void mainMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {

            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Afficher tous les utilisateurs");
            System.out.println("2. Créer un nouvel utilisateur");
            System.out.println("3. Modifier un  utilisateur");
            System.out.println("4. supprimée un utilisateur");
            System.out.println("5. ajouter carbon pour un utilisateur");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            char choice = scanner.next().charAt(0);

            switch (choice) {
                case '2':
                    User.createUser(scanner);
                    break;
                case '1':
                    UserManagement.displayUsers();
                    break;
                case '3':
                    User.updateUser(scanner);
                    break;
                case '4':
                    User.deleteUser(scanner);
                    break;
                case '5':
                    Carbon.addCarbonToUser(scanner);
                    break;
                case '0':
                    exit = true;
                        System.out.println("Merci d'avoir utilisé l'application !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }
}
