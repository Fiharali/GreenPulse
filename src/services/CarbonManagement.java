package services;

import entities.Alimentation;
import entities.Logement;
import entities.Transport;
import repository.AlimentationRepository;
import repository.CarbonRepository;
import repository.LogementRepository;
import repository.TransportRepository;

import java.sql.SQLException;
import java.time.LocalDate;

public class CarbonManagement  {

    private final TransportRepository transportRepository = new TransportRepository();
    private final LogementRepository logementRepository = new LogementRepository();
    private final AlimentationRepository alimentationRepository = new AlimentationRepository();
    private final CarbonRepository carbonRepository = new CarbonRepository();


    public void createTransport(double quantity, LocalDate startDate, LocalDate endDate, double distanceParcourue, String typeDeVehicule, int userId) {
        Transport transport = new Transport(quantity, startDate, endDate, distanceParcourue, typeDeVehicule);
        try {
            transportRepository.insertTransport(transport, userId);
            System.out.println("Transport carbon consumption created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void createLogement(double quantity, LocalDate startDate, LocalDate endDate, double consommationEnergie, String typeDeEnergie, int userId) {
        Logement logement = new Logement(quantity, startDate, endDate, consommationEnergie, typeDeEnergie);
        try {
            logementRepository.insertLogement(logement, userId);
            System.out.println("Transport carbon consumption created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createAliment(double quantity, LocalDate startDate, LocalDate endDate, double poids, String typeDeAliment, int userId) {
        Alimentation alimentation = new Alimentation(quantity, startDate, endDate, typeDeAliment, poids);
        try {
            alimentationRepository.insertAlimentation(alimentation, userId);
            System.out.println("Transport carbon consumption created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int carbonId){

        if (carbonRepository.destroy(carbonId)) {
            System.out.println("user a été supprimé avec succès  ");
        }else{
            System.out.println("L'utilisateur  n'existe pas.");
        }

    }

}

