import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import java.util.InputMismatchException;


class User {
    private String name;
    private int age;
    private String id;


    public User(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





    public static User createUser(Scanner scanner, Map<String, User> users) {
        System.out.print("Entrez votre nom : ");
        String name = scanner.nextLine();


        int age = -1; // Initialize age to an invalid value
        while (age < 0) { // Loop until a valid age is entered
            System.out.print("Entrez votre âge : ");
            try {
                age = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                if (age < 0) {
                    System.out.println("L'âge ne peut pas être négatif. Veuillez réessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier pour l'âge.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

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
        return newUser;
    }
}
