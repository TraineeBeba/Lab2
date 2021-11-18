package com.Lab2.parsers.patterns;

import com.Lab2.student.Student;

import java.io.File;
import java.util.List;

public interface Strategy {
    public List<Student> strategyParse(File xmlToRead, String strSearch);
}
