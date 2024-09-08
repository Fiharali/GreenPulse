package views;

import entities.User;
import entities.enums.TypeCarbon;
import services.CarbonManagement;
import utils.InputUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.time.temporal.TemporalAdjusters;

//import static services.UserManagement.users;

public class Carbon {




    private String choiceVehiculeOrEnergieOrAlimentType(String ch1 ,String ch2 , String carbonType) {
        int choice = 0;
        String typeDeVehicule = "";

        while (choice < 1 || choice > 2) {
            System.out.println(" ------------------------------------------------ ");
            System.out.println(" 1 : type de "+carbonType+" "+ch1);
            System.out.println(" 2 : type de "+carbonType+" "+ch2);
            System.out.println(" ------------------------------------------------ ");

            choice = InputUtils.readInt("Choisissez le type de véhicule :");

            switch (choice) {
                case 1:
                    typeDeVehicule = ch1;
                    break;
                case 2:
                    typeDeVehicule = ch2;
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez choisir 1 ou 2.");
                    break;
            }
        }

        return typeDeVehicule;
    }
    public void choiceCarbonType(Scanner scanner) {
        int choice = 0;

        while (choice < 1 || choice > 3) {
            System.out.println(" ------------------------------------------------ ");
            System.out.println(" 1 : pour ajouter de type TRANSPORT ");
            System.out.println(" 2 : pour ajouter de type LOGEMENT ");
            System.out.println(" 3 : pour ajouter de type ALIMENTATION ");
            System.out.println(" ------------------------------------------------ ");

            choice = InputUtils.readInt("Choisissez votre type :");

            switch (choice) {
                case 1:
                    addCarbonWithTransport(scanner);
                    break;
                case 2:
                    addCarbonWithLogement(scanner);
                    break;
                case 3:
                    addCarbonWithAlimentation(scanner);
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez choisir 1, 2 ou 3.");
                    break;
            }
        }
    }
    public  void addCarbonWithTransport(Scanner scanner){

        LocalDate startDate;
        LocalDate endDate;

        String pass = scanner.nextLine();
        int userId = InputUtils.readInt("Entrez l'identifiant de l'utilisateur : ");
        double quantity = InputUtils.readDouble("Entrez la quantité de carbone :");
        startDate = InputUtils.readDate("Entrez la date de debut (format JJ/MM/AAAA) : ");
        endDate =  InputUtils.readEndDateAfterStart("Entrez la date de fin (format JJ/MM/AAAA) : ", startDate);
        int distance = InputUtils.readInt("Entrez la distance  : ");
        String vehiculeType = choiceVehiculeOrEnergieOrAlimentType("voiture","train","Vehicule");
        CarbonManagement carbonManagement = new CarbonManagement();
        carbonManagement.createTransport(quantity,startDate,endDate,distance, vehiculeType ,userId);

    }

    public  void addCarbonWithLogement(Scanner scanner){

        LocalDate startDate;
        LocalDate endDate;

        String pass = scanner.nextLine();
        int userId = InputUtils.readInt("Entrez l'identifiant de l'utilisateur : ");
        double quantity = InputUtils.readDouble("Entrez la quantité de carbone :");
        startDate = InputUtils.readDate("Entrez la date de debut (format JJ/MM/AAAA) : ");
        endDate =  InputUtils.readEndDateAfterStart("Entrez la date de fin (format JJ/MM/AAAA) : ", startDate);
        int consommationEnergie = InputUtils.readInt("Entrez la consomation  : ");
        String energieType = choiceVehiculeOrEnergieOrAlimentType("electricité","gaz","Energie");
        CarbonManagement carbonManagement = new CarbonManagement();
        carbonManagement.createLogement(quantity,startDate,endDate,consommationEnergie, energieType ,userId);

    }

    public  void addCarbonWithAlimentation(Scanner scanner){

        LocalDate startDate;
        LocalDate endDate;

        String pass = scanner.nextLine();
        int userId = InputUtils.readInt("Entrez l'identifiant de l'utilisateur : ");
        double quantity = InputUtils.readDouble("Entrez la quantité de carbone :");
        startDate = InputUtils.readDate("Entrez la date de debut (format JJ/MM/AAAA) : ");
        endDate =  InputUtils.readEndDateAfterStart("Entrez la date de fin (format JJ/MM/AAAA) : ", startDate);
        int poids = InputUtils.readInt("Entrez la poids  : ");
        String alimentType = choiceVehiculeOrEnergieOrAlimentType("viande ","Legume ","alimentation");
        CarbonManagement carbonManagement = new CarbonManagement();
        carbonManagement.createAliment(quantity,startDate,endDate,poids, alimentType ,userId);

    }












    public   void showRapport(Scanner scanner) {

        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        String pass = scanner.nextLine().trim();
        Integer userId = Integer.valueOf(scanner.nextLine().trim());

        entities.User user = new User();



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
