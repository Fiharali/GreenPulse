package views;

import Config.DBConnection;
import services.UserManagement;
import utils.InputUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {



    public  void mainMenu(Scanner scanner) {
        boolean exit = false;
        User user = new User();
        Carbon carbon = new Carbon();

        while (!exit) {

            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Afficher tous les utilisateurs");
            System.out.println("2. Créer un nouvel utilisateur");
            System.out.println("3. Afficher un seul  utilisateur");
            System.out.println("4. supprimée un utilisateur");
            System.out.println("5. modifier   un utilisateur");
            System.out.println("----------------------------------------------------------");
            System.out.println("6. Ajouter Carbon Consommation");
            System.out.println("7. supprimée Carbon Consommation");
            System.out.println("8. Afficher un utilisateur ses Consommation");
            System.out.println("9.  filtrer les utilisateurs en fonction de la consommation totale");
            System.out.println("10. Calculer la consommation moyenne de carbone par utilisateur pour une période donnée. ");
            System.out.println("11. Identifier les utilisateurs n'ayant pas enregistré de consommation de carbone pendant une période spécifiée.. ");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");


            int choice = InputUtils.readInt("Choisissez une option :");

            switch (choice) {

                case 1:
                    user.displayUsers();
                    break;
                case 2:
                    user.createUser(scanner);
                    break;
                case 3:
                    user.getUserById(scanner);
                    break;
                case 4:
                    user.deleteUser(scanner);
                    break;
                case 5:
                    user.updateUser(scanner);
                    break;
                case 6:
                    carbon.choiceCarbonType(scanner);
                    break;
                case 7:
                    carbon.deleteCarbon(scanner);
                    break;
                case 8:
                    carbon.getUserWithCarbon(scanner);
                    break;
                case 9:
                    carbon.filterUsersByCarbon(scanner);
                    break;
                case 10:
                    user.moyenneWithPeriod(scanner);
                    break;
                case 11:
                    user.detectInactiveUsers(scanner);
                    break;
                case 0:
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
