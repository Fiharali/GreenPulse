package repository;

import Config.DBConnection;
import entities.Logement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogementRepository {


    private final CarbonRepository carbonRepository;

    public LogementRepository() {
        this.carbonRepository = new CarbonRepository();
    }


    public void insertLogement(Logement logement, int userId) throws SQLException {

        int carbonId = carbonRepository.insertCarbon(logement, userId);

        String insertSQL = "INSERT INTO logements (carbon_id, consommation_energie, type_energie) VALUES (?, ?, ?)";
        try  {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(insertSQL);

            stmt.setInt(1, carbonId);
            stmt.setDouble(2, logement.getConsommationEnergie());
            stmt.setString(3, logement.getTypeEnergie());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
