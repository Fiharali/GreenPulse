package repository;

import Config.DBConnection;
import entities.Alimentation;
import entities.Logement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlimentationRepository {


    private final CarbonRepository carbonRepository;

    public AlimentationRepository() {
        this.carbonRepository = new CarbonRepository();
    }


    public void insertAlimentation(Alimentation alimentation, int userId) throws SQLException {

        int carbonId = carbonRepository.insertCarbon(alimentation, userId);

        String insertSQL = "INSERT INTO alimentations (carbon_id, poids, type_aliment) VALUES (?, ?, ?)";
        try  {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(insertSQL);

            stmt.setInt(1, carbonId);
            stmt.setDouble(2, alimentation.getPoids());
            stmt.setString(3, alimentation.getTypeAliment());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
