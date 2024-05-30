package org.ecf_mai.hmi;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public interface HMIInterface {
    void start();
    default int promptInteger() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NoSuchElementException | NumberFormatException e) {
                System.out.println("Please enter an integer");
            }
        }
    }
    default double promptDouble() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Please enter a double");
            }
        }
    }
    default String promptStringNotEmpty() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                String promptResult = scanner.nextLine();
                if (promptResult.isEmpty()) throw new RuntimeException("String should not be empty");
                return promptResult;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
