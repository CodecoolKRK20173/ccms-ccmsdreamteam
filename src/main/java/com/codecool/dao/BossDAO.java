package com.codecool.dao;

import com.codecool.model.Mentor;
import com.codecool.model.Student;
import com.codecool.model.User;
import com.codecool.view.View;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BossDAO implements BossDAOinter {


    public List<User> getUsersListByType(String typeOfUser) {

        List<User> mentorsList = new ArrayList<User>();

        Element root = prepareRootElement();
        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {

            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                NodeList mentors = element.getElementsByTagName(typeOfUser);

                for (int j = 0; j < mentors.getLength(); j++) {

                    Node nodeMentor = mentors.item(j);

                    if (nodeMentor.getNodeType() == Node.ELEMENT_NODE) {


                        Element mentorData = (Element) nodeMentor;

                        String login = mentorData.getAttribute("login");
                        String name = mentorData.getElementsByTagName("name").item(0).getTextContent();
                        String surname = mentorData.getElementsByTagName("surname").item(0).getTextContent();
                        String password = mentorData.getElementsByTagName("password").item(0).getTextContent();

                        mentorsList.add(new Mentor(login, name, surname, password));
                    }
                }
            }
        }
        return mentorsList;
    }


    private Element prepareRootElement() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        File file = new File("src/main/resources/UserData.xml");
        Document doc = null;
        try {
            doc = builder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc.getDocumentElement();
    }


    public void addUserToDataBase(User userToAdd, String typeOfUser) {

        View view = new View();

        if (!checkIfLoginExist(userToAdd.getLogin())) {


            Document document = createDocumentElement();
            Element root = document.getDocumentElement();

            Element userType = (Element) root.getElementsByTagName(typeOfUser).item(0);

            typeOfUser = typeOfUser.substring(0, typeOfUser.length() - 1);


            Element newUser = document.createElement(typeOfUser);
            newUser.setAttribute("login", userToAdd.getLogin());

            newUser.appendChild(document.createTextNode("\n"));

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(userToAdd.getNameOfUser()));
            newUser.appendChild(name);

            newUser.appendChild(document.createTextNode("\n"));

            Element surname = document.createElement("surname");
            surname.appendChild(document.createTextNode(userToAdd.getSurnameOfUser()));
            newUser.appendChild(surname);

            newUser.appendChild(document.createTextNode("\n"));

            Element password = document.createElement("password");
            password.appendChild(document.createTextNode(userToAdd.getPassword()));
            newUser.appendChild(password);

            newUser.appendChild(document.createTextNode("\n"));

            userType.appendChild(newUser);




            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = transformerFactory.newTransformer();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            }
            StreamResult result = new StreamResult("src/main/resources/UserData.xml");
            try {
                transformer.transform(source, result);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }
        else view.printMessage("This login is occupied!");
    }

    private Document createDocumentElement() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder.parse("src/main/resources/UserData.xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }


    public void removeUserFromDataBase(String login, String type) {
        View view = new View();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            document = dbf.newDocumentBuilder().parse("src/main/resources/UserData.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        if (checkIfLoginExist(login)) {
            Element users = document.getDocumentElement();
            NodeList usersList = users.getElementsByTagName(type);

            for (int i = 0; i < usersList.getLength(); i++) {

                Node node = usersList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {


                    Element toRemove = (Element) node;
                    if (toRemove.getTagName().equals(type)) {

                        if (toRemove.hasAttribute("login")) {


                            if (toRemove.getAttribute("login").equals(login)) {

                                type = type + "s";

                                Element countOfRemove = (Element) users.getElementsByTagName(type).item(0);
                                countOfRemove.removeChild(node);
                            }
                        }
                    }
                }
            }

            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = transformerFactory.newTransformer();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            }
            StreamResult result = new StreamResult("src/main/resources/UserData.xml");
            try {
                transformer.transform(source, result);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } else view.printMessage("There isn't user with such login!");
    }


    public void editUser(String loginUserToEdit, String type) {
        View view = new View();

        if (checkIfLoginExist(loginUserToEdit)) {

            removeUserFromDataBase(loginUserToEdit, type);

            String login = view.getStringInputFromUser("Edit user's login");
            String name = view.getStringInputFromUser("Edit user's name");
            String surname = view.getStringInputFromUser("Edit user's surname");
            String password = view.getStringInputFromUser("Edit user's password");
            if (type.equals("mentor")) {
                type = type + "s";
                addUserToDataBase(new Mentor(login, name, surname, password), type);
            } else if (type.equals("student")) {
                type = type + "s";
                addUserToDataBase(new Student(login, name, surname, password), type);
            }
        } else view.printMessage("There isn't user with such login!");
    }


    private boolean checkIfLoginExist(String login) {

        Document document = createDocumentElement();
        Element root = document.getDocumentElement();
        NodeList nodelist = root.getChildNodes();

        for (int i = 0; i < nodelist.getLength(); i++) {

            Node node = nodelist.item(i);

            System.out.println(node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element nodeElement = (Element) node;

                NodeList usersNodes = nodeElement.getChildNodes();

                for (int j = 0; j < usersNodes.getLength(); j++) {

                    Node userNode = usersNodes.item(j);

                    if (userNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element userElement = (Element) userNode;
                        if (userElement.getAttribute("login").equals(login)) {
                            return true;
                        }
                    }

                }

            }
        }
        return false;
    }



}




