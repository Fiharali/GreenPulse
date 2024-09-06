package views;

import services.UserManagement;
import utils.InputUtils;

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
        int age = InputUtils.readInt("Entrez votre âge : ");
        userManagement.createUser(name ,age);
    }


    public void getUserById(Scanner scanner){

        int id = InputUtils.readInt("Entrez user id  : ");
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

        int id = InputUtils.readInt("Entrez l'identifiant de l'utilisateur à mettre à jour : ");
        System.out.print("Entrez un nouveau nom : ");
        String pass = scanner.nextLine();
        String newName = scanner.nextLine();
        int newAge = InputUtils.readInt("Entrez un nouveau âge : ");

        userManagement.UpdateUser(id, newName, newAge);
    }

    public  void deleteUser(Scanner scanner) {
        int id = InputUtils.readInt("Entrez l'identifiant de l'utilisateur à supprimer : ");
        userManagement.deleteUser(id);
    }
}
