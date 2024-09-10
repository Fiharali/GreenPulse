package repository;

import Config.DBConnection;
import entities.*;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public boolean destroy( int carbonId)  {
        boolean carbonDeleted=false;
        String deleteSQL = "delete from carbons where id =? ";
        try {
            Connection cnx = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = cnx.prepareStatement(deleteSQL);
            stmt.setDouble(1, carbonId);
            stmt.executeUpdate();
            carbonDeleted=true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carbonDeleted;
    }



    public Optional<User> find(int id) {
        User user = new User();
        try {
            conn = DBConnection.getInstance().getConnection();
            String selectSQL = "SELECT u.id u_user_id, u.name, u.age, c.id  carbon_id, c.user_id AS c_user_id, c.quantity, c.start_date, c.end_date, " +
                    "c.type AS carbon_type, a.type_aliment, a.poids, " +
                    "l.consommation_energie, l.type_energie, " +
                    "t.distance_parcourue, t.type_de_vehicule " +
                    "FROM users u " +
                    "LEFT JOIN carbons c ON u.id = c.user_id " +
                    "LEFT JOIN alimentations a ON c.id = a.carbon_id " +
                    "LEFT JOIN logements l ON c.id = l.carbon_id " +
                    "LEFT JOIN transports t ON c.id = t.carbon_id where  u.id= ?";

            stmt = conn.prepareStatement(selectSQL);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            user.getCarbons().clear();
            while (rs.next()) {
                int userId = rs.getInt("u_user_id");
                String userName = rs.getString("name");
                int userAge = rs.getInt("age");
                int carbonId = rs.getInt("carbon_id");
                int carbonUserId = rs.getInt("c_user_id");
                user.setId(userId);
                user.setName(userName);
                user.setAge(userAge);

                Carbon carbonAnyInstance = null;
                    if (carbonId != 0 && carbonUserId == userId) {
                        double quantity = rs.getDouble("quantity");
                        LocalDate startDate = rs.getDate("start_date").toLocalDate();
                        LocalDate endDate = rs.getDate("end_date").toLocalDate();
                        String carbonType = rs.getString("carbon_type");

                        switch (carbonType) {
                            case "ALIMENTATION":
                                String typeAliment = rs.getString("type_aliment");
                                double poids = rs.getDouble("poids");
                                carbonAnyInstance = new Alimentation(carbonId, quantity, startDate, endDate, typeAliment, poids);
                                break;
                            case "LOGEMENT":
                                double consommationEnergie = rs.getDouble("consommation_energie");
                                String typeEnergie = rs.getString("type_energie");
                                carbonAnyInstance = new Logement(carbonId, quantity, startDate, endDate, consommationEnergie, typeEnergie);
                                break;
                            case "TRANSPORT":
                                double distanceParcourue = rs.getDouble("distance_parcourue");
                                String typeVehicule = rs.getString("type_de_vehicule");
                                carbonAnyInstance = new Transport(carbonId, quantity, startDate, endDate, distanceParcourue, typeVehicule);
                                break;
                        }

                        user.addCarbon(carbonAnyInstance);
                    }

            }
            return Optional.of(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
