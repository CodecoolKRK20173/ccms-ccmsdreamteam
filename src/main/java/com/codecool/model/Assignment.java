package com.codecool.model;

public class Assignment implements Gradeable {

    private String assigmentTittle;
    private String assigmentLink;
    private boolean isGraded;
    private boolean grade;

    @Override
    public String getAssignmentTittle() {
        return assigmentTittle;
    }

    public void setAssigmentTittle(String assigmentTittle){
        this.assigmentTittle = assigmentTittle;
    }

    @Override
    public String getAssignmentLink() {
        return assigmentLink;
    }

    public  void setAssigmentLink(String assigmentLink){
        this.assigmentLink = assigmentLink;
    }

    @Override
    public boolean getIsGraded() {
        return isGraded;
    }

    public void setIsGraded(boolean isGraded){
        this.isGraded = isGraded;
    }

    @Override
    public boolean getGrade() {
        return grade;
    }

    public void setGrade(boolean grade){
        this.grade = grade;
    }
}
