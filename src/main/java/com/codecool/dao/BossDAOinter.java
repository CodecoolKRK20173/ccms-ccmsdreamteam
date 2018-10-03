package com.codecool.dao;

import com.codecool.model.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import javax.xml.parsers.*;


public interface BossDAOinter {

    public List<User> getMentorsList();


}
