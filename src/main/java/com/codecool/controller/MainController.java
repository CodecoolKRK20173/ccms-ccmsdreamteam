package com.codecool.controller;

import com.codecool.model.*;
import com.codecool.view.View;

public class MainController {
    private LoginController loginController;
    private View view;
    private final String[] mainMenu = {"Log in",
                                          "Exit"};

    public MainController() {
        loginController = new LoginController();
        this.view = new View();
    }

    public void run() {
        int logInOption = 1;
        int exitOption = 2;
        boolean exit = false;

        while (!exit) {
            view.printHeader();
            view.printMenuForUser(mainMenu);
            view.printGetOption();
            int userMenuOption = view.getUserMenuOption();
            if (userMenuOption == logInOption) {
                manageUserSession();
            } else if (userMenuOption == exitOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }
    }
    private void manageUserSession() {
        String login = validateUserPassword();
        createUserSession(login);
    }
    private String validateUserPassword() {
        String userLogin = "";
        boolean isLoginPasswordCorrect = false;

        while (!isLoginPasswordCorrect) {
            view.printInputLogin();
            userLogin = view.getUserLogin();
            view.printInputPassword();
            String userPassword = view.getUserPassword();
            isLoginPasswordCorrect = loginController.checkUserExistence(userLogin, userPassword);
            if (!isLoginPasswordCorrect) {
                view.printUserLoginPasswordError();
            }
        }
        return userLogin;
    }
    private void createUserSession(String login) {
        User user = loginController.getUser(login);

        if (user instanceof Boss) {
            new BossController(user).manageBoss();
        } else if (user instanceof Mentor) {
            new MentorController(user).manageMentor();
        } else if (user instanceof RegularEmployee) {
            new RegularEmployeeControler(user).manageRegularEmployee();
        } else if (user instanceof Student) {
            new StudentController(user).manageStudent();
        }
    }
}

