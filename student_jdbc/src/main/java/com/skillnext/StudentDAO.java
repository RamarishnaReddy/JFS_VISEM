package com.skillnext;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/skillnext";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Add Student
    public void addStudent(Student stud) throws Exception {
        String sql = "INSERT INTO student (name, sem, dept) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, stud.getName());
            stmt.setInt(2, stud.getSem());
            stmt.setString(3, stud.getDept());
            stmt.executeUpdate();
        }
    }

    // Fetch all Students
    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student e = new Student();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setSem(rs.getInt("sem"));
                e.setDept(rs.getString("dept"));
                list.add(e);
            }
        }
        return list;
    }

    // Delete Student
    public void deleteStudent(int id) throws Exception {
        String sql = "DELETE FROM student WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int x = stmt.executeUpdate();
            if (x > 0) {
                System.out.println("Student deleted successfully");
            }
        }
    }

    // Update Student
    public void updateStudent(Student stud) throws Exception {
        String sql = "UPDATE student SET name=?, sem=?, dept=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, stud.getName());
            stmt.setInt(2, stud.getSem());
            stmt.setString(3, stud.getDept());
            stmt.setInt(4, stud.getId());
            stmt.executeUpdate();
        }
    }

    // Check if Student Exists
    public boolean isExist(int id) throws Exception {
        String sql = "SELECT COUNT(*) FROM student WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Get department count
    public Map<String, Integer> getDepartmentCount() throws Exception {
        String sql = "SELECT dept, COUNT(*) as count FROM student GROUP BY dept";
        Map<String, Integer> deptCount = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                deptCount.put(rs.getString("dept"), rs.getInt("count"));
            }
        }
        return deptCount;
    }
}
