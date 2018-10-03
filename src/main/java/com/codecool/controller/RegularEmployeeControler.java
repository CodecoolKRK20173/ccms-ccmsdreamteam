package com.codecool.controller;

import com.codecool.dao.RegularEmployeeDAO;
import com.codecool.dao.RegularEmployeeDAOinter;
import com.codecool.model.Student;
import com.codecool.model.User;
import  com.codecool.view.View;

import java.util.ArrayList;
import java.util.List;

public class RegularEmployeeControler {
    private RegularEmployeeDAOinter regularEmployeeDAO;
    private User regularEmployee;
    private View view;

    public RegularEmployeeControler(User regularEmployeeUser) {
        this.regularEmployee = regularEmployeeUser;
        this.regularEmployeeDAO = new RegularEmployeeDAO();
        this.view = new View();
    }

    public void manageRegularEmployee() {
        List<List<String>> listOfStudents;
        int seeListOfStudents = 1;
        int logOutOption = 2;
        boolean exit = false;

        view.printWelcomeUser(regularEmployee.toString());
        while (!exit) {
            view.printRegularEmployeeMenu();
            int userMenuOption = view.getUserMenuOption();
            if (userMenuOption == seeListOfStudents) {
                // listOfStudents = getListOfStudents(egularEmployeeDAO.getStudents());
                // view.printListOfStudents(listOfStudents);
            } else if (userMenuOption == logOutOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }
    }
    private List<List<String>> getListOfStudents(List<Student> students) {
        List<List<String>> listOfStudents = new ArrayList<>();

        for (Student student : students) {
            // listOfStudents.add(student.getName());
            // listOfStudents.add(student.getSubname());
        }
        return listOfStudents;
    }
}
