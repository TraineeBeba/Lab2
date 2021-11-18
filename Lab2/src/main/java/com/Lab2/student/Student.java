package com.Lab2.student;

public class Student {

    private String fullname;

    private String faculty;

    private String cathedra;

    private String disciplines;

    public Student(String fullname, String faculty,
                    String cathedra, String disciplines) {
        this.fullname = fullname;
        this.faculty = faculty;
        this.cathedra = cathedra;
        this.disciplines = disciplines;
    }

    //public Student(){}

//    public static Student getInstance(){
//        return new Student();
//    }

    public static Student getInstance(String fullname, String faculty,
                                      String cathedra, String disciplines){
        return new Student(fullname, faculty, cathedra, disciplines);
    }

    @Override
    public String toString(){
        return fullname + ", " + faculty + cathedra + ", " + disciplines;
    }

    public String getFullname() {
        return fullname;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getCathedra() {
        return cathedra;
    }

    public String getDisciplines() {
        return disciplines;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setCathedra(String cathedra) {
        this.cathedra = cathedra;
    }

    public void setDisciplines(String payment) {
        this.disciplines = payment;
    }
}
