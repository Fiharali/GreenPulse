import views.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import  Config.DBConnection;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
       Menu menu = new Menu();
       menu.mainMenu(scanner);
       scanner.close();
   }



   // public static void main(String[] args) {
    //    try {

          //  DBConnection dbConnection = DBConnection.getInstance();
          //  Connection connection = dbConnection.getConnection();
           // Statement statement = connection.createStatement();
           // String sql = "INSERT INTO users (id) VALUES (51)";

           // try (Statement stm = connection.createStatement()) {

               // int rowsAffected = stm.executeUpdate(sql);
               // System.out.println("Rows inserted: " + rowsAffected);

           // } catch (SQLException e) {
    //     e.printStackTrace();
    // }


    // }  catch (SQLException e) {
    //    throw new RuntimeException(e);
    // }
    //}
}















