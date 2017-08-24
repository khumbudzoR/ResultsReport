package com.example.codetribe.resultsreport;

/**
 * Created by codetribe on 8/24/2017.
 */

class ResultsClass {
    String comments;

    int studentno;
    String name;
    int subject;
    String marks;
    int semester;
    // int comments;
    int grade;
    int etId;


    public void setName(String name) {
        this.name = name;
    }

    // getter
    public String getName() {
        return this.name;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getMarks() {
        return this.marks;
    }


    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getSemester() {
        return this.semester;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return this.comments;
    }


    public void serSemester(int semester) {
        this.semester = semester;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setEtId(int etId) {
        this.etId = etId;
    }

    public int getEtId() {
        return this.etId;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getSubject() {
        return this.subject;

    }


    public void setStudentno(int studentno) {
        this.studentno = studentno;
    }


    public int getStudentno() {
        return this.studentno;
    }
}