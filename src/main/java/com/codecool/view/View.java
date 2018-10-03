package com.codecool.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    public void printMainMenu() {
        printHeader();
        System.out.println("1.Log In\n2.Exit");
        printGetOption();
    }
    private void printHeader() {
        System.out.println("CcMS - Codecool Management System");
    }
    private void printGetOption() {
        System.out.print("Choose option: ");
    }
    public int getUserMenuOption() {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean isNumber = false;

        while (!isNumber) {
            try {
                userInput = scanner.nextInt();
                isNumber = true;
            } catch (InputMismatchException e) {
                printErrorInputMessage();
                scanner.next();
            }
        }
        return userInput;
    }
    public void printErrorInputMessage() {
        System.out.println("Provide proper input!");
    }
}
