package com.example.demo.service;

import com.example.demo.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DBStudentService implements IStudentService {

    private static EntityManager entityManager;
    private static SessionFactory sessionFactory;

    static {
        try{
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getStudents(String keyword, String sort, String dir, int page, int size) {
        String query = "select s from Student as s";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        return typedQuery.getResultList();
    }

    @Override
    public void addStudent(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    @Override
    public Student findStudentById(String id) {
        String query = "select s from Student as s where s.id = :id";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.getTransaction().begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteStudent(String id) {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Student> getAllStudents() {
        String query = "select s from Student as s";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        return typedQuery.getResultList();
    }
}
