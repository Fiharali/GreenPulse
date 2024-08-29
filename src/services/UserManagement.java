package services;

import entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManagement {
    private static  Map<String, User> users = new HashMap<>();






    public static  void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("Aucun utilisateur enregistré.");
        } else {
            System.out.println("Liste des utilisateurs :");
            for (User user : users.values()) {
                System.out.println( "id : " + user.getId());
                System.out.println( "nom : " + user.getName());
                System.out.println( "age : " + user.getAge());
                System.out.println( "-----------------------------");
            }
        }
    }


    public static void createUser(Scanner scanner) {

        System.out.print("Entrez votre nom : ");
        String name = scanner.next();

        System.out.print("Entrez votre âge : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        String id;

        while (true) {
            System.out.print("Entrez un identifiant unique : ");
            id = scanner.nextLine();
            if (!users.containsKey(id)) {
                break;
            } else {
                System.out.println("Cet identifiant est déjà utilisé. Veuillez en choisir un autre.");
            }
        }

        User newUser = new User(name, age, id);
        users.put(id, newUser);
        System.out.println("Compte créé avec succès !");
    }





}
