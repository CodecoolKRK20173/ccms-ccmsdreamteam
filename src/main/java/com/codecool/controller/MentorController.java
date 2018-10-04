package com.codecool.controller;

import com.codecool.dao.MentorDAO;
import com.codecool.dao.MentorDAOinter;
import com.codecool.model.*;
import com.codecool.view.View;
import com.codecool.dao.UsersDAOinter;

public class MentorController {

    private UsersDAOinter mentorDAO;
    private User mentor;
    private View view;
    private final String[] mentorMenu = {"List students",
                                         "Add assignment",
                                         "Grade assignment",
                                         "Check attendance",
                                         "Add student",
                                         "Remove student",
                                         "Edit student",
                                         "Log out"};

    public MentorController(User mentorUser) {
        this.mentorDAO = new MentorDAO();
        this.mentor = mentorUser;

        this.view = new View();
    }

    public void manageMentor() {
        int listStudentsOption = 1;
        int addAssignmentOption = 2;
        int gradeAssignmentOption = 3;
        int checkAttendanceOption = 4;
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
                view.printListOfUsers(mentorDAO.getUsersListByType("student"));
            } else if (userMenuOption == addAssignmentOption) {
                // TODO
            } else if (userMenuOption == gradeAssignmentOption) {
                // TODO
            } else if (userMenuOption == checkAttendanceOption) {
                // TODO
            } else if (userMenuOption == addStudentOption) {
                User user = createUserObject("student");
                mentorDAO.addUserToDataBase(user, "students");
            } else if (userMenuOption == removeStudentOption) {
                String userToRemove = view.getStringInputFromUser("Enter  students's login to remove: ");
                mentorDAO.removeUserFromDataBase(userToRemove, "student");
            } else if (userMenuOption == editStudentsOption) {
                String userToEdit = view.getStringInputFromUser("Enter  student's login to edit: ");
                mentorDAO.editUser(userToEdit, "student");
            } else if (userMenuOption == logOutOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }
    }

    public void addAssigmen(){
        view.askMentorToSetTitle();
        String assigmentTitle = view.getStringInput();
        String assigmentLink = "";
        String status = "Waiting for submission";
        String note = "0";
        Gradeable newAssigment = new Assignment(assigmentTitle, assigmentLink, status, note);



    }

    public boolean gradeAssigment(boolean isPositiveGrade){
        return isPositiveGrade;
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
        String login = view.getStringInputFromUser("Enter a login of user");
        String name = view.getStringInputFromUser("Enter a name of user");
        String surname = view.getStringInputFromUser("Enter a surname of user");
        String password = view.getStringInputFromUser("Enter a password of user");

        if (kindOfUser.equals("mentor")) {
            return new Mentor(login, name, surname, password);
        }
        else  {
            return new Student(login, name, surname, password);
        }
    }
}
