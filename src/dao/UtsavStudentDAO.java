package dao;

import model.Student;
import java.util.List;

public class UtsavStudentDAO implements StudentDAO {

    @Override
    public void addStudent(Student student) {
        System.out.println("Dummy DB: HELLO");
    }

    @Override
    public List<Student> getAllStudents() {
        System.out.println("Dummy DB: View students");
        return null;
    }

    @Override
    public void updateStudent(Student student) {
        System.out.println("Dummy DB: Update student");
    }

    @Override
    public void deleteStudent(int id) {
        System.out.println("Dummy DB: Delete student");
    }
}