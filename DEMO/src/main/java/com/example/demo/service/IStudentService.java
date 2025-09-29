package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getStudents(String keyword, String sort, String dir, int page, int size);

    void addStudent(Student student);

    Student findStudentById(String id);

    void updateStudent(Student student);

    void deleteStudent(String id);

    List<Student> getAllStudents();
}
