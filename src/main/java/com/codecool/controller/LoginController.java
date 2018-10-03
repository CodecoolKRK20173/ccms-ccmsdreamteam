package com.codecool.controller;

import com.codecool.dao.LoginDAO;
import com.codecool.dao.LoginDAOinter;
import com.codecool.model.*;

import java.util.List;

public class LoginController {
    private LoginDAOinter loginDao;

    public LoginController() {
        loginDao = new LoginDAO();
    }

    public boolean checkUserExistence (String userLogin, String userPassword) {
        return loginDao.checkPassword(userLogin, userPassword);
    }
    public User getUser(String login) {
        List<String> userParameters = loginDao.getUserDataList(login);
        String userType = userParameters.get(0);
        String userLogin = userParameters.get(1);
        String userName = userParameters.get(2);
        String userSurname = userParameters.get(3);
        String userPassword = userParameters.get(4);
        User user = new Student(userLogin, userName, userSurname, userPassword);    // Should not be initialized, but must be initialized:(

        if (userType.equals("boss")) {
            user = new Boss(userLogin, userName, userSurname, userPassword);
        } else if (userType.equals("mentor")) {
            user = new Mentor(userLogin, userName, userSurname, userPassword);
        } else if (userType.equals("student")) {
            user = new Student(userLogin, userName, userSurname, userPassword);
        } else if (userType.equals("regular")) {
            user = new RegularEmployee(userLogin, userName, userSurname, userPassword);
        }
        return user;
    }
}
