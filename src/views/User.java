package views;

import entities.Alimentation;
import entities.Carbon;
import entities.Logement;
import entities.Transport;
import services.UserManagement;
import utils.InputUtils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//import static services.UserManagement.users;


public class User {

public UserManagement userManagement= new UserManagement();

    public void displayUsers() {
        List<entities.User> users = userManagement.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("Users List:");
            for (entities.User user : users) {
               int  lastUserId =0 ;

                if (lastUserId!=(user.getAge())) {
                    System.out.println("----------------------------------------------------------");
                    System.out.printf("User ID: %d | Name: %s | Age: %d\n", user.getAge(), user.getName(), user.getAge());
                    System.out.println("----------------------------------------------------------");
                    lastUserId = user.getAge();
                }

                for (Carbon carbon : user.getCarbons()) {
                    String type = "";

                    System.out.printf("Carbon ID: %d | Quantity: %.2f | Start Date: %s | End Date: %s | Type: %s\n",
                            carbon.getId(), carbon.getQuantity(), carbon.getStartDate(), carbon.getEndDate(), type);

                    if (carbon instanceof Alimentation) {

                        Alimentation alim = (Alimentation) carbon;
                        type = "ALIMENTATION";
                        String typeAlimentation = alim.getTypeAliment();
                        double poids = alim.getPoids();
                        System.out.printf("Alimentation: Type Aliment: %s | Poids: %.2f\n", typeAlimentation, poids);

                    } else if (carbon instanceof Logement) {

                        Logement log = (Logement) carbon;
                        type = "LOGEMENT";
                        double consommationEnergie = log.getConsommationEnergie();
                        String typeEnergie = log.getTypeEnergie();
                        System.out.printf("Logement: Consommation Energie: %.2f | Type Energie: %s\n", consommationEnergie, typeEnergie);

                    } else if (carbon instanceof Transport) {

                        Transport trans = (Transport) carbon;
                        type = "TRANSPORT";
                        double distance = trans.getDistanceParcourue();
                        String typeVehicule = trans.getTypeDeVehicule();
                        System.out.printf("Transport: Distance Parcourue: %.2f | Type de Vehicule: %s\n", distance, typeVehicule);
                    }
                    System.out.println("----------------------------------------------------------");

                }
                System.out.println("----------------------------------------------------------");
                System.out.println("----------------------------------------------------------");
                System.out.println("----------------------------------------------------------");
            }
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
