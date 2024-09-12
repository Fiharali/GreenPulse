package repository;

import Config.DBConnection;
import entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class UserRepository {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public List<User> all() {
        List<User> users = new ArrayList<>();
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
                    "LEFT JOIN transports t ON c.id = t.carbon_id";
            stmt = conn.prepareStatement(selectSQL);
            rs = stmt.executeQuery();


            Map<Integer, User> userMap = new HashMap<>();

            while (rs.next()) {

                int userId = rs.getInt("u_user_id");
                String userName = rs.getString("name");
                int userAge = rs.getInt("age");
                User user = userMap.get(userId);
                if (user == null) {
                    user = new User(userId, userName, userAge);
                    userMap.put(userId, user);
                    users.add(user);
                }

                int carbonId = rs.getInt("carbon_id");
                int carbonUserId = rs.getInt("c_user_id");


                if (carbonId != 0 && carbonUserId == userId) {
                    double quantity = rs.getDouble("quantity");
                    LocalDate startDate = rs.getDate("start_date").toLocalDate();
                    LocalDate endDate = rs.getDate("end_date").toLocalDate();
                    String carbonType = rs.getString("carbon_type");

                    Carbon carbonAnyInstance = null;
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


                    if (carbonAnyInstance != null && !user.hasCarbon(carbonId) && user.getId() == carbonUserId) {
                        user.addCarbon(carbonAnyInstance);
                    }
                }
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    private Optional<User> findOrCreateUser(List<User> users, int id, String name, int age) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .or(() -> {
                    User newUser = new User(id, name, age);
                    users.add(newUser);
                    return Optional.of(newUser);
                });
    }

    public  boolean create( String name , int age) {
        boolean isCreated = false;
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
            isCreated=true;

        } catch (SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
        return isCreated;
    }

    public Optional<User> find(int id) {
        try {
            DBConnection dbConnection = DBConnection.getInstance();
            conn = dbConnection.getConnection();
            conn.setAutoCommit(false);
            String selectUserSQL = "SELECT id, name, age FROM users WHERE id = ?";
            stmt = conn.prepareStatement(selectUserSQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int uId = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return Optional.of(new User(uId, name, age));
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
        return Optional.empty();
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


//    public List<Optional<User>> all() {
//        List<Optional<User>> users = new ArrayList<>();
//        try {
//            conn = DBConnection.getInstance().getConnection();
//            String selectSQL = "SELECT u.id , u.name, u.age from users u";
//            stmt = conn.prepareStatement(selectSQL);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                int userId = rs.getInt("id");
//                String userName = rs.getString("name");
//                int userAge = rs.getInt("age");
//                Optional<User> user = carbonRepository.find(userId);
//                users.add(user);
//
//            }
//
//            return users;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}



