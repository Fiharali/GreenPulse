package services;

import entities.Carbon;
import entities.User;
import repository.UserRepository;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagement implements UserRepository {
    public static  Map<Integer, User> users = new HashMap<>();
    @Override
    public  void displayUsers() {
        
        if (users.isEmpty()) {
            System.out.println("Aucun utilisateur enregistré.");
        } else {
            System.out.println("Liste des utilisateurs :");
            for (User user : users.values()) {
                System.out.println("ID : " + user.getId());
                System.out.println("Nom : " + user.getName());
                System.out.println("Âge : " + user.getAge());
                System.out.println("Carbone Consommation :");

                List<Carbon> carbons = user.getCarbons();

                double totalCarbon = 0;
                long totalDays = 0;
                if (((List<?>) carbons).isEmpty()) {
                    System.out.println("Aucune consommation de carbone enregistrée.");
                } else {

                    for (Carbon carbon : carbons) {
                        System.out.println("  - Quantité: " + carbon.getQuantity() + " g");
                        System.out.println("    Date de début: " + carbon.getStartDate());
                        System.out.println("    Date de fin: " + carbon.getEndDate());
                        System.out.println("    -----------------------------");
                        totalDays += ChronoUnit.DAYS.between(carbon.getStartDate(), carbon.getEndDate()) + 1;
                        totalCarbon += carbon.getQuantity();
                    }
                    System.out.println("Total de consommation de carbone de  : " + user.getName() + " c'est " + totalCarbon + " g");
                    System.out.println("-----------------------------");
                    final double DAYS_IN_WEEK = 7.0;
                    final double DAYS_IN_MONTH = 30.0;
                    final double DAYS_IN_YEAR = 365.0;


                    double averagePerDay = totalCarbon / totalDays;
                    double averagePerWeek = averagePerDay * DAYS_IN_WEEK;
                    double averagePerMonth = averagePerDay * DAYS_IN_MONTH;
                    double averagePerYear = averagePerDay * DAYS_IN_YEAR;


                    System.out.println("Moyenne de carbone par jour: " + averagePerDay + " g");
                    System.out.println("Moyenne de carbone par semaine: " + averagePerWeek + " g");
                    System.out.println("Moyenne de carbone par mois: " + averagePerMonth + " g");
                    System.out.println("Moyenne de carbone par an: " + averagePerYear + " g");
                }

                System.out.println("------------------------------------------------------------");
            }
        }
    }
    @Override
    public  void createUser(int id, String name , int age) {
        User newUser = new User(name, age, id);
        users.put(id, newUser);
        System.out.println("Compte créé avec succès !");
    }
    @Override
    public  void UpdateUser(User user, String newName, Integer newAge) {
        if (newName != null && !newName.isEmpty()) {
            user.setName(newName);
        }
        if (newAge != null) {
            user.setAge(newAge);
        }
        System.out.println("Utilisateur mis à jour avec succès !");
    }
    @Override
    public  void deleteUser(Integer id) {
        User removedUser = users.remove(id);
        if (removedUser != null) {
            System.out.println("Utilisateur supprimé avec succès !");
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
    }






}
