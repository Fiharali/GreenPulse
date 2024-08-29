package services;

import entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManagement {
    public static  Map<Integer, User> users = new HashMap<>();
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
    public static void createUser(int id, String name , int age) {
        User newUser = new User(name, age, id);
        users.put(id, newUser);
        System.out.println("Compte créé avec succès !");
    }

    public static void UpdateUser(User user, String newName, Integer newAge) {
        if (newName != null && !newName.isEmpty()) {
            user.setName(newName);
        }
        if (newAge != null) {
            user.setAge(newAge);
        }

        System.out.println("Utilisateur mis à jour avec succès !");
    }

    public static boolean deleteUser(Integer id) {
        User removedUser = users.remove(id);
        if (removedUser != null) {
            System.out.println("Utilisateur supprimé avec succès !");
            return true;
        } else {
            System.out.println("Utilisateur non trouvé.");
            return false;
        }
    }






}
