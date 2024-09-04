package views;

import entities.User;
import services.CarbonManagement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.time.temporal.TemporalAdjusters;

import static services.UserManagement.users;

public class Carbon {
    public   void addCarbonToUser(Scanner scanner){
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
                if (endDate.isAfter(startDate)) {
                    break;
                } else {
                    System.out.println("La date de fin doit être après la date de début. Veuillez entrer une date valide.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Date de fin invalide. Veuillez entrer une date au format JJ/MM/AAAA.");
            }
        }

        entities.Carbon newCarbon = new entities.Carbon(quantity, startDate, endDate);
        CarbonManagement carbonManagement = new CarbonManagement();
        carbonManagement.addConsumptionToUser(user,newCarbon);


    }

    public   void showRapport(Scanner scanner) {

        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        String pass = scanner.nextLine().trim();
        Integer userId = Integer.valueOf(scanner.nextLine().trim());

        entities.User user = users.get(userId);
        if (user == null) {
            System.out.println("Utilisateur non trouvé. Veuillez vérifier l'identifiant.");
            return;
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date;
        while (true) {
            try {
                System.out.print("Entrez la date spécifique (format JJ/MM/AAAA) : ");
                date = LocalDate.parse(scanner.nextLine().trim(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Date invalide. Veuillez entrer une date au format JJ/MM/AAAA.");
            }
        }

        // User Uuser=new User();
        List<entities.Carbon> carbons = user.getCarbons();
        double totalCarbon = 0;
        long totalDays = 0;
        boolean found = false;
        double averageForDay = 0;
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        double totalCarbonForWeek = 0.0;
        boolean weekFound = false;
        YearMonth yearMonth = YearMonth.from(date);
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        double totalCarbonForMonth = 0.0;
        boolean monthFound = false;

        for (entities.Carbon carbon : carbons) {

            if ((carbon.getStartDate().equals(date) || carbon.getEndDate().equals(date)) ||
                    (carbon.getStartDate().isBefore(date) && carbon.getEndDate().isAfter(date))) {
                totalCarbon = carbon.getQuantity();
                totalDays = ChronoUnit.DAYS.between(carbon.getStartDate(), carbon.getEndDate()) + 1;
                double avg = totalCarbon / totalDays;
                averageForDay += avg;
                found = true;
            }

//---------------------------------------------------------------------------

            LocalDate carbonStart = carbon.getStartDate();
            LocalDate carbonEnd = carbon.getEndDate();
            if ((carbonStart.isBefore(endOfWeek) && carbonEnd.isAfter(startOfWeek)) ||
                    carbonStart.equals(startOfWeek) || carbonEnd.equals(endOfWeek)) {

                LocalDate overlapStart = carbonStart.isBefore(startOfWeek) ? startOfWeek : carbonStart;
                LocalDate overlapEnd = carbonEnd.isAfter(endOfWeek) ? endOfWeek : carbonEnd;


                long daysInPeriod = ChronoUnit.DAYS.between(overlapStart, overlapEnd) + 1;

                double avgCarbonPerDay = carbon.getQuantity() /
                        (ChronoUnit.DAYS.between(carbonStart, carbonEnd) + 1);


                totalCarbonForWeek += avgCarbonPerDay * daysInPeriod;
                weekFound = true;
            }

//---------------------------------------------------------------------------


            if ((carbonStart.isBefore(endOfMonth) && carbonEnd.isAfter(startOfMonth)) ||
                    carbonStart.equals(startOfMonth) || carbonEnd.equals(endOfMonth)) {


                LocalDate overlapStart = carbonStart.isBefore(startOfMonth) ? startOfMonth : carbonStart;
                LocalDate overlapEnd = carbonEnd.isAfter(endOfMonth) ? endOfMonth : carbonEnd;


                long daysInPeriod = ChronoUnit.DAYS.between(overlapStart, overlapEnd) + 1;


                double avgCarbonPerDay = carbon.getQuantity() /
                        (ChronoUnit.DAYS.between(carbonStart, carbonEnd) + 1);

                totalCarbonForMonth += avgCarbonPerDay * daysInPeriod;
                monthFound = true;
            }

        }


        if (!found) {
            System.out.println("Aucune consommation de carbone enregistrée pour cette date.");
        } else {
            System.out.println("le consomation  de carbone par pour le  " + date + " c'est " + averageForDay + " g");
        }
        if (weekFound) {
            System.out.println("La consommation totale de carbone pour la semaine du " +
                    startOfWeek + " au " + endOfWeek + " est de " + totalCarbonForWeek + " g.");
        } else {
            System.out.println("Aucune consommation de carbone enregistrée pour cette semaine.");
        }

        if (monthFound) {
            System.out.println("La consommation totale de carbone pour le mois de " +
                    yearMonth.getMonth() + " " + yearMonth.getYear() + " est de " + totalCarbonForMonth + " g.");
        } else {
            System.out.println("Aucune consommation de carbone enregistrée pour ce mois.");
        }

    }


}
