package com.codecool.controller;

import com.codecool.dao.StudentDAO;
import com.codecool.model.Assignment;
import com.codecool.model.User;
import com.codecool.view.View;

import java.util.ArrayList;
import java.util.List;

public class StudentController {

    private StudentDAO studentDAO;
    private User student;
    private View view;
    private final String[] studentMenu = {"Submit assignment",
                                          "View grades",
                                          "Log out"};

    public StudentController(User studentUser) {
        this.student = studentUser;
        this.studentDAO = new StudentDAO();
        this.view = new View();
    }

    public void manageStudent() {
        int submitAssignmentOption = 1;
        int viewGradesOption = 2;
        int logOutOption = 3;
        boolean exit = false;

        view.printWelcomeUser(student.toString());
        while (!exit) {
            view.printMenuForUser(studentMenu);
            int userMenuOption = view.getUserMenuOption();

            if (userMenuOption == submitAssignmentOption) {
                // TODO
            } else if (userMenuOption == viewGradesOption) {
                // TODO
            } else if (userMenuOption == logOutOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }
    }

    List<Assignment> assignmentList = new ArrayList<>();

    public void submitAssigment(String assignmentTittle, String assignmentLink) {
        Assignment assignment = new Assignment(assignmentTittle, assignmentLink);
        assignmentList.add(assignment);
    }

    public List getDataAssigmentList() {
        List<String> dataAssignmentList = new ArrayList<>();
        for (Assignment assignment : assignmentList) {
            dataAssignmentList.add(assignment.getAssignmentTittle());
            dataAssignmentList.add(assignment.getAssignmentLink());
            if (assignment.getIsGraded()) {
                dataAssignmentList.add("Graded");
                if (assignment.getGrade()) {
                    dataAssignmentList.add("Passed");
                } else {
                    dataAssignmentList.add("Not passed");
                }

            } else {
                dataAssignmentList.add("Not Graded");
            }
        }

        return dataAssignmentList;

    }
}
