package com.Lab2.parsers.patterns;

import com.Lab2.student.Student;

import java.io.File;
import java.util.List;

public class Context {
    private Strategy strategy;
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Student> executeStrategy(File xmlFile, String search) {
        return strategy.strategyParse(xmlFile,search);
    }
}