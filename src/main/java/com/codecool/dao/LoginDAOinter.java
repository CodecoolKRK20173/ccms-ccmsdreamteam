package com.codecool.dao;

import java.util.List;

public interface LoginDAOinter {

    boolean checkPassword(String login, String potentialPassword);
    List<String> getUserDataList(String login);

}
