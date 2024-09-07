package repository;

import Config.DBConnection;
import entities.Transport;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransportRepository {

    private final CarbonRepository carbonRepository;

    public TransportRepository() {
        this.carbonRepository = new CarbonRepository();
    }

    public void insertTransport(Transport transport, int userId) throws SQLException {

        int carbonId = carbonRepository.insertCarbon(transport, userId);

        String insertSQL = "INSERT INTO transports (carbon_id, distance_parcourue, type_de_vehicule) VALUES (?, ?, ?)";
        try  {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(insertSQL);

            stmt.setInt(1, carbonId);
            stmt.setDouble(2, transport.getDistanceParcourue());
            stmt.setString(3, transport.getTypeDeVehicule());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
