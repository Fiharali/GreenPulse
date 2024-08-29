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
    public static  void updateUser(Scanner scanner) {
        System.out.print("Entrez l'identifiant de l'utilisateur à mettre à jour : ");
        String id = scanner.next();
        User user = users.get(id);
        if (user != null) {
            System.out.println("Mise à jour des informations pour l'utilisateur : " + user.getName());

            System.out.print("Entrez un nouveau nom (laissez vide pour conserver le même) : ");
            String a = scanner.nextLine();
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                user.setName(newName);
            }

            System.out.print("Entrez un nouveau age (laissez vide pour conserver le même) : ");
            String newAge = scanner.nextLine();
            if (!newAge.isEmpty()) {
                user.setAge(Integer.parseInt(newAge));
            }
        }else {
            System.out.println(" l'utilisateur  non trouvé") ;
        }
    }
    public static void deleteUser(Scanner scanner) {
        System.out.print("Entrez l'identifiant de l'utilisateur à supprimer : ");
        String a = scanner.nextLine();
        String id = scanner.nextLine();
        User removedUser = users.remove(id);
        if (removedUser != null) {
            System.out.println("Utilisateur supprimé avec succès !");
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
    }





}
