import views.Carbon;
import services.CarbonManagement;
import services.UserManagement;
import views.Menu;
import views.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static services.UserManagement.users;


public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Menu.mainMenu(scanner);

        scanner.close();

    }














}
