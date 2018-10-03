package com.codecool.model;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;

public class StudentList {

    private String nameXmlFile = "src/main/resources/UserData.xml";
    private List<Student> listOfStudent;

    public void loadStudentFromXml(){
        try {
            File productFile = new File(nameXmlFile);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(productFile);

            doc.getDocumentElement().normalize();

            String users = doc.getDocumentElement().getNodeName();

            NodeList mentorsList = doc.getElementsByTagName("mentors");
            NodeList mentorElementsList = doc.getElementsByTagName("mentor");

            for (int i = 0; i < mentorElementsList.getLength(); i++){
                Node nNode = mentorElementsList.item(i);




            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
