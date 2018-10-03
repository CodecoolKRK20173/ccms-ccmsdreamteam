package com.codecool.controller;

import com.codecool.dao.BossDAO;
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
            int userMenuOption = view.getUserMenuOption();

            if (userMenuOption == addMentorOption) {
                // TODO
            } else if (userMenuOption == removeMentorOption) {
                // TODO
            } else if (userMenuOption == editMentorOption) {
                // TODO
            } else if (userMenuOption == listMentorsOption) {
                // TODO
            } else if (userMenuOption == listStudentsOption) {
                // TODO
            } else if (userMenuOption == logOutOption) {
                exit = true;
            } else {
                view.printErrorInputMessage();
            }
        }
    }

}

