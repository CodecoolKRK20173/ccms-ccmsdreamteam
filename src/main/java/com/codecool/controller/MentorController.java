package com.codecool.controller;

import com.codecool.dao.MentorDAO;
import com.codecool.dao.MentorDAOinter;
import com.codecool.dao.StudentDAO;
import com.codecool.model.Assignment;
import com.codecool.model.Gradeable;
import com.codecool.model.User;
import com.codecool.view.View;

import java.util.ArrayList;
import java.util.List;

public class MentorController {

    private MentorDAO mentorDAO;
    private StudentDAO studentDao;
    private List<Assignment> assignmentList;
    private User mentor;
    private View view;
    private final String[] mentorMenu = {"List students",
                                         "Add assignment",
                                         "Grade assignment",
                                         "View student assigment",
                                         "Add student",
                                         "Remove student",
                                         "Edit student",
                                         "Log out"};

    public MentorController(User mentorUser) {
        this.mentorDAO = new MentorDAO();
        this.mentor = mentorUser;
        this.mentorDAO = new MentorDAO();
        this.view = new View();
        this.studentDao = new StudentDAO();

    }

    public void manageMentor() {
        int listStudentsOption = 1;
        int addAssignmentOption = 2;
        int gradeAssignmentOption = 3;
        int viewStudentsAssigmentOption = 4;
        int addStudentOption = 5;
        int removeStudentOption = 6;
        int editStudentsOption = 7;
        int logOutOption = 8;
        boolean exit = false;

        view.printWelcomeUser(mentor.toString());
        while (!exit) {
            view.printMenuForUser(this.mentorMenu);
            int userMenuOption = view.getUserMenuOption();

            if (userMenuOption == listStudentsOption) {
                // TODO
            } else if (userMenuOption == addAssignmentOption) {
                // TODO
            } else if (userMenuOption == gradeAssignmentOption) {
                gradeAssigment(view.getStringInput(), view.getStringInput(), view.getStringInput());
                // TODO
            } else if (userMenuOption == viewStudentsAssigmentOption) {
                view.printGradedAssigmentForStudent(viewStudentAssigments(view.getStringInput()));
                // TODO
            } else if (userMenuOption == addStudentOption) {
                // TODO
            } else if (userMenuOption == removeStudentOption) {
                // TODO
            } else if (userMenuOption == editStudentsOption) {
                // TODO
            } else if (userMenuOption == logOutOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }
    }

    public List viewStudentAssigments(String login){
        this.assignmentList = studentDao.loadAssignments(login);

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

    public void addAssigmen(){
        view.askMentorToSetTitle();
        String assigmentTitle = view.getStringInput();
        String assigmentLink = "";
        String status = "Waiting for submission";
        String note = "0";
        Gradeable newAssigment = new Assignment(assigmentTitle, assigmentLink, status, note);


    }

    public void gradeAssigment(String login, String assignmentTittle, String grade){

        boolean isPased = false;

        this.assignmentList = studentDao.loadAssignments(login);
        if (grade.equals("Passed")) {
            isPased = true;
        }

        for(Assignment assignment: assignmentList){
            if(assignment.getAssignmentTittle().equals(assignmentTittle)){
                assignment.setIsGraded(true);
                assignment.setGrade(isPased);
                studentDao.updateAssignment(StudentDAO.AssignmentParameters.NOTE, login, grade);
            }
        }
    }

    public void addStudent(){

    }

    public void removeStudent(){

    }

    public void editStudent(){

    }


}
