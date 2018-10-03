package com.codecool.controller;

import com.codecool.model.Assignment;

import java.util.ArrayList;
import java.util.List;

public class StudentController {

    List<Assignment> assignmentList = new ArrayList<>();

    public void submitAssigment(String assignmentTittle, String assignmentLink) {
        Assignment assignment = new Assignment(assignmentTittle, assignmentLink);
        assignmentList.add(assignment);
    }

    public List getDataAssigmentList() {
        List<String> dataAssignmentList = new ArrayList<>();
        for (Assignment assignment : assignmentList) {
            dataAssignmentList.add(assignment.getAssignmentTittle());
            dataAssignmentList.add(assignment.getAssignmentLink());
            if (assignment.getIsGraded()) {
                dataAssignmentList.add("Graded");
                if (assignment.getGrade()) {
                    dataAssignmentList.add("Passed");
                } else {
                    dataAssignmentList.add("Not passed");
                }

            } else {
                dataAssignmentList.add("Not Graded");
            }
        }

        return dataAssignmentList;

    }
}
