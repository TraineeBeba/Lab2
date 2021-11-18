package com.Lab2.parsers.patterns;

import com.Lab2.parsers.dom.DOM;
import com.Lab2.student.Student;

import java.io.File;
import java.util.List;

public class DOMStrategy implements Strategy{
    @Override
    public List<Student> strategyParse(File xmlFile, String search) {
        System.out.println("DOM");
        DOM dom = new DOM();
        return dom.parseDOM(xmlFile,search);
    }
}