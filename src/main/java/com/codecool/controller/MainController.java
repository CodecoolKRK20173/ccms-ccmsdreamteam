package com.codecool.controller;

import com.codecool.dao.BossDAO;
import com.codecool.view.View;

public class MainController {

    public void run() {
        BossDAO dao = new BossDAO();
        View view = new View();
        view.printListOfUsers(dao.getMentorsList());

    }
}

