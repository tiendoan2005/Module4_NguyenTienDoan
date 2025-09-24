package com.example.demo.dao;

import com.example.demo.model.Student;
import com.example.demo.until.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class StudentDAO {
    //Thêm sinh viên
    public void insertStudent(Student student) {
        String sql = "insert into student (msv, name, garden) values(?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setFloat(3, student.getGarden());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách sinh viên
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();
        String sql = "select * from student";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getString("msv"));
                student.setName(resultSet.getString("name"));
                student.setGarden(resultSet.getFloat("garden"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student findStudent(String id) {
        String sql = "select * from student where msv = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("msv"));
                student.setName(rs.getString("name"));
                student.setGarden(rs.getFloat("garden"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStudent(Student student) {
        String sql = "update student set name = ?, garden = ? where msv = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setFloat(2, student.getGarden());
            statement.setString(3, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(String id) {
        String sql = "delete from student where msv = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
