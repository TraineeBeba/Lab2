package com.Lab2.parsers.sax;

import com.Lab2.student.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SAX {
    private static List<Student> students = new ArrayList<>();
    private static List<Student> filtredListOfStudents = new ArrayList<>();
    private static String lastElementName;

    public List<Student> parseSAX(File xmlFile, String search){
        try{
            students.clear();
            filtredListOfStudents.clear();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            AdvancedXMLHandler handler = new AdvancedXMLHandler();
            parser.parse(xmlFile, handler);

            for (Student student : students) {
                if(studentContains(student,search))
                    filtredListOfStudents.add(student);
            }

        }
        catch (Exception ex){
            System.out.println("ERROR");
        }
        return filtredListOfStudents;
    }

    private static class AdvancedXMLHandler extends DefaultHandler {
        private String fullname, faculty, cathedra, disciplines;


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("fullname"))
                    fullname = information;
                if (lastElementName.equals("faculty"))
                    faculty = information;
                if (lastElementName.equals("cathedra"))
                    cathedra = information;
                if (lastElementName.equals("disciplines"))
                    disciplines = information;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ( (fullname != null && !fullname.isEmpty()) &&
                    (faculty != null && !faculty.isEmpty()) &&
                    (cathedra != null && !cathedra.isEmpty()) &&
                    (disciplines != null && !disciplines.isEmpty())) {
                students.add(new Student(fullname, faculty, cathedra, disciplines));
                fullname = null;
                faculty = null;
                cathedra = null;
                disciplines = null;
            }
        }
    }

    private boolean studentContains(Student article, String search){
        return ((article.getFullname().contains(search)) ||
                (article.getFaculty().contains(search)) ||
                (article.getCathedra().contains(search)) ||
                (article.getDisciplines().contains(search)));
    }
}
