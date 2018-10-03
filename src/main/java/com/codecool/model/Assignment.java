package com.codecool.model;

public class Assignment implements Gradeable {

    private String assignmentTittle;
    private String assignmentLink;
    private boolean isGraded;
    private boolean grade;

    public Assignment(String assignmentTittle, String assignmentLink) {
        this.assignmentTittle = assignmentTittle;
        this.assignmentLink = assignmentLink;
    }

    public Assignment(String assignmentTittle) {
        this.assignmentTittle = assignmentTittle;
    }

    @Override
    public String getAssignmentTittle() {
        return assignmentTittle;
    }

    public void setAssignmentTittle(String assignmentTittle) {
        this.assignmentTittle = assignmentTittle;
    }

    @Override
    public String getAssignmentLink() {
        return assignmentLink;
    }

    public void setAssignmentLink(String assignmentLink) {
        this.assignmentLink = assignmentLink;
    }

    @Override
    public boolean getIsGraded() {
        return isGraded;
    }

    public void setIsGraded(boolean isGraded) {
        this.isGraded = isGraded;
    }

    @Override
    public boolean getGrade() {
        return grade;
    }

    public void setGrade(boolean grade) {
        this.grade = grade;
    }
}
