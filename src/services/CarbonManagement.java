package services;

import entities.Carbon;
import entities.Transport;
import entities.User;
import repository.CarbonRepository;
import repository.TransportRepository;

import java.sql.SQLException;
import java.time.LocalDate;

import static entities.User.carbons;

public class CarbonManagement  {

    private final TransportRepository transportRepository = new TransportRepository();
    //private final LogementRepository logementRepository = new LogementRepository();
    //private final AlimentationRepository alimentationRepository = new AlimentationRepository();




    public void createTransport(double quantity, LocalDate startDate, LocalDate endDate, double distanceParcourue, String typeDeVehicule, int userId) {
        Transport transport = new Transport(quantity, startDate, endDate, distanceParcourue, typeDeVehicule);
        try {
            transportRepository.insertTransport(transport, userId);
            System.out.println("Transport carbon consumption created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

