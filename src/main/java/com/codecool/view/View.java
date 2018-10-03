package com.codecool.view;

import com.codecool.model.User;

import java.util.List;

public class View {


    public void printMenuForUser(String[] userMenu){
        for (int i = 0; i < userMenu.length; i++) {
            System.out.println("(" + (i + 1) + ") " + userMenu[i]);
        }
    }

    public void printListOfUsers(List<User> list) {
        int num = 1;
        for (User user : list) {
            System.out.println("(" + num + ") " + user.getNameOfUser() + " " + user.getSurnameOfUser());
            num ++;
        }
    }


}
