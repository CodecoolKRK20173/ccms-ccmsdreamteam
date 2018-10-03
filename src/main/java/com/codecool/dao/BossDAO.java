package com.codecool.dao;

import com.codecool.model.Mentor;
import com.codecool.model.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

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
                    if (nodeMentor instanceof Element) {
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



}
