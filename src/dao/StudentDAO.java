package dao;

import model.Student;
import java.util.List;

public interface StudentDAO {

    void addStudent(Student student);
    List<Student> getAllStudents();
    void updateStudent(Student Student);
    void deleteStudent(int id);
}