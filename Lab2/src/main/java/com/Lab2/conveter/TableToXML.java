package com.Lab2.conveter;

import com.Lab2.student.Student;

import java.io.*;
import java.util.List;

public class TableToXML {
    private static FileOutputStream out = null;

    public static void updateXML(List<Student> students){
        try {
            out = new FileOutputStream("src\\main\\java\\com\\files\\table.xml");
            BufferedWriter bo = new BufferedWriter(new OutputStreamWriter(out));
            bo.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            bo.newLine();
            bo.write("<?xml-stylesheet type=\"\" ?>");
            bo.newLine();
            bo.write("<students type=\"root\">");
            bo.newLine();
            for (int i = 0; i < students.size(); i++) {
                bo.write("    <student id=\"" + i + "\">");
                bo.newLine();
                bo.write("        <fullname>" + students.get(i).getFullname() + "</fullname>");
                bo.newLine();
                bo.write("        <faculty>" + students.get(i).getFaculty() + "</faculty>");
                bo.newLine();
                bo.write("        <cathedra>" + students.get(i).getCathedra() + "</cathedra>");
                bo.newLine();
                bo.write("        <disciplines>" + students.get(i).getDisciplines() + "</disciplines>");
                bo.newLine();
                bo.write("    </student>");
                bo.newLine();
            }
            bo.write("</students>");
            bo.close();
        } catch (Exception ex) {
            System.out.println("ERROR");
        }
    }
}
