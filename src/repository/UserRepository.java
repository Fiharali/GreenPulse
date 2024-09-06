package repository;

import Config.DBConnection;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            String selectSQL = "SELECT id, name, age FROM users";
            stmt = conn.prepareStatement(selectSQL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                User user = new User(id ,name, age);
              //  user.setId(id);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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



