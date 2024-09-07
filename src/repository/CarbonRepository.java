package repository;

import Config.DBConnection;
import entities.Carbon;


import java.sql.*;

public class CarbonRepository {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public int insertCarbon(Carbon carbon, int userId)  {
        String insertSQL = "INSERT INTO carbons (quantity, start_date, end_date, type, user_id) VALUES (?, ?, ?, CAST(? AS type_carbon), ?)";
        try {
            Connection cnx = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = cnx.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

            stmt.setDouble(1, carbon.getQuantity());
            stmt.setDate(2, Date.valueOf(carbon.getStartDate()));
            stmt.setDate(3, Date.valueOf(carbon.getEndDate()));
            stmt.setString(4, carbon.type.name());
            stmt.setInt(5, userId);

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating carbon failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
