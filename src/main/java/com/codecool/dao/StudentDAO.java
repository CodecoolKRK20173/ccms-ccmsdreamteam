
package com.codecool.dao;

import com.codecool.model.Assignment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements StudentDAOinter {

    private Document userData;

    public StudentDAO() {
        parseXMLToDocument();
    }

    private void saveToXMLDocument() {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(userData);
        StreamResult result = new StreamResult(openFile("src/main/resources/Assignment.xml"));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void parseXMLToDocument() {
        try {
            File userDataXML = openFile("src/main/resources/Assignment.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            userData = documentBuilder.parse(userDataXML);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userData.getDocumentElement().normalize();
    }

    private File openFile(String filePath) {
        return new File(filePath);
    }

    public enum AssignmentParameters {
        GIT_HUB_LINK("linkToRepository", "Waiting for revision"),
        NOTE("note", "Noted");

        private String parameter;
        private String newStatus;

        AssignmentParameters(String parameter, String newStatus) {
            this.parameter = parameter;
            this.newStatus = newStatus;
        }

        public String getTextContent() {
            return this.parameter;
        }

        public String getStatus() {
            return this.newStatus;
        }
    }

    public void updateAssignment(AssignmentParameters assignmentParameter, String login, String newEntry) {
        String assignmentParameterString = assignmentParameter.getTextContent();
        NodeList students = userData.getElementsByTagName("student");
        Element student = searchForStudent(students, login);
        if (student !=null) {
            NodeList studentAssignments = student.getElementsByTagName("assignment");
            for (int i=0; i<studentAssignments.getLength(); i++) {
                Node studentNode = studentAssignments.item(i);
                if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) studentNode;
                    studentElement.getElementsByTagName(assignmentParameterString).item(0).setTextContent(newEntry);
                    studentElement.setAttribute("status", assignmentParameter.getStatus());
                    saveToXMLDocument();
                }
            }
        }
    }

    public List<Assignment> loadAssignments(String login) {
        List<Assignment> assignmentList = new ArrayList<>();
        NodeList students = userData.getElementsByTagName("student");
        Element student = searchForStudent(students, login);
        if (student != null) {
            NodeList studentAssignments = student.getElementsByTagName("assignment");
            deserializeToAssignments(assignmentList, studentAssignments);
        }
        return assignmentList;
    }

    private void deserializeToAssignments(List<Assignment> assignmentList, NodeList studentAssignments) {
        for (int j = 0; j < studentAssignments.getLength(); j++) {
            Node studentAssignment = studentAssignments.item(j);
            if (studentAssignment.getNodeType() == Node.ELEMENT_NODE) {
                Element studentAssignmentElement = (Element) studentAssignment;
                deserializeToAssignment(assignmentList, studentAssignmentElement);
            }
        }
    }

    private Element searchForStudent(NodeList students, String login) {
        Element studentElement = null;
        for (int i=0; i<students.getLength(); i++) {
            Node student = students.item(i);
            if (student.getNodeType() == Node.ELEMENT_NODE) {
                studentElement = (Element) student;
                if (studentElement.getAttribute("login").equals(login)) {
                    return studentElement;
                }
            }
        }
        return studentElement;
    }

    private void deserializeToAssignment(List<Assignment> assignmentList, Element studentAssignmentElement) {
        String assignmentName = studentAssignmentElement.getAttribute("name");
        String status = studentAssignmentElement.getAttribute("status");
        String linkToRepository = studentAssignmentElement.getElementsByTagName("linkToRepository").
                                                           item(0).
                                                           getTextContent();
        String note = studentAssignmentElement.getElementsByTagName("note").
                                               item(0).
                                               getTextContent();
        addAssignmentToList(assignmentList, assignmentName, status, linkToRepository, note);
    }

    private void addAssignmentToList(List<Assignment> assignmentList,
                                     String assignmentName,
                                     String status,
                                     String linkToRepository,
                                     String note) {
        assignmentList.add(new Assignment(assignmentName, linkToRepository, status, note));
    }
}
