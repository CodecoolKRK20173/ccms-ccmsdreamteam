package com.codecool.dao;

import com.codecool.model.Mentor;
import com.codecool.model.User;
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

public class BossDAO implements BossDAOinter{


    public List<User> getMentorsList() {

        List<User> mentorsList = new ArrayList<User>();

        Element root = prepareRootElement();
        NodeList nodes = root.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {

            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                NodeList mentors = element.getElementsByTagName("mentor");

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
            }catch (SAXException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return doc.getDocumentElement();
        }


//        public void addUser(User userToAdd, String typeOfUser) {
//
//            Document doc = createDocumentElement();
//            Element rootElement = doc.createElement(typeOfUser);
//
//            rootElement.setAttribute("login", userToAdd.getLogin());
//
//            doc.appendChild(rootElement);
//
//            Element name = doc.createElement("name");
//            name.setTextContent(userToAdd.getNameOfUser());
//
//            rootElement.appendChild(name);
//
//            Element surname = doc.createElement("surname");
//            surname.setTextContent(userToAdd.getSurnameOfUser());
//
//            rootElement.appendChild(surname);
//
//            Element password = doc.createElement("password");
//            password.setTextContent(userToAdd.getPassword());
//
//            rootElement.appendChild(password);
//
////            createXmlFile(doc);
//
//
//
//
//        }
//
//        private Document createDocumentElement() {
//
//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = null;
//            try {
//                docBuilder = docFactory.newDocumentBuilder();
//            } catch (ParserConfigurationException e) {
//                e.printStackTrace();
//            }
//
//            return docBuilder.newDocument();
//
//        }
//
//        private void createXmlFile(Document doc) {
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = null;
//            try {
//                transformer = transformerFactory.newTransformer();
//            } catch (TransformerConfigurationException e) {
//                e.printStackTrace();
//            }
//            DOMSource source = new DOMSource(doc);
//            StreamResult result = new StreamResult(new File("src/main/resources/Test.xml"));
//
//            try {
//                transformer.transform(source, result);
//            } catch (TransformerException e) {
//                e.printStackTrace();
//            }
//        }

        public void addUserToDataBase(User userToAdd, String typeOfUser) {

            Document document = createDocumentElement();
            Element root = document.getDocumentElement();
            Element userType = (Element) root.getElementsByTagName(typeOfUser).item(0);



            Element newUser = document.createElement(typeOfUser);
            newUser.setAttribute("login", userToAdd.getLogin());

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(userToAdd.getNameOfUser()));
            newUser.appendChild(name);

            Element surname = document.createElement("surname");
            surname.appendChild(document.createTextNode(userToAdd.getSurnameOfUser()));
            newUser.appendChild(surname);

            Element password = document.createElement("password");
            password.appendChild(document.createTextNode(userToAdd.getPassword()));
            newUser.appendChild(password);

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

            Element users = document.getDocumentElement();
            NodeList usersList = users.getElementsByTagName(type);

            for (int i = 0; i < usersList.getLength(); i++) {

                Node node = usersList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {


                    Element toRemove = (Element) node;
                     if (toRemove.getTagName().equals(type)) {

                         if (toRemove.hasAttribute("login")) {


                             if (toRemove.getAttribute("login").equals(login)) {
                                 System.out.println("weszlo");
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
        }






}
