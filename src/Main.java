import entities.User;
import services.UserManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static services.UserManagement.users;


public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       // Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Display menu options
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Afficher tous les utilisateurs");
            System.out.println("2. Créer un nouvel utilisateur");
            System.out.println("3. Modifier un  utilisateur");
            System.out.println("4. supprimée un utilisateur");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option : ");

            char choice = scanner.next().charAt(0);

            switch (choice) {
                case '2':
                    createUser();
                    break;
                case '1':
                    UserManagement.displayUsers();
                    break;
                case '3':
                    updateUser();
                    break;
                case '4':
                    deleteUser();
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

        scanner.close();

    }


    public static void createUser(){
        System.out.print("Entrez votre nom : ");
        String name = scanner.next();

        System.out.print("Entrez votre âge : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        int id;

        while (true) {
            System.out.print("Entrez un identifiant unique : ");
            id = Integer.parseInt(scanner.nextLine());
            if (!users.containsKey(id)) {
                break;
            } else {
                System.out.println("Cet identifiant est déjà utilisé. Veuillez en choisir un autre.");
            }
        }
        UserManagement.createUser(id,name,age);
    }



    public static void updateUser() {
        System.out.print("Entrez l'identifiant de l'utilisateur à mettre à jour : ");
        String pass = scanner.nextLine();
        int id = Integer.parseInt(scanner.nextLine());
        User user = users.get(id);

        if (user != null) {
            System.out.println("Mise à jour des informations pour l'utilisateur : " + user.getName());

            System.out.print("Entrez un nouveau nom (laissez vide pour conserver le même) : ");
            String newName = scanner.nextLine();

            System.out.print("Entrez un nouveau âge (laissez vide pour conserver le même) : ");
            String ageInput = scanner.nextLine();
            Integer newAge = null;
            if (!ageInput.isEmpty()) {
                try {
                    newAge = Integer.parseInt(ageInput);
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide pour l'âge. Veuillez entrer un nombre entier.");
                    return;
                }
            }

            UserManagement.UpdateUser(user, newName, newAge);
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
    }


    public static void deleteUser() {
        System.out.print("Entrez l'identifiant de l'utilisateur à supprimer : ");

        String pass = scanner.nextLine().trim();
        String idInput = scanner.nextLine().trim();

        if (idInput.isEmpty()) {
            System.out.println("Entrée invalide. L'identifiant ne peut pas être vide.");
            return;
        }

        Integer id;
        try {
            id = Integer.valueOf(idInput);
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre entier pour l'identifiant.");
            return;
        }


        UserManagement.deleteUser(id);
    }




}
