package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {

    private static Scanner scanner = new Scanner(System.in);



    public static int readInt(String prompt) {
        int value = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : La valeur doit être un nombre entier.");
                scanner.next();
            }
        }
        return value;
    }
}
