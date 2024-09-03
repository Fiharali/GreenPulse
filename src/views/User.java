package views;

import services.UserManagement;
import java.util.Scanner;
import static services.UserManagement.users;


public class User {

public UserManagement userManagement= new UserManagement();
    public  void createUser(Scanner scanner){
        System.out.print("Entrez votre nom : ");
        String name = scanner.next();

        System.out.print("Entrez votre âge : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        int id;

        while (true) {
             id = Integer.parseInt(scanner.nextLine());
            if (!users.containsKey(id)) {
                break;
            } else {
                System.out.println("Cet identifiant est déjà utilisé. Veuillez en choisir un autre.");
            }
        }
        this.userManagement.createUser(id,name,age);
    }



    public  void updateUser(Scanner scanner) {
        System.out.print("Entrez l'identifiant de l'utilisateur à mettre à jour : ");
        String pass = scanner.nextLine();
        int id = Integer.parseInt(scanner.nextLine());
        entities.User user = users.get(id);

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

            userManagement.UpdateUser(user, newName, newAge);
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
    }



    public  void deleteUser(Scanner scanner) {
        System.out.print("Entrez l'identifiant de l'utilisateur à supprimer : ");

        String pass = scanner.nextLine().trim();
        String idInput = scanner.nextLine().trim();

        if (idInput.isEmpty()) {
            System.out.println("Entrée invalide. L'identifiant ne peut pas être vide.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre entier pour l'identifiant.");
            return;
        }


        userManagement.deleteUser(id);
    }
}
