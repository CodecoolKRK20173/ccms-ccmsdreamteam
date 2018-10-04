package com.codecool.controller;

import com.codecool.controller.BossController;
import com.codecool.controller.MentorController;
import com.codecool.controller.RegularEmployeeControler;
import com.codecool.controller.StudentController;
import com.codecool.dao.BossDAO;
import com.codecool.model.*;
import com.codecool.view.View;

import java.util.Scanner;

public class MainController {
    private LoginController loginController;
    private View view;

    public MainController() {
        loginController = new LoginController();
        this.view = new View();
    }

    public void run() {
        int logInOption = 1;
        int exitOption = 2;
        boolean exit = false;

        while (!exit) {
            view.printMainMenu();
            int userMenuOption = view.getUserMenuOption();
            if (userMenuOption == logInOption) {
                manageUserSession();
            } else if (userMenuOption == exitOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }

//        BossDAO dao = new BossDAO();
//        View view = new View();
//        view.printListOfUsers(dao.getUsersListByType("mentor"));
//
////        dao.removeUserFromDataBase("mentor1", "mentor");
//
////        dao.addUserToDataBase(new Mentor("test", "Staszek", "Wyrobek", "wow"), "mentors");
//
//        String userToEdit = view.getStringInputFromUser("Enter a login user's to edit: ");
//        dao.editUser(userToEdit, "mentor");
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

