package com.codecool.model;

public abstract class Employee extends User {

    public Employee(String login, String name, String surname, String password) {
        super(login, name, surname, password);
    }
}
