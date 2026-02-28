package dao;

import model.Student;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresStudentDAO implements StudentDAO {

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students(name,email,age,mobile) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getMobile());

            ps.executeUpdate();
            System.out.println("Student Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("mobile")
                );
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void updateStudent(Student student) {

        String sql = "UPDATE students SET name=?, email=?, age=?, mobile=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getMobile());
            ps.setInt(5, student.getId());

            ps.executeUpdate();
            System.out.println("Student Updated Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Student Deleted Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}