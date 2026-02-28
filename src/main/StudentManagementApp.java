package main;

import dao.StudentDAO;
import dao.PostgresStudentDAO;
import exception.InvalidStudentDataException;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Runtime Polymorphism
        StudentDAO dao = new PostgresStudentDAO();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {

                switch (choice) {

                    case 1:
                        Student student = readStudentData();
                        dao.addStudent(student);
                        break;

                    case 2:
                        List<Student> list = dao.getAllStudents();
                        list.forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Enter ID to update: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Student updatedStudent = readStudentData();
                        updatedStudent.setId(id);
                        dao.updateStudent(updatedStudent);
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: ");
                        int deleteId = sc.nextInt();
                        dao.deleteStudent(deleteId);
                        break;

                    case 5:
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice!");

                }

            } catch (InvalidStudentDataException e) {
                System.out.println("Validation Error: " + e.getMessage());
            }
        }
    }

    private static Student readStudentData() throws InvalidStudentDataException {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Mobile: ");
        String mobile = sc.nextLine();

        validate(name, email, age, mobile);

        return new Student(name, email, age, mobile);
    }

    private static void validate(String name, String email, int age, String mobile)
            throws InvalidStudentDataException {

        if (name.isEmpty() || name.matches("\\d+"))
            throw new InvalidStudentDataException("Invalid Name!");

        if (!email.contains("@"))
            throw new InvalidStudentDataException("Invalid Email!");

        if (age <= 0)
            throw new InvalidStudentDataException("Age must be positive!");

        if (!mobile.matches("\\d{10}"))
            throw new InvalidStudentDataException("Mobile must be exactly 10 digits!");
    }
}