package com.codecool.dao;


import com.codecool.view.View;
import com.codecool.model.User;


public class BossDAO extends UsersDAO {

    public void removeMentorFromDataBase(String login) {
        String type = "mentors";
        View view = new View();
        view.printListOfUsers(super.getUsersListByType(type));
        super.removeUserFromDataBase(login, type);
    }

    public void addMentorToDataBase(User user) {
        String type = "mentors";
        super.addUserToDataBase(user, type);
    }




}




