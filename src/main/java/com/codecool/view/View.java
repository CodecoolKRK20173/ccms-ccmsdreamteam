package com.codecool.view;

import com.codecool.model.User;

import java.util.List;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class View {


    public void printMenuForUser(String[] userMenu){
        for (int i = 0; i < userMenu.length; i++) {
            System.out.println("(" + (i + 1) + ") " + userMenu[i]);
        }
    }

    public void printListOfUsers(List<User> list) {
        int num = 1;
        for (User user : list) {
            System.out.println("(" + num + ") " + user.getNameOfUser() + " " + user.getSurnameOfUser());
            num ++;
        }
    }



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
    public void printWelcomeUser(String user) {
        System.out.println("Welcome" + user + "!");
    }

}
