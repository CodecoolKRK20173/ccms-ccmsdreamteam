package com.codecool.controller;

import com.codecool.dao.MentorDAO;
import com.codecool.dao.StudentDAO;
import com.codecool.model.Assignment;
import com.codecool.model.Gradeable;
import com.codecool.model.User;
import com.codecool.view.View;
import com.codecool.model.Mentor;
import com.codecool.model.Student;


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
            view.printGetOption();
            int userMenuOption = view.getUserMenuOption();

            if (userMenuOption == listStudentsOption) {
                view.printListOfUsers(mentorDAO.getUsersListByType("student"));
            } else if (userMenuOption == addAssignmentOption) {
                // TODO
            } else if (userMenuOption == gradeAssignmentOption) {
                gradeAssigment(view.getStringInput(), view.getStringInput(), view.getStringInput());
            } else if (userMenuOption == viewStudentsAssigmentOption) {
                view.printGradedAssigmentForStudent(viewStudentAssigments(view.getStringInput()));
            } else if (userMenuOption == addStudentOption) {
                User user = createUserObject("student");
                mentorDAO.addUserToDataBase(user, "students");
            } else if (userMenuOption == removeStudentOption) {
                String userToRemove = view.getStringInput("Enter  students's login to remove: ");
                mentorDAO.removeStudentFromDataBase(userToRemove);
            } else if (userMenuOption == editStudentsOption) {
                String userToEdit = view.getStringInput("Enter  student's login to edit: ");
                mentorDAO.editUser(userToEdit, "student");
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

    public void checkAttendance(){

    }

    private User createUserObject(String kindOfUser) {
        View view = new View();
        String login = view.getStringInput("Enter a login of user");
        String name = view.getStringInput("Enter a name of user");
        String surname = view.getStringInput("Enter a surname of user");
        String password = view.getStringInput("Enter a password of user");

        if (kindOfUser.equals("mentor")) {
            return new Mentor(login, name, surname, password);
        }
        else  {
            return new Student(login, name, surname, password);
        }
    }
}
