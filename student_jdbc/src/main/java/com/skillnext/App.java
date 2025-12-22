package com.skillnext;

import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n======= STUDENT MANAGEMENT =======");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Check Student Exists");
            System.out.println("6. Department-wise Count");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Sem: ");
                    int sem = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Dept: ");
                    String dept = sc.nextLine();

                    Student s = new Student(0, name, sem, dept);
                    dao.addStudent(s);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    List<Student> list = dao.getAllStudents();
                    System.out.println("\n--- Student List ---");
                    for (Student st : list) {
                        System.out.println(st);
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    if (!dao.isExist(uid)) {
                        System.out.println("ID not found!");
                        break;
                    }
                    System.out.print("Enter New Name: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter New Sem: ");
                    int usem = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Dept: ");
                    String udept = sc.nextLine();

                    Student us = new Student(uid, uname, usem, udept);
                    dao.updateStudent(us);
                    System.out.println("Student updated!");
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    if (!dao.isExist(did)) {
                        System.out.println("ID not found!");
                        break;
                    }
                    dao.deleteStudent(did);
                    break;

                case 5:
                    System.out.print("Enter ID to check: ");
                    int cid = sc.nextInt();
                    if (dao.isExist(cid)) {
                        System.out.println("Student exists!");
                    } else {
                        System.out.println("Student NOT found.");
                    }
                    break;

                case 6:
                    Map<String, Integer> deptCount = dao.getDepartmentCount();
                    System.out.println("\n--- Department-wise Student Count ---");
                    for (String d : deptCount.keySet()) {
                        System.out.println(d + " : " + deptCount.get(d));
                    }
                    break;

                case 7:
                    System.out.println("Exiting... Bye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
