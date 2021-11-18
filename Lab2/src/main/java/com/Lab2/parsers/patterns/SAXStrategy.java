package com.Lab2.parsers.patterns;

import com.Lab2.parsers.sax.SAX;
import com.Lab2.student.Student;

import java.io.File;
import java.util.List;

public class SAXStrategy implements Strategy{
    @Override
    public List<Student> strategyParse(File xmlFile, String search) {
        System.out.println("SAX");
        SAX sax = new SAX();
        return sax.parseSAX(xmlFile,search);
    }
}