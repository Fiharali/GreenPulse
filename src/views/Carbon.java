package views;

import services.CarbonManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static services.UserManagement.users;

public class Carbon {
    public static void addCarbonToUser(Scanner scanner){
        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        String pass = scanner.nextLine().trim();
        Integer userId = Integer.valueOf(scanner.nextLine().trim());

        entities.User user = users.get(userId);
        if (user == null) {
            System.out.println("Utilisateur non trouvé. Veuillez vérifier l'identifiant.");
            return;
        }

        System.out.print("Entrez la quantité de carbone : ");
        double quantity;
        try {
            quantity = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre pour la quantité.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate;
        LocalDate endDate;

        while (true) {
            try {
                System.out.print("Entrez la date de début (format JJ/MM/AAAA) : ");
                startDate = LocalDate.parse(scanner.nextLine().trim(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Date de début invalide. Veuillez entrer une date au format JJ/MM/AAAA.");
            }
        }

        while (true) {
            try {
                System.out.print("Entrez la date de fin (format JJ/MM/AAAA) : ");
                endDate = LocalDate.parse(scanner.nextLine().trim(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Date de fin invalide. Veuillez entrer une date au format JJ/MM/AAAA.");
            }
        }

        entities.Carbon newCarbon = new entities.Carbon(quantity, startDate, endDate);

        CarbonManagement.addConsumptionToUser(user,newCarbon);


    }
}
