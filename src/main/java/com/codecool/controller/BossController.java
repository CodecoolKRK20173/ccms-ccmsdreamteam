package com.codecool.controller;

import com.codecool.dao.BossDAO;
import com.codecool.model.Mentor;
import com.codecool.model.Student;
import com.codecool.model.User;
import com.codecool.view.View;

public class BossController {

    private final String[] bossMenu = {"Add mentor",
                                       "Remove mentor",
                                       "Edit mentor",
                                       "List mentors",
                                       "List students",
                                       "Log out"};

    private BossDAO bossDAO;
    private User boss;
    private View view;

    public BossController(User bossUser) {
        this.boss = bossUser;
        this.bossDAO = new BossDAO();
        this.view = new View();
    }

    public void manageBoss() {
        int addMentorOption = 1;
        int removeMentorOption = 2;
        int editMentorOption = 3;
        int listMentorsOption = 4;
        int listStudentsOption = 5;
        int logOutOption = 6;
        boolean exit = false;

        view.printWelcomeUser(boss.toString());
        while (!exit) {
            view.printMenuForUser(this.bossMenu);
            view.printGetOption();
            int userMenuOption = view.getUserMenuOption();

            if (userMenuOption == addMentorOption) {
                User user = createUserObject("mentor");
                bossDAO.addMentorToDataBase(user);
            } else if (userMenuOption == removeMentorOption) {
                String userToRemove = view.getStringInput("Enter  mentor's login to remove: ");
                bossDAO.removeMentorFromDataBase(userToRemove);
            } else if (userMenuOption == editMentorOption) {
                String userToEdit = view.getStringInput("Enter  mentor's login to edit: ");
                bossDAO.editUser(userToEdit, "mentor");
            } else if (userMenuOption == listMentorsOption) {
                view.printListOfUsers(bossDAO.getUsersListByType("mentor"));
            } else if (userMenuOption == listStudentsOption) {
                view.printListOfUsers(bossDAO.getUsersListByType("student"));
            } else if (userMenuOption == logOutOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }
    }

    private User createUserObject(String kindOfUser) {
        View view = new View();
        String login = view.getStringInput("Enter a login of user: ");
        String name = view.getStringInput("Enter a name of user: ");
        String surname = view.getStringInput("Enter a surname of user: ");
        String password = view.getStringInput("Enter a password of user: ");

        if (kindOfUser.equals("mentor")) {
           return new Mentor(login, name, surname, password);
        }
        else  {
            return new Student(login, name, surname, password);
        }
    }

}

