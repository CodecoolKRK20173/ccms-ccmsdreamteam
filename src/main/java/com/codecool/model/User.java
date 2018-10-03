package com.codecool.model;

public abstract class User {

    String login;
    String name;
    String surname;
    String password;

    public User(String login, String name, String surname, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.surname = surname;

    }

    public User() {

    }

    public String getNameOfUser() {
        return this.name;
    }

    public String getSurnameOfUser() {
        return this.surname;
    }



}
