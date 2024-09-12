package views;

import entities.Alimentation;
import entities.Carbon;
import entities.Logement;
import entities.Transport;
import services.UserManagement;
import utils.InputUtils;

//import java.util.InputMismatchException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
//import static services.UserManagement.users;


public class User {

public UserManagement userManagement= new UserManagement();

    public void displayUsers() {
        List<entities.User> users = userManagement.getAllUsers();
        //System.out.println(users.get(1).getCarbons());

        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";

        users.sort(Comparator.comparingDouble((entities.User user) ->
                user.getCarbons().stream()
                        .mapToDouble(Carbon::calculerImpact)
                        .sum()
        ).reversed());


        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("Users List:");
            int lastUserId = -1;

            for (entities.User user : users) {
                if (lastUserId != user.getId()) {
                    System.out.println("----------------------------------------------------------");
                    System.out.printf("User ID: %d | Name: %s | Age: %d\n", user.getId(), user.getName(), user.getAge());
                    System.out.println("----------------------------------------------------------");
                    lastUserId = user.getId();
                }

                for (Carbon carbon : user.getCarbons()) {
                    String type = "";
                    System.out.printf("Carbon ID: %d | Quantity: %.2f | Start Date: %s | End Date: %s | Type: %s\n",
                            carbon.getId(), carbon.getQuantity(), carbon.getStartDate(), carbon.getEndDate(), type);
                    if (carbon instanceof Alimentation) {
                        Alimentation alim = (Alimentation) carbon;
                        String typeAlimentation = alim.getTypeAliment();
                        double poids = alim.getPoids();
                        System.out.printf("Alimentation: Type Aliment: %s | Poids: %.2f    ", typeAlimentation, poids);
                        System.out.println(ANSI_RED + "   ||  impact :: " +  alim.calculerImpact() + ANSI_RESET);

                    } else if (carbon instanceof Logement) {
                        Logement log = (Logement) carbon;
                        double consommationEnergie = log.getConsommationEnergie();
                        String typeEnergie = log.getTypeEnergie();
                        System.out.printf("Logement: Consommation Energie: %.2f | Type Energie: %s   ", consommationEnergie, typeEnergie);
                        System.out.println(ANSI_RED + "   ||  impact :: " +  log.calculerImpact() + ANSI_RESET);

                    } else if (carbon instanceof Transport) {
                        Transport trans = (Transport) carbon;

                        double distance = trans.getDistanceParcourue();
                        String typeVehicule = trans.getTypeDeVehicule();
                        System.out.printf("Transport: Distance Parcourue: %.2f | Type de Vehicule: %s  ", distance, typeVehicule);
                        System.out.println(ANSI_RED + "   ||  impact :: " +  trans.calculerImpact() + ANSI_RESET);

                    }
                }
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
        Optional<entities.User> user = userManagement.getUserById(id);
        if (user.isEmpty()) {
            System.out.println("No user found with this id .");
        } else {
            System.out.println("----------------------------------------------------------");
            System.out.println("ID: " + user.get().getId());
            System.out.println("Name: " + user.get().getName());
            System.out.println("Age: " + user.get().getAge());
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


    public void moyenneWithPeriod(Scanner scanner) {


        int id = InputUtils.readInt("Entrez user id  : ");
        LocalDate startDate = InputUtils.readDate("Entrez la date de debut ");
        LocalDate endDate = InputUtils.readDate("Entrez la date de fin ");
        Double averageConsumption = userManagement.filterByPeriod(id, startDate, endDate);

        if (averageConsumption == null) {
            System.out.println("No user found with this id or no carbon consumption data for the given period.");
        } else {
            System.out.println("----------------------------------------------------------");
            System.out.println("User ID: "+id+"| Average Carbon Consumption: "+averageConsumption+" KgCO2eq" );
            System.out.println("----------------------------------------------------------");
        }
    }

    public void detectInactiveUsers(Scanner scanner) {

        LocalDate startDate = InputUtils.readDate("Entrez la date de debut ");
        LocalDate endDate = InputUtils.readDate("Entrez la date de fin ");

        List<entities.User> users = userManagement.detectInactiveUsers(startDate,endDate);

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println(" Inactive Users List:");
            int lastUserId = -1;

            for (entities.User user : users) {
                if (lastUserId != user.getId()) {
                    System.out.println("----------------------------------------------------------");
                    System.out.printf("User ID: %d | Name: %s | Age: %d\n", user.getId(), user.getName(), user.getAge());
                    System.out.println("----------------------------------------------------------");
                    lastUserId = user.getId();
                }


            }
        }
    }
}
