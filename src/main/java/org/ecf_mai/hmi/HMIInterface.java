package org.ecf_mai.hmi;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface HMIInterface {
    void start();
    default int promptInteger() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return Integer.parseInt(scanner.nextLine());
            } catch (IllegalStateException | InputMismatchException e) {
                System.out.println("Please enter an integer");
            }
        }
    }
    default double promptDouble() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Please enter a double");
            }
        }
    }
    default String promptStringNotEmpty() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String promptResult = scanner.nextLine();
                if (promptResult.isEmpty())
                    throw new RuntimeException("String should not be empty");
                return promptResult;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
