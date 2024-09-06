package views;

import Config.DBConnection;
import services.UserManagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {



    public  void mainMenu(Scanner scanner) {
        boolean exit = false;
        User user = new User();
        UserManagement userManagement = new UserManagement();
        Carbon carbon = new Carbon();

        while (!exit) {

            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Afficher tous les utilisateurs");
            System.out.println("2. Créer un nouvel utilisateur");
            System.out.println("3. Afficher un seul  utilisateur");
            System.out.println("4. supprimée un utilisateur");
            System.out.println("5. modifier   un utilisateur");
            System.out.println("----------------------------------------------------------");
            System.out.println("6. generate Rapport d'un utilisateur");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            char choice = scanner.next().charAt(0);

            switch (choice) {
                case '2':
                    user.createUser(scanner);
                    break;
                case '1':
                    user.displayUsers();
                    break;
                case '3':
                    user.getUserById(scanner);
                    break;
                case '4':
                    user.deleteUser(scanner);
                    break;
                case '5':
                    user.updateUser(scanner);
                    break;
                case '6':
                    carbon.showRapport(scanner);
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
