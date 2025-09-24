package com.example.demo.service;

import com.example.demo.dao.StudentDAO;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service //@Bean trong IoC container
public class StudentService implements IStudentService {
    @Autowired
    private StudentDAO studentDAO;


    @Override
    public List<Student> getStudents(String keyword, String sort, String dir, int page, int size) {
        List<Student> students = studentDAO.getAllStudents();

        //Tìm kiếm
        if (!keyword.isEmpty()) {
            students = students.stream().filter(s -> s.getId().toLowerCase().contains(keyword.toLowerCase()) ||
                    s.getName().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
        }

        //Sort
        Comparator<Student> comparator;
        switch (sort) {
            case "name":
                comparator = Comparator.comparing(Student::getName);
                break;
                case "garden":
                    comparator = Comparator.comparing(Student::getGarden);
                    break;
                    default:
                        comparator = Comparator.comparing(Student::getId);
        }
        if(dir.equals("desc")) comparator = comparator.reversed(); {
            students = students.stream().sorted(comparator).collect(Collectors.toList());
        }

        //trang hiển thị
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, students.size());
        if(fromIndex > students.size()) return List.of();
        return students.subList(fromIndex, toIndex);
    }

    @Override
    public void addStudent(Student student) {
        studentDAO.insertStudent(student);
    }

    @Override
    public Student findStudentById(String id) {
        return studentDAO.findStudent(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    @Override
    public void deleteStudent(String id) {
        studentDAO.deleteStudent(id);
    }
}
