package com.Lab2.parsers.dom;

import com.Lab2.student.Student;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOM {
    private static List<Student> students = new ArrayList<>();

    public List<Student> parseDOM(File xmlFile, String search) {
        try{
            students.clear();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            NodeList studentAttributes = document.getDocumentElement().getElementsByTagName("student");
            for (int i = 0; i < studentAttributes.getLength(); i++) {
                Node student = studentAttributes.item(i);
                if (student.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) student;
                    if (studentContains(element, search)) {
                        students.add(new Student(element.getElementsByTagName("fullname").item(0).getTextContent(),
                                element.getElementsByTagName("faculty").item(0).getTextContent(),
                                element.getElementsByTagName("cathedra").item(0).getTextContent(),
                                element.getElementsByTagName("disciplines").item(0).getTextContent()));
                    }
                }
            }

        }
        catch (Exception ex){
            System.out.println("ERROR");
        }
        return students;
    }

    private boolean studentContains(Element element, String search){
        return ((element.getElementsByTagName("fullname").item(0).getTextContent().contains(search)) ||
                (element.getElementsByTagName("faculty").item(0).getTextContent().contains(search)) ||
                (element.getElementsByTagName("cathedra").item(0).getTextContent().contains(search)) ||
                (element.getElementsByTagName("disciplines").item(0).getTextContent().contains(search)));
    }
}


