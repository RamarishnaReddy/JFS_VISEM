package com.skillnext1;

import java.util.List;
import java.util.Scanner;

import com.skillnext1.StudentDAO;
import com.skillnext1.Student;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. View All Students");
            System.out.println("4. View Student by ID");
            System.out.println("5. Count Students by Branch");
            System.out.println("6. View Students Order By Name");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1: // Insert
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Branch: ");
                    String branch = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    Student student = new Student(name, branch, email, age);
                    dao.saveStudent(student);
                    System.out.println("Student added successfully!");
                    break;

                case 2: // Update
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();

                    System.out.print("Enter New Age: ");
                    int newAge = sc.nextInt();

                    dao.updateStudent(id, newEmail, newAge);
                    break;

                case 3: // Retrieve all
                    List<Student> students = dao.getAllStudents();
                    for (Student s : students) {
                        System.out.println(
                                s.getId() + " | " +
                                s.getName() + " | " +
                                s.getBranch() + " | " +
                                s.getEmail() + " | " +
                                s.getAge()
                        );
                    }
                    break;

                case 4: // Retrieve by ID
                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();

                    Student s = dao.getStudentById(sid);
                    if (s != null) {
                        System.out.println(
                                s.getId() + " | " +
                                s.getName() + " | " +
                                s.getBranch() + " | " +
                                s.getEmail() + " | " +
                                s.getAge()
                        );
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 5: // Count by branch
                    System.out.print("Enter Branch: ");
                    String br = sc.nextLine();

                    long count = dao.countStudentsByBranch(br);
                    System.out.println("Number of students in " + br + " = " + count);
                    break;

                case 6: // Order by name
                     List<Student> orderedStudents = dao.getStudentsOrderByName();
                       for (Student s1 : orderedStudents) {
                         System.out.println(
                         s1.getId() + " | " +
                        s1.getName() + " | " +
                        s1.getBranch() + " | " +
                        s1.getEmail() + " | " +
                         s1.getAge()
        );
    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                        sc.close();
                        System.exit(0);

                
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
