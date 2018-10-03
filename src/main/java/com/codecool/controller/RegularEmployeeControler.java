package com.codecool.controller;

import com.codecool.dao.RegularEmployeeDAO;
import com.codecool.dao.RegularEmployeeDAOinter;
import com.codecool.model.User;
import  com.codecool.view.View;

import java.util.List;

public class RegularEmployeeControler {
    private RegularEmployeeDAOinter regularEmployeeDAO;
    private User regularEmployee;
    private View view;
    private final String[] regularEmployeeMenu = { "List students",
                                                   "Log out"};

    public RegularEmployeeControler(User regularEmployeeUser) {
        this.regularEmployee = regularEmployeeUser;
        this.regularEmployeeDAO = new RegularEmployeeDAO();
        this.view = new View();
    }

    public void manageRegularEmployee() {
        List<User> listOfStudents;
        int seeListOfStudents = 1;
        int logOutOption = 2;
        boolean exit = false;

        view.printWelcomeUser(regularEmployee.toString());
        while (!exit) {
            view.printMenuForUser(this.regularEmployeeMenu);
            int userMenuOption = view.getUserMenuOption();
            if (userMenuOption == seeListOfStudents) {
                listOfStudents = regularEmployeeDAO.getListOfStudent();
                view.printListOfUsers(listOfStudents);
            } else if (userMenuOption == logOutOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }
    }
}
