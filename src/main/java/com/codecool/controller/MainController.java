package com.codecool.controller;

import com.codecool.model.Boss;
import com.codecool.model.Mentor;
import com.codecool.model.RegularEmployee;
import com.codecool.model.Student;
import com.codecool.view.View;

public class MainController {
    private View view;

    public MainController() {
        view = new View();
    }

    public void run() {
        int logInOption = 1;
        int exitOption = 2;
        boolean exit = false;

        while (!exit) {
            view.printMainMenu();
            int userMenuOption = view.getUserMenuOption();
            if (userMenuOption == logInOption) {
                manageUser();
            } else if (userMenuOption == exitOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }
    }
    private void manageUser() {
//        User user = new LoginController().getUser();
//        if (user instanceof Boss) {
//            new BossController(user).manageBoss();
//        } else if (user instanceof Mentor) {
//            new MainController(user).manageMentor();
//        } else if (user instanceof RegularEmployee) {
//            new RegularEmployeeController(user).manageRegularEmployee();
//        } else if (user instanceof Student) {
//            new StudentController(user).manageStudent();
//        }
    }
}

