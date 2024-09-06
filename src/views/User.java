package views;

import services.UserManagement;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//import static services.UserManagement.users;


public class User {

public UserManagement userManagement= new UserManagement();

    public void displayUsers(){

        List<entities.User> users = userManagement.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("Users List:");
            users.forEach( user -> {
                System.out.println("----------------------------------------------------------");
                System.out.println("ID: " + user.getId());
                System.out.println("Name: " + user.getName());
                System.out.println("Age: " + user.getAge());
                System.out.println("----------------------------------------------------------");
            });
        }
    }

    public  void createUser(Scanner scanner){
        System.out.print("Entrez votre nom : ");
        String name = scanner.next();
        boolean validAge = false;
        int age = 0;
        while (!validAge) {
            System.out.print("Entrez votre âge : ");
            try {
                age = scanner.nextInt();
                validAge = true; 
            } catch (InputMismatchException e) {
                System.out.println("Erreur : L'âge doit être un nombre entier.");
                scanner.next(); 
            }
        }
        scanner.nextLine();
        userManagement.createUser(name ,age);
    }


    public void getUserById(Scanner scanner){
        System.out.print("Entrez user id  : ");
        int id = scanner.nextInt();
       entities.User user = userManagement.getUserById(id);
        if (user == null) {
            System.out.println("No user found with this id .");
        } else {
            System.out.println("----------------------------------------------------------");
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Age: " + user.getAge());
            System.out.println("----------------------------------------------------------");

        }
    }

    public  void updateUser(Scanner scanner) {
        System.out.print("Entrez l'identifiant de l'utilisateur à mettre à jour : ");
        String pass = scanner.nextLine();
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez un nouveau nom : ");
        String newName = scanner.nextLine();
        System.out.print("Entrez un nouveau âge : ");
        int newAge = scanner.nextInt();

        userManagement.UpdateUser(id, newName, newAge);
    }

    public  void deleteUser(Scanner scanner) {
        System.out.print("Entrez l'identifiant de l'utilisateur à supprimer : ");

        String pass = scanner.nextLine().trim();
        String idInput = scanner.nextLine();
        if (idInput.isEmpty()) {
            System.out.println("Entrée invalide. L'identifiant ne peut pas être vide.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idInput);
            userManagement.deleteUser(id);
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre entier pour l'identifiant.");
            return;
        }


        userManagement.deleteUser(id);
    }
}
