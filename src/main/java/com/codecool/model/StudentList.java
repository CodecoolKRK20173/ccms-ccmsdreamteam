package com.codecool.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

            NodeList studentsList = doc.getElementsByTagName("students");
            NodeList studentElementsList = doc.getElementsByTagName("student");

            for (int i = 0; i < studentElementsList.getLength(); i++){
                Node nNode = studentElementsList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String id = eElement.getAttribute("ID");
                    String studentName = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String studentSurname = eElement.getElementsByTagName("surname").item(0).getTextContent();
                    String studentPassword = eElement.getElementsByTagName("password").item(0).getTextContent();

                    Student student = new Student();
                }




            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
