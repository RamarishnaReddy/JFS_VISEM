package com.skillnext1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.skillnext1.Student;
import com.skillnext1.HibernateUtil;

public class StudentDAO {

    // Insert
    public void saveStudent(Student student) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Update
    public void updateStudent(int id, String email, int age) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Student student = session.get(Student.class, id);
            if (student != null) {
                student.setEmail(email);
                student.setAge(age);
                session.update(student);
                tx.commit();
            } else {
                System.out.println("Student not found!");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }

    // Retrieve by ID
    public Student getStudentById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        }
    }

    // Count students by branch
    public long countStudentsByBranch(String branch) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                    "select count(s) from Student s where s.branch = :branch",
                    Long.class
            );
            query.setParameter("branch", branch);
            return query.getSingleResult();
        }
    }
    // Retrieve students ordered by name
public List<Student> getStudentsOrderByName() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        return session.createQuery(
                "from Student s order by s.name asc",
                Student.class
        ).list();
    }
}

}
