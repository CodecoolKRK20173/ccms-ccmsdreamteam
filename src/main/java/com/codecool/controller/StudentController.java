package com.codecool.controller;

import com.codecool.dao.StudentDAO;
import com.codecool.model.Assignment;
import com.codecool.model.User;
import com.codecool.view.View;

import java.util.ArrayList;
import java.util.List;

public class StudentController {

    private  List<Assignment> assignmentList;
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
        this.assignmentList = studentDAO.loadAssignments(student.getLogin());
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
                view.printGradedAssigmentForStudent(getDataAssignmentList());
                view.printGetTitle();
                String assignmentTitle = view.getStringInput();
                view.printGetLink();
                String assignmentLink = view.getStringInput();
                submitAssignment(assignmentTitle, assignmentLink);
            } else if (userMenuOption == viewGradesOption) {
                view.printGradedAssigmentForStudent(getDataAssignmentList());
            } else if (userMenuOption == logOutOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }
    }

    public void submitAssignment(String assignmentTitle, String assignmentLink) {

        for(Assignment assignment: assignmentList){
            if(assignment.getAssignmentTittle().equals(assignmentTitle)){
                assignment.setAssignmentLink(assignmentLink);
                studentDAO.updateAssignment(StudentDAO.AssignmentParameters.GIT_HUB_LINK, student.getLogin(), assignmentLink);
            }
        }
    }

    public List getDataAssignmentList() {
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
                dataAssignmentList.add("Not passed");
            }
        }

        return dataAssignmentList;

    }
}


