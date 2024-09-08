package repository;

import Config.DBConnection;
import entities.Alimentation;
import entities.Logement;
import entities.Transport;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public List<User> all() {

        List<User> users = new ArrayList<>();

        try {
            conn = DBConnection.getInstance().getConnection();

            String selectSQL = "SELECT u.id, u.name, u.age, c.id AS carbon_id, c.quantity, c.start_date, c.end_date, " +
                    "       c.type AS carbon_type, a.type_aliment, a.poids, " +
                    "       l.consommation_energie, l.type_energie, " +
                    "       t.distance_parcourue, t.type_de_vehicule " +
                    "FROM users u " +
                    "LEFT JOIN carbons c ON u.id = c.user_id " +
                    "LEFT JOIN alimentations a ON c.id = a.carbon_id " +
                    "LEFT JOIN logements l ON c.id = l.carbon_id " +
                    "LEFT JOIN transports t ON c.id = t.carbon_id";
            stmt = conn.prepareStatement(selectSQL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("id");
                String userName = rs.getString("name");
                int userAge = rs.getInt("age");
                User user = findOrCreateUser(users, userId, userName, userAge);
                Integer carbonId = rs.getInt("carbon_id");

                if (rs.wasNull()) {
                    continue;
                }

                double quantity = rs.getDouble("quantity");
                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate endDate = rs.getDate("end_date").toLocalDate();
                String carbonType = rs.getString("carbon_type");

                if ("ALIMENTATION".equals(carbonType)) {
                    String typeAliment = rs.getString("type_aliment");
                    double poids = rs.getDouble("poids");
                    user.addCarbon(new Alimentation(quantity, startDate, endDate, typeAliment, poids));
                } else if ("LOGEMENT".equals(carbonType)) {
                    double consommationEnergie = rs.getDouble("consommation_energie");
                    String typeEnergie = rs.getString("type_energie");
                    user.addCarbon(new Logement(quantity, startDate, endDate, consommationEnergie, typeEnergie));
                } else if ("TRANSPORT".equals(carbonType)) {
                    double distanceParcourue = rs.getDouble("distance_parcourue");
                    String typeVehicule = rs.getString("type_de_vehicule");
                    user.addCarbon(new Transport(quantity, startDate, endDate, distanceParcourue, typeVehicule));
                }

            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private User findOrCreateUser(List<User> users, int id, String name, int age) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        User newUser = new User(id, name, age);
        users.add(newUser);
        return newUser;
    }

    public  void create( String name , int age) {

        try {
            User newUser = new User(name, age);
            DBConnection dbConnection = DBConnection.getInstance();
            conn = dbConnection.getConnection();
            conn.setAutoCommit(false);
            String insertUserSQL = "INSERT INTO users (name, age) VALUES (?, ?)";
            stmt = conn.prepareStatement(insertUserSQL);
           // stmt.setString(1, newUser.getId());
            stmt.setString(1, newUser.getName());
            stmt.setInt(2, newUser.getAge());
            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public  User find( int id) {
        User user = null;
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            conn = dbConnection.getConnection();
            conn.setAutoCommit(false);
            String selectUserSQL = " select  id , name , age from users where id = ?";
            stmt = conn.prepareStatement(selectUserSQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int uId = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                user = new User(uId, name, age);
            }

        } catch (SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
        return user;
    }

    public  Boolean update( int id , String name , int age) {
        boolean isUpdated = false;
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            conn = dbConnection.getConnection();
            conn.setAutoCommit(false);
            String updateUserSQL = "UPDATE users  set name = ? ,  age = ? WHERE id = ?";
            stmt = conn.prepareStatement(updateUserSQL);
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setInt( 3, id);
            stmt.executeUpdate();
            conn.commit();
            isUpdated = true;
        } catch (SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
        return isUpdated ;
    }
    public  Boolean destroy( int id) {
        boolean isDeleted = false;
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            conn = dbConnection.getConnection();
            conn.setAutoCommit(false);
            String insertUserSQL = "DELETE  FROM users WHERE id = ?";
            stmt = conn.prepareStatement(insertUserSQL);
            stmt.setInt( 1, id);
            stmt.executeUpdate();
            conn.commit();
             isDeleted = true;
        } catch (SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
        return isDeleted ;
    }




}



