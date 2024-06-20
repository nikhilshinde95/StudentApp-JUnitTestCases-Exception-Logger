package com.org.service;

import java.util.List;

import com.org.entities.Student;

public interface StudentService {

    public List<Student> getAllStudent();

    public Student getStudent(long studentid);

    public Student addStudent(Student student);

    public Student updateStudent(Student student);

    public boolean deleteStudent(long studentid);
    
}
